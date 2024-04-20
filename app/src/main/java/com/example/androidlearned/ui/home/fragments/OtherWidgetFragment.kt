package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import java.time.ZoneId
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
        binding.videoView.setVideoURI(Uri.parse("https://vdept3.bdstatic.com/mda-pf67yz864q7avwdh/720p/h264/1686116258113519827/mda-pf67yz864q7avwdh.mp4?v_from_s=hkapp-haokan-hnb&auth_key=1713631564-0-0-38ceaa34590bacf63a12dd67990058ed&bcevod_channel=searchbox_feed&pd=1&cr=2&cd=0&pt=3&logid=2764954496&vid=6224617423035670594&klogid=2764954496&abtest=101830_2-102148_2-17451_2-3000225_1"))
        val mediaController =MediaController(requireActivity())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.start()

    }


}