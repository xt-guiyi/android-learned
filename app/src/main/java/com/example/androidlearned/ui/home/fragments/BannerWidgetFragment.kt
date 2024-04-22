package com.example.androidlearned.ui.home.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.androidlearned.R
import com.example.androidlearned.adapters.BannerAdapter
import com.example.androidlearned.databinding.FragmentBannerWidgetBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BannerWidgetFragment : Fragment() {
    lateinit var binding: FragmentBannerWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerWidgetBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val images = listOf(
            ResourcesCompat.getDrawable(resources,R.drawable.img_1, null),
            ResourcesCompat.getDrawable(resources,R.drawable.img_2, null),
            ResourcesCompat.getDrawable(resources,R.drawable.img_3, null),
            ResourcesCompat.getDrawable(resources,R.drawable.img_4, null),
            ResourcesCompat.getDrawable(resources,R.drawable.img_5, null),
        )
        binding.banner1
            .setAdapter(object : BannerImageAdapter<Drawable>(images){
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: Drawable?,
                    position: Int,
                    size: Int
                ) {
                    holder?.imageView?.setImageDrawable(data)
                }

            })
            .addBannerLifecycleObserver(requireActivity())
            .setIndicator(CircleIndicator(requireContext()))
            .setIndicatorSelectedColorRes(R.color.primary_color)
            .setIndicatorNormalColorRes(R.color.silver)
            .isAutoLoop(false)

        binding.banner2
            .addBannerLifecycleObserver(requireActivity())
            .setAdapter(object : BannerImageAdapter<Drawable>(images){
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: Drawable?,
                    position: Int,
                    size: Int
                ) {
                    holder?.imageView?.setImageDrawable(data)
                }

            })
            .setStartPosition(1)
            .setIndicator(CircleIndicator(requireContext()))
            .setIndicatorSelectedColor(ResourcesCompat.getColor(resources,R.color.primary_color, null))
            .setIndicatorNormalColor(ResourcesCompat.getColor(resources,R.color.silver, null))
            .setIndicatorWidth(30,30)
            .setBannerGalleryEffect(16,16,0.9f)
            .isAutoLoop(false)


        binding.banner3
            .addBannerLifecycleObserver(requireActivity())
            .setAdapter(object : BannerImageAdapter<Drawable>(images){
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: Drawable?,
                    position: Int,
                    size: Int
                ) {
                    holder?.imageView?.setImageDrawable(data)
                }

            })
            .setStartPosition(2)
            .setIndicator(CircleIndicator(requireContext()))
            .setIndicatorSelectedColor(ResourcesCompat.getColor(resources,R.color.primary_color, null))
            .setIndicatorNormalColor(ResourcesCompat.getColor(resources,R.color.silver, null))
            .setIndicatorWidth(30,30)
            .setBannerGalleryMZ(24)
            .isAutoLoop(false)



        binding.banner4
            .setAdapter(BannerAdapter(images))
            .addBannerLifecycleObserver(requireActivity())
            .setIndicator(CircleIndicator(requireContext()))
            .setIndicatorSelectedColorRes(R.color.primary_color)
            .setIndicatorNormalColorRes(R.color.silver)
    }

}