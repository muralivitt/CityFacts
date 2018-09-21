package com.app.facts.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.facts.R
import com.app.facts.contract.FactsContract
import com.app.facts.network.model.FactResponse
import com.app.facts.presenter.FactsPresenter

class FactsListActivity : AppCompatActivity(),FactsContract.View {

    private var mPresenter:FactsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)

        mPresenter = FactsPresenter(this)
    }

    override fun init() {
        //Initialize the views
    }

    override fun onSuccess(list: ArrayList<FactResponse.FactItem>) {

    }

    override fun onError(error: String) {

    }
}
