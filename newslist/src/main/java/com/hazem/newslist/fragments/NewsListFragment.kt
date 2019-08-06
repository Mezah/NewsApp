package com.hazem.newslist.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hazem.newslist.R
import com.hazem.newslist.adapters.NewsListAdapter
import com.hazem.newslist.di.NewsListModule
import com.hazem.newslist.viewmodels.NewsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.StringQualifier

class NewsListFragment : Fragment() {
    private lateinit var newsList: RecyclerView
    private lateinit var emptyView: View
    private val newsListAdpter = NewsListAdapter()
    private val newsListViewModel:NewsListViewModel by viewModel(StringQualifier(NewsListModule.NEWS_LIST_VM))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_news_list, container, false)
        newsList = mView.findViewById(R.id.news_list)
        emptyView = mView.findViewById(R.id.empty_view)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        newsList.adapter = newsListAdpter
        newsList.layoutManager = layoutManager
        newsListViewModel.newsList.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                emptyView.visibility = View.VISIBLE
                newsList.visibility = View.GONE
            } else {
                newsListAdpter.loadNewsList(it)
                emptyView.visibility = View.GONE
                newsList.visibility = View.VISIBLE
            }
        })
        newsListViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        newsListViewModel.loadNewsList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.news_list_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.filter -> {
                findNavController().navigate(NewsListFragmentDirections.actionNewsListFragmentToFilterFragment())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}