package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import net.minecraft.client.gui.GuiScreenOnlineServers;
import net.minecraft.client.gui.GuiSlotOnlineServerList;
import net.minecraft.client.mco.McoServer;

@SideOnly(Side.CLIENT)
class ThreadConnectToOnlineServer extends Thread {

   // $FF: synthetic field
   final McoServer field_96597_a;
   // $FF: synthetic field
   final GuiSlotOnlineServerList field_96596_b;


   ThreadConnectToOnlineServer(GuiSlotOnlineServerList p_i10009_1_, McoServer p_i10009_2_) {
      this.field_96596_b = p_i10009_1_;
      this.field_96597_a = p_i10009_2_;
   }

   public void run() {
      boolean var27 = false;

      label183: {
         label184: {
            label185: {
               label186: {
                  label187: {
                     try {
                        var27 = true;
                        long var1 = System.nanoTime();
                        GuiScreenOnlineServers.func_101002_a(this.field_96596_b.field_96294_a, this.field_96597_a);
                        long var3 = System.nanoTime();
                        this.field_96597_a.field_96412_m = (var3 - var1) / 1000000L;
                        var27 = false;
                        break label183;
                     } catch (UnknownHostException var35) {
                        this.field_96597_a.field_96412_m = -1L;
                        var27 = false;
                     } catch (SocketTimeoutException var36) {
                        this.field_96597_a.field_96412_m = -1L;
                        var27 = false;
                        break label187;
                     } catch (ConnectException var37) {
                        this.field_96597_a.field_96412_m = -1L;
                        var27 = false;
                        break label186;
                     } catch (IOException var38) {
                        this.field_96597_a.field_96412_m = -1L;
                        var27 = false;
                        break label185;
                     } catch (Exception var39) {
                        this.field_96597_a.field_96412_m = -1L;
                        var27 = false;
                        break label184;
                     } finally {
                        if(var27) {
                           synchronized(GuiScreenOnlineServers.func_101007_h()) {
                              GuiScreenOnlineServers.func_101013_k();
                           }
                        }
                     }

                     synchronized(GuiScreenOnlineServers.func_101007_h()) {
                        GuiScreenOnlineServers.func_101013_k();
                        return;
                     }
                  }

                  synchronized(GuiScreenOnlineServers.func_101007_h()) {
                     GuiScreenOnlineServers.func_101013_k();
                     return;
                  }
               }

               synchronized(GuiScreenOnlineServers.func_101007_h()) {
                  GuiScreenOnlineServers.func_101013_k();
                  return;
               }
            }

            synchronized(GuiScreenOnlineServers.func_101007_h()) {
               GuiScreenOnlineServers.func_101013_k();
               return;
            }
         }

         synchronized(GuiScreenOnlineServers.func_101007_h()) {
            GuiScreenOnlineServers.func_101013_k();
            return;
         }
      }

      synchronized(GuiScreenOnlineServers.func_101007_h()) {
         GuiScreenOnlineServers.func_101013_k();
      }

   }
}
