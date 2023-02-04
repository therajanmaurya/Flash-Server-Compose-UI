package com.flash.compose

import androidx.lifecycle.ViewModel
import com.flash.compose.models.ViewState
import com.flash.domain.flow.model.FlashEntity
import com.flash.domain.flow.usecase.FlashUseCase
import com.flash.domain.flow.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val flashUseCase: FlashUseCase
) : ViewModel() {
    fun getFlashServerComposeUiData(): Flow<ViewState<FlashEntity?>> = flow {
        flashUseCase.getServerDrivenUiData()
            .catch {
                emit(ViewState.Error(it.message ?: "Error"))
            }.collect {
                when (it) {
                    is DataState.Error -> emit(ViewState.Error(it.exception.message ?: "Error"))
                    is DataState.Loading -> emit(ViewState.Loading)
                    is DataState.Success -> emit(ViewState.Success(it.data))
                }
            }
    }
}