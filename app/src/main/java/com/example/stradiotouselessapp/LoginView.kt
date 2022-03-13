package com.example.stradiotouselessapp

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun LoginView() {

    var loggedIn by remember { mutableStateOf(false)}

    Column(modifier = Modifier
        .background(Color(0xFF5290CE))
        .fillMaxHeight()
        .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        LoginHeader(Alignment.TopCenter)// Justify example)
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 20.dp)) {
            if(loggedIn) {
                HomeView()
            }
            else {
                val fAuth = Firebase.auth

                var loginData by remember { mutableStateOf("") }
                var passwordData by remember { mutableStateOf("")}
                var info by remember { mutableStateOf("")}

                OutlinedTextField(modifier = Modifier.padding(top = 40.dp),
                    value = loginData,
                    onValueChange = {loginData = it},
                    label = { Text(text = "Username")},
                )
                OutlinedTextField(
                    value = passwordData,
                    onValueChange = {passwordData = it},
                    label = { Text(text = "Password")},
                    visualTransformation = PasswordVisualTransformation(),
                    )
                Row(horizontalArrangement =  Arrangement.spacedBy(space = 5.dp)) {
                    Button(
                        onClick = {
                            fAuth
                                .createUserWithEmailAndPassword(loginData,passwordData)
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
                                .signInWithEmailAndPassword(loginData,passwordData)
                                .addOnSuccessListener {
                                    info =  "You are logged in with acc ${it.user!!.email.toString()}"
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

@Composable
fun LoginHeader(topCenter: Alignment) {

    Row () {
        Text(
            text = "SuAPP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .background(Color(0xFF000000))
                .height(80.dp)
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Start,
        )

    }
}