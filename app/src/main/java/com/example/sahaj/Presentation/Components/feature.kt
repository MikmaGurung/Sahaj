package com.example.sahaj.Presentation.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sahaj.Data.Model.Sahaj

@Composable
fun FeatureSection(
    sahaj: List<Sahaj>,
    onCardClicked : (Sahaj)-> Unit

){
    Column(modifier = Modifier.fillMaxWidth()){
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp)
        ){
            items(sahaj.size){
            SahajCard(sahaj = sahaj[it], onCardClicked = onCardClicked )
            }
        }
    }
}