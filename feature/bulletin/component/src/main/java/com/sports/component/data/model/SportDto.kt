package com.sports.component.data.model


import com.sports.component.domain.model.SportDomainModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportDto(
    val key: String? = null,
    val group: String? = null,
    val title: String? = null,
    val description: String? = null,
    val active: Boolean? = null,
    @Json(name = "has_outrights")
    val hasOutrights: Boolean? = null,
)

fun SportDto.toDomainModel(): SportDomainModel {
    return SportDomainModel(
        key = this.key.orEmpty(),
        group = this.group.orEmpty(),
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        isActive = this.active?: false,
        hasOutrights = this.hasOutrights?: false,
    )
}

fun List<SportDto>.toDomainModel(): List<SportDomainModel> {
    return this.map { it.toDomainModel() }
}