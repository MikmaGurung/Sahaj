package com.example.sahaj.Presentation.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sahaj.Data.Model.Sahaj
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Util.SahajState
import com.example.sahaj.Presentation.ViewModel.SahajViewModel

@Composable
fun List(
    state: SahajState,
    modifier: Modifier,
    onEvent: (SahajEvent) -> Unit,
    onItemClicked: (Sahaj) -> Unit,
    viewModel: SahajViewModel
){

    HomeScreenAddItem(modifier = modifier, onEvent = onEvent, state = state, viewModel)
    LazyColumn(
        modifier = Modifier.height(1000.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        items(state.sahaj){sahaj->
            SahajItem(
                sahaj = sahaj,
                onEvent = onEvent,
                onItemClicked = onItemClicked
            )
        }
    }
}
