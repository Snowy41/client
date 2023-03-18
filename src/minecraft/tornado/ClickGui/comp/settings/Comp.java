package tornado.ClickGui.comp.settings;

import tornado.ClickGui.ClickGui;
import tornado.event.mod.Mod;

public class Comp {

    public double x, y, x2, y2;
    public ClickGui parent;
    public Mod module;
    public Setting setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public void drawScreen() {

    }

    public boolean isInside(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2);
    }

    public void keyTyped(char typedChar, int keyCode) {

    }
}
