package net.ezra.ui.des4

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.ezra.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_SHOES2


@Composable
fun Des4Screen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
//                    }
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            Icons.Filled.Edit,
//                            contentDescription = "Localized description",
//                        )
//                    }
                    Spacer(modifier = Modifier.width(150.dp))
                    IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Localized description",
                        )
                    }
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            Icons.Filled.Person,
//                            contentDescription = "Localized description",
//                        )
//                    }
                },
//                floatingActionButton = {
//                    FloatingActionButton(
//                        onClick = { /* do something */ },
//                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
//                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
//                    ) {
//                        Icon(Icons.Filled.Add, "Localized description")
//                    }
//                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Example of a scaffold with a bottom app bar."
        )
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
//        Image(
//            painter = painterResource(id = R.drawable.imge),
//            contentDescription = "",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    BookCard(
                        name = "Item 1",
                        what = "what its all about",
                        bookDescription = "A classic leather dress shoe exuding sophistication and style. Handcrafted with meticulous detail, featuring a cushioned insole and sturdy heel for all-day comfort and timeless elegance.",
                        imageResId = R.drawable.shoes2,
                        destination = ROUTE_SHOES2,
                        navController = navController,
                        presetRating = 4 // Specify the preset rating here
                    )

                }
            }
        }
    }


    }
}

@Composable
fun BookCard(
    name: String,
    what: String,
    bookDescription: String,
    imageResId: Int,
    navController: NavHostController,
    destination: String,
    presetRating: Int // New parameter for preset rating
) {
    var userRating by remember { mutableStateOf(presetRating) } // Use the preset rating

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .clickable { /* Navigate to book details screen */ },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .background(color = Color.White)

        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .graphicsLayer(clip = true)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(
                rating = userRating,
                onRatingChanged = { newRating -> userRating = newRating }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = what,
                color = Color.Black,
                fontSize = 30.sp

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bookDescription,
                color = Color.Black,
                fontSize = 15.sp
            )



            OutlinedButton(
                onClick = {
                    navController.navigate(destination)
                },
//                        modifier = Modifier.align(Alignment.CenterVertically)
            ){
                Text(text = "Buy Item", color = Color.Black)
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Rate: ")
        (1..5).forEach { index ->
            val isSelected = index <= rating
            Icon(
                painter = if (isSelected) {
                    painterResource(id = R.drawable.star4)
                } else {
                    painterResource(id = R.drawable.star4)
                },
                contentDescription = null,
                modifier = Modifier
                    .clickable { onRatingChanged(index) }
                    .padding(2.dp)
            )
        }
    }
}


@Preview
@Composable
fun DesScreenPreview() {
    Des4Screen(navController = rememberNavController())
}


