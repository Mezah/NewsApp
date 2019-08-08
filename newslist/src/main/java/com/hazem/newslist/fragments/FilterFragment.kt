package com.hazem.newslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.hazem.entities.headlines.local.Source
import com.hazem.newslist.R
import com.hazem.newslist.adapters.SourcesArrayAdapter
import com.hazem.newslist.di.NewsListModule
import com.hazem.newslist.viewmodels.NewsFilterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.StringQualifier

class FilterFragment : DialogFragment() {

    private lateinit var closeButton: View
    private lateinit var okButton: View
    private lateinit var cancelButton: View
    private lateinit var countrySpinner: Spinner
    private lateinit var sourceSpinner: Spinner
    private lateinit var countryRadioButton: RadioButton
    private lateinit var sourceRadioButton: RadioButton

    private val filterViewModel: NewsFilterViewModel by viewModel(StringQualifier(NewsListModule.FILTER_NEWS_VM))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_filter, container, false)
        closeButton = mView.findViewById(R.id.close_btn)
        okButton = mView.findViewById(R.id.ok_filter)
        cancelButton = mView.findViewById(R.id.cancel_filter)
        countrySpinner = mView.findViewById(R.id.country_spinner)
        sourceSpinner = mView.findViewById(R.id.source_spinner)
        countryRadioButton = mView.findViewById(R.id.country_radio_button)
        sourceRadioButton = mView.findViewById(R.id.source_radio_button)
        prepareSpinner()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeButton.setOnClickListener {
            dismiss()
        }
        cancelButton.setOnClickListener { dismiss() }
        okButton.setOnClickListener {
            //perform filter

        }
        filterViewModel.countriesCodesList.observe(this, Observer {
            // populate countries codes
            it.apply {
                add(0, "Select Country")
            }
            val countryAdapter = createSpinnerAdapter(it?.toTypedArray() ?: emptyArray())
            countrySpinner.adapter = countryAdapter
        })

        filterViewModel.newsSources.observe(this, Observer {
            //populate news sources
            it.add(0, Source("","Select News Source"))
            val sourcAdapter = SourcesArrayAdapter(context!!,it)
            sourceSpinner.adapter = sourcAdapter
            sourcAdapter.notifyDataSetChanged()
        })
        filterViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()
        filterViewModel.loadCountriesCodes()
        filterViewModel.loadNewsSources()
    }

    fun prepareSpinner() {
        countrySpinner.setOnItemLongClickListener { adapterView, view, position, id ->
            if (position == 0)
                return@setOnItemLongClickListener false
            val source: Source? = adapterView?.adapter?.getItem(position) as Source
            Toast.makeText(view.context, source?.id, Toast.LENGTH_SHORT).show()
            true
        }

        sourceSpinner.setOnItemLongClickListener { adapterView, view, position, id ->
            if (position == 0)
                return@setOnItemLongClickListener false
            val newsSource: String? = adapterView?.adapter?.getItem(position) as String
            Toast.makeText(view.context, newsSource, Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun createSpinnerAdapter(items: Array<String>): ArrayAdapter<String> {

        return ArrayAdapter<String>(activity!!, android.R.layout.simple_spinner_dropdown_item, items)

    }
}