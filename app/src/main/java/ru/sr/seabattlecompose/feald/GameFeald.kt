package ru.sr.seabattlecompose.feald

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun GameField(modifier: Modifier = Modifier) {

    val viewModel by remember {
        mutableStateOf(GameFieldViewModel())
    }


    Canvas(modifier = modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
        viewModel.setDimension(size.minDimension)
        viewModel.render(this)
    }

}

@Preview
@Composable
fun GameFieldPreview() {

    GameField()

}