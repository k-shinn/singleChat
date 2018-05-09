package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment

/**
 * チャット画面のActivity
 *
 * FragmentやViewModelの生成管理を担う
 */
class ChatActivity : AppCompatActivity() {

    private lateinit var chatViewModel: ChatViewModel
    private var currentUserId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        // TODO: 前画面などでユーザ設定口を作る
        // ユーザ設定がなければ毎回別ユーザを作る
        currentUserId = chatViewModel.createUser("guestUser")

        // fragment、ViewModel設定
        val timelineFragment = TimelineFragment.createInstance(currentUserId, chatViewModel)
        val editorFragment = EditorFragment.createInstance(currentUserId, chatViewModel)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.timeline, timelineFragment)
            replace(R.id.editor, editorFragment)
            commit()
        }
    }

}