package com.biBalance.myapplication.presentation.writings

import com.biBalance.myapplication.data.source.remote.model.Note

data class WritingsUIState(
    val isLoading: Boolean = false,
    val writings: String = "",
    val isWritingScreenVisible: Boolean = false,
    val notes: List<Note> = emptyList(),
)