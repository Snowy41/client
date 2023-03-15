package tornado.event.mod.render;

import org.lwjgl.input.Keyboard;
import tornado.Tornado;
import tornado.event.mod.Category;
import tornado.event.mod.Mod;

public class ClickGUI extends Mod {

    public ClickGUI() {
        super("ClickGUI", "Opens the ClickGUI", Category.RENDER, Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Tornado.instance.getClickGui());
        toggle();
    }
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
