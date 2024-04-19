package com.example.androidlearned.ui.home.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentTextViewWidgetBinding
import java.time.Duration


class TextviewWidgetFragment : Fragment() {
    lateinit var binding: FragmentTextViewWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTextViewWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    private fun init(){
        binding.tvMarquee.isSelected = true // 设置聚焦,进行走马灯
        // 设置文本样式,只能设置一次文本
        val spanStringClient1 = SpannableString("1.这是使用SpannableString设置的文字颜色")
        spanStringClient1.setSpan(ForegroundColorSpan(resources.getColor(R.color.primary_color,activity?.theme)),6,21,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span1.text = spanStringClient1

        // 可多次设置文本
        val spanStringClient2 = SpannableStringBuilder("2.这是使用SpannableStringBuilder设置的文字颜色,有追加文本，还有加粗效果")
        // 设置span
        spanStringClient2.setSpan(ForegroundColorSpan(Color.RED),6,28,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        // 插入文本
        spanStringClient2.insert(4,"**123**")
        spanStringClient2.append("哈哈哈哈哈😂")
        // 加粗
        spanStringClient2.setSpan(StyleSpan(Typeface.BOLD),10,spanStringClient2.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.span2.text = spanStringClient2

        val spanStringClient3 = SpannableString("3.使用下划线span的文本")
        spanStringClient3.setSpan(UnderlineSpan(),4,11,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.span3.text = spanStringClient3

        val spanStringClient4 = SpannableStringBuilder("4.放大文字的span")
        spanStringClient4.setSpan(RelativeSizeSpan(1.5f),2,4,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span4.text = spanStringClient4

        val spanStringClient5 = SpannableStringBuilder("5.这是使用SpannableStringBuilder设置的背景颜色")
        spanStringClient5.setSpan(BackgroundColorSpan(Color.YELLOW),6,28,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span5.text = spanStringClient5

        val spanStringClient6 = SpannableStringBuilder("6.这是使用SpannableStringBuilder设置的图片")
        spanStringClient6.setSpan(activity?.let { ImageSpan(it, R.drawable.bottom_nav_home_24, DynamicDrawableSpan.ALIGN_BASELINE) },6,7,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span6.text = spanStringClient6

        val spanStringClient7 = SpannableStringBuilder("7.通过span可以设置点击事件，来点我")
        spanStringClient7.setSpan(object:ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.primary_color,null)
                ds.bgColor = resources.getColor(R.color.silver,null)
                ds.linkColor = Color.RED
                ds.isUnderlineText = true
            }

            override fun onClick(widget: View) {
                Log.i("test","lkkkkkk")
                Toast.makeText(activity,"我被点击啦",Toast.LENGTH_SHORT).show()
            }
        },17,20,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span7.text = spanStringClient7
        binding.span7.movementMethod = LinkMovementMethod.getInstance()

        val spanStringClient8 = SpannableStringBuilder("8.还有更多span类型，请看谷歌官方文档😄")
        spanStringClient8.setSpan(URLSpan("https://developer.android.com/reference/android/text/style/package-summary"),15,spanStringClient8.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.span8.text = spanStringClient8
        binding.span8.movementMethod = LinkMovementMethod.getInstance()


    }

}