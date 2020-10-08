package com.github.alexthe666.iceandfire.world;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.config.BiomeConfig;
import com.github.alexthe666.iceandfire.world.gen.*;
import com.github.alexthe666.iceandfire.world.structure.DreadMausoleumStructure;
import com.github.alexthe666.iceandfire.world.structure.GorgonTemplePiece;
import com.github.alexthe666.iceandfire.world.structure.GorgonTempleStructure;
import com.github.alexthe666.iceandfire.world.structure.MausoleumPiece;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.PillagerOutpostStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public class IafWorldRegistry {

    public static Feature<NoFeatureConfig> FIRE_DRAGON_ROOST;
    public static Feature<NoFeatureConfig> ICE_DRAGON_ROOST;
    public static Feature<NoFeatureConfig> LIGHTNING_DRAGON_ROOST;
    public static Feature<NoFeatureConfig> FIRE_DRAGON_CAVE;
    public static Feature<NoFeatureConfig> ICE_DRAGON_CAVE;
    public static Feature<NoFeatureConfig> LIGHTNING_DRAGON_CAVE;
    public static Feature<NoFeatureConfig> CYCLOPS_CAVE;
    public static Feature<NoFeatureConfig> PIXIE_VILLAGE;
    public static Feature<NoFeatureConfig> SIREN_ISLAND;
    public static Feature<NoFeatureConfig> HYDRA_CAVE;
    public static Feature<NoFeatureConfig> MYRMEX_HIVE_DESERT;
    public static Feature<NoFeatureConfig> MYRMEX_HIVE_JUNGLE;
    public static Feature<NoFeatureConfig> MOB_SPAWNS;
    public static IStructurePieceType MAUSOLEUM_PIECE;
    public static Structure<NoFeatureConfig> MAUSOLEUM = new DreadMausoleumStructure(NoFeatureConfig.field_236558_a_);
    public static final Structure<NoFeatureConfig> MAUSOLEUM_SF = func_236394_a_("iceandfire:mausoleum", MAUSOLEUM, GenerationStage.Decoration.SURFACE_STRUCTURES);
    public static IStructurePieceType GORGON_PIECE;
    public static Structure<NoFeatureConfig> GORGON_TEMPLE = new GorgonTempleStructure(NoFeatureConfig.field_236558_a_);
    public static final Structure<NoFeatureConfig> GORGON_TEMPLE_SF = func_236394_a_("iceandfire:gorgon_temple", GORGON_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES);

    public static void register() {
        FIRE_DRAGON_ROOST = Registry.register(Registry.FEATURE, "iceandfire:fire_dragon_roost", new WorldGenFireDragonRoosts(NoFeatureConfig.field_236558_a_));
        ICE_DRAGON_ROOST = Registry.register(Registry.FEATURE, "iceandfire:ice_dragon_roost", new WorldGenIceDragonRoosts(NoFeatureConfig.field_236558_a_));
        LIGHTNING_DRAGON_ROOST = Registry.register(Registry.FEATURE, "iceandfire:lightning_dragon_roost", new WorldGenLightningDragonRoosts(NoFeatureConfig.field_236558_a_));
        FIRE_DRAGON_CAVE = Registry.register(Registry.FEATURE, "iceandfire:fire_dragon_cave", new WorldGenFireDragonCave(NoFeatureConfig.field_236558_a_));
        ICE_DRAGON_CAVE = Registry.register(Registry.FEATURE, "iceandfire:ice_dragon_cave", new WorldGenIceDragonCave(NoFeatureConfig.field_236558_a_));
        LIGHTNING_DRAGON_CAVE = Registry.register(Registry.FEATURE, "iceandfire:lightning_dragon_cave", new WorldGenLightningDragonCave(NoFeatureConfig.field_236558_a_));
        CYCLOPS_CAVE = Registry.register(Registry.FEATURE, "iceandfire:cyclops_cave", new WorldGenCyclopsCave(NoFeatureConfig.field_236558_a_));
        PIXIE_VILLAGE = Registry.register(Registry.FEATURE, "iceandfire:pixie_village", new WorldGenPixieVillage(NoFeatureConfig.field_236558_a_));
        SIREN_ISLAND = Registry.register(Registry.FEATURE, "iceandfire:siren_island", new WorldGenSirenIsland(NoFeatureConfig.field_236558_a_));
        HYDRA_CAVE = Registry.register(Registry.FEATURE, "iceandfire:hydra_cave", new WorldGenHydraCave(NoFeatureConfig.field_236558_a_));
        MYRMEX_HIVE_DESERT = Registry.register(Registry.FEATURE, "iceandfire:myrmex_hive_desert", new WorldGenMyrmexHive(false, false, NoFeatureConfig.field_236558_a_));
        MYRMEX_HIVE_JUNGLE = Registry.register(Registry.FEATURE, "iceandfire:myrmex_hive_jungle", new WorldGenMyrmexHive(false, true, NoFeatureConfig.field_236558_a_));
        MOB_SPAWNS = Registry.register(Registry.FEATURE, "iceandfire:mob_spawns", new WorldGenMobSpawn(NoFeatureConfig.field_236558_a_));
        MAUSOLEUM_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "iceandfire:mausoleum_piece", MausoleumPiece.Piece::new);
        MAUSOLEUM = Registry.register(Registry.STRUCTURE_FEATURE, "iceandfire:mausoleum", MAUSOLEUM);
        putStructureOnAList("iceandfire:mausoleum", MAUSOLEUM);

        GORGON_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "iceandfire:gorgon_piece", GorgonTemplePiece.Piece::new);
        GORGON_TEMPLE = Registry.register(Registry.STRUCTURE_FEATURE, "iceandfire:gorgon_temple", GORGON_TEMPLE);
        putStructureOnAList("iceandfire:gorgon_temple", GORGON_TEMPLE);
        addStructureSeperation(DimensionSettings.field_242734_c, GORGON_TEMPLE, new StructureSeparationSettings(Math.max(IafConfig.spawnGorgonsChance, 2), Math.max(IafConfig.spawnGorgonsChance / 2, 1), 34222645));
        addStructureSeperation(DimensionSettings.field_242734_c, MAUSOLEUM, new StructureSeparationSettings(Math.max(IafConfig.generateMausoleumChance, 2), Math.max(IafConfig.generateMausoleumChance / 2, 1), 34222645));
    }

    public static void addStructureSeperation(RegistryKey<DimensionSettings> preset, Structure structure, StructureSeparationSettings settings) {
        WorldGenRegistries.field_243658_j.func_230516_a_(preset).func_236108_a_().func_236195_a_().put(structure, settings);
    }

    public static <F extends Structure<?>> void putStructureOnAList(String nameForList, F structure) {
        Structure.field_236365_a_.put(nameForList.toLowerCase(Locale.ROOT), structure);
    }

    private static <F extends Structure<?>> F func_236394_a_(String p_236394_0_, F p_236394_1_, GenerationStage.Decoration p_236394_2_) {
        return Registry.register(Registry.STRUCTURE_FEATURE, p_236394_0_.toLowerCase(Locale.ROOT), p_236394_1_);
    }

    public static void setup() {
    }

    public static boolean isFarEnoughFromSpawn(IWorld world, BlockPos pos) {
        BlockPos spawnRelative = new BlockPos(0, pos.getY(), 0);
        boolean spawnCheck = !spawnRelative.withinDistance(pos, IafConfig.dangerousWorldGenDistanceLimit);
        return spawnCheck;
    }

    public static boolean isDimensionListed(IServerWorld world) {
        ResourceLocation name = world.getWorld().func_234923_W_().func_240901_a_();
        if (name == null) {
            return false;
        }
        if (IafConfig.useDimensionBlackList) {
            for (String blacklisted : IafConfig.blacklistedDimensions) {
                if (name.toString().equals(blacklisted)) {
                    return false;
                }
            }
            return true;
        } else {
            for (String whitelist : IafConfig.whitelistedDimensions) {
                if (name.toString().equals(whitelist)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isDimensionListedForDragons(IServerWorld world) {
        ResourceLocation name = world.getWorld().func_234923_W_().func_240901_a_();
        if (name == null) {
            return false;
        }
        if (IafConfig.useDimensionBlackList) {
            for (String blacklisted : IafConfig.dragonBlacklistedDimensions) {
                if (name.toString().equals(blacklisted)) {
                    return false;
                }
            }
            return true;
        } else {
            for (String whitelist : IafConfig.dragonWhitelistedDimensions) {
                if (name.toString().equals(whitelist)) {
                    return true;
                }
            }
            return false;
        }
    }


    public static boolean isFarEnoughFromDangerousGen(IWorld world, BlockPos pos) {
        /*boolean canGen = true;
        IafWorldData data = IafWorldData.get(world.getWorld());
        if (data != null) {
            BlockPos last = data.lastGeneratedDangerousStructure;
            canGen = last.distanceSq(pos) > IafConfig.dangerousWorldGenSeparationLimit * IafConfig.dangerousWorldGenSeparationLimit;
            if (canGen) {
                data.setLastGeneratedDangerousStructure(pos);
            }
        }
        return canGen;*/
        return true;
    }

    public static void onBiomesLoad(BiomeLoadingEvent event) {
        if (BiomeConfig.fireLilyBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(IafBlockRegistry.FIRE_LILY.getDefaultState()), new SimpleBlockPlacer()).tries(1).build()).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l));
        }
        if (BiomeConfig.lightningLilyBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(IafBlockRegistry.LIGHTNING_LILY.getDefaultState()), new SimpleBlockPlacer()).tries(1).build()).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l));
        }
        if (BiomeConfig.iceLilyBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(IafBlockRegistry.FROST_LILY.getDefaultState()), new SimpleBlockPlacer()).tries(1).build()).withPlacement(Features.Placements.field_244000_k).withPlacement(Features.Placements.field_244001_l));
        }
        if (BiomeConfig.oreGenBiomes.contains(event.getName().toString())) {
            if (IafConfig.generateSilverOre) {
                event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, IafBlockRegistry.SILVER_ORE.getDefaultState(), 8)).func_242733_d(32).func_242728_a().func_242731_b(2));
            }
            if (IafConfig.generateCopperOre) {
                event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a, IafBlockRegistry.COPPER_ORE.getDefaultState(), 5)).func_242733_d(128).func_242728_a().func_242731_b(5));
            }
        }
        if (IafConfig.generateSapphireOre && BiomeConfig.sapphireBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.STONE.getDefaultState(), IafBlockRegistry.SAPPHIRE_ORE.getDefaultState())).withPlacement(Placement.EMERALD_ORE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
        if (IafConfig.generateAmythestOre && BiomeConfig.amethystBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.EMERALD_ORE.withConfiguration(new ReplaceBlockConfig(Blocks.STONE.getDefaultState(), IafBlockRegistry.AMYTHEST_ORE.getDefaultState())).withPlacement(Placement.EMERALD_ORE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
        if (IafConfig.generateDragonRoosts) {
            if (BiomeConfig.fireDragonBiomes.contains(event.getName().toString())) {
                event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FIRE_DRAGON_ROOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FIRE_DRAGON_CAVE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            if (BiomeConfig.lightningDragonBiomes.contains(event.getName().toString())) {
                event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, LIGHTNING_DRAGON_ROOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, LIGHTNING_DRAGON_CAVE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }
            if (BiomeConfig.iceDragonBiomes.contains(event.getName().toString())) {
                event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, ICE_DRAGON_ROOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                event.getGeneration().func_242513_a(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, ICE_DRAGON_CAVE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            }
        }
        if (IafConfig.generateCyclopsCaves && BiomeConfig.cyclopsCaveBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, CYCLOPS_CAVE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.spawnGorgons && BiomeConfig.gorgonTempleBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242516_a(GORGON_TEMPLE.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.spawnPixies && BiomeConfig.pixieBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, PIXIE_VILLAGE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.generateHydraCaves && BiomeConfig.pixieBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, HYDRA_CAVE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.generateMausoleums && BiomeConfig.mausoleumBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242516_a(MAUSOLEUM.func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.generateMyrmexColonies && BiomeConfig.desertMyrmexBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, MYRMEX_HIVE_DESERT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.generateMyrmexColonies && BiomeConfig.jungleMyrmexBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, MYRMEX_HIVE_JUNGLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (IafConfig.generateMyrmexColonies && BiomeConfig.sirenBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, SIREN_ISLAND.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
        if (BiomeConfig.overworldSpawnBiomes.contains(event.getName().toString())) {
            event.getGeneration().func_242513_a(GenerationStage.Decoration.SURFACE_STRUCTURES, MOB_SPAWNS.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
}
