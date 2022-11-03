package com.rk.quex.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.CoinRepo

class CommentsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var coinRepo = CoinRepo()

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
    }

    fun getComments(coin: String) {
        coinRepo.getComments(coin)
        _comments = coinRepo.comments
    }

    fun postComment(coin: String, comment: String) {
        coinRepo.postComment(coin, comment)
        _result = coinRepo.result
    }

    fun deleteComment(comment: Comment) {
        coinRepo.deleteComment (comment)
        _result = coinRepo.result
    }
}