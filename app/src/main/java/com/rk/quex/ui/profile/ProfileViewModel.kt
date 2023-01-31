package com.rk.quex.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel@Inject constructor(
    private val userRepo: UserRepo,
    private val coinRepo: CoinRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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

        _informations = userRepo.user
        _favorites = coinRepo.favorites
    }

    fun outProfile() = userRepo.outProfile()

    private fun getProfile(uid: String) = userRepo.getProfile(uid)

    private fun getFavorites(uid: String) = coinRepo.getFavorites(uid)
}