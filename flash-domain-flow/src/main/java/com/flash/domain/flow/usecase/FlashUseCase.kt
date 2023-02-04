package com.flash.domain.flow.usecase

import com.flash.domain.flow.model.FlashEntity
import com.flash.domain.flow.repository.FlashRepository
import com.flash.domain.flow.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlashUseCase @Inject constructor(
    private val serverDrivenUiRepository: FlashRepository
) {

    suspend fun getServerDrivenUiData(): Flow<DataState<FlashEntity?>> =
        serverDrivenUiRepository.getFlashServerComposeUiData()

}