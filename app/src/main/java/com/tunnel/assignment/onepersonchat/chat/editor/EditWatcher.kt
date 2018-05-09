package com.tunnel.assignment.onepersonchat.chat.editor

import android.text.Editable
import android.text.TextWatcher

/**
 * EditTextの変更を監視する
 */
class EditWatcher(private val changeListener: ChangeListener) : TextWatcher {

    interface ChangeListener {
        /**
         * Text変更後、Text内容が存在するかを監視するリスナー
         * @param isTextExist true:テキストが存在する, false:テキストが存在しない
         */
        fun onChanged(isTextExist: Boolean)
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