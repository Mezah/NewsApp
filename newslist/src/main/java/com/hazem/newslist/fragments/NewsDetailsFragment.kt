package com.hazem.newslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.newslist.R

class NewsDetailsFragment : Fragment() {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var author: TextView
    private lateinit var sourceLink: TextView
    private var headLine: HeadLine? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headLine = NewsDetailsFragmentArgs.fromBundle(arguments!!).headLine

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_news_details, container, false)
        title = mView.findViewById(R.id.title)
        description = mView.findViewById(R.id.description)
        author = mView.findViewById(R.id.author)
        sourceLink = mView.findViewById(R.id.source)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = headLine?.title
        description.text = headLine?.description
        author.text = "By: ${headLine?.author}"
        sourceLink.text = "Source Url: ${headLine?.url}"

    }

    override fun onResume() {
        super.onResume()
        findNavController().currentDestination?.label = headLine?.source

    }

}