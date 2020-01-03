package com.quiz.client.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.firebase.iid.FirebaseInstanceId
import com.quiz.client.R

fun hasInternetConnection(context: Context): Boolean {

    var result = false


    val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    else{
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when(type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }

    return result
}

fun getApplicationToken(): String {
    return FirebaseInstanceId.getInstance().id
}

fun countScore(remainingTime:String):Int{

    val multipler :Int= 800
    val bonus:Int
    try {
        bonus = remainingTime.toInt()
    }
    catch(e:NumberFormatException){
        println("NumberFormatE.,Msg:"+e.message)
        return 0
    }
    return (multipler*bonus*0.001).toInt()
}