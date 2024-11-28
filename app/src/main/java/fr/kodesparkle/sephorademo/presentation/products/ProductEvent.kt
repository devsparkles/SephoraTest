package fr.kodesparkle.sephorademo.presentation.products

import fr.kodesparkle.sephorademo.domain.util.DomainError
import fr.kodesparkle.sephorademo.domain.util.NetworkError

sealed interface ProductEvent {
    data class Error(val error: DomainError): ProductEvent


}