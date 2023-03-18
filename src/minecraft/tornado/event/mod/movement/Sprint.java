package tornado.event.mod.movement;

import com.google.common.eventbus.Subscribe;
import org.lwjgl.input.Keyboard;
import tornado.ClickGui.comp.settings.Setting;
import tornado.Tornado;
import tornado.event.mod.Category;
import tornado.event.mod.Mod;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", "Auto Sprint", Category.MOVEMENT, Keyboard.KEY_F);
    }

    @Override
    public void setup() {
        super.setup();
        rSetting(new Setting("Check-Test", this, false));
    }
    public void onEnable() {
        if(this.isEnabled()) {
            mc.thePlayer.setSprinting(true);
            super.onEnable();
        }
    }
    public void onUpdate() {
        if(this.isEnabled()) {
            if(!mc.thePlayer.isBlocking() && !mc.thePlayer.isSneaking()) {
                mc.thePlayer.setSprinting(true);
            }
        }
        super.onUpdate();
    }

    public void onDisable() {
        mc.thePlayer.setSprinting(false);
        super.onDisable();
    }
}
