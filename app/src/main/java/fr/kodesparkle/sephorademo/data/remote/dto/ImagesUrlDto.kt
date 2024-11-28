package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImagesUrlDto(
    val small: String,
    val large: String
)