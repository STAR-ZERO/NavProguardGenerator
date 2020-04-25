plugins {
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.11.0"
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:3.6.3")
}

group = "com.star-zero.gradle"
version = "1.0.0"

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

gradlePlugin {
    plugins {
        create("navProguardGenerator") {
            id = "com.star-zero.gradle.nav-proguard-generator"
            implementationClass = "com.star_zero.gradle.nav_proguard_generator.NavProguardGeneratorPlugin"
        }
    }
}

// How to publish
// $ ./gradlew clean publishPlugins -Pgradle.publish.key=<key> -Pgradle.publish.secret=<secret>
pluginBundle {
    website = "https://github.com/STAR-ZERO/NavProguardGenerator"
    vcsUrl = "https://github.com/STAR-ZERO/NavProguardGenerator.git"
    description = "Generate proguard rule files for Android Jetpack Navigation Component"

    (plugins) {
        "navProguardGenerator" {
            displayName = "Gradle Greeting plugin"
            tags = listOf("Android", "Jetpack")
        }
    }
}
