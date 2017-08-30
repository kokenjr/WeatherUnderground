package us.tadaima.weatherunderground.data.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by korji on 8/29/17.
 */
open class History (
      var observations: List<Observation>,
      @SerializedName("dailysummary")
      var dailySummary: List<DailySummary>,
      @SerializedName("date")
      var weatherDate: WeatherDate
)
