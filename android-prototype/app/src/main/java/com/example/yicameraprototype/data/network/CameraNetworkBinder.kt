package com.example.yicameraprototype.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.coroutines.resume

class CameraNetworkBinder(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun bindCameraNetwork(timeoutMs: Int = 7000, retries: Int = 3): Result<Network> {
        connectivityManager.activeNetwork?.let { activeNetwork ->
            if (isCameraReachable(network = activeNetwork)) {
                connectivityManager.bindProcessToNetwork(activeNetwork)
                return Result.success(activeNetwork)
            }
        }

        repeat(retries) {
            val result = requestWifiNetwork(timeoutMs)
            if (result.isSuccess) {
                val network = result.getOrThrow()
                connectivityManager.bindProcessToNetwork(network)
                return Result.success(network)
            }
        }
        return Result.failure(IllegalStateException("Camera Wi‑Fi is unavailable"))
    }

    fun unbind() {
        connectivityManager.bindProcessToNetwork(null)
    }

    fun isCameraReachable(
        host: String = "192.168.42.1",
        port: Int = 7878,
        network: Network? = null,
        attempts: Int = 1,
        connectTimeoutMs: Int = 1800,
        retryDelayMs: Long = 0
    ): Boolean = checkCameraReachability(
        host = host,
        port = port,
        network = network,
        attempts = attempts,
        connectTimeoutMs = connectTimeoutMs,
        retryDelayMs = retryDelayMs
    ).isSuccess

    fun checkCameraReachability(
        host: String = "192.168.42.1",
        port: Int = 7878,
        network: Network? = null,
        attempts: Int = 1,
        connectTimeoutMs: Int = 1800,
        retryDelayMs: Long = 0
    ): Result<Unit> {
        var lastError: Throwable? = null
        repeat(attempts.coerceAtLeast(1)) { attempt ->
            val result = runCatching {
                val socket = network?.socketFactory?.createSocket() ?: Socket()
                socket.use {
                    it.connect(InetSocketAddress(host, port), connectTimeoutMs)
                }
            }
            if (result.isSuccess) return Result.success(Unit)
            lastError = result.exceptionOrNull()
            if (retryDelayMs > 0 && attempt < attempts - 1) {
                Thread.sleep(retryDelayMs)
            }
        }
        return Result.failure(lastError ?: IllegalStateException("$host:$port is unreachable"))
    }

    private suspend fun requestWifiNetwork(timeoutMs: Int): Result<Network> {
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build()

        return withTimeoutOrNull(timeoutMs.toLong() + 500L) {
            suspendCancellableCoroutine { continuation ->
                val callback = object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        if (continuation.isActive) {
                            runCatching { connectivityManager.unregisterNetworkCallback(this) }
                            continuation.resume(Result.success(network))
                        }
                    }

                    override fun onUnavailable() {
                        if (continuation.isActive) {
                            continuation.resume(Result.failure(IllegalStateException("Wi‑Fi unavailable")))
                        }
                    }
                }

                runCatching {
                    connectivityManager.requestNetwork(request, callback, timeoutMs)
                }.onFailure { error ->
                    if (continuation.isActive) {
                        continuation.resume(Result.failure(error))
                    }
                }

                continuation.invokeOnCancellation {
                    runCatching { connectivityManager.unregisterNetworkCallback(callback) }
                }
            }
        } ?: Result.failure(IllegalStateException("Wi‑Fi request timed out"))
    }
}
