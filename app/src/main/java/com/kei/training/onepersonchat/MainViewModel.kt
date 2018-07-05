package com.kei.training.onepersonchat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kei.training.onepersonchat.chat.model.ChatDataRepository
import com.kei.training.onepersonchat.chat.model.orma.User
import javax.inject.Inject

/**
 * MainActivity(起動画面)用ViewModel
 *
 * MainActivity上でのデータ管理を担う
 */
class MainViewModel @Inject constructor(private val chatDataRepository: ChatDataRepository) : ViewModel() {

    private val liveData: MutableLiveData<User> = MutableLiveData()

    fun getLiveData(): LiveData<User> = liveData

    /**
     * ゲストユーザを作成する
     */
    fun createGuestUser() {
        val userId = chatDataRepository.createUser(User(0, "guestUser"))
        val user = chatDataRepository.getUser(userId)
        liveData.postValue(user)
    }
}