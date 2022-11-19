package com.rk.quex.ui.update

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rk.quex.R
import com.rk.quex.common.viewBinding
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.databinding.FragmentUpdateBinding

class UpdateFragment : BottomSheetDialogFragment(R.layout.fragment_update) {

    private val binding by viewBinding(FragmentUpdateBinding::bind)
    private val args: UpdateFragmentArgs by navArgs()
    private val viewModel: UpdateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            updateButton.setOnClickListener {
                if (args.model == "comment") {
                    args.comment?.let {
                        val text = updateEditText.text.toString() + " -YTD."
                        updateComment(text, it)
                        this@UpdateFragment.dismiss()
                    }
                } else {
                    args.answer?.let {
                        val text = updateEditText.text.toString() + " -YTD."
                        updateAnswer(text, it)
                        this@UpdateFragment.dismiss()
                    }
                }
            }
        }
    }

    private fun updateComment(text: String, comment: Comment) = viewModel.putComment(text, comment)

    private fun updateAnswer(text: String, answer: Answer) = viewModel.putAnswer(text, answer)
}