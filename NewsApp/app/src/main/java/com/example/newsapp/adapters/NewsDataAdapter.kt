package com.example.newsapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.features.news.viewmodels.views.NewsDetailsFragment


class NewsDataAdapter(private val context: FragmentManager, var list: ArrayList<NewsItemData>) : RecyclerView.Adapter<NewsDataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)

        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = list.get(position)
        holder.title.text = data.title
        holder.child.setOnClickListener {

            val bundle = Bundle()
            bundle.putString(NewsDetailsFragment.EXTRA_STORY_URL, data.url)
            val fragment = NewsDetailsFragment()
            fragment.arguments = bundle
            val transaction = context.beginTransaction()
            transaction.replace(R.id.root_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class DataViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val child: ConstraintLayout = itemView.findViewById(R.id.child)
    }
}