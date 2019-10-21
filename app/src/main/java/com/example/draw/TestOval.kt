package com.example.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import android.content.res.Resources
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.RectF
import kotlin.math.cos
import kotlin.math.sin


class TestOval @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    var w: Int = 0
    var h: Int = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    init {
        setWillNotDraw(false)
    }

    //1. setWillNotDraw(false) - без него не рисуют
    //2. ось н инверитрована в android поэтому формулы sin/cos меняются
    //3. когда рисуем прямоугольник указываем 4 координаты углов
    //4. когда рисуем круг указываем центр и радиус
    //5. размер узнаем onSizeChanged
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            val paintYellow = Paint(ANTI_ALIAS_FLAG).apply {
                color = Color.YELLOW

            }

            val paintBlue = Paint(ANTI_ALIAS_FLAG).apply {
                color = Color.BLUE
            }

            val paintGreen = Paint(ANTI_ALIAS_FLAG).apply {
                color = Color.GREEN
            }

            val paintWhite = Paint(ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                strokeWidth = 5.dpToPx
            }

            // it.drawCircle(w/2f, h/2f, 100f, divider1)

            val radius = w / 2f

            val oval = RectF(0f, 0f, w.toFloat(), h.toFloat())

            val a = 150f
            val b = 150f
            val c = 150f

            val ags = (a / (a + b + c)) * 360f
            val bgs = (b / (a + b + c)) * 360f
            val cgs = (c / (a + b + c)) * 360f

            canvas.drawArc(oval, 0f, ags, true, paintYellow)
            canvas.drawArc(oval, ags, bgs, true, paintBlue)
            canvas.drawArc(oval, ags + bgs, cgs, true, paintGreen)


//            val x = radius - (radius * -cos(Math.toRadians(0.0))).toFloat()//100
//            val y = radius - (radius * -sin(Math.toRadians(0.0))).toFloat() //50
//
//
//            val x2 = radius - (radius * -cos(Math.toRadians(90.0))).toFloat() //50
//            val y2 = radius - (radius * -sin(Math.toRadians(90.0))).toFloat()//100
//
//            val x3 = radius - (radius * -cos(Math.toRadians(180.0))).toFloat()//0
//            val y3 = radius - (radius * -sin(Math.toRadians(180.0))).toFloat()//50
//
//            val x4 = radius - (radius * -cos(Math.toRadians(270.0))).toFloat()//50
//            val y4 = radius - (radius * -sin(Math.toRadians(270.0))).toFloat()//0

            val x = radius - (radius * -cos(Math.toRadians(ags.toDouble()))).toFloat()
            val y = radius - (radius * -sin(Math.toRadians(ags.toDouble()))).toFloat()


            val x2 = radius - (radius * -cos(Math.toRadians((ags + bgs).toDouble()))).toFloat()
            val y2 = radius - (radius * -sin(Math.toRadians((ags + bgs).toDouble()))).toFloat()

            val x3 =
                radius - (radius * -cos(Math.toRadians((ags + bgs + cgs).toDouble()))).toFloat()
            val y3 =
                radius - (radius * -sin(Math.toRadians((ags + bgs + cgs).toDouble()))).toFloat()


            canvas.drawLine(w / 2f, h / 2f, x, y, paintWhite)
            canvas.drawLine(w / 2f, h / 2f, x2, y2, paintWhite)
            canvas.drawLine(w / 2f, h / 2f, x3, y3, paintWhite)

            val borderWidth = 5.dpToPx

            val painWhiteStroke = Paint(ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                style = Paint.Style.STROKE
                strokeWidth = borderWidth
            }

            canvas.drawCircle(radius, radius, radius - borderWidth / 2, painWhiteStroke)
        }

    }

    companion object {
        val Int.dpToPx get() = this * Resources.getSystem().displayMetrics.density
    }
}