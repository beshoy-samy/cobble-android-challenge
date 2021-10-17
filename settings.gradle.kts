dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CobbleChallenge"

include(":app")
include(":network")
include(":core")

include(":weather:weather-domain")
include(":weather:weather-model")
include(":weather:weather-presentation")
