package com.mu.lastkey.feature.login.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mu.lastkey.core.ui.components.Buttons
import com.mu.lastkey.core.ui.components.Loaders
import com.mu.lastkey.core.ui.components.TextFields
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: SignInViewModel by inject()
        val appNavigation: AppNavigation by inject()
        val navigator = LocalNavigator.currentOrThrow

        SignInUiScreen(
            viewModel = viewModel,
            navigateToSignUp = {
                appNavigation.signUp(navigator)
            },
            navigateToDashboard = {
                appNavigation.dashboard(navigator)
            }
        )
    }
}

@Composable
private fun SignInUiScreen(
    viewModel: SignInViewModel,
    navigateToSignUp: () -> Unit,
    navigateToDashboard: () -> Unit
) {
    val state: SignInUiState by viewModel.uiState.collectAsState(SignInUiState.Idle)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    LaunchedEffect((state as? SignInUiState.SignIn)?.message?.id) {
        val message = (state as? SignInUiState.SignIn)?.message?.message
        if (!message.isNullOrBlank()) {
            snackbarHostState.showSnackbar(message)
            viewModel.onMessageShown((state as SignInUiState.SignIn).message.id)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when (state) {
            is SignInUiState.SignIn -> {
                SignInUiScreenContent(
                    modifier = Modifier.padding(paddingValues),
                    state = state as SignInUiState.SignIn,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    signIn = viewModel::signIn,
                    signUp = viewModel::signUp
                )
            }

            is SignInUiState.SignUp -> {
                viewModel.onSignUp()
                navigateToSignUp()
            }

            is SignInUiState.Success -> {
                navigateToDashboard()
            }

            else -> {}
        }
    }
}

@Composable
internal fun SignInUiScreenContent(
    modifier: Modifier,
    state: SignInUiState.SignIn,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    signIn: () -> Unit,
    signUp: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Logo()
        Spacer(modifier = Modifier.size(LastKeyTheme.spacing.ten.dp))
        SignIn(
            email = state.email,
            password = state.password,
            signInLoading = state.loading,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            signIn = signIn,
            signUp = signUp
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Logo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LastKeyTheme.spacing.three.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.three.dp))

        Image(
            modifier = Modifier.height(70.dp),
            painter = painterResource(LastKeyTheme.icons.AppIcon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.one.dp))

        Text(
            text = LastKeyTheme.strings.welcomeToAppLabel,
            style = LastKeyTheme.typo.headlineSmall
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.one.dp))

        Text(
            text = LastKeyTheme.strings.appDescription,
            style = LastKeyTheme.typo.bodyMedium
        )
    }
}

@Composable
private fun SignIn(
    email: String,
    password: String,
    signInLoading: Boolean,
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

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.one.dp))

        // NOTE: Forgot Password not yet implemented
        /*
        Text(
            modifier = Modifier.align(Alignment.End),
            text = LastKeyTheme.strings.forgotPassword,
            style = LastKeyTheme.typo.bodySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )*/

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.three.dp))

        Buttons.Primary(
            modifier = Modifier.fillMaxWidth(),
            onClick = signIn,
            content = {
                if (!signInLoading) {
                    Text(LastKeyTheme.strings.login)
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
            onClick = signUp,
            content = {
                Text(LastKeyTheme.strings.createNewAccount)
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
