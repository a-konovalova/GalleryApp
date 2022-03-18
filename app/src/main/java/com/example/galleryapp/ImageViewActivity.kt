package com.example.galleryapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class ImageViewActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_image_view)

        val itemsList = ViewModel().itemsList
        viewPager = findViewById<View>(R.id.slider) as ViewPager
        position = intent.getIntExtra("current", 0)

        val sliderAdapter = SliderAdapter(this, itemsList)
        viewPager.adapter = sliderAdapter
        viewPager.setCurrentItem(position, true)

        val buttonBackToMainActivity = findViewById<ImageButton>(R.id.action_back)
        buttonBackToMainActivity.setOnClickListener{
            finish()
        }

        val buttonShare = findViewById<ImageButton>(R.id.action_share)
        buttonShare.setOnClickListener{

            val bitmap = BitmapFactory.decodeResource(resources, itemsList[position].image)
            var path = externalCacheDir.toString() + "/"+ itemsList[position].name + ".jpg"
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
            val bmpUri = FileProvider.getUriForFile(
                baseContext,
                applicationContext
                    .packageName + ".provider", file
            )
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/jpeg"

            shareIntent.putExtra(Intent.EXTRA_TEXT, "Sample Text")
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            startActivity(shareIntent)
        }
    }
}