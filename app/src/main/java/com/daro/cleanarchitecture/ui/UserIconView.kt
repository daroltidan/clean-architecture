package com.daro.cleanarchitecture.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.daro.cleanarchitecture.R

class UserIconView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var ovalPaint: Paint? = null
    private var textPaint: Paint? = null
    private var userName: String = "a"

    fun setupIconView(userName: String) {
        this.userName = userName
        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setupBackgroundPaint()
        setupTextPain()
        ovalPaint?.let { canvas.drawOval(0F, 0F, width.toFloat(), height.toFloat(), it) }
        textPaint?.let {
            val xPos = (width / 2).toFloat()
            val yPos = (height / 2 - (it.descent() + it.ascent()) / 2)
            canvas.drawText(userName, xPos, yPos, it)
        }
    }

    private fun setupTextPain() {
        textPaint = Paint().apply {
            color = Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = context.resources.getDimensionPixelSize(R.dimen.text_size_icon).toFloat()
        }
    }

    private fun setupBackgroundPaint() {
        ovalPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.colorPrimary)
        }
    }
}