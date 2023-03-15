package tornado.event.mod;

import tornado.event.mod.movement.*;

import java.util.ArrayList;

public class ModManager {

    private static ArrayList<Mod> mods;

    public ModManager() {
        mods = new ArrayList<Mod>();
        //COMBAT//

        //RENDER//

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

    public static void onKey(int k) {
        for(Mod m : mods) {
            if(m.getKey() == k) {
                m.toggle();
            }
        }
    }
}