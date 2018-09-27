package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Principal implements Serializable {

    private static final long serialVersionUID = 8029683914223027121L;

    private int id_usuario;
    private String id_estado;
    private String estado;
    private int gestion;
    private int periodo;
    private Date fec_registro;
    private Date fec_modificacion;
    private Date fec_expiracion;
    private int ult_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public Date getFec_modificacion() {
        return fec_modificacion;
    }

    public void setFec_modificacion(Date fec_modificacion) {
        this.fec_modificacion = fec_modificacion;
    }

    public Date getFec_expiracion() {
        return fec_expiracion;
    }

    public void setFec_expiracion(Date fec_expiracion) {
        this.fec_expiracion = fec_expiracion;
    }

    public int getUlt_usuario() {
        return ult_usuario;
    }

    public void setUlt_usuario(int ult_usuario) {
        this.ult_usuario = ult_usuario;
    }

}
