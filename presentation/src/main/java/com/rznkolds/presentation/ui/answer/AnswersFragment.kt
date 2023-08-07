package com.rznkolds.presentation.ui.answer

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rznkolds.domain.common.collect
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.endsWith
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentAnswersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnswersFragment : Fragment(R.layout.fragment_answers) {

    private val binding by viewBinding(FragmentAnswersBinding::bind)
    private val viewModel: AnswersViewModel by viewModels()
    private val args: AnswersFragmentArgs by navArgs()
    private val adapter by lazy { AnswersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            adapter.onShowProfileClick = {

                findNavController().navigate(AnswersFragmentDirections.actionAnswersToProfile(it))
            }

            adapter.onShowMenuClick = { view: View, answer: AnswerUI ->

                showMenu(view,answer)
            }

            answerEditText.setOnEditorActionListener { v, actionId, _ ->

                when (actionId) {

                    EditorInfo.IME_ACTION_SEND -> {

                        if (answerEditText.text.toString().isNotBlank()) {

                            if (answerEditText.endsWith()) {

                                sendAnswer((v.text.toString() + "-YTD."))
                                answerEditText.text.clear()
                            }
                        }

                        true
                    }

                    else -> false
                }
            }
        }

        setFragmentResultListener("sheet") { _, _ ->

            viewModel.getAnswers(args.uid, args.coin, args.date, args.time)
        }

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) {

            if (it.answers != null) {

                answerRecycler.adapter = adapter
                adapter.setData(it.answers)
            }

            if (it.added != null) {

                viewModel.getAnswers(args.uid, args.coin, args.date, args.time)
            }
        }
    }

    private fun sendAnswer(comment: String) = viewModel.postAnswer(args.uid, args.coin, comment, args.date, args.time)

    private fun deleteAnswer(answer: AnswerUI) = viewModel.deleteAnswer(answer)

    private fun showMenu(view: View, answer: AnswerUI) {

        val menu = PopupMenu(this.requireContext(),view).apply {
            this.inflate(R.menu.external_menu)
            this.show()
        }

        menu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update_item -> {
                    findNavController().navigate(
                        AnswersFragmentDirections.actionAnswersToUpdateFragment (
                            "answer" ,
                            null,
                            answer
                        )
                    )
                }
                R.id.delete_item -> {
                    deleteAnswer(answer)
                }
            }
            false
        }
    }
}