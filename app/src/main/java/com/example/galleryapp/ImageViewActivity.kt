package com.example.galleryapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R

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
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/jpeg"
            shareIntent.putExtra(Intent.EXTRA_STREAM, ItemDataRepoImpl().getItemUri(itemsList[viewPager.currentItem], applicationContext, baseContext, resources, externalCacheDir))
            startActivity(shareIntent)
        }
    }
}