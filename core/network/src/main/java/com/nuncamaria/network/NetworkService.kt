package com.nuncamaria.network

import com.nuncamaria.network.client.KtorClient
import com.nuncamaria.network.errormanager.NetworkErrorManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import okhttp3.Interceptor

object NetworkService {

    private var engine: HttpClientEngine = OkHttp.create()

    suspend inline fun <reified T> createApi(
        url: String,
        httpMethod: HttpMethodRequest,
        parameters: LinkedHashMap<String, Any?>? = null,
        interceptors: List<Interceptor>? = null
    ): Result<T> = resultApiCall {
        getHttpClient(interceptors).request(url) {
            method = getHttpMethod(httpMethod)
            parameters?.forEach {
                parameter(it.key, it.value ?: "")
            }


        }.body<T>()
    }

    @PublishedApi
    internal fun getHttpClient(interceptors: List<Interceptor>? = null): HttpClient =
        KtorClient.buildKtorClient(
            interceptors = interceptors,
            engine = engine
        )

    @PublishedApi
    internal fun getHttpMethod(method: HttpMethodRequest): HttpMethod =
        when (method) {
            HttpMethodRequest.GET -> {
                HttpMethod.Get
            }

            HttpMethodRequest.POST -> {
                HttpMethod.Post
            }

            HttpMethodRequest.PUT -> {
                HttpMethod.Put
            }

            HttpMethodRequest.PATCH -> {
                HttpMethod.Patch
            }

            HttpMethodRequest.DELETE -> {
                HttpMethod.Delete
            }
        }

    @Suppress("TooGenericExceptionCaught")
    @PublishedApi
    internal inline fun <T> resultApiCall(apiCall: () -> T): Result<T> =
        try {
            Result.success(apiCall())
        } catch (e: ResponseException) {
            Result.failure(
                NetworkErrorManager.getExceptionTypeByCode(
                    e,
                    e.response.status.value
                )
            )
        } catch (e: Throwable) {
            Result.failure(NetworkErrorManager.getException(e))
        }
}