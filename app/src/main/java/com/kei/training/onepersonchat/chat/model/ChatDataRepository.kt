package com.kei.training.onepersonchat.chat.model

import com.kei.training.onepersonchat.chat.model.orma.OrmaDatabase
import com.kei.training.onepersonchat.chat.model.orma.Statement
import com.kei.training.onepersonchat.chat.model.orma.User
import javax.inject.Inject

/**
 * チャットデータのRepositoryクラス
 *
 * データの保存方法をActivityから隠蔽し、DB等の存在を意識させない
 * -> FirebaseとかローカルDB以外に保存する時にここ以降で分岐する
 */
class ChatDataRepository
@Inject constructor(private val ormaDatabase: OrmaDatabase) {

    /**
     * メッセージの保存
     *
     * @param statement 保存するメッセージ情報
     */
    fun postMessage(statement: Statement) {
        ormaDatabase.insertIntoStatement(statement)
    }

    /**
     * 保存されたDataを取得する
     *
     * @return 全Statementのリスト
     */
    fun getSavedData(): List<Statement> {
        val relation = ormaDatabase.relationOfStatement().orderByDateLongAsc()
        return relation.toList()
    }

    /**
     * 新規にユーザを作成し保存する
     *
     * @param user 新規ユーザ情報
     * @return 作成されたユーザID
     */
    fun createUser(user: User): Long {
        return ormaDatabase.insertIntoUser(user)
    }

    fun getUser(id: Long): User? {
        val relation = ormaDatabase.relationOfUser().orderByIdAsc()
        for (user in relation) {
            if (user.id == id) {
                return user
            }
        }
        return null
    }

}