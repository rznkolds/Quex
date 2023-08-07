package com.rznkolds.presentation.ui.comment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.usecase.comment.AddCommentUseCase
import com.rznkolds.domain.usecase.comment.DeleteCommentUseCase
import com.rznkolds.domain.usecase.comment.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CommentUIState())
    val state: StateFlow<CommentUIState> = _state

    init {
        savedStateHandle.get<String>("coin")?.let {
            getComments(it)
        }
    }

    fun getComments(coin: String) {

        getCommentsUseCase(coin).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(comments = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun postComment(coin: String, comment: String) {

        addCommentUseCase(coin, comment).onEach { v ->

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

    fun deleteComment(comment: CommentUI) {

        deleteCommentUseCase(comment).onEach { v ->

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