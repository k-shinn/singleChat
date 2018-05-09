package com.tunnel.assignment.onepersonchat.chat.editor

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.ChatViewModel
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.databinding.FragmentEditorBinding
import java.util.*

class EditorFragment : Fragment() {

    private lateinit var binding: FragmentEditorBinding
    private lateinit var chatViewModel: ChatViewModel
    private var currentUserId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor, container, false)
        currentUserId = arguments?.getLong(USER) as Long

        val editWatcher = EditWatcher(object : EditWatcher.ChangeListener {
            override fun onChanged(isExist: Boolean) {
                binding.sendButton.isEnabled = isExist
            }
        })

        binding.editor.addTextChangedListener(editWatcher)
        binding.sendButton.setOnClickListener {
            val editable = binding.editor.text
            val message = editable.toString()
            val statement = Statement(0, message, Calendar.getInstance().timeInMillis, currentUserId)
            chatViewModel.sendMessage(statement)
            editable.clear()
        }
        return binding.root
    }

    fun setViewModel(chatViewModel: ChatViewModel) {
        this.chatViewModel = chatViewModel
    }

    companion object {
        const val USER = "USER"
        fun createInstance(userId: Long) = EditorFragment().apply {
            arguments = Bundle().apply {
                putLong(USER, userId)
            }
        }
    }
}