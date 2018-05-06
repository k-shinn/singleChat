package com.tunnel.assignment.onepersonchat.chat.model

import com.github.gfx.android.orma.ModelFactory
import com.tunnel.assignment.onepersonchat.chat.model.orma.*

object ChatDataRepository {

    var orma: OrmaDatabase = OrmaDB.orma

    fun sendPost(statement: Statement) {
        orma.relationOfStatement().inserter().execute(statement)
    }

    fun fetchPost(){
        var orderByDateLongAsc = orma.relationOfStatement().orderByDateLongAsc()
    }
}