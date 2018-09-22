package khee.app.vidya

import android.app.Application


/**
 * Created by murali.m on 21/11/17.
 */
class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}