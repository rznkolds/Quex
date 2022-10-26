package com.rk.quex.ui.comment

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
import com.rk.quex.databinding.FragmentCommentsBinding
import com.rk.quex.databinding.FragmentCreateBinding

class Comments : Fragment(R.layout.fragment_comments) {

    private val binding by viewBinding(FragmentCommentsBinding::bind)
    private val args: CommentsArgs by navArgs()
    private val viewModel: CommentViewModel by viewModels()
    private val adapter by lazy { CommentAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this)
                .load(it.picture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.sampleCoinPicture )

            binding.sampleCoinName.text = it.coin
            binding.sampleCoinPrice.text = it.price
        }

        viewModel.comments.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                 LinearLayoutManager(requireContext()).apply {

                    reverseLayout = true
                    stackFromEnd = true
                    binding.commentRecycler.layoutManager = this
                }

                binding.commentRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        binding.commentEditText.setOnEditorActionListener { v, actionId, _ ->

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

        viewModel.postComment(args.coin, comment)

        viewModel.result.observe(viewLifecycleOwner) {

            if (it) {

                viewModel.getComments(args.coin)

            } else {

                toast("Yorumunuz eklenemedi")
            }
        }
    }

    private fun toast( text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}