package com.hazem.newslist.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.hazem.entities.headlines.local.Source

class SourcesArrayAdapter(context: Context, val list: List<Source>) : ArrayAdapter<Source>(context,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,list) {

}