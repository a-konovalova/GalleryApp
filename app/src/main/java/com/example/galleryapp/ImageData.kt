package com.example.galleryapp

import com.example.galtest.R

object ImageData {

    private val names = arrayListOf(
        "img1",
        "img2",
        "img3",
        "img4",
        "img5",
        "img6",
        "img7",
        "img8",
        "img9",
        "img10",
        "img11",
        "img12",
        "img13",
        "img14",
        "img16",
    )

    private val images = arrayListOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6,
        R.drawable.img7,
        R.drawable.img8,
        R.drawable.img9,
        R.drawable.img10,
        R.drawable.img11,
        R.drawable.img12,
        R.drawable.img13,
        R.drawable.img14,
        R.drawable.img16,
    )

    val itemsList = arrayListOf<Model>()

    init{
        for (i in 0 until images.size){
            itemsList.add(Model(names[i], images[i]))
        }
    }
}