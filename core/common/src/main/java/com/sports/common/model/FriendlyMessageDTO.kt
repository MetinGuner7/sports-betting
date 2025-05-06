package com.sports.common.model

data class FriendlyMessageDTO(
    val title: String?,
    val message: String?,
    val buttonPositive: String?,
    val buttonNegative: String?,
    val positiveButtonClick: (() -> Unit)? = null,
    val negativeButtonClick: (() -> Unit)? = null,
)
