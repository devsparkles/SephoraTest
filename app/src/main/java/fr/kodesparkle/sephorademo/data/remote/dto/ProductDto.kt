package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("product_id")
    val productId: Long,

    @SerialName("product_name")
    val productName: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("images_url")
    val imagesUrl: ImagesUrlDto,

    @SerialName("c_brand")
    val brand: BrandDto,

    @SerialName("is_productSet")
    val isProductSet: Boolean,

    @SerialName("is_special_brand")
    val isSpecialBrand: Boolean
)