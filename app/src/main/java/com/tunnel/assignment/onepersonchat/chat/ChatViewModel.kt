package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.Statement

class ChatViewModel : ViewModel() {

    private val statements: MutableLiveData<Statement> = MutableLiveData()

    fun getStatements(): LiveData<Statement> = statements

    fun setNavigator(editorNavigator: EditorNavigator) {

    }

    fun sendMessage(statement: Statement) {
        statements.postValue(statement)
    }

}