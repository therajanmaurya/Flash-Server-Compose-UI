package com.flash.data.remote.service

import com.flash.domain.flow.model.FlashEntity

interface RemoteHelper {

    suspend fun getFlashServerComposeUiData(): FlashEntity?
}