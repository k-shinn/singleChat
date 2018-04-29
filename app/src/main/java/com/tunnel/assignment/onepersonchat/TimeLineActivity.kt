package com.tunnel.assignment.onepersonchat

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tunnel.assignment.onepersonchat.databinding.ActivityTimeLineBinding

class TimeLineActivity : AppCompatActivity() {
    private val binding: ActivityTimeLineBinding by lazy {
        DataBindingUtil.setContentView<ActivityTimeLineBinding>(this, R.layout.activity_time_line)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
