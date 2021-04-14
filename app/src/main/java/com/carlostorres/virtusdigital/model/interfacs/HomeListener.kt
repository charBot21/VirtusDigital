package com.carlostorres.virtusdigital.model.interfacs

import com.carlostorres.virtusdigital.data.local.entity.Items
import com.carlostorres.virtusdigital.data.network.models.reponses.Hit

interface HomeListener {

    fun onError()
    fun showProgressBar()
    fun hideProgressBar()
    fun itemClicked(items: Items, position: Int )

}