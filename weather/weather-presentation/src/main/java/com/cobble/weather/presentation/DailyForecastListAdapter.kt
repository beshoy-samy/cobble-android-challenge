package com.cobble.weather.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.cobble.weather.domain.entities.DailyForecast

class DailyForecastListAdapter(private val onClickListener: (Int, DailyForecast) -> Unit) :
    ListAdapter<DailyForecast, DailyForecastListAdapter.ViewHolder<ViewBinding>>(
        DailyForecastListDiffUtilsCallback()
    ) {

    override fun getItemViewType(position: Int): Int =
        if (getItem(position).isExpanded) DailyForecastExpandedViewHolder.TYPE else DailyForecastViewHolder.TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> =
        ViewHolder.create(parent, viewType)

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) =
        holder.bind(getItem(position), onClickListener)

    abstract class ViewHolder<out T : ViewBinding>(protected val binding: T) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(forecast: DailyForecast, onClickListener: (Int, DailyForecast) -> Unit)

        companion object {

            fun create(parent: ViewGroup, viewType: Int): ViewHolder<*> =
                if (viewType == DailyForecastExpandedViewHolder.TYPE)
                    DailyForecastExpandedViewHolder.from(parent)
                else DailyForecastViewHolder.from(parent)
        }
    }

}

class DailyForecastListDiffUtilsCallback : DiffUtil.ItemCallback<DailyForecast>() {

    override fun areItemsTheSame(old: DailyForecast, aNew: DailyForecast): Boolean =
        old.date == aNew.date

    override fun areContentsTheSame(old: DailyForecast, aNew: DailyForecast): Boolean = old == aNew

}