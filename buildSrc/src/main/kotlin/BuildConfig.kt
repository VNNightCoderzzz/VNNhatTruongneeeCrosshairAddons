/**
 * Centralized build-time version constants shared across all loader subprojects.
 * Referenced from the fabric / neoforge build scripts as `BuildConfig.<NAME>`.
 */
object BuildConfig {
    const val MINECRAFT_VERSION = "1.21.11"

    // Fabric
    const val FABRIC_LOADER_VERSION = "0.19.3"
    const val FABRIC_API_VERSION = "0.141.4+1.21.11"
    const val FABRIC_KOTLIN_VERSION = "1.11.0+kotlin.2.0.0"

    // Mixin runtime required by fabric-loader 0.19.3 (see fabric-installer.json inside
    // the loader jar). Loom otherwise resolves an older 0.16.x which is missing
    // org.spongepowered.asm.service.IAdviceProvider and crashes on launch.
    const val SPONGE_MIXIN_VERSION = "0.17.3+mixin.0.8.7"

    // NeoForge (matching Minecraft 1.21.11)
    const val NEOFORGE_VERSION = "21.11.45"

    // Cloth Config (in-game config screen) + ModMenu integration
    const val CLOTH_CONFIG_VERSION = "21.11.153"
    const val MODMENU_VERSION = "17.0.0"
}
