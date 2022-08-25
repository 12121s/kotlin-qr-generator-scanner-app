package com.illis.qrgenerator

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var qrCode = ""
        set(value) {
            savedStateHandle.set("QR CODE",value)
            field = value
        }
    init {
        savedStateHandle.get<String>("QR CODE")?.run {
            qrCode = this
        }
    }

}