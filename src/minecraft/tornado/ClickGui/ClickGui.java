package tornado.ClickGui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.block.Block;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tornado.ClickGui.comp.ClickGuiCategoryButton;
import tornado.ClickGui.comp.ModButton;
import tornado.ClickGui.comp.settings.Checkbox;
import tornado.ClickGui.comp.settings.Comp;
import tornado.ClickGui.comp.settings.Setting;
import tornado.ClickGui.manager.CategoryManager;
import tornado.ClickGui.manager.SettingsManager;
import tornado.ClickGui.modSetting.ModSettingGui;
import tornado.Tornado;
import tornado.event.mod.Category;
import tornado.event.mod.Mod;
import tornado.event.mod.ModManager;


public class ClickGui extends GuiScreen{

	public ArrayList<ClickGuiCategoryButton> clickGuiCategoryButton = new ArrayList<>();

	public ArrayList<ModButton> modButtonToRenderCombat = new ArrayList<>();
	public ArrayList<ModButton> modButtonToRenderRender = new ArrayList<>();
	public ArrayList<ModButton> modButtonToRenderPlayer = new ArrayList<>();
	public ArrayList<ModButton> modButtonToRenderMovement = new ArrayList<>();



	ScaledResolution sr;

	int backgroundW = 200;
	int centerW;
	int centerH;

	int buttonCount;

	ResourceLocation logo = new ResourceLocation("tornado/logo.png");
	public ArrayList<ModSettingGui> modSettingRender = new ArrayList<>();

	ModButton test;
	int offset, indexB;

	@Override
	public void initGui() {
		sr = new ScaledResolution(mc);
		centerW = sr.getScaledWidth()/2;
		centerH = sr.getScaledHeight()/2;
		reset();
		buttonCount = 0;
		indexB = 0;
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 65,100,20,  "Combat",0));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 45,100,20,  "Render",1));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 25,100,20,  "Player",2));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 5,100,20,  "Movement",3));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH + 15,100,20,  "Test",4));
			
		int modButtonW = 260;
		int modButtonH = 25;
		Category lastCa = null;
		offset = 0;
		for(Mod mod : Tornado.instance.modManager.mods) {
			if(!mod.name.equalsIgnoreCase("ClickGui")) {
				if(lastCa != mod.category) {
					offset = 0;
				}
				if(mod.category == Category.COMBAT) {
					this.modButtonToRenderCombat.add(new ModButton(centerW, centerH + offset, modButtonW, modButtonH, mod,0));
					offset += 30;

				}
				if (mod.category == Category.RENDER) {
					this.modButtonToRenderRender.add(new ModButton(centerW, centerH + offset, modButtonW, modButtonH, mod,1));

					offset += 30;

				}
				if (mod.category == Category.PLAYER) {
					this.modButtonToRenderPlayer.add(new ModButton(centerW, centerH + offset, modButtonW, modButtonH, mod,2));

					offset += 30;

				}
				if (mod.category == Category.MOVEMENT) {
					this.modButtonToRenderMovement.add(new ModButton(centerW, centerH + offset, modButtonW, modButtonH, mod,3));

					offset += 30;
				}
				lastCa = mod.category;
			}
		}
		for(ModButton modButton : modButtonToRenderCombat) {
			Tornado.instance.clickGui.modSettingRender.add(new ModSettingGui(modButton.mod, modButton));
		}

		for(ModButton modButton : modButtonToRenderPlayer) {
			Tornado.instance.clickGui.modSettingRender.add(new ModSettingGui(modButton.mod, modButton));
		}

		for(ModButton modButton : modButtonToRenderRender) {
			Tornado.instance.clickGui.modSettingRender.add(new ModSettingGui(modButton.mod, modButton));
		}

		for(ModButton modButton : modButtonToRenderMovement) {
			Tornado.instance.clickGui.modSettingRender.add(new ModSettingGui(modButton.mod, modButton));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		//update center w
		centerW = sr.getScaledWidth()/2;
		centerH = sr.getScaledHeight()/2;
		
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		Gui.drawRoundedRect(centerW - backgroundW, centerH - 100, centerW + backgroundW, centerH + 100, 8, new Color(26, 26, 26,255).getRGB());
		Gui.drawRoundedRect(centerW - backgroundW + 390, centerH - 100, centerW + backgroundW, centerH + 100, 8, new Color(32, 219, 155,255).getRGB());
		GlStateManager.popMatrix();


		Gui.drawRoundedRect(centerW - backgroundW, centerH - 100, centerW - backgroundW + 100, centerH - 65,  6, new Color(21, 140, 99,255).getRGB());
		Gui.drawRoundedRect(centerW - backgroundW + 1, centerH - 100 + 1, centerW - backgroundW + 100 - 1, centerH - 65 - 1,  6, new Color(26, 26, 26,255).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(Tornado.instance.getNamever(), centerW - backgroundW + 5, centerH - 95, -1 );
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Player: " + Minecraft.getMinecraft().thePlayer.getName(), centerW - backgroundW + 5, centerH - 80, -1 );
		for(ClickGuiCategoryButton clickGuiCategoryButton :clickGuiCategoryButton) {
			clickGuiCategoryButton.renderButton();
		}
		int wheel = Mouse.getDWheel();
		indexB = 1;
		for(ModButton modButton : modButtonToRenderCombat) {
			if(modButton.id == CategoryManager.currentPage) {
				buttonCount = modButtonToRenderCombat.size();
				if(buttonCount > 8) {
					GL11.glEnable(GL11.GL_SCISSOR_TEST);
					this.glScissor(centerW - backgroundW, centerH - 90, centerW + backgroundW, 180);
					modButton.render();
					if (wheel < 0) {
						modButton.y -= 8;
					} else if (wheel > 0) {
						modButton.y += 8;
					}
					GL11.glDisable(GL11.GL_SCISSOR_TEST);
				} else {
					if(modButton.open) {
						for(int l = 0; l == modButtonToRenderCombat.size(); l++) {
							if(l > indexB) {
								ModButton openButton = modButtonToRenderCombat.get(l);
								openButton.updateRender(20);
							} else {
								modButton.render();
							}
						}
					} else {
						modButton.render();
					}
				}
			}
			indexB++;
		}
		indexB = 1;
		for(ModButton modButton : modButtonToRenderRender) {
			if(modButton.id == CategoryManager.currentPage) {
				buttonCount = modButtonToRenderRender.size();
				if(buttonCount > 8) {
					GL11.glEnable(GL11.GL_SCISSOR_TEST);
					this.glScissor(centerW - backgroundW, centerH - 90, centerW + backgroundW, 180);
					modButton.render();
					if (wheel < 0) {
						modButton.y -= 8;
					} else if (wheel > 0) {
						modButton.y += 8;
					}
					GL11.glDisable(GL11.GL_SCISSOR_TEST);
				} else {
					if(modButton.open) {
						System.out.println("Test1");
						for(int l = 1; l == modButtonToRenderRender.size(); l++) {
							if(indexB <= l) {
								ModButton openButton = modButtonToRenderRender.get(l);
								openButton.updateRender(20);
							} else {
								modButton.render();
							}
						}
					} else {
						modButton.render();
					}
				}
			}
			indexB++;
		}
		indexB = 1;
		for(ModButton modButton : modButtonToRenderPlayer) {
			if(modButton.id == CategoryManager.currentPage) {
				buttonCount = modButtonToRenderPlayer.size();
				if(buttonCount > 8) {
					GL11.glEnable(GL11.GL_SCISSOR_TEST);
					this.glScissor(centerW - backgroundW, centerH - 90, centerW + backgroundW, 180);
					modButton.render();
					if (wheel < 0) {
						modButton.y -= 8;
					} else if (wheel > 0) {
						modButton.y += 8;
					}
					GL11.glDisable(GL11.GL_SCISSOR_TEST);
				} else {
					if(modButton.open) {
						for(int l = 0; l == modButtonToRenderPlayer.size(); l++) {
							if(l > indexB) {
								ModButton openButton = modButtonToRenderPlayer.get(l);
								openButton.updateRender(100);
							} else {
								modButton.render();
							}
						}
					} else {
						modButton.render();
					}
				}
			}
			indexB++;
		}
		int indexC = 0;
		for(ModButton modButton : modButtonToRenderMovement) {
			if(modButton.id == CategoryManager.currentPage) {
				if(modButton == test) {
					break;
				}
				buttonCount = modButtonToRenderMovement.size();
				if(buttonCount > 8) {
					GL11.glEnable(GL11.GL_SCISSOR_TEST);
					this.glScissor(centerW - backgroundW, centerH - 90, centerW + backgroundW, 180);
					modButton.render();
					if (wheel < 0) {
						modButton.y -= 8;
					} else if (wheel > 0) {
						modButton.y += 8;
					}
					GL11.glDisable(GL11.GL_SCISSOR_TEST);
				}
				if(modButton.open) {
					ModButton test2 = modButtonToRenderMovement.get(modButtonToRenderMovement.size() - 1);
					if(modButton != test2) {
						indexC++;
						if(modButtonToRenderMovement.get(indexC) != null) {
							test = modButtonToRenderMovement.get(indexC);
							test.setY(70);
							test.render();
							modButton.render();
							System.out.println(test.mod.name + test.y);
							break;
						}
					} else {
						modButton.render();
					}
				} else {
					if(test != null) {
						test.returnY();
						test.render();
					}
					modButton.render();
				}
			}
			indexC++;
		}
		mc.getTextureManager().bindTexture(logo);
		Gui.drawModalRectWithCustomSizedTexture(centerW - backgroundW + 20, centerH + 30, 0, 0, 64 , 64, 64,64);

        GlStateManager.popAttrib();

	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if(mouseX >= (centerW - backgroundW) && mouseX <= (centerW + backgroundW) && mouseY >= (centerH - 90) && mouseY <= (centerH + 90)) {
			for(ModButton modButton : modButtonToRenderCombat) {
				if(modButton.id == CategoryManager.currentPage) {
					modButton.onClick(mouseX, mouseY, mouseButton);
				}
			}

			for(ModButton modButton : modButtonToRenderRender) {
				if(modButton.id == CategoryManager.currentPage) {
					modButton.onClick(mouseX, mouseY, mouseButton);
				}
			}

			for(ModButton modButton : modButtonToRenderPlayer) {
				if(modButton.id == CategoryManager.currentPage) {
					modButton.onClick(mouseX, mouseY, mouseButton);
				}
			}
			for(ModButton modButton : modButtonToRenderMovement) {
				if(modButton.id == CategoryManager.currentPage) {
					modButton.onClick(mouseX, mouseY, mouseButton);
				}
			}
		}
		for(ClickGuiCategoryButton clickGuiCategoryButton :clickGuiCategoryButton) {
			clickGuiCategoryButton.onClick(mouseX, mouseY, mouseButton);
		}
	}
	public ArrayList<ClickGuiCategoryButton> getClickGuiCategoryButton() {
		return clickGuiCategoryButton;
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		// TODO Auto-generated method stub
		super.keyTyped(typedChar, keyCode);
	}
	private void reset() {
		modButtonToRenderCombat.removeAll(modButtonToRenderCombat);
		modButtonToRenderRender.removeAll(modButtonToRenderRender);
		modButtonToRenderPlayer.removeAll(modButtonToRenderPlayer);
		modButtonToRenderMovement.removeAll(modButtonToRenderMovement);
		clickGuiCategoryButton.removeAll(clickGuiCategoryButton);
	}
	private void glScissor(double x, double y, double width, double height) {

        y += height;

        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        Minecraft mc = Minecraft.getMinecraft();

        GL11.glScissor((int) ((x * mc.displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - y) * mc.displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * mc.displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * mc.displayHeight / scaledResolution.getScaledHeight()));
    }

}
	
	