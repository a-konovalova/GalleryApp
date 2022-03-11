package com.example.galleryapp

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    var itemsList : ArrayList<Model> = arrayListOf()

    init {
        for (i in 0 until ImageDataRepository.images.size){
            itemsList.add(Model(ImageDataRepository.names[i],
            ImageDataRepository.images[i]))
        }
    }
}