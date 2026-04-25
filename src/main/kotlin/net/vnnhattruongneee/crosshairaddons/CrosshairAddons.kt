package net.vnnhattruongneee.crosshairaddons

import net.fabricmc.api.ClientModInitializer
import net.vnnhattruongneee.vnnhattruongneeelib.VNNhatTruongneeeLib;

class CrosshairAddons : ClientModInitializer {
    override fun onInitializeClient() {
        VNNhatTruongneeeLib.registerMod("vnnhattruongneeecrosshairaddons", "VNNhatTruongneee's Crosshair Addons", "0.10.3");
    }
}