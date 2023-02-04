package com.flash.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import com.flash.compose.models.ViewState
import com.flash.views.SetView
import com.flash.views.ShowError
import com.flash.views.ShowLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = "Loading Flash-Server-Compose-UI", block = {
                lifecycleScope.launch {
                    viewModel
                        .getFlashServerComposeUiData()
                        .catch { setContent { ShowError() } }
                        .collect {
                            when (it) {
                                is ViewState.Error -> setContent { ShowError() }
                                is ViewState.Loading -> setContent { ShowLoading() }
                                is ViewState.Success -> {
                                    setContent {
                                        SetView(
                                            data = it.data?.data!!,
                                            applicationContext = applicationContext
                                        )
                                    }
                                }
                            }
                        }
                }
            })
        }
    }
}
