package com.tunnel.assignment.onepersonchat.chat.model.orma

import com.github.gfx.android.orma.AccessThreadConstraint
import com.tunnel.assignment.onepersonchat.App

object OrmaDB {

    var orma: OrmaDatabase = OrmaDatabase.Builder(App.app.applicationContext)
            .name("Timeline_Database")
            .readOnMainThread(AccessThreadConstraint.NONE)
            .writeOnMainThread(AccessThreadConstraint.NONE)
            .build()

//    fun insertStatement(statement: Statement){
//        orma.insertIntoStatement(statement)
//    }
}