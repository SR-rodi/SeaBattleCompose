package ru.sr.seabattlecompose.feald

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.lifecycle.ViewModel

class GameFieldViewModel() : ViewModel() {

    private var gameField: Field = Field()

    fun setDimension(size: Float) {
        gameField.initField(size = size)
    }

    fun render(drawScope: DrawScope) {
        gameField.render(drawScope)
    }

}