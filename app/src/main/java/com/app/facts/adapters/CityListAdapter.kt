package com.app.facts.adapters

import android.content.Context
import android.content.Intent
import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.app.facts.R
import com.app.facts.network.model.FactResponse
import com.app.facts.utils.ImageUtils
import com.app.facts.view.DetailsActivity
import com.app.facts.view.DetailsActivity.Companion.FACT_ITEM

class CityListAdapter(private val mContext: Context) : RecyclerView.Adapter<CityListAdapter.MyViewHolder>() {

    private var mCityFactsList: ArrayList<FactResponse.FactItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var factItem = mCityFactsList[position]
        holder.tvTitle.text = factItem?.title
        holder.tvDescription.text = factItem?.description
        holder.llCardItem.setOnClickListener {
            mContext.startActivity(Intent(mContext, DetailsActivity::class.java)
                    .putExtra(FACT_ITEM, factItem))
        }

        ImageUtils.loadImage(factItem?.imageHref,holder.ivThumbnail)
    }

    override fun getItemCount(): Int {
        return mCityFactsList.size
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        internal var tvDescription: TextView = v.findViewById(R.id.tvDescription)
        internal var llCardItem: LinearLayout = v.findViewById(R.id.llCardItem)
        internal var ivThumbnail: ImageView = v.findViewById(R.id.ivThumbnail)
    }

    @UiThread
    fun addItems(items: ArrayList<FactResponse.FactItem>) {
        mCityFactsList.clear()
        for (item in items) {
            if (!item.title.isNullOrEmpty()) {
                mCityFactsList.add(item)
            }
        }
        notifyDataSetChanged()
    }
}
