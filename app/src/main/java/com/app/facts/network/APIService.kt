package com.app.facts.network

import io.reactivex.Observable
import com.app.facts.network.model.FactResponse
import retrofit2.http.POST

interface APIService {

    companion object {
        const val BASE_URL = "https://dl.dropboxusercontent.com"
        const val END_POINT = "/s/2iodh4vg0eortkl/facts.json"
    }

    @POST(END_POINT)
    fun getFactsList(): Observable<FactResponse>

   }