package com.tunnel.assignment.onepersonchat.chat.timeline.recyclerview

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.R
import com.tunnel.assignment.onepersonchat.chat.model.orma.Statement
import com.tunnel.assignment.onepersonchat.chat.model.orma.User
import com.tunnel.assignment.onepersonchat.databinding.TimelineMyRowBinding
import com.tunnel.assignment.onepersonchat.databinding.TimelineRowBinding
import java.util.*

class TimelineAdapter(var list: ArrayList<Statement>, var user: User) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

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

//        val binding = if (viewType == MY_VIEW) {
//            TimelineMyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        } else {
//            TimelineRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        }
//        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].user?.id == user.id) {
            Companion.MY_VIEW
        } else {
            Companion.OTHER_VIEW
        }
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        if (holder.itemViewType == Companion.MY_VIEW) {
            val rowBinding = holder.binding as TimelineMyRowBinding
            rowBinding.date.text = "12:59"
            rowBinding.text.text = list[position].message
        } else {
            val rowBinding = holder.binding as TimelineRowBinding
            rowBinding.date.text = "12:00"
            rowBinding.text.text = list[position].message

        }
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.text.text = list[position].message
//    }
//
//    class ViewHolder(var binding: TimelineRowBinding) : RecyclerView.ViewHolder(binding.root)

    class TimelineViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}