package com.app.facts.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.app.facts.R
import com.app.facts.network.model.FactResponse
import com.app.facts.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {
    companion object {
        const val FACT_ITEM = "FactItem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val factItem = intent.extras.getSerializable(FACT_ITEM) as FactResponse.FactItem
        tvTitle.text = factItem.title
        tvDescription.text = factItem.description
        ImageUtils.loadImage(factItem?.imageHref, ivThumbnail)


        ivThumbnail.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                ImageUtils.imageHeight(this))
    }
}
