package pl.brolek.starwarsapp.main.dataProvider

import io.reactivex.Observable
import pl.brolek.starwarsapp.main.data.MainModels

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
interface MainDataProvider {

    fun getPeople(page: Int): Observable<MainModels.PersonResult>

    fun getVehicles(page: Int): Observable<MainModels.VehicleResult>
}