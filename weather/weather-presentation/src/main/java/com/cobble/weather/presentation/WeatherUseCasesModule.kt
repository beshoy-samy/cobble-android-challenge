package com.cobble.weather.presentation

import com.cobble.weather.domain.contracts.WeatherRepository
import com.cobble.weather.domain.usecases.GetDailyWeatherUseCase
import com.cobble.weather.domain.usecases.GetDailyWeatherUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object WeatherUseCasesModule {

    @Provides
    fun provideGetDailyWeatherUseCase(weatherRepository: WeatherRepository): GetDailyWeatherUseCase =
        GetDailyWeatherUseCaseImp(weatherRepository)
}