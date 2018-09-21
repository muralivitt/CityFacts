package com.app.facts.presenter

import com.app.facts.contract.FactsContract
import com.app.facts.network.APIClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FactsPresenter(factView:FactsContract.View) : FactsContract.Presenter{
    private var mFactView:FactsContract.View = factView

    init {
        mFactView?.init()
    }


    override fun getFactsList() {
        APIClient()
                .getAPIService()
                .getFactsList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { response ->
                            mFactView?.onSuccess(response.rows)
                        },
                        onError = {
                            e-> e.printStackTrace()
                            mFactView?.onError(e.message.toString())
                        }
                )
    }
}