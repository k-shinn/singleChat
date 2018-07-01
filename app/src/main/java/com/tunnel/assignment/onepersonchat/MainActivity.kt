package com.tunnel.assignment.onepersonchat

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tunnel.assignment.onepersonchat.chat.ChatActivity
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inject
        (application as App).getComponent().inject(this)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        // TODO: ゲストユーザ以外の使用
        binding.button.setOnClickListener({ _ ->
            createUser(viewModel)
        })
    }

    @SuppressLint("ShowToast")
    private fun createUser(viewModel: MainViewModel) {
        viewModel.getLiveData().observe(this, Observer { it ->
            if (it != null) {
                Toast.makeText(this, "guestUser created", Toast.LENGTH_LONG)
                startChat(it)
            } else {
                Toast.makeText(this, "Fail to createUser", Toast.LENGTH_LONG)
            }
        })
        viewModel.createGuestUser()
    }

    private fun startChat(user: User) {
        val intent = ChatActivity.createIntent(this, user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
