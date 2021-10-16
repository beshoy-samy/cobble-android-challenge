package com.cobble.weather.model.repositories

import com.cobble.weather.domain.contracts.WeatherRepository
import com.cobble.weather.model.datasources.network.WeatherNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @Provides
    fun provideWeatherRepository(dataSource: WeatherNetworkDataSource): WeatherRepository =
        WeatherRepositoryImp(dataSource)

}