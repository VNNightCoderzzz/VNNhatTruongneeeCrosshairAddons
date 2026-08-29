plugins {
    id("net.neoforged.moddev")
    kotlin("jvm")
}

neoForge {
    version = BuildConfig.NEOFORGE_VERSION

    runs {
        create("client") {
            client()
            gameDirectory = rootProject.file("run")
        }

        // Second client with a different player name + game dir, so two clients can be
        // launched side by side to test the player-reach crosshair highlight.
        // Task: gradlew :neoforge:runClientDev2
        create("clientDev2") {
            client()
            gameDirectory = rootProject.file("run2")
            ideName = "neoforge - Client (Dev2)"
            programArgument("--username")
            programArgument("Dev2")
        }
    }

    mods {
        create(rootProject.name) {
            sourceSet(sourceSets["main"])
            sourceSet(project(":common").sourceSets["main"])
        }
    }
}

dependencies {
    // Thư viện hỗ trợ ngôn ngữ Kotlin cho NeoForge (Fix lỗi Unresolved @Mod)
    // implementation("net.neoforged:neoforge-kotlin:1.0.0")

    // Cloth Config (in-game settings screen)
    implementation("me.shedaniel.cloth:cloth-config-neoforge:${BuildConfig.CLOTH_CONFIG_VERSION}")

    // Kết nối lấy code chung từ thư mục common
    implementation(project(":common"))
}

// Bundle the shared :common classes and resources into the NeoForge jar so the
// mixin + config classes actually ship inside the mod jar.
tasks.named<Jar>("jar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(project(":common").sourceSets["main"].output)
}

// Inject the Gradle project version into neoforge.mods.toml so the mod version is
// declared in a single place (root build.gradle.kts).
tasks.named<ProcessResources>("processResources") {
    inputs.property("version", project.version)
    filesMatching("META-INF/neoforge.mods.toml") {
        expand("version" to project.version)
    }
}