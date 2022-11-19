package com.rk.quex.ui.update

import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.repository.CoinRepo

class UpdateViewModel : ViewModel() {

    private var coinRepo = CoinRepo()

    fun putComment(text: String, comment: Comment) {
        coinRepo.putComment(text, comment)
    }

    fun putAnswer(text: String, answer: Answer){
        coinRepo.putAnswer(text, answer)
    }
}