package com.flash.compose.di

import com.flash.data.remote.service.RemoteService
import com.flash.data.remote.service.RemoteHelper
import com.flash.data.remote.service.RemoteHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideApiService(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://gist.githubusercontent.com/")
        .build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteHelper(remoteServiceImpl: RemoteHelperImpl): RemoteHelper = remoteServiceImpl
}