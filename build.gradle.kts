plugins {
    kotlin("jvm") version "2.2.21"
    id("fabric-loom") version "1.16-SNAPSHOT"
    id("maven-publish")
}

version = "0.10.3"
group = "net.vnnhattruongneee.crosshairaddons"

base {
    archivesName.set("vnnhattruongneeecrosshairaddons")
}

repositories {
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.11")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.19.2")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.141.3+1.21.11")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.7+kotlin.2.2.21")
    modImplementation("maven.modrinth:vnnhattruongneeelib:0.10.3")
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}


java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}