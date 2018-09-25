package com.app.facts.presenter

import android.content.Context
import android.net.ConnectivityManager
import com.app.facts.R
import com.app.facts.contract.FactsContract
import com.app.facts.model.FactsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import khee.app.vidya.App

class FactsPresenter(factView: FactsContract.View) : FactsContract.Presenter {
    private var mFactView: FactsContract.View = factView
    private var mFactsModel = FactsModel()

    init {
        mFactView?.init()
    }


    override fun getFactsList() {
        if (!App.isNetworkAvailable()) {
            mFactView?.onError(App.instance.resources.getString(R.string.network_error))
            return
        }

        mFactsModel
                .getFacts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mFactView?.onSuccess(response)
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mFactView?.onError(App.instance.resources.getString(R.string.response_error))
                        }
                )
    }

}