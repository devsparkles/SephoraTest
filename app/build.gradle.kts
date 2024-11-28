plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Kotlin serialization plugin for type safe routes and navigation arguments
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "fr.kodesparkle.sephorademo"
    compileSdk = 35

    defaultConfig {
        applicationId = "fr.kodesparkle.sephorademo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://sephoraandroid.github.io/testProject/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://sephoraandroid.github.io/testProject/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)


    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // logging
    implementation(libs.timber)

    implementation(libs.androidx.recyclerview)


    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    testImplementation(libs.turbine)
    testImplementation(libs.mockito.core) // For mocking the repository
    testImplementation(libs.mockito.kotlin) // For mocking with Kotlin syntax
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlin.test)

    testImplementation(libs.koin.test)  // Koin test module
    testImplementation(libs.koin.core)
}