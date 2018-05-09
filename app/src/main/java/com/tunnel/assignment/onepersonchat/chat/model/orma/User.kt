package com.tunnel.assignment.onepersonchat.chat.model.orma

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

@Table
data class User(
        @Column
        @PrimaryKey(autoincrement = true)
        var id: Long,
        @Setter("name")
        @Column(defaultExpr = "guest")
        var name: String
)