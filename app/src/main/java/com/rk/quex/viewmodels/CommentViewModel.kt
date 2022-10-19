package com.rk.quex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.MemberRepo

class CommentViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _comments = MutableLiveData<ArrayList<Comment>>()

    var result = MutableLiveData<Boolean>()

    val comments: LiveData<ArrayList<Comment>>
        get() {

            return _comments
        }

    init {

        savedStateHandle.get<String>("coin")?.let {

            getCommentList(it)
        }
    }

    fun getCommentList(coin: String) {

        memberRepo.getComments(coin)

        _comments = memberRepo.comments
    }

    fun sendComment(coin: String, comment: String, date: Int, time: Int) {

        memberRepo.postComment(coin, comment, date, time)

        result = memberRepo.result
    }
}