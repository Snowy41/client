package tornado.ClickGui.comp.settings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tornado.ClickGui.ClickGui;
import tornado.ClickGui.comp.ModButton;
import tornado.ClickGui.modSetting.ModSettingGui;
import tornado.Tornado;
import tornado.event.mod.Mod;

import java.awt.*;

public class Checkbox extends Comp{

    public ModButton modButton;
    public Checkbox(double x, double y, ClickGui parent, Mod module, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }

    @Override
    public void drawScreen() {
        super.drawScreen();
        for(ModButton mod : Tornado.instance.clickGui.modButtonToRenderCombat) {
            if(mod.mod == module) {
                modButton = mod;
            }
        }
        for(ModButton mod : Tornado.instance.clickGui.modButtonToRenderRender) {
            if(mod.mod == module) {
                modButton = mod;
            }
        }
        for(ModButton mod : Tornado.instance.clickGui.modButtonToRenderPlayer) {
            if(mod.mod == module) {
                modButton = mod;
            }
        }
        for(ModButton mod : Tornado.instance.clickGui.modButtonToRenderMovement) {
            if(mod.mod == module) {
                modButton = mod;
            }
        }
        Gui.drawRoundedRect((float) (modButton.x + x + 5), (float) (modButton.y + y + 30), (float) (modButton.x + x + 20), (float) (modButton.y + y + 45),8, new Color(33, 33, 33).getRGB());
        Gui.drawRoundedRect((float) (modButton.x + x + 6), (float) (modButton.y + y + 31), (float) (modButton.x + x + 19), (float) (modButton.y + y + 44),8, setting.getValBoolean() ? new Color(21, 140, 99).getRGB() : new Color(33, 33, 33).getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawString(setting.getName(), (int)(modButton.x + x + 25), (int)(modButton.y + y + 34), new Color(224, 224, 224).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if(modButton != null) {
            if (isInside(mouseX, mouseY, modButton.x + x + 2, modButton.y + y + 27, modButton.x + x + 23, modButton.y + y + 48) && mouseButton == 0) {
                setting.setValBoolean(!setting.getValBoolean());
            }
        }
    }


}
