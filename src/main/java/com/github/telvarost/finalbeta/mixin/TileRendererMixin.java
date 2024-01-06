package xyz.pixelatedw.finalbeta.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.colour.GrassColour;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import net.minecraft.tile.Tile;

@Mixin(TileRenderer.class)
public class TileRendererMixin {

	@Shadow
	private int field_83;

	// Grass block texture using side textures for top fix
	@Inject(method = "method_48", at = @At("HEAD"), cancellable = true)
	public void grassBlockRenderer(Tile arg, int i, float f, CallbackInfo ci) {
		// Normal Blocks are rendered using this the 0 type, we're using this
		// just as a safenet in case the tile is not grass
		int type = arg.method_1621();
		if (arg.id == Tile.GRASS.id && type == 0) {
			Tessellator tess = Tessellator.INSTANCE;
			TileRenderer renderer = (TileRenderer) (Object) this;

			arg.method_1605();
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tess.start();
			tess.method_1697(0.0F, -1.0F, 0.0F);
			renderer.method_46(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(0, i));
			tess.draw();

			// This bit controls the top texture specifically
			float r = (GrassColour.getColour(0.96, 0.44) >> 16 & 255) / 255.0F;
			float g = (GrassColour.getColour(0.96, 0.44) >> 8 & 255) / 255.0F;
			float b = (GrassColour.getColour(0.96, 0.44) & 255) / 255.0F;
			tess.start();
			tess.method_1697(0.0F, 1.0F, 0.0F);
			tess.colour(r * f, g * f, b * f);
			renderer.method_55(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(1, i));
			tess.draw();

			tess.start();
			tess.method_1697(0.0F, 0.0F, -1.0F);
			renderer.method_61(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(2, i));
			tess.draw();
			tess.start();
			tess.method_1697(0.0F, 0.0F, 1.0F);
			renderer.method_65(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(3, i));
			tess.draw();
			tess.start();
			tess.method_1697(-1.0F, 0.0F, 0.0F);
			renderer.method_67(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(4, i));
			tess.draw();
			tess.start();
			tess.method_1697(1.0F, 0.0F, 0.0F);
			renderer.method_69(arg, 0.0D, 0.0D, 0.0D, arg.getTextureForSide(5, i));
			tess.draw();
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			ci.cancel();
		}
	}

	// Torch rendering with a bottom texture fix
	@Inject(method = "method_45", at = @At("HEAD"), cancellable = true)
	public void torchRenderer(Tile arg, double x, double y, double z, double w, double h, CallbackInfo ci) {
		Tessellator tess = Tessellator.INSTANCE;
		int tex = arg.getTextureForSide(0);
		if (this.field_83 >= 0) {
			tex = this.field_83;
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
