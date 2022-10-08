package com.rk.quex.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MembershipRepo

class CreateViewModel : ViewModel() {

    private var createRepo = MembershipRepo()
    var result = MutableLiveData<Boolean>()

    fun createUser(name: String, text: String, email: String, password: String, picture: Uri) {

        createRepo.createUser(name, text, email, password, picture)

        result = createRepo.result
    }
}