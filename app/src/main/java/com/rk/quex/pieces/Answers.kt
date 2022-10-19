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
import com.rk.quex.databinding.FragmentAnswersBinding
import com.rk.quex.viewmodels.AnswerViewModel

class Answers : Fragment() {

    private val viewModel: AnswerViewModel by viewModels()
    private lateinit var binding: FragmentAnswersBinding
    private val args: AnswersArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnswersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this).load(it.url).into(binding.commentPicture)
            binding.commentName.text = it.name
            binding.commentText.text = it.comment
        }

        viewModel.answers.observe(viewLifecycleOwner) {

            /*if (!it.isNullOrEmpty()) {

                val linear = LinearLayoutManager(requireContext()).apply {

                    reverseLayout = true
                    stackFromEnd = true
                }

                binding.commentRecycler.layoutManager = linear
                binding.commentRecycler.adapter = adapter
                adapter.setData(it)

            } else {

                binding.commentRecycler.visibility = View.INVISIBLE
            }*/
        }
    }
}