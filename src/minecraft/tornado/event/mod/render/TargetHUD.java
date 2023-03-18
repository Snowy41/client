package tornado.event.mod.render;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import tornado.Tornado;
import tornado.event.mod.Category;
import tornado.event.mod.Mod;

import java.awt.*;
import java.util.ArrayList;

public class TargetHUD extends Mod {
    EntityLivingBase target;
    private ArrayList<Long> clicks = new ArrayList<Long>();
    private boolean wasPressed;
    private long lastPressed;
    FontRenderer fr = Tornado.instance.mc.fontRendererObj;
    public TargetHUD() {
        super("TargetHUD", "Enables Target-HUD", Category.RENDER, Keyboard.KEY_NONE);
    }


    @Override
    public void draw() {
        super.draw();
        if(enabled) {
            renderTargetHud();
        }
    }
    public int getWidth() {
        if(target != null) {
            return fr.getStringWidth(target.getName()) + 70;
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
    public int getHeight() {
        return fr.FONT_HEIGHT * 2 + 15;
    }

    private void renderTargetHud() {
        if(!(mc.pointedEntity instanceof EntityItemFrame)) {
            EntityLivingBase target = (EntityLivingBase)mc.pointedEntity;
            if(target!=null) {
                final boolean pressed = Mouse.isButtonDown(0);
                if(pressed != this.wasPressed) {
                    this.lastPressed = System.currentTimeMillis();
                    this.wasPressed = pressed;
                    if(pressed) {
                        this.clicks.add(this.lastPressed);
                    }
                }
                Gui.drawRoundedRect(getX(), getY() + 10, getX() + 125, getY() + getHeight() + 15,6,  new Color(49, 49, 49, 211).getRGB());
                renderHealthBar(target);
                if(target.getHealth() > 5) {
                    fr.drawString(String.format("%.0f\u2764", target.getHealth()), getX() + getWidth() + 100, getY() + 18, new Color(232, 232, 232,255).getRGB());
                } else {
                    fr.drawString(String.format("%.0f\u2764", target.getHealth()), getX() + getWidth() + 100, getY() + 18, new Color(138, 39, 39,255).getRGB());

                }

                fr.drawString(String.format("%.2f Blocks", target.getDistance(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)), getX() + 5, getY() + 37,  new Color(232, 232, 232,255).getRGB());
                fr.drawString("Cps: " + getCpsLeft(), getX() + 90, getY() + 37,  new Color(232, 232, 232,255).getRGB());
                fr.drawStringWithShadow(target.getName(), getX() + 28, getY() + 17,  new Color(232, 232, 232,255).getRGB());
                Gui.drawPlayerHead(getX() + 10, getY() + 20, 16, target);
            }
        }
    }
    private void renderHealthBar(EntityLivingBase target) {
        Gui.drawRoundedRect(getX() + 27, getY() + 27, getX() + 120,getY() + 35,8,  new Color(19, 19, 19).getRGB());
        if(target.getHealth() > 5) {
            Gui.drawRoundedRect(getX() + 27, getY() + 27, getX() + 27 + (int)( 93 * (target.getHealth() / target.getMaxHealth())),getY() + 35, 8, new Color(27, 185, 130).getRGB());

        } else {
            Gui.drawRoundedRect(getX() + 27, getY() + 27, getX() + 27 + (int)( 93 * (target.getHealth() / target.getMaxHealth())),getY() + 35,8 ,new Color(96, 18, 18).getRGB());

        }
    }

    private int getCpsLeft() {
        final long time = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks.size();
    }

    public int getX() {
        ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
        return sr.getScaledWidth()/2;
    }

    public int getY () {
        ScaledResolution sr = new ScaledResolution(Tornado.instance.mc);
        return sr.getScaledHeight() / 2;
    }
}


