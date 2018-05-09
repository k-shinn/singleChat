package com.tunnel.assignment.onepersonchat.chat.editor

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
import com.tunnel.assignment.onepersonchat.databinding.FragmentEditorBinding
import java.util.*

/**
 * メッセージを作成、送信するFragment
 *
 * Viewの生成とDataの設定、橋渡しを行う
 */
class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    private lateinit var chatViewModel: ChatViewModel
    private var currentUserId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor, container, false)

        binding.sendButton.setOnClickListener {
            val editable = binding.editor.text
            val message = binding.editor.text.toString()
            chatViewModel.postMessage(message, Calendar.getInstance().timeInMillis, currentUserId)
            editable.clear()
        }

        // テキストの変更監視、テキストが存在しない時はボタンを非活性にする
        val editWatcher = EditWatcher(object : EditWatcher.ChangeListener {
            override fun onChanged(isTextExist: Boolean) {
                binding.sendButton.isEnabled = isTextExist
            }
        })
        binding.editor.addTextChangedListener(editWatcher)

        return binding.root
    }

    companion object {
        fun createInstance(userId: Long, viewModel: ChatViewModel) = EditorFragment().apply {
            currentUserId = userId
            chatViewModel = viewModel
        }
    }
}