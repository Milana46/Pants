package com.example.benchmarkz

import android.content.Intent
import android.util.Log
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pants.data.repository.ColorRepositoryImpl
import com.example.pants.data.service.ColorApiService
import com.example.pants.domain.ColorResponse
import com.example.pants.domain.Hsv
import com.example.pants.domain.Name
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class ApiMock : ColorApiService {
    override suspend fun getColor(hsv: String): ColorResponse {
        return ColorResponse(Name("name"), Hsv(Hsv.Fraction(0.512f, 0.445f, 0.456f)))
    }
}

@RunWith(AndroidJUnit4::class)
class ExampleBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()


    @Test
    fun example14() {
        benchmarkRule.measureRepeated {
            runBlocking {
                launch { (ColorRepositoryImpl(ApiMock())).getRandomColors(1024) }.join()
            }

        }
    }
}