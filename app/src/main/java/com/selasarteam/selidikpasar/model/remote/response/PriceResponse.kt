package com.selasarteam.selidikpasar.model.remote.response

import com.google.gson.annotations.SerializedName

data class PriceResponse(

    @field:SerializedName("regional_prices")
    val regionalPrices: List<RegionalPricesItem>
)

data class RegionalPricesItem(

    @field:SerializedName("commodity")
    val commodity: List<CommodityItem>,

    @field:SerializedName("region")
    val region: String
)

data class CommodityItem(

    @field:SerializedName("average")
    val average: Boolean,

    @field:SerializedName("price_list")
    val priceList: List<PriceListItem>,

    @field:SerializedName("name")
    val name: String
)

data class PriceListItem(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("price")
    val price: Int
)
