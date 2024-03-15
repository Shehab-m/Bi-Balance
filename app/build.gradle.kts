plugins {
    id("com.android.application")
    id("kotlin-kapt")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
//    id("androidx.navigation.safeargs.kotlin")
//    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.biBalance.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.biBalance.myapplication"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.material:material:1.6.3")
    implementation ("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material:material:1.6.3")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    // Navigation
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-work:1.1.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation ("androidx.navigation:navigation-compose:2.7.5")
    implementation ("androidx.navigation:navigation-common-ktx:2.7.5")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //Permission
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    // LottiAnimation
    implementation("com.airbnb.android:lottie-compose:6.0.1")
    // KotlinX
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    // Compose
    implementation ("androidx.compose.foundation:foundation:1.5.4")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    //okHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.11"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.11")

    implementation("org.apache.commons:commons-text:1.9")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    //Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
}