package com.barbasdev.discover.presentation.redux

import com.barbasdev.base.redux.Store
import io.reactivex.disposables.Disposable

class DiscoverStore(
        state: DiscoverState,
        reducer: DiscoverReducer
) : Store<DiscoverAction, DiscoverState, DiscoverReducer>(state, reducer) {

    override fun subscribe(
            onNext: (DiscoverState) -> Unit,
            onError: (Throwable) -> Unit
    ): Disposable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispatch(action: DiscoverAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
