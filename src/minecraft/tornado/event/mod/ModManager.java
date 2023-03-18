package tornado.event.mod;

import tornado.event.mod.movement.*;
import tornado.event.mod.render.ClickGUI;
import tornado.event.mod.render.TargetHUD;

import java.util.ArrayList;

public class ModManager {

    public static ArrayList<Mod> mods;

    public ModManager() {
        mods = new ArrayList<Mod>();
        //COMBAT//

        //RENDER//
        addMod(new ClickGUI());
        addMod(new TargetHUD());
        //PLAYER//

        //MOVEMENT//
        addMod(new Sprint());
    }

    public void addMod(Mod m) {
        mods.add(m);
    }
    public static ArrayList<Mod> getActiveModules() {
        return mods;
    }
    public static void onUpdate() {
        for(Mod m : mods) {
            m.onUpdate();
        }
    }

    public static void onRender() {
        for (Mod m : mods) {
            m.onRender();
        }
    }

    public static void onDraw() {
        for (Mod m : mods) {
            m.draw();
        }
    }

    public static void onKey(int k) {
        for(Mod m : mods) {
            if(m.getKey() == k) {
                m.toggle();
            }
        }
    }
}
