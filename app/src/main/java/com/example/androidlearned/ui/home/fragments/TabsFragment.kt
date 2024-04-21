package com.example.androidlearned.ui.home.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEach
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentTabsBinding
import com.example.androidlearned.utils.Display
import com.google.android.material.tabs.TabLayout


class TabsFragment : Fragment() {
    lateinit var binding: FragmentTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val tabItems = listOf("tabs1","tabs2","tabs3","tabs4","tabs5")
        val tabItems2 = listOf("京东","神舟集团","拼多多","阿里巴巴","腾讯","天猫", "苹果手机", "华为手机")
        tabItems.forEach {
            val tab = binding.tabs1.newTab()
            tab.text = it
            binding.tabs1.addTab(tab)
        }
        binding.tabs1.tabMode = TabLayout.MODE_AUTO

        tabItems.forEach {
            val tab = binding.tabs2.newTab()
            tab.text = it
            binding.tabs2.addTab(tab)
        }
        binding.tabs2.isTabIndicatorFullWidth = true
        binding.tabs2.tabIndicatorAnimationMode = TabLayout.INDICATOR_ANIMATION_MODE_LINEAR
        binding.tabs2.tabMode = TabLayout.MODE_AUTO

        tabItems.forEach {
            val tab = binding.tabs3.newTab()
            tab.text = it
            tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.bottom_nav_person_24, null)
            binding.tabs3.addTab(tab)
        }
        binding.tabs3.tabMode = TabLayout.MODE_AUTO
        binding.tabs3.isInlineLabel = true
        binding.tabs3.tabIndicatorAnimationMode = TabLayout.INDICATOR_ANIMATION_MODE_FADE


        tabItems.forEach {
            val tab = binding.tabs4.newTab()
            tab.text = it
            tab.icon = ResourcesCompat.getDrawable(resources,R.drawable.baseline_airplanemode_active_24, null)
            binding.tabs4.addTab(tab)

        }
        binding.tabs4.tabMode = TabLayout.MODE_AUTO
        binding.tabs4.tabIconTint = ResourcesCompat.getColorStateList(resources,R.color.tabs_tint,null)
        binding.tabs4.tabTextColors = ResourcesCompat.getColorStateList(resources,R.color.tabs_tint,null)
        binding.tabs4.setSelectedTabIndicatorColor(ResourcesCompat.getColor(resources,R.color.bilibili_pink,null))

        tabItems.forEach {
            val tab = binding.tabs5.newTab()
            tab.text = it
            binding.tabs5.addTab(tab)
        }
        binding.tabs5.tabMode = TabLayout.MODE_AUTO


        tabItems.forEach {
            val tab = binding.tabs6.newTab()
            tab.text = it
            binding.tabs6.addTab(tab)
        }

        tabItems.forEach {
            val tab = binding.tabs7.newTab()
            tab.text = it
            binding.tabs7.addTab(tab)
        }
        binding.tabs7.setSelectedTabIndicator(R.drawable.background_shape_11)
        // 设置indicator颜色
        binding.tabs7.setSelectedTabIndicatorColor(ResourcesCompat.getColor(resources,R.color.bilibili_pink,null))

        tabItems.forEach {
            val tab = binding.tabs8.newTab()
            tab.text = it
            binding.tabs8.addTab(tab)
        }
        binding.tabs8.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_CENTER)


        tabItems.forEach {
            val tab = binding.tabs9.newTab()
            tab.text = it
            binding.tabs9.addTab(tab)
        }
        binding.tabs9.forEach {
            val linearLayout = it as LinearLayout
            linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            linearLayout.dividerDrawable =  ResourcesCompat.getDrawable(resources,R.drawable.background_shape_12, null)
            linearLayout.dividerPadding = Display.dip2px(requireContext(),15f)
        }

        tabItems.forEach {
            val tab = binding.tabs10.newTab()
            tab.text = it
            binding.tabs10.addTab(tab)
        }

        tabItems.forEach {
            val tab = binding.tabs11.newTab()
            tab.text = it
            binding.tabs11.addTab(tab)
        }
        binding.tabs11.getTabAt(1)?.apply {
            orCreateBadge.apply {
                backgroundColor = Color.RED
                number = 44
                maxNumber = 99
            }
        }

        binding.tabs11.getTabAt(2)?.apply {
            orCreateBadge.apply {
                backgroundColor = Color.RED
            }
        }

        binding.tabs11.getTabAt(4)?.apply {
            orCreateBadge.apply {
                backgroundColor = Color.RED
                text = "点我"
                maxCharacterCount = 3
            }
        }

        tabItems2.forEach {
            val tab = binding.tabs12.newTab()
            tab.text = it
            binding.tabs12.addTab(tab)
        }
        binding.tabs12.tabMode = TabLayout.MODE_AUTO

        tabItems2.forEach {
            val tab = binding.tabs13.newTab()
            tab.text = it
            binding.tabs13.addTab(tab)
        }
        binding.tabs13.tabMode = TabLayout.MODE_AUTO

    }
}