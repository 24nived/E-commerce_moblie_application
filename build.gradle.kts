// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
  //  id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("com.android.library") version "7.4.2" apply false
}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("com.android.tools.build:gradle:4.2.2")
    }
}