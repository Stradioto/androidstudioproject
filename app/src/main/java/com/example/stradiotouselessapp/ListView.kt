package com.example.stradiotouselessapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun ListView(navControl: NavHostController) {
    Column(modifier = Modifier
        .background(Color(0xFF5290CE))
        .fillMaxHeight()
        .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {
            ListBody()
            BottomField()
        }

    }
}

@Composable
fun ListBody() {
    var prodsList by remember { mutableStateOf(mutableListOf<String>())}

    Column(modifier = Modifier.padding(top= 20.dp, bottom = 20.dp),verticalArrangement = Arrangement.spacedBy(space = 5.dp))
    {
        val fAuth = Firebase.auth
        val fireStore = Firebase.firestore;

        var InfoList by remember { mutableStateOf("") }
        var InfoList2 by remember { mutableStateOf("") }

        InfoList = fAuth.currentUser?.email.toString()

        Text(text = InfoList)

        fireStore
            .collection("products")
            .whereEqualTo("user",fAuth.currentUser!!.uid)
            //.document()
            .get()
            .addOnSuccessListener {

                var prods = mutableListOf<String>();
                for(doc in it) {
                    prods.add(doc.get("food").toString())
                    //InfoList2 = doc.get("food").toString()
                    Log.d("*",doc.get("food").toString())
                }

                prodsList = prods
            }
            //ProductField(t = InfoList2)
        prodsList.forEach {
            ProductField(t = it)

        }
    }
}

@Composable
fun ProductField(t: String) {
    val fAuth = Firebase.auth
    val fireStore = Firebase.firestore;

    var item = "";

    Row(modifier = Modifier
        .background(Color(0xFF3434))
        .padding(10.dp)
        ) {
        Text(modifier = Modifier
            .padding(top = 10.dp, end = 20.dp), text = t)
        Button(
            onClick = {
                item = fireStore.collection("products")
                    .whereEqualTo("food",t).get().toString()

                fireStore.collection("products")
                    .document(item)
                    .delete() //not working in the current format
                Log.d("Button","Removed!")
            },
            modifier = Modifier
                .width(40.dp)
                .background(Color(0xFF3434))

        ) {
            Text(text = "-") /*Checkbox, radio and others*/
        }
    }

}

@Composable
fun BottomField() {

    val fAuth = Firebase.auth
    val fireStore = Firebase.firestore;

    var listData by remember { mutableStateOf("") }

    Row (modifier = Modifier
        .padding(5.dp)
        .width(400.dp)
        .padding(10.dp),
         verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(modifier = Modifier.height(60.dp), value = listData, onValueChange = {listData = it})

        Button(
            onClick = {
                fireStore
                    .collection("products")
                    .add(Product(listData,fAuth.currentUser!!.uid))
            },
            modifier = Modifier
                .width(40.dp)
                .background(Color(0xFF3434))
                .padding(bottom = 10.dp)

        ) {
            Text(text = "+") /*Checkbox, radio and others*/

        }
    }
}
