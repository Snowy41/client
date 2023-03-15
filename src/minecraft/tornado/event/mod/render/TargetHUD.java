package tornado.event.mod.render;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import tornado.event.mod.HudMod;

import java.awt.*;

public class TargetHUD extends HudMod {
    EntityLivingBase target;
    Color color = new Color(0,0,0,170);
    public TargetHUD() {
        super("TargetHUD", 150, 150);
    }

    @Override
    public void draw() {
        renderTargetHud();;
        super.draw();
    }

    @Override
    public void renderDummy(int mouseX, int mouseY) {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), color.getRGB());
        if(target == null) {
            fr.drawStringWithShadow(mc.thePlayer.getName(), getX() + 2, getY() + 2, -1);
            fr.drawStringWithShadow(mc.thePlayer.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, -1);
            GuiInventory.drawEntityOnScreen(getX() + getPlayerNameWidth() + 25, getY() + 30, 20, 50, 0, mc.thePlayer);
        } else {
            fr.drawStringWithShadow(target.getName(), getX() + 2, getY() + 2, -1);
            fr.drawStringWithShadow(target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, -1);
            GuiInventory.drawEntityOnScreen(getX() + getNameWidth() + 25, getY() + 30, 20, 50, 0, target);
        }


        super.renderDummy(mouseX, mouseY);
    }
    @Override
    public int getWidth() {
        if(target != null) {
            return fr.getStringWidth(target.getName()) + 70;
        }else{
            return fr.getStringWidth(mc.thePlayer.getName()) + 70;
        }

    }
    public int getNameWidth () {
        if(target != null) {
            return fr.getStringWidth(target.getName());
        }else{
            return 0;
        }

    }
    public int getPlayerNameWidth () {
        if(target != null) {
            return fr.getStringWidth(mc.thePlayer.getName());
        }else{
            return 0;
        }

    }
    @Override
    public int getHeight() {
        return fr.FONT_HEIGHT * 2 + 15;
    }

    private void renderTargetHud() {
        target = (EntityLivingBase) mc.pointedEntity;


        if(target != null) {
            fr.drawStringWithShadow(target.getName(), getX() + 2, getY() + 2, -1);
            fr.drawStringWithShadow(target.getHealth() + " \u2764", getX() + 2, getY() + 2 + fr.FONT_HEIGHT, -1);
            GuiInventory.drawEntityOnScreen(getX() + getNameWidth() + 25, getY() + 30, 20, 50, 0, target);
        }

    }

}
