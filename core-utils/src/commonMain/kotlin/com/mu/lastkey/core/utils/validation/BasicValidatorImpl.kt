package com.mu.lastkey.core.utils.validation

class BasicValidatorImpl : BasicValidator {
    override fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneRegex.matches(phoneNumber)
    }

    override fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    override fun areAllDigits(value: String): Boolean {
        return value.all { it.isDigit() }
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    companion object {
        private val phoneRegex = """^(?:\+?(\d{1,3}))?[-.\s]?(\d{3})[-.\s]?(\d{3})[-.\s]?(\d{4})$""".toRegex()
        private val emailRegex = """^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""".toRegex()
    }
}
