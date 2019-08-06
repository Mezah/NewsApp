package com.hazem.newslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hazem.newslist.R

class FilterFragment :DialogFragment() {

    private lateinit var closeButton:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_filter,container,false)
        closeButton = mView.findViewById(R.id.close_btn)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeButton.setOnClickListener {
            dismiss()
        }
    }
}