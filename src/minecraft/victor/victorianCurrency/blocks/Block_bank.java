package victor.victorianCurrency.blocks;

import java.util.Random;

import victor.victorianCurrency.VictorianCurrency;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Block_bank extends BlockContainer {
	
	static NBTTagCompound compound = VictorianCurrency.bankList;
	
	InventoryBlockBank bankInv = new InventoryBlockBank();

	public Block_bank(int id, Material mat) {
		super(id, mat);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		
			bankInv.setOwner(player.getEntityName());
			player.addChatMessage(bankInv.getOwner());
		
        TileEntityBank tileentitybank = (TileEntityBank)par1World.getBlockTileEntity(par2, par3, par4);

        if (bankInv != null && tileentitybank != null)
        {
            if (par1World.isBlockNormalCube(par2, par3 + 1, par4))
            {
                return true;
            }
            else if (par1World.isRemote)
            {
                return true;
            }
            else
            {
                bankInv.setAssociatedChest(tileentitybank);
                player.displayGUIChest(bankInv);
                return true;
            }
        }
        else
        {
            return true;
        }
    }
	
	public static ItemStack createItem(String owner)
    {
	    ItemStack stack = new ItemStack(VictorianCurrency.bank);
        if(!owner.equals("global"))
        {
            if(!stack.hasTagCompound())
                stack.setTagCompound(compound);
            stack.getTagCompound().setString("owner", owner);
        }
        return stack;
    }

	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBank();
	}
	
}
