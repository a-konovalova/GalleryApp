package com.example.galleryapp

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import java.io.File

interface ItemDataRepository {
    fun getItemsList(): ArrayList<Model>

    fun getItemUri(
        item: Model,
        applicationContext: Context,
        baseContext: Context,
        resources: Resources,
        externalCacheDir: File?
    ): Uri
}