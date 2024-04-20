package com.example.androidlearned.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlearned.components.ModalBottomSheet1
import com.example.androidlearned.databinding.FragmentBottomSheetWidgetBinding
import com.example.androidlearned.utils.Display
import com.google.android.material.bottomsheet.BottomSheetBehavior


class BottomSheetWidgetFragment : Fragment() {
    lateinit var binding: FragmentBottomSheetWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetWidgetBinding.inflate(inflater,container,false)
        initModalBottomSheet()
        initStandardBottomSheet()
        return binding.root
    }

    private fun initModalBottomSheet() {
        binding.modalBottomSheetControl.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet1()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet1.TAG)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initStandardBottomSheet() {
        val standardBottomSheet = binding.standardBottomSheet
        val standardBottomSheetBehavior = BottomSheetBehavior.from(standardBottomSheet)
        // 窥视高度
        standardBottomSheetBehavior.peekHeight = Display.dip2px(requireContext(),200f)
        // 禁止隐藏
//        standardBottomSheetBehavior.isHideable = false
        standardBottomSheetBehavior.isFitToContents = false
        //  STATE_COLLAPSED：底部工作表可见，但仅显示其窥视高度。这种状态通常是底部工作表的“静止位置”，并且应该具有足够的高度以指示有额外的内容供用户交互。
        //  STATE_EXPANDED：底部板材在其最大高度处可见，并且既不拖动也不沉降（见下文）。
        //  STATE_HALF_EXPANDED：底部工作表半展开（仅在 behavior_fitToContents设置为 false 时适用），既不拖动也不沉降（见下文）。
        //  STATE_HIDDEN：底部工作表不再可见，只能以编程方式重新显示。
        //  STATE_DRAGGING：用户主动向上或向下拖动底部工作表。
        //  STATE_SETTLING：拖动/滑动手势后，底部工作表会稳定到特定高度。这将是查看高度、展开高度或 0，以防用户操作导致底部工作表隐藏。
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;
         binding.standardBottomSheetStateText.text = "状态: BottomSheetBehavior.STATE_HIDDEN"

        // 监听状态和sheet的变化
        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Do something for new state.
                 when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.standardBottomSheetStateText.text = "状态: STATE_COLLAPSED"
                    BottomSheetBehavior.STATE_EXPANDED -> binding.standardBottomSheetStateText.text = "状态: STATE_EXPANDED"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> binding.standardBottomSheetStateText.text = "状态: STATE_HALF_EXPANDED"
                    BottomSheetBehavior.STATE_HIDDEN -> binding.standardBottomSheetStateText.text = "状态: STATE_HIDDEN"
                    BottomSheetBehavior.STATE_DRAGGING -> binding.standardBottomSheetStateText.text = "状态: STATE_DRAGGING"
                    BottomSheetBehavior.STATE_SETTLING -> binding.standardBottomSheetStateText.text = "状态: STATE_SETTLING"
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset.
//                Toaster.debugShow(slideOffset.toString())
            }
        }
        standardBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)


        // 打开
        binding.standardBottomSheetControl.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)  {
                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED;
                binding.standardBottomSheetStateText.text = "状态: BottomSheetBehavior.STATE_COLLAPSED"
            }else {
                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;
                binding.standardBottomSheetStateText.text = "状态: BottomSheetBehavior.STATE_HIDDEN"
            }
        }

    }

}