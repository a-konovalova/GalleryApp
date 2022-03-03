package com.example.galtest

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    var itemList : ArrayList<Model> = ArrayList()

    init {
        this.itemList = ImageData.getImages()
    }

    fun getListImages() = itemList
}