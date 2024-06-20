package com.example.androidlearned.ui.center.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentAnimationPracticeRootBinding


class AnimationPracticeRootFragment : Fragment() {
    lateinit var binding: FragmentAnimationPracticeRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationPracticeRootBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init() {
        binding.attrAnimate.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_animate, AnimationPracticeAttrAnimFragment(),"attrAnim")
                addToBackStack("attrAnim")
            }
        }

        binding.drawableAnimate.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_animate, AnimationPracticeDrawableAnimFragment(),"drawableAnim")
                addToBackStack("drawableAnim")
            }
        }

        binding.transitionAnimate.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_animate, AnimationPracticeTransitionAnimFragment(),"transitionAnim")
                addToBackStack("transitionAnim")
            }
        }

        binding.motionLayoutAnimate.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_animate, AnimationPracticeMotionLayoutAnimFragment(),"motionLayoutAnim")
                addToBackStack("motionLayoutAnim")
            }
        }

        binding.lottieAnimate.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slider_in_right_enter,R.anim.slider_out_left_exit,R.anim.slider_in_left_enter,R.anim.slider_out_right_exit)
                replace(R.id.fragment_container_view_animate, AnimationPracticeLottieAnimFragment(),"lottieAnim")
                addToBackStack("lottieAnim")
            }
        }
    }
}