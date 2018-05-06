package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDB
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment

class ChatActivity : AppCompatActivity(), EditorNavigator {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // ユーザ設定がなければゲスト登録
//        val currentUser = intent.getSerializableExtra("currentUser")
        // ユーザ登録口を作るまで毎回別ユーザを作っておく
        currentUser = createUser(User(0, "instantUser"))

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

    private fun createUser(user: User): User {
        val id = OrmaDB.orma.insertIntoUser(user)
        return OrmaDB.orma.selectFromUser().idEq(id).get(0)
    }

    override fun sendMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}