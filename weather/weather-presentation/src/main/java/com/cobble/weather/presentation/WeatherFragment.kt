package com.cobble.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cobble.core.base.BaseFragment
import com.cobble.core.domain.ResultModel
import com.cobble.core.utils.networkErrorMessage
import com.cobble.core.utils.shortToast
import com.cobble.core.utils.withLinearSpaceItemDecoration
import com.cobble.weather.domain.entities.DailyForecast
import com.cobble.weather.presentation.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>() {

    override val viewBinder: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding =
        FragmentWeatherBinding::inflate

    private val dailyForecastAdapter: DailyForecastListAdapter by lazy {
        DailyForecastListAdapter(this::onForecastItemClicked)
    }

    override fun onBindFinished(savedInstanceState: Bundle?) {
        setupForecastRecycler()

        lifecycleScope.launch {

            viewModel.forecastResult.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { renderForecast(it) }
        }
    }

    private fun renderForecast(result: ForecastResult) {
        when (result) {
            is ResultModel.Progress -> binding.forecastSwipeRefresh.isRefreshing = true
            is ResultModel.ErrorResult -> {
                requireContext().shortToast(
                    result.throwable.networkErrorMessage(requireContext())
                        ?: result.throwable.message ?: "Error..."
                )
                binding.forecastSwipeRefresh.isRefreshing = false
            }
            is ResultModel.SuccessResult -> {
                binding.locationTv.text = result.data.first.name
                dailyForecastAdapter.submitList(result.data.second)
                binding.forecastSwipeRefresh.isRefreshing = false
            }
            else -> return
        }
    }

    private fun setupForecastRecycler() {
        binding.forecastRv.withLinearSpaceItemDecoration(R.dimen.margin_small)
        binding.forecastRv.adapter = dailyForecastAdapter
        binding.forecastSwipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun onForecastItemClicked(index: Int, dailyForecast: DailyForecast) =
        viewModel.onForecastItemClicked(index, dailyForecast)


    override val viewModel: WeatherViewModel by viewModels()

    companion object {

        fun instance() = WeatherFragment()
    }
}