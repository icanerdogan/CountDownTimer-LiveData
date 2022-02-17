package com.icanerdogan.livedatacountdowntimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var timer: CountDownTimer? = null

    var millisInFuture = MutableLiveData<Long>()

    var finished = MutableLiveData<Boolean>()

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    fun startTimer() {
        timer = object : CountDownTimer(millisInFuture.value!!.toLong(), 1000) {
            override fun onTick(p0: Long) {
                val timeLeft = p0 / 1000
                _seconds.value = timeLeft.toInt()
                finished.value = false
            }

            override fun onFinish() {
                finished.value = true
            }

        }.start()
    }

    fun stopTimer() {
        timer?.cancel()
    }
}