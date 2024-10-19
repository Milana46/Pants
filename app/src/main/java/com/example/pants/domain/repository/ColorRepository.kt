package com.example.pants.domain.repository

import com.example.pants.domain.model.ColorModel



val COMMON_USE_NAMES = setOf(
    "beige",
    "black",
    "blue violet",
    "blue",
    "brown",
    "crimson",
    "cyan",
    "gold",
    "gray",
    "green",
    "indigo",
    "khaki",
    "lavender",
    "lime green",
    "magenta",
    "maroon",
    "navy blue",
    "olive",
    "orange",
    "pink",
    "plum",
    "purple",
    "red",
    "salmon",
    "silver",
    "sky blue",
    "teal",
    "violet",
    "white",
    "yellow",
)

interface ColorRepository {



    suspend fun getRandomColors(count: Int): Result<Set<ColorModel>>
}
