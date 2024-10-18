package com.example.pants.data.service

import com.example.pants.usecases.CheckBoardOrderUseCase
import com.example.pants.usecases.GetColorBoardUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetColorBoardUseCase)
    factoryOf(::CheckBoardOrderUseCase)
}
