package com.rk.quex.ui.create

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class CreateViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean>
        get() {
            return _result
        }

    fun register(name: String, text: String, email: String, password: String, picture: Uri) {

        memberRepo.register(name, text, email, password, picture)

        _result = memberRepo.result
    }
}