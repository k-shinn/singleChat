package com.tunnel.assignment.onepersonchat.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tunnel.assignment.onepersonchat.App
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.editor.EditorFragment
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.chat.timeline.TimelineFragment

/**
 * チャット画面のActivity
 *
 * FragmentやViewModelの生成管理を担う
 */
class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

//        val user = intent.getParcelableExtra<User>(USER_DATA)
        val user = intent.getLongExtra(USER_DATA, 0)

        // Inject
//        (application as App).getComponent().inject(this)

        // fragment、ViewModel設定
        val timelineFragment = TimelineFragment.createInstance(user)
        val editorFragment = EditorFragment.createInstance(user)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.timeline, timelineFragment)
            replace(R.id.editor, editorFragment)
            commit()
        }
    }

    companion object {
        const val USER_DATA = "userData"
        fun createIntent(context: Context, user: User): Intent {
            return Intent(context, ChatActivity::class.java).apply {
//                putExtra(USER_DATA, user)
                putExtra(USER_DATA, user.id)
            }
        }
    }

}