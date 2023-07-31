package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(context: Context, navController: NavController) {
    // Header with logo
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Header with logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .padding(35.dp)
                .height(50.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier.fillMaxWidth().padding(15.dp)
        )
        {
            Text(
                text = "Personal information",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display info
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString("first_name", "")
        val lastName = sharedPreferences.getString("last_name", "")
        val email = sharedPreferences.getString("email", "")

        Column(
            modifier = Modifier.padding(16.dp) // Add spacing between the text fields
        ) {

            if (firstName != null) {
                OutlinedTextField(
                    value = firstName,
                    readOnly = true,
                    maxLines = 1,
                    onValueChange = {},
                    label = { Text(text = "First name") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LittleLemonColor.green,
                        cursorColor = Color.Black,
                        focusedLabelColor = LittleLemonColor.green
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Add spacing between the first and last name fields

            if (lastName != null) {
                OutlinedTextField(
                    value = lastName,
                    readOnly = true,
                    maxLines = 1,
                    onValueChange = { },
                    label = { Text(text = "Last name") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LittleLemonColor.green,
                        cursorColor = Color.Black,
                        focusedLabelColor = LittleLemonColor.green,
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Add spacing between the last name and email fields

            if (email != null) {
                OutlinedTextField(
                    value = email,
                    readOnly = true,
                    maxLines = 1,
                    onValueChange = { },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = LittleLemonColor.green,
                        cursorColor = Color.Black,
                        focusedLabelColor = LittleLemonColor.green,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(150.dp))

        Button(
            onClick = {
                    val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
                    sharedPreferences.edit().clear().commit()

                    navController.navigate(Onboarding.route)
                },
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor=LittleLemonColor.yellow, contentColor=Color.Black)
        ) {
            Text("Log out")
        }

    }
}

