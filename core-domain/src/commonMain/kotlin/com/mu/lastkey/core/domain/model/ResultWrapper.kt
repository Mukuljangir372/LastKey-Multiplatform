package com.mu.lastkey.core.domain.model

sealed class ResultWrapper<T>(
    val data: T?,
    val throwable: Throwable?,
    val message: String
) {
    class Success<T>(
        data: T,
        message: String = ""
    ) : ResultWrapper<T>(
        data = data,
        message = message,
        throwable = null
    )

    class Failure<T>(
        data: T? = null,
        throwable: Throwable? = null,
        message: String
    ) : ResultWrapper<T>(
        data = data,
        throwable = throwable,
        message = message
    )

    fun <R> map(convert: (T?) -> R?): ResultWrapper<R?> {
        return when (val source = this) {
            is Success -> {
                Success(
                    data = convert(this.data),
                    message = source.message
                )
            }
            is Failure -> {
                Failure(
                    data = convert(this.data),
                    throwable = source.throwable,
                    message = source.message
                )
            }
        }
    }

    fun isSuccess(): Boolean {
        return this is Success
    }
}
