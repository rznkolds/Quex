package com.rk.quex.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.repository.CoinRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val coinRepo: CoinRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _answers = MutableLiveData<ArrayList<Answer>>()
    val answers: LiveData<ArrayList<Answer>>
        get() = _answers

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        val uid = savedStateHandle.get<String>("uid").toString()
        val coin = savedStateHandle.get<String>("coin").toString()
        val date = savedStateHandle.get<Int>("date")!!.toInt()
        val time = savedStateHandle.get<Int>("time")!!.toInt()

        getAnswers(uid, coin, date, time)

        _answers = coinRepo.answers
        _result = coinRepo.result
    }

    fun getAnswers(uid: String, coin: String, date: Int, time: Int) = coinRepo.getAnswers(coin, uid, date, time)

    fun postAnswer(uid: String, coin: String, comment: String, date: Int, time: Int) = coinRepo.postAnswer(uid, coin, comment, date, time)

    fun deleteAnswer(answer: Answer) = coinRepo.deleteAnswer(answer)
}