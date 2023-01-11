package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.R

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    modifier: Modifier = Modifier
) {
    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibianUiState.Success -> AmphibiansApp(amphibianUiState.amphibians)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun AmphibiansApp(amphibians: List<Amphibian>) {
    Scaffold(
        topBar = {
            AmphibianTopAppBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colors.background)
        ) {
            items(amphibians) {
                AmphibiansAppCard(amphibian = it)
            }
        }
    }
}

@Composable
fun AmphibiansAppCard(modifier: Modifier = Modifier, amphibian: Amphibian) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column {
            Text(
                text = amphibian.name,
                modifier = modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = amphibian.description,
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                style = MaterialTheme.typography.body1
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = "imageDescription",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun AmphibianTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}

@Preview
@Composable
fun AmphibiansAppCardPreview() {
    //AmphibiansAppCard()
}