# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep all model classes for Gson serialization
-keep class com.example.kidscoding.models.** { *; }

# Keep all data classes for Gson serialization
-keep class com.example.kidscoding.data.** { *; }

# Keep Lottie animation classes
-keep class com.airbnb.lottie.** { *; }