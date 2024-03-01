plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
    }
}

dependencies {
    implementation(project(":domain"))

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    val hiltVersion = "2.50"

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    val navigationVersion = "2.7.7"

    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    implementation("com.google.code.gson:gson:2.10.1")

}

kapt {
    correctErrorTypes = true
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val navigationVersion = "2.7.7"

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}