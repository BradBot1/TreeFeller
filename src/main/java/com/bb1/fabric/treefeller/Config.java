package com.bb1.fabric.treefeller;

import com.bb1.api.config.Storable;
import com.google.gson.JsonArray;

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
public class Config extends com.bb1.api.config.Config {

	public Config() { super("treefeller"); }
	
	@Storable public int maxFellingSize = 100;
	
	@Storable public JsonArray fellableBlocks = getFellables();
	
	@Storable(key = "registerAllAxesAsFellableItems") public boolean useAxes = true;
	
	@Storable public JsonArray fellableItems = new JsonArray();
	
	private JsonArray getFellables() {
		JsonArray jsonArray = new JsonArray();
		jsonArray.add("minecraft:oak_log");
		jsonArray.add("minecraft:spruce_log");
		jsonArray.add("minecraft:birch_log");
		jsonArray.add("minecraft:jungle_log");
		jsonArray.add("minecraft:acacia_log");
		jsonArray.add("minecraft:dark_oak_log");
		jsonArray.add("minecraft:warped_stem");
		jsonArray.add("minecraft:crimson_stem");
		return jsonArray;
	}

}
