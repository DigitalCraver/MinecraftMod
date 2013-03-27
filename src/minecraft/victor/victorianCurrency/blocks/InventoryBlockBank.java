package victor.victorianCurrency.blocks;

import victor.victorianCurrency.VictorianCurrency;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryBlockBank extends InventoryBasic {
	
	private String owner = "global";
	
	public static TileEntityBank associatedChest;
	
	private static NBTTagCompound compound = VictorianCurrency.bankList;

	public InventoryBlockBank() {
		super("Piggy Bank", false, 27);
		this.setOwner("global");
		
	}
	
	public String getOwner(){
		return owner;
	}
	
	public  void setOwner(String owner){
		this.owner = owner;
	}
	
	public static void setAssociatedChest(TileEntityBank tileentitybank)
	{
		associatedChest = tileentitybank;
	}
	
	  public void loadInventoryFromNBT(NBTTagList par1NBTTagList)
	    {
	        int i;

	        for (i = 0; i < this.getSizeInventory(); ++i)
	        {
	            this.setInventorySlotContents(i, (ItemStack)null);
	        }

	        for (i = 0; i < par1NBTTagList.tagCount(); ++i)
	        {
	            compound = (NBTTagCompound)par1NBTTagList.tagAt(i);
	            int j = compound.getByte("Slot") & 255;

	            if (j >= 0 && j < this.getSizeInventory())
	            {
	                this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(compound));
	            }
	        }
	    }

	    public NBTTagList saveInventoryToNBT()
	    {
	        NBTTagList nbttaglist = new NBTTagList("Piggy Bank");

	        for (int i = 0; i < this.getSizeInventory(); ++i)
	        {
	            ItemStack itemstack = this.getStackInSlot(i);

	            if (itemstack != null)
	            {
	               
	                compound.setByte("Slot", (byte)i);
	                itemstack.writeToNBT(compound);
	                nbttaglist.appendTag(compound);
	            }
	        }

	        return nbttaglist;
	    }

	    /**
	     * Do not make give this method the name canInteractWith because it clashes with Container
	     */
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return this.associatedChest != null && !this.associatedChest.isUseableByPlayer(par1EntityPlayer) ? false : super.isUseableByPlayer(par1EntityPlayer);
	    }

	    public void openChest()
	    {
	        if (this.associatedChest != null)
	        {
	            this.associatedChest.openChest();
	        }

	        super.openChest();
	    }

	    public void closeChest()
	    {
	        if (this.associatedChest != null)
	        {
	            this.associatedChest.closeChest();
	        }

	        super.closeChest();
	        this.associatedChest = null;
	    }

	    public boolean func_94041_b(int par1, ItemStack par2ItemStack)
	    {
	        return true;
	    }

		
		}
	
