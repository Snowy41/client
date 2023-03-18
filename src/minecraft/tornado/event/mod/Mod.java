package tornado.event.mod;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tornado.ClickGui.comp.settings.Setting;
import tornado.Tornado;

public class Mod {

    public Minecraft mc = Minecraft.getMinecraft();
    public String name, description;
    public boolean enabled;
    public Category category;
    public int key;

    public Mod(String name, String description, Category category, int key) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = key;
        this.enabled = false;
        setup();
    }
    public void setup() {

    }

    public void toggle() {
        enabled = !enabled;
        if(enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onUpdate() {

    }

    public void onRender() {

    }

    public Minecraft getMc() {
        return mc;
    }

    public void setMc(Minecraft mc) {
        this.mc = mc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    protected final void rSetting(Setting setting) {
        if(setting != null)
            Tornado.instance.getSettingsManager().rSetting(setting);
    }
    protected final Setting getSetting(String name) {
        return Tornado.instance.getSettingsManager().getSettingByName(name);
    }
    public void draw() {

    }
}
