package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Camas implements Serializable {

    private static final long serialVersionUID = 1730269480804562900L;

    private int id_sala;
    private int id_sala0;
    private int id_piso0;
    private int id_piso;
    private int cod_esta;
    private String piso;
    private String sala;
    private int id_cama;
    private String cama;
    private double cama_unit;
    private double cantidad;
    private double total;
    private int id_paciente;
    private int id_persona;
    private Date fecha_ini;
    private Date fecha_fin;
    private int estado;
    private String id_estado;
    private String tipo;

// salas
    public int getId_sala0() {
        return id_sala0;
    }

    public void setId_sala0(int id_sala0) {
        this.id_sala0 = id_sala0;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_piso0() {
        return id_piso0;
    }

    public void setId_piso0(int id_piso0) {
        this.id_piso0 = id_piso0;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public int getId_cama() {
        return id_cama;
    }

    public void setId_cama(int id_cama) {
        this.id_cama = id_cama;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public double getCama_unit() {
        return cama_unit;
    }

    public void setCama_unit(double cama_unit) {
        this.cama_unit = cama_unit;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
