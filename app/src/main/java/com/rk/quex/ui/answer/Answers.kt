package com.rk.quex.ui.answer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.R
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentAnswersBinding
import com.rk.quex.databinding.FragmentCommentsBinding

class Answers : Fragment(R.layout.fragment_answers) {

    private val binding by viewBinding(FragmentAnswersBinding::bind)
    private val args: AnswersArgs by navArgs()
    private val viewModel: AnswerViewModel by viewModels()
    private val adapter by lazy { AnswerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this)
                .load(it.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.sampleCommentPicture)

            binding.sampleCommentName.text = it.name
            binding.sampleCommentText.text = it.comment
        }

        viewModel.answers.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                if (it.size <= 8) {

                    val linear = LinearLayoutManager(requireContext())

                    binding.answerRecycler.layoutManager = linear

                } else {

                    val linear = LinearLayoutManager(requireContext()).apply {

                        stackFromEnd = true
                    }

                    binding.answerRecycler.layoutManager = linear
                }

                binding.answerRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        binding.answerEditText.setOnEditorActionListener { v, actionId, event ->

            when (actionId) {

                EditorInfo.IME_ACTION_SEND -> {

                    sendAnswer(v.text.toString())

                    binding.answerEditText.text.clear()

                    true
                }

                else -> false
            }
        }
    }

    private fun sendAnswer(comment: String) {

        viewModel.postAnswer(args.uid, args.coin, comment, args.date, args.time)

        viewModel.result.observe(viewLifecycleOwner) {

            if (it) {

                viewModel.getAnswers(args.coin, args.uid, args.date, args.time)

            } else {

                toast("Yanıtın eklenemedi")
            }
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}