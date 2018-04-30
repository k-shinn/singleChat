package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.User
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment
import java.util.*

class ChatActivity : AppCompatActivity(), EditorNavigator {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // ユーザ設定がなければゲスト登録
        val user = intent.getSerializableExtra("user")
        currentUser = user.let { User(UUID.randomUUID().toString(), "guest") }

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)
        chatViewModel.setNavigator(this)

        val transaction = supportFragmentManager.beginTransaction()
        val timelineFragment = TimelineFragment.createInstance(currentUser)
        timelineFragment.setViewModel(chatViewModel)
        val editorFragment = EditorFragment.createInstance(currentUser)
        editorFragment.setViewModel(chatViewModel)
        transaction.replace(R.id.timeline, timelineFragment)
        transaction.replace(R.id.editor, editorFragment)
        transaction.commit()

    }

    override fun sendMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}