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
import com.tunnel.assignment.onepersonchat.chat.model.Statement
import com.tunnel.assignment.onepersonchat.chat.model.User
import com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview.TimelineAdapter
import com.tunnel.assignment.onepersonchat.databinding.FragmentTimelineBinding
import java.util.*

class TimelineFragment : Fragment() {

    lateinit var binding: FragmentTimelineBinding

    lateinit var chatViewModel: ChatViewModel

    lateinit var timelineAdapter: TimelineAdapter

    var list: ArrayList<Statement> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)

        val user = arguments?.getSerializable(USER) is User

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
    }


    private fun initRecyclerView() {
        list.add(Statement("first", Calendar.getInstance(), User("test", "test")))
        timelineAdapter = TimelineAdapter(list)
        binding.timeline.setHasFixedSize(true)
        binding.timeline.layoutManager = LinearLayoutManager(context)
        binding.timeline.adapter = timelineAdapter
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
        fun createInstance(currentUser: User) = TimelineFragment().apply {
            arguments = Bundle().apply {
                putSerializable(USER, currentUser)
            }
        }
    }
}