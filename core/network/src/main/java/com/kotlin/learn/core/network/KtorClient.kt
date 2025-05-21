package com.kotlin.learn.core.network

import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.ERROR_MESSAGE
import com.kotlin.learn.core.utilities.Constant.SIXTY_LONG
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

class KtorClient(
    chuckerInterceptor: ChuckerInterceptor,
) {
    private val ktorClient = initializeKtor(chuckerInterceptor)

    private val springClient = initializeKtor(
        chuckerInterceptor = chuckerInterceptor,
        springUrl = "192.168.0.100:8080",
        springHeader = mapOf(
            "X-Api-Key" to "SECRET"
        )
    )

    internal suspend inline fun <reified Z : Any, reified T> getRequestApiWithQuery(
        resources: Z,
        query: Map<String, String>,
        path: String? = null,
        body: Z? = null,
    ): T {
        return ktorClient
            .get(resources) {
                url {
                    query.forEach { item ->
                        parameters.append(item.key, item.value)
                    }
                    path?.let {
                        appendPathSegments(it)
                    }
                    body?.let {
                        setBody(body)
                    }
                }
            }
            .body()
    }

    internal suspend inline fun <reified Z : Any, reified T> postAPIwithResponseFromSpring(
        resources: String,
        query: Map<String, String>? = null,
        path: String? = null,
        body: Z? = null,
    ): T {
        return springClient
            .post(resources) {
                url {
                    query?.let {
                        it.forEach { item ->
                            parameters.append(item.key, item.value)
                        }
                    }
                    path?.let {
                        appendPathSegments(it)
                    }
                }
                body?.let {
                    setBody(it)
                }
            }.body()
    }

    internal suspend inline fun <reified Z : Any, reified T> putAPIwithResponseFromSpring(
        resources: String,
        query: Map<String, String>? = null,
        path: String? = null,
        body: Z? = null,
    ): T {
        return springClient
            .put(resources) {
                url {
                    query?.let {
                        it.forEach { item ->
                            parameters.append(item.key, item.value)
                        }
                    }
                    path?.let {
                        appendPathSegments(it)
                    }
                }
                body?.let {
                    setBody(it)
                }
            }.body()
    }

    companion object {

        private fun initializeKtor(
            chuckerInterceptor: ChuckerInterceptor,
            springUrl: String? = null,
            springHeader: Map<String, String>? = null,
        ) = HttpClient(OkHttp) {
            callEngineKtor(this, chuckerInterceptor)
            callInstallKtor(this, springHeader)
            callDefaultRequest(this, springUrl)
            callHttpResponseValidator(this)
        }

        private fun callEngineKtor(
            httpClientConfig: HttpClientConfig<OkHttpConfig>,
            chuckerInterceptor: ChuckerInterceptor
        ) {
            httpClientConfig.apply {
                engine {
                    addInterceptor(chuckerInterceptor)
                    config {
                        connectTimeout(timeout = SIXTY_LONG, unit = TimeUnit.SECONDS)
                        writeTimeout(timeout = SIXTY_LONG, unit = TimeUnit.SECONDS)
                        readTimeout(timeout = SIXTY_LONG, unit = TimeUnit.SECONDS)
                    }
                }
            }
        }

        private fun callInstallKtor(
            httpClientConfig: HttpClientConfig<OkHttpConfig>,
            springHeader: Map<String, String>?
        ) {
            httpClientConfig.apply {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        }
                    )
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.v("Logger Ktor ->", message)
                        }
                    }
                    level = LogLevel.ALL
                }

                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(Constant.TOKEN, Constant.EMPTY_STRING)
                        }
                    }
                }

                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("Http status:", "${response.status.value}")
                    }
                }

                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    springHeader?.let { map ->
                        map.forEach {
                            header(it.key, it.value)
                        }
                    }
                }

                install(Resources)
            }
        }

        private fun callDefaultRequest(httpClientConfig: HttpClientConfig<OkHttpConfig>, springUrl: String?) {
            httpClientConfig.apply {
                defaultRequest {
                    url {
                        protocol = if (springUrl.isNullOrBlank()) URLProtocol.HTTPS else URLProtocol.HTTP
                        host = springUrl ?: Constant.BASE_URL
                    }
                }
            }
        }

        private fun callHttpResponseValidator(httpClientConfig: HttpClientConfig<OkHttpConfig>) {
            httpClientConfig.apply {
                HttpResponseValidator {
                    validateResponse { response: HttpResponse ->
                        val statusCode = response.status.value
                        when (statusCode) {
                            in 300..599 -> throw ErrorException(
                                response = response,
                                responseBody = response.bodyAsText()
                            )
                        }
                        if (statusCode >= 600) {
                            throw MissingPageException(
                                response = response,
                                responseBody = ERROR_MESSAGE
                            )
                        }
                    }
                }
            }
        }
    }
}

class MissingPageException(response: HttpResponse, responseBody: String) : ResponseException(response, responseBody) {
    override val message: String = response.status.description
}

class ErrorException(response: HttpResponse, responseBody: String) : ResponseException(response, responseBody) {
    override val message: String = responseBody
}