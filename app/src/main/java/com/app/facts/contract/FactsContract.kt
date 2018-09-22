package com.app.facts.contract

import com.app.facts.network.model.FactResponse
import io.reactivex.Observable

interface FactsContract{

    interface Model{
        fun getFacts(): Observable<FactResponse>
    }

    interface View{
        fun init()
        fun onSuccess(response:FactResponse)
        fun onError(error:String)
    }

    interface Presenter {
        fun getFactsList()
    }
}