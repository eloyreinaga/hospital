package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Personas extends Principal implements Serializable {

    private static final long serialVersionUID = -7040285853493866971L;

    private int id_persona;
    private String nombres;
    private String paterno;
    private String materno;
    private String correo;
    private String telefono;
    private String celular;
    private String consultorio;
    private String dip;
    private int id_consultorio;
    private String direccion;
    private int id_pais;
    private int id_departamento;
    private int id_provincia;
    private int id_farmacia;
    private int id_medico;
    private int nropac;
    private int id_localidad;
    private int id_sexo;
    private int id_ecivil;
    private int cod_esta;
    private int urgencias;
    private int id_laboratorio;
    private Date fec_nacimiento;
    private String firma;
    private String establecimiento;
    private String matricula;
    private String codigoprof;
    private String cadena1;
    private String cadena2;
    private String cadena3;
    private String cadena4;
    private int id_piso;
    private int suma1;
    private int suma2;
    private int pagina;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        nombres = nombres.toUpperCase();
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        paterno = paterno.toUpperCase();
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        materno = materno.toUpperCase();
        this.materno = materno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public int getNropac() {
        return nropac;
    }

    public void setNropac(int nropac) {
        this.nropac = nropac;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public int getId_ecivil() {
        return id_ecivil;
    }

    public void setId_ecivil(int id_ecivil) {
        this.id_ecivil = id_ecivil;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public int getUrgencias() {
        return urgencias;
    }

    public void setUrgencias(int urgencias) {
        this.urgencias = urgencias;
    }

    public Date getFec_nacimiento() {
        return fec_nacimiento;
    }

    public void setFec_nacimiento(Date fec_nacimiento) {
        this.fec_nacimiento = fec_nacimiento;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCodigoprof() {
        return codigoprof;
    }

    public void setCodigoprof(String codigoprof) {
        this.codigoprof = codigoprof;
    }

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
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

    public String getCadena3() {
        return cadena3;
    }

    public void setCadena3(String cadena3) {
        this.cadena3 = cadena3;
    }

    public String getCadena4() {
        return cadena4;
    }

    public void setCadena4(String cadena4) {
        this.cadena4 = cadena4;
    }

    public int getSuma1() {
        return suma1;
    }

    public void setSuma1(int suma1) {
        this.suma1 = suma1;
    }

    public int getSuma2() {
        return suma2;
    }

    public void setSuma2(int suma2) {
        this.suma2 = suma2;
    }

}
