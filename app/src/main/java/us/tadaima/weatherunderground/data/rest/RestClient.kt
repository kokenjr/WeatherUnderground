package us.tadaima.weatherunderground.data.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import us.tadaima.weatherunderground.common.Constants
import us.tadaima.weatherunderground.data.rest.adapter.GsonDateAdapter
import java.util.*

/**
 * Created by korji on 8/29/17.
 */
class RestClient {
    val apiService: ApiService
    init {
        val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, GsonDateAdapter())
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.REST_ENDPOINT_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }
}