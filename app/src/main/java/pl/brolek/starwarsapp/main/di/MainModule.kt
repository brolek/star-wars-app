package pl.brolek.starwarsapp.main.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.brolek.starwarsapp.main.MainActivity
import pl.brolek.starwarsapp.main.MainContract
import pl.brolek.starwarsapp.main.MainPresenter
import pl.brolek.starwarsapp.main.dataProvider.MainDataProvider
import pl.brolek.starwarsapp.main.dataProvider.MainDataProviderImpl

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
@Module
class MainModule(private val activity: MainActivity) {

    @Provides
    @MainScope
    fun provideActivity(): Activity = activity

    @Provides
    @MainScope
    fun provideContext(): Context = activity

    @Provides
    fun provideMainPresenter(impl: MainPresenter): MainContract.Presenter = impl

    @Provides
    fun provideMainDataProvider(impl: MainDataProviderImpl): MainDataProvider = impl
}