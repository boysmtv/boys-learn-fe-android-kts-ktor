package com.kotlin.learn.core.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.kotlin.learn.core.utilities.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.setBody
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient(
    chuckerInterceptor: ChuckerInterceptor,
) {

    internal val client = initializeKtor(chuckerInterceptor)

    internal suspend inline fun <reified Z : Any, reified T> sendRequestApiWithQuery(
        resources: Z,
        query: Map<String, String>,
        path: String? = null,
        body: Z? = null,
    ): T {
        return client
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

    companion object {
        private fun initializeKtor(
            chuckerInterceptor: ChuckerInterceptor,
        ) = HttpClient(OkHttp) {
            engine {
                addInterceptor(chuckerInterceptor)
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    },
                )
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(Constant.TOKEN, Constant.EMPTY_STRING)
                    }
                }
            }

            install(Resources)
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constant.BASE_URL
                }
            }
        }
    }
}