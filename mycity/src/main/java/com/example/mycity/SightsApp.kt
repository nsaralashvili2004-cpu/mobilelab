package com.example.mycity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycity.model.Sight
import com.example.mycity.model.SightCategory
import com.example.mycity.ui.SightDetails
import com.example.mycity.ui.SightsViewModel
import com.example.mycity.ui.SightsList
import com.example.mycity.ui.theme.GreetingCardTheme

@Composable
fun SightsApp() {
    val viewModel: SightsViewModel = SightsViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            SightsAppBar(
                isShowingFirstPage = uiState.currentCategory == SightCategory.MAIN,
                onBackButtonClick = {
                    if (uiState.isShowingListPage) {
                        viewModel.updateCurrentCategory(SightCategory.MAIN)
                    } else {
                        viewModel.navigateToListPage()
                    }
                },
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage) {
            SightsList(
                sights = uiState.sightsList,
                onClick = if (uiState.currentCategory != SightCategory.MAIN) {
                    { sight: Sight ->
                        viewModel.updateCurrentSight(sight)
                        viewModel.navigateToDetailPage()
                    }
                } else {
                    { sight: Sight ->
                        viewModel.updateCurrentCategory(sight.category)
                    }
                },
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding,
            )
        } else {
            SightDetails(
                selectedSight = uiState.currentSight,
                contentPadding = innerPadding,
                onBackPressed = {
                    viewModel.navigateToListPage()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SightsAppBar(
    onBackButtonClick: () -> Unit,
    isShowingFirstPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Что посетить в Рыбинске?",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        navigationIcon = if (!isShowingFirstPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
fun AppPreview() {
    GreetingCardTheme {
        SightsApp()
    }
}