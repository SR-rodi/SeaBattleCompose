package ru.sr.seabattlecompose.feald

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb

class Field {
    private var minDimension: Float? = null
    private val items = List(11) {}
    private var cellSize = 0f
    private val legend = listOf("A", "Б", "В", "Г", "Д", "Ж", "З", "И", "К", "Л")
    private var cellMinus = 0f
    private var cellCenter = 0f
    private var textSize = 0f
    private val strokeBorder = 15f
    private val strokeGrid = 5f

    fun initField(size: Float) {
        minDimension = size
        cellSize = size / items.size
        cellMinus = (cellSize * .3f)
        cellCenter = cellSize / 2
        textSize = cellSize - cellMinus
    }

    fun render(drawScope: DrawScope) {
        minDimension ?: throw NullPointerException("the init method of the class is not called")
        renderField(drawScope = drawScope)
        renderLegend(drawScope = drawScope)
    }

    private fun renderLegend(drawScope: DrawScope) {

        val numberPaint =
            Paint().apply {
                this.color = Color.Red.toArgb()
                this.textSize = this@Field.textSize
                this.textAlign = Paint.Align.LEFT
            }
        val textPaint =
            Paint().apply {
                this.color = Color.Red.toArgb()
                this.textSize = this@Field.textSize
                this.textAlign = Paint.Align.CENTER
            }

        for (index in 2..items.size) {
            val text = (index - 1).toString()

            drawScope.drawContext.canvas.nativeCanvas.apply {
                val measureText = numberPaint.measureText(text) + 20
                drawText(
                    text,
                    cellSize - measureText,
                    cellSize * index - cellMinus,
                    numberPaint
                )
                drawText(
                    legend[index - 2],
                    cellSize * (index - 1) + cellCenter,
                    textSize - 20,
                    textPaint
                )
            }
        }
    }

    private fun renderField(drawScope: DrawScope) {
        for (index in 2..items.lastIndex) {
            val x = index * cellSize
            val y = index * cellSize

            drawScope.drawLine(
                color = Color.Black,
                start = Offset(x, cellSize),
                end = Offset(x, cellSize * items.size),
                strokeWidth = strokeGrid
            )

            drawScope.drawLine(
                color = Color.Black,
                start = Offset(cellSize, y),
                end = Offset(cellSize * items.size, y),
                strokeWidth = strokeGrid
            )

        }

        drawScope.drawRect(
            color = Color.Black,
            topLeft = Offset(cellSize, cellSize),
            size = Size(cellSize * items.lastIndex, cellSize * items.lastIndex),
            style = Stroke(width = strokeBorder)
        )
    }
}