package com.app.facts.contract

import com.app.facts.network.model.FactResponse

interface FactsContract{

    interface View{
        fun init()
        fun onSuccess(list:ArrayList<FactResponse.FactItem>)
        fun onError(error:String)
    }


    interface Presenter {
        fun getFactsList()
    }
}