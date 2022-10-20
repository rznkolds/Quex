package com.rk.quex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.repository.MemberRepo

class AnswerViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _answers = MutableLiveData<ArrayList<Answer>>()

    var result = MutableLiveData<Boolean>()

    val answers: LiveData<ArrayList<Answer>>
        get() {

            return _answers
        }

    init {

        val coin = savedStateHandle.get<String>("coin").toString()
        val uid = savedStateHandle.get<String>("uid").toString()
        val date = savedStateHandle.get<Int>("date")!!.toInt()
        val time = savedStateHandle.get<Int>("time")!!.toInt()

        getAnswers(coin,uid,date,time)
    }

    fun getAnswers(coin: String, above_uid: String, top_date: Int, top_time: Int) {

        memberRepo.getAnswers(coin, above_uid, top_date, top_time)

        _answers = memberRepo.answers
    }
}