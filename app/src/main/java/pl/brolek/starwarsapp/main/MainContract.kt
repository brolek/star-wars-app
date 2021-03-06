package pl.brolek.starwarsapp.main

import pl.brolek.starwarsapp.main.data.MainModels

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
interface MainContract {

    interface View {
        fun showPeople(peopleList: List<MainModels.Person>, shouldAppend: Boolean, shouldLoadMore: Boolean)
        fun showVehicles(vehiclesList: List<MainModels.Vehicle>, shouldAppend: Boolean, shouldLoadMore: Boolean)
        fun showStarships(starshipsList: List<MainModels.Starship>, shouldAppend: Boolean, shouldLoadMore: Boolean)
        fun showError(message: String)
    }

    interface Presenter {
        fun attachView(view: MainContract.View)
        fun detachView()
        fun getPeople(page: Int, shouldAppend: Boolean)
        fun getVehicles(page: Int, shouldAppend: Boolean)
        fun getStarships(page: Int, shouldAppend: Boolean)
    }
}