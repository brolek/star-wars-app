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

    override fun getPeople(page: Int, shouldAppend: Boolean) {
        peopleDisposable = mainDataProvider
                .getPeople(page)
                .subscribe({
                    var shouldLoadMore = true
                    if (it.next == null) {
                        shouldLoadMore = false
                    }
                    view?.showPeople(it.results, shouldAppend, shouldLoadMore)
                }, { t: Throwable -> view?.showError(t.message.toString()) })
    }

    override fun getVehicles(page: Int) {
        vehiclesDisposable = mainDataProvider
                .getVehicles(page)
                .subscribe({ view?.showVehicles(it.results) }, { t: Throwable -> view?.showError(t.message.toString()) })
    }


}