package pl.brolek.starwarsapp.main.di

import dagger.Subcomponent
import pl.brolek.starwarsapp.main.MainActivity

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
@MainScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun injectMainActivity(rankingActivity: MainActivity)
}