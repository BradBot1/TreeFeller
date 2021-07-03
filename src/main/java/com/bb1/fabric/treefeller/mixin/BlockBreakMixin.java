package com.bb1.fabric.treefeller.mixin;

import static com.bb1.fabric.treefeller.Loader.isFellable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.bb1.fabric.treefeller.Loader;

import io.netty.util.internal.IntegerHolder;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Copyright 2021 BradBot_1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License", "");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Mixin(ServerPlayerInteractionManager.class)
public class BlockBreakMixin {
	
	@Shadow @Final public ServerPlayerEntity player;
	
	@Inject(method = "finishMining", at=@At(value = "HEAD", shift = At.Shift.AFTER))
	private void init(BlockPos pos, PlayerActionC2SPacket.Action action, String reason, CallbackInfo info) {
		ItemStack item = this.player.getMainHandStack();
		if (item!=null && item.getItem() instanceof AxeItem && player.isSneaking()) {
			IntegerHolder integerHolder = new IntegerHolder();
			integerHolder.value = 0;
			recur(this.player, item, this.player.getEntityWorld(), pos, integerHolder, Loader.getConfig().maxFellingSize);
		}
	}
	
	private void recur(ServerPlayerEntity player, ItemStack tool, World world, BlockPos pos, IntegerHolder current, int maxDepth) {
		current.value++;
		if (current.value>maxDepth) return; // Don't break more than 100 at a time lol
		// Cache the Block as we use it for checking fellable and matching types
		Block block = world.getBlockState(pos).getBlock();
		// If given block isnt fellable ignore the request
		if (!isFellable(block)) return;
		// Check durability is in limits
		if (!tool.isDamageable() || (tool.getMaxDamage()-tool.getDamage())<=1) return;
		// If all checks pass then damage the tool and remove the given block
		tool.damage(1, player.getRandom(), player);
		world.breakBlock(pos, true);
		// Check all blocks around the given block
		for (int xOffset = -1; xOffset<=1; xOffset++) {
			for (int yOffset = -1; yOffset<=1; yOffset++) {
				for (int zOffset = -1; zOffset<=1; zOffset++) {
					if (xOffset==0&&yOffset==0&&zOffset==0) continue; // Don't check the given block
					BlockPos offsetPos = new BlockPos(pos.getX()+xOffset, pos.getY()+yOffset, pos.getZ()+zOffset);
					if (world.getBlockState(offsetPos).isOf(block)) {
						recur(player, tool, world, offsetPos, current, maxDepth);
					}
				}
			}
		}
	}
	
}
