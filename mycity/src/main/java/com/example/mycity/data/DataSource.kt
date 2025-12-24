package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Sight
import com.example.mycity.model.SightCategory

object DataSource {
    val defaultSight = getMain()[0]

    fun getMain(): List<Sight> {
        return listOf(
            Sight(1, R.string.category_architecture, R.string.default_string, R.drawable.architecture1, SightCategory.ARCHITECTURE),
            Sight(2, R.string.category_museums, R.string.default_string, R.drawable.museum1, SightCategory.MUSEUM),
            Sight(3, R.string.category_religious, R.string.default_string, R.drawable.cathedral1, SightCategory.RELIGIOUS),
            Sight(4, R.string.category_monuments, R.string.default_string, R.drawable.monument1, SightCategory.MONUMENT)
            )
    }

    fun getArchitecture(): List<Sight> {
        return listOf(
            Sight(5, R.string.architecture_title_1, R.string.architecture_desc_1, R.drawable.architecture1, SightCategory.ARCHITECTURE),
            Sight(6, R.string.architecture_title_2, R.string.architecture_desc_2, R.drawable.architecture2, SightCategory.ARCHITECTURE),
            Sight(7, R.string.architecture_title_3, R.string.architecture_desc_3, R.drawable.architecture3, SightCategory.ARCHITECTURE),
        )
    }

    fun getMuseums(): List<Sight> {
        return listOf(
            Sight(8, R.string.museum_title_1, R.string.museum_desc_1, R.drawable.museum1, SightCategory.MUSEUM),
            Sight(9, R.string.museum_title_2, R.string.museum_desc_2, R.drawable.museum2, SightCategory.MUSEUM),
            Sight(10, R.string.museum_title_3, R.string.museum_desc_3, R.drawable.museum3, SightCategory.MUSEUM),
        )
    }

    fun getReligiousObjects(): List<Sight> {
        return listOf(
            Sight(11, R.string.religious_title_1, R.string.religious_desc_1, R.drawable.cathedral1, SightCategory.RELIGIOUS),
            Sight(12, R.string.religious_title_2, R.string.religious_desc_2, R.drawable.cathedral2, SightCategory.RELIGIOUS),
        )
    }

    fun getMonuments(): List<Sight> {
        return listOf(
            Sight(13, R.string.monument_title_1, R.string.monument_desc_1, R.drawable.monument1, SightCategory.MONUMENT),
            Sight(14, R.string.monument_title_2, R.string.monument_desc_2, R.drawable.monument2, SightCategory.MONUMENT),
            Sight(15, R.string.monument_title_3, R.string.monument_desc_3, R.drawable.monument3, SightCategory.MONUMENT)
        )
    }
}
