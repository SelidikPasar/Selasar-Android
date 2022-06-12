package com.selasarteam.selidikpasar.model.remote.response

import com.google.gson.annotations.SerializedName

data class MarketResponse(

	@field:SerializedName("market")
	val market: List<MarketItem>
)

data class MarketItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("long")
	val long: Double
)
