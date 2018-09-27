package org.ayaic.domain;

import java.io.Serializable;
import java.util.List;

public class Categorias extends Principal implements Serializable {

    private static final long serialVersionUID = -5209838228578339304L;

    private int id_categoria;
    private int id_rol;
    private String categoria;
    private String imagen;
    private List enlaces;

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List getEnlaces() {
        return this.enlaces;
    }

    public void setEnlaces(List enlaces) {
        this.enlaces = enlaces;
    }
}
