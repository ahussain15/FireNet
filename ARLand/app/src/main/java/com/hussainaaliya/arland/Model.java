package com.hussainaaliya.arland;

import com.google.ar.core.Plane;

public class Model {
    private int res;
    private String display_name;
    private Plane.Type plane_type;

    public Model(int r, String dis, Plane.Type pt){
        res = r;
        display_name = dis;
        plane_type = pt;
    }

    public Plane.Type getPlane_type() {
        return plane_type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public int getRes() {
        return res;
    }
}
