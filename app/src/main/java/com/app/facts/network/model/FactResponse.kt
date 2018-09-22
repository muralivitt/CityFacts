package com.app.facts.network.model

import java.io.Serializable

data class FactResponse(
        val title: String,
        val rows: ArrayList<FactItem> = ArrayList()){

    inner class FactItem : Serializable{
        var title =""
        var description = ""
        var imageHref = ""
    }
}