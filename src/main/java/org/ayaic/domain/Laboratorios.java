package org.ayaic.domain;

import java.io.Serializable;

public class Laboratorios implements Serializable {

    private static final long serialVersionUID = 5115989026144115159L;

    private int id_laboratorio;
    private int id_laboratoriog;
    private int id_consultorio;
    private int id_costo;
    private String laboratorio;
    private String id_estado;
    private String cadena;
    private int ult_usuario;
    private int cod_esta;

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public int getId_laboratoriog() {
        return id_laboratoriog;
    }

    public void setId_laboratoriog(int id_laboratoriog) {
        this.id_laboratoriog = id_laboratoriog;
    }

    public int getId_costo() {
        return id_costo;
    }

    public void setId_costo(int id_costo) {
        this.id_costo = id_costo;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
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

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

}
