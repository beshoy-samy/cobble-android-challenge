package com.cobble.weather.presentation

import androidx.lifecycle.viewModelScope
import com.cobble.core.base.BaseViewModel
import com.cobble.core.di.CoroutineDispatcherIO
import com.cobble.core.domain.ResultModel
import com.cobble.weather.domain.entities.DailyForecast
import com.cobble.weather.domain.usecases.GetDailyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

typealias ForecastResult = ResultModel<Pair<Location, List<DailyForecast>>>

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    @CoroutineDispatcherIO private val coroutineContext: CoroutineContext
) : BaseViewModel() {

    private val _forecastResult = MutableStateFlow<ForecastResult>(ResultModel.Idle)
    val forecastResult: StateFlow<ForecastResult> get() = _forecastResult

    private lateinit var userLocation: Location
    private var forecast: List<DailyForecast> = listOf()

    init {
        getDailyWeather(mockUserLocation())
    }

    private fun mockUserLocation(): Location = mockLocations().random().also { location ->
        userLocation = location
    }

    private fun getDailyWeather(location: Location) {
        viewModelScope.launch(coroutineContext) {
            getDailyWeatherUseCase.getDailyWeather(location.lat, location.lon)
                .onStart { _forecastResult.emit(ResultModel.Progress) }
                .catch { _forecastResult.emit(ResultModel.ErrorResult(it)) }
                .collect {
                    forecast = it
                    _forecastResult.emit(ResultModel.SuccessResult(Pair(location, it)))
                }
        }
    }

    fun onForecastItemClicked(index: Int, dailyForecast: DailyForecast) {
        viewModelScope.launch(coroutineContext) {
            forecast = forecast.toMutableList().apply {
                this[index] = this[index].copy(isExpanded = dailyForecast.isExpanded.not())
            }
            _forecastResult.emit(ResultModel.SuccessResult(Pair(userLocation, forecast)))
        }
    }

    fun refresh() = getDailyWeather(userLocation)

}