package com.kotlin.learn.core.common.util.listener

interface CommonListener {

    fun restartTask()

    fun mainErrorHandler(
        code: String?,
        title: String? = null,
        message: String? = null,
        httpCode: String? = null
    )

    fun showProgressDialog(isShow: Boolean)
}