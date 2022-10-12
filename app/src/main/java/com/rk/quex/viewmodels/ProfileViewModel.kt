package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import com.rk.quex.data.repository.MemberRepo

class ProfileViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    fun user(uid: String): MutableLiveData<User> {

        return memberRepo.profile(uid)
    }

    fun coins(uid: String): MutableLiveData<ArrayList<Favorite>> {

        return memberRepo.favorites(uid)
    }
}