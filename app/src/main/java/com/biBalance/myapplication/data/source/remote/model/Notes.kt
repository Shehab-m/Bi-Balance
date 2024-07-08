package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("body") val notes: String,
    @SerializedName("created_at") val creationDate: String,
)

//data class Notes(
//    @SerializedName("data") val notes: List<Note>
//)