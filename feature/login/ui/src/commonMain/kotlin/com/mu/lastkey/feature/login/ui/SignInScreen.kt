package com.mu.lastkey.feature.login.ui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.mu.lastkey.core.ui.components.Buttons
import com.mu.lastkey.core.ui.components.TextFields
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class SignInScreen : Screen {
    @Composable
    override fun Content() {
        SignInUiScreen()
    }
}

@Composable
private fun SignInUiScreen() {
    SignInUiScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignInUiScreenContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Logo()
            }

            item {
                Spacer(modifier = Modifier.size(LastKeyTheme.spacing.ten.dp))
            }

            item {
                SignIn()
            }
        }
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
        Image(
            modifier = Modifier.size(LastKeyTheme.dimens.eight.dp),
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
private fun SignIn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LastKeyTheme.spacing.four.dp)
    ) {
        TextFields.Primary(
            modifier = Modifier.fillMaxWidth(),
            value = "Username"
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        TextFields.Primary(
            modifier = Modifier.fillMaxWidth(),
            value = "Password"
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.one.dp))

        Text(
            modifier = Modifier.align(Alignment.End),
            text = LastKeyTheme.strings.forgotPassword,
            style = LastKeyTheme.typo.bodySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.three.dp))

        Buttons.Primary(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text(LastKeyTheme.strings.login)
        }

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        HorizontalOrLine()

        Spacer(modifier = Modifier.height(LastKeyTheme.dimens.two.dp))

        Buttons.PrimaryOutlined(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text(LastKeyTheme.strings.createNewAccount)
        }
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
