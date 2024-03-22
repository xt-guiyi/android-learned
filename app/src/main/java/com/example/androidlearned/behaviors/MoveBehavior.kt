package com.example.androidlearned.behaviors

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MoveBehavior : Behavior<View> {
    constructor():super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

      override fun layoutDependsOn(
          parent: CoordinatorLayout,
          child: View,
          dependency: View
      ): Boolean {
          return dependency is  AppBarLayout
      }

      @SuppressLint("ClickableViewAccessibility")
      override fun onDependentViewChanged(
          parent: CoordinatorLayout,
          child: View,
          dependency: View
      ): Boolean {
          val translateY:Float = abs(dependency.top.toDouble()).toFloat()
          child.translationY  = translateY
          return false
      }
}