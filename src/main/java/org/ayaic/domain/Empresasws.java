package org.ayaic.domain;

import java.io.Serializable;

public class Empresasws implements Serializable {

    private static final long serialVersionUID = 461190328736053466L;

    private int id;
    private String detalle;
    private String nropatronal;
    private String razonsocial;
    private String nit;
    private String fecha;
    private String telefonos;
    private String estadomora;
    private String estadoafiliacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNropatronal() {
        return nropatronal;
    }

    public void setNropatronal(String nropatronal) {
        this.nropatronal = nropatronal;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEstadomora() {
        return estadomora;
    }

    public void setEstadomora(String estadomora) {
        this.estadomora = estadomora;
    }

    public String getEstadoafiliacion() {
        return estadoafiliacion;
    }

    public void setEstadoafiliacion(String estadoafiliacion) {
        this.estadoafiliacion = estadoafiliacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
