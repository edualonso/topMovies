package com.barbasdev.base.redux

import io.reactivex.disposables.Disposable

/**
 * Where we store a concrete redux [State].
 */
abstract class Store<A: Action, S: State, R: Reducer<S, A>>(
        protected var state: S,
        protected val reducer: R
) {

    /**
     * Observers must call this method to get [State] updates.
     * @param onNext delivers updated states
     * @param onError in case something bad happens
     * @return a [Disposable] result of the subscription
     */
    abstract fun subscribe(
            onNext: (S) -> Unit,
            onError: (Throwable) -> Unit
    ): Disposable

    /**
     * Whenever there occur changes in a state due to user-triggered or asynchronous events, you
     * must call this method with the appropriate [Action].
     * @param action what happened that cause a state change.
     */
    abstract fun dispatch(action: A)
}
