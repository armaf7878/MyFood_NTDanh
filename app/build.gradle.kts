plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myfood_ngothanhdanh"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myfood_ngothanhdanh"
        minSdk = 24
        targetSdk = 35
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0") //API
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.material:material:1.9.0")

    implementation(platform("com.google.firebase:firebase-bom:33.16.0")) //Firebasse
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth")
    implementation ("com.google.firebase:firebase-firestore")


    implementation ("com.github.bumptech.glide:glide:4.16.0") //Glide
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    implementation ("com.google.android.gms:play-services-maps:18.2.0") //google API
    implementation ("com.google.android.gms:play-services-location:21.0.1") //FusedLocationProviderClient lib

}