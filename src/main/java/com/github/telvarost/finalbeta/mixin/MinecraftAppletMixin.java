package com.github.telvarost.finalbeta.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftApplet;

@Mixin(MinecraftApplet.class)
public class MinecraftAppletMixin {

    @Redirect(method = "init", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;isApplet:Z"))
    private void finalBeta_disableIsApplet(Minecraft minecraft, boolean value) {
    }
}