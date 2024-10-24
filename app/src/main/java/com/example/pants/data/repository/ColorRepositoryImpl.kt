package com.example.pants.data.repository

import com.example.pants.data.mapper.toColorModel
import com.example.pants.domain.model.ColorModel
import com.example.pants.data.service.ColorApiService
import com.example.pants.domain.repository.ColorRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.util.Locale
import kotlin.random.Random


class ColorRepositoryImpl(
    private val apiService: ColorApiService,

) : ColorRepository {

    override suspend fun getRandomColors(count: Int): Result<Set<ColorModel>> = runCatching {
        val colorSet = mutableSetOf<ColorModel>()

        coroutineScope {
            while (colorSet.size < count) {
                val newColorsNeeded = count - colorSet.size


                val deferredColors = (1..newColorsNeeded).map {
                    async {
                        generateRandomColorModel()
                    }
                }

                deferredColors.awaitAll().forEach { color ->
                    if (isColorValid(color)) {
                        colorSet.add(color)
                    }
                }
            }
        }

        colorSet
    }

    private suspend fun generateRandomColorModel(): ColorModel {
        val hue = generateRandomHue()
        val saturation = 100
        val value = 100
        val hsvString = "$hue,$saturation,$value"
        val randomColorResponse = apiService.getColor(hsvString)
        return randomColorResponse.toColorModel()
    }

    private fun generateRandomHue(): Int {
        return Random.nextInt(0, 361)
    }

    private fun isColorValid(color: ColorModel): Boolean {
        return color.name.lowercase(Locale.getDefault()) !in COMMON_USE_NAMES
                && color.saturation > 0.3 && color.realHue > 0.4
    }

    private companion object {
        val COMMON_USE_NAMES = setOf(
            "beige", "black", "blue violet", "blue", "brown", "crimson",
            "cyan", "gold", "gray", "green", "indigo", "khaki",
            "lavender", "lime green", "magenta", "maroon", "navy blue",
            "olive", "orange", "pink", "plum", "purple", "red",
            "salmon", "silver", "sky blue", "teal", "violet",
            "white", "yellow",
        )
    }
}
