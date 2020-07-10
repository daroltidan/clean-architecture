package com.daro.cleanarchitecture.posts.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostViewModel(val id: Int, val title: String, val body: String, val userId: Int) :
    Parcelable