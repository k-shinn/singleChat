package com.tunnel.assignment.onepersonchat

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tunnel.assignment.onepersonchat.databinding.ActivityMainBinding
import com.tunnel.assignment.onepersonchat.timeline.TimeLineActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener({ view ->
            val intent = Intent(view.context, TimeLineActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            view.context.startActivity(intent)
        })
    }
}
