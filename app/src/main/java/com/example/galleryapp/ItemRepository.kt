package com.example.galleryapp

import com.example.galleryapp.ImageDataRepository.itemsList

class ItemRepository: ItemDataRepository {

    override fun getItemsList(): ArrayList<Model> {
        return itemsList
    }
}