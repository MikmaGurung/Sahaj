package com.example.sahaj.Presentation.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sahaj.Data.Model.Sahaj


@Composable
fun SahajCard(
    sahaj : Sahaj,
    onCardClicked : (Sahaj) -> Unit
){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(100.dp)
            .height(100.dp)
            .clickable(onClick = {
                onCardClicked(sahaj)}
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Text(text = sahaj.title,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp)
        }
    }
}

//@Composable
//@Preview
//fun SahajCardPreview(){
//    SahajCard(
//        sahaj = Sahaj(
//            title = "Book Store",
//            amount = null,
//            units = null,
//            remarks = "",
//            paid = false
//        )
//    )
//}