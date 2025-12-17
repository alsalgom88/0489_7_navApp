plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.navapp"

    // ❌ compileSdk NO és un bloc, és una propietat integer
    // ❌ release(36) no existeix en cap API
    // ✔ Correcte:
    compileSdk = 34 // o 35 quan surti estable

    defaultConfig {
        applicationId = "com.example.navapp"
        minSdk = 29
        targetSdk = 34   // 36 no existeix encara
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
        viewBinding = true
    }
}

dependencies {

    // Navigation Component modern
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // AndroidX bàsic
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // dependencia per a tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
