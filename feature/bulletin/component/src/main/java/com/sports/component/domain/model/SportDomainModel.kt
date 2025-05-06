package com.sports.component.domain.model

data class SportDomainModel(
    val key: String,
    val group: String,
    val title: String,
    val description: String,
    val isActive: Boolean,
    val hasOutrights: Boolean
)
