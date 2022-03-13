package com.example.galleryapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R

class ImageViewActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var factor = 1.0f
    private lateinit var viewImage: ImageView
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_image_view)

        val fullScreenView: View = layoutInflater.inflate(R.layout.full_screen_item, null)
        viewImage = fullScreenView.findViewById(R.id.viewImage)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener(viewImage, factor))

        val actionBarToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(actionBarToolbar)

        val buttonBackToMainActivity = findViewById<ImageButton>(R.id.action_back)

        buttonBackToMainActivity.setOnClickListener{
            finish()
        }

        val itemsList = ViewModel().itemsList

        viewPager = findViewById<View>(R.id.slider) as ViewPager

        if(savedInstanceState == null){
            position = intent.getIntExtra("current", 0)
        }

        val sliderAdapter = SliderAdapter(this, itemsList)

        viewPager.adapter = sliderAdapter
        viewPager.setCurrentItem(position, true)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }

    class ScaleListener(private val viewImage: ImageView, var factor: Float) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            factor *= scaleGestureDetector.scaleFactor

            viewImage.scaleX = factor
            viewImage.scaleY = factor

            return true
        }
    }
}