package com.cobble.weather.presentation

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.core.text.scale
import com.cobble.weather.domain.entities.DailyForecast
import com.cobble.weather.presentation.databinding.ItemDailyForecastListBinding
import com.cobble.weather.presentation.databinding.ItemDailyForecastListExpandedBinding
import com.google.android.material.textview.MaterialTextView

class DailyForecastViewHolder(binding: ItemDailyForecastListBinding) :
    DailyForecastListAdapter.ViewHolder<ItemDailyForecastListBinding>(binding) {

    override fun bind(forecast: DailyForecast, onClickListener: (Int, DailyForecast) -> Unit) {
        binding.dateTv.spannableTitleWithValueText(
            title = if (forecast.isToday) binding.root.context.getString(R.string.today) else forecast.dayOfWeek,
            titleColorRes = R.color.white,
            value = forecast.monthTime,
            valueColorRes = R.color.secondary_text
        )
        binding.tempTv.spannableScaledTitleWithValue(forecast.temp.toString(), forecast.unit)
        binding.iconIv.setImageResource(getWeatherIconResId(forecast.weatherIcon))

        binding.root.setOnClickListener { onClickListener(layoutPosition, forecast) }
    }

    companion object {

        const val TYPE = 1

        fun from(parent: ViewGroup): DailyForecastViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDailyForecastListBinding.inflate(layoutInflater, parent, false)
            return DailyForecastViewHolder(binding)
        }
    }

}

class DailyForecastExpandedViewHolder(binding: ItemDailyForecastListExpandedBinding) :
    DailyForecastListAdapter.ViewHolder<ItemDailyForecastListExpandedBinding>(binding) {

    override fun bind(forecast: DailyForecast, onClickListener: (Int, DailyForecast) -> Unit) {
        binding.dateTv.spannableTitleWithValueText(
            title = if (forecast.isToday) binding.root.context.getString(R.string.today) else forecast.dayOfWeek,
            titleColorRes = R.color.white,
            value = forecast.monthTime,
            valueColorRes = R.color.secondary_text
        )
        binding.tempTv.spannableScaledTitleWithValue(forecast.temp.toString(), forecast.unit)
        binding.iconIv.setImageResource(getWeatherIconResId(forecast.weatherIcon))

        binding.sunriseTv.spannableTitleWithValueText(
            title = binding.root.context.getString(R.string.sunrise),
            value = forecast.sunriseTime
        )

        binding.sunsetTv.spannableTitleWithValueText(
            title = binding.root.context.getString(R.string.sunset),
            value = forecast.sunsetTime
        )

        binding.descriptionTv.spannableTitleWithValueText(
            title = binding.root.context.getString(R.string.description),
            value = forecast.weatherDesc
        )

        binding.windSpeedTv.spannableTitleWithValueText(
            title = binding.root.context.getString(R.string.wind_speed),
            value = binding.root.context.getString(R.string.wind_speed_format, forecast.windSpeed)
        )

        binding.root.setOnClickListener { onClickListener(layoutPosition, forecast) }
    }

    companion object {

        const val TYPE = 2

        fun from(parent: ViewGroup): DailyForecastExpandedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                ItemDailyForecastListExpandedBinding.inflate(layoutInflater, parent, false)
            return DailyForecastExpandedViewHolder(binding)
        }
    }

}

/**
 * @see "https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2"
 */
private fun getWeatherIconResId(weatherId: Int): Int =
    when (weatherId) {
        in 200..232 -> R.drawable.ic_weather_cloudy_storm // Thunderstorm
        in 300..321 -> R.drawable.ic_weather_cloudy_rain // Drizzle
        in 500..531 -> R.drawable.ic_weather_rain // Rain
        in 600..622 -> R.drawable.ic_weather_cloudy_snow // Snow
        in 701..781 -> R.drawable.ic_weather_cloudy // Atmosphere
        800 -> R.drawable.ic_weather_sun // Clear
        in 801..804 -> R.drawable.ic_weather_cloudy // Clouds
        else -> R.drawable.ic_weather_cloudy_sun
    }

private fun MaterialTextView.spannableTitleWithValueText(
    title: String,
    @ColorRes titleColorRes: Int = R.color.secondary_text,
    value: String,
    @ColorRes valueColorRes: Int = R.color.white
) {
    val titleColor = ContextCompat.getColor(context, titleColorRes)
    val valueColorColor = ContextCompat.getColor(context, valueColorRes)
    val spannableString = SpannableStringBuilder()
    spannableString.color(titleColor) { append(title) }
    spannableString.append("\n")
    spannableString.color(valueColorColor) { append(value) }
    text = spannableString
}

private fun MaterialTextView.spannableScaledTitleWithValue(title: String, value: String) {
    val spannableString = SpannableStringBuilder(title)
    spannableString.scale(0.4F) {
        append(value)
    }
    text = spannableString
}