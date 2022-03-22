package com.example.galleryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.galtest.R
import com.github.chrisbanes.photoview.PhotoView

class SliderAdapter(
    var context: Context,
    private var itemsList: ArrayList<Model>,
) : PagerAdapter() {

    override fun getCount(): Int {
        return itemsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val item: View = inflater.inflate(R.layout.full_screen_item, null)

        val photoView: PhotoView = item.findViewById(R.id.viewImage)
        val textView: TextView = item.findViewById(R.id.img_name)

        photoView.setImageResource(itemsList[position].image)
        textView.text = itemsList[position].name

        val viewPager: ViewPager = container as ViewPager
        viewPager.addView(item,0)

        return item
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager: ViewPager = container as ViewPager
        val item: View = `object` as View
        viewPager.removeView(item)
    }
}