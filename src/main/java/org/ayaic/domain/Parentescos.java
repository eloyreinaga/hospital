package org.ayaic.domain;

import java.io.Serializable;

public class Parentescos implements Serializable {

    private static final long serialVersionUID = 6642701005914876159L;

    private int id_parentesco;
    private String parentesco;
    private String id_estado;
    private int ult_usuario;

    public int getId_parentesco() {
        return id_parentesco;
    }

    public void setId_parentesco(int id_parentesco) {
        this.id_parentesco = id_parentesco;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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
