package com.rk.quex.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val coinRepo: CoinRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _comments = MutableLiveData<ArrayList<Comment>>()
    val comments: LiveData<ArrayList<Comment>>
        get() = _comments

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        savedStateHandle.get<String>("coin")?.let {
            getComments(it)
        }

        _comments = coinRepo.comments
        _result = coinRepo.result
    }

    fun getComments(coin: String) = coinRepo.getComments(coin)

    fun postComment(coin: String, comment: String) = coinRepo.postComment(coin, comment)

    fun deleteComment(comment: Comment) = coinRepo.deleteComment (comment)
}