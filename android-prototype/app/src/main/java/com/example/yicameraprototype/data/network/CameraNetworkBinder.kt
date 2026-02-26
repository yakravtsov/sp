package com.example.yicameraprototype.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.coroutines.resume

class CameraNetworkBinder(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun bindCameraNetwork(timeoutMs: Int = 7000, retries: Int = 3): Result<Network> {
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

    fun isCameraReachable(host: String = "192.168.42.1", port: Int = 7878): Boolean = runCatching {
        Socket().use { socket ->
            socket.connect(InetSocketAddress(host, port), 1200)
        }
        true
    }.getOrElse { false }

    private suspend fun requestWifiNetwork(timeoutMs: Int): Result<Network> {
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            .build()

        return suspendCancellableCoroutine { continuation ->
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    connectivityManager.unregisterNetworkCallback(this)
                    continuation.resume(Result.success(network))
                }

                override fun onUnavailable() {
                    continuation.resume(Result.failure(IllegalStateException("Wi‑Fi unavailable")))
                }
            }

            connectivityManager.requestNetwork(request, callback, timeoutMs)
            continuation.invokeOnCancellation {
                runCatching { connectivityManager.unregisterNetworkCallback(callback) }
            }
        }
    }
}
