package tornado.event.mod;

import tornado.event.mod.render.TargetHUD;

import java.util.ArrayList;

public class HudManager {

    public ArrayList<HudMod> hudMods = new ArrayList<>();

    public HudManager() {
        hudMods.add(new TargetHUD());
    }

    public void renderMods() {
        for(HudMod m : hudMods) {
            m.draw();
        }
    }

    //public void onUpdate() {
    //   for(HudMod m : hudMods) {
    //        m.onUpdate();
    //    }
    //}
}
