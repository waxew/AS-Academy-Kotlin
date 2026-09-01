plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.asdevelopers.academy.kotlin"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.asdevelopers.academy.kotlin"
        minSdk = 23
        targetSdk = 37
        // Monotonic versionCode preserves Android update compatibility.
        versionCode = 12000
        versionName = "1.2.0"
    }

    // Release signing is intentionally external to source control.
    val releaseStoreFile = providers.environmentVariable("AS_RELEASE_STORE_FILE").orNull
    val releaseStorePassword = providers.environmentVariable("AS_RELEASE_STORE_PASSWORD").orNull
    val releaseKeyAlias = providers.environmentVariable("AS_RELEASE_KEY_ALIAS").orNull
    val releaseKeyPassword = providers.environmentVariable("AS_RELEASE_KEY_PASSWORD").orNull
    val hasReleaseSigning = listOf(
        releaseStoreFile,
        releaseStorePassword,
        releaseKeyAlias,
        releaseKeyPassword
    ).all { !it.isNullOrBlank() }

    signingConfigs {
        if (hasReleaseSigning) {
            create("release") {
                storeFile = file(requireNotNull(releaseStoreFile))
                storePassword = releaseStorePassword
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword
            }
        }
    }

    buildTypes {
        getByName("debug") { isDebuggable = true }
        getByName("release") {
            isMinifyEnabled = false
            if (hasReleaseSigning) signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].assets.srcDirs("src/main/assets")
}

dependencies {
    // MainUi exposes the shared Academy presentation surface and transitively exposes Core APIs.
    implementation("com.asdevelopers.academy:main-ui")
    implementation("androidx.core:core-ktx:1.19.0")
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation(platform("androidx.compose:compose-bom:2026.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
