package com.example.galleryapp

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    var itemsList = ItemDataRepo().getItemsList()
}