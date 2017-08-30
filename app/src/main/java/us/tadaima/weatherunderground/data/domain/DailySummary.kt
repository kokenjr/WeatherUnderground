package us.tadaima.weatherunderground.data.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by korji on 8/29/17.
 */
open class DailySummary (
        var date: WeatherDate,
        @SerializedName("maxtempi")
        var maxTemperature: Float,
        @SerializedName("mintempi")
        var minTemperature: Float,
        @SerializedName("meanwindspdi")
        var meanWindSpeed: Float,
        @SerializedName("maxhumidity")
        var maxHumidity: Int,
        @SerializedName("minhumidity")
        var minHumidity: Int,
        @SerializedName("meanpressurei")
        var meanPressure: Float

)