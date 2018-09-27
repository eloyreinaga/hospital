package org.ayaic.domain;

import java.io.Serializable;

public class Departamentos extends Paises implements Serializable {

    private static final long serialVersionUID = -4486006280682765639L;

    private int id_departamento;
    private String departamento;
    private String sigla;

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
