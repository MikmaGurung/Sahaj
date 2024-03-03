package com.example.sahaj.Presentation.Screens.ItemScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sahaj.Data.Model.Sahaj
import com.example.sahaj.Feature.Navigation.Screen
import com.example.sahaj.Util.AddOrUpdateMode
import com.example.sahaj.Util.SahajEvent
import com.example.sahaj.Presentation.ViewModel.SahajViewModel

@Composable
fun ItemScreen(
    sahaj: Sahaj,
    onEvent :(SahajEvent)-> Unit,
    navigation : NavController,
    viewModel: SahajViewModel
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
           Text(
               text = sahaj.title,
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            modifier = Modifier
                .padding(start = 150.dp),
            text = buildAnnotatedString {
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
        Text(
            modifier = Modifier
                .padding(start = 150.dp),
            text = buildAnnotatedString {
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
        Text(
            modifier = Modifier
                .padding(start = 150.dp),
            text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold)
            ){
                append( "Remarks : ")
            }
            append(sahaj.remarks)
        })
        Text(
            modifier = Modifier
                .padding(start = 150.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Date : ")
                }
                append(sahaj.date)
            } )
        Text(
            modifier = Modifier
                .padding(start = 150.dp),
            text = buildAnnotatedString {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .padding(start = 150.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .width(100.dp),
                onClick = {
                    navigation.navigate(Screen.MainScreen.route)
                }) {
                Text(text = "Ok")
            }
            Button(onClick = {
                navigation.navigate(Screen.SelectionScreen.route)
                viewModel.changeMode(AddOrUpdateMode.UPDATE)
            }) {
                Text(text = "Update")
            }
        }
    }
}


//@Preview
//fun ItemScreenPreview(){
//    ItemScreen(
//        sahaj = Sahaj(
//            id = 1,
//            amount = null,
//            paid = false,
//            remarks = "lauda",
//            title = "lasan",
//            units= "43"
//        ),
//        onEvent = {},
//        navigation = rememberNavController()
//    )
//}