package com.derlishn.uberx.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.derlishn.uberx.R
import com.derlishn.uberx.ui.theme.CardColor
import com.derlishn.uberx.ui.theme.CardColorSecondary
import com.derlishn.uberx.ui.theme.DarkBackgroundColor
import com.derlishn.uberx.ui.theme.ItemMenuColor

@Composable
fun HomeScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackgroundColor)
    ) {
        val (nameRow, cardBanner, cardTravels, destinationInput, destinationList, bottomMenu ) = createRefs()
        NameRow(
            name = "Derlis Aguilar",
            modifier = Modifier.padding(top = 32.dp).constrainAs(nameRow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        CardBanner(
            modifier = Modifier
                .constrainAs(cardBanner) {
                top.linkTo(nameRow.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        CardTravels(
            modifier = Modifier.constrainAs(cardTravels) {
                top.linkTo(cardBanner.bottom)
                start.linkTo(parent.start)
            }
        )
        DestinationInput(
            modifier = Modifier.constrainAs(destinationInput) {
                top.linkTo(cardTravels.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        PlanetListScreen(
            modifier = Modifier.constrainAs(destinationList) {
                top.linkTo(destinationInput.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        BottomMenu(
            navController = navController,
            modifier = Modifier.constrainAs(bottomMenu) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }
        )
    }
}

@Composable
fun NameRow(name: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun CardBanner(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardColor, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Always Wear Your Helmet\nWhen Traveling In Space",
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                )
                TextButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Text(
                        text = "About Safe Space Flight ",
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_astronaut),
                contentDescription = null,
                //contentScale = androidx.compose.ui.layout.ContentScale.None,
                modifier = Modifier.size(130.dp)
            )
        }
    }
}


@Composable
fun CardTravels(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TravelOption(iconId = R.drawable.ic_nave, text = "Travels")
        TravelOption(iconId = R.drawable.ic_planteta, text = "Explore")
        TravelOption(iconId = R.drawable.ic_astronaut_white, text = "Book")
    }
}

@Composable
fun TravelOption(iconId: Int, text: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .height(100.dp)
            .width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DestinationInput(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(CardColor, shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Where to...?",
            color = Color.White,
            fontSize = 16.sp
        )
        Box(
            modifier = Modifier
                .background(CardColorSecondary, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Now",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(
                    onClick = { /* TODO: Implement dropdown action */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlanetListScreen(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            PlanetItem(
                imageId = R.drawable.earth,
                title = "Earth",
                subtitle = "Solar System, Planet 4"
            )
        }
        item {
            PlanetItem(
                imageId = R.drawable.mars,
                title = "Mars",
                subtitle = "Solar System, Planet 5"
            )
        }
        item {
            PlanetItem(
                imageId = R.drawable.iss,
                title = "International Space Station",
                subtitle = "Low Earth Orbit"
            )
        }
        item {
            PlanetItem(
                imageId = R.drawable.moon,
                title = "Moon",
                subtitle = "Low Earth Orbit"
            )
        }
    }
}

@Composable
fun PlanetItem(imageId: Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(CardColor.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BottomMenu(navController: NavController, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CardColor.copy(0.9f), shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MenuItem(iconId = R.drawable.ic_home, text = "Home")
        Spacer(modifier = Modifier.width(1.dp).height(24.dp).background(Color.Gray))
        MenuItem(iconId = R.drawable.ic_journey, text = "Journeys")
    }
}

@Composable
fun MenuItem(iconId: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {

        }) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                Modifier.size(20.dp),
                tint = Color.White
            )
        }
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}