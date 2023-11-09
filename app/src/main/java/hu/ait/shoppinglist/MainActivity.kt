package hu.ait.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.shoppinglist.screen.ListScreen
import hu.ait.shoppinglist.ui.theme.ShoppingListTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // create a loading screen here
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    ShoppingListApp()
                }
            }
            
        
        
        }
    }
}

@Composable
fun ShoppingListApp(
    modifier: Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination: String = "listScreen"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        //composable("addScreen") { AddScreen() }
        composable("listScreen") { ListScreen() }
    }
}



@Composable
fun AddScreen() {
    TODO("Not yet implemented")
}



