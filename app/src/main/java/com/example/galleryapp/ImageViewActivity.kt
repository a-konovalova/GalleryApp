package com.example.galleryapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R

class ImageViewActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var factor = 1.0f
    private lateinit var viewImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_image_view)

        viewImage = findViewById(R.id.viewImage)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener(viewImage, factor))

        val actionBarToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(actionBarToolbar)

        val buttonBackToMainActivity = findViewById<ImageButton>(R.id.action_back)

        buttonBackToMainActivity.setOnClickListener{
            finish()
        }

        val item = intent.getSerializableExtra("data") as Model

        viewImage.setImageResource(item.image)

        val textView: TextView = findViewById(R.id.img_name)
        textView.text = item.name
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector.onTouchEvent(event)
    }

    class ScaleListener(private val viewImage: ImageView, private var factor: Float) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            factor *= scaleGestureDetector.scaleFactor

            viewImage.scaleX = factor
            viewImage.scaleY = factor

            return true
        }
    }
}