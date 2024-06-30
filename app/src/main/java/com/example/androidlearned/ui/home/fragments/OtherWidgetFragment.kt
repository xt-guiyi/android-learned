package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.androidlearned.databinding.FragmentOtherWidgetBinding
import com.hjq.toast.Toaster
import java.util.TimeZone


class OtherWidgetFragment : Fragment() {

    lateinit var binding:FragmentOtherWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherWidgetBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        Toaster.debugShow("当前时区:${TimeZone.getDefault().id} 描述${TimeZone.getDefault().displayName}")
        // webView
        binding.webViewContainer.loadUrl("https://github.com/xt-guiyi")
        binding.webViewContainer.settings.javaScriptEnabled = true
        // 处理地址跳转
        binding.webViewContainer.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Toaster.debugShow((Uri.parse(request?.url.toString()).host == "github.com").toString())
                if (Uri.parse(request?.url.toString()).host == "github.com") {
                    return false
                }
                Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString())).apply {
                    startActivity(this)
                }
                return true
            }
        }
        // videoView
        binding.videoView.setVideoURI(Uri.parse("https://videos.cubox.pro/iw3rni/file/2024062923083169063/mda-qdq8qnmw9x5wm9jx.mp4"))
        val mediaController =MediaController(requireActivity())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.start()

    }


}