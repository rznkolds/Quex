package com.rk.quex.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.MemberRepo

class ProfileViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var memberRepo = MemberRepo()
    private var coinRepo = CoinRepo()

    private var _informations = MutableLiveData<User>()
    val informations: LiveData<User>
        get() = _informations

    private var _favorites = MutableLiveData<ArrayList<Favorite>>()
    val favorites: LiveData<ArrayList<Favorite>>
        get() = _favorites

    init {
        savedStateHandle.get<String>("uid")?.let {
            getProfile(it)
            getFavorites(it)
        }

        _informations = memberRepo.user
        _favorites = coinRepo.favorites
    }

    private fun getProfile(uid: String) = memberRepo.getProfile(uid)

    private fun getFavorites(uid: String) = coinRepo.getFavorites(uid)
}