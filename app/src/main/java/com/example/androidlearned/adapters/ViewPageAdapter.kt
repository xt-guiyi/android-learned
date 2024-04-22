package com.example.androidlearned.adapters
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(val fragment: Fragment, val fgList: List<Fragment>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fgList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = fgList[position]
        fragment.arguments = Bundle().apply {
            putString("param",(position + 1).toString())
        }
        return fragment
    }
}