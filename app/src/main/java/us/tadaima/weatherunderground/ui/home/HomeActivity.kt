package us.tadaima.weatherunderground.ui.home

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_home.*
import us.tadaima.weatherunderground.R
import us.tadaima.weatherunderground.WUApplication
import us.tadaima.weatherunderground.data.domain.DailySummary
import us.tadaima.weatherunderground.data.domain.Observation
import us.tadaima.weatherunderground.ui.home.adapter.ObservationsAdapter
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {
    @Inject lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        WUApplication.appComponent.inject(this)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        homePresenter.takeView(this)
        homePresenter.getWeather()
    }

    override fun onDestroy() {
        homePresenter.dropView()
        super.onDestroy()
    }

    override fun showErrorMessage(messageId: Int) {
        val snackbar = Snackbar.make(clHome, getString(messageId), Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
        snackbar.show()
    }

    override fun setSummaryDate(summaryDate: String) {
        toolbar.title = summaryDate
    }

    @SuppressLint("SetTextI18n")
    override fun setDailySummary(dailySummary: DailySummary) {
        tvSummaryTempHigh.text = "${dailySummary.maxTemperature}º"
        tvSummaryTempLow.text = "${dailySummary.minTemperature}º"
        tvSummaryHumidity.text = getString(R.string.humidity) +
                " ${dailySummary.maxHumidity}% / ${dailySummary.minHumidity}%"
        tvSummaryPressure.text = getString(R.string.pressure) + " ${dailySummary.meanPressure} " +
                getString(R.string.pressure_unit)
        tvSummaryWindSpeed.text = getString(R.string.wind) + " ${dailySummary.meanWindSpeed} " +
                getString(R.string.speed_unit)
    }

    override fun setObservationData(observations: List<Observation>) {
        rvWeatherObservations.setHasFixedSize(true)
        rvWeatherObservations.layoutManager = LinearLayoutManager(applicationContext)
        rvWeatherObservations.adapter = ObservationsAdapter(observations, applicationContext)

        rvWeatherObservations.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvWeatherObservations.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val adapter = (rvWeatherObservations.adapter as ObservationsAdapter)
                if (!adapter.hasStoppedAnimation()) {
                    adapter.stopAnimation()
                }
            }
        })
    }

    override fun showLoadingDialog() {
        progressBar.visibility = View.VISIBLE
    }

    override fun dismissLoadingDialog() {
        progressBar.visibility = View.INVISIBLE
    }
}
