package com.example.galleryapp

import android.content.Intent
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R
import java.io.IOException
import java.io.InputStream

class ImageViewActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private var position: Int = 0

    @RequiresApi(Build.VERSION_CODES.N)
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
            shareIntent.putExtra(
                Intent.EXTRA_STREAM,
                ItemDataRepoImpl().getItemUri(
                    itemsList[viewPager.currentItem],
                    applicationContext,
                    baseContext,
                    resources,
                    externalCacheDir
                )
            )
            startActivity(shareIntent)
        }

        val buttonShowOnMap = findViewById<ImageButton>(R.id.action_show_on_map)
        buttonShowOnMap.setOnClickListener{

            lateinit var input: InputStream
            try {
                input = contentResolver.openInputStream(
                    ItemDataRepoImpl().getItemUri(
                        itemsList[viewPager.currentItem],
                        applicationContext,
                        baseContext,
                        resources,
                        externalCacheDir
                    )
                )!!
                val exifInterface = ExifInterface(input)
                val latLong = FloatArray(2)
                if (exifInterface.getLatLong(latLong)) {
                    val lat = latLong[0]
                    val long = latLong[1]
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat%$long"))
                    startActivity(intent)
                }
                Log.d("TAG",latLong[0].toString())
            } catch (e: IOException) {
                Log.d("TAG","Couldn't read exif info: " + e.localizedMessage)
            } finally {
                try {
                    input.close()
                } catch (ignored: IOException) {
                }
            }
        }
    }
}