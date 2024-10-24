package com.example.pants

import com.example.pants.data.repository.ColorRepositoryImpl
import com.example.pants.data.service.ColorApiService
import com.example.pants.domain.ColorResponse
import com.example.pants.domain.Hsv
import com.example.pants.domain.Name
import com.example.pants.domain.repository.COMMON_USE_NAMES
import com.example.pants.domain.repository.ColorRepository
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking
import org.junit.Test


import org.junit.Assert.*


class ApiMock : ColorApiService {
    override suspend fun getColor(hsv: String): ColorResponse {
        return ColorResponse(Name("name"), Hsv(Hsv.Fraction(0.512f, 0.445f, 0.456f)))
    }
}

suspend fun colorRepositoryTest(repo: ColorRepository) {
    val colors = repo.getRandomColors(1024).getOrThrow()

    for (color in colors) {
        assert(color.saturation > 0.3)
        assert(color.realHue > 0.4)

        for (name in COMMON_USE_NAMES) {
            assert(!color.name.contains(name))
        }
    }
}

class ExampleUnitTest {
    @Test
    fun colorRepository() = runBlocking {
        launch { colorRepositoryTest(ColorRepositoryImpl(ApiMock())) }.join()
    }
}
