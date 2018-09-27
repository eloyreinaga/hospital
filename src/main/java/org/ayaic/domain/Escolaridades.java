package org.ayaic.domain;

import java.io.Serializable;

public class Escolaridades implements Serializable {

    private static final long serialVersionUID = 8724481665032755025L;

    private int id_escolaridad;
    private String escolaridad;
    private String id_estado;
    private int ult_usuario;

    public int getId_escolaridad() {
        return id_escolaridad;
    }

    public void setId_escolaridad(int id_escolaridad) {
        this.id_escolaridad = id_escolaridad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public int getUlt_usuario() {
        return ult_usuario;
    }

    public void setUlt_usuario(int ult_usuario) {
        this.ult_usuario = ult_usuario;
    }

}
