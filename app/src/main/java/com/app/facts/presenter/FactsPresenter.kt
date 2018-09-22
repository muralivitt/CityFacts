package com.app.facts.presenter

import com.app.facts.contract.FactsContract
import com.app.facts.model.FactsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FactsPresenter(factView: FactsContract.View) : FactsContract.Presenter {
    private var mFactView: FactsContract.View = factView
    private var mFactsModel = FactsModel()

    init {
        mFactView?.init()
    }


    override fun getFactsList() {
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
                            mFactView?.onError(e.message.toString())
                        }
                )
    }
}