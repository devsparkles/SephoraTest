package fr.kodesparkle.sephorademo.presentation.products

import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.model.ProductWithReviews

data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<ProductWithReviews> = emptyList(),
    val filteredProducts: List<ProductWithReviews> = emptyList(), // Filtered products for search
    val searchQuery: String = "",
    val errorMessage: String? = null
)
