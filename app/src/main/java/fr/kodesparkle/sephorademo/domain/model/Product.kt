package fr.kodesparkle.sephorademo.domain.model

data class Product(
    val idProduct: Long,
    val name: String,
    val averageRating: Double,
    val description: String,
    val price: Double,
    val imageUrlSmall: String,
    val imageUrlLarge: String

)