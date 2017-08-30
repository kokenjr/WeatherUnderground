package us.tadaima.weatherunderground.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_observation.view.*
import us.tadaima.weatherunderground.R
import us.tadaima.weatherunderground.R.id.*
import us.tadaima.weatherunderground.common.Constants
import us.tadaima.weatherunderground.data.domain.Observation
import us.tadaima.weatherunderground.util.DateUtilities

/**
 * Created by korji on 8/29/17.
 */
class ObservationsAdapter(var observations: List<Observation>, var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var animOffset: Long = 0
    private var stopAnimation = false

    override fun getItemCount(): Int {
        return observations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_observation, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val observation = observations[position]
        val iconUrl = Constants.ICON_URL + observation.icon + ".gif"
        Glide.with(context).load(iconUrl).into(holder.itemView.ivObservationIcon)
        holder.itemView.tvObservationTime.text = DateUtilities.getFormattedObservationTime(observation.weatherDate.date)
        holder.itemView.tvCondition.text = observation.conditions
        holder.itemView.tvTempHigh.text = "${observation.temperature}º"
        holder.itemView.tvHumidity.text = context.getString(R.string.humidity) +
                " ${observation.humidity}%"
        holder.itemView.tvPressure.text = context.getString(R.string.pressure) +
                " ${observation.pressure} " + context.getString(R.string.pressure_unit)

        val elevation = position + 1
        ViewCompat.setElevation(holder.itemView.rlObservation, elevation.toFloat())
        val layoutParams = (holder.itemView.rlObservation.layoutParams as RecyclerView.LayoutParams)
        if (position == observations.size - 1){
            layoutParams.setMargins(layoutParams.rightMargin, 0, layoutParams.leftMargin,
                    context.resources.getDimension(R.dimen.observations_bottom_padding).toInt())
        } else {
            layoutParams.setMargins(layoutParams.rightMargin, 0, layoutParams.leftMargin,
                    context.resources.getDimension(R.dimen.card_overlap_margin).toInt())
        }

        if (!stopAnimation) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.card_slide_up_and_fade_in)
            animation.startOffset = animOffset
            holder.itemView.rlObservation.startAnimation(animation)
            animOffset += 200
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun stopAnimation() {
        this.stopAnimation = true
    }

    fun hasStoppedAnimation(): Boolean {
        return stopAnimation
    }

}

