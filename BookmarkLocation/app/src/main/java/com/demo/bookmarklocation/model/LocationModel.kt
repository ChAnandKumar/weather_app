package com.demo.bookmarklocation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity
data class LocationModel(

    @SerializedName("lat")
    @Expose
    var latValue: String? = null,

    @SerializedName("lng")
    @Expose
    var lugValue: String? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @PrimaryKey
    @SerializedName("pincode")
    @Expose
    var pincode: Int
)

