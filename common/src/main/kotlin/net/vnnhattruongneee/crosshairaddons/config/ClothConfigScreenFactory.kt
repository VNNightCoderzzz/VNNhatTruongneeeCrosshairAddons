package net.vnnhattruongneee.crosshairaddons.config

import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

/**
 * Builds the Cloth Config settings screen. Shared by both loaders:
 * Fabric wires it through ModMenu, NeoForge through its IConfigScreenFactory.
 */
object ClothConfigScreenFactory {

    fun create(parent: Screen?): Screen {
        val config = CrosshairConfig.get()

        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.literal("Crosshair Addons"))

        val entryBuilder = builder.entryBuilder()
        val general = builder.getOrCreateCategory(Component.literal("General"))

        general.addEntry(
            entryBuilder.startBooleanToggle(Component.literal("Enabled"), config.enabled)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Master switch for the custom crosshair."))
                .setSaveConsumer { config.enabled = it }
                .build()
        )

        general.addEntry(
            entryBuilder.startBooleanToggle(Component.literal("Highlight on reach"), config.highlightOnReach)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Turn the crosshair red when a player is within attack range."))
                .setSaveConsumer { config.highlightOnReach = it }
                .build()
        )

        general.addEntry(
            entryBuilder.startIntSlider(Component.literal("Crosshair size"), config.crosshairSize, 4, 64)
                .setDefaultValue(16)
                .setTooltip(Component.literal("Rendered crosshair size in pixels."))
                .setSaveConsumer { config.crosshairSize = it }
                .build()
        )

        builder.setSavingRunnable { config.save() }

        return builder.build()
    }
}
