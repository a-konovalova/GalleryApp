package com.example.galleryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.galtest.R

class ImageAdapter (
    var itemsModel: ArrayList<Model>,
    context: Context
    ) : BaseAdapter() {

    var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return itemsModel.size
    }

    override fun getItem(itemId: Int): Model {
        return itemsModel[itemId]
    }

    override fun getItemId(itemId: Int): Long {
        return itemId.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var localView = view;
        if(localView == null){
            localView = layoutInflater.inflate(R.layout.gallery_item, viewGroup, false)
        }

        val imageView = localView!!.findViewById<ImageView>(R.id.imageView)
        imageView?.setImageResource(itemsModel[position].image)

        return localView
    }
}