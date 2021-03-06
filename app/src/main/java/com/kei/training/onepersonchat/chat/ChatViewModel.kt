package com.kei.training.onepersonchat.chat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kei.training.onepersonchat.chat.model.ChatDataRepository
import com.kei.training.onepersonchat.chat.model.orma.Statement
import com.kei.training.onepersonchat.chat.model.orma.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * チャット画面用ViewModel
 *
 * Dataの全般的な管理を担う
 * FragmentからのData更新、取得などはすべてここを通す
 */
@Singleton
class ChatViewModel @Inject constructor(private val chatDataRepository: ChatDataRepository) : ViewModel() {

    private val liveData: MutableLiveData<Statement> = MutableLiveData()

    override fun onCleared() {
        // 開き直した時に前のポストが通知される事があるため初期化
        liveData.postValue(null)
    }

    /**
     * LiveDataの取得
     *
     * @return メッセージの更新を通知するLiveData
     */
    fun getLiveData(): LiveData<Statement> = liveData

    /**
     * メッセージ送信
     *
     * LiveDataへのPostとRepositoryへの保存を行う
     *
     * @param message メッセージ内容
     * @param timeInMillis 送信時刻
     * @param userId 送信者のUserId
     */
    fun postMessage(message: String, timeInMillis: Long, userId: Long) {
        val statement = Statement(0, message, timeInMillis, userId)
        liveData.postValue(statement)
        chatDataRepository.postMessage(statement)
    }

    /**
     * 保存されたDataを取得する
     *
     * @return 全Statementのリスト
     */
    fun getSavedData(): List<Statement> {
        return chatDataRepository.getSavedData()
    }

    /**
     * 新規にユーザを作成する
     *
     * @param name 新規ユーザ名
     * @return 作成されたユーザID
     */
    fun createUser(name: String): Long {
        return chatDataRepository.createUser(User(0, name))
    }

}