package tornado.event.mod.movement;

import org.lwjgl.input.Keyboard;
import tornado.ClickGui.comp.settings.Setting;
import tornado.event.mod.Category;
import tornado.event.mod.Mod;

public class Speed extends Mod {

    public Speed() {
        super("Speed", "Speed the player up", Category.MOVEMENT, Keyboard.KEY_NONE);
    }
    @Override
    public void setup() {
        super.setup();
        rSetting(new Setting("Speed-Test", this, false));
    }

    public void onUpdate() {
        if(this.enabled) {
            if (mc.thePlayer.onGround) {
                mc.thePlayer.motionX *= 2.0f;
                mc.thePlayer.motionZ *= 2.0f;
            }
        }
    }
}
