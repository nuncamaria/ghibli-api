package com.nuncamaria.network.client

import com.nuncamaria.network.NetworkConstants
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor

internal fun HttpClientConfig<*>.timeOutConfig() {
    install(HttpTimeout) {
        socketTimeoutMillis = NetworkConstants.TIMEOUT_LONG
        requestTimeoutMillis = NetworkConstants.TIMEOUT_LONG
        connectTimeoutMillis = NetworkConstants.TIMEOUT_LONG
    }
}

internal fun HttpClientConfig<*>.loggingConfig() {
    install(Logging) {
        level = LogLevel.ALL
    }
}

internal fun HttpClientConfig<*>.serializationConfig() {
    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
            }
        )
    }
}

internal fun HttpClientConfig<OkHttpConfig>.interceptorsConfig(interceptors: List<Interceptor>?) {
    interceptors?.forEach {
        engine {
            addNetworkInterceptor(it)
        }
    }
}