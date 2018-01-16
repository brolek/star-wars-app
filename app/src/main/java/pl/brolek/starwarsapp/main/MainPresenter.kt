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
    private var itemListDisposable: Disposable? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        if (view != null) view = null
        DisposableUtils.dispose(itemListDisposable)
    }

    override fun getPeople(page: Int, shouldAppend: Boolean) {
        DisposableUtils.dispose(itemListDisposable)
        itemListDisposable = mainDataProvider
                .getPeople(page)
                .subscribe({
                    var shouldLoadMore = true
                    if (it.next == null) {
                        shouldLoadMore = false
                    }
                    view?.showPeople(it.results, shouldAppend, shouldLoadMore)
                }, { t: Throwable -> view?.showError(t.message.toString()) })
    }

    override fun getVehicles(page: Int, shouldAppend: Boolean) {
        DisposableUtils.dispose(itemListDisposable)
        itemListDisposable = mainDataProvider
                .getVehicles(page)
                .subscribe({
                    var shouldLoadMore = true
                    if (it.next == null) {
                        shouldLoadMore = false
                    }
                    view?.showVehicles(it.results, shouldAppend, shouldLoadMore)
                }, { t: Throwable -> view?.showError(t.message.toString()) })
    }

    override fun getStarships(page: Int, shouldAppend: Boolean) {
        DisposableUtils.dispose(itemListDisposable)
        itemListDisposable = mainDataProvider
                .getStarships(page)
                .subscribe({
                    var shouldLoadMore = true
                    if (it.next == null) {
                        shouldLoadMore = false
                    }
                    view?.showStarships(it.results, shouldAppend, shouldLoadMore)
                }, { t: Throwable -> view?.showError(t.message.toString()) })
    }


}