plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinSymbolProcessing)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.compose.compiler)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.mubarak.wikinews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mubarak.wikinews"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {

        // enabling desugared library for agp 4.0.0+ to support java 8+ api feature
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Desugaring Library
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Core UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Navigation Suite Scaffold
    implementation(libs.material3.adaptive.navigation.suite)

    // Material Icons Extended
    implementation(libs.androidx.material.icons.extended)

    // AAC
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Dagger-Hilt (Dependency Injection)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Ktor-Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android) // Engine

    // Ktor Plugins
    implementation(libs.ktor.client.auth) // Auth
    implementation(libs.ktor.client.logging) // Logging (Debug feature only)
    implementation(libs.ktor.client.content.negotiation) // Serialization
    implementation(libs.ktor.serialization.kotlinx.json)

    // Coil Compose
    implementation(libs.coil.compose)

    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)

    // Design System
    implementation(libs.androidx.material3)

    // Debug Features
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Unit Test
    testImplementation(libs.junit)

    // Instrumental Test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
}