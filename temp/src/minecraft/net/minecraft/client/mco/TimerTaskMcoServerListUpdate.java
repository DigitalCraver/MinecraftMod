package net.minecraft.client.mco;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import net.minecraft.client.mco.ExceptionMcoService;
import net.minecraft.client.mco.McoClient;
import net.minecraft.client.mco.McoServer;
import net.minecraft.client.mco.McoServerList;
import net.minecraft.client.mco.McoServerListINNER1;

@SideOnly(Side.CLIENT)
class TimerTaskMcoServerListUpdate extends TimerTask {

   McoClient field_98262_a;
   // $FF: synthetic field
   final McoServerList field_98261_b;


   private TimerTaskMcoServerListUpdate(McoServerList p_i11000_1_) {
      this.field_98261_b = p_i11000_1_;
      this.field_98262_a = new McoClient(McoServerList.func_100014_a(this.field_98261_b));
   }

   public void run() {
      if(!McoServerList.func_98249_b(this.field_98261_b)) {
         this.func_98260_a();
      }

   }

   private void func_98260_a() {
      try {
         List var1 = this.field_98262_a.func_96382_a().field_96430_d;
         this.func_101018_a(var1);
         McoServerList.func_98247_a(this.field_98261_b, var1);
      } catch (ExceptionMcoService var2) {
         ;
      } catch (IOException var3) {
         System.err.println(var3);
      }

   }

   private void func_101018_a(List p_101018_1_) {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      Iterator var4 = p_101018_1_.iterator();

      while(var4.hasNext()) {
         McoServer var5 = (McoServer)var4.next();
         if(var5.field_96405_e.equals(McoServerList.func_100014_a(this.field_98261_b).field_74286_b)) {
            var2.add(var5);
         } else {
            var3.add(var5);
         }
      }

      Collections.sort(var2);
      Collections.sort(var3);
      p_101018_1_.clear();
      p_101018_1_.addAll(var2);
      p_101018_1_.addAll(var3);
   }

   // $FF: synthetic method
   TimerTaskMcoServerListUpdate(McoServerList p_i11001_1_, McoServerListINNER1 p_i11001_2_) {
      this(p_i11001_1_);
   }
}
