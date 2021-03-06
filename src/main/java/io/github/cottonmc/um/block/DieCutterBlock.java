package io.github.cottonmc.um.block;

import io.github.cottonmc.um.block.entity.DieCutterEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class DieCutterBlock extends AbstractMachineBlock implements BlockEntityProvider, InventoryProvider {

	public DieCutterBlock() {
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new DieCutterEntity();
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
		if (!world.isClient) {
			BlockEntity entity = world.getBlockEntity(pos);
			if (entity!=null && entity instanceof DieCutterEntity) {
				((DieCutterEntity)entity).pulse();
			} else {
				setStatus(world, pos, MachineStatus.ERROR);
			}
		}
	}

	@Override
	public SidedInventory getInventory(BlockState state, IWorld world, BlockPos pos) {
		BlockEntity be = world.getBlockEntity(pos);
		if (be!=null && be instanceof DieCutterEntity) {
			return ((DieCutterEntity)be).getInventory();
		} else {
			return null;
		}
	}
}