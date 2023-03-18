package tornado.ClickGui.modSetting;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tornado.ClickGui.comp.ModButton;
import tornado.Tornado;
import tornado.event.mod.Mod;

public class ModSettingGui {
	public Mod mod;
	public int x,y,w,h,y1;
	public ModButton button;

	public ModSettingGui(Mod mod, ModButton button) {
		this.w = getW();
		this.h = getH();
		this.mod = mod;
		this.button = button;
		y1 = button.y + button.h;
	}
	
	
	public void render() {
		this.x = button.x;
		this.y = button.y + button.h;
		Gui.drawRoundedRect(button.x, y1, button.x + button.getW() , y1 + h, 8, new Color(66, 66, 66,255).getRGB());
		Gui.drawRoundedRect(button.x, y1 + h - 5, button.x + button.getW() , y1 + h, 6, new Color(21, 140, 99,255).getRGB());
	}


	public int getW() {
		ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
		return (sr.getScaledWidth()/2) - 70;
	}

	public int getH () {
		ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
		return sr.getScaledHeight()/5;
	}


}
