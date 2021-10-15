plugins {
    id(Build.Plugins.androidApplicationPlugin)
    kotlin(Build.Plugins.kotlinAndroidPlugin)
    kotlin(Build.Plugins.kotlinKaptPlugin)
    id(Build.Plugins.hiltAndroidPlugin)
}

android {

    compileSdk = Config.AndroidProject.compileSdkVersion

    defaultConfig {
        applicationId = "com.cobble.challenge"
        minSdk = Config.AndroidProject.minSdkVersion
        targetSdk = Config.AndroidProject.targetSdkVersion
        versionCode = Config.AndroidProject.versionCode
        versionName = Config.AndroidProject.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {
            isMinifyEnabled = Config.isReleaseMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    withHilt()
    withNavigationComponent()
    withTestDependencies()
}

kapt {
    correctErrorTypes = true
}