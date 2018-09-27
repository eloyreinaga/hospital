package org.ayaic.domain;

import java.io.Serializable;

public class Paises implements Serializable {

    private static final long serialVersionUID = -6008314036304391269L;

    private int id_pais;
    private int id_red;
    private String pais;
    private String nacionalidad;
    private String id_estado;
    private int ult_usuario;

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_red() {
        return id_red;
    }

    public void setId_red(int id_red) {
        this.id_red = id_red;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
