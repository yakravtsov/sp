plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

fun extraInt(name: String): Int = rootProject.extra[name]?.toString()?.toInt()
    ?: error("Expected integer Gradle extra property: $name")

val compileSdkInt = extraInt("compileSdk")
val minSdkInt = extraInt("minSdk")
val targetSdkInt = extraInt("targetSdk")
val composeCompiler = rootProject.extra["composeCompiler"] as String

android {
    namespace = "com.example.yicameraprototype"
    compileSdk = compileSdkInt

    defaultConfig {
        applicationId = "com.example.yicameraprototype"
        minSdk = minSdkInt
        targetSdk = targetSdkInt
        versionCode = 1
        versionName = "0.2"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "channel"
    productFlavors {
        create("dev") {
            dimension = "channel"
            applicationIdSuffix = ".dev"
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    implementation("androidx.compose.ui:ui:1.7.1")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.1")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.media3:media3-ui:1.4.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
