package com.demo.bookmarklocation.interfaces

import com.demo.bookmarklocation.model.LocationModel

interface ItemClickListener {

    fun onItemClick(imdbId: LocationModel)
}