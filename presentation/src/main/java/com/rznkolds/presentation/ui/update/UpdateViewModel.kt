package com.rznkolds.presentation.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.usecase.answer.UpdateAnswerUseCase
import com.rznkolds.domain.usecase.comment.UpdateCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UpdateUIState())
    val state: StateFlow<UpdateUIState> = _state

    fun updateComment(text: String, comment: CommentUI) {

        updateCommentUseCase(text, comment).onEach { v->

            when (v) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(updated = it?.received)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun updateAnswer(text: String, answer: AnswerUI) {

        updateAnswerUseCase(text, answer).onEach { v ->

            when (v) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(updated = it?.received)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}