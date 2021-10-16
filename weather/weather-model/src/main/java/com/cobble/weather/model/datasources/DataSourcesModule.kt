package com.cobble.weather.model.datasources

import com.cobble.weather.model.datasources.network.*
import com.cobble.weather.model.datasources.network.WeatherNetworkDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataSourcesModule {

    @Provides
    fun provideWeatherNetworkDataSource(weatherService: WeatherService): WeatherNetworkDataSource =
        WeatherNetworkDataSourceImp(weatherService)

}