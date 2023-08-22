package com.kotlin.learn.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.kotlin.learn.core.network.KtorClient
import com.kotlin.learn.core.network.firebase.FirebaseClient
import com.kotlin.learn.core.network.firestore.FirestoreClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class KtorModule {

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
    ): ChuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

    @Provides
    @Singleton
    fun provideKtorClient(
        chuckerInterceptor: ChuckerInterceptor,
    ): KtorClient {
        return KtorClient(chuckerInterceptor)
    }

    @Provides
    @Singleton
    fun provideFirebaseClient(
    ): FirebaseClient {
        return FirebaseClient()
    }

    @Provides
    @Singleton
    fun provideFirestoreClient(
    ): FirestoreClient {
        return FirestoreClient()
    }

}