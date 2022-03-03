package com.example.galtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.gallery.ImageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel: ViewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        val imageAdapter: ImageAdapter = ImageAdapter(ViewModel().itemList,this )

        gridView.adapter = imageAdapter
        gridView.setOnItemClickListener{
                imageAdapter, view, i, l ->
            var data: Model = viewModel.getListImages()[i]


            var intent = Intent(this, ImageViewActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
            }
        }
    }
