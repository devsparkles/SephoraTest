package fr.kodesparkle.sephorademo.domain.model

data class ProductWithReviews(
    val product: Product,
    val reviews: List<Review>,
    var isExpanded: Boolean = false // New field for tracking expanded state

)