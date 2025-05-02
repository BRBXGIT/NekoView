plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    //Compose
    alias(libs.plugins.kotlin.compose)
    //Ksp
    alias(libs.plugins.ksp)
    //Hilt
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.design_system"
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
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    //Material 3
    implementation(libs.androidx.material3)
    //Coil
    implementation(libs.coil.compose)
    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    //Lottie
    implementation(libs.lottie)
}