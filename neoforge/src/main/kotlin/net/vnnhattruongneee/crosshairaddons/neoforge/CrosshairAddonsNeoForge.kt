package net.vnnhattruongneee.crosshairaddons.neoforge

import com.mojang.logging.LogUtils
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import net.vnnhattruongneee.crosshairaddons.config.ClothConfigScreenFactory

/**
 * NeoForge client entrypoint. Registers the Cloth Config screen with the
 * mod list "Config" button via IConfigScreenFactory.
 */
@Mod(value = "vnnhattruongneeecrosshairaddons", dist = [Dist.CLIENT])
class CrosshairAddonsNeoForge(container: ModContainer) {
    private val logger = LogUtils.getLogger()

    init {
        logger.info("VNNhatTruongneee's Crosshair Addons Running On NeoForge")

        container.registerExtensionPoint(
            IConfigScreenFactory::class.java,
            IConfigScreenFactory { _, parent -> ClothConfigScreenFactory.create(parent) }
        )
    }
}
