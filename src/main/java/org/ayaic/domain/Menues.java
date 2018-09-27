package org.ayaic.domain;

import java.io.Serializable;

public class Menues extends Enlaces implements Serializable {

    private static final long serialVersionUID = 8918268475365502301L;

    private int id_rol;
    private String rol;

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
