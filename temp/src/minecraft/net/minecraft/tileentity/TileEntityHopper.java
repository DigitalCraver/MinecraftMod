package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntity implements Hopper {

   private ItemStack[] field_94124_b = new ItemStack[5];
   private String field_94123_d;
   private int field_98048_c = -1;


   public void func_70307_a(NBTTagCompound p_70307_1_) {
      super.func_70307_a(p_70307_1_);
      NBTTagList var2 = p_70307_1_.func_74761_m("Items");
      this.field_94124_b = new ItemStack[this.func_70302_i_()];
      if(p_70307_1_.func_74764_b("CustomName")) {
         this.field_94123_d = p_70307_1_.func_74779_i("CustomName");
      }

      this.field_98048_c = p_70307_1_.func_74762_e("TransferCooldown");

      for(int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
         NBTTagCompound var4 = (NBTTagCompound)var2.func_74743_b(var3);
         byte var5 = var4.func_74771_c("Slot");
         if(var5 >= 0 && var5 < this.field_94124_b.length) {
            this.field_94124_b[var5] = ItemStack.func_77949_a(var4);
         }
      }

   }

   public void func_70310_b(NBTTagCompound p_70310_1_) {
      super.func_70310_b(p_70310_1_);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.field_94124_b.length; ++var3) {
         if(this.field_94124_b[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.field_94124_b[var3].func_77955_b(var4);
            var2.func_74742_a(var4);
         }
      }

      p_70310_1_.func_74782_a("Items", var2);
      p_70310_1_.func_74768_a("TransferCooldown", this.field_98048_c);
      if(this.func_94042_c()) {
         p_70310_1_.func_74778_a("CustomName", this.field_94123_d);
      }

   }

   public void func_70296_d() {
      super.func_70296_d();
   }

   public int func_70302_i_() {
      return this.field_94124_b.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return this.field_94124_b[p_70301_1_];
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_94124_b[p_70298_1_] != null) {
         ItemStack var3;
         if(this.field_94124_b[p_70298_1_].field_77994_a <= p_70298_2_) {
            var3 = this.field_94124_b[p_70298_1_];
            this.field_94124_b[p_70298_1_] = null;
            return var3;
         } else {
            var3 = this.field_94124_b[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_94124_b[p_70298_1_].field_77994_a == 0) {
               this.field_94124_b[p_70298_1_] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_94124_b[p_70304_1_] != null) {
         ItemStack var2 = this.field_94124_b[p_70304_1_];
         this.field_94124_b[p_70304_1_] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_94124_b[p_70299_1_] = p_70299_2_;
      if(p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

   }

   public String func_70303_b() {
      return this.func_94042_c()?this.field_94123_d:"container.hopper";
   }

   public boolean func_94042_c() {
      return this.field_94123_d != null && this.field_94123_d.length() > 0;
   }

   public void func_96115_a(String p_96115_1_) {
      this.field_94123_d = p_96115_1_;
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_70331_k.func_72796_p(this.field_70329_l, this.field_70330_m, this.field_70327_n) != this?false:p_70300_1_.func_70092_e((double)this.field_70329_l + 0.5D, (double)this.field_70330_m + 0.5D, (double)this.field_70327_n + 0.5D) <= 64.0D;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public void func_70316_g() {
      if(this.field_70331_k != null && !this.field_70331_k.field_72995_K) {
         --this.field_98048_c;
         if(!this.func_98047_l()) {
            this.func_98046_c(0);
            this.func_98045_j();
         }

      }
   }

   public boolean func_98045_j() {
      if(this.field_70331_k != null && !this.field_70331_k.field_72995_K) {
         if(!this.func_98047_l() && BlockHopper.func_94452_d(this.func_70322_n())) {
            boolean var1 = this.func_94116_j() | func_96116_a(this);
            if(var1) {
               this.func_98046_c(8);
               this.func_70296_d();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean func_94116_j() {
      int var1 = func_94115_a(this, -1);
      boolean var2 = false;
      if(var1 > -1) {
         IInventory var3 = this.func_94119_v();
         if(var3 != null) {
            ItemStack var4 = this.func_70301_a(var1).func_77946_l();
            ItemStack var5 = func_94117_a(var3, this.func_70298_a(var1, 1), Facing.field_71588_a[BlockHopper.func_94451_c(this.func_70322_n())]);
            if(var5 != null && var5.field_77994_a != 0) {
               this.func_70299_a(var1, var4);
            } else {
               var2 |= true;
               var3.func_70296_d();
            }
         }
      }

      return var2;
   }

   public static boolean func_96116_a(Hopper p_96116_0_) {
      boolean var1 = false;
      IInventory var2 = func_96118_b(p_96116_0_);
      if(var2 != null) {
         byte var3 = 0;
         int var4 = 0;
         int var5 = var2.func_70302_i_();
         if(var2 instanceof ISidedInventory && var3 > -1) {
            ISidedInventory var6 = (ISidedInventory)var2;
            var4 = var6.func_94127_c(var3);
            var5 = Math.min(var5, var4 + var6.func_94128_d(var3));
         }

         for(int var11 = var4; var11 < var5 && !var1; ++var11) {
            ItemStack var7 = var2.func_70301_a(var11);
            if(var7 != null) {
               ItemStack var8 = var7.func_77946_l();
               ItemStack var9 = func_94117_a(p_96116_0_, var2.func_70298_a(var11, 1), -1);
               if(var9 != null && var9.field_77994_a != 0) {
                  var2.func_70299_a(var11, var8);
               } else {
                  var1 |= true;
                  var2.func_70296_d();
               }
            }
         }
      } else {
         EntityItem var10 = func_96119_a(p_96116_0_.func_70314_l(), p_96116_0_.func_96107_aA(), p_96116_0_.func_96109_aB() + 1.0D, p_96116_0_.func_96108_aC());
         if(var10 != null) {
            var1 |= func_96114_a(p_96116_0_, var10);
         }
      }

      return var1;
   }

   public static boolean func_96114_a(IInventory p_96114_0_, EntityItem p_96114_1_) {
      boolean var2 = false;
      if(p_96114_1_ == null) {
         return false;
      } else {
         ItemStack var3 = p_96114_1_.func_92059_d().func_77946_l();
         ItemStack var4 = func_94117_a(p_96114_0_, var3, -1);
         if(var4 != null && var4.field_77994_a != 0) {
            p_96114_1_.func_92058_a(var4);
         } else {
            var2 = true;
            p_96114_1_.func_70106_y();
         }

         return var2;
      }
   }

   public static int func_94115_a(IInventory p_94115_1_, int p_94115_2_) {
      int var2 = 0;
      int var3 = p_94115_1_.func_70302_i_();
      if(p_94115_1_ instanceof ISidedInventory && p_94115_2_ > -1) {
         ISidedInventory var4 = (ISidedInventory)p_94115_1_;
         var2 = var4.func_94127_c(p_94115_2_);
         var3 = Math.min(var3, var2 + var4.func_94128_d(p_94115_2_));
      }

      for(int var5 = var2; var5 < var3; ++var5) {
         if(p_94115_1_.func_70301_a(var5) != null) {
            return var5;
         }
      }

      return -1;
   }

   public static ItemStack func_94117_a(IInventory p_94117_1_, ItemStack p_94117_2_, int p_94117_3_) {
      int var3 = 0;
      int var4 = p_94117_1_.func_70302_i_();
      if(p_94117_1_ instanceof ISidedInventory && p_94117_3_ > -1) {
         ISidedInventory var5 = (ISidedInventory)p_94117_1_;
         var3 = var5.func_94127_c(p_94117_3_);
         var4 = Math.min(var4, var3 + var5.func_94128_d(p_94117_3_));
      }

      for(int var10 = var3; var10 < var4 && p_94117_2_ != null && p_94117_2_.field_77994_a > 0; ++var10) {
         ItemStack var6 = p_94117_1_.func_70301_a(var10);
         if(p_94117_1_.func_94041_b(var10, p_94117_2_)) {
            boolean var7 = false;
            if(var6 == null) {
               p_94117_1_.func_70299_a(var10, p_94117_2_);
               p_94117_2_ = null;
               var7 = true;
            } else if(func_94114_a(var6, p_94117_2_)) {
               int var8 = p_94117_2_.func_77976_d() - var6.field_77994_a;
               int var9 = Math.min(p_94117_2_.field_77994_a, var8);
               p_94117_2_.field_77994_a -= var9;
               var6.field_77994_a += var9;
               var7 = var9 > 0;
            }

            if(var7) {
               if(p_94117_1_ instanceof TileEntityHopper) {
                  ((TileEntityHopper)p_94117_1_).func_98046_c(8);
               }

               p_94117_1_.func_70296_d();
            }
         }
      }

      if(p_94117_2_ != null && p_94117_2_.field_77994_a == 0) {
         p_94117_2_ = null;
      }

      return p_94117_2_;
   }

   private IInventory func_94119_v() {
      int var1 = BlockHopper.func_94451_c(this.func_70322_n());
      return func_96117_b(this.func_70314_l(), (double)(this.field_70329_l + Facing.field_71586_b[var1]), (double)(this.field_70330_m + Facing.field_71587_c[var1]), (double)(this.field_70327_n + Facing.field_71585_d[var1]));
   }

   public static IInventory func_96118_b(Hopper p_96118_0_) {
      return func_96117_b(p_96118_0_.func_70314_l(), p_96118_0_.func_96107_aA(), p_96118_0_.func_96109_aB() + 1.0D, p_96118_0_.func_96108_aC());
   }

   public static EntityItem func_96119_a(World p_96119_0_, double p_96119_1_, double p_96119_3_, double p_96119_5_) {
      List var7 = p_96119_0_.func_82733_a(EntityItem.class, AxisAlignedBB.func_72332_a().func_72299_a(p_96119_1_, p_96119_3_, p_96119_5_, p_96119_1_ + 1.0D, p_96119_3_ + 1.0D, p_96119_5_ + 1.0D), IEntitySelector.field_94557_a);
      return var7.size() > 0?(EntityItem)var7.get(0):null;
   }

   public static IInventory func_96117_b(World p_96117_0_, double p_96117_1_, double p_96117_3_, double p_96117_5_) {
      IInventory var7 = null;
      int var8 = MathHelper.func_76128_c(p_96117_1_);
      int var9 = MathHelper.func_76128_c(p_96117_3_);
      int var10 = MathHelper.func_76128_c(p_96117_5_);
      if(var7 == null) {
         TileEntity var11 = p_96117_0_.func_72796_p(var8, var9, var10);
         if(var11 != null && var11 instanceof IInventory) {
            var7 = (IInventory)var11;
            if(var7 instanceof TileEntityChest) {
               int var12 = p_96117_0_.func_72798_a(var8, var9, var10);
               Block var13 = Block.field_71973_m[var12];
               if(var13 instanceof BlockChest) {
                  var7 = ((BlockChest)var13).func_94442_h_(p_96117_0_, var8, var9, var10);
               }
            }
         }
      }

      if(var7 == null) {
         List var14 = p_96117_0_.func_94576_a((Entity)null, AxisAlignedBB.func_72332_a().func_72299_a(p_96117_1_, p_96117_3_, p_96117_5_, p_96117_1_ + 1.0D, p_96117_3_ + 1.0D, p_96117_5_ + 1.0D), IEntitySelector.field_96566_b);
         if(var14 != null && var14.size() > 0) {
            var7 = (IInventory)var14.get(p_96117_0_.field_73012_v.nextInt(var14.size()));
         }
      }

      return var7;
   }

   private static boolean func_94114_a(ItemStack p_94114_1_, ItemStack p_94114_2_) {
      return p_94114_1_.field_77993_c != p_94114_2_.field_77993_c?false:(p_94114_1_.func_77960_j() != p_94114_2_.func_77960_j()?false:(p_94114_1_.field_77994_a > p_94114_1_.func_77976_d()?false:ItemStack.func_77970_a(p_94114_1_, p_94114_2_)));
   }

   public double func_96107_aA() {
      return (double)this.field_70329_l;
   }

   public double func_96109_aB() {
      return (double)this.field_70330_m;
   }

   public double func_96108_aC() {
      return (double)this.field_70327_n;
   }

   public void func_98046_c(int p_98046_1_) {
      this.field_98048_c = p_98046_1_;
   }

   public boolean func_98047_l() {
      return this.field_98048_c > 0;
   }
}
