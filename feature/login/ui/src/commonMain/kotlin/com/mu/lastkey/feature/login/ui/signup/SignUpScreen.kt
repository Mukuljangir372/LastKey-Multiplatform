package com.mu.lastkey.feature.login.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mu.lastkey.core.ui.components.Buttons
import com.mu.lastkey.core.ui.components.Loaders
import com.mu.lastkey.core.ui.components.TextFields
import com.mu.lastkey.core.ui.components.Toolbars
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpScreen : Screen, KoinComponent {
    private val viewModel: SignUpViewModel by inject()
    private val appNavigation: AppNavigation by inject()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SignUpUiScreen(
            viewModel = viewModel,
            navigateToSignIn = {
                appNavigation.signIn(navigator)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpUiScreen(
    viewModel: SignUpViewModel,
    navigateToSignIn: () -> Unit
) {
    val state: SignUpUiState by viewModel.uiState.collectAsState(SignUpUiState.Idle)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    LaunchedEffect((state as? SignUpUiState.SignUp)?.message?.id) {
        val message = (state as? SignUpUiState.SignUp)?.message?.message
        if (!message.isNullOrBlank()) {
            snackbarHostState.showSnackbar(message)
            viewModel.onMessageShown((state as SignUpUiState.SignUp).message.id)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Toolbars.Primary(
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = navigateToSignIn) {
                        Icon(
                            painter = rememberVectorPainter(LastKeyTheme.materialIcons.Default.ArrowBack),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = LastKeyTheme.strings.createNewAccount,
                        style = LastKeyTheme.typo.titleMedium
                    )
                }
            )
        }
    ) { paddingValues ->
        when (state) {
            is SignUpUiState.SignUp -> {
                SignUpUiScreenContent(
                    modifier = Modifier.padding(paddingValues),
                    email = (state as SignUpUiState.SignUp).email,
                    password = (state as SignUpUiState.SignUp).password,
                    signUpLoading = (state as SignUpUiState.SignUp).loading,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    signIn = viewModel::signIn,
                    signUp = viewModel::signUp
                )
            }

            is SignUpUiState.SignIn -> {
                viewModel.onSignIn()
                navigateToSignIn()
            }

            is SignUpUiState.Success -> {
                // TODO: Navigate to Dashboard Screen
            }

            else -> {}
        }
    }
}

@Composable
internal fun SignUpUiScreenContent(
    modifier: Modifier,
    email: String,
    password: String,
    signUpLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
    signUp: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(LastKeyTheme.spacing.three.dp))
        SignUp(
            email = email,
            password = password,
            signUpLoading = signUpLoading,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            signIn = signIn,
            signUp = signUp
        )
    }
}

@Composable
private fun SignUp(
    email: String,
    password: String,
    signUpLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
    signUp: () -> Unit
) {
    val emailState = remember { mutableStateOf(email) }
    val passwordState = remember { mutableStateOf(password) }

    LaunchedEffect(emailState.value) {
        onEmailChange(emailState.value)
    }

    LaunchedEffect(passwordState.value) {
        onPasswordChange(passwordState.value)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LastKeyTheme.spacing.four.dp)
    ) {
        TextFields.Primary(
            modifier = Modifier.fillMaxWidth(),
            value = emailState.value,
            onValueChange = { emailState.value = it },
            placeholder = {
                Text(LastKeyTheme.strings.email)
            }
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        TextFields.Primary(
            modifier = Modifier.fillMaxWidth(),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            placeholder = {
                Text(LastKeyTheme.strings.password)
            }
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.three.dp))

        Buttons.Primary(
            modifier = Modifier.fillMaxWidth(),
            onClick = signUp,
            content = {
                if (!signUpLoading) {
                    Text(LastKeyTheme.strings.createNewAccount)
                } else {
                    Loaders.Primary(modifier = Modifier)
                }
            }
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        HorizontalOrLine()

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        Buttons.PrimaryOutlined(
            modifier = Modifier.fillMaxWidth(),
            onClick = signIn,
            content = {
                Text(LastKeyTheme.strings.login)
            }
        )
    }
}

@Composable
private fun HorizontalOrLine() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(end = LastKeyTheme.dimens.one.dp),
            thickness = 0.5.dp
        )

        Text(
            text = LastKeyTheme.strings.or,
            style = LastKeyTheme.typo.bodySmall
        )

        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(start = LastKeyTheme.dimens.one.dp),
            thickness = 0.5.dp
        )
    }
}
