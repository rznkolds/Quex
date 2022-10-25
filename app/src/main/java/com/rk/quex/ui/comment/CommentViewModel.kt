package com.rk.quex.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.MemberRepo

class CommentViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _comments = MutableLiveData<ArrayList<Comment>>()
    val comments: LiveData<ArrayList<Comment>>
        get() {
            return _comments
        }

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() {
            return _result
        }

    init {

        savedStateHandle.get<String>("coin")?.let {

            getComments(it)
        }
    }

    fun getComments(coin: String) {

        memberRepo.getComments(coin)

        _comments = memberRepo.comments
    }

    fun postComment(coin: String, comment: String) {

        memberRepo.postComment(coin, comment)

        _result = memberRepo.result
    }
}