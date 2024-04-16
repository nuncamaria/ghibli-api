package com.nuncamaria.network.errormanager

import com.nuncamaria.network.exceptions.ResourceNotFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException

object NetworkErrorManager {
    private const val ERROR_TEMPLATE = "ERROR_%s"
    private const val ERROR_UNEXPECTED_DEFAULT = "ERROR_unexpected_default"
    private const val NOT_FOUND_EXCEPTION = 404

    fun getException(cause: Throwable): Exception =
        when (cause) {
            is RedirectResponseException -> {
                Exception(String.format(ERROR_TEMPLATE, cause.localizedMessage))
            }

            is ClientRequestException -> {
                Exception(String.format(ERROR_TEMPLATE, cause.localizedMessage))
            }

            is ServerResponseException -> {
                Exception(String.format(ERROR_TEMPLATE, cause.localizedMessage))
            }

            is IllegalArgumentException -> {
                Exception(String.format(ERROR_TEMPLATE, cause.localizedMessage))
            }

            is JsonConvertException -> {
                Exception(String.format(ERROR_TEMPLATE, cause.localizedMessage))
            }

            else -> {
                Exception("$ERROR_UNEXPECTED_DEFAULT : ${cause.localizedMessage}")
            }
        }

    fun getExceptionTypeByCode(cause: Exception, code: Int): Exception =
        when (code) {
            NOT_FOUND_EXCEPTION -> {
                ResourceNotFoundException()
            }

            else -> {
                getException(cause)
            }
        }
}