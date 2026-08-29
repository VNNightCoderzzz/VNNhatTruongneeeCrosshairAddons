plugins {
    id("fabric-loom")
    kotlin("jvm") // Đảm bảo có plugin Kotlin để compile code .kt
}

repositories {
    maven {
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
}

loom {
    runs.named("client") {
        client()
        configName = "fabric - Client"
        runDir = "../run"
        appendProjectPathToConfigName = false
        ideConfigGenerated(true)
    }
    
    @Suppress("UnstableApiUsage")
    mixin.useLegacyMixinAp.set(true)
}

dependencies {
    minecraft("com.mojang:minecraft:${BuildConfig.MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings()) // Dùng Official Mappings theo cấu hình của bro

    // Fabric Loader & API
    implementation("net.fabricmc:fabric-loader:${BuildConfig.FABRIC_LOADER_VERSION}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${BuildConfig.FABRIC_API_VERSION}")

    // Thư viện hỗ trợ Kotlin cho môi trường Fabric
    modImplementation("net.fabricmc:fabric-language-kotlin:${BuildConfig.FABRIC_KOTLIN_VERSION}")

    // Cloth Config (in-game settings screen) + ModMenu integration
    modImplementation("me.shedaniel.cloth:cloth-config-fabric:${BuildConfig.CLOTH_CONFIG_VERSION}") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation("com.terraformersmc:modmenu:${BuildConfig.MODMENU_VERSION}")

    
    // Kết nối lấy code chung từ thư mục common
    implementation(project(":common", configuration = "namedElements"))
}

// fabric-loader 0.19.3 ships against sponge-mixin 0.17.3, but loom's default
// resolution pulls 0.16.5 which lacks org.spongepowered.asm.service.IAdviceProvider
// and dies during MixinBootstrap. Force the version the loader expects.
configurations.all {
    resolutionStrategy {
        force("net.fabricmc:sponge-mixin:${BuildConfig.SPONGE_MIXIN_VERSION}")
    }
}

// Bundle the shared :common classes and resources into the final (remapped) Fabric jar.
// Without this the mixin + config classes live only on the compile classpath and never
// ship inside the mod jar, so nothing actually runs at runtime.
tasks.named<Jar>("jar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(project(":common").sourceSets["main"].output)
}

// Inject the Gradle project version into fabric.mod.json so the mod version is
// declared in a single place (root build.gradle.kts).
tasks.named<ProcessResources>("processResources") {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}