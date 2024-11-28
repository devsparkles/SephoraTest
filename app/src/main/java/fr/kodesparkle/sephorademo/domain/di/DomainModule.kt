package fr.kodesparkle.sephorademo.domain.di

import fr.kodesparkle.sephorademo.data.remote.repositories.RemoteProductRepositoryImpl
import fr.kodesparkle.sephorademo.data.remote.repositories.RemoteReviewRepositoryImpl
import fr.kodesparkle.sephorademo.data.remote.utils.HttpClientFactory
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import fr.kodesparkle.sephorademo.domain.usecases.GetProductsWithReviewsUseCase
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    factory { GetProductsWithReviewsUseCase(get(), get()) }

}