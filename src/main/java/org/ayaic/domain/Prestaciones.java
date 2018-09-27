package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Prestaciones implements Serializable {

    private static final long serialVersionUID = 1869448628548993247L;

    private int id_prestacion;
    private int id_detalle;
    private int id_historial;
    private int id_persona;
    private int id_paciente;
    private String prestacion;
    private String descripcion;
    private String paquete;
    private String referido;
    private String cadena1;
    private String cadena2;
    private float costo;
    private int cantidad;
    private int mes;
    private int gestion;
    private int anio;
    private int hcl;
    private Date fecha_ini;
    private Date fecha_fin;
    private int id_grupo;
    private int edad_ini;
    private int edad_fin;
    private int suma1;
    private int suma2;
    private int suma3;
    private int suma4;
    private int suma5;
    private int suma6;
    private int suma7;
    private int suma8;
    private int suma9;
    private int suma10;
    private int suma11;
    private int suma12;
    private int cod_esta;

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_prestacion() {
        return id_prestacion;
    }

    public void setId_prestacion(int id_prestacion) {
        this.id_prestacion = id_prestacion;
    }

    public String getReferido() {
        return referido;
    }

    public void setReferido(String referido) {
        this.referido = referido;
    }

    public String getCadena1() {
        return cadena1;
    }

    public void setCadena1(String cadena1) {
        this.cadena1 = cadena1;
    }

    public String getCadena2() {
        return cadena2;
    }

    public void setCadena2(String cadena2) {
        this.cadena2 = cadena2;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion.toUpperCase();
        this.descripcion = descripcion;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        paquete = paquete.toUpperCase();
        this.paquete = paquete;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        prestacion = prestacion.toUpperCase();
        this.prestacion = prestacion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getHcl() {
        return hcl;
    }

    public void setHcl(int hcl) {
        this.hcl = hcl;
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

    public int getEdad_ini() {
        return edad_ini;
    }

    public void setEdad_ini(int edad_ini) {
        this.edad_ini = edad_ini;
    }

    public int getEdad_fin() {
        return edad_fin;
    }

    public void setEdad_fin(int edad_fin) {
        this.edad_fin = edad_fin;
    }

    public int getSuma10() {
        return suma10;
    }

    public void setSuma10(int suma10) {
        this.suma10 = suma10;
    }

    public int getSuma11() {
        return suma11;
    }

    public void setSuma11(int suma11) {
        this.suma11 = suma11;
    }

    public int getSuma12() {
        return suma12;
    }

    public void setSuma12(int suma12) {
        this.suma12 = suma12;
    }

    public int getSuma9() {
        return suma9;
    }

    public void setSuma9(int suma9) {
        this.suma9 = suma9;
    }

    public int getSuma8() {
        return suma8;
    }

    public void setSuma8(int suma8) {
        this.suma8 = suma8;
    }

    public int getSuma7() {
        return suma7;
    }

    public void setSuma7(int suma7) {
        this.suma7 = suma7;
    }

    public int getSuma6() {
        return suma6;
    }

    public void setSuma6(int suma6) {
        this.suma6 = suma6;
    }

    public int getSuma5() {
        return suma5;
    }

    public void setSuma5(int suma5) {
        this.suma5 = suma5;
    }

    public int getSuma4() {
        return suma4;
    }

    public void setSuma4(int suma4) {
        this.suma4 = suma4;
    }

    public int getSuma3() {
        return suma3;
    }

    public void setSuma3(int suma3) {
        this.suma3 = suma3;
    }

    public int getSuma2() {
        return suma2;
    }

    public void setSuma2(int suma2) {
        this.suma2 = suma2;
    }

    public int getSuma1() {
        return suma1;
    }

    public void setSuma1(int suma1) {
        this.suma1 = suma1;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

}
