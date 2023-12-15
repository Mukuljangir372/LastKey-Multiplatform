package com.mu.lastkey.core.data

import com.mu.lastkey.core.domain.InputValidator

internal class InputValidatorImpl : InputValidator {
    override fun isValidPhone(phone: String): Boolean {
        return REGEX_PHONE.matches(phone)
    }

    override fun isValidEmail(email: String): Boolean {
        return REGEX_EMAIL.matches(email)
    }

    override fun isValidPassword(password: String): Boolean {
        return password.length > 5
    }

    companion object {
        private val REGEX_PHONE = """^(?:\+?(\d{1,3}))?[-.\s]?(\d{3})[-.\s]?(\d{3})[-.\s]?(\d{4})$""".toRegex()
        private val REGEX_EMAIL = """^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""".toRegex()
    }
}
