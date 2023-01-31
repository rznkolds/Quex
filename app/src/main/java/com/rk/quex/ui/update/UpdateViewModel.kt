package com.rk.quex.ui.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.CoinRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val coinRepo: CoinRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        _result = coinRepo.result
    }

    fun putComment(text: String, comment: Comment) = coinRepo.putComment(text, comment)

    fun putAnswer(text: String, answer: Answer) = coinRepo.putAnswer(text, answer)
}