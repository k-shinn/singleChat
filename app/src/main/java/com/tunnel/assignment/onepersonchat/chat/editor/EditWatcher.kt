package com.tunnel.assignment.onepersonchat.chat.editor

import android.text.Editable
import android.text.TextWatcher

class EditWatcher(private val changeListener: ChangeListener) : TextWatcher {

    interface ChangeListener {
        fun onChanged(isExist: Boolean)
    }

    override fun afterTextChanged(editable: Editable?) {
        if (editable.isNullOrBlank()) {
            changeListener.onChanged(false)
        } else {
            changeListener.onChanged(true)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}