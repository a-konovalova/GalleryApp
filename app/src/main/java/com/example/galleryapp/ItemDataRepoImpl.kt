package com.example.galleryapp

class ItemDataRepoImpl: ItemDataRepository {
    override fun getItemsList(): ArrayList<Model> {
        return ImageData.itemsList
    }

}