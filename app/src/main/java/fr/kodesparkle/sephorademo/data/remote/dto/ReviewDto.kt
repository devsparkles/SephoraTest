package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    val name: String,
    val text: String,
    val rating: Double
)