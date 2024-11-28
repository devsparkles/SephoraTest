package fr.kodesparkle.sephorademo.domain.repository

import fr.kodesparkle.sephorademo.domain.model.Product
import fr.kodesparkle.sephorademo.domain.util.NetworkError
import fr.kodesparkle.sephorademo.domain.util.Result

interface RemoteProductRepository {

    suspend fun getProducts(): Result<List<Product>, NetworkError>

}