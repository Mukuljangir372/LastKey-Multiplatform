package com.mu.lastkey.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.mu.lastkey.core.ui.components.PrimaryTextField
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
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()
            SignIn()
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Logo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
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
    PrimaryTextField(value = "username") {
    }
}
