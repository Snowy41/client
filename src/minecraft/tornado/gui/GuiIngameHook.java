package tornado.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import tornado.Tornado;
import tornado.utils.Wrapper;

public class GuiIngameHook extends GuiIngame {

    Minecraft mc;


    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc);
        int width = scaledRes.getScaledWidth();
        int height = scaledRes.getScaledHeight();
        int sHeight = Wrapper.fr.FONT_HEIGHT;
        int sWidth = Wrapper.fr.getStringWidth("FPS: " + mc.getDebugFPS());

        drawRect(0 ,  height - 20, width, height, 0x80000000);
        Wrapper.fr.drawString(Tornado.instance.getNamever(), 2, height - sHeight - 3, 0x0E96D0);
        Wrapper.fr.drawString("FPS: " + mc.getDebugFPS(), width - sWidth - 2, height - sHeight - 3, -1);

    }

}
