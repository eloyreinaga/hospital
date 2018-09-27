package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Usuarios extends Personas implements Serializable {

    private static final long serialVersionUID = 3223780201467614348L;

    private int id_usuario;
    private String apodo;
    private String clave;
    private String recordatorio;
    private int id_ubicacion_organica;
    private String ubicacion_organica;
    private int id_proceso;
    private int id_farmacia;
    private int cod_esta;
    private int id_rol;
    private String rol;
    private int suma1;
    private int pagina;
    private Date fec_reg;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public int getId_ubicacion_organica() {
        return id_ubicacion_organica;
    }

    public void setId_ubicacion_organica(int id_ubicacion_organica) {
        this.id_ubicacion_organica = id_ubicacion_organica;
    }

    public String getUbicacion_organica() {
        return ubicacion_organica;
    }

    public void setUbicacion_organica(String ubicacion_organica) {
        this.ubicacion_organica = ubicacion_organica;
    }

    public int getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(int id_proceso) {
        this.id_proceso = id_proceso;
    }

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
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

    public int getSuma1() {
        return suma1;
    }

    public void setSuma1(int suma1) {
        this.suma1 = suma1;
    }
    
    public Date getFec_reg() {
        return fec_reg;
    }

    public void setFec_reg(Date fec_reg) {
        this.fec_reg = fec_reg;
    }

}
