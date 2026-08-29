package net.vnnhattruongneee.crosshairaddons.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.vnnhattruongneee.crosshairaddons.config.ClothConfigScreenFactory

/**
 * Exposes the Cloth Config screen to ModMenu's mod list "config" button.
 */
class ModMenuIntegration : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent -> ClothConfigScreenFactory.create(parent) }
    }
}
