package com.example.galleryapp

class ItemDataRepo: ItemDataRepository {
    override fun getItemsList(): ArrayList<Model> {
        return ImageData.itemsList
    }

}