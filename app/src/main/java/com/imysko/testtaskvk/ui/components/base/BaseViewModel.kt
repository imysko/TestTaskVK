package com.imysko.testtaskvk.ui.components.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.testtaskvk.domain.entities.NetworkStatus
import com.imysko.testtaskvk.utils.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

open class BaseViewModel : LifecycleObserver, ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _networkStatus = MutableStateFlow(NetworkStatus.Available)
    val networkStatus: StateFlow<NetworkStatus>
        get() = _networkStatus.asStateFlow()

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
                    _networkStatus.value = NetworkStatus.Available
                }

                result.exceptionOrNull()?.let { error ->
                    onError?.invoke(error)
                }
            } catch (ex: NoConnectivityException) {
                _networkStatus.value = NetworkStatus.Unavailable
                onNetworkUnavailable?.invoke()
            } catch (ex: SocketTimeoutException) {
                onTimeout?.invoke()
                onError?.invoke(ex)
            }
        }
    }
}
