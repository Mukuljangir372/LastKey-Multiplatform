package com.mu.lastkey.core.utils.validation

interface BasicValidator {
    fun isPhoneNumberValid(phoneNumber: String): Boolean
    fun isEmailValid(email: String): Boolean
    fun areAllDigits(value: String): Boolean
    fun isPasswordValid(password: String): Boolean
}
