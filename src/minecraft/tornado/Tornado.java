package tornado;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import tornado.ClickGui.ClickGui;
import tornado.event.EventManager;
import tornado.event.EventTarget;
import tornado.event.impl.ClientTick;
import tornado.event.mod.HudManager;
import tornado.event.mod.ModManager;
import tornado.hud.HUDConfigScreen;

public class Tornado {

    public String name = "Tornado", version = "a0.1", author = "TornadoTeam", namever = name + " " + version;
    public static  Tornado instance = new Tornado();
    public Minecraft mc = Minecraft.getMinecraft();

    public EventManager eventManager;
    public static ModManager modManager;
    public HudManager hudManager;



    public ClickGui clickGui;
    public void startup() {
        eventManager = new EventManager();
        modManager = new ModManager();
        hudManager = new HudManager();
        clickGui = new ClickGui();
        System.out.println("Starting " + namever + "....");

        EventManager.register(this);
    }

    public void shutdown ( ) {
        System.out.println("Shutting down " + name + "...");


        eventManager.unregister(this);
    }

    @EventTarget
    public void onTick(ClientTick event) {
        if(mc.gameSettings.HUD_CONFIG.isPressed()) {
            mc.displayGuiScreen(new HUDConfigScreen());
        }
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
}
