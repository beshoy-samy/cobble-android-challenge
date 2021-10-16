package com.cobble.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object BaseViewModelModule {

    @Provides
    @CoroutineDispatcherMain
    fun provideCoroutineDispatcherMain(): CoroutineContext = Dispatchers.Main

    @Provides
    @CoroutineDispatcherIO
    fun provideCoroutineDispatcherIO(): CoroutineContext = Dispatchers.IO

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherMain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherIO