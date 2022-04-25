package com.example.galleryapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ItemDataRepoImpl() : ItemDataRepository {

    override fun getItemsList(): ArrayList<Model> {
        return ImageData.itemsList
    }

    override fun getItemUri(
        item: Model,
        applicationContext: Context,
        baseContext: Context,
        resources: Resources,
        externalCacheDir: File?
    ): Uri {
        val bitmap = BitmapFactory.decodeResource(resources, item.image)
        var path = externalCacheDir.toString() + "/" + item.name + ".png"
        val out: OutputStream?
        val file = File(path)
        try {
            out = FileOutputStream(file)
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                100,
                out
            )
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        path = file.path

        return FileProvider.getUriForFile(
            baseContext,
            applicationContext
                .packageName + ".provider", file
        )
    }
}