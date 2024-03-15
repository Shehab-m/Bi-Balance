package com.biBalance.myapplication.presentation.chat

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface ChatInteractionListener: BaseInteractionListener {
    fun onClickLike(postId: Int)
}