import json
import os

MOD_ID = "pebblesandtwigs"
OUT = "gen_caves"
os.makedirs(f"{OUT}/worldgen/placed_feature", exist_ok=True)

def save(name, feature, count, min_y, max_y):
    data = {
        "feature": f"{MOD_ID}:{feature}",
        "placement": [
            {"type": "minecraft:count", "count": count},
            {"type": "minecraft:in_square"},
            {
                "type": "minecraft:height_range",
                "height": {
                    "type": "minecraft:uniform",
                    "min_inclusive": {"absolute": min_y},
                    "max_inclusive": {"absolute": max_y}
                }
            },
            {"type": "minecraft:biome"},
            {
                "type": "minecraft:random_offset",
                "xz_spread": {
                    "type": "minecraft:trapezoid",
                    "min": -3,
                    "max": 3,
                    "plateau": 0  # FIX: plateau is mandatory for trapezoid
                },
                "y_spread": {
                    "type": "minecraft:trapezoid",
                    "min": -1,
                    "max": 1,
                    "plateau": 0  # FIX: plateau is mandatory for trapezoid
                }
            },
            {
                "type": "minecraft:block_predicate_filter",
                "predicate": {
                    "type": "minecraft:all_of",
                    "predicates": [
                        {"type": "minecraft:matching_blocks", "blocks": "minecraft:air"},
                        {"type": "minecraft:has_sturdy_face", "offset": [0, -1, 0], "direction": "up"}
                    ]
                }
            }
        ]
    }
    with open(f"{OUT}/worldgen/placed_feature/{name}.json", 'w') as f:
        json.dump(data, f, indent=2)

# --- EXECUTION ---

# NORMAL CAVES (Y 0 to 320)
save("stone_pebble_cave_king", "stone_pebble", 1024, 0, 320)
for s in ["andesite", "diorite", "granite"]:
    save(f"{s}_pebble_cave_secondary", f"{s}_pebble", 768, 0, 320)
save("tuff_pebble_cave_uncommon", "tuff_pebble", 256, 0, 320)
save("deepslate_pebble_cave_uncommon", "deepslate_pebble", 512, 0, 320)

# DEEP CAVES (Y -64 to 0)
save("deepslate_pebble_deep_king", "deepslate_pebble", 1024, -64, 0)
save("tuff_pebble_deep_king", "tuff_pebble", 768, -64, 0)
save("stone_pebble_deep_common", "stone_pebble", 512, -64, 0)
for s in ["andesite", "diorite", "granite"]:
    save(f"{s}_pebble_deep_uncommon", f"{s}_pebble", 256, -64, 0)

print("Cave JSONs generated with mandatory plateau keys.")