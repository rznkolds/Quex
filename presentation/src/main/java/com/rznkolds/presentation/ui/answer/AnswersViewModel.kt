package com.rznkolds.presentation.ui.answer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.usecase.answer.AddAnswerUseCase
import com.rznkolds.domain.usecase.answer.DeleteAnswerUseCase
import com.rznkolds.domain.usecase.answer.GetAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val addAnswerUseCase: AddAnswerUseCase,
    private val getAnswersUseCase: GetAnswersUseCase,
    private val deleteAnswerUseCase: DeleteAnswerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AnswerUIState())
    val state: StateFlow<AnswerUIState> = _state

    init {
        val uid = savedStateHandle.get<String>("uid").toString()
        val coin = savedStateHandle.get<String>("coin").toString()
        val date = savedStateHandle.get<Int>("date")!!.toInt()
        val time = savedStateHandle.get<Int>("time")!!.toInt()

        getAnswers(uid, coin, date, time)
    }

    fun getAnswers(uid: String, coin: String, date: Int, time: Int) {

        getAnswersUseCase(coin, uid, date, time).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(answers = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun postAnswer(uid: String, coin: String, comment: String, date: Int, time: Int) {

        addAnswerUseCase(uid, coin, comment, date, time).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(added = it?.received)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun deleteAnswer(answer: AnswerUI) {

        deleteAnswerUseCase(answer).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(deleted = it?.received)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}