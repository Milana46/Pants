package com.example.pants.domain.repository

import com.example.pants.service.ColorRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val productRepositoryModule = module {
    singleOf(::ColorRepositoryImpl) { bind<ColorRepository>() }
}
