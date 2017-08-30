package us.tadaima.weatherunderground.data.repository


import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import timber.log.Timber
import us.tadaima.weatherunderground.data.rest.ApiService
import us.tadaima.weatherunderground.data.rest.response.WeatherResponse
import javax.inject.Inject

/**
 * Created by korji on 8/29/17.
 */
open class WeatherRepository @Inject constructor(private val apiService: ApiService) : WeatherDataSource {

    override fun getWeather(weatherCallback: WeatherDataSource.WeatherCallback) {
        apiService.getWeatherHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                             weatherCallback.onSuccess(response)
                        },
                        { error ->
                            weatherCallback.onFailure(error)
                        }
                )
    }
}