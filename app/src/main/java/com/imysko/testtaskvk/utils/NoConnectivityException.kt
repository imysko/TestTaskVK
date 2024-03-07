package com.imysko.testtaskvk.utils

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}