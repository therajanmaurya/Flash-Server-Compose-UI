package com.flash.domain.flow.repository

import com.flash.domain.flow.model.FlashEntity
import com.flash.domain.flow.utils.DataState
import kotlinx.coroutines.flow.Flow

interface FlashRepository {

    suspend fun getFlashServerComposeUiData(): Flow<DataState<FlashEntity?>>
}