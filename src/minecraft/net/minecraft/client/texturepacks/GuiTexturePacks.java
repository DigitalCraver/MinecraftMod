package net.minecraft.client.texturepacks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumOS;
import net.minecraft.util.StringTranslate;
import org.lwjgl.Sys;

@SideOnly(Side.CLIENT)
public class GuiTexturePacks extends GuiScreen
{
    protected GuiScreen guiScreen;
    private int refreshTimer = -1;

    /** the absolute location of this texture pack */
    private String fileLocation = "";

    /**
     * the GuiTexturePackSlot that contains all the texture packs and their descriptions
     */
    private GuiTexturePackSlot guiTexturePackSlot;
    private GameSettings field_96146_n;

    public GuiTexturePacks(GuiScreen par1, GameSettings par2)
    {
        this.guiScreen = par1;
        this.field_96146_n = par2;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        this.buttonList.add(new GuiSmallButton(5, this.width / 2 - 154, this.height - 48, stringtranslate.translateKey("texturePack.openFolder")));
        this.buttonList.add(new GuiSmallButton(6, this.width / 2 + 4, this.height - 48, stringtranslate.translateKey("gui.done")));
        this.mc.texturePackList.updateAvaliableTexturePacks();
        this.fileLocation = (new File(Minecraft.getMinecraftDir(), "texturepacks")).getAbsolutePath();
        this.guiTexturePackSlot = new GuiTexturePackSlot(this);
        this.guiTexturePackSlot.registerScrollButtons(this.buttonList, 7, 8);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 5)
            {
                if (Minecraft.getOs() == EnumOS.MACOS)
                {
                    try
                    {
                        this.mc.func_98033_al().func_98233_a(this.fileLocation);
                        Runtime.getRuntime().exec(new String[] {"/usr/bin/open", this.fileLocation});
                        return;
                    }
                    catch (IOException ioexception)
                    {
                        ioexception.printStackTrace();
                    }
                }
                else if (Minecraft.getOs() == EnumOS.WINDOWS)
                {
                    String s = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] {this.fileLocation});

                    try
                    {
                        Runtime.getRuntime().exec(s);
                        return;
                    }
                    catch (IOException ioexception1)
                    {
                        ioexception1.printStackTrace();
                    }
                }

                boolean flag = false;

                try
                {
                    Class oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {(new File(Minecraft.getMinecraftDir(), "texturepacks")).toURI()});
                }
                catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                    flag = true;
                }

                if (flag)
                {
                    this.mc.func_98033_al().func_98233_a("Opening via system class!");
                    Sys.openURL("file://" + this.fileLocation);
                }
            }
            else if (par1GuiButton.id == 6)
            {
                this.mc.displayGuiScreen(this.guiScreen);
            }
            else
            {
                this.guiTexturePackSlot.actionPerformed(par1GuiButton);
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int par1, int par2, int par3)
    {
        super.mouseMovedOrUp(par1, par2, par3);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.guiTexturePackSlot.drawScreen(par1, par2, par3);

        if (this.refreshTimer <= 0)
        {
            this.mc.texturePackList.updateAvaliableTexturePacks();
            this.refreshTimer += 20;
        }

        StringTranslate stringtranslate = StringTranslate.getInstance();
        this.drawCenteredString(this.fontRenderer, stringtranslate.translateKey("texturePack.title"), this.width / 2, 16, 16777215);
        this.drawCenteredString(this.fontRenderer, stringtranslate.translateKey("texturePack.folderInfo"), this.width / 2 - 77, this.height - 26, 8421504);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        --this.refreshTimer;
    }

    static Minecraft func_73950_a(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73955_b(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73958_c(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73951_d(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73952_e(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73962_f(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73959_g(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73957_h(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73956_i(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73953_j(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_73961_k(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_96143_l(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static Minecraft func_96142_m(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.mc;
    }

    static FontRenderer func_73954_n(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.fontRenderer;
    }

    static FontRenderer func_96145_o(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.fontRenderer;
    }

    static FontRenderer func_96144_p(GuiTexturePacks par0GuiTexturePacks)
    {
        return par0GuiTexturePacks.fontRenderer;
    }
}
