package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Localidades extends Provincias implements Serializable {

    private static final long serialVersionUID = -6493520527540704170L;

    private int id_localidad;
    private int id_provincia;
    private int id_persona;
    private String localidad;
    private int id_red;
    private String red;
    private String area;
    private String tipo;
    private int cod_esta;
    private int codesta;
    private int compartehcl;
    private String establecimiento;
    private String nombrefact;
    private String nit;
    private String num_auto;
    private String llave;
    private Date fecha;
    private Date fecha2;
    private int num_fact;
    private int hcl;
    private int inst_id;
    private String direccion;
    private String tele1;
    private String tele2;
    private double minimo;
    private double maximo;
    private int izquierda;
    private int derecha;
    private int superior;
    private int inferior;
    private int far_sinstock;
    private int suma;
    private int id_departamento;
    private String departamento;
    private String codigolab;
    private String nombreseg;
    private String cod_cen;
    private String latitud;
    private String longitud;

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getId_red() {
        return id_red;
    }

    public void setId_red(int id_red) {
        this.id_red = id_red;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public int getCodesta() {
        return codesta;
    }

    public void setCodesta(int codesta) {
        this.codesta = codesta;
    }

    public int getCompartehcl() {
        return compartehcl;
    }

    public void setCompartehcl(int compartehcl) {
        this.compartehcl = compartehcl;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getNombrefact() {
        return nombrefact;
    }

    public void setNombrefact(String nombrefact) {
        this.nombrefact = nombrefact;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNum_auto() {
        return num_auto;
    }

    public void setNum_auto(String num_auto) {
        this.num_auto = num_auto;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public int getNum_fact() {
        return num_fact;
    }

    public void setNum_fact(int num_fact) {
        this.num_fact = num_fact;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTele1() {
        return tele1;
    }

    public void setTele1(String tele1) {
        this.tele1 = tele1;
    }

    public String getTele2() {
        return tele2;
    }

    public void setTele2(String tele2) {
        this.tele2 = tele2;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public int getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }

    public int getDerecha() {
        return derecha;
    }

    public void setDerecha(int derecha) {
        this.derecha = derecha;
    }

    public int getSuperior() {
        return superior;
    }

    public void setSuperior(int superior) {
        this.superior = superior;
    }

    public int getInferior() {
        return inferior;
    }

    public void setInferior(int inferior) {
        this.inferior = inferior;
    }

    public int getFar_sinstock() {
        return far_sinstock;
    }

    public void setFar_sinstock(int far_sinstock) {
        this.far_sinstock = far_sinstock;
    }
    
    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigolab() {
        return codigolab;
    }

    public void setCodigolab(String codigolab) {
        this.codigolab = codigolab;
    }

    public String getNombreseg() {
        return nombreseg;
    }

    public void setNombreseg(String nombreseg) {
        this.nombreseg = nombreseg;
    }

    public String getCod_cen() {
        return cod_cen;
    }

    public void setCod_cen(String cod_cen) {
        this.cod_cen = cod_cen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getHcl() {
        return hcl;
    }

    public void setHcl(int hcl) {
        this.hcl = hcl;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

}
