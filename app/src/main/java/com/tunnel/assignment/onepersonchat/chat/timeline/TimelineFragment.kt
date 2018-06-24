package com.tunnel.assignment.onepersonchat.chat.timeline

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.App
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDatabase
import com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview.TimelineAdapter
import com.tunnel.assignment.onepersonchat.databinding.FragmentTimelineBinding
import javax.inject.Inject

/**
 * チャットのタイムラインを表示するFragment
 *
 * Viewの生成とDataの設定、橋渡しを行う
 * View(RecyclerView)の見え方はViewに任せる
 */
class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var timelineAdapter: TimelineAdapter
    private var currentUserId: Long = 0

    @Inject
    lateinit var ormaDatabase: OrmaDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

        (activity?.application as App).getComponent().inject(this)
        checkInject()
    }

    /**
     * RecyclerViewの初期化
     */
    private fun initRecyclerView() {
        timelineAdapter = TimelineAdapter(currentUserId)
        binding.timeline.setHasFixedSize(true)
        binding.timeline.layoutManager = LinearLayoutManager(context)
        binding.timeline.adapter = timelineAdapter

        // 初期Dataとして保存済のDataを取得する
        val savedData = chatViewModel.getSavedData()
        timelineAdapter.list.addAll(savedData)

        // Dataの更新を監視
        chatViewModel.getLiveData().observe(this, Observer { it ->
            it?.let {
                timelineAdapter.list.add(it)
                timelineAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun checkInject() {
        val orderByIdAsc = ormaDatabase.relationOfStatement().orderByIdAsc()
        val list = orderByIdAsc.toList()
        Log.d("injectTest at fragment", "listSize: " + list.size)
        for (statement in list) {
            Log.d("injectTest at fragment", statement.message)
        }
    }

    companion object {
        fun createInstance(userId: Long, viewModel: ChatViewModel) = TimelineFragment().apply {
            currentUserId = userId
            chatViewModel = viewModel
        }
    }
}