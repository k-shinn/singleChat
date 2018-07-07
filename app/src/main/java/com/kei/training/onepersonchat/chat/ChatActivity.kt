package com.kei.training.onepersonchat.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kei.training.onepersonchat.R
import com.kei.training.onepersonchat.chat.editor.EditorFragment
import com.kei.training.onepersonchat.chat.model.orma.User
import com.kei.training.onepersonchat.chat.timeline.TimelineFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * チャット画面のActivity
 *
 * FragmentやViewModelの生成管理を担う
 */
class ChatActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inject
//        AndroidInjection.inject(this)

        setContentView(R.layout.activity_chat)

        val user = intent.getParcelableExtra<User>(USER_DATA)

        // fragment生成
        val timelineFragment = TimelineFragment.createInstance(user.id)
        val editorFragment = EditorFragment.createInstance(user.id)

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
                putExtra(USER_DATA, user)
            }
        }
    }

}