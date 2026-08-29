pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
        mavenCentral()
    }
}

rootProject.name = "vnnhattruongneeecrosshairaddons"

include("common", "fabric", "neoforge")