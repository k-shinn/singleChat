package com.tunnel.assignment.onepersonchat.chat.editor

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.App
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
import com.tunnel.assignment.onepersonchat.databinding.FragmentEditorBinding
import java.util.*
import javax.inject.Inject

/**
 * メッセージを作成、送信するFragment
 *
 * Viewの生成とDataの設定、橋渡しを行う
 */
class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    private var currentUserId: Long = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        currentUserId = arguments?.getLong(EXTRA_USER_ID, 0) ?: 0
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.application as App).getComponent().inject(this)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)
        initEditor(viewModel)
    }

    private fun initEditor(viewModel: ChatViewModel) {
        binding.sendButton.setOnClickListener {
            val editable = binding.editor.text
            val message = binding.editor.text.toString()
            viewModel.postMessage(message, Calendar.getInstance().timeInMillis, currentUserId)
            editable.clear()
        }

        // テキストの変更監視、テキストが存在しない時はボタンを非活性にする
        val editWatcher = EditWatcher(object : EditWatcher.ChangeListener {
            override fun onChanged(isTextExist: Boolean) {
                binding.sendButton.isEnabled = isTextExist
            }
        })
        binding.editor.addTextChangedListener(editWatcher)
    }

    companion object {
        const val EXTRA_USER_ID = "userId"
        fun createInstance(userId: Long): EditorFragment {
            val bundle = Bundle().apply {
                putLong(EXTRA_USER_ID, userId)
            }
            return EditorFragment().apply {
                arguments = bundle
            }
        }
    }
}