package pl.brolek.starwarsapp.main

import pl.brolek.starwarsapp.main.data.MainModels

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
interface MainContract {

    interface View {
        fun showPeople(peopleList: List<MainModels.Person>)
        fun showError(message: String)
    }

    interface Presenter {
        fun attachView(view: MainContract.View)
        fun detachView()
        fun getPeople()
    }
}