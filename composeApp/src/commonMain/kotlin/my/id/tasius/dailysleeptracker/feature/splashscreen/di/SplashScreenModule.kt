package my.id.tasius.dailysleeptracker.feature.splashscreen.di

import my.id.tasius.dailysleeptracker.feature.splashscreen.data.repository.SplashScreenRepositoryImpl
import my.id.tasius.dailysleeptracker.feature.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.dailysleeptracker.feature.splashscreen.ui.SplashScreenVM
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

fun splashModule() = module {
    single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    viewModel { SplashScreenVM(get()) }
}