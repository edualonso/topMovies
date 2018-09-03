package com.barbasdev.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val disposables = CompositeDisposable()


    override fun onStop() {
        super.onStop()

        disposables.clear()
    }
}
