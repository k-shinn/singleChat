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
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.databinding.FragmentEditorBinding
import java.util.*

class EditorFragment : Fragment() {

    lateinit var chatViewModel: ChatViewModel
    lateinit var binding: FragmentEditorBinding
    lateinit var currentUser: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor, container, false)

        currentUser = arguments?.getSerializable(EditorFragment.USER) as User

        val editWatcher = EditWatcher(object : EditWatcher.ChangeListener {
            override fun onChanged(isExist: Boolean) {
                binding.sendButton.isEnabled = isExist
            }
        })
        binding.editor.addTextChangedListener(editWatcher)
        binding.sendButton.setOnClickListener {
            val editable = binding.editor.text
            val message = editable.toString()
            val statement = Statement(0, message, Calendar.getInstance().timeInMillis, currentUser)
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
        fun createInstance(currentUser: User) = EditorFragment().apply {
            arguments = Bundle().apply {
                putSerializable(USER, currentUser)
            }
        }
    }
}