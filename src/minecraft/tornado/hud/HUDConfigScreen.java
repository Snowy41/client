package tornado.hud;

import net.minecraft.client.gui.GuiScreen;
import tornado.Tornado;
import tornado.event.mod.HudMod;

public class HUDConfigScreen extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        for(HudMod m : Tornado.instance.hudManager.hudMods) {
            m.renderDummy(mouseX, mouseY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
