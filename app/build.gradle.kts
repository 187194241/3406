plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.a3406"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a3406"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0") // For Kotlin extensions
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0") // For lifecycle management

    implementation("androidx.compose.ui:ui:1.4.0") // Jetpack Compose UI
    implementation("androidx.compose.material3:material3:1.0.0") // Material 3 for Compose
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0") // Preview for Compose
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.navigation:navigation-compose:2.5.0")// Foundation for Compose
    implementation("androidx.activity:activity-compose:1.7.0") // Compose integration for Activity

    testImplementation("junit:junit:4.13.2") // For unit testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // For Android testing
    androidTestImplementation("androidx.espresso:espresso-core:3.5.1") // For Espresso testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0") // Compose UI Testing

    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0") // UI tooling for debugging
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.0") // Manifest for testing

    // Additional dependencies as required
}
