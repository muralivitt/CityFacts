package com.app.facts.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.app.facts.R
import com.app.facts.adapters.CityListAdapter
import com.app.facts.contract.FactsContract
import com.app.facts.network.model.FactResponse
import com.app.facts.presenter.FactsPresenter
import kotlinx.android.synthetic.main.activity_facts_list.*

class FactsListActivity : AppCompatActivity(), FactsContract.View {
    private var mPresenter: FactsPresenter? = null
    private var mAdapter: CityListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts_list)
        mPresenter = FactsPresenter(this)

        mPresenter?.getFactsList()
        supportActionBar?.subtitle = "Loading.."
    }

    override fun init() {
        mAdapter = CityListAdapter(this)
        rvCityFactsList.layoutManager = LinearLayoutManager(this)
        rvCityFactsList.adapter = mAdapter

        vRefreshLayout.setOnRefreshListener {
            mPresenter?.getFactsList()
            supportActionBar?.subtitle = "Loading.."
        }
    }

    override fun onSuccess(response: FactResponse) {
        vRefreshLayout.isRefreshing = false
        supportActionBar?.subtitle = response?.title
        mAdapter?.addItems(response.rows)
    }

    override fun onError(error: String) {
        vRefreshLayout.isRefreshing = false
        supportActionBar?.subtitle = "Failed to load data !"
        println(error)
    }
}
