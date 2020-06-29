package com.daro.cleanarchitecture.ui

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daro.cleanarchitecture.R
import com.google.android.material.snackbar.Snackbar


fun View.setupSnackbar(@StringRes message: Int) {
    Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(ContextCompat.getColor(context, R.color.colorPrimary))
        .show()
}

fun SwipeRefreshLayout.setUpRefreshLayout(listener: SwipeRefreshLayout.OnRefreshListener) {
    this.apply {
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorPrimary))
        setOnRefreshListener(listener)
    }
}

@BindingAdapter("app:userId")
fun setupUserIcon(view: UserIconView, userId: Int) {
    val alphabetNumber = listOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )

    val userName = when (userId) {
        in 0..alphabetNumber.size -> alphabetNumber[userId]
        else -> alphabetNumber.random()
    }

    view.setupIconView(userName)
}