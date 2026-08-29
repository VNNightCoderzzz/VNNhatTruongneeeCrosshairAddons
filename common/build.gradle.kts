plugins {
    id("fabric-loom")
    kotlin("jvm")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.11")
    mappings(loom.officialMojangMappings())

    compileOnly("org.spongepowered:mixin:0.8.7")
    compileOnly("com.google.guava:guava:33.2.1-jre")

    // Cloth Config API for the shared in-game config screen.
    // compileOnly + loom remap so the shared factory compiles against official mappings;
    // the actual cloth jar is provided at runtime by each loader module.
    modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:${BuildConfig.CLOTH_CONFIG_VERSION}") {
        exclude(group = "net.fabricmc")
        exclude(group = "net.fabricmc.fabric-api")
    }
}

loom {
    @Suppress("UnstableApiUsage")
    mixin.useLegacyMixinAp.set(true)
}