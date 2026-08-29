package net.vnnhattruongneee.crosshairaddons.config

import com.google.gson.GsonBuilder
import net.minecraft.client.Minecraft
import java.nio.file.Files
import java.nio.file.Path

/**
 * Runtime-editable configuration for the crosshair addon.
 *
 * Stored as JSON in `<gameDir>/config/vnnhattruongneeecrosshairaddons.json`.
 * This class is loader-agnostic: it only relies on vanilla Minecraft APIs so
 * it can be shared by both the Fabric and NeoForge entrypoints.
 */
class CrosshairConfig {
    /** Master switch. When false the mod does nothing and the vanilla crosshair is used. */
    var enabled: Boolean = true

    /** Rendered crosshair size in GUI pixels. */
    var crosshairSize: Int = 16

    /** When true, switches to the red crosshair while a player is within attack reach. */
    var highlightOnReach: Boolean = true

    fun clampValues() {
        crosshairSize = crosshairSize.coerceIn(4, 64)
    }

    /** Persists the current config to disk. */
    fun save() {
        try {
            val path = configPath()
            Files.createDirectories(path.parent)
            clampValues()
            Files.newBufferedWriter(path).use { writer ->
                GSON.toJson(this, writer)
            }
        } catch (e: Exception) {
            // Ignore write failures; config simply won't persist this session.
        }
    }

    companion object {
        private const val FILE_NAME = "vnnhattruongneeecrosshairaddons.json"
        private val GSON = GsonBuilder().setPrettyPrinting().create()

        @Volatile
        private var instance: CrosshairConfig? = null

        /** Lazily loads (or creates) the singleton config instance. */
        fun get(): CrosshairConfig {
            return instance ?: synchronized(this) {
                instance ?: load().also { instance = it }
            }
        }

        private fun configPath(): Path =
            Minecraft.getInstance().gameDirectory.toPath().resolve("config").resolve(FILE_NAME)

        private fun load(): CrosshairConfig {
            val path = configPath()
            return try {
                if (Files.exists(path)) {
                    Files.newBufferedReader(path).use { reader ->
                        (GSON.fromJson(reader, CrosshairConfig::class.java) ?: CrosshairConfig())
                    }.also { it.clampValues() }
                } else {
                    CrosshairConfig().also { it.save() }
                }
            } catch (e: Exception) {
                // Corrupt or unreadable config: fall back to defaults rather than crashing.
                CrosshairConfig()
            }
        }
    }
}
