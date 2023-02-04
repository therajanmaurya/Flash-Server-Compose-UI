package com.flash.data.remote.service

import com.flash.domain.flow.model.FlashEntity
import retrofit2.http.GET

interface RemoteService {

    @GET("therajanmaurya/3c64b1cfb47f3925588f910b27002423/raw/020c073537039d6b8e60e630262bda760ea43093/flash_server_compose_ui")
    suspend fun getFlashServerComposeUiData(): FlashEntity?
}