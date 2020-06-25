package com.daro.data.remote.entities

import com.google.gson.annotations.SerializedName

data class PostDTO(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("userId") val userId: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("body") val body: String
)