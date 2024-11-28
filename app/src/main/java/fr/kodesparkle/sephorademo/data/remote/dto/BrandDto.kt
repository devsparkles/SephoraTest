package fr.kodesparkle.sephorademo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandDto(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String
)