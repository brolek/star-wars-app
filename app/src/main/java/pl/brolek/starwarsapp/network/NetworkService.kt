package pl.brolek.starwarsapp.network

import io.reactivex.Observable
import pl.brolek.starwarsapp.main.data.MainModels
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
interface NetworkService {

    @GET("people/")
    fun getPeople(@Query("page") page: Int): Observable<MainModels.PersonResult>

    @GET("vehicles/")
    fun getVehicles(@Query("page") page: Int): Observable<MainModels.VehicleResult>

    @GET("starships/")
    fun getStarships(@Query("page") page: Int): Observable<MainModels.StarshipResult>
}