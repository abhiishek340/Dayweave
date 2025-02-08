package com.lifesyncpro.app.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lifesyncpro.app.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LifeSync Pro") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedItem == 1,
                    onClick = { 
                        selectedItem = 1
                        navController.navigate(Screen.Profile.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = "Tasks") },
                    label = { Text("Tasks") },
                    selected = selectedItem == 2,
                    onClick = { 
                        selectedItem = 2
                        navController.navigate(Screen.Task.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Health") },
                    label = { Text("Health") },
                    selected = selectedItem == 3,
                    onClick = { 
                        selectedItem = 3
                        navController.navigate(Screen.Health.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Analytics, contentDescription = "Insights") },
                    label = { Text("Insights") },
                    selected = selectedItem == 4,
                    onClick = { 
                        selectedItem = 4
                        navController.navigate(Screen.Insights.route)
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Quick Actions Section
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Activity Summary Section
            Text(
                text = "Today's Activity",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
            
            // Upcoming Tasks Section
            Text(
                text = "Upcoming Tasks",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
            
            // Health Insights Section
            Text(
                text = "Health Insights",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )
        }
    }
} 