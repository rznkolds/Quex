package com.rk.quex.ui.profile

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
    val informations : LiveData<User>
        get() {
            return _informations
        }

    private var _favorites = MutableLiveData<ArrayList<Favorite>>()
    val favorites : LiveData<ArrayList<Favorite>>
        get() {
            return _favorites
        }

    init {

        savedStateHandle.get<String>("uid")?.let {

            getProfile(it)

            getFavorites(it)
        }
    }

    private fun getProfile(uid: String) {

        memberRepo.getProfile(uid)

        _informations = memberRepo.user
    }

    private fun getFavorites(uid: String) {

        memberRepo.getFavorites(uid)

        _favorites = memberRepo.favorites
    }
}