package com.buzynski.pokedex.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.buzynski.pokedex.R

private const val PROGRESSBAR_HEIGHT_DEFAULT = 120
private const val MAXIMUM_VALUE_DEFAULT = 100

private const val MAXIMUM_REACHED_COLOR_DEFAULT = Color.BLACK
private const val UNFILLED_SECTION_COLOR_DEFAULT = Color.YELLOW

class ProgressBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progressPaint: Paint? = null
    private var progressBarHeight: Float = 0f

    private var maximumReachedColor = 0
    private var unfilledSectionColor = 0

    private var max = 0
    private var progress = 0

    // ---

    init {
        progressPaint = Paint()
        progressPaint?.style = Paint.Style.FILL_AND_STROKE

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ProgressBar, 0, 0)
        try {
            setBarHeight(typedArray.getDimensionPixelOffset(R.styleable.ProgressBar_progressBarHeight, PROGRESSBAR_HEIGHT_DEFAULT))
            setBarMaximum(typedArray.getDimensionPixelOffset(R.styleable.ProgressBar_progressMaximum, MAXIMUM_VALUE_DEFAULT))

            setMaximumReachedColor(typedArray.getColor(R.styleable.ProgressBar_maximumReachedColor, MAXIMUM_REACHED_COLOR_DEFAULT))
            setUnfilledSectionColor(typedArray.getColor(R.styleable.ProgressBar_unfilledSectionColor, UNFILLED_SECTION_COLOR_DEFAULT))
        } catch (e: Exception) {
            Log.i("ProgressBar", "Error: ${e.message}")
        } finally {
            typedArray.recycle()
        }
    }

    // ---

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putInt("progress", progress)
        bundle.putInt("max", max)

        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var currentState = state
        if (currentState is Bundle) {
            setProgress(currentState.getInt("progress"))
            setBarMaximum(currentState.getInt("max"))

            currentState = currentState.getParcelable("superState")
        }

        super.onRestoreInstanceState(currentState)
    }

    // ---

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        progressPaint?.let { paint ->
            val halfHeight = (height / 2).toFloat()
            val progressEndX = (width * progress / this.max.toFloat())

            paint.color = maximumReachedColor
            paint.strokeWidth = progressBarHeight
            canvas?.drawLine(0f, halfHeight, progressEndX, halfHeight, paint)

            paint.color = unfilledSectionColor
            canvas?.drawLine(progressEndX, halfHeight, width.toFloat(), halfHeight, paint)
        }
    }

    // ---

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val height: Int
        height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> specHeight
            MeasureSpec.UNSPECIFIED -> specHeight
            else -> specHeight
        }

        setMeasuredDimension(width, height)
    }

    // ---

    fun setProgress(progress: Int) {
        this.progress = progress
        postInvalidate()
    }

    // --- SETTERS

    private fun setBarHeight(dimensionPixelOffset: Int) {
        this.progressBarHeight = dimensionPixelOffset.toFloat()
        postInvalidate()
    }

    private fun setUnfilledSectionColor(color: Int) {
        this.unfilledSectionColor = color
        postInvalidate()
    }

    private fun setMaximumReachedColor(color: Int) {
        this.maximumReachedColor = color
        postInvalidate()
    }

    fun setBarMaximum(dimensionPixelOffset: Int) {
        this.max = dimensionPixelOffset
        postInvalidate()
    }
}