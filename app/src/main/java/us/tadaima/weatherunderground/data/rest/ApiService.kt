package us.tadaima.weatherunderground.data.rest

import io.reactivex.Observable
import retrofit2.http.GET
import us.tadaima.weatherunderground.data.rest.response.WeatherResponse

/**
 * Created by korji on 8/29/17.
 */
interface ApiService {

    //NOTE: Using full path rather than params, for the purposes of this exercise
    @GET("history_20170113/q/GA/Alpharetta.json")
    fun getWeatherHistory(): Observable<WeatherResponse>
}