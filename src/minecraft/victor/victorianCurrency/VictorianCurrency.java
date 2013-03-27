package victor.victorianCurrency;

import victor.victorianCurrency.blocks.Block_bank;
import victor.victorianCurrency.blocks.Block_shop;
import victor.victorianCurrency.blocks.InventoryBlockBank;
import victor.victorianCurrency.blocks.TileEntityBank;
import victor.victorianCurrency.items.Item_coin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="VC", name="VictorianCurrency", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class VictorianCurrency {

        // The instance of your mod that Forge uses.
        @Instance("VictorianCurrency")
        public static VictorianCurrency instance;
        
        /** the basic coin value basically the value of 1 copper coin*/
        private static final int BASIC_COIN_VALUE = 1;
        
        
         /** The amount of coins it takes to advance a tier
         * ex: 32 copper coin to make a silver coin, 32 silver coin to make a gold coin
         */
        private static final int TIER_MODIFIER = 32;
        
        public static NBTTagCompound bankList = new NBTTagCompound("bankData");
        
        private final static Item coin_bronze = new Item_coin(256, "Victorian Coin", CreativeTabs.tabMisc,BASIC_COIN_VALUE).setUnlocalizedName("coin_bronze");
        private final static Item coin_silver = new Item_coin(257, "Victorian Coin", CreativeTabs.tabMisc,BASIC_COIN_VALUE * TIER_MODIFIER).setUnlocalizedName("coin_silver");
        private final static Item coin_gold = new Item_coin(258, "Victorian Coin", CreativeTabs.tabMisc, ((Item_coin) coin_silver).getValue() * TIER_MODIFIER).setUnlocalizedName("coin_gold");
        private final static Block shop = new Block_shop(556,Material.wood, CreativeTabs.tabBlock).setHardness(0.5f).setLightValue(0.5f).setUnlocalizedName("shop");
        public final static Block bank = new Block_bank(557,Material.iron).setHardness(1.5f).setUnlocalizedName("shop");
        
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="victor.victorianCurrency.client.ClientProxy", serverSide="victor.victorianCurrency.CommonProxy")
        public static CommonProxy proxy;
       
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        	
        }
       
        @Init
        public void load(FMLInitializationEvent event) {
        	LanguageRegistry.addName(coin_bronze, "Bronze Victorian");
        	LanguageRegistry.addName(coin_silver, "Silver Victorian");
        	LanguageRegistry.addName(coin_gold, "Gold Victorian");
        	LanguageRegistry.addName(shop, "Coin shop");
        	LanguageRegistry.addName(bank, "piggy bank");
        	
        	GameRegistry.registerTileEntity(TileEntityBank.class, "Bank");
        	GameRegistry.registerBlock(shop);
        	GameRegistry.registerBlock(bank);
        	
        	
        	
                //proxy.registerRenderers();
        }
       
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}