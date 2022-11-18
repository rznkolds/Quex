package com.rk.quex.ui.comment

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
import com.rk.quex.common.setPicture
import com.rk.quex.common.showToast
import com.rk.quex.common.viewBinding
import com.rk.quex.data.model.Comment
import com.rk.quex.databinding.FragmentCommentsBinding

class CommentsFragment : Fragment(R.layout.fragment_comments) {

    private val binding by viewBinding(FragmentCommentsBinding::bind)
    private val args: CommentsFragmentArgs by navArgs()
    private val viewModel: CommentsViewModel by viewModels()
    private val adapter by lazy { CommentsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            args.let {
                sampleCoinPicture.setPicture(it.picture)
                sampleCoinName.text = it.coin
                sampleCoinPrice.text = it.price
            }

            adapter.onShowAnswersClick = {
                findNavController().navigate(
                    CommentsFragmentDirections.actionCommentsToAnswers(
                        it.uid!!,
                        it.coin!!,
                        it.date!!,
                        it.time!!
                    )
                )
            }

            adapter.onShowProfileClick = {
                if (!it.uid.isNullOrEmpty()) {
                    findNavController().navigate(
                        CommentsFragmentDirections.actionCommentsToProfile(
                            it.uid
                        )
                    )
                }
            }

            adapter.onDeleteCommentClick = {
                it.comment?.let { _ ->
                    AlertDialog.Builder(requireContext()).apply {
                        setMessage("Yorumun silinsin mi ?")
                        setPositiveButton(R.string.yes) { dialog, id ->
                            deleteComment(it)
                        }
                        create()
                        show()
                    }
                }
            }

            commentEditText.setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEND -> {
                        if (commentEditText.text.toString().isNotBlank()) {
                            if (commentEditText.endsWith()) {
                                sendComment((v.text.toString() + "-YTD."))
                                commentEditText.text.clear()
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

        viewModel.comments.observe(viewLifecycleOwner) {
            if ( it != null ) {
                LinearLayoutManager(requireContext()).apply {
                    commentRecycler.layoutManager = this
                }
                commentRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getComments(args.coin)
            } else {
                requireContext().showToast("Yorumunuz eklenemedi")
            }
        }
    }

    private fun sendComment(comment: String) = viewModel.postComment(args.coin, comment)

    private fun deleteComment(comment: Comment) = viewModel.deleteComment(comment)
}
