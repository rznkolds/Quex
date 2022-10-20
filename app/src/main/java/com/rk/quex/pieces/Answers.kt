package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rk.quex.adapter.AnswerAdapter
import com.rk.quex.adapter.CommentAdapter
import com.rk.quex.databinding.FragmentAnswersBinding
import com.rk.quex.viewmodels.AnswerViewModel

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

                val linear = LinearLayoutManager(requireContext()).apply {

                    stackFromEnd = true
                }

                binding.answerRecycler.layoutManager = linear
                binding.answerRecycler.adapter = adapter
                adapter.setData(it)

            } else {

                binding.answerRecycler.visibility = View.INVISIBLE
            }
        }
    }
}