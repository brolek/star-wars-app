package pl.brolek.starwarsapp.app

import android.app.Application

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}