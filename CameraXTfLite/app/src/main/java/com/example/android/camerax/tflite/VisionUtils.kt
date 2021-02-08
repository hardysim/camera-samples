package com.example.android.camerax.tflite

import android.content.res.Configuration
import android.graphics.RectF
import com.example.android.camerax.tflite.graphic.GraphicOverlay

fun getBarcodeReticleBox(overlay: GraphicOverlay): RectF {
    val overlayWidth = overlay.width.toFloat()
    val overlayHeight = overlay.height.toFloat()

    val boxWidth: Float
    val boxHeight: Float

    if (BARCODE_RETICLE_IS_SQUARE) {
        if (overlay.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            boxHeight = overlayHeight * RETICLE_SIZE / 100
            boxWidth = boxHeight

        } else {
            boxWidth = overlayWidth * RETICLE_SIZE / 100
            boxHeight = boxWidth
        }

    } else {
        boxWidth = overlayWidth * BARCODE_RETICLE_WIDTH / 100
        boxHeight = overlayHeight * BARCODE_RETICLE_HEIGHT / 100
    }

    val cx = overlayWidth / 2
    val cy = overlayHeight / 2
    return RectF(cx - boxWidth / 2, cy - boxHeight / 2, cx + boxWidth / 2, cy + boxHeight / 2)
}

fun getPercentageReticleBox(overlay: GraphicOverlay, heightPercent: Int, widthPercent: Int): RectF {
    val overlayWidth = overlay.width.toFloat()
    val overlayHeight = overlay.height.toFloat()

    val boxWidth = overlayWidth * widthPercent / 100
    val boxHeight = overlayHeight * heightPercent / 100

    val cx = overlayWidth / 2
    val cy = overlayHeight / 2
    return RectF(cx - boxWidth / 2, cy - boxHeight / 2, cx + boxWidth / 2, cy + boxHeight / 2)
}
