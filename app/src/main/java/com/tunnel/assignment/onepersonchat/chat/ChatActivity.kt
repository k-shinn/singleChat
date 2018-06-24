package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.tunnel.assignment.onepersonchat.App
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDatabase
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment
import javax.inject.Inject

/**
 * チャット画面のActivity
 *
 * FragmentやViewModelの生成管理を担う
 */
class ChatActivity : AppCompatActivity() {

    private lateinit var chatViewModel: ChatViewModel
    private var currentUserId: Long = 0

    @Inject
    lateinit var ormaDatabase: OrmaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Inject
        (application as App).getComponent().inject(this)
        checkInject()


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

    private fun checkInject() {
        val orderByIdAsc = ormaDatabase.relationOfStatement().orderByIdAsc()
        val list = orderByIdAsc.toList()
        Log.d("injectTest", "listSize: " + list.size)
        for (statement in list) {
            Log.d("injectTest", statement.message)
        }

    }

}