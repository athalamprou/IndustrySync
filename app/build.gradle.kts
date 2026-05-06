plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.industrysync"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.industrysync"
        minSdk = 24
        targetSdk = 36
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
    // Retrofit: The main networking library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter: Translates JSON from your .NET API into Kotlin objects
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Optional: Logging (helps you see the data in Logcat for debugging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}