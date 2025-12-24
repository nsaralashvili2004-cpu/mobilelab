package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Sight(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val detailsResourseId: Int,
    @DrawableRes val imageResourceId: Int,
    val category: SightCategory
)

enum class SightCategory {
    MAIN,
    ARCHITECTURE,
    MUSEUM,
    RELIGIOUS,
    MONUMENT
}