package com.nuncamaria.network.client

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.parseQueryString
import okhttp3.Interceptor

@PublishedApi
internal object KtorClient {

    private var ktorClient: HttpClient? = null

    fun buildKtorClient(
        interceptors: List<Interceptor>? = null,
        engine: HttpClientEngine = OkHttp.create()
    ): HttpClient {
        return ktorClient ?: iniKtorClient(
            interceptors = interceptors,
            engine = engine
        )
    }

    private fun iniKtorClient(
        interceptors: List<Interceptor>? = null,
        engine: HttpClientEngine
    ): HttpClient {
        val httpClient = HttpClient(engine) {
            if (engine.config is OkHttpConfig) {
                (this as HttpClientConfig<OkHttpConfig>)
                interceptorsConfig(interceptors)
            }

            // default request parameters
            defaultRequest {

                url {
                    protocol = URLProtocol.HTTPS
                }
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            // default validation to throw exceptions for non-2xx responses
            expectSuccess = true

            timeOutConfig()
            loggingConfig()
            serializationConfig()
        }
        ktorClient = httpClient
        return httpClient
    }

    /**
     * Internal function to facilitates the test
     */
    internal fun destroyClient() {
        ktorClient = null
    }
}
