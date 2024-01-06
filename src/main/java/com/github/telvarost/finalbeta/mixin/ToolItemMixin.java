package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.item.tool.HatchetItem;
import net.minecraft.item.tool.PickaxeItem;
import net.minecraft.item.tool.ToolItem;
import net.minecraft.item.tool.ToolMaterial;
import net.minecraft.tile.Tile;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(ToolItem.class)
public class ToolItemMixin {

	private static final Tile[] PICKAXE_BLOCKS = new Tile[]{Tile.COBBLESTONE, Tile.DOUBLE_STONE_SLAB, Tile.STONE_SLAB, Tile.STONE,
			Tile.SANDSTONE, Tile.MOSSY_COBBLESTONE, Tile.IRON_ORE, Tile.BLOCK_IRON, Tile.COAL_ORE, Tile.BLOCK_GOLD, Tile.GOLD_ORE,
			Tile.DIAMOND_ORE, Tile.BLOCK_DIAMOND, Tile.ICE, Tile.NETHERRACK, Tile.LAPIS_LAZULI_ORE, Tile.LAPIS_LAZULI_BLOCK,
			// Here starts the list of blocks that are not affected by default
			Tile.STAIRS_STONE, Tile.REDSTONE_ORE, Tile.REDSTONE_ORE_LIT, Tile.DOOR_IRON, Tile.BRICK, Tile.FURNACE, Tile.FURNACE_LIT,
			Tile.DISPENSER, Tile.STONE_PRESSURE_PLATE, Tile.RAIL, Tile.DETECTOR_RAIL, Tile.GOLDEN_RAIL, Tile.PISTON, Tile.STICKY_PISTON};

	private static final Tile[] HATCHET_BLOCKS = new Tile[]{Tile.WOOD, Tile.BOOKSHELF, Tile.LOG, Tile.CHEST,
			// Here starts the list of blocks that are not affected by default
			Tile.STAIRS_WOOD, Tile.DOOR_WOOD, Tile.WOODEN_PRESSURE_PLATE, Tile.JUKEBOX, Tile.NOTEBLOCK, Tile.PUMPKIN, Tile.LIT_PUMPKIN,
			Tile.STANDING_SIGN, Tile.WALL_SIGN, Tile.TRAPDOOR, Tile.LADDER, Tile.WORKBENCH, Tile.FENCE};

	@Shadow
	public Tile[] field_2712;

	@Inject(method = "<init>(IILnet/minecraft/item/tool/ToolMaterial;[Lnet/minecraft/tile/Tile;)V", at = @At("TAIL"))
	public void init(int i, int j, ToolMaterial arg, Tile[] args, CallbackInfo ci) {

		ToolItem tool = ((ToolItem) (Object) this);
		if (tool instanceof PickaxeItem && ModConfig.FIX_PICKAXE_EFFECTIVENESS.get()) {
			this.field_2712 = PICKAXE_BLOCKS;
		} else if (tool instanceof HatchetItem && ModConfig.FIX_AXE_EFFECTIVENESS.get()) {
			this.field_2712 = HATCHET_BLOCKS;
		}
	}
}
