package com.example.androidlearned.adapters

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearned.utils.Display
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils


/**
 * 自定义布局，下面是常见的图片样式，更多实现可以看demo，可以自己随意发挥
 */
class ImageAdapter(mData: List<Drawable?>?) :
    BannerAdapter<Drawable?, ImageAdapter.BannerViewHolder?>(mData) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        // 设置圆角
        BannerUtils.setBannerRound(imageView, 20f)

        val imageContainer = FrameLayout(parent.context)
        // 设置容器padding
        imageContainer.setPadding(Display.dip2px(parent.context,20f),0,Display.dip2px(parent.context,20f), 0)
        imageContainer.addView(imageView)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageContainer.setLayoutParams(
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        return BannerViewHolder(imageContainer)
    }

    override fun onBindView(holder: BannerViewHolder?, data: Drawable?, position: Int, size: Int) {
        val container = holder?.imageContainer?.getChildAt(0) as ImageView
        container.setImageDrawable(data)
    }

    inner class BannerViewHolder(var imageContainer: FrameLayout) : RecyclerView.ViewHolder(
        imageContainer
    )
}

