package com.lifesyncpro.app.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Insights") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // AI Summary Card
            AISummaryCard()

            // Productivity Insights
            ProductivityInsightsSection()

            // Health Recommendations
            HealthRecommendationsSection()

            // Habit Analysis
            HabitAnalysisSection()
        }
    }
}

@Composable
fun AISummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    Icons.Filled.Psychology,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "AI Assistant Summary",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Text(
                text = "Based on your recent activities and patterns, here are personalized insights to help you optimize your daily routine.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun ProductivityInsightsSection() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Productivity Insights",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                InsightItem(
                    icon = Icons.Filled.TrendingUp,
                    title = "Peak Performance Times",
                    description = "Your productivity peaks between 9 AM and 11 AM. Schedule important tasks during this window."
                )
                InsightItem(
                    icon = Icons.Filled.Schedule,
                    title = "Task Completion Pattern",
                    description = "You complete 30% more tasks when they're scheduled before noon."
                )
                InsightItem(
                    icon = Icons.Filled.WorkHistory,
                    title = "Focus Sessions",
                    description = "Your average focus session lasts 45 minutes. Consider using the Pomodoro technique."
                )
            }
        }
    }
}

@Composable
fun HealthRecommendationsSection() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Health Recommendations",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                InsightItem(
                    icon = Icons.Filled.DirectionsRun,
                    title = "Activity Level",
                    description = "Increase your daily steps by 2000 to reach the recommended 10,000 steps."
                )
                InsightItem(
                    icon = Icons.Filled.Bedtime,
                    title = "Sleep Pattern",
                    description = "Your sleep quality improves when you go to bed before 10:30 PM."
                )
                InsightItem(
                    icon = Icons.Filled.WaterDrop,
                    title = "Hydration",
                    description = "You're meeting 80% of your daily water intake goal. Keep it up!"
                )
            }
        }
    }
}

@Composable
fun HabitAnalysisSection() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Habit Analysis",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                HabitItem(
                    title = "Morning Routine",
                    consistency = 85,
                    trend = "Improving"
                )
                HabitItem(
                    title = "Exercise",
                    consistency = 70,
                    trend = "Stable"
                )
                HabitItem(
                    title = "Meditation",
                    consistency = 60,
                    trend = "Needs Focus"
                )
            }
        }
    }
}

@Composable
fun InsightItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun HabitItem(
    title: String,
    consistency: Int,
    trend: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Consistency: $consistency%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Text(
            text = trend,
            style = MaterialTheme.typography.bodyMedium,
            color = when (trend) {
                "Improving" -> MaterialTheme.colorScheme.primary
                "Stable" -> MaterialTheme.colorScheme.secondary
                else -> MaterialTheme.colorScheme.error
            },
            textAlign = TextAlign.End,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
} 