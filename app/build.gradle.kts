plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
  //  id ("kotlin-kapt")
   // id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
    id ("com.google.gms.google-services")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.navasc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.navasc"
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
    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.0")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-firestore:25.0.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
   // implementation("com.google.dagger:hilt-android:2.44")
    //kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    //implementation ("br.com.simplepass:loading-button-android:2.2.0")
    implementation("br.com.simplepass:loading-button-android:2.2.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("io.github.vejei.viewpagerindicator:viewpagerindicator:1.0.0-alpha.1")
    implementation ("com.shuhart.stepview:stepview:1.5.1")
    implementation ("androidx.fragment:fragment-ktx:1.3.0")



}
//kapt {
//    correctErrorTypes = true
//}