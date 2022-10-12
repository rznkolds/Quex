package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.User
import com.rk.quex.data.repository.MemberRepo

class ProfileViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    fun user(uid: String): MutableLiveData<User> {

        return memberRepo.info(uid)
    }
}