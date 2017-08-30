package us.tadaima.weatherunderground.di

import android.content.Context
import dagger.Module
import dagger.Provides
import us.tadaima.weatherunderground.WUApplication
import us.tadaima.weatherunderground.data.rest.ApiService
import us.tadaima.weatherunderground.data.rest.RestClient
import javax.inject.Singleton

/**
 * Created by korji on 8/29/17.
 */
@Module
class AppModule(val wuApplication: WUApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return wuApplication
    }

    @Provides
    @Singleton
    fun provideApplication(): WUApplication = wuApplication

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RestClient().apiService
    }
}