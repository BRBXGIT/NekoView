plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    //Compose
    alias(libs.plugins.kotlin.compose)
    //Nav
    alias(libs.plugins.kotlin.serialization)
    //Hilt
    alias(libs.plugins.hilt.android)
    //Ksp
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.navbar_screens"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //Modules
    implementation(project(":core:design-system"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":feature:common"))

    //Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    //Material 3
    implementation(libs.androidx.material3)
    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    //Nav
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    //Animated icons(compose animation graphics)
    implementation(libs.androidx.animation.graphics)
}