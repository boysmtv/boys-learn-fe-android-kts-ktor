package com.kotlin.learn.core.data.repository

import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.RegisterReqModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun postAuthorization(model: AuthGoogleSignInModel) : Flow<Result<Unit>>

    fun getAuthorization(id: String, resources: Any): Flow<Result<Any?>>

}