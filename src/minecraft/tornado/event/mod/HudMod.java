package tornado.event.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import tornado.hud.DraggableComponent;

import java.awt.*;

public class HudMod {

    public Minecraft mc = Minecraft.getMinecraft();
    public FontRenderer fr = mc.fontRendererObj;

    public String name;
    public boolean enabled;
    public int x,y;
    public int height = 50, width = 50;
    Color color = new Color(0,0,0,0);
    public DraggableComponent draggableComponent;

    public HudMod(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;

        draggableComponent = new DraggableComponent(x, y, getWidth(), getHeight(), color.getRGB());
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void draw() {

    }

    public void renderDummy(int mouseX, int mouseY) {
        draggableComponent.draw(mouseX, mouseY);
    }

    public int getX()
    {
        return draggableComponent.getxPosition();
    }
    public int getY()
    {
        return draggableComponent.getyPosition();
    }
}
