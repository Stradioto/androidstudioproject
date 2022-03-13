package com.example.stradiotouselessapp

import android.widget.SearchView
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun BookView(navControl: NavHostController) {
    val dataVM = viewModel<APIVIEWMODEL>()
    val desertVM = viewModel<APIDESERTVIEWMODEL>()
    val beerVM = viewModel<APIBEERVIEWMODEL>()

    Column(modifier = Modifier
        .background(Color(0xFF5290CE))
        .fillMaxHeight()
        .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {

            Row(modifier = Modifier.padding(top= 5.dp),horizontalArrangement =  Arrangement.spacedBy(space = 5.dp)) {
                Button(
                    onClick = { dataVM.getRandomData() },
                    modifier = Modifier
                        .width(85.dp)
                        .background(Color(0xFF3334))
                        .padding(top = 10.dp)
                ) {
                    Text(text = "  Main ") /*Checkbox, radio and others*/
                }

                Button(
                    onClick = { desertVM.getDesertData() },
                    modifier = Modifier
                        .width(85.dp)
                        .background(Color(0xFF3334))
                        .padding(top = 10.dp)
                ) {
                    Text(text = "Desert") /*Checkbox, radio and others*/
                }

                Button(
                    onClick = { beerVM.getBeerData() },
                    modifier = Modifier
                        .width(85.dp)
                        .background(Color(0xFF3334))
                        .padding(top = 10.dp)
                ) {
                    Text(text = "  Beer  ") /*Checkbox, radio and others*/
                }
            }
            ResultsView(e = "Main Course",t2 = dataVM.fetchedData.value,t4 = dataVM.fetchedData2.value)
            ResultsView(e = "Desert",t2 = desertVM.desertData.value, t4 = desertVM.desertData2.value)
            ResultsView(e = "Beer",t2 = beerVM.beerData.value, t4 = beerVM.beerData2.value)
        }

    }
}

@Composable
fun ResultsView(e: String, t2: String, t4: String) {

    Text(text = e)

    Row (modifier = Modifier
        .padding(5.dp)
        .background(Color(0xFFA2A588))
        .height(100.dp)
        .width(300.dp)
        .padding(5.dp)
    ) {
        ResultText(t = t2, t3 = t4)
    }
}

@Composable
fun ResultText(t: String, t3: String) {

    Column() {
    Text(
        text = t,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        color = androidx.compose.ui.graphics.Color.Black,
    )
    Text(
        text = t3,
        fontSize = 14.sp,
        fontWeight = FontWeight.Black,
        color = androidx.compose.ui.graphics.Color.Black,
    )
    }
}