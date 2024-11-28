package fr.kodesparkle.sephorademo.domain.repository

import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductReview
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result

interface RemoteReviewRepository {
    suspend fun getReviews(): Result<List<ProductReview>, NetworkError>

}