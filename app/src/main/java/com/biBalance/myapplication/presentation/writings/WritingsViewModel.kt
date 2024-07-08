package com.biBalance.myapplication.presentation.writings

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Note
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WritingsViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<WritingsUIState, WritingsUIEffect>(WritingsUIState()),
    WritingsInteractionListener {

    init {
        getNotes()
    }

    override fun onClickBack() {
        sendEffect(WritingsUIEffect.OnClickBack)
    }

    private fun getNotes() {
        tryToExecute(
            { repository.getNotes() },
            ::onGetNotes,
            ::onError
        )
    }

    private fun onGetNotes(notes: List<Note>) {
        updateState {
            it.copy(notes = notes, isLoading = false)
        }
    }

    private fun saveNotes() {
        tryToExecute(
            { repository.saveNotes(_state.value.writings) },
            ::onSaveNotes,
            ::onError
        )
    }

    private fun onSaveNotes(unit: Unit) {
        updateState {
            it.copy(writings = "", isLoading = false)
        }
        getNotes()
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(isLoading = false) }
    }

    override fun onWritingsInputChange(text: String) {
        _state.update { it.copy(writings = text) }
    }

    override fun onClickAddWriting() {
        saveNotes()
        _state.update { it.copy(isWritingScreenVisible = true) }
    }

    override fun onClickBackFromWriting() {
        _state.update { it.copy(isWritingScreenVisible = false) }
    }

    override fun onClickSaveWriting() {
        _state.update { it.copy(isWritingScreenVisible = false) }
        tryToExecute(
            { repository.saveNotes(_state.value.writings) },
            { getNotes() },
            ::onError
        )
    }
}