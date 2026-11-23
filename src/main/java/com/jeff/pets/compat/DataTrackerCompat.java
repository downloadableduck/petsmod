package com.jeff.pets.compat;
public class DataTrackerCompat {
    public void startTracking(int id, Object val) {}
    public void set(int id, Object val) {}
    public byte getByte(int id) { return 0; }
    public int getInt(int id) { return 0; }
    public String getString(int id) { return ""; }
    public boolean getBoolean(int id) { return false; }
    public Object get(int id) { return null; }
    public void watch(int id, Object val) {}
}
