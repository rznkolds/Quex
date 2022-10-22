package com.rk.quex.pieces

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
import com.rk.quex.adapter.CommentAdapter
import com.rk.quex.databinding.FragmentCommentsBinding
import com.rk.quex.viewmodels.CommentViewModel

class Comments : Fragment() {

    private val viewModel: CommentViewModel by viewModels()
    private lateinit var binding: FragmentCommentsBinding
    private val adapter by lazy { CommentAdapter() }
    private val args: CommentsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentCommentsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this).load(it.picture).into(binding.sampleCoinPicture )
            binding.sampleCoinName.text = it.coin
            binding.sampleCoinPrice.text = it.price
        }

        viewModel.comments.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                val linear = LinearLayoutManager(requireContext()).apply {

                    reverseLayout = true
                    stackFromEnd = true
                }

                binding.commentRecycler.layoutManager = linear
                binding.commentRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        binding.commentEditText.setOnEditorActionListener { v, actionId, event ->

            when (actionId) {

                EditorInfo.IME_ACTION_SEND -> {

                    sendComment(v.text.toString())

                    binding.commentEditText.text.clear()

                    true
                }

                else -> false
            }
        }
    }

    private fun sendComment(comment: String) {

        viewModel.sendComment(args.coin, comment)

        viewModel.result.observe(viewLifecycleOwner) {

            if (it) {

                viewModel.getCommentList(args.coin)

            } else {

                toast("Yorumunuz eklenemedi")
            }
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}