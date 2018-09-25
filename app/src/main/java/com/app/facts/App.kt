package khee.app.vidya

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by murali.m on 21/11/17.
 */
class App : Application() {
    companion object {
        lateinit var instance: App
            private set

        fun isNetworkAvailable(): Boolean {
            val cm = instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo?.isConnectedOrConnecting == true
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}