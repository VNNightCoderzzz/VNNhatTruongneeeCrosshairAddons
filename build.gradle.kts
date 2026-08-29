plugins {
    kotlin("jvm") version "2.2.21" apply false
    id("fabric-loom") version "1.16-SNAPSHOT" apply false
    id("net.neoforged.moddev") version "2.0.141" apply false
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    version = "0.10.4"
    group = "net.vnnhattruongneee.crosshairaddons"

    repositories {
        mavenCentral()
        maven("https://maven.neoforged.net/releases/")
        maven("https://repo.spongepowered.org/repository/maven-public/")    
        maven("https://maven.shedaniel.me/")
        maven("https://maven.terraformersmc.com/releases/")
        maven {
            name = "Modrinth"
            url = uri("https://api.modrinth.com/maven")
            content {
                includeGroup("maven.modrinth")
            }
        }
    }

}