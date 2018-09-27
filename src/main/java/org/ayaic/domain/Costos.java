package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Costos implements Serializable {

    private static final long serialVersionUID = 3435462050488193664L;

    private int id_rubro;
    private String rubro;
    private int id_costo;
    private String costo;
    private String normales;
    private double costo_unit;
    private String defecto;
    private String muestra;
    private String laboratorio;
    private int tipo;
    private String id_estado;
    private double cantidad;
    private double total;
    private int id_paciente;
    private int id_prestacion;
    private int id_laboratorio;
    private int id_persona;
    private int id_historial;
    private Date fecha_ini;
    private Date fecha_fin;
    private int id_nivel;
    private int emergencias;
    private int cod_esta;

// rubros
    public int getId_rubro() {
        return id_rubro;
    }

    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    // costos 
    public int getId_costo() {
        return id_costo;
    }

    public void setId_costo(int id_costo) {
        this.id_costo = id_costo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getNormales() {
        return normales;
    }

    public void setNormales(String normales) {
        this.normales = normales;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public double getCosto_unit() {
        return costo_unit;
    }

    public void setCosto_unit(double costo_unit) {
        this.costo_unit = costo_unit;
    }

    public String getDefecto() {
        return defecto;
    }

    public void setDefecto(String defecto) {
        this.defecto = defecto;
    }

    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String muestra) {
        this.muestra = muestra;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
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

    public int getId_prestacion() {
        return id_prestacion;
    }

    public void setId_prestacion(int id_prestacion) {
        this.id_prestacion = id_prestacion;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
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

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public int getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(int emergencias) {
        this.emergencias = emergencias;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

}
