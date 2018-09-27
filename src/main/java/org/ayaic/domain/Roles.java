package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Roles extends Clientes implements Serializable {

    private static final long serialVersionUID = 6815436652248021991L;

    private int id_usr_rol;
    private int id_rol_padre;
    private String descripcion;
    private Date fec_expiracion;
    private String id_estado;
    private Date fec_registro;
    private Date fec_modificacion;
    private int ult_usuario;
    private int id_rol;
    private String rol;

    public int getId_usr_rol() {
        return id_usr_rol;
    }

    public void setId_usr_rol(int id_usr_rol) {
        this.id_usr_rol = id_usr_rol;
    }

    public int getId_rol_padre() {
        return id_rol_padre;
    }

    public void setId_rol_padre(int id_rol_padre) {
        this.id_rol_padre = id_rol_padre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFec_expiracion() {
        return fec_expiracion;
    }

    public void setFec_expiracion(Date fec_expiracion) {
        this.fec_expiracion = fec_expiracion;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
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

    public int getUlt_usuario() {
        return ult_usuario;
    }

    public void setUlt_usuario(int ult_usuario) {
        this.ult_usuario = ult_usuario;
    }

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
