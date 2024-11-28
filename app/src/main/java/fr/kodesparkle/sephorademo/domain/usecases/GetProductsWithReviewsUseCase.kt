package fr.kodesparkle.sephorademo.domain.usecases

import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductWithReviews
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import fr.kodesparkle.sephorademo.domain.util.DomainError
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result
class GetProductsWithReviewsUseCase(
    private val productRepository: RemoteProductRepository,
    private val reviewRepository: RemoteReviewRepository
) {
    suspend operator fun invoke(): Result<List<ProductWithReviews>, DomainError> {
        // Fetch products and reviews
        val productsResult = productRepository.getProducts()
        val reviewsResult = reviewRepository.getReviews()

        // Combine results using the custom Result type
        return when {
            productsResult is Result.Error -> Result.Error(productsResult.error)
            reviewsResult is Result.Error -> Result.Error(reviewsResult.error)
            productsResult is Result.Success && reviewsResult is Result.Success -> {
                val products = productsResult.data
                val reviews = reviewsResult.data

                // Combine products and reviews into the new domain model
                val productsWithReviews = products.map { product ->
                    val productReviews = reviews.find { it.productId == product.idProduct }?.reviews ?: emptyList()
                    ProductWithReviews(product, productReviews)
                }

                Result.Success(productsWithReviews)
            }
            else -> Result.Error(NetworkError.UNKNOWN) // Fallback case should create a specific domain error
        }
    }
}