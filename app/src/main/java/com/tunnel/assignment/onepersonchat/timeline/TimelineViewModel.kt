package com.tunnel.assignment.onepersonchat.timeline

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TimelineViewModel : ViewModel() {

    private val liveData = MutableLiveData<TimelineRowData>()

    fun getLiveData(): LiveData<TimelineRowData> = liveData

    fun addData(string: String) {
        liveData.postValue(TimelineRowData(string))
    }

}