package com.example.stradiotouselessapp

import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeView() {

    var loggedIn by remember { mutableStateOf(true)}

    Column(modifier = Modifier
        .background(Color(0xFF5290CE))
        .fillMaxHeight()
        .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {
        }

        if (loggedIn) {
            val navControl = rememberNavController()

            Row (modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFF444444))
                .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Icon(modifier = Modifier.clickable { navControl.navigate("HomeInfo") }, painter = painterResource(R.drawable.ic_baseline_home_24), contentDescription = "")
                Icon(modifier = Modifier.clickable { navControl.navigate("BookView") }, painter = painterResource(R.drawable.ic_baseline_menu_book_24), contentDescription = "")
                Icon(modifier = Modifier.clickable { navControl.navigate("ListView") }, painter = painterResource(R.drawable.ic_baseline_list_24), contentDescription = "")
                Icon(modifier = Modifier.clickable {
                    loggedIn = false;
                }, painter = painterResource(R.drawable.ic_baseline_exit_to_app_24), contentDescription = "")
            }
            Column(modifier = Modifier
                .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,

                ) {
                NavHost(navController = navControl, startDestination = "HomeInfo") {
                    composable(route="HomeInfo") {
                        HomeInfo(navControl)
                    }
                    composable(route="BookView") {
                        BookView(navControl)
                    }
                    composable(route="ListView") {
                        ListView(navControl)
                    }
                }
            }
        HomeH()
        RecipesR()
        var userName by remember { mutableStateOf("") }

        val fAuth = Firebase.auth

        userName = "Logged as ${fAuth.currentUser!!.email.toString()}!"

        Text(text = userName)

        }
        else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 20.dp)
            ) {

                val fAuth = Firebase.auth

                var loginData by remember { mutableStateOf("") }
                var passwordData by remember { mutableStateOf("") }
                var info by remember { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.padding(top = 40.dp),
                    value = loginData,
                    onValueChange = { loginData = it },
                    label = { Text(text = "Username") },
                )
                OutlinedTextField(
                    value = passwordData,
                    onValueChange = { passwordData = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),

                    )

                Row(horizontalArrangement = Arrangement.spacedBy(space = 5.dp)) {
                    Button(
                        onClick = {
                            fAuth
                                .createUserWithEmailAndPassword(loginData, passwordData)
                                .addOnSuccessListener {
                                    info = "New acc created"
                                }
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .background(Color(0xFF3434))

                    ) {
                        Text(text = "Register") /*Checkbox, radio and others*/
                    }

                    Button(
                        onClick = {
                            fAuth
                                .signInWithEmailAndPassword(loginData, passwordData)
                                .addOnSuccessListener {
                                    info =
                                        "You are logged in with acc ${it.user!!.email.toString()}"
                                    loggedIn = true
                                }
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .background(Color(0xFFFFFF)),

                        ) {
                        Text(text = "Login") /*Checkbox, radio and others*/
                    }
                }
                Text(text = info)
            }
        }
    }
}

@Composable //Explanation boxes
fun HomeH() {

    Row (modifier = Modifier
        .padding(20.dp)
        .background(Color(0xFFA2A588))
        .height(100.dp)
        .width(300.dp)
        .padding(10.dp)
    ) {

        Column () {
            Text(
                text = "HOME",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = androidx.compose.ui.graphics.Color.Black,
            )

            Text(text = "I really don't know how useful is this field, but three navigation icons are better than two")
        }

    }

}

@Composable //Explanation boxes
fun RecipesR() {


    Row (modifier = Modifier
        .padding(20.dp)
        .background(Color(0xFFA2A588))
        .height(100.dp)
        .width(300.dp)
        .padding(10.dp)
    ) {

        Column () {
            Text(
                text = "RANDOM MENU GENERATOR",
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = androidx.compose.ui.graphics.Color.Black,
            )

            Text(text = "Click in the book icon to generate challenging recipes")
        }
    }

}

@Composable //Explanation boxes
fun ProductsP() {


    Row (modifier = Modifier
        .padding(20.dp)
        .background(Color(0xFFA2A588))
        .height(100.dp)
        .width(300.dp)
        .padding(10.dp)
    ) {

        Column () {
        Text(
            text = "PRODUCTS LIST",
            fontSize = 14.sp,
            fontWeight = FontWeight.Black,
            color = androidx.compose.ui.graphics.Color.Black,
        )
            Text(text = "Click in the list icon to organize the items you need to make the generated recipes")
        }

    }

}

@Composable
    fun HomeInfo(navControl: NavHostController) {
        HomeH()
        RecipesR()
        ProductsP()
}