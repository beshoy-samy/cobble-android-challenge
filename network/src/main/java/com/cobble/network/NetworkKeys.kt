package com.cobble.network

object NetworkKeys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String

    external fun getAPIKey(): String
}