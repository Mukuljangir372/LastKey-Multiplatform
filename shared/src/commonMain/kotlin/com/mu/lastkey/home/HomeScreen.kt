package com.mu.lastkey.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.mu.lastkey.core.ui.components.Cards
import com.mu.lastkey.core.ui.components.Loaders
import com.mu.lastkey.core.ui.components.Toolbars
import com.mu.lastkey.core.ui.theme.LastKeyTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class HomeScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel by inject()
        HomeUiScreen(viewModel)
    }
}

@Composable
private fun HomeUiScreen(viewModel: HomeViewModel) {
    val state by viewModel.uiState.collectAsState(HomeUiState.Home)

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbars.Primary(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = LastKeyTheme.strings.home,
                        style = LastKeyTheme.typo.titleMedium
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = rememberVectorPainter(LastKeyTheme.materialIcons.Default.MoreVert),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (state) {
            HomeUiState.Loading -> {
                Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                    Loaders.Primary(modifier = Modifier.align(Alignment.Center))
                }
            }

            HomeUiState.Home -> {
                Home(modifier = Modifier.padding(paddingValues).fillMaxSize())
            }
        }
    }
}

@Composable
private fun Home(modifier: Modifier) {
    Column(modifier = modifier) {
        Profile(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun Profile(modifier: Modifier) {
    Cards.Primary(
        modifier = modifier
            .wrapContentHeight()
            .padding(
                vertical = LastKeyTheme.dimens.two.dp,
                horizontal = LastKeyTheme.dimens.two.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LastKeyTheme.dimens.two.dp,
                    horizontal = LastKeyTheme.dimens.half.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(LastKeyTheme.dimens.two.dp))

            Box(
                modifier = Modifier
                    .size(LastKeyTheme.dimens.four.dp)
                    .clip(CircleShape)
                    .background(LastKeyTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(LastKeyTheme.dimens.two.dp),
                    imageVector = LastKeyTheme.materialIcons.Default.Person,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(LastKeyTheme.dimens.two.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = LastKeyTheme.strings.profile,
                    style = LastKeyTheme.typo.titleMedium
                )
                Text(
                    text = LastKeyTheme.strings.viewProfile,
                    style = LastKeyTheme.typo.bodySmall
                )
            }
        }
    }
}
