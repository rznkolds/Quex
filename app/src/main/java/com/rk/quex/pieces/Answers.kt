package com.rk.quex.pieces

import android.os.Bundle
import android.text.SpannedString
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
import com.rk.quex.adapter.AnswerAdapter
import com.rk.quex.databinding.FragmentAnswersBinding
import com.rk.quex.viewmodels.AnswerViewModel
import java.util.*

class Answers : Fragment() {

    private val viewModel: AnswerViewModel by viewModels()
    private lateinit var binding: FragmentAnswersBinding
    private val args: AnswersArgs by navArgs()
    private val adapter by lazy { AnswerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnswersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this).load(it.url).into(binding.sampleCommentPicture)
            binding.sampleCommentName.text = it.name
            binding.sampleCommentText.text = it.comment
        }

        viewModel.answers.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                if (it.size <= 8) {

                    binding.answerRecycler.layoutManager = LinearLayoutManager(requireContext())

                } else {

                    binding.answerRecycler.layoutManager = LinearLayoutManager(requireContext()).apply {

                        stackFromEnd = true
                    }
                }

                binding.answerRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        binding.answerEditText.setOnEditorActionListener{ v, actionId, event ->

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

        viewModel.sendAnswer(args.uid, args.coin, comment, args.date, args.time)

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