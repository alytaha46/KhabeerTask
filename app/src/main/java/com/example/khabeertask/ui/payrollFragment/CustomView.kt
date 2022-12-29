package com.example.khabeertask.ui.payrollFragment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.Color
import androidx.core.content.withStyledAttributes
import com.example.khabeertask.R


class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var claimingSalary: Double = 0.0
    var claimingSalaryPercent: Double = 0.0
    var claimingSalaryEndAngle: Double = 0.0
    var deduction: Double = 0.0
    var deductionEndAngle: Double = 0.0
    var deductionPercent: Double = 0.0
    var total: Double = 1.0
    private var color1 = 0
    private var color2 = 0
    private var widthSize = 0
    private var heightSize = 0
    init {
        context.withStyledAttributes(attrs, R.styleable.CustomView) {
            color1 = getColor(R.styleable.CustomView_firstColor, 0)
            color2 = getColor(R.styleable.CustomView_secondColor, 0)
        }
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 45.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = color1
        canvas?.drawArc(
            0f,
            0f,
            heightSize.toFloat(),
            heightSize.toFloat(),
            270f,
            claimingSalaryEndAngle.toFloat(),
            true,
            paint
        )

        paint.color = color2
        canvas?.drawArc(
            0f,
            0f,
            heightSize.toFloat(),
            heightSize.toFloat(),
            claimingSalaryEndAngle.toFloat() + 270f,
            deductionEndAngle.toFloat(),
            true,
            paint
        )
        paint.color = Color.White.hashCode()
        canvas?.drawText(
            "" + (Math.round((claimingSalaryPercent * 100) * 10.0) / 10.0) + "%",
            (widthSize / 2).toFloat()+35f,
            100f,
            paint
        )
        paint.color = Color.Black.hashCode()
        canvas?.drawText(
            "" + (Math.round((deductionPercent * 100) * 10.0) / 10.0) + "%",
            80f,
            100f,
            paint
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }
}