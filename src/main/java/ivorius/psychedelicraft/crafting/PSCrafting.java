/*
 *  Copyright (c) 2014, Lukas Tenbrink.
 *  * http://lukas.axxim.net
 */

package ivorius.psychedelicraft.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import ivorius.psychedelicraft.blocks.TileEntityDryingTable;
import ivorius.psychedelicraft.blocks.TileEntityMashTub;
import ivorius.psychedelicraft.config.PSConfig;
import ivorius.psychedelicraft.entities.drugs.DrugInfluence;
import ivorius.psychedelicraft.entities.drugs.DrugInfluenceHarmonium;
import ivorius.psychedelicraft.items.ItemBong;
import ivorius.psychedelicraft.items.ItemSmokingPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

import static ivorius.psychedelicraft.blocks.PSBlocks.*;
import static ivorius.psychedelicraft.fluids.PSFluids.*;
import static ivorius.psychedelicraft.items.PSItems.*;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.*;
import static net.minecraft.init.Items.wheat;

/**
 * Created by lukas on 18.10.14.
 */
public class PSCrafting
{
    public static void initialize()
    {
        GameRegistry.addRecipe(new ItemStack(syringe), "I", "#", 'I', iron_ingot, '#', glass);
        GameRegistry.addRecipe(new ItemStack(pipe), "  I", " S ", "WS ", 'I', iron_ingot, 'S', stick, 'W', planks);
        GameRegistry.addRecipe(new ItemStack(bong, 1, 3), " P ", "G G", "GGG", 'P', glass_pane, 'G', glass);

        for (int type = 0; type < BlockWood.field_150096_a.length; type++)
        {
            GameRegistry.addRecipe(new ItemStack(itemBarrel, 1, type), " I ", "# #", "S#S", '#', new ItemStack(planks, 1, type), 'I', iron_ingot, 'S', stick);
        }

        GameRegistry.addRecipe(new ItemStack(itemMashTub), "# #", "I I", "###", 'I', iron_ingot, '#', planks);

        Object flaskIngotItem = foreignItem("ingotCopper", iron_ingot);
        GameRegistry.addRecipe(new ItemStack(itemFlask), " # ", "#G#", "###", 'G', glass, '#', flaskIngotItem);
        GameRegistry.addRecipe(new ItemStack(itemDistillery), "##", "D ", 'D', itemFlask, '#', flaskIngotItem);

        ItemStack wineMash = new ItemStack(itemMashTub);
        itemMashTub.fill(wineMash, new FluidStack(wine, TileEntityMashTub.MASH_TUB_CAPACITY), true);
        addShapelessRecipe(wineMash, new ItemStack(itemMashTub), wineGrapes, wineGrapes, wineGrapes, wineGrapes, wineGrapes, wineGrapes, wineGrapes, wineGrapes);

        ItemStack wortMash = new ItemStack(itemMashTub);
        itemMashTub.fill(wortMash, new FluidStack(beer, TileEntityMashTub.MASH_TUB_CAPACITY), true);
        addShapelessRecipe(wortMash, new ItemStack(itemMashTub), water_bucket, wheat, wheat, wheat, wheat, wheat, wheat, wheat);

        ItemStack vodkaMash = new ItemStack(itemMashTub);
        itemMashTub.fill(vodkaMash, new FluidStack(vodka, TileEntityMashTub.MASH_TUB_CAPACITY), true);
        addShapelessRecipe(vodkaMash, new ItemStack(itemMashTub), water_bucket, potato, potato, potato, potato, potato, potato, potato);

        ItemStack jeneverMash = new ItemStack(itemMashTub);
        itemMashTub.fill(jeneverMash, new FluidStack(jenever, TileEntityMashTub.MASH_TUB_CAPACITY), true);
        addShapelessRecipe(jeneverMash, new ItemStack(itemMashTub), juniperBerries, juniperBerries, juniperBerries, juniperBerries, sugar, wineGrapes, wineGrapes, wheat);

        GameRegistry.addRecipe(new ItemStack(glassChalice, 4), "# #", " # ", " # ", '#', glass);
        GameRegistry.addRecipe(new ItemStack(wineGrapeLattice), "###", "###", "O#O", '#', stick, 'O', planks);

        GameRegistry.addRecipe(new ItemStack(woodenMug, 8), "# #", "# #", "###", '#', planks);

//        for (int color = 0; color < ItemDye.field_150922_c.length; color++)
//            GameRegistry.addRecipe(new ItemStack(molotovCocktail, 4, color), "P", "#", '#', new ItemStack(stained_glass, 1, 15 - color), 'P', paper);

        for (int color = 0; color < ItemDye.field_150922_c.length; color++)
        {
            GameRegistry.addRecipe(new ItemStack(bottle, 8, color), " # ", "# #", "###", '#', new ItemStack(stained_glass, 1, 15 - color));
            GameRegistry.addRecipe(new ItemStack(molotovCocktail, 1, color), "P", "G", 'P', paper, 'G', new ItemStack(bottle, 1, color));
        }

        GameRegistry.addRecipe(new ItemStack(dryingTable), "###", "#R#", '#', planks, 'R', redstone);

        TileEntityDryingTable.addDryingResult(cannabisLeaf, new ItemStack(driedCannabisLeaves, 3));
        TileEntityDryingTable.addDryingResult(cannabisBuds, new ItemStack(driedCannabisBuds, 3));
        GameRegistry.addRecipe(new ItemStack(hashMuffin), "LLL", "#X#", "LLL", 'X', new ItemStack(dye, 1, 3), '#', Items.wheat, 'L', driedCannabisLeaves);
        GameRegistry.addRecipe(new ItemStack(joint), "P", "C", "P", 'P', paper, 'C', driedCannabisBuds);
        pipe.addConsumable(new ItemSmokingPipe.ItemSmokingPipeConsumable(new ItemStack(driedCannabisBuds), new DrugInfluence[]{new DrugInfluence("Cannabis", 20, 0.002, 0.001, 0.25f)}));
        bong.addConsumable(new ItemBong.ItemBongConsumable(new ItemStack(driedCannabisBuds), new DrugInfluence[]{new DrugInfluence("Cannabis", 20, 0.002, 0.001, 0.2f)})); //TODO: Play around with the bongs benefits

        TileEntityDryingTable.addDryingResult(Item.getItemFromBlock(brown_mushroom), new ItemStack(magicMushroomsBrown, 3));
        TileEntityDryingTable.addDryingResult(Item.getItemFromBlock(red_mushroom), new ItemStack(magicMushroomsRed, 3));

        GameRegistry.addRecipe(new ItemStack(cigarette, 4), "P", "T", "P", 'P', paper, 'T', driedTobacco);
        GameRegistry.addRecipe(new ItemStack(cigar), "TTT", "TTT", "PPP", 'P', paper, 'T', driedTobacco);
        TileEntityDryingTable.addDryingResult(tobaccoLeaf, new ItemStack(driedTobacco, 3));
        pipe.addConsumable(new ItemSmokingPipe.ItemSmokingPipeConsumable(new ItemStack(driedTobacco), new DrugInfluence[]{new DrugInfluence("Tobacco", 0, 0.1, 0.02, 0.8f)}));
        bong.addConsumable(new ItemBong.ItemBongConsumable(new ItemStack(driedTobacco), new DrugInfluence[]{new DrugInfluence("Tobacco", 0, 0.1, 0.02, 0.6f)})); //TODO: Play around with the bongs benefits

        TileEntityDryingTable.addDryingResult(cocaLeaf, new ItemStack(driedCocaLeaves, 3));

        GameRegistry.addRecipe(new ItemStack(planks, 4, 1), "#", '#', psycheLog);

        GameRegistry.addSmelting(coffeaCherries, new ItemStack(coffeeBeans), 0.2f);

        TileEntityDryingTable.addDryingResult(Item.getItemFromBlock(peyote), new ItemStack(driedPeyote, 3));
        GameRegistry.addRecipe(new ItemStack(peyoteJoint), "P", "D", "P", 'P', paper, 'D', driedPeyote);

        for (int i = 0; i < 16; i++)
        {
            pipe.addConsumable(new ItemSmokingPipe.ItemSmokingPipeConsumable(new ItemStack(harmonium, 1, 15 - i), new DrugInfluence[]{new DrugInfluenceHarmonium("Harmonium", 0, 0.04, 0.01, 0.65f, EntitySheep.fleeceColorTable[i])}, EntitySheep.fleeceColorTable[i]));

            if (PSConfig.enableHarmonium)
                addShapelessRecipe(new ItemStack(harmonium, 1, i), new ItemStack(dye, 1, i), glowstone_dust, driedTobacco);
        }

        if (PSConfig.enableRiftJars)
            GameRegistry.addRecipe(new ItemStack(riftJar), "O-O", "GO ", "OIO", 'O', glass, '-', planks, 'G', gold_ingot, 'I', iron_ingot);

        RecipeActionRegistry.addRecipe("ps_pourDrink", new RecipePourDrink());

        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(peyoteJuice, 100), water_bucket, driedPeyote, driedPeyote));
        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(coffee, 100), water_bucket, coffeeBeans, coffeeBeans));
        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(cocaTea, 100), water_bucket, cocaLeaf, cocaLeaf));
        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(cannabisTea, 100), water_bucket, cannabisLeaf, cannabisLeaf));
        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(cocaineFluid, 10), water_bucket, driedCocaLeaves, driedCocaLeaves));
        GameRegistry.addRecipe(new RecipeFillDrink(new FluidStack(caffeineFluid, 10), coffeeBeans, coffeeBeans));

        //TODO Add when Forge fixes smelting with NBT
//                GameRegistry.addSmelting(DrinkRegistry.createDrinkStack(itemDrinkHolder, 1, "coldCoffee"), new ItemStack(emptyContainer, 1, 3), 0.2f);
    }

    private static Object foreignItem(String oredictID, Object replacement)
    {
        List<ItemStack> foreignItems = OreDictionary.getOres(oredictID);
        if (foreignItems.size() == 0)
            return replacement;

        return foreignItems.get(0);
    }

    private static void addShapelessRecipe(ItemStack output, Object... params)
    {
        for (int i = 0; i < params.length; i++)
        {
            if (params[i] instanceof Item)
                params[i] = new ItemStack((Item) params[i], 1, OreDictionary.WILDCARD_VALUE);
            else if (params[i] instanceof Block)
                params[i] = new ItemStack((Block) params[i], 1, OreDictionary.WILDCARD_VALUE);
        }

        GameRegistry.addShapelessRecipe(output, params);
    }
}
