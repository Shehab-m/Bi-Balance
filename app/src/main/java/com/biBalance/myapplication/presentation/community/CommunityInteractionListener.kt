package com.biBalance.myapplication.presentation.community

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface CommunityInteractionListener: BaseInteractionListener {
    fun onClickPostLike(postId: Int,isLiked: Boolean)
    fun onClickAddWriting()
    fun onClickBackFromWriting()
    fun onWritingsInputChange(text: String)
    fun onClickSaveWriting()
    fun getUserData()
}