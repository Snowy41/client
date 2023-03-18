package tornado;

import net.minecraft.client.Minecraft;
import tornado.ClickGui.ClickGui;
import tornado.ClickGui.manager.SettingsManager;
import tornado.event.EventManager;
import tornado.event.mod.ModManager;

public class Tornado {

    public String name = "Tornado", version = "a0.1", author = "TornadoTeam", namever = name + " " + version;
    public static  Tornado instance = new Tornado();
    public Minecraft mc = Minecraft.getMinecraft();

    public EventManager eventManager;
    public ModManager modManager;
    public SettingsManager settingsManager;


    public ClickGui clickGui;
    public void startup() {
        eventManager = new EventManager();
        settingsManager = new SettingsManager();
        modManager = new ModManager();
        clickGui = new ClickGui();
        System.out.println("Starting " + namever + "....");

        EventManager.register(this);
    }

    public void shutdown ( ) {
        System.out.println("Shutting down " + name + "...");


        eventManager.unregister(this);
    }

    public String getNamever() {
        return namever;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }
    public final ClickGui getClickGui() {
        return clickGui;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
