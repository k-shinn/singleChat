package com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.databinding.TimelineCurrentUserRowBinding
import com.tunnel.assignment.onepersonchat.databinding.TimelineOtherRowBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * タイムラインFragment用のRecyclerViewAdapter
 *
 * RecyclerViewの見え方の設定を行う
 */
class TimelineAdapter(private val currentUserId: Long) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    // 現在のユーザとその他のユーザで見た目を分ける
    companion object {
        private const val CURRENT_USER_VIEW: Int = 0      // 現在のユーザ判定
        private const val OTHER_VIEW: Int = 1   // 他のユーザ判定
    }

    val list: ArrayList<Statement> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val viewDataBinding = if (viewType == Companion.CURRENT_USER_VIEW) {
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.timeline_current_user_row, parent, false)
        } else {
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.timeline_other_row, parent, false)
        }
        return TimelineViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].userId == currentUserId) {
            Companion.CURRENT_USER_VIEW
        } else {
            Companion.OTHER_VIEW
        }
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        if (holder.itemViewType == Companion.CURRENT_USER_VIEW) {
            val rowBinding = holder.binding as TimelineCurrentUserRowBinding
            val date = convertDate(list[position].dateLong)
            rowBinding.date.text = date
            rowBinding.message.text = list[position].message
        } else {
            val rowBinding = holder.binding as TimelineOtherRowBinding
            val date = convertDate(list[position].dateLong)
            rowBinding.date.text = date
            rowBinding.message.text = list[position].message
        }
    }

    /**
     * 日付の表記変更
     *
     * @param dateLong Dateのミリ秒
     * @return "HH:mm"表記の値
     */
    private fun convertDate(dateLong: Long): String? {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.JAPAN)
        return dateFormat.format(Date(dateLong))
    }

    class TimelineViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}