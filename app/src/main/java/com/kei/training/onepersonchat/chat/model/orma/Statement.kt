package com.kei.training.onepersonchat.chat.model.orma

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

@Table
data class Statement(
        @Column
        @PrimaryKey(autoincrement = true)
        var id: Long,
        @Setter("message")
        @Column
        var message: String = "",
        @Setter("dateLong")
        @Column(indexed = true)
        var dateLong: Long,
        @Setter("userId")
        @Column(indexed = true)
        var userId: Long
)
