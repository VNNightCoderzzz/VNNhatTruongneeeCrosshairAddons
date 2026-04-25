package net.vnnhattruongneee.crosshairaddons.mixin

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.DeltaTracker
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.client.renderer.RenderPipelines
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Unique
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(Gui::class)
abstract class CrosshairMixin {

    @Unique
    private val WHITE_CROSS_TEX = Identifier.parse("vnnhattruongneeecrosshairaddons:textures/gui/crosshair.png")
    
    @Unique
    private val RED_CROSS_TEX = Identifier.parse("vnnhattruongneeecrosshairaddons:textures/gui/red_crosshair.png")


    @Inject(method = ["renderCrosshair"], at = [At("RETURN")], cancellable = true)
    private fun injectCrosshair(guiGraphics: GuiGraphics, deltaTracker: DeltaTracker, ci: CallbackInfo) {
        val minecraft = Minecraft.getInstance()
        
        

        if (minecraft.level == null || minecraft.player == null) return

        val player = minecraft.player!!
        val target = minecraft.hitResult
        var textureToRender = WHITE_CROSS_TEX


        if (target != null && target.type == HitResult.Type.ENTITY) {
            val entityHit = target as EntityHitResult
            val entity = entityHit.entity
            if (entity is Player && entity != player) {
                val reach = player.getAttributeValue(Attributes.ENTITY_INTERACTION_RANGE)
                if (player.distanceTo(entity) <= reach) {
                    textureToRender = RED_CROSS_TEX
                }
            }
        }

        val size = 16
        val x = (guiGraphics.guiWidth() - size) / 2
        val y = (guiGraphics.guiHeight() - size) / 2


        try {
            ci.cancel();
        } catch (e: Exception) {
            
        }

        try {
            
            guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                textureToRender,
                x, y,
                0f, 0f,
                size, size,
                size, size
            )
        } catch (e: Exception) {
            
        }
    }
}