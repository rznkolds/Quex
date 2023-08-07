package com.rznkolds.presentation.ui.update

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rznkolds.domain.common.collect
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.showToast
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : BottomSheetDialogFragment(R.layout.fragment_update) {

    private val binding by viewBinding(FragmentUpdateBinding::bind)
    private val viewModel: UpdateViewModel by viewModels()
    private val args: UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            updateButton.setOnClickListener {

                if (args.model == "comment") {

                    args.comment?.let {

                        updateComment(
                            updateEditText.text.toString() + " -YTD.",
                            args.comment!!
                        )
                    }

                } else {

                    args.answer?.let {

                        updateAnswer(
                            updateEditText.text.toString() + " -YTD.",
                            args.answer!!
                        )
                    }
                }
            }
        }

        initObservers()
    }

    private fun initObservers() {

        viewModel.state.collect(lifecycleScope) {

            if (it.updated != null && it.updated != false) {

                setFragmentResult("sheet", Bundle())
                dismiss()
            } else {

                requireContext().showToast("Yorum düzeltilemedi")
            }
        }
    }

    private fun updateComment(text: String, comment: CommentUI) = viewModel.updateComment(text, comment)

    private fun updateAnswer(text: String, answer: AnswerUI) = viewModel.updateAnswer(text, answer)
}