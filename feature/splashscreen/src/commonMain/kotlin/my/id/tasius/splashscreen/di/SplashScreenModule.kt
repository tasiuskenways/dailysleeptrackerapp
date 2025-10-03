package my.id.tasius.splashscreen.di

import my.id.tasius.splashscreen.data.repository.SplashScreenRepositoryImpl
import my.id.tasius.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.splashscreen.domain.usecase.GetSplashStateUseCase
import my.id.tasius.splashscreen.ui.SplashScreenVM
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

fun splashModule() = module {
    single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    viewModel { SplashScreenVM(get()) }
    factory { GetSplashStateUseCase(get()) }
}