package com.flash.compose.di

import com.flash.data.repository.FlashUiRepositoryImpl
import com.flash.domain.flow.repository.FlashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlashServerComposeUiRepositoryModule {

    @Singleton
    @Provides
    fun provideFlashRepository(
        flashUiRepositoryImpl: FlashUiRepositoryImpl
    ): FlashRepository = flashUiRepositoryImpl
}