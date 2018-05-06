package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDB
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement

class ChatViewModel : ViewModel() {

    private val statements: MutableLiveData<Statement> = MutableLiveData()

    init {
        val relation = OrmaDB.orma.relationOfStatement().orderByDateLongAsc()
        for (statement in relation.toList()) {
            statements.postValue(statement)
        }
    }

    fun getStatements(): LiveData<Statement> = statements

    fun setNavigator(editorNavigator: EditorNavigator) {

    }

    fun sendMessage(statement: Statement) {
        statements.postValue(statement)

        OrmaDB.orma.insertIntoStatement(statement)
    }

}