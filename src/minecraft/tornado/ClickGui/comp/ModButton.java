package tornado.ClickGui.comp;

import java.awt.Color;
import java.util.ArrayList;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tornado.ClickGui.ClickGui;
import tornado.ClickGui.comp.settings.Checkbox;
import tornado.ClickGui.comp.settings.Comp;
import tornado.ClickGui.comp.settings.Setting;
import tornado.ClickGui.modSetting.ModSettingGui;
import tornado.Tornado;
import tornado.event.mod.Mod;


public class ModButton {

	public int x, y, before;
	public int w, h;
	public Mod mod;
	public int id;
	public boolean open;
	public int nOffset;

	public int difference;
	public int test;
	public ArrayList<Comp> comps = new ArrayList<>();

	public ModButton(int x, int y, int w, int h, Mod mod, int id) {
		this.x = x - 85;
		this.y = y - 80;
		this.before = y - 80;
		this.w = w;
		this.h = h;
		this.mod = mod;
		this.id = id;
		this.open = false;
		difference = 0;
		this.test = y - 80;
	}

	public void render() {
			//Module Button
			Gui.drawRoundedRect(x, y, x + w, y + h, 8, new Color(33, 33, 33, 255).getRGB());
			//Toggle Button
			Gui.drawRoundedRect((x + w) - 28, (y + h) - 18, (x + w) - 5, (y + h) - 7, 8, getColor());
			//Render Slider on either side depending on "enabled"
			if(this.mod.enabled) {
				Gui.drawRoundedRect((x + w) - 14, (y + h) - 16, (x + w) - 7, (y + h) - 9, 8, new Color(26, 26, 26, 255).getRGB());

			} else {
				Gui.drawRoundedRect((x + w) - 26, (y + h) - 16, (x + w) - 19, (y + h) - 9, 8, new Color(23, 23, 23, 255).getRGB());

			}
		Minecraft.getMinecraft().fontRendererObj.drawString(mod.name, x + 8, y + 8, new Color(245, 245, 245, 255).getRGB());
			if(this.open) {
				//Loops through all modSettingGUIs that will be created
				for(ModSettingGui modSettingGui : Tornado.instance.clickGui.modSettingRender) {
					//If the mod of this modSettingGUI is "this.mod"
					if(modSettingGui.mod == this.mod) {
						modSettingGui.render();
						for (Comp comp : comps) {
							comp.drawScreen();
						}
					}
				}
			}
	}
	public void setY(int offset) {
		this.y = this.test + offset;
	}
	public void returnY() {
		this.y = this.before;
	}

	public void updateRender(int offset) {
		//change y-Axis for Settings-field
		this.y = this.y + offset;
		this.nOffset = this.y;
		//Module Button
		Gui.drawRoundedRect(x, y, x + w, (y + h), 8, new Color(33, 33, 33, 255).getRGB());
		//Toggle Button
		Gui.drawRoundedRect((x + w) - 28, ((y + h) - 18), (x + w) - 5, ((y + h) - 7), 8, getColor());
		//Render Slider on either side depending on "enabled"
		if(this.mod.enabled) {
			Gui.drawRoundedRect((x + w) - 14, (y + h) - 16, (x + w) - 7, (y + h) - 9, 8, new Color(26, 26, 26, 255).getRGB());

		} else {
			Gui.drawRoundedRect((x + w) - 26, (y + h) - 16, (x + w) - 19, (y + h) - 9, 8, new Color(23, 23, 23, 255).getRGB());
		}
		Minecraft.getMinecraft().fontRendererObj.drawString(mod.name, x + 8, y + 8, new Color(245, 245, 245, 255).getRGB());
	}

	private int getColor() {
		if (mod.isEnabled()) {
			return new Color(27, 185, 130, 255).getRGB();
		} else {
			return new Color(51, 51, 51, 255).getRGB();
		}
	}
	public void onClick(int mouseX, int mouseY, int button) {
			if (button == 0) {
				if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
					if(mod.enabled) {
						this.open = false;
					}
					mod.setEnabled(!mod.isEnabled());
				}
			} else if (button == 1) {
				if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h){
					open = !open;
					int sOffset = 0;
					comps.clear();
					if (Tornado.instance.getSettingsManager().getSettingsByMod(this.mod) != null) {
						for (Setting setting : Tornado.instance.getSettingsManager().getSettingsByMod(this.mod)) {
							Mod selectedModule = this.mod;
							if (setting.isCheck()) {
								comps.add(new Checkbox(0, sOffset, Tornado.instance.clickGui, selectedModule, setting));
								sOffset += 15;
							}
						}
					}
				} else {
					open = false;

				}
			}

		for (Comp comp : comps) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}
	public int getW() {return w;}
	public int getH() {return h;}


	public int getWidth() {
		ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
		return (sr.getScaledWidth()/2) - 70;
	}

	public int getHeight() {
		ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
		return sr.getScaledHeight()/8;
	}
}
