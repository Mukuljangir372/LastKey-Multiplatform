package com.mu.lastkey.feature.login.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.mu.lastkey.feature.login.domain.usecase.SignInUsecase

class SignInViewModel(private val signInUsecase: SignInUsecase) : ScreenModel
