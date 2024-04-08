package com.example.androidlearned.ui.home.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidlearned.R
import com.example.androidlearned.utils.CommonCode

class EditTextWidgetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_widget)
        CommonCode.addNaviGator(this,supportFragmentManager,R.id.fragment_container_view_aetw,R.navigation.nav_graph_edit_text_widget)
    }
}