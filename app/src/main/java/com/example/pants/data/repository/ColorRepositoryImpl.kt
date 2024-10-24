package com.example.pants.data.repository

import com.example.pants.data.mapper.toColorModel
import com.example.pants.domain.model.ColorModel
import com.example.pants.data.repository.utils.generateRandomColor
import com.example.pants.data.service.ColorApiService
import com.example.pants.domain.repository.COMMON_USE_NAMES
import com.example.pants.domain.repository.ColorRepository
import java.util.Locale

class ColorRepositoryImpl(
    private val apiService: ColorApiService,
    ) : ColorRepository {

    override suspend fun getRandomColors(count: Int): Result<Set<ColorModel>> = runCatching {
        val colorSet = mutableSetOf<ColorModel>()

        while (colorSet.size < count) {

            val randomColorResponse = apiService.getColor(generateRandomColor())
            val color = randomColorResponse.toColorModel()

            if (isColorValid(color)) {
                colorSet.add(color)
            }
        }
        colorSet.toSet()
    }


    private fun isColorValid(color: ColorModel): Boolean {
        return color.name.lowercase(Locale.getDefault()) !in COMMON_USE_NAMES
                && color.saturation > 0.3 && color.realHue > 0.4
    }

//    private fun generateRandomColorList(size: Int): List<String> {
//        return List(size) { generateRandomColor() }
//    }
}
