package fr.kodesparkle.sephorademo.domain.model

data class ProductReview(
    val productId: Long,
    val reviews: List<Review>
)