package org.ayaic.domain;

import java.io.Serializable;
import java.util.List;

public class Clientes implements Serializable {

    private static final long serialVersionUID = 6713945515720408798L;

    private int id_usuario;
    private int id_consultorio;
    private List roles;
    private String nombres;
    private String departamento;
    private String red;
    private String localidad;
    private String consultorio;
    private String establecimiento;
    private int id_ubicacion_organica;
    private int id_rol;
    private int id_rol2;
    private String rol;
    private int id_nivel_acceso;
    private int id_cargo;
    private int id_farmacia;
    private int id_programa;
    private int id_departamento;
    private int id_especialidad;
    private int id_laboratorio;
    private int id_facultad;
    private int id_almacen;
    private int id_piso;
    private int inst_id;
    private int id_persona;
    private int cod_esta;
    private String almacen;
    private String tipo;
    private String area;
    private String farmacia;
    private String filtro;
    private String permiso;
    private String latitud;
    private String longitud;
    private List almacenes;
    private int gestion;
    private int periodo;
    private String apodo;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
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

    public List getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_rol2() {
        return id_rol2;
    }

    public void setId_rol2(int id_rol2) {
        this.id_rol2 = id_rol2;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId_nivel_acceso() {
        return id_nivel_acceso;
    }

    public void setId_nivel_acceso(int id_nivel_acceso) {
        this.id_nivel_acceso = id_nivel_acceso;
    }

    public int getId_ubicacion_organica() {
        return id_ubicacion_organica;
    }

    public void setId_ubicacion_organica(int id_ubicacion_organica) {
        this.id_ubicacion_organica = id_ubicacion_organica;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getId_facultad() {
        return id_facultad;
    }

    public void setId_facultad(int id_facultad) {
        this.id_facultad = id_facultad;
    }

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public List getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List almacenes) {
        this.almacenes = almacenes;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitudd() {
        return longitud;
    }

    public void setLongitudd(String longitud) {
        this.longitud = longitud;
    }

}
