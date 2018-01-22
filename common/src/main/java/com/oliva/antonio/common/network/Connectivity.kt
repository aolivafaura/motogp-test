package com.oliva.antonio.common.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * Created by antonio on 1/20/18.
 */
/**
 * Check device's network connectivity and speed
 * Based on Connectivity utils of {@link http://stackoverflow.com/users/220710/emil}
 */
class Connectivity(val context: Context) {

    /**
     * Get the network info
     * @return
     */
    @SuppressLint("MissingPermission")
    fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    /**
     * Check if there is any connectivity
     * @return
     */
    fun isConnected() = getNetworkInfo()?.isConnected ?: false

    /**
     * Check if there is no connectivity
     * @return
     */
    fun isNotConnected() = !isConnected()

    /**
     * Check if there is any connectivity to a Wifi network
     * @return
     */
    fun isConnectedWifi(): Boolean {
        val info = getNetworkInfo()
        return isConnected() && info!!.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Check if there is any connectivity to a mobile network
     * @return
     */
    fun isConnectedMobile(): Boolean {
        val info = getNetworkInfo()
        return isConnected() && info!!.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * Check if there is fast connectivity
     * @return
     */
    fun isConnectedFast(): Boolean {
        val info = getNetworkInfo()
        return isConnected() && isConnectionFast(info!!.type, info.subtype)
    }

    /**
     * Check if the connection is fast
     * @param type
     * *
     * @param subType
     * *
     * @return
     */
    fun isConnectionFast(type: Int, subType: Int): Boolean {
        if (type == ConnectivityManager.TYPE_MOBILE) {
            val fastConnections = arrayOf(TelephonyManager.NETWORK_TYPE_EVDO_0, // ~ 400-1000 kbps
                    TelephonyManager.NETWORK_TYPE_EVDO_A, // ~ 600-1400 kbps
                    TelephonyManager.NETWORK_TYPE_HSDPA, // ~ 2-14 Mbps
                    TelephonyManager.NETWORK_TYPE_HSPA, // ~ 700-1700 kbps
                    TelephonyManager.NETWORK_TYPE_HSUPA, // ~ 1-23 Mbps
                    TelephonyManager.NETWORK_TYPE_UMTS, // ~ 400-7000 kbps
                    TelephonyManager.NETWORK_TYPE_EHRPD, // ~ 1-2 Mbps
                    TelephonyManager.NETWORK_TYPE_EVDO_B, // ~ 5 Mbps
                    TelephonyManager.NETWORK_TYPE_HSPAP, // ~ 10-20 Mbps
                    TelephonyManager.NETWORK_TYPE_LTE) // ~ 10+ Mbps

            return subType in fastConnections
        }
        return type == ConnectivityManager.TYPE_WIFI
    }
}