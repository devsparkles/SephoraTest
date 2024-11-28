package fr.kodesparkle.sephorademo.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.kodesparkle.sephorademo.app.di.coroutines.DispatcherProvider
import fr.kodesparkle.sephorademo.domain.usecases.GetProductsWithReviewsUseCase
import fr.kodesparkle.sephorademo.domain.util.onError
import fr.kodesparkle.sephorademo.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsWithReviewsUseCase: GetProductsWithReviewsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())

    val state = _state.onStart { loadProducts() }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), ProductUiState()
    )

    private val _events = Channel<ProductEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() = viewModelScope.launch(dispatcherProvider.io) {
        _state.update { it.copy(isLoading = true) }
        getProductsWithReviewsUseCase()
            .onSuccess { products ->
                _state.update { it.copy(products = products, isLoading = false) }
            }
            .onError { error ->
                _state.update { it.copy(isLoading = false) }
                _events.send(ProductEvent.Error(error))
            }
    }

    fun onSearch(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun toggleReviewVisibility(productId: Int) {
        _state.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.product.idProduct.toInt() == productId) {
                    product.copy(reviews = product.reviews.reversed())
                } else product
            }
            currentState.copy(products = updatedProducts)
        }
    }
}
