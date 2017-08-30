package us.tadaima.weatherunderground.home

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import us.tadaima.weatherunderground.data.domain.DailySummary

import us.tadaima.weatherunderground.data.domain.History
import us.tadaima.weatherunderground.data.domain.Observation
import us.tadaima.weatherunderground.data.domain.WeatherDate
import us.tadaima.weatherunderground.data.repository.WeatherDataSource
import us.tadaima.weatherunderground.data.repository.WeatherRepository
import us.tadaima.weatherunderground.data.rest.ApiService
import us.tadaima.weatherunderground.data.rest.response.WeatherResponse
import us.tadaima.weatherunderground.ui.home.HomeContract
import us.tadaima.weatherunderground.ui.home.HomePresenter
import java.util.*

/**
 * Created by korji on 8/30/17.
 */
class HomePresenterTest {

    @Mock lateinit var weatherRepository: WeatherRepository
    @Mock lateinit var homeView: HomeContract.View

    lateinit var weatherCallbackCaptor: ArgumentCaptor<WeatherDataSource.WeatherCallback>
    lateinit var homePresenter: HomePresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        weatherCallbackCaptor = ArgumentCaptor.forClass(WeatherDataSource.WeatherCallback::class.java)
        homePresenter = HomePresenter(weatherRepository)
        homePresenter.takeView(homeView)
    }

    @Test
    fun getWeather_success(){
        homePresenter.getWeather()
        verify(homeView).showLoadingDialog()

        verify(weatherRepository).getWeather(capture(weatherCallbackCaptor))
        val weatherDate = WeatherDate(Date())
        val observation = Observation(weatherDate, 1f, "conds", 1, 1f, "icon")
        val dailySummary = DailySummary(weatherDate, 1f, 1f, 1f, 1, 1, 1f)
        val history = History(listOf(observation), listOf(dailySummary), weatherDate)
        val weatherResponse = WeatherResponse(history)
        weatherCallbackCaptor.value.onSuccess(weatherResponse)

        verify(homeView).dismissLoadingDialog()
        verify(homeView).setSummaryDate(any())
        verify(homeView).setDailySummary(any())
        verify(homeView).setObservationData(any())
        verify(homeView, never()).showErrorMessage(any())
    }

    @Test
    fun getWeather_failure(){
        homePresenter.getWeather()
        verify(homeView).showLoadingDialog()

        verify(weatherRepository).getWeather(capture(weatherCallbackCaptor))
        weatherCallbackCaptor.value.onFailure(Throwable("error occurred"))
        verify(homeView).dismissLoadingDialog()
        verify(homeView).showErrorMessage(any())
        verify(homeView, never()).setSummaryDate(any())
        verify(homeView, never()).setDailySummary(any())
        verify(homeView, never()).setObservationData(any())
    }
}