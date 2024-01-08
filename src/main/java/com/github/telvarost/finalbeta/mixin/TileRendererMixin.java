package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.block.GrassColour;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.block.BlockBase;

@Mixin(BlockRenderer.class)
public class TileRendererMixin {

	@Shadow
	private int textureOverride;

	// Grass block texture using side textures for top fix
	@Inject(method = "method_48", at = @At("HEAD"), cancellable = true)
	public void grassBlockRenderer(BlockBase arg, int i, float f, CallbackInfo ci) {
		// Normal Blocks are rendered using this the 0 type, we're using this
		// just as a safenet in case the tile is not grass
		int type = arg.getRenderType();
		if (arg.id == BlockBase.GRASS.id && type == 0) {
			Tessellator tess = Tessellator.INSTANCE;
			BlockRenderer renderer = (BlockRenderer) (Object) this;

			arg.method_1605();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tess.start();
			tess.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderBottomFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(0, i));
			tess.draw();

			// This bit controls the top texture specifically
			float r = (GrassColour.get(0.96, 0.44) >> 16 & 255) / 255.0F;
			float g = (GrassColour.get(0.96, 0.44) >> 8 & 255) / 255.0F;
			float b = (GrassColour.get(0.96, 0.44) & 255) / 255.0F;
			tess.start();
			tess.setNormal(0.0F, 1.0F, 0.0F);
			tess.colour(r * f, g * f, b * f);
			renderer.renderTopFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(1, i));
			tess.draw();

			tess.start();
			tess.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderEastFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(2, i));
			tess.draw();
			tess.start();
			tess.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderWestFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(3, i));
			tess.draw();
			tess.start();
			tess.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderNorthFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(4, i));
			tess.draw();
			tess.start();
			tess.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderSouthFace(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(5, i));
			tess.draw();
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			ci.cancel();
		}
	}

	// Torch rendering with a bottom texture fix
	@Inject(method = "renderTorchTilted", at = @At("HEAD"), cancellable = true)
	public void torchRenderer(BlockBase arg, double x, double y, double z, double w, double h, CallbackInfo ci) {
		Tessellator tess = Tessellator.INSTANCE;
		int tex = arg.getTextureForSide(0);
		if (this.textureOverride >= 0) {
			tex = this.textureOverride;
		}

		int var14 = (tex & 15) << 4;
		int var15 = tex & 240;
		float u = var14 / 256.0F;
		float u2 = (var14 + 15.99F) / 256.0F;
		float v = var15 / 256.0F;
		float v2 = (var15 + 15.99F) / 256.0F;
		double var20 = u + 0.02734375D;
		double var22 = v + 0.0234375D;
		double var24 = u + 0.03515625D;
		double var26 = v + 0.03125D;
		x += 0.5D;
		z += 0.5D;
		double var28 = x - 0.5D;
		double var30 = x + 0.5D;
		double var32 = z - 0.5D;
		double var34 = z + 0.5D;
		double var36 = 0.0625D;
		double var38 = 0.625D;

		tess.vertex(x + w * (1.0D - var38) - var36, y + var38, z + h * (1.0D - var38) - var36, var20, var22);
		tess.vertex(x + w * (1.0D - var38) - var36, y + var38, z + h * (1.0D - var38) + var36, var20, var26);
		tess.vertex(x + w * (1.0D - var38) + var36, y + var38, z + h * (1.0D - var38) + var36, var24, var26);
		tess.vertex(x + w * (1.0D - var38) + var36, y + var38, z + h * (1.0D - var38) - var36, var24, var22);

		tess.vertex(x - var36, y + 1.0D, var32, u, v);
		tess.vertex(x - var36 + w, y + 0.0D, var32 + h, u, v2);
		tess.vertex(x - var36 + w, y + 0.0D, var34 + h, u2, v2);
		tess.vertex(x - var36, y + 1.0D, var34, u2, v);

		tess.vertex(x + var36, y + 1.0D, var34, u, v);
		tess.vertex(x + w + var36, y + 0.0D, var34 + h, u, v2);
		tess.vertex(x + w + var36, y + 0.0D, var32 + h, u2, v2);
		tess.vertex(x + var36, y + 1.0D, var32, u2, v);

		tess.vertex(var28, y + 1.0D, z + var36, u, v);
		tess.vertex(var28 + w, y + 0.0D, z + var36 + h, u, v2);
		tess.vertex(var30 + w, y + 0.0D, z + var36 + h, u2, v2);
		tess.vertex(var30, y + 1.0D, z + var36, u2, v);

		tess.vertex(var30, y + 1.0D, z - var36, u, v);
		tess.vertex(var30 + w, y + 0.0D, z - var36 + h, u, v2);
		tess.vertex(var28 + w, y + 0.0D, z - var36 + h, u2, v2);
		tess.vertex(var28, y + 1.0D, z - var36, u2, v);

		double u3 = u + 0.02734375D;
		double v3 = v + 0.0434375D;
		double u4 = u + 0.03515625D;
		double v4 = v + 0.05125D;
		double w2 = 0.0625D;

		tess.vertex(x + w * 1.0D - w2, y, z + h * 1.0D + w2, u3, v3);
		tess.vertex(x + w * 1.0D - w2, y, z + h * 1.0D - w2, u3, v4);
		tess.vertex(x + w * 1.0D + w2, y, z + h * 1.0D - w2, u4, v4);
		tess.vertex(x + w * 1.0D + w2, y, z + h * 1.0D + w2, u4, v3);
		ci.cancel();
	}
}
