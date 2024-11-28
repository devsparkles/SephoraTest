package fr.kodesparkle.sephorademo.data.remote.repositories

import fr.kodesparkle.sephorademo.data.remote.dto.ProductReviewsDto
import fr.kodesparkle.sephorademo.data.remote.mappers.toProductReview
import fr.kodesparkle.sephorademo.data.remote.utils.constructUrl
import fr.kodesparkle.sephorademo.data.remote.utils.safeCall
import fr.kodesparkle.sephorademo.domain.model.ProductReview
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result
import fr.kodesparkle.sephorademo.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteReviewRepositoryImpl(private val httpClient: HttpClient) : RemoteReviewRepository {

    override suspend fun getReviews(): Result<List<ProductReview>, NetworkError> {
        return safeCall<List<ProductReviewsDto>> {
            httpClient.get(
                urlString = constructUrl("/reviews.json")
            )
        }.map { response ->
            response.map {
                it.toProductReview()
            }
        }
    }

}