package tornado.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import tornado.Tornado;
import tornado.utils.Wrapper;

public class GuiIngameHook extends GuiIngame {
    int width = mc.displayWidth;
    int height = mc.displayHeight;
    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);

        drawRect(0 ,  height + 40, width + 110, height + 60, 0x80000000);
        Wrapper.fr.drawString(Tornado.instance.getNamever(), 2, height + 48, 0x0E96D0);
        Wrapper.fr.drawString("FPS: " + mc.getDebugFPS(), 70, height + 48, -1);


    }

}
