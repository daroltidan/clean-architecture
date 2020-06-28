package com.daro.cleanarchitecture.ui

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
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
        setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.colorPrimary))
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
        setOnRefreshListener(listener)
    }
}