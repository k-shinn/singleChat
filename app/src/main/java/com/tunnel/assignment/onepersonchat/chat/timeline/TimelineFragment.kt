package com.tunnel.assignment.onepersonchat.chat.timeline

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview.TimelineAdapter
import com.tunnel.assignment.onepersonchat.databinding.FragmentTimelineBinding
import java.util.*

class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var timelineAdapter: TimelineAdapter
    private var list: ArrayList<Statement> = ArrayList()
    private var currentUserId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        currentUserId = arguments?.getLong(USER) as Long
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        timelineAdapter = TimelineAdapter(list, currentUserId)
        binding.timeline.setHasFixedSize(true)
        binding.timeline.layoutManager = LinearLayoutManager(context)
        binding.timeline.adapter = timelineAdapter

        val savedData = this.chatViewModel.getSavedData()
        timelineAdapter.list.addAll(savedData)
    }

    fun setViewModel(chatViewModel: ChatViewModel) {
        this.chatViewModel = chatViewModel
        this.chatViewModel.getStatements().observe(this, Observer { it ->
            it?.let {
                timelineAdapter.list.add(it)
                timelineAdapter.notifyDataSetChanged()
            }
        })
    }

    companion object {
        const val USER = "USER"
        fun createInstance(userId: Long) = TimelineFragment().apply {
            arguments = Bundle().apply {
                putLong(USER, userId)
            }
        }
    }
}