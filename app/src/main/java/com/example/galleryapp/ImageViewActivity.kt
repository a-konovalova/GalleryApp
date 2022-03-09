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
import com.example.galtest.R
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector1: ScaleGestureDetector
    private var factor = 1.0f
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_image_view)

        imageView = findViewById(R.id.viewImage)

        scaleGestureDetector1 = ScaleGestureDetector(this, ScaleListener(imageView, factor))

        val modelItems: Model = intent.getSerializableExtra("data") as Model
        viewImage.setImageResource(modelItems.image)

        val actionBarToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(actionBarToolbar)

        val textView = findViewById<TextView>(R.id.img_name)
        textView.text = modelItems.name

        val buttonBackToMainActivity = findViewById<ImageButton>(R.id.action_back)

        buttonBackToMainActivity.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return scaleGestureDetector1.onTouchEvent(event)
    }

    class ScaleListener(private val imageView: ImageView, var factor: Float) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector2: ScaleGestureDetector): Boolean {

            factor *= scaleGestureDetector2.scaleFactor

            imageView.scaleX = factor
            imageView.scaleY = factor

            return true
        }
    }
}