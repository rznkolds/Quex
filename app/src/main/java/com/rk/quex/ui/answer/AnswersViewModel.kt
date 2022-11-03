package com.rk.quex.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.CoinRepo

class AnswersViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var coinRepo = CoinRepo()

    private var _answers = MutableLiveData<ArrayList<Answer>>()
    val answers: LiveData<ArrayList<Answer>>
        get() = _answers

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        val coin = savedStateHandle.get<String>("coin").toString()
        val uid = savedStateHandle.get<String>("uid").toString()
        val date = savedStateHandle.get<Int>("date")!!.toInt()
        val time = savedStateHandle.get<Int>("time")!!.toInt()

        getAnswers(coin, uid, date, time)
    }

    fun getAnswers(coin: String, uid: String, date: Int, time: Int) {
        coinRepo.getAnswers(coin, uid, date, time)
        _answers = coinRepo.answers
    }

    fun postAnswer(uid: String, coin: String, comment: String, date: Int, time: Int) {
        coinRepo.postAnswer(uid, coin, comment, date, time)
        _result = coinRepo.result
    }

    fun deleteAnswer(answer: Answer) {
        coinRepo.deleteAnswer(answer)
        _result = coinRepo.result
    }
}