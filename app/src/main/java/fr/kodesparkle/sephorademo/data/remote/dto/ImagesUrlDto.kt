package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesUrlDto(
    @SerialName("small")
    val small: String,

    @SerialName("large")
    val large: String
)