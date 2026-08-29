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

// AS Academy Core is consumed as a composite build so all shared learning,
// progress, quiz, exercise and content-engine logic stays centralized.
includeBuild("as-academy-core") {
    dependencySubstitution {
        // Map the stable Course Package coordinate used by course apps to
        // the actual :core module inside the AS-Academy-Core repository.
        substitute(module("com.asdevelopers.academy:core")).using(project(":core"))
    }
}
