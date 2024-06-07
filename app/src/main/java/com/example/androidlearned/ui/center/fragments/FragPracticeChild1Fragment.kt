package com.example.androidlearned.ui.center.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowInsetsAnimation.Bounds
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import androidx.savedstate.SavedStateRegistry
import androidx.transition.TransitionInflater
import com.example.androidlearned.R
import com.hjq.toast.Toaster

private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "msg"
class FragPracticeChild1Fragment : Fragment() {
    private var mid: String? = null
    private var msg: String? = null

    // 横，竖屏切换，保存简单,少量数据
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("Fragment视图（view）状态保存","onSaveInstanceState")
        outState.putString("name","张三")
    }
    // 恢复数据执行的操作，也可以写在onCreate,onCreateView,onViewCreated
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("Fragment视图（view）状态恢复","onViewStateRestored->${savedInstanceState?.getString("name")}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Fragment生命周期","onCreate")
        arguments?.let {
            mid = it.getString(ARG_PARAM1)
            msg = it.getString(ARG_PARAM2)
        }
//        转换动画比我在fragment管理器设置的补间动画优先级高，这里设置了，补间动画就不会生效
//        exitTransition = TransitionInflater.from(requireContext()).inflateTransition (R.transition.fade)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment生命周期","onCreateView")
        // 膨胀布局
        val mainView = inflater.inflate(R.layout.fragment_frag_practice_child1, container, false)
        mainView.findViewById<TextView>(R.id.child_1_params).text = "activity传来的参数：id=$mid;msg=$msg"
        // 获取child2传递回来的参数
        setFragmentResultListener("child2") { _, bundle ->
            val result = bundle.getString("name")
            mainView.findViewById<TextView>(R.id.child_1_params2).text = "child2返回的参数：name=${result};"
        }
        // 跳到child2,使用补间动画效果
        mainView.findViewById<TextView>(R.id.child_1_button).setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_frag, FragPracticeChild2Fragment.newInstance("2", "哈哈哈哈"),"child2")
                addToBackStack("child2")
            }
        }
        // 跳到child2,使用转换动画效果
        val img = mainView.findViewById<ImageView>(R.id.child_1_img)
        // 尝试overlay的用法
        img.post {
            val drawable = ColorDrawable(Color.BLACK)
            drawable.alpha = 80
            drawable.setBounds(0,0,img.measuredWidth,img.measuredHeight)
            img.overlay.add(drawable)
        }
        // 1.设置唯一的转换名称，允许视图从一个 Fragment 映射到下一个 Fragment
        ViewCompat.setTransitionName(img, "img_1")
        img.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                // 2.将共享元素视图和下个界面设置的转换名称添加到 FragmentTransaction。
                addSharedElement(img,"img_2")
                replace(R.id.fragment_container_view_frag, FragPracticeChild2Fragment.newInstance("2", "哈哈哈哈"),"child2")
                addToBackStack("child2")
            }
        }

        return mainView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Fragment生命周期","onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Fragment生命周期","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Fragment生命周期","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Fragment生命周期","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Fragment生命周期","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Fragment生命周期","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragment生命周期","onDestroy")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Fragment附加状态","onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Fragment附加状态","onDetach")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param mid Parameter 1.
         * @param msg Parameter 2.
         * @return A new instance of fragment FragPracticeChild1Fragment.
         */
        @JvmStatic
        fun newInstance(mid: String, msg: String) =
            FragPracticeChild1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, mid)
                    putString(ARG_PARAM2, msg)
                }
            }
    }
}