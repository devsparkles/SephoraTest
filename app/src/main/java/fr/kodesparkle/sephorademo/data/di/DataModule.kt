package fr.kodesparkle.sephorademo.data.di

import fr.kodesparkle.sephorademo.data.remote.repositories.RemoteProductRepositoryImpl
import fr.kodesparkle.sephorademo.data.remote.repositories.RemoteReviewRepositoryImpl
import fr.kodesparkle.sephorademo.data.remote.utils.HttpClientFactory
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import org.koin.dsl.bind

val dataModule = module {
    single { HttpClientFactory.create(CIO.create()) }

    single { RemoteReviewRepositoryImpl(get()) } bind RemoteReviewRepository::class
    single { RemoteProductRepositoryImpl(get()) } bind RemoteProductRepository::class

}