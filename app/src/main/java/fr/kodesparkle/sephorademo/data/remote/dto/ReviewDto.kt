package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName("name")
    val name: String? = null, // Peut être null si absent

    @SerialName("text")
    val text: String? = null, // Peut être null si absent

    @SerialName("rating")
    val rating: Double? = null // Peut être null si absent
)