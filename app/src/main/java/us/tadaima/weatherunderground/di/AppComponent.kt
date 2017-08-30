package us.tadaima.weatherunderground.di

import dagger.Component
import us.tadaima.weatherunderground.ui.home.HomeActivity
import javax.inject.Singleton

/**
 * Created by korji on 8/29/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class)
)
interface AppComponent {
    fun inject(homeActivity: HomeActivity)

}