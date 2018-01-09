package pl.brolek.starwarsapp.network

import io.reactivex.Observable
import pl.brolek.starwarsapp.main.data.MainModels
import retrofit2.http.GET

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
interface NetworkService {

    @GET("people/")
    fun getPeople(): Observable<MainModels.PersonResult>

    @GET("vehicles/")
    fun getVehicles(): Observable<MainModels.VehicleResult>
}