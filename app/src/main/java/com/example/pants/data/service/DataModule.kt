package com.example.pants.data.service

import com.example.pants.domain.repository.productRepositoryModule
import org.koin.dsl.module

val dataModule = module {
    includes(networkModule, productRepositoryModule, useCaseModule)
}
