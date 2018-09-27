package org.ayaic.domain;

import java.io.Serializable;

public class Eciviles implements Serializable {

    private static final long serialVersionUID = 1373472270322697750L;

    private int id_ecivil;
    private String ecivil;
    private String id_estado;
    private int ult_usuario;

    public int getId_ecivil() {
        return id_ecivil;
    }

    public void setId_ecivil(int id_ecivil) {
        this.id_ecivil = id_ecivil;
    }

    public String getEcivil() {
        return ecivil;
    }

    public void setEcivil(String ecivil) {
        this.ecivil = ecivil;
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
