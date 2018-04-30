package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.Statement
import com.tunnel.assignment.onepersonchat.chat.model.User
import java.util.*

class ChatViewModel : ViewModel() {

    private val statements: MutableLiveData<Statement> = MutableLiveData()

    fun getStatemensts(): LiveData<Statement> = statements

    fun setNavigator(editorNavigator: EditorNavigator) {

    }

    fun sendMessage(message: String) {
        val newStatement = Statement(message, Calendar.getInstance(), User("test", "test"))
        statements.postValue(newStatement)
    }

}