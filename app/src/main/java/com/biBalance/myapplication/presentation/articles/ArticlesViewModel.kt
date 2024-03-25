package com.biBalance.myapplication.presentation.articles

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.data.source.remote.model.UserPost
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ArticlesUIState, ArticlesUIEffect>(ArticlesUIState()),
    ArticlesInteractionListener {

    init {
        getArticles()
        getUserData()
    }
    override fun onClickBack() {
        sendEffect(ArticlesUIEffect.OnClickBack)
    }

    private fun getArticles(){
        tryToExecute(
            {repository.getArticlePosts()},
            ::onGetArticlesSuccess,
            ::onError
        )
    }

    private fun onGetArticlesSuccess(articles:List<UserPost>?) {
        updateState { it.copy(posts = articles ?: emptyList(), isLoadingLevels = false) }
    }

    private fun getUserData(){
        tryToExecute(
            {repository.getUserData()},
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    private fun onGetUserDataSuccess(userData:UserData) {
        updateState {
            it.copy(userName = userData.username, totalScore = userData.totalScore ,isLoadingUserData = false)
        }
    }
    private fun onError(error: Exception) {
        _state.update { it.copy(isLoadingLevels = false, isLoadingUserData = false, isError = true, error = error,) }
    }

}