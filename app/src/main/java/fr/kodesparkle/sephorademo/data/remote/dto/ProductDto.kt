package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val productId: Long,
    val productName: String,
    val description: String,
    val price: Double,
    val imagesUrl: ImagesUrlDto,
    val brand: BrandDto,
    val isProductSet: Boolean,
    val isSpecialBrand: Boolean
)

