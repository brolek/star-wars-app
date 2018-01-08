package pl.brolek.starwarsapp.utils

import io.reactivex.disposables.Disposable

/**
 * Created by Bartłomiej Rolek on 2018-01-08
 */
object DisposableUtils {

    fun dispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}