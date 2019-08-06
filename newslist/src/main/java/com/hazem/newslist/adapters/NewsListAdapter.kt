package com.hazem.newslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.newslist.R
import com.hazem.newslist.fragments.NewsListFragmentDirections

class NewsListAdapter : RecyclerView.Adapter<NewsVH>() {
    private var headLines: List<HeadLine> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_headline, parent, false)
        return NewsVH(view)
    }

    override fun getItemCount(): Int {
        return headLines.size
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.bindData(headLines[position])
    }

    fun loadNewsList(list: List<HeadLine>) {
        headLines = list
        notifyDataSetChanged()
    }
}


class NewsVH(private val view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.title)
    private val logo: ImageView = view.findViewById(R.id.logo)
    private val date: TextView = view.findViewById(R.id.date)
    private val container: View = view.findViewById(R.id.news_container)
    fun bindData(headLine: HeadLine) {
        title.text = headLine.title
        date.text = headLine.date

        Glide.with(view.context)
            .load(headLine.logo)
            .into(logo)

        container.setOnClickListener {
            it.findNavController().navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(headLine))
        }
    }
}