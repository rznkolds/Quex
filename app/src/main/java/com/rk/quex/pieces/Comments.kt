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
import com.rk.quex.adapter.CommentAdapter
import com.rk.quex.databinding.FragmentCommentsBinding
import com.rk.quex.viewmodels.CommentViewModel

class Comments : Fragment() {

    private val viewModel: CommentViewModel by viewModels()
    private lateinit var binding: FragmentCommentsBinding
    private val adapter by lazy { CommentAdapter() }
    private val args: CommentsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCommentsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {

            Glide.with(this).load(it.picture).into(binding.commentCoinPicture)
            binding.commentCoinName.text = it.coin
            binding.commentCoinPrice.text = it.price
        }

        binding.commentRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.commentRecycler.adapter = adapter

        viewModel.comments.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                adapter.setData(it)

            } else {

                binding.commentRecycler.visibility = View.INVISIBLE
            }
        }
    }
}