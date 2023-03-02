package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    modifier: Modifier = Modifier
) {

    val systemUiController = rememberSystemUiController()
    val darkTheme = isSystemInDarkTheme()

    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    when (amphibianUiState) {
        is AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibianUiState.Success -> AmphibiansApp(amphibianUiState.amphibians)
        is AmphibianUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun AmphibiansApp(amphibians: List<Amphibian>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
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
            .aspectRatio(1f)
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = amphibian.name,
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = "(" + amphibian.type + ")",
                    style = MaterialTheme.typography.h6
                )
            }
            Text(
                text = amphibian.description,
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                contentDescription = "image description",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                contentScale = ContentScale.FillBounds
            )
        }
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
    AmphibiansAppCard(modifier = Modifier, amphibian = Amphibian("Great Basin Spadefoot","Toad","This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.","https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"))
}