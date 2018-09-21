package com.app.facts.network.model

data class FactResponse(
        val title: String,
        val rows: ArrayList<FactItem> = ArrayList()){

    inner class FactItem{
        var title =""
        var description = ""
        var imageHref = ""
    }
}