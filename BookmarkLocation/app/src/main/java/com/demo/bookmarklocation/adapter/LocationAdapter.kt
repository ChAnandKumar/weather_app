package com.demo.bookmarklocation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.demo.bookmarklocation.R
import com.demo.bookmarklocation.interfaces.ItemClickListener
import com.demo.bookmarklocation.model.LocationModel

class LocationAdapter(
    private val mContext: FragmentActivity?,
    private var locationResult: List<LocationModel>,
    private var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.loction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = locationResult
        movie.apply {
            holder.mTitleView.text = "${locationResult[position].city}(${locationResult[position].pincode})"

            holder.mView.setOnClickListener {
                //val imdbID = it.tag as String
                onItemClickListener.onItemClick(locationResult[position])
            }
        }
    }

    override fun getItemCount(): Int {
        val total = if (locationResult!! == null) {
            0
        } else locationResult.size
        Log.d("anand","Total $total")
        return total
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mTitleView: TextView = mView.findViewById<TextView>(R.id.city_name_textView)

    }

}