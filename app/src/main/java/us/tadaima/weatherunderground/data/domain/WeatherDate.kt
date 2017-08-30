package us.tadaima.weatherunderground.data.domain

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by korji on 8/29/17.
 */

open class WeatherDate (
        @SerializedName("pretty")
        var date: Date
)