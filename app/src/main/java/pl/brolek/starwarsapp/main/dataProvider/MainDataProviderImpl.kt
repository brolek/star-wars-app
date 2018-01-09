package pl.brolek.starwarsapp.main.dataProvider

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.brolek.starwarsapp.main.data.MainModels
import pl.brolek.starwarsapp.network.NetworkService
import javax.inject.Inject

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
class MainDataProviderImpl @Inject constructor(private val restService: NetworkService) : MainDataProvider {


    override fun getPeople(): Observable<MainModels.PersonResult> {
        return restService
                .getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getVehicles(): Observable<MainModels.VehicleResult> {
        return restService
                .getVehicles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}