package com.rk.quex.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.repository.MemberRepo

class AnswerViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _answers = MutableLiveData<ArrayList<Answer>>()
    val answers: LiveData<ArrayList<Answer>>
        get() {
            return _answers
        }

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() {
            return _result
        }

    init {

        val coin = savedStateHandle.get<String>("coin").toString()
        val uid = savedStateHandle.get<String>("uid").toString()
        val date = savedStateHandle.get<Int>("date")!!.toInt()
        val time = savedStateHandle.get<Int>("time")!!.toInt()

        getAnswers(coin, uid, date, time)
    }

    fun getAnswers(coin: String, above_uid: String, top_date: Int, top_time: Int) {

        memberRepo.getAnswers(coin, above_uid, top_date, top_time)

        _answers = memberRepo.answers
    }

    fun postAnswer(
        above_uid: String,
        coin: String,
        comment: String,
        top_date: Int,
        top_time: Int,
    ) {

        memberRepo.postAnswer(above_uid, coin, comment, top_date, top_time)

        _result = memberRepo.result
    }
}