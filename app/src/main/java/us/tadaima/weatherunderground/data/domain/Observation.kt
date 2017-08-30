package us.tadaima.weatherunderground.data.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by korji on 8/29/17.
 */
open class Observation (
        @SerializedName("date")
        var weatherDate: WeatherDate,
        @SerializedName("tempi")
        var temperature: Float,
        @SerializedName("conds")
        var conditions: String,
        @SerializedName("hum")
        var humidity: Int,
        @SerializedName("pressurei")
        var pressure: Float,
        var icon: String
)