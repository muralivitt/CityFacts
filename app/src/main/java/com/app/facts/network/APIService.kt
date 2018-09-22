package com.app.facts.network

import com.app.facts.network.model.FactResponse
import io.reactivex.Observable
import retrofit2.http.*

interface APIService {
    companion object {
        const val BASE_URL = "https://dl.dropboxusercontent.com"
        const val END_POINT = "/s/2iodh4vg0eortkl/facts.json"
    }

    @GET(END_POINT)
    fun getFactsList(): Observable<FactResponse>
}