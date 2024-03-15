package com.biBalance.myapplication.presentation.community

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface CommunityInteractionListener: BaseInteractionListener {
    fun onClickLike(postId: Int)
}