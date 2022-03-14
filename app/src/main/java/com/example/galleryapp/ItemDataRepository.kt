package com.example.galleryapp

interface ItemDataRepository {
    fun getItemsList(): ArrayList<Model>
}