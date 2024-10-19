package com.example.pants.usecases

import com.example.pants.domain.model.ColorModel

class CheckBoardOrderUseCase {

    operator fun invoke(board: List<ColorModel>): Boolean {
        return board.zipWithNext { a, b -> a.realHue <= b.realHue }.all { it }
    }
}
