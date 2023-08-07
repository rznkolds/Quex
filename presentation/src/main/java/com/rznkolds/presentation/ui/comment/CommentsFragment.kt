package com.rznkolds.presentation.ui.comment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rznkolds.domain.common.collect
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.endsWith
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.common.showToast
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentCommentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment(R.layout.fragment_comments) {

    private val binding by viewBinding(FragmentCommentsBinding::bind)
    private val viewModel: CommentsViewModel by viewModels()
    private val args: CommentsFragmentArgs by navArgs()
    private val adapter by lazy { CommentsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            args.let {
                sampleCoinPicture.load(it.picture)
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
                        CommentsFragmentDirections.actionCommentsToProfile(it.uid!!)
                    )
                }
            }

            adapter.onShowMenuClick = { view: View, comment: CommentUI ->

                showMenu(view,comment)
            }

            commentEditText.setOnEditorActionListener { v, actionId, _ ->

                when (actionId) {

                    EditorInfo.IME_ACTION_SEND -> {

                        if (commentEditText.text.toString().isNotBlank()) {

                            if (commentEditText.endsWith()) {

                                sendComment((v.text.toString() + " -YTD."))
                                commentEditText.text.clear()
                            }
                        }

                        true
                    }

                    else -> false
                }
            }
        }

        setFragmentResultListener("sheet") { _, _ -> viewModel.getComments(args.coin) }

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) {

            if (it.comments != null) {

                commentRecycler.adapter = adapter
                adapter.setData(it.comments)
            }

            if(it.added != null){

                if (it.added) {
                    viewModel.getComments(args.coin)
                } else {
                    requireContext().showToast("Yorumunuz eklenemedi")
                }
            }
        }
    }

    private fun sendComment(comment: String) = viewModel.postComment(args.coin, comment)

    private fun deleteComment(comment: CommentUI) = viewModel.deleteComment(comment)

    private fun showMenu(view: View, comment: CommentUI) {

        val menu = PopupMenu(this.requireContext(),view).apply {
            this.inflate(R.menu.external_menu)
            this.show()
        }

        menu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update_item -> {
                    findNavController().navigate(
                        CommentsFragmentDirections.actionCommentsToUpdateFragment (
                            "comment" , comment,null
                        )
                    )
                }
                R.id.delete_item -> {
                    deleteComment(comment)
                }
            }
            false
        }
    }
}
