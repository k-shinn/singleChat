package com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.databinding.TimelineMyRowBinding
import com.tunnel.assignment.onepersonchat.databinding.TimelineRowBinding
import java.text.SimpleDateFormat
import java.util.*

class TimelineAdapter(var list: ArrayList<Statement>, var userId: Long) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    companion object {
        private const val MY_VIEW: Int = 0
        private const val OTHER_VIEW: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val viewDataBinding = if (viewType == Companion.MY_VIEW) {
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.timeline_my_row, parent, false)
        } else {
            DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.timeline_row, parent, false)
        }
        return TimelineViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].userId == userId) {
            Companion.MY_VIEW
        } else {
            Companion.OTHER_VIEW
        }
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        if (holder.itemViewType == Companion.MY_VIEW) {
            val rowBinding = holder.binding as TimelineMyRowBinding
            val date = convertDate(list[position].dateLong)
            rowBinding.date.text = date
            rowBinding.text.text = list[position].message
        } else {
            val rowBinding = holder.binding as TimelineRowBinding
            val date = convertDate(list[position].dateLong)
            rowBinding.date.text = date
            rowBinding.text.text = list[position].message
        }
    }

    private fun convertDate(dateLong: Long): String? {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.JAPAN)
        return dateFormat.format(Date(dateLong))
    }

    class TimelineViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}