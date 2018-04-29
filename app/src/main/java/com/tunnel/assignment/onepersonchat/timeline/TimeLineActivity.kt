package com.tunnel.assignment.onepersonchat.timeline

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.databinding.ActivityTimeLineBinding

class TimeLineActivity : AppCompatActivity() {
    private val binding: ActivityTimeLineBinding by lazy {
        DataBindingUtil.setContentView<ActivityTimeLineBinding>(this, R.layout.activity_time_line)
    }

    private var list = arrayListOf<TimelineRowData>().apply {
        add(TimelineRowData("test1"))
        add(TimelineRowData("test2"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = TimelineAdaper(list)

        val viewModel = ViewModelProviders.of(this).get(TimelineViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            it?.let { it1 ->
                list.add(it1)
                adapter.notifyDataSetChanged()
            }
        })

        binding.timelineView.setHasFixedSize(true)
        binding.timelineView.layoutManager = LinearLayoutManager(this)
        binding.timelineView.adapter = adapter
        binding.sendButton.setOnClickListener { _ ->
            val string = binding.inputText.text.toString()
            viewModel.addData(string)
            binding.inputText.text.clear()
        }
    }
}
