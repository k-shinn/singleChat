package com.tunnel.assignment.onepersonchat

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tunnel.assignment.onepersonchat.chat.model.ChatDataRepository
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import javax.inject.Inject

class MainViewModel @Inject constructor(private val chatDataRepository: ChatDataRepository) : ViewModel() {

    private val liveData: MutableLiveData<User> = MutableLiveData()

    fun getLiveData(): LiveData<User> = liveData

    fun createGuestUser() {
        val userId = chatDataRepository.createUser(User(0, "guestUser"))
        val user = chatDataRepository.getUser(userId)
        liveData.postValue(user)
    }
}