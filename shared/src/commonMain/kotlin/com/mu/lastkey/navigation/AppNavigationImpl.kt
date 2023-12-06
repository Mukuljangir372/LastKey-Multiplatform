package com.mu.lastkey.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.mu.lastkey.core.ui.navigation.AppNavigation
import com.mu.lastkey.dashboard.DashboardScreen
import com.mu.lastkey.feature.home.ui.HomeScreen
import com.mu.lastkey.feature.login.ui.signin.SignInScreen
import com.mu.lastkey.feature.login.ui.signup.SignUpScreen
import com.mu.lastkey.feature.splash.ui.SplashScreen

internal class AppNavigationImpl : AppNavigation {
    override fun initialScreen(): Screen {
        return SplashScreen()
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

    override fun dashboard(navigator: Navigator): AppNavigation {
        navigator.push(DashboardScreen())
        return this
    }

    override fun home(navigator: Navigator): AppNavigation {
        navigator.push(HomeScreen())
        return this
    }
}
