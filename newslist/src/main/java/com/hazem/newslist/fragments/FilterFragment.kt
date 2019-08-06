package com.hazem.newslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hazem.newslist.R

class FilterFragment :DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mView = inflater.inflate(R.layout.fragment_filter,container,false)
        return mView
    }
}