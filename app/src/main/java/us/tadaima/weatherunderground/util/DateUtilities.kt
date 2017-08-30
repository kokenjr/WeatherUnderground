package us.tadaima.weatherunderground.util

import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import timber.log.Timber
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by korji on 8/29/17.
 */
object DateUtilities {
    private val DATE_FORMATS = arrayOf("MMMMM d, yyyy", "hh:mm a 'EST' 'on' MMMMM d, yyyy")
    fun getDate(jsonElement: JsonElement): Date? {
        val strDate = jsonElement.asString
        if (strDate.isEmpty()) {
            return null
        }

        for (format in DATE_FORMATS) {
            try {
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                simpleDateFormat.timeZone = TimeZone.getTimeZone("EST")
                if (!strDate.isEmpty()) {
                    val date = simpleDateFormat.parse(jsonElement.asString)
                    return date
                }
            } catch (e: ParseException) {
                Timber.e("Unparseable date: " + jsonElement.asString + " with format: " + format)
            }

        }
        throw JsonParseException("Unparseable date: \"" + jsonElement.asString
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS))
    }

    fun getFormattedSummaryDate(date: Date): String{
        val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
        return dateFormat.format(date.time)
    }

    fun getFormattedObservationTime(date: Date): String {
        val dateFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        return dateFormat.format(date.time)
    }
}