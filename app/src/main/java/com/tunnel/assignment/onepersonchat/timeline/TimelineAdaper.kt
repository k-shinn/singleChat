package com.tunnel.assignment.onepersonchat.timeline

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tunnel.assignment.onepersonchat.databinding.TimelineRowBinding

class TimelineAdaper(var list: List<TimelineRowData>) : RecyclerView.Adapter<TimelineAdaper.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TimelineRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.text.text = list.get(position).message
    }

    class ViewHolder(var binding: TimelineRowBinding) : RecyclerView.ViewHolder(binding.root)
}