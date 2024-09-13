package com.mubarak.wikinews.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mubarak.wikinews.R

// Set of Material typography styles to start with

val dmSansFamily = FontFamily(
    Font(R.font.dmsans_18pt_light, FontWeight.Light),
    Font(R.font.dmsans_18pt_regular, FontWeight.Normal),
    Font(R.font.dmsans_24pt_medium, FontWeight.Medium),
    Font(R.font.dmsans_18pt_semibold, FontWeight.Bold)
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = dmSansFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = dmSansFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = dmSansFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = dmSansFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = dmSansFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = dmSansFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = dmSansFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = dmSansFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = dmSansFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = dmSansFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = dmSansFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = dmSansFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = dmSansFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = dmSansFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = dmSansFamily),
)
