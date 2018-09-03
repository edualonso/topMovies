package com.barbasdev.base.redux

/**
 * Used to move from one [State] to another when an [Action] is triggered.
 */
interface Reducer<S: State, A: Action> {
    fun reduce(oldState: S, action: A): S
}
