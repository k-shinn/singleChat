package com.tunnel.assignment.onepersonchat.chat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.tunnel.assignment.onepersonchat.chat.editor.EditorNavigator
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDB
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement_Relation

class ChatViewModel : ViewModel() {

    private val statements: MutableLiveData<Statement> = MutableLiveData()

    fun getStatements(): LiveData<Statement> = statements

    fun setNavigator(editorNavigator: EditorNavigator) {

    }

    fun sendMessage(statement: Statement) {
        statements.postValue(statement)
        OrmaDB.orma.insertIntoStatement(statement)
    }

    fun getSavedData(): List<Statement> {
        val relation = OrmaDB.orma.relationOfStatement().orderByDateLongAsc()
        return relation.toList()
    }

}