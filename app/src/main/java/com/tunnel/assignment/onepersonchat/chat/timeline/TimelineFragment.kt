package com.tunnel.assignment.onepersonchat.chat.timeline

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.App
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
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
    private var currentUserId: Long = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        currentUserId = arguments?.getLong(EXTRA_USER_ID, 0) ?: 0
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.application as App).getComponent().inject(this)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)

        initRecyclerView(viewModel)
    }

    /**
     * RecyclerViewの初期化
     */
    private fun initRecyclerView(viewModel: ChatViewModel) {
        val timelineAdapter = TimelineAdapter(currentUserId)
        binding.timeline.setHasFixedSize(true)
        binding.timeline.layoutManager = LinearLayoutManager(context)
        binding.timeline.adapter = timelineAdapter

        // 初期Dataとして保存済のDataを取得する
        val savedData = viewModel.getSavedData()
        timelineAdapter.list.addAll(savedData)

        // Dataの更新を監視
        viewModel.getLiveData().observe(this, Observer { it ->
            it?.let {
                timelineAdapter.list.add(it)
                timelineAdapter.notifyDataSetChanged()
            }
        })
    }

    companion object {
        const val EXTRA_USER_ID = "userId"
        fun createInstance(userId: Long): TimelineFragment {
            val bundle = Bundle().apply {
                putLong(EXTRA_USER_ID, userId)
            }
            return TimelineFragment().apply {
                arguments = bundle
            }
        }
    }
}