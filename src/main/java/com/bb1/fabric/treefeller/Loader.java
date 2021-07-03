package com.bb1.fabric.treefeller;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
public class Loader implements ModInitializer {
	
	private static final Set<Block> FELLABLE_BLOCKS = new HashSet<Block>();
	
	public static final void addFellable(Block block) { FELLABLE_BLOCKS.add(block); }
	
	public static final boolean isFellable(Block block) { return FELLABLE_BLOCKS.contains(block); }
	
	private static final Set<Item> FELLABLE_ITEMS = new HashSet<Item>();
	
	public static final void makeUsable(Item item) { FELLABLE_ITEMS.add(item); }
	
	public static final boolean isUsable(ItemStack itemStack) { return (CONFIG.useAxes && (itemStack.getItem() instanceof AxeItem)) || (CONFIG.fellableItems.contains(null)); }
	
	private static final Config CONFIG = new Config();
	
	public static final Config getConfig() { return CONFIG; }
	
	@Override
	public void onInitialize() {
		CONFIG.load();
		CONFIG.save();
		for (JsonElement jsonElement : CONFIG.fellableBlocks) {
			if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
				FELLABLE_BLOCKS.add(Registry.BLOCK.get(new Identifier(jsonElement.getAsString())));
			}
		}
		for (JsonElement jsonElement : CONFIG.fellableItems) {
			if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
				FELLABLE_ITEMS.add(Registry.ITEM.get(new Identifier(jsonElement.getAsString())));
			}
		}
	}
	
}
