package org.ayaic.domain;

import java.io.Serializable;

public class Enlaces extends Categorias implements Serializable {

    private static final long serialVersionUID = 9011698684994413412L;

    private int id_enlace;
    private String enlace;
    private String ruta;
    private int orden;
    private String tabla;
    private String permiso;

    public int getId_enlace() {
        return id_enlace;
    }

    public void setId_enlace(int id_enlace) {
        this.id_enlace = id_enlace;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

}
