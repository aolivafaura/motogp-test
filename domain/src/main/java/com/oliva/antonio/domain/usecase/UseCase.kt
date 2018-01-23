package com.oliva.antonio.domain.usecase

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by antonio
 */

abstract class UseCase<T, in Params> {

    /*
     * On this project, those schedulers will be always the same and provided by dagger.
     * There is no advantage then injecting them on children constructor vs injecting them directly
     * here
     */
    @field:[Inject Named("uiThread")] lateinit var postExecutionThread: Scheduler
    @field:[Inject Named("workerThread")] lateinit var workerThread: Scheduler

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: Params): Flowable<T>

    /**
     * Execute use case logic for the parameters supplied
     * @param observer Object to be notified
     * @param params   Use case params
     */
    fun execute(observer: ResourceSubscriber<T>, params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(workerThread)
                .observeOn(postExecutionThread)

        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}