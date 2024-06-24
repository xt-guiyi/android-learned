package com.example.androidlearned.ui.center.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionListenerAdapter
import com.example.androidlearned.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "msg"

/**
 * A simple [Fragment] subclass.
 * Use the [FragPracticeChild2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragPracticeChild2Fragment : Fragment() {
    private var mid: String? = null
    private var msg: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mid = it.getString(ARG_PARAM1)
            msg = it.getString(ARG_PARAM2)
        }
        // 入场转换动画
//        enterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.slide) // xml定义方式
        enterTransition = Slide(Gravity.BOTTOM) // 类实例化方式
//        enterTransition = Explode() // 类实例化方式

    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainView = inflater.inflate(R.layout.fragment_frag_practice_child2, container, false)
      // 设置child1传来的参数
        mainView.findViewById<TextView>(R.id.child_2_params).text = "child1传来的参数：id=$mid;msg=$msg"
        // 给child1传参数
        setFragmentResult("child2",Bundle().apply {
            putString("name","李四")
        })

        // 1.设置唯一的转换名称
        val img = mainView.findViewById<ImageView>(R.id.child_2_img)
        ViewCompat.setTransitionName(img, "img_2")
        // 3.设置共享元素转换动画。
        val transitionClient = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
        sharedElementEnterTransition = transitionClient
        // 监听转换动画结束
        transitionClient?.addListener(object : TransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition) {
                transitionClient.removeListener(this)
                mainView.findViewById<TextView>(R.id.child_2_animate_text).post{
                    mainView.findViewById<TextView>(R.id.child_2_animate_text).visibility = View.VISIBLE
                }
            }
        })
        return mainView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param mid Parameter 1.
         * @param msg Parameter 2.
         * @return A new instance of fragment FragPracticeChild2Fragment.
         */
        @JvmStatic
        fun newInstance(mid: String, msg: String) =
            FragPracticeChild2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, mid)
                    putString(ARG_PARAM2, msg)
                }
            }
    }
}