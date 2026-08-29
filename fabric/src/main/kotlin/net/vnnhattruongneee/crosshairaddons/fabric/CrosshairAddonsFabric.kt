package net.vnnhattruongneee.crosshairaddons.fabric

import net.fabricmc.api.ClientModInitializer
import com.mojang.logging.LogUtils

class CrosshairAddonsFabric : ClientModInitializer {
    private val logger = LogUtils.getLogger()
    override fun onInitializeClient() {
        logger.info("VNNhatTruongneee's Crosshair Addons Running On Fabric")
    }
}