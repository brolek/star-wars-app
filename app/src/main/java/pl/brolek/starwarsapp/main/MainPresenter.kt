package pl.brolek.starwarsapp.main

import io.reactivex.disposables.Disposable
import pl.brolek.starwarsapp.main.dataProvider.MainDataProvider
import pl.brolek.starwarsapp.utils.DisposableUtils
import javax.inject.Inject

/**
 * Created by Bartłomiej Rolek on 2018-01-06
 */
class MainPresenter @Inject constructor(private val mainDataProvider: MainDataProvider) : MainContract.Presenter {

    var view: MainContract.View? = null
    private var peopleDisposable: Disposable? = null
    private var vehiclesDisposable: Disposable? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        if (view != null) view = null
        DisposableUtils.dispose(peopleDisposable)
    }

    override fun getPeople() {
        peopleDisposable = mainDataProvider
                .getPeople()
                .subscribe({ view?.showPeople(it.results) }, { t: Throwable -> view?.showError(t.message.toString()) })
    }

    override fun getVehicles() {
        vehiclesDisposable = mainDataProvider
                .getVehicles()
                .subscribe({view?.showVehicles(it.results)}, { t: Throwable -> view?.showError(t.message.toString()) } )
    }


}