package com.rk.quex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import com.rk.quex.data.repository.MemberRepo

class ProfileViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _informations = MutableLiveData<User>()

    private var _favorites = MutableLiveData<ArrayList<Favorite>>()

    val informations : LiveData<User>
        get() = _informations

    val favorites : LiveData<ArrayList<Favorite>>
        get() = _favorites

    init {

        savedStateHandle.get<String>("uid")?.let {

            getProfileInfo(it)

            getFavoriteList(it)
        }
    }

    private fun getProfileInfo(uid: String) {

        _informations = memberRepo.getUser(uid)
    }

    private fun getFavoriteList(uid: String) {

        _favorites = memberRepo.getFavoriteCoins(uid)
    }
}