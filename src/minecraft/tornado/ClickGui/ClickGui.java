package tornado.ClickGui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tornado.ClickGui.comp.CategoryManager;
import tornado.ClickGui.comp.ClickGuiCategoryButton;
import tornado.ClickGui.comp.ModButton;
import tornado.ClickGui.modSetting.ModSettingManager;
import tornado.Tornado;
import tornado.event.mod.ModManager;
import tornado.event.mod.movement.Sprint;

public class ClickGui extends GuiScreen{

	public static ArrayList<ClickGuiCategoryButton> clickGuiCategoryButton = new ArrayList<>();
	
	public static ArrayList<ModButton> modButtonToRender = new ArrayList<>();
	
	ScaledResolution sr;
	private ModSettingManager msManager;
	
	int backgroundW = 200;
	int centerW;
	int centerH;

	int buttonCount;

	ResourceLocation logo = new ResourceLocation("tornado/logo.png");
	
	
	@Override
	public void initGui() {
		//Enable Minecrafts blur shader
		mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/menu_blur.json"));
		
		sr = new ScaledResolution(mc);
		centerW = sr.getScaledWidth()/2;
		centerH = sr.getScaledHeight()/2;
		reset();
		buttonCount = 0;
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 65,100,20,  "Combat",0));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 45,100,20,  "Render",1));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 25,100,20,  "Player",2));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH - 5,100,20,  "Movement",3));
		this.clickGuiCategoryButton.add(new ClickGuiCategoryButton(centerW - 200, centerH + 15,100,20,  "Test",4));
			
		int modButtonW = 260;
		int modButtonH = 25;
		int spaceBetween = 26;

		//Combat = id0

		//render = id1

		//Player = id2
		this.modButtonToRender.add(new ModButton(centerW,centerH, modButtonW, modButtonH, ModManager.mods.get(1) ,3));

		//Movement = id3

		//this.modButtonToRender.add(new ModButton(centerW,centerH, modButtonW, modButtonH, ModInstances.getModTargetHUD(),3));
		
		msManager = new ModSettingManager(centerW,centerH);
	}
	@Override
    public void onGuiClosed() {
        //Disable Minecrafts blur shader
		mc.entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
        
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		
		super.drawScreen(mouseX, mouseY, partialTicks);
		//update center w
		centerW = sr.getScaledWidth()/2;
		centerH = sr.getScaledHeight()/2;
		
		GlStateManager.pushAttrib(); //idk why i do this i sohuld learn gl
		GlStateManager.pushMatrix();
		Gui.drawRoundedRect(centerW - backgroundW, centerH - 100, centerW + backgroundW, centerH + 100, 8, new Color(26, 26, 26,255).getRGB());
		Gui.drawRoundedRect(centerW - backgroundW + 390, centerH - 100, centerW + backgroundW, centerH + 100, 8, new Color(32, 219, 155,255).getRGB());
		GlStateManager.popMatrix();
		msManager.render();

		Gui.drawRoundedRect(centerW - backgroundW, centerH - 100, centerW - backgroundW + 100, centerH - 65,  6, new Color(21, 140, 99,255).getRGB());
		Gui.drawRoundedRect(centerW - backgroundW + 1, centerH - 100 + 1, centerW - backgroundW + 100 - 1, centerH - 65 - 1,  6, new Color(26, 26, 26,255).getRGB());
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(Tornado.instance.getNamever(), centerW - backgroundW + 5, centerH - 95, -1 );
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Player: " + Minecraft.getMinecraft().thePlayer.getName(), centerW - backgroundW + 5, centerH - 80, -1 );
		for(ClickGuiCategoryButton clickGuiCategoryButton :clickGuiCategoryButton) {
			clickGuiCategoryButton.renderButton();
		}
		
		int wheel = Mouse.getDWheel();
        for (ModButton modButton : modButtonToRender) {
        	if(modButton.id == CategoryManager.currentPage) {
				buttonCount++;
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
        	}
        }


		mc.getTextureManager().bindTexture(logo);
		Gui.drawModalRectWithCustomSizedTexture(centerW - backgroundW + 20, centerH + 30, 0, 0, 64 , 64, 64,64);

        GlStateManager.popAttrib();
		
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if(mouseX >= (centerW - backgroundW) && mouseX <= (centerW + backgroundW) && mouseY >= (centerH - 90) && mouseY <= (centerH + 90)) {
			for(ModButton modButton : modButtonToRender) {
				if(modButton.id == CategoryManager.currentPage) {
					modButton.onClick(mouseX, mouseY, mouseButton);
				}
			}
		}
		
		for(ClickGuiCategoryButton clickGuiCategoryButton :clickGuiCategoryButton) {
			clickGuiCategoryButton.onClick(mouseX, mouseY, mouseButton);
		}
		
	}
	public static ArrayList<ClickGuiCategoryButton> getClickGuiCategoryButton() {
		return clickGuiCategoryButton;
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		// TODO Auto-generated method stub
		super.keyTyped(typedChar, keyCode);
	}
	
	private static void reset() {
		modButtonToRender.removeAll(modButtonToRender);
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
	
	