package com.mu.lastkey.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.feature.login.ui.signin.SignInScreen
import com.mu.lastkey.feature.login.ui.signup.SignUpScreen

class AppNavigationImpl: AppNavigation {
    override fun initialScreen(): Screen {
        return SignInScreen()
    }

    override fun pop(navigator: Navigator): AppNavigation {
        navigator.pop()
        return this
    }

    override fun signIn(navigator: Navigator): AppNavigation {
        navigator.push(SignInScreen())
        return this
    }

    override fun signUp(navigator: Navigator): AppNavigation {
        navigator.push(SignUpScreen())
        return this
    }
}