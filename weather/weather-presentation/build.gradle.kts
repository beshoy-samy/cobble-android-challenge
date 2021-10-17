import AppModules.withCoreModule
import Weather.withWeatherDomainModule

plugins {
    id(Build.Plugins.androidLibraryPlugin)
    kotlin(Build.Plugins.kotlinAndroidPlugin)
    kotlin(Build.Plugins.kotlinKaptPlugin)
    id(Build.Plugins.hiltAndroidPlugin)
}

android {
    compileSdk = Config.AndroidProject.compileSdkVersion

    defaultConfig {
        minSdk = Config.AndroidProject.minSdkVersion
        targetSdk = Config.AndroidProject.targetSdkVersion
    }

    buildTypes {

        release {
            isMinifyEnabled = Config.isReleaseMinifyEnabled
        }

    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.javaVersion.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    withAndroidX()
    withLifeCycleKtx()
    withHilt()
    withNavigationComponent()
    withCoreModule()
    withWeatherDomainModule()
    withTestDependencies()
}

kapt {
    correctErrorTypes = true
}