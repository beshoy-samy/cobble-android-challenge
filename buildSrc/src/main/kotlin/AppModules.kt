import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object AppModules {

    fun DependencyHandlerScope.withNetworkModule() =
        add("api", project(":network"))

    fun DependencyHandlerScope.withCoreModule() =
        add("implementation", project(":core"))
}

object Weather {

    fun DependencyHandlerScope.withWeatherDomainModule() =
        add("implementation", project(":weather:weather-domain"))

    fun DependencyHandlerScope.withWeatherModelModule() =
        add("implementation", project(":weather:weather-model"))

    fun DependencyHandlerScope.withWeatherPresentationModule() =
        add("implementation", project(":weather:weather-presentation"))

    fun DependencyHandlerScope.withWeatherModule() {
        withWeatherDomainModule()
        withWeatherModelModule()
        withWeatherPresentationModule()
    }

}