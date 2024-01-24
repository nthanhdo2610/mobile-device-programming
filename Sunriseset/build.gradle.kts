// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
}
buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}