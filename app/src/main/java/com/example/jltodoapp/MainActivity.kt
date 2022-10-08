package com.example.jltodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jltodoapp.presentation.listing.ListingType
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CallListScreen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.ListingScreen
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.JLTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JLTodoAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CallListScreen.route
                        ) {
                            CallListScreen(navController)
                        }
                        composable(
                            route = Screen.ListingScreen.route + "/{listingType}"
                        ) {
                            ListingScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    navController.navigate(Screen.CallListScreen.route)
                }
            ) {
                Text(stringResource(R.string.label_call_list))
            }
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    navController.navigate(Screen.ListingScreen.route + "/${ListingType.BUY}")
                }
            ) {
                Text(stringResource(R.string.label_buy_list))
            }
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    navController.navigate(Screen.ListingScreen.route + "/${ListingType.SELL}")
                }
            ) {
                Text(stringResource(R.string.label_sell_list))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JLTodoAppTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}