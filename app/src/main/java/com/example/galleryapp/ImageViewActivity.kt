package com.example.galtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryapp.Model
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        var modelItems: Model = intent.getSerializableExtra("data") as Model
        viewImage.setImageResource(modelItems.image)
    }
}