package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Cuentas implements Serializable {

    private static final long serialVersionUID = -2667680649141690204L;

    private int id_cuenta;
    private String cuenta;
    private String codigo;
    private String id_estado;
    private int tipo_cuenta;
    private int id_transaccion;
    private String transaccion;
    private int id_librodiario;
    private double debe;
    private double haber;
    private Date fecha_ini;
    private Date fecha_fin;
    private Date fec_registro;

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String nacionalidad) {
        this.codigo = nacionalidad;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public int getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(int ult_usuario) {
        this.tipo_cuenta = ult_usuario;
    }

    public int getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public int getId_librodiario() {
        return id_librodiario;
    }

    public void setId_librodiario(int id_librodiario) {
        this.id_librodiario = id_librodiario;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
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
}
