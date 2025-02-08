package com.lifesyncpro.app.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lifesyncpro.app.R
import com.lifesyncpro.app.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }
    var showAddTaskDialog by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        isLoading = false
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = when (selectedTab) {
                            0 -> stringResource(R.string.app_name)
                            1 -> stringResource(R.string.tasks_title)
                            2 -> stringResource(R.string.health_title)
                            3 -> stringResource(R.string.insights_title)
                            4 -> stringResource(R.string.profile_title)
                            else -> stringResource(R.string.app_name)
                        },
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(stringResource(R.string.nav_home)) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Task, contentDescription = "Tasks") },
                    label = { Text(stringResource(R.string.nav_tasks)) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Health") },
                    label = { Text(stringResource(R.string.nav_health)) },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Psychology, contentDescription = "Insights") },
                    label = { Text(stringResource(R.string.nav_insights)) },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text(stringResource(R.string.nav_profile)) },
                    selected = selectedTab == 4,
                    onClick = { selectedTab = 4 }
                )
            }
        },
        floatingActionButton = {
            if (selectedTab == 1) {
                FloatingActionButton(
                    onClick = { showAddTaskDialog = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task")
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AnimatedVisibility(
                visible = isLoading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LoadingScreen()
            }

            AnimatedVisibility(
                visible = !isLoading,
                enter = slideInAnimation(),
                exit = slideOutAnimation()
            ) {
                when (selectedTab) {
                    0 -> HomeScreen()
                    1 -> TasksScreen()
                    2 -> HealthScreen()
                    3 -> InsightsScreen()
                    4 -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            WelcomeCard()
        }
        
        item {
            DailySummaryCard()
        }

        item {
            UpcomingActivitiesCard()
        }

        item {
            AiSuggestionsCard()
        }
    }
}

@Composable
private fun TasksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Task Categories
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TaskCategoryCard("Work", Icons.Default.Work, 5)
            }
            item {
                TaskCategoryCard("Personal", Icons.Default.Person, 3)
            }
            item {
                TaskCategoryCard("Health", Icons.Default.Favorite, 2)
            }
            item {
                TaskCategoryCard("Social", Icons.Default.Group, 1)
            }
        }
    }
}

@Composable
private fun HealthScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            DailyHealthSummaryCard()
        }
        
        item {
            WorkoutTrackerCard()
        }
        
        item {
            NutritionCard()
        }
        
        item {
            SleepTrackerCard()
        }
    }
}

@Composable
private fun InsightsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProductivityInsightsCard()
        }
        
        item {
            HealthRecommendationsCard()
        }
        
        item {
            HabitAnalysisCard()
        }
        
        item {
            AiAssistantSummaryCard()
        }
    }
}

@Composable
private fun DailySummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.daily_summary),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Task, value = "8/12", label = "Tasks")
                StatItem(icon = Icons.Default.DirectionsRun, value = "5,234", label = "Steps")
                StatItem(icon = Icons.Default.WaterDrop, value = "6/8", label = "Water")
            }
        }
    }
}

@Composable
private fun UpcomingActivitiesCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Upcoming Activities",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActivityItem(
                icon = Icons.Default.FitnessCenter,
                title = "Morning Workout",
                subtitle = "7:00 AM - 8:00 AM",
                progress = 0f
            )
            ActivityItem(
                icon = Icons.Default.Work,
                title = "Team Meeting",
                subtitle = "9:00 AM - 10:00 AM",
                progress = 0f
            )
        }
    }
}

@Composable
private fun AiSuggestionsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "AI Suggestions",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            SuggestionItem(
                icon = Icons.Default.WbSunny,
                text = "Consider going for a walk during your lunch break"
            )
            SuggestionItem(
                icon = Icons.Default.WaterDrop,
                text = "You're behind on water intake today"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskCategoryCard(title: String, icon: ImageVector, count: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Badge(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(count.toString())
            }
        }
    }
}

@Composable
private fun SuggestionItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Surface(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(60.dp)),
            color = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Profile Info
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileItem(Icons.Default.Person, "John Doe")
                ProfileItem(Icons.Default.Email, "john.doe@example.com")
                ProfileItem(Icons.Default.Phone, "+1 234 567 890")
                ProfileItem(Icons.Default.LocationOn, "New York, USA")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Settings
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Settings",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                SettingsItem(Icons.Default.Notifications, "Notifications")
                SettingsItem(Icons.Default.Lock, "Privacy")
                SettingsItem(Icons.Default.Settings, "General")
                SettingsItem(Icons.Default.Help, "Help & Support")
            }
        }
    }
}

@Composable
private fun ProfileItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun SettingsItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge)
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Navigate",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) {
            ShimmerLoadingCard()
        }
    }
}

@Composable
private fun WelcomeCard() {
    PulsingCard(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome to ${stringResource(R.string.app_name)}!",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Track your daily progress and stay healthy",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                AnimatedButton(
                    text = "Start Activity",
                    onClick = { /* Handle click */ }
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DailyHealthSummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.daily_health_summary),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.FitnessCenter, value = "75%", label = "Activity")
                StatItem(icon = Icons.Default.WaterDrop, value = "80%", label = "Water")
                StatItem(icon = Icons.Default.Favorite, value = "90%", label = "Health")
            }
        }
    }
}

@Composable
private fun WorkoutTrackerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.workout_tracker),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            ActivityItem(
                icon = Icons.Default.FitnessCenter,
                title = "Morning Workout",
                subtitle = "7:00 AM - 8:00 AM",
                progress = 0.8f
            )
        }
    }
}

@Composable
private fun NutritionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.nutrition),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Restaurant, value = "2,500", label = "Calories")
                StatItem(icon = Icons.Default.LocalDining, value = "50%", label = "Carbs")
                StatItem(icon = Icons.Default.LocalDining, value = "30%", label = "Protein")
            }
        }
    }
}

@Composable
private fun SleepTrackerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.sleep_tracker),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Bedtime, value = "7 hours", label = "Sleep")
                StatItem(icon = Icons.Default.Bedtime, value = "224", label = "Minutes")
            }
        }
    }
}

@Composable
private fun ProductivityInsightsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.productivity_insights),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Work, value = "8/12", label = "Tasks")
                StatItem(icon = Icons.Default.Psychology, value = "75%", label = "Productivity")
            }
        }
    }
}

@Composable
private fun HealthRecommendationsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.health_recommendations),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.FitnessCenter, value = "75%", label = "Activity")
                StatItem(icon = Icons.Default.Favorite, value = "90%", label = "Health")
            }
        }
    }
}

@Composable
private fun HabitAnalysisCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.habit_analysis),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Favorite, value = "30", label = "Habits")
                StatItem(icon = Icons.Default.Psychology, value = "75%", label = "Completion")
            }
        }
    }
}

@Composable
private fun AiAssistantSummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.ai_assistant_summary),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(icon = Icons.Default.Psychology, value = "75%", label = "Accuracy")
                StatItem(icon = Icons.Default.Psychology, value = "90%", label = "Completion")
            }
        }
    }
}

@Composable
private fun StatItem(
    icon: ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            value,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun ActivityItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    progress: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall
            )
        }
        AnimatedProgressIndicator(
            progress = progress,
            modifier = Modifier.width(60.dp)
        )
    }
} 