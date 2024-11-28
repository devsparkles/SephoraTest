package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductReviewsDto(
    val productId: Long,
    val hide: Boolean,
    val reviews: List<ReviewDto>
)

