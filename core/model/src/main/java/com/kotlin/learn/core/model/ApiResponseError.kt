package com.kotlin.learn.core.model

sealed class ApiResponse<out O, out E> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<O>(val body: O) : ApiResponse<O, Nothing>()

    sealed class Error<E> : ApiResponse<Nothing, E>() {
        /**
         * Represents server (50x) and client (40x) errors.
         */
        data class HttpError<E>(val code: Int, val errorBody: E?) : Error<E>()

        /**
         * Represent IOExceptions and connectivity issues.
         */
        object NetworkError : Error<Nothing>()

        /**
         * Represent SerializationExceptions.
         */
        object SerializationError : Error<Nothing>()
    }
}