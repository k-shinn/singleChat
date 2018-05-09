package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDB
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment

class ChatActivity : AppCompatActivity() {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // TODO: 前画面でユーザ設定口を作る
        // ユーザ設定がなければ毎回別ユーザを作る
        currentUser = createUser(User(0, "guestUser"))

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel::class.java)

        val timelineFragment = TimelineFragment.createInstance(currentUser)
        timelineFragment.setViewModel(chatViewModel)
        val editorFragment = EditorFragment.createInstance(currentUser)
        editorFragment.setViewModel(chatViewModel)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.timeline, timelineFragment)
            replace(R.id.editor, editorFragment)
            commit()
        }
    }

    private fun createUser(user: User): User {
        val id = OrmaDB.orma.insertIntoUser(user)
        return OrmaDB.orma.selectFromUser().idEq(id).get(0)
    }

}