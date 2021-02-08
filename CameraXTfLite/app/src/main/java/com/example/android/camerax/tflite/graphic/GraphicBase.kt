package com.example.android.camerax.tflite.graphic

import android.graphics.*
import android.graphics.Paint.Style
import androidx.core.content.ContextCompat
import com.android.example.camerax.tflite.R

internal abstract class GraphicBase(
    overlay: GraphicOverlay,
    val boxRect: RectF
) : GraphicOverlay.Graphic(overlay) {

    private val boxPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.reticle_stroke)
        style = Style.STROKE
        strokeWidth = context.resources
            .getDimensionPixelOffset(R.dimen.reticle_stroke_width)
            .toFloat()
    }

    private val scrimPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.reticle_background)
    }

    private val eraserPaint: Paint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    val boxCornerRadius: Float = context.resources
        .getDimensionPixelOffset(R.dimen.reticle_corner_radius)
        .toFloat()

    val pathPaint: Paint = Paint().apply {
        color = Color.WHITE
        style = Style.STROKE
        strokeWidth = boxPaint.strokeWidth
        pathEffect = CornerPathEffect(boxCornerRadius)
    }

    override fun draw(canvas: Canvas) {
        // Draws the dark background scrim and leaves the box area clear.
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), scrimPaint)
        // As the stroke is always centered, so erase twice with FILL and STROKE respectively to clear
        // all area that the box rect would occupy.
        eraserPaint.style = Style.FILL
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        eraserPaint.style = Style.STROKE
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        // Draws the box.
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, boxPaint)
    }
}
