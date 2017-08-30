package us.tadaima.weatherunderground

import android.app.Application
import timber.log.Timber
import us.tadaima.weatherunderground.di.AppComponent
import us.tadaima.weatherunderground.di.AppModule
import us.tadaima.weatherunderground.di.DaggerAppComponent

/**
 * Created by korji on 8/29/17.
 */
class WUApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}