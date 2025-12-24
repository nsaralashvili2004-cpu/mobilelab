package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.DataSource
import com.example.mycity.model.Sight
import com.example.mycity.model.SightCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SightsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SightsUiState(
            sightsList = DataSource.getMain(),
            currentSight = DataSource.getMain().getOrElse(0) {
                DataSource.defaultSight
            },
            isShowingListPage = true,
            currentCategory = SightCategory.MAIN
        )
    )
    val uiState: StateFlow<SightsUiState> = _uiState

    fun updateCurrentCategory(selectedCategory: SightCategory) {
        _uiState.update {
            when (selectedCategory) {
                SightCategory.ARCHITECTURE -> it.copy(currentCategory = selectedCategory, sightsList = DataSource.getArchitecture())
                SightCategory.MUSEUM -> it.copy(currentCategory = selectedCategory, sightsList = DataSource.getMuseums())
                SightCategory.RELIGIOUS -> it.copy(currentCategory = selectedCategory, sightsList = DataSource.getReligiousObjects())
                SightCategory.MONUMENT -> it.copy(currentCategory = selectedCategory, sightsList = DataSource.getMonuments())
                else -> it.copy(currentCategory = selectedCategory, sightsList = DataSource.getMain())
            }
        }
    }

    fun updateCurrentSight(selectedSight: Sight) {
        _uiState.update {
            it.copy(currentSight = selectedSight)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class SightsUiState(
    val sightsList: List<Sight> = emptyList(),
    val currentSight: Sight = DataSource.defaultSight,
    val currentCategory: SightCategory = SightCategory.MAIN,
    val isShowingListPage: Boolean = true
)
