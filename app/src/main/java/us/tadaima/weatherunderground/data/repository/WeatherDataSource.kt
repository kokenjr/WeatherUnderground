package us.tadaima.weatherunderground.data.repository

import us.tadaima.weatherunderground.data.rest.response.WeatherResponse

/**
 * Created by korji on 8/29/17.
 */
interface WeatherDataSource {
    interface WeatherCallback {
        abstract fun onSuccess(weatherResponse: WeatherResponse)

        abstract fun onFailure(t: Throwable)
    }

    abstract fun getWeather(weatherCallback: WeatherCallback)
}