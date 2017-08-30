package us.tadaima.weatherunderground.ui.home

import us.tadaima.weatherunderground.data.domain.DailySummary
import us.tadaima.weatherunderground.data.domain.Observation

/**
 * Created by korji on 8/29/17.
 */
interface HomeContract {
    interface View {
        fun showLoadingDialog()
        fun dismissLoadingDialog()
        fun showErrorMessage(messageId: Int)
        fun setDailySummary(dailySummary: DailySummary)
        fun setObservationData(observations: List<Observation>)
        fun setSummaryDate(summaryDate: String)
    }

    interface Presenter {

        fun takeView(homeView: HomeContract.View)
        fun dropView()
        fun getWeather()

    }
}