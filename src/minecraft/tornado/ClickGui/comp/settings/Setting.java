package tornado.ClickGui.comp.settings;

import tornado.event.mod.Mod;

import java.util.ArrayList;

public class Setting {

    public String name;
    public Mod parent;
    public String mode;

    public String sval;
    public ArrayList<String> options;

    public boolean bval;

    public double dval;
    public double min;
    public double max;
    public boolean onlyint = false;


    public Setting(String name, Mod parent, String sVal, ArrayList<String> options){
        this.name = name;
        this.parent = parent;
        this.sval = sVal;
        this.options = options;
        this.mode = "Combo";
    }

    public Setting(String name, Mod parent, boolean bVal){
        this.name = name;
        this.parent = parent;
        this.bval = bVal;
        this.mode = "Check";
    }

    public Setting(String name, Mod parent, double dVal, double min, double max, boolean onlyInt){
        this.name = name;
        this.parent = parent;
        this.dval = dVal;
        this.min = min;
        this.max = max;
        this.onlyint = onlyInt;
        this.mode = "Slider";
    }

    public String getName(){
        return name;
    }

    public Mod getParentMod(){
        return parent;
    }

    public String getValString(){
        return this.sval;
    }

    public void setValString(String in){
        this.sval = in;
    }

    public ArrayList<String> getOptions(){
        return this.options;
    }

    public boolean getValBoolean(){
        return this.bval;
    }

    public void setValBoolean(boolean in){
        this.bval = in;
    }

    public double getValDouble(){
        if(this.onlyint){
            this.dval = (int)dval;
        }
        return this.dval;
    }

    public void setValDouble(double in){
        this.dval = in;
    }

    public double getMin(){
        return this.min;
    }

    public double getMax(){
        return this.max;
    }

    public boolean isCombo(){
        return this.mode.equalsIgnoreCase("Combo");
    }

    public boolean isCheck(){
        return this.mode.equalsIgnoreCase("Check");
    }

    public boolean isSlider(){
        return this.mode.equalsIgnoreCase("Slider");
    }

    public boolean onlyInt(){
        return this.onlyint;
    }
}
