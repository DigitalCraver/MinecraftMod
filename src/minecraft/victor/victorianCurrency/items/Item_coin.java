package victor.victorianCurrency.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Item_coin extends Item {
	
	private static int coin_value;
	
	public Item_coin(int id, String name, CreativeTabs tab,int value) {
		super(id);
		this.setMaxStackSize(64);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
		coin_value = value;
		
	}
	
	public static int getValue(){
		return coin_value;
	}
	
   // public String getTextureFile() {
          //  return CommonProxy.ITEMS_PNG;
   // }

}
