import json
import os

MOD_ID = "pebblesandtwigs"
OUTPUT_DIR = "gen_surface_and_dimensions"

os.makedirs(f"{OUTPUT_DIR}/worldgen/configured_feature", exist_ok=True)
os.makedirs(f"{OUTPUT_DIR}/worldgen/placed_feature", exist_ok=True)


def save_json(path, data):
    with open(path, 'w') as f:
        json.dump(data, f, indent=2)


# ==========================================
# 1. CONFIGURED FEATURES
# ==========================================
def make_pebble_configured(stone_name, is_underwater=False):
    suffix = "_uw" if is_underwater else ""
    entries = []
    for i in range(1, 5):
        props = {"amount": str(i)}
        if is_underwater: props["waterlogged"] = "true"
        entries.append({"weight": 50 - (i * 10), "data": {"Name": f"{MOD_ID}:{stone_name}", "Properties": props}})
    save_json(f"{OUTPUT_DIR}/worldgen/configured_feature/{stone_name}{suffix}.json", {
        "type": "minecraft:simple_block",
        "config": {"to_place": {"type": "minecraft:weighted_state_provider", "entries": entries}}
    })


def make_twig_configured(twig_name):
    entries = [{"weight": 1, "data": {"Name": f"{MOD_ID}:{twig_name}", "Properties": {"facing": d}}} for d in
               ["north", "south", "east", "west"]]
    save_json(f"{OUTPUT_DIR}/worldgen/configured_feature/{twig_name}.json", {
        "type": "minecraft:simple_block",
        "config": {"to_place": {"type": "minecraft:weighted_state_provider", "entries": entries}}
    })


# ==========================================
# 2. PLACED FEATURES
# ==========================================
def make_placed(file_name, feature_ref, count, rarity=None, style="clump", mode="surface"):
    placement = []

    # Only apply rarity if provided (Fixes the low Nether rate)
    if rarity:
        placement.append({"type": "minecraft:rarity_filter", "chance": rarity})

    if style == "scatter":
        # --- SCATTER STYLE (For Nether/End/Twigs) ---
        # Count is FIRST. This picks 'count' separate, totally random points in the chunk.
        placement.append({"type": "minecraft:count", "count": count})
        placement.append({"type": "minecraft:in_square"})

        if mode == "nether":
            # Cap at 117 to prevent spawning on top of the bedrock roof
            placement.append({"type": "minecraft:height_range",
                              "height": {"type": "minecraft:uniform", "min_inclusive": {"absolute": 0},
                                         "max_inclusive": {"absolute": 117}}})
        else:
            placement.append({"type": "minecraft:heightmap", "heightmap": "MOTION_BLOCKING"})

        placement.append({"type": "minecraft:biome"})

        # Soft offset to make them look wild and disconnected
        placement.append({
            "type": "minecraft:random_offset",
            "xz_spread": {"type": "minecraft:trapezoid", "min": -4, "max": 4, "plateau": 0},
            "y_spread": {"type": "minecraft:trapezoid", "min": -1, "max": 1, "plateau": 0}
        })

    else:
        # --- CLUMP STYLE (For Surface/Underwater) ---
        # Pick one spot, then spawn 'count' pebbles in a wide spread around it
        placement.append({"type": "minecraft:in_square"})

        if mode == "underwater":
            placement.append({"type": "minecraft:heightmap", "heightmap": "OCEAN_FLOOR_WG"})
        else:
            placement.append({"type": "minecraft:heightmap", "heightmap": "MOTION_BLOCKING"})

        placement.append({"type": "minecraft:biome"})
        placement.append({"type": "minecraft:count", "count": count})

        # WIDENED to -5 to +5 so it looks like a natural, faded patch, not a 5x5 block
        placement.append({
            "type": "minecraft:random_offset",
            "xz_spread": {"type": "minecraft:trapezoid", "min": -5, "max": 5, "plateau": 1},
            "y_spread": 0
        })

    # Predicates (Same as before)
    target = "minecraft:water" if mode == "underwater" else "minecraft:air"
    predicates = [
        {"type": "minecraft:matching_blocks", "blocks": target},
        {"type": "minecraft:has_sturdy_face", "offset": [0, -1, 0], "direction": "up"}
    ]

    if mode == "underwater":
        predicates.append({"type": "minecraft:not",
                           "predicate": {"type": "minecraft:matching_blocks", "offset": [0, -1, 0],
                                         "blocks": ["minecraft:ice", "minecraft:packed_ice"]}})
    elif mode == "nether":
        predicates.append({"type": "minecraft:not",
                           "predicate": {"type": "minecraft:matching_blocks", "offset": [0, -1, 0],
                                         "blocks": ["minecraft:nether_wart_block", "minecraft:warped_wart_block",
                                                    "minecraft:shroomlight"]}})

    placement.append({"type": "minecraft:block_predicate_filter",
                      "predicate": {"type": "minecraft:all_of", "predicates": predicates}})

    save_json(f"{OUTPUT_DIR}/worldgen/placed_feature/{file_name}.json",
              {"feature": f"{MOD_ID}:{feature_ref}", "placement": placement})


# ==========================================
# 3. EXECUTION LOGIC
# ==========================================

# (Generate Configured Features)
for s in ["stone", "sandstone", "red_sandstone", "andesite", "diorite", "granite", "tuff", "deepslate"]:
    make_pebble_configured(f"{s}_pebble")
    if s not in ["sandstone", "red_sandstone"]: make_pebble_configured(f"{s}_pebble", is_underwater=True)
make_pebble_configured("blackstone_pebble")
make_pebble_configured("end_stone_pebble")
make_twig_configured("crimson_twig")
make_twig_configured("warped_twig")

# --- SURFACE / UNDERWATER (Clump Style - Widened to look natural) ---
make_placed("stone_pebble_placed", "stone_pebble", count=25, rarity=5, style="clump", mode="surface")
make_placed("sandstone_pebble_placed", "sandstone_pebble", count=25, rarity=5, style="clump", mode="surface")
make_placed("red_sandstone_pebble_placed", "red_sandstone_pebble", count=25, rarity=5, style="clump", mode="surface")

make_placed("stone_pebble_tier2", "stone_pebble", count=15, rarity=10, style="clump", mode="surface")
for s in ["andesite", "diorite", "granite"]:
    make_placed(f"{s}_pebble_tier2", f"{s}_pebble", count=15, rarity=10, style="clump", mode="surface")

for s in ["tuff", "deepslate"]:
    make_placed(f"{s}_pebble_tier3", f"{s}_pebble", count=10, rarity=20, style="clump", mode="surface")

for s in ["stone", "andesite", "diorite", "granite", "tuff", "deepslate"]:
    make_placed(f"{s}_pebble_underwater", f"{s}_pebble_uw", count=20, rarity=2, style="clump", mode="underwater")

# --- DIMENSIONS & TWIGS (Scatter Style - Fixed!) ---
# Count is back to creating completely independent spawns, and rarity is removed.
make_placed("blackstone_pebble_placed", "blackstone_pebble", count=1024, rarity=None, style="scatter", mode="nether")
make_placed("end_stone_pebble_placed", "end_stone_pebble", count=16, rarity=None, style="scatter", mode="end")
make_placed("crimson_twig_placed", "crimson_twig", count=768, rarity=None, style="scatter", mode="nether")
make_placed("warped_twig_placed", "warped_twig", count=768, rarity=None, style="scatter", mode="nether")