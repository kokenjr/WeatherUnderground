package us.tadaima.weatherunderground.util

import com.google.gson.JsonPrimitive
import org.junit.Assert
import org.junit.Test
import us.tadaima.weatherunderground.util.DateUtilities

/**
 * Created by korji on 8/30/17.
 */
class DateUtilitiesTest {

    @Test
    fun convertHistoryDate(){
        val jsonElement = JsonPrimitive("January 13, 2017")
        val date = DateUtilities.getDate(jsonElement)
        Assert.assertNotNull(date)
    }

    @Test
    fun convertObservationDate(){
        val jsonElement = JsonPrimitive("12:15 AM EST on January 13, 2017")
        val date = DateUtilities.getDate(jsonElement)
        Assert.assertNotNull(date)
    }

}