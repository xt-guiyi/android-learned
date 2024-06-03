package com.example.androidlearned.ui.home.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ContextMenu
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidlearned.R
import com.example.androidlearned.databinding.FragmentMenusWidgetBinding
import com.example.androidlearned.utils.Display
import me.saket.cascade.CascadePopupMenu
import me.saket.cascade.CascadePopupWindow
import me.saket.cascade.overrideAllPopupMenus


class MenusWidgetFragment : Fragment() {

    lateinit var  binding:FragmentMenusWidgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenusWidgetBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    fun init(){
        // toolbar菜单
        binding.otherToolbarMenu.overrideAllPopupMenus{ context, anchor ->
            val toolbarMenu = CascadePopupMenu(context, anchor)
            // 设置屏幕变暗
            applyBackgroundDim(toolbarMenu.popup,anchor,100)
            toolbarMenu
        }
        // 上下文菜单(contextMenu)
        registerForContextMenu(binding.menuContextTextView)
        // 弹出式菜单(PopupMenu)
        binding.menuPopupButton.setOnClickListener {
            showMenu(it,R.menu.menu)
        }
        // 悬浮菜单(PopupWindow)
        binding.menuLevitateButton.setOnClickListener {
            showLevitateMenu(it,R.menu.menu)
        }
        // 列表弹出式菜单(ListPopupWindow)
        binding.menuListPopupButton.setOnClickListener {
            showMenuList()
        }
        // 第三方弹出式菜单
        binding.menuOtherPopupButton.setOnClickListener {
            showOtherMenu(it,R.menu.menu)
        }

        // 第三方悬浮菜单
        binding.menuOtherLevitateButton.setOnClickListener {
            showOtherLevitateMenu(it,R.menu.menu)
        }
    }
    // 创建上下文菜单
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_1 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_2 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_3 -> {
                Toast.makeText(requireActivity(),item.title,Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }


    private fun showMenu(v: View?, menuRes: Int) {
        val popup = PopupMenu(requireContext(),v,Gravity.END)
        popup.menuInflater.inflate(menuRes,popup.menu)
        popup.setOnMenuItemClickListener {
            Toast.makeText(requireContext(),it.title, Toast.LENGTH_SHORT).show()
            true
        }

        popup.setOnDismissListener {
            Toast.makeText(requireContext(),"菜单关闭了",Toast.LENGTH_SHORT).show()
        }
        popup.show()
    }


    private fun showLevitateMenu(v: View, menuRes: Int) {
        val customLayout = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_menus_widget_levitate_layout,null,false)
        val popupWindow = PopupWindow(customLayout,
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        applyBackgroundDim(popupWindow,v,100)
        //设置CascadePopupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAsDropDown(v,Display.dip2px(requireContext(),180f),Display.dip2px(requireContext(),0f))

    }

    private fun showMenuList() {
        val listPopupWindow = ListPopupWindow(requireContext(),null, com.google.android.material.R.attr.listPopupWindowStyle)
        // Set button as the list popup's anchor
        listPopupWindow.anchorView = binding.menuListPopupButton
        // Set list popup's content
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(requireContext(), R.layout.fragment_menus_widget_list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)

        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            listPopupWindow.dismiss()
        }
        listPopupWindow.show()
    }


    private fun showOtherMenu(v: View, menuRes: Int) {
        val popupMenu = CascadePopupMenu(requireContext(), v,Gravity.getAbsoluteGravity(Gravity.START,200))
        popupMenu.inflate(R.menu.menu)

        applyBackgroundDim(popupMenu.popup,v,100)
        popupMenu.show()
        // 修改位置,相对于v,不传v就是相对于窗口
        popupMenu.popup.update(
            v,
            Display.dip2px(requireContext(),180f),
            Display.dip2px(requireContext(),50f),
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun showOtherLevitateMenu(v: View, menuRes: Int) {
        val popupWindow = CascadePopupWindow(requireContext())
        val customLayout = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_menus_widget_levitate_layout,null,false)
        popupWindow.contentView.addView(customLayout)
        //设置CascadePopupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        applyBackgroundDim(popupWindow,v,100)
        popupWindow.showAsDropDown(v,Display.dip2px(requireContext(),180f),Display.dip2px(requireContext(),50f))
    }

    /**
     * 让背景变暗，github上已有开发者为Cascade库提交了相关代码，但是还没有合并，我这里先copy到本地用🤭
     * url: https://github.com/saket/cascade/pull/45
     * */
    private fun applyBackgroundDim(popup: PopupWindow , anchor:View,dimAmount: Int) {
        val dim: Drawable = ColorDrawable(Color.BLACK)
        dim.setBounds(0, 0, anchor.rootView.width, anchor.rootView.height)
        dim.alpha = 0
        val overlay = anchor.rootView.overlay
        overlay.add(dim)

        /* FADE IN: Animating dim from 0 to dimAmount */
        ObjectAnimator.ofInt(dim, "alpha", dimAmount).apply {
            duration = 300
            start()
        }

        popup.setOnDismissListener {
            /* FADE OUT: Animating dim from dimAbout to 0 */
            ObjectAnimator.ofInt(dim, "alpha", 0).apply {
                duration = 300
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        overlay.clear()
                    }
                })
                start()
            }
        }
    }





}