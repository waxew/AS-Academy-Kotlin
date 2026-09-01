pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AS-Academy-Kotlin"
include(":app")

// Shared runtime/learning engine.
includeBuild("as-academy-core") {
    dependencySubstitution {
        substitute(module("com.asdevelopers.academy:core")).using(project(":core"))
    }
}

// Shared presentation layer. CI/local checkout can provide the sibling repository
// through ACADEMY_MAIN_UI_DIR while the fallback keeps the expected workspace layout explicit.
val academyMainUiDir = System.getenv("ACADEMY_MAIN_UI_DIR") ?: "../AS-Academy-MainUi"
includeBuild(academyMainUiDir) {
    dependencySubstitution {
        substitute(module("com.asdevelopers.academy:main-ui")).using(project(":main-ui"))
    }
}
