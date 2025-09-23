plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    alias(libs.plugins.google.gms.google.services)
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.atlmovie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.atlmovie"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase
    implementation(libs.firebase.auth)

    // Navigation
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    // CircleImageView
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // lottie
    implementation ("com.airbnb.android:lottie:6.6.7")
    implementation ("com.airbnb.android:lottie:3.4.0")

    //Dots Indicator
//    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("com.tbuonomo:dotsindicator:4.3")

    // Dependency Injection Koin
    implementation ("io.insert-koin:koin-android:3.4.2")

    // Material Design
    implementation ("com.google.android.material:material:1.13.0")

    // Room
    implementation ("androidx.room:room-runtime:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")
}