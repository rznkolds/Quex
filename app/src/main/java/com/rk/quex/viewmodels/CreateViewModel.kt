package com.rk.quex.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class CreateViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    var result = MutableLiveData<Boolean>()

    fun createUser(name: String, text: String, email: String, password: String, picture: Uri) {

        memberRepo.userRegister(name, text, email, password, picture)

        result = memberRepo.result
    }
}