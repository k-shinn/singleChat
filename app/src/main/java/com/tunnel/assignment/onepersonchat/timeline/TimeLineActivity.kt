package com.tunnel.assignment.onepersonchat.timeline

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.databinding.ActivityTimeLineBinding

class TimeLineActivity : AppCompatActivity() {
    private val binding: ActivityTimeLineBinding by lazy {
        DataBindingUtil.setContentView<ActivityTimeLineBinding>(this, R.layout.activity_time_line)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.sendButton.setOnClickListener { view ->
            Snackbar.make(view, binding.inputText.text.toString(), Snackbar.LENGTH_LONG)
                    .show()
        }

        val list = arrayListOf<TimelineRowData>().apply {
            add(TimelineRowData("test1"))
            add(TimelineRowData("test2"))
        }

        val adapter = TimelineAdaper(list)

        binding.timelineView.setHasFixedSize(true)
        binding.timelineView.layoutManager = LinearLayoutManager(this)
        binding.timelineView.adapter = adapter

    }
}
