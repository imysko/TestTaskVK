package com.imysko.testtaskvk.ui.components.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.testtaskvk.utils.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

open class BaseViewModel : LifecycleObserver, ViewModel() {

    protected fun <T> call(
        apiCall: suspend () -> Result<T>,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        onTimeout: (() -> Unit)? = null,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                val result = apiCall.invoke()

                result.getOrNull()?.let { value ->
                    onSuccess?.invoke(value)
                }

                result.exceptionOrNull()?.let { error ->
                    onError?.invoke(error)
                }
            } catch (ex: NoConnectivityException) {
                onNetworkUnavailable?.invoke()
            } catch (ex: SocketTimeoutException) {
                onTimeout?.invoke()
                onError?.invoke(ex)
            }
        }
    }
}
