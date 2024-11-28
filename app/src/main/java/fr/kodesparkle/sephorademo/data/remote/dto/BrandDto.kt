package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BrandDto(
    val id: String,
    val name: String
)
