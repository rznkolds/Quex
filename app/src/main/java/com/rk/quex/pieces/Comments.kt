package com.rk.quex.pieces

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rk.quex.R
import com.rk.quex.databinding.FragmentCommentsBinding
import com.rk.quex.databinding.FragmentCreateBinding
import com.rk.quex.databinding.FragmentProfileBinding
import com.rk.quex.viewmodels.CommentViewModel
import com.rk.quex.viewmodels.CreateViewModel

class Comments : Fragment() {

    private lateinit var binding: FragmentCommentsBinding

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

        val viewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        Glide.with(this).load(args.picture).into(binding.commentCoinPicture)

        binding.commentCoinName.text = args.coin
        binding.commentCoinPrice.text = args.price

        viewModel.comments.observe(this.requireActivity()) {

            if ( !it.isNullOrEmpty() ) {

                Log.d("Yorum Listesi 1:", it.toString())

            } else {

                Log.d("Yorum Listesi 2:", "Boş")
            }
        }
    }
}