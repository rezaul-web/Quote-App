package com.example.quotegeneratorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotegeneratorapp.screens.HomeScreen
import com.example.quotegeneratorapp.screens.SavedQuotesScreen
import com.example.quotegeneratorapp.ui.theme.QuoteGeneratorAppTheme
import com.example.quotegeneratorapp.viewmodel.DatabaseViewModel
import com.example.quotegeneratorapp.viewmodel.HomeViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController= rememberNavController()
            val showTopBar = remember { mutableStateOf(true) } // State to control visibility

            // Update top bar visibility based on current destination
            navController.addOnDestinationChangedListener { _, destination, _ ->
                showTopBar.value = when (destination.route) {
                    "home" -> true // Show top bar on "home"
                    "saved" -> false // Show top bar on "saved"
                    else -> false // Hide top bar on other screens
                }
            }
            QuoteGeneratorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (showTopBar.value){
                            TopAppBarItems(navController=navController)
                        }
                    },


                ) { innerPadding ->
                    val homeViewmodel: HomeViewmodel by viewModels()
                    val databaseViewModel:DatabaseViewModel by viewModels()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home",

                            ) {
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                homeViewmodel = homeViewmodel
                            )

                        }
                        composable("saved") {

                            SavedQuotesScreen(databaseViewModel = databaseViewModel
                            )
                        }
                    }

                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun TopAppBarItems(navController: NavController) {
        TopAppBar(
            title = { Text("Quote Generator",
                modifier = Modifier.clickable {
                    if (navController.currentBackStackEntry?.destination?.route != "home") {
                        navController.popBackStack()
                        navController.navigate("home")
                    }



                }) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Cyan,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black,
                actionIconContentColor = Color.Cyan
            ),
            actions = {
                TextButton (
                    onClick = {
                        navController.navigate("saved")

                    },
                    colors = ButtonDefaults.buttonColors(Color.Cyan)
                ) {
                    Text("Saved Quotes", fontSize = 18.sp)
                }

            }
        )
    }
}

