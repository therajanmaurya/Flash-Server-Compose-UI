package com.flash.data.remote.service

import javax.inject.Inject


class RemoteHelperImpl @Inject constructor(private val remoteService: RemoteService) :
    RemoteHelper {

    override suspend fun getFlashServerComposeUiData() = remoteService.getFlashServerComposeUiData()
}