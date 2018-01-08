package pl.brolek.starwarsapp.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import pl.brolek.starwarsapp.main.di.MainComponent
import pl.brolek.starwarsapp.main.di.MainModule
import pl.brolek.starwarsapp.network.NetworkServiceModule
import javax.inject.Singleton

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */

@Singleton
@Component(modules = arrayOf(NetworkServiceModule::class))
interface AppComponent {

    fun plus(mainModule: MainModule): MainComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}