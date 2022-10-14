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

    val comments : LiveData<ArrayList<Comment>>
        get() {

            return _comments
        }

    init {

        savedStateHandle.get<String>("coin")?.let {

            getCommentList(it)
        }
    }

    private fun getCommentList(coin: String) {

        _comments = memberRepo.getCommentList(coin)
    }
}