package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.skydoves.landscapist.glide.GlideImage

// Fonts
val karlaFontFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Bold),
    // Add other font weights if available (e.g., Bold, Italic, etc.)
)

val markFontFamily = FontFamily(
    Font(R.font.markaztext_regular, FontWeight.Normal)
)


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(context: Context, navController: NavController, items: List<MenuItemRoom>) {


    var filterMenuItems by remember { mutableStateOf(false) }
    var filterCategory by remember { mutableStateOf("") }

    val groupedMenuItems = items.groupBy { it.category }

    var menuItems = if (filterMenuItems) {
        filterMenuItems(items, filterCategory)
    } else {
        items
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HomeHeader(onProfileClick = {
            // Navigate to the Profile screen
            navController.navigate(Profile.route)
        })

        // Static text
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(LittleLemonColor.green), contentAlignment = Alignment.Center
        )
        {
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                Text( text = "Little Lemon", color = Color.Yellow, fontSize = 50.sp )
                //Spacer(modifier = Modifier.height(10.dp))
               Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier
                        .widthIn(max = 230.dp)
                        .padding(end = 10.dp)
                        .weight(1f)) {
                        Text( text = "Chicago", color = Color.White, fontSize = 30.sp )
                        //Spacer(modifier = Modifier.height(10.dp))
                        Text( text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist", softWrap = true, color = Color.White, fontFamily = markFontFamily, fontSize = 18.sp )
                    }
                   Spacer(modifier = Modifier.width(5.dp))
                    Box(modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .offset(y = (0).dp)
                        .padding(top = 20.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.hero_image), contentDescription = "", modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)), contentScale = ContentScale.Crop)
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                var searchPhrase by remember { mutableStateOf("") }

                // Add OutlinedTextField2
                TextField(value = searchPhrase, onValueChange = {searchPhrase = it}, label = { Text(text ="Enter search phrase")}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, containerColor = Color.White,
                        focusedLabelColor = Color.Black, focusedTrailingIconColor = Color.Black,
                        cursorColor = Color.Black,  focusedIndicatorColor = Color.Black
                    )
                )


                if (searchPhrase.isNotEmpty()) {
                    menuItems = menuItems.filter { menuItem ->
                        menuItem.title.contains(searchPhrase, ignoreCase = true)
                    }
                }

            }

        }

        Column(
            modifier = Modifier.padding(top=15.dp, start = 10.dp, end = 10.dp)
        )
        {
            Text(
                text = "ORDER FOR DELIVERY!",
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                for (category in groupedMenuItems.keys) {
                    Button(
                        onClick = { filterMenuItems = true
                            filterCategory = category
                        },
                        modifier = Modifier.padding(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LittleLemonColor.LightGrey, contentColor = LittleLemonColor.green)
                    ) {
                        Text(text = category)
                    }
                }
            }

            Divider(color = LittleLemonColor.LightGrey, thickness = 1.dp)
        }

        MenuItems(items = menuItems)

    }

}

fun filterMenuItems(menuItems: List<MenuItemRoom>, filterCategory: String): List<MenuItemRoom> {
    return menuItems.filter { it.category == filterCategory }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(onProfileClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .height(30.dp),
                    alignment = Alignment.Center
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .height(40.dp)
                    .clickable { onProfileClick() }
            )
        }
    )
}


@Composable
private fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp, start = 10.dp, end = 25.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column( modifier = Modifier.widthIn(max = 280.dp)
                        .padding(end = 10.dp)) {

                        Text(text = menuItem.title, fontFamily = karlaFontFamily)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = menuItem.description, fontSize = 10.sp, softWrap = true)
                        Text(text = menuItem.price.toString(), fontFamily = karlaFontFamily)
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                    GlideImage(
                        imageModel = menuItem.image,
                        contentDescription = "Image of dish", // Provide a proper content description if needed ,
                        modifier = Modifier.size(50.dp)
                        // Optional: shimmerParams for a shimmer effect while loading
                        // shimmerParams = ShimmerParams() // Customize shimmer effect if needed
                    )
                }
            }
        )
    }
}



