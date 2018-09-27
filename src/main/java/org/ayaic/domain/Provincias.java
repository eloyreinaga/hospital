package org.ayaic.domain;

public class Provincias extends Departamentos {

    private static final long serialVersionUID = -3226272128593614278L;

    private int id_provincia;
    private String provincia;

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
