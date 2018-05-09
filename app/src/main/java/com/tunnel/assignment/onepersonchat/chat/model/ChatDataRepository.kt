package com.tunnel.assignment.onepersonchat.chat.model

import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDB
import com.tunnel.assignment.onepersonchat.chat.model.orma.OrmaDatabase
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.chat.model.orma.User

/**
 * チャットデータのRepositoryクラス
 *
 * データの保存方法をActivityから隠蔽し、DBやキャッシュを存在を意識させない
 */
object ChatDataRepository {

    // TODO: DBの他にキャッシュを実装

    private val orma: OrmaDatabase = OrmaDB.orma

    /**
     * メッセージの保存
     *
     * @param statement 保存するメッセージ情報
     */
    fun postMessage(statement: Statement) {
        orma.insertIntoStatement(statement)
    }

    /**
     * 保存されたDataを取得する
     *
     * @return 全Statementのリスト
     */
    fun getSavedData(): List<Statement> {
        val relation = orma.relationOfStatement().orderByDateLongAsc()
        return relation.toList()
    }

    /**
     * 新規にユーザを作成し保存する
     *
     * @param user 新規ユーザ情報
     * @return 作成されたユーザID
     */
    fun createUser(user: User): Long {
        return orma.insertIntoUser(user)
    }
}