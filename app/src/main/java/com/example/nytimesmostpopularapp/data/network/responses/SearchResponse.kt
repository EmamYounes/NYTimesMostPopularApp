package com.example.sampleapp.data.network.responses

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("SearchResponse")
	val searchResponse: List<SearchResponseItem?>? = null
)

data class SearchResponseItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("url")
	val url: String? = null
)
