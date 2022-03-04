package com.example.galleryapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.galtest.ImageViewActivity
import com.example.galtest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView.adapter = ImageAdapter(ViewModel().itemList,this )
        gridView.setOnItemClickListener{
                gridViewAdapter, _, position, _ ->
            val imageAdapter: ImageAdapter = gridViewAdapter.adapter as ImageAdapter
            val data: Model = imageAdapter.itemModel[position]

            val intent = Intent(this, ImageViewActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
            }
        }
    }
