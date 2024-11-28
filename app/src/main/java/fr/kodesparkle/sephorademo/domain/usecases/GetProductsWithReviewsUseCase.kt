package fr.kodesparkle.sephorademo.domain.usecases

import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductWithReviews
import fr.kodesparkle.sephorademo.domain.model.Review
import fr.kodesparkle.sephorademo.domain.repository.RemoteProductRepository
import fr.kodesparkle.sephorademo.domain.repository.RemoteReviewRepository
import fr.kodesparkle.sephorademo.domain.util.DomainError
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result
class GetProductsWithReviewsUseCase(
    private val productRepository: RemoteProductRepository,
    private val reviewRepository: RemoteReviewRepository
) {

    // Main function to fetch and combine products and reviews
    suspend operator fun invoke(): Result<List<ProductWithReviews>, DomainError> {
        val productsResult = productRepository.getProducts()
        val reviewsResult = reviewRepository.getReviews()

        return when {
            productsResult is Result.Error -> Result.Error(productsResult.error)
            reviewsResult is Result.Error -> Result.Error(reviewsResult.error)
            productsResult is Result.Success && reviewsResult is Result.Success -> {
                val products = productsResult.data
                val reviews = reviewsResult.data

                // Combine products and reviews, and calculate average rating
                val productsWithReviews = products.map { product ->
                    val productReviews = reviews.find { it.productId == product.idProduct }?.reviews ?: emptyList()
                    val averageRating = calculateAverageRating(productReviews)

                    ProductWithReviews(product= product, reviews = productReviews, isExpanded = false, averageRating=averageRating)
                }

                Result.Success(productsWithReviews)
            }
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    // Helper function to calculate the average rating
    private fun calculateAverageRating(reviews: List<Review>): Double {
        return if (reviews.isNotEmpty()) {
            reviews.map { it.rating }.average()  // Calculate the average of all ratings
        } else {
            0.0  // Default to 0.0 if there are no reviews
        }
    }
}