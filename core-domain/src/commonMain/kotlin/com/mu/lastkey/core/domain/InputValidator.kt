package com.mu.lastkey.core.domain

interface InputValidator {
    fun isValidPhone(phone: String): Boolean
    fun isValidEmail(email: String): Boolean
    fun isValidPassword(password: String): Boolean
}