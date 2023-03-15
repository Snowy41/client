package tornado.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import tornado.Tornado;
import tornado.utils.Wrapper;

public class GuiIngameHook extends GuiIngame {

    Minecraft mc = Minecraft.getMinecraft();


    public GuiIngameHook(Minecraft mcIn) {
        super(mcIn);
    }

    public void renderGameOverlay(float p_175180_1_) {
        super.renderGameOverlay(p_175180_1_);
        ScaledResolution scaledRes = new ScaledResolution(mc);
        int width = scaledRes.getScaledWidth();
        int height = scaledRes.getScaledHeight();

        drawRect(0 ,  height - 15, width, height, 0x80000000);
        Wrapper.fr.drawString(Tornado.instance.getNamever(), 2, height, 0x0E96D0);
        Wrapper.fr.drawString("FPS: " + mc.getDebugFPS(), 70, height, -1);


    }

}
