package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("Notes") val notes: String,
    @SerializedName("created_at") val creationDate: String,
)

data class Notes(
    @SerializedName("notes") val notes: List<Note>
)