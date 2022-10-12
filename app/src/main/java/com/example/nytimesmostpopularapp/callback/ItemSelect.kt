package com.example.nytimesmostpopularapp.callback

import com.example.nytimesmostpopularapp.data.network.responses.ResultsItem

interface ItemSelect {
    fun onItemSelected(itemModel: ResultsItem)
}