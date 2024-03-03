package com.example.sahaj.Presentation.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sahaj.Data.Model.Sahaj
import com.example.sahaj.Util.SahajEvent

@Composable
fun SahajItem(
    sahaj: Sahaj,
    onEvent: (SahajEvent) -> Unit,
    onItemClicked :(Sahaj) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 10.dp,
                end = 6.dp,
                start = 6.dp
            )
            .clickable {
                onItemClicked(sahaj)
              onEvent(SahajEvent.selectedItem(sahaj))
            }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = sahaj.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        Row(modifier= Modifier.fillMaxWidth()){
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ){
                        append( "Units : ")
                    }
                    append(sahaj.units)
                })
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ){
                        append( "Amount : ")
                    }
                    append(sahaj.amount.toString())
                })
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold)
                    ){
                        append( "Remarks : ")
                    }
                    append(sahaj.remarks)
                })
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                    ){
                        append( "Status : ")
                    }
                    append(if (sahaj.paid) "Paid" else "Not Paid")
                }
                )
            }
            IconButton(onClick = {
                onEvent(SahajEvent.ShowDialog(true))
                onEvent(SahajEvent.onDeleteClicked(sahaj))
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
            }
        }
    }
}
@Preview
@Composable
fun SahajItemPreview(){
    SahajItem(
        sahaj = Sahaj(
            amount = 10000,
            title =  "Book Store",
            paid = true,
            remarks = "Null",
            units = "",
        ), onEvent = {},
        onItemClicked = {}
    )
}