package com.flash.data.repository

import com.flash.data.remote.service.RemoteHelper
import com.flash.domain.flow.model.FlashEntity
import com.flash.domain.flow.repository.FlashRepository
import com.flash.domain.flow.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FlashUiRepositoryImpl @Inject constructor(
    private val remoteHelper: RemoteHelper
) : FlashRepository {

    override suspend fun getFlashServerComposeUiData(): Flow<DataState<FlashEntity?>> = flow {
        emit(DataState.Loading)
        try {
            val response = remoteHelper.getFlashServerComposeUiData()
            emit(DataState.Success(data = response))
        } catch (e: Exception) {
            emit(DataState.Error(exception = e))
        }
    }
}
