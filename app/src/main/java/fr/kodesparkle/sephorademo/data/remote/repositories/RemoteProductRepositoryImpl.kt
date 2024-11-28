package fr.kodesparkle.sephorademo.data.remote.repositories

import fr.kodesparkle.sephorademo.data.remote.dto.ProductDto
import fr.kodesparkle.sephorademo.data.remote.mappers.toProduct
import fr.kodesparkle.sephorademo.data.remote.utils.constructUrl
import fr.kodesparkle.sephorademo.data.remote.utils.safeCall
import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result
import fr.kodesparkle.sephorademo.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteProductRepositoryImpl(private val httpClient: HttpClient) : RemoteProductRepository {

    override suspend fun getProducts(): Result<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(
                urlString = constructUrl("/items.json")
            )
        }.map { response ->
            response.map {
                it.toProduct()
            }
        }
    }

}