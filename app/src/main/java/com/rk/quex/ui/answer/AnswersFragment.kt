package com.rk.quex.ui.answer

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rk.quex.R
import com.rk.quex.common.endsWith
import com.rk.quex.common.viewBinding
import com.rk.quex.data.model.Answer
import com.rk.quex.databinding.FragmentAnswersBinding

class AnswersFragment : Fragment(R.layout.fragment_answers) {

    private val binding by viewBinding(FragmentAnswersBinding::bind)
    private val args: AnswersFragmentArgs by navArgs()
    private val viewModel: AnswersViewModel by viewModels()
    private val adapter by lazy { AnswersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            adapter.onAnswerClick = {
                findNavController().navigate(AnswersFragmentDirections.actionAnswersToProfile(it))
            }

            adapter.onDeleteAnswerClick = {
                it.comment?.let { _ ->
                    AlertDialog.Builder(requireContext()).apply {
                        setMessage("Yanıtın silinsin mi ?")
                        setPositiveButton(R.string.yes) { _, _ ->
                            deleteAnswer(it)
                        }
                        create()
                        show()
                    }
                }
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

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.answers.observe(viewLifecycleOwner) {
            if ( it != null ) {
                if (it.size <= 8) {
                    LinearLayoutManager(requireContext()).apply {
                        answerRecycler.layoutManager = this
                    }
                } else {
                    LinearLayoutManager(requireContext()).apply {
                        stackFromEnd = true
                        answerRecycler.layoutManager = this
                    }
                }
                answerRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getAnswers(args.uid, args.coin, args.date, args.time)
            }
        }
    }

    private fun sendAnswer(comment: String) = viewModel.postAnswer(args.uid, args.coin, comment, args.date, args.time)

    private fun deleteAnswer(answer: Answer) = viewModel.deleteAnswer(answer)
}