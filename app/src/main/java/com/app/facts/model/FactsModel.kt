package com.app.facts.model

import com.app.facts.contract.FactsContract
import com.app.facts.network.APIClient
import com.app.facts.network.model.FactResponse
import io.reactivex.Observable

class FactsModel : FactsContract.Model {

    override fun getFacts(): Observable<FactResponse> {
        return APIClient().getAPIService().getFactsList()
    }
}