package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductReviewsDto(
    @SerialName("product_id")
    val productId: Long,

    @SerialName("hide")
    val hide: Boolean = false, // Par défaut à false si absent

    @SerialName("reviews")
    val reviews: List<ReviewDto>
)
