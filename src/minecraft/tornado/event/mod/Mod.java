package tornado.event.mod;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

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
}
