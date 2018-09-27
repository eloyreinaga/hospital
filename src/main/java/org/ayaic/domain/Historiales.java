package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Historiales extends Pacientes implements Serializable {

    private static final long serialVersionUID = -343632326736592934L;

    private int id_reservacion;
    private int id_historial;
    private int id_historia;
    private int id_consultorio;
    private int id_cargo;
    private int id_por;
    private int id_persona;
    private int id_farmacia;
    private int id_cama;
    private int id_sala;
    private int id_piso;
    private int id_tipo;
    private int id_riesgo;
    private int vigencia;
    private int triaje;
    private int tipoconsult;
    private String repetida;
    private String registro;
    private String diagnostico;
    private String codigo;
    private String subjetivo;
    private String objetivo;
    private String accion;
    private Date fecha;
    private Date fechalab;
    private String consultorio;
    private int rango;
    private int id_sexo;
    private String expedido;
    private double talla;
    private double peso;
    private double temperatura;
    private String estimc;
    private String fc;
    private String pa;
    private String fr;
    private String so2;
    private String glicemia;
    private int edad_ini;
    private int edad_fin;
    private int lvacunas;
    private int num;
    private int internado;
    private String cama;
    private String sala;
    private String piso;
    private int cod_esta;
    private long suma70;

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public int getId_historia() {
        return id_historia;
    }

    public void setId_historia(int id_historia) {
        this.id_historia = id_historia;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_cama() {
        return id_cama;
    }

    public void setId_cama(int id_cama) {
        this.id_cama = id_cama;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_piso() {
        return id_piso;
    }

    public void setId_piso(int id_piso) {
        this.id_piso = id_piso;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_riesgo() {
        return id_riesgo;
    }

    public void setId_riesgo(int id_riesgo) {
        this.id_riesgo = id_riesgo;
    }

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public int getTriaje() {
        return triaje;
    }

    public void setTriaje(int triaje) {
        this.triaje = triaje;
    }

    public int getTipoconsult() {
        return tipoconsult;
    }

    public void setTipoconsult(int tipoconsult) {
        this.tipoconsult = tipoconsult;
    }

    public String getRepetida() {
        return repetida;
    }

    public void setRepetida(String repetida) {
        this.repetida = repetida;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSubjetivo() {
        return subjetivo;
    }

    public void setSubjetivo(String referencia) {
        this.subjetivo = referencia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechalab() {
        return fechalab;
    }

    public void setFechalab(Date fechalab) {
        this.fechalab = fechalab;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String nreferencia) {
        this.objetivo = nreferencia;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getId_por() {
        return id_por;
    }

    public void setId_por(int id_por) {
        this.id_por = id_por;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstimc() {
        return estimc;
    }

    public void setEstimc(String estimc) {
        this.estimc = estimc;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getGlicemia() {
        return glicemia;
    }

    public void setGlicemia(String glicemia) {
        this.glicemia = glicemia;
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

    public int getLvacunas() {
        return lvacunas;
    }

    public void setLvacunas(int lvacunas) {
        this.lvacunas = lvacunas;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getInternado() {
        return internado;
    }

    public void setInternado(int internado) {
        this.internado = internado;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
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

    public long getSuma70() {
        return suma70;
    }

    public void setSuma70(long suma70) {
        this.suma70 = suma70;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

}
