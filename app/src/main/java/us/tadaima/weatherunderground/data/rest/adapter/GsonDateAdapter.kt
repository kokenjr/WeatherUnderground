package us.tadaima.weatherunderground.data.rest.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import us.tadaima.weatherunderground.util.DateUtilities
import java.lang.reflect.Type
import java.util.*

/**
 * Created by korji on 8/29/17.
 */
class GsonDateAdapter: JsonDeserializer<Date> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        return DateUtilities.getDate(json!!)
    }
}