package com.example.galleryapp

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    var itemList : ArrayList<Model> = ArrayList()

    init {
        this.itemList = ItemRepository().getItemsList()
    }
}