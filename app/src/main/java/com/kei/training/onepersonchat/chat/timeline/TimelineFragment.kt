package com.kei.training.onepersonchat.chat.timeline

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kei.training.onepersonchat.R
import com.kei.training.onepersonchat.chat.ChatViewModel
import com.kei.training.onepersonchat.chat.timeline.recyclerview.TimelineAdapter
import com.kei.training.onepersonchat.databinding.FragmentTimelineBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * チャットのタイムラインを表示するFragment
 *
 * Viewの生成とDataの設定、橋渡しを行う
 * View(RecyclerView)の見え方はViewに任せる
 */
class TimelineFragment : DaggerFragment() {

    private lateinit var binding: FragmentTimelineBinding

    private var currentUserId: Long = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        return binding.root
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        AndroidSupportInjection.inject(this)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        (activity?.application as App).getComponent().inject(this)

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
        fun createInstance(userId: Long) = TimelineFragment().apply {
            currentUserId = userId
        }
    }
}