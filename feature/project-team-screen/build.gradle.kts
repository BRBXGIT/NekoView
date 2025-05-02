plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    //Nav
    alias(libs.plugins.kotlin.serialization)
    //Ksp
    alias(libs.plugins.ksp)
    //Hilt
    alias(libs.plugins.hilt.android)
    //Compose
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.project_team"
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
}

dependencies {

    //Modules
    implementation(project(":core:design-system"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    //Nav
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    //Material 3
    implementation(libs.androidx.material3)
    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
}