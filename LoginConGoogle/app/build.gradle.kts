plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.logincongoogle" // Replace with your actual package name
    compileSdk = 36 // Or your desired SDK version

    defaultConfig {
        applicationId = "com.example.logincongoogle" // Replace with your actual package name
        minSdk = 26 // Or your desired minimum SDK version
        targetSdk = 34 // Or your desired target SDK version
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
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dependencias CLAVE para el método clásico
    implementation(platform("com.google.firebase:firebase-bom:33.1.0")) // 1. BOM de Firebase
    implementation("com.google.firebase:firebase-auth-ktx")             // 2. Autenticación de Firebase
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation(libs.androidx.activity)  // 3. Google Sign-In (clásico)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
