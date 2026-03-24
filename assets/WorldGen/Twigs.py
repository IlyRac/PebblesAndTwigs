import json
import os

MOD_ID = "pebblesandtwigs"
OUT = "gen_twigs"

os.makedirs(f"{OUT}/worldgen/configured_feature", exist_ok=True)
os.makedirs(f"{OUT}/worldgen/placed_feature", exist_ok=True)


def save_json(path, data):
    with open(path, 'w') as f:
        json.dump(data, f, indent=2)


def make_twig(name, rarity=None):
    # 1. CONFIGURED FEATURE (Random rotation)
    conf_data = {
        "type": "minecraft:simple_block",
        "config": {
            "to_place": {
                "type": "minecraft:weighted_state_provider",
                "entries": [
                    {"weight": 1, "data": {"Name": f"{MOD_ID}:{name}", "Properties": {"facing": d}}}
                    for d in ["north", "south", "east", "west"]
                ]
            }
        }
    }
    save_json(f"{OUT}/worldgen/configured_feature/{name}.json", conf_data)

    # 2. PLACED FEATURE (Surface Clumping)
    placement = [
        {"type": "minecraft:count", "count": 6},
        {"type": "minecraft:in_square"},
        {"type": "minecraft:heightmap", "heightmap": "MOTION_BLOCKING"},
        {"type": "minecraft:biome"},
        {
            "type": "minecraft:random_offset",
            "xz_spread": {"type": "minecraft:trapezoid", "min": -3, "max": 3, "plateau": 0},
            "y_spread": 0
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

    if rarity:
        placement.insert(0, {"type": "minecraft:rarity_filter", "chance": rarity})

    save_json(f"{OUT}/worldgen/placed_feature/{name}_placed.json",
              {"feature": f"{MOD_ID}:{name}", "placement": placement})


# --- EXECUTION ---
twigs = ["oak_twig", "birch_twig", "spruce_twig", "jungle_twig", "acacia_twig", "dark_oak_twig", "cherry_twig",
         "mangrove_twig", "pale_oak_twig"]

for t in twigs:
    # Oak is universal, so we give it a rarity filter to make it "common but not annoying"
    # The others spawn specifically in their biomes.
    rarity = 2 if t == "oak_twig" else None
    make_twig(t, rarity=rarity)

print("Twig JSONs generated with random rotation logic.")