package us.tadaima.weatherunderground.ui.home

import us.tadaima.weatherunderground.R
import us.tadaima.weatherunderground.data.repository.WeatherDataSource
import us.tadaima.weatherunderground.data.repository.WeatherRepository
import us.tadaima.weatherunderground.data.rest.response.WeatherResponse
import us.tadaima.weatherunderground.util.DateUtilities
import javax.inject.Inject

/**
 * Created by korji on 8/29/17.
 */
class HomePresenter @Inject constructor(val weatherRepository: WeatherRepository) : HomeContract.Presenter{

    var homeView: HomeContract.View? = null

    override fun takeView(homeView: HomeContract.View) {
        this.homeView = homeView
    }

    override fun dropView() {
        homeView = null
    }

    override fun getWeather() {
        homeView!!.showLoadingDialog()
        weatherRepository.getWeather(object: WeatherDataSource.WeatherCallback{
            override fun onSuccess(weatherResponse: WeatherResponse){
                homeView?.let { view ->
                    view.dismissLoadingDialog()
                    view.setSummaryDate(DateUtilities.getFormattedSummaryDate(weatherResponse.history.weatherDate.date))
                    view.setDailySummary(weatherResponse.history.dailySummary[0])
                    view.setObservationData(weatherResponse.history.observations)
                }
            }
            override fun onFailure(t: Throwable) {
                homeView?.let { view ->
                    view.dismissLoadingDialog()
                    view.showErrorMessage(R.string.error_occurred)
                }
            }
        })


    }
}