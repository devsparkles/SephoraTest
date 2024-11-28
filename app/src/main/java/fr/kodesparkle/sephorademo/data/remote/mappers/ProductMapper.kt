package fr.kodesparkle.sephorademo.data.remote.mappers

import fr.kodesparkle.sephorademo.data.remote.dto.ProductDto
import fr.kodesparkle.sephorademo.data.remote.dto.ProductReviewsDto
import fr.kodesparkle.sephorademo.data.remote.dto.ReviewDto
import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductReview
import fr.kodesparkle.sephorademo.domain.model.Review

fun ProductDto.toProduct(): Product {
    return Product(
        idProduct = productId,
        name = productName,
        description = description,
        price = price,
        imageUrlSmall = imagesUrl.small,
        imageUrlLarge = imagesUrl.large,
        averageRating = 0.0
    )
}

fun ProductReviewsDto.toProductReview(): ProductReview {
    return ProductReview(
        productId = productId,
        reviews = reviews.map { it.toReview() }
    )
}

fun ReviewDto.toReview(): Review {
    return Review(
        name = name?:"",
        text = text?:"",
        rating = rating?:0.0
    )
}