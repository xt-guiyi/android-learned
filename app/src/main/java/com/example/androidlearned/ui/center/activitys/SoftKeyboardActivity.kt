package com.example.androidlearned.ui.center.activitys

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearned.databinding.ActivitySoftKeyboardBinding

/**
 * 软键盘与输入框实践
 * @see <a href="https://developer.android.com/develop/ui/views/layout/sw-keyboard?hl=zh-cn">官方文档</a>
 * */
class SoftKeyboardActivity : AppCompatActivity() {
    lateinit var binding: ActivitySoftKeyboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 开启面对面布局，可以有动画，关闭就无动画，开启后根布局要设置fitsSystemWindows=true，不然输入框不会弹起
        binding = ActivitySoftKeyboardBinding.inflate(layoutInflater)

        // 监听软键盘高度变化
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            binding.softKeyBoardToolbar.setPadding(0, systemBars.top, 0, systemBars.bottom)
//            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
//            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
//            Toaster.show(imeHeight)
            insets
        }

        var startBottom = 0f
        var endBottom = 0f
        ViewCompat.setWindowInsetsAnimationCallback(
            binding.root,
            object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {

                override fun onPrepare(
                    animation: WindowInsetsAnimationCompat
                ) {
                    startBottom = binding.editTextContainer.bottom.toFloat()
                }

                override fun onStart(
                    animation: WindowInsetsAnimationCompat,
                    bounds: WindowInsetsAnimationCompat.BoundsCompat
                ): WindowInsetsAnimationCompat.BoundsCompat {
                    // Record the position of the view after the IME transition.
                    endBottom = binding.editTextContainer.bottom.toFloat()
                    return bounds
                }


                // Override methods.
                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: MutableList<WindowInsetsAnimationCompat>
                ): WindowInsetsCompat {
                    // Find an IME animation.
                    val imeAnimation = runningAnimations.find {
                        it.typeMask and WindowInsetsCompat.Type.ime() != 0
                    } ?: return insets

                    // Offset the view based on the interpolated fraction of the IME animation.
                    Log.d("SoftKeyboardActivity", "onProgress: ${(startBottom - endBottom) * (1 - imeAnimation.interpolatedFraction)}")
                    binding.editTextContainer.translationY = (startBottom - endBottom) * (1 - imeAnimation.interpolatedFraction)
                    return insets
                }
            }
        )

        setContentView(binding.root)
    }
}