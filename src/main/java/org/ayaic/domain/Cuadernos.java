package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Cuadernos extends Historiales implements Serializable {

    private static final long serialVersionUID = -6234295479008946234L;

    private int id_laboratorio;
    private int id_cuaderno;
    private int id_persona;
    private int exodoncia;
    private int restauracion;
    private int periodoncia;
    private int cirugia;
    private int endodoncia;
    private int rayosx;
    private int preventiva;
    private int otras;
    private long num_auto;
    private long num_auto2;
    private String tipo;
    private String tipoconsulta;
    private String contraref;
    private String referido;
    private String tipodent;
    private int primera;
    private int numpieza;
    private int cod_esta;
    private double cm;
    private double m3ds;
    private double m2ds;
    private double m1ds;
    private double ds0;
    private double ds1;
    private double ds2;
    private double ds3;
    //cuaderno1
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
    private int suma13;
    private int suma14;
    private int suma15;
    private int suma16;
    private int suma17;
    private int suma18;
    private int suma19;
    private int suma20;
    private int suma21;
    private int suma22;
    private int suma23;
    private int suma24;
    private int suma25;
    private int suma26;
    private int suma27;
    private int suma28;
    private int suma29;
    private int suma30;
    private int suma31;
    private int suma32;
    private int suma33;
    private int suma34;
    private int suma35;
    private int suma36;
    private int suma37;
    private int suma38;
    private int suma39;
    private int suma40;
    private int suma41;
    private int suma42;
    private int suma43;
    private int suma44;
    private int suma45;
    private int suma46;
    private int suma47;
    private int suma48;
    private int suma49;
    private int suma50;
    private int suma51;
    private int suma52;
    private int suma53;
    private int suma54;
    private int suma55;
    private int suma56;
    private int suma57;
    private int suma58;
    private int suma59;
    private int suma60;
    private int suma61;
    private int suma62;
    private int suma63;
    private int suma64;
    private int suma65;
    private int suma66;
    private int speso;
    private int fuma;
    private int alcohol;
    private int violencia;
    private int auto;
    private int urinaria;
    private int sistemica;
    private int arterial;
    private int diabetes;
    private int glome;
    private int tracto;
    private int lupus;
    private int litiasis;
    private int nooplasia;
    private int nefro;
    private int disli;
    private int frenal;
    private int bajopeso;
    private int prematuro;
    private int eritro;
    private int renal;
    private int tuberculo;
    private int otro;
    private int cardio;
    private int reuma;
    private int cancer;
    private int cancero;
    private int depre;
    private int epilepsia;
    private int psico;
    private int discapa;
    //cuaderno3
    private int orientacion;
    private int diu;
    private int inyectable;
    private int sueros;
    private int curaciones;
    private int condon;
    private int pildora;
    private int aqv;
    private int mnatural;
    private int insumos;
    private int pap;
    private String observa;
    //cuaderno2  
    private int tabletas;
    private int sfembarazada;
    private int sfpuerpera;
    private int vitamina;
    private int semanas;
    private int parto;
    private int sexo;
    private int imc;
    private int nembarazo;
    private int eclam;
    private int aborto;
    private int hemo;
    private int anestesia;
    private int controlpos;
    private String pesonac;
    private String estado;
    private String laboratorio;
    private String resultado;
    private Date fechap;
    private Date fum;
    private Date fechae;
    // cuaderno 4
    private String dglobal;
    private String dcronica;
    private String dhierro;
    private String dvitaa;
    private String lmexclu;
    private int desesti;
    private int seleccion;
    // cuaderno 5

    private int ingreso;
    private String diagnostico_ing;
    private int anastecia;
    private int servicio;
    private String diagnostico;
    private Date fec_ingreso;
    private Date fec_egreso;
    private int egreso;
    private int diasi;
    private int diasc;
    private int tipo_egreso;
    private int fallecido;
    private int fallecidom5;
    private int fallecidoy5;
    // Vacunas
    private String polio;
    private String penta;
    private String anti;
    private String mujerdt;
    private String srp;
    private String fama;
    private int bcg;
    private int sr;
    private int vph;
    private int rhumana;
    private int rcanina;
    private int gestion;
    /* JavaBeans Properties */
    //Laboratorios SANGRE
    private String sglorojos;
    private String sblancos;
    private String splaquetas;
    private String shemoglo;
    private String shemato;
    private String svcm;
    private String shgm;
    private String schcm;
    private String sbas;
    private String seos;
    private String smielo;
    private String sjuy;
    private String scay;
    private String sseg;
    private String slinf;
    private String smono;
    private String smroja;
    private String smblanca;
    private String smplaquetas;
    private String smves;
    private String smreti;
    private String smindreti;
    private String smotros;

    //ORINA
    private String cantidad;
    private String proteinas;
    private String color;
    private String glucosa;
    private String olor;
    private String sangre;
    private String aspecto;
    private String cetonas;
    private String reaccion;
    private String bilirrubina;
    private String densidad;
    private String urabilinogeno;
    private String espuma;
    private String nitritos;
    private String sedimento;
    private String otros;
    private String epiteliales;
    private String leucocitos;
    private String ematies;
    private String piocitos;
    private String bacterias;
    private String cilindros;
    private String granulosos;
    private String hialianos;
    private String leucocitarios;
    private String cristales;
    private String observaciones;

    private Date fech1;
    private Date fech2;
    private Date fech3;
    private Date fech4;
    private Date fech5;
    private Date fech6;
    private Date fech7;
    private Date fech8;
    private Date fech9;
    private Date fech10;
    private Date fech11;
    private Date fech12;
    private Date fech13;
    private Date fech14;
    private Date fech15;
    private Date fech16;
    private Date fech17;
    private Date fech18;
    private Date fech19;
    private Date fech20;
    private Date fech21;
    private Date fech22;
    private Date fech23;
    private Date fech24;
    private Date fech25;
    private Date fech26;
    private Date fech27;
    private Date fech28;
    private Date fech29;
    private Date fech30;
    private Date fech31;
    private Date fech32;
    private Date fech33;
    private Date fech34;
    private Date fech35;

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getControlpos() {
        return controlpos;
    }

    public void setControlpos(int controlpos) {
        this.controlpos = controlpos;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPesonac() {
        return pesonac;
    }

    public void setPesonac(String pesonac) {
        this.pesonac = pesonac;
    }

    public int getAnestesia() {
        return anestesia;
    }

    public void setAnestesia(int anestesia) {
        this.anestesia = anestesia;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public int getParto() {
        return parto;
    }

    public void setParto(int parto) {
        this.parto = parto;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getImc() {
        return imc;
    }

    public void setImc(int imc) {
        this.imc = imc;
    }

    public int getNembarazo() {
        return nembarazo;
    }

    public void setNembarazo(int nembarazo) {
        this.nembarazo = nembarazo;
    }

    public int getEclam() {
        return eclam;
    }

    public void setEclam(int eclam) {
        this.eclam = eclam;
    }

    public int getAborto() {
        return aborto;
    }

    public void setAborto(int aborto) {
        this.aborto = aborto;
    }

    public int getHemo() {
        return hemo;
    }

    public void setHemo(int hemo) {
        this.hemo = hemo;
    }

    public int getVitamina() {
        return vitamina;
    }

    public void setVitamina(int vitamina) {
        this.vitamina = vitamina;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public int getSfpuerpera() {
        return sfpuerpera;
    }

    public void setSfpuerpera(int sfpuerpera) {
        this.sfpuerpera = sfpuerpera;
    }

    public int getSfembarazada() {
        return sfembarazada;
    }

    public void setSfembarazada(int sfembarazada) {
        this.sfembarazada = sfembarazada;
    }

    public int getTabletas() {
        return tabletas;
    }

    public void setTabletas(int tabletas) {
        this.tabletas = tabletas;
    }

    public int getPap() {
        return pap;
    }

    public void setPap(int pap) {
        this.pap = pap;
    }

    public String getObserva() {
        return observa;
    }

    public void setObserva(String observa) {
        this.observa = observa;
    }

    public int getInsumos() {
        return insumos;
    }

    public void setInsumos(int insumos) {
        this.insumos = insumos;
    }

    public int getMnatural() {
        return mnatural;
    }

    public void setMnatural(int mnatural) {
        this.mnatural = mnatural;
    }

    public int getAqv() {
        return aqv;
    }

    public void setAqv(int aqv) {
        this.aqv = aqv;
    }

    public int getPildora() {
        return pildora;
    }

    public void setPildora(int pildora) {
        this.pildora = pildora;
    }

    public int getCondon() {
        return condon;
    }

    public void setCondon(int condon) {
        this.condon = condon;
    }

    public int getInyectable() {
        return inyectable;
    }

    public void setInyectable(int inyectable) {
        this.inyectable = inyectable;
    }

    public int getSueros() {
        return sueros;
    }

    public void setSueros(int sueros) {
        this.sueros = sueros;
    }

    public int getCuraciones() {
        return curaciones;
    }

    public void setCuraciones(int curaciones) {
        this.curaciones = curaciones;
    }

    public int getDiu() {
        return diu;
    }

    public void setDiu(int diu) {
        this.diu = diu;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }

    public int getSpeso() {
        return speso;
    }

    public void setSpeso(int speso) {
        this.speso = speso;
    }

    public int getFuma() {
        return fuma;
    }

    public void setFuma(int fuma) {
        this.fuma = fuma;
    }

    public int getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(int alcohol) {
        this.alcohol = alcohol;
    }

    public int getViolencia() {
        return violencia;
    }

    public void setViolencia(int violencia) {
        this.violencia = violencia;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public int getUrinaria() {
        return urinaria;
    }

    public void setUrinaria(int urinaria) {
        this.urinaria = urinaria;
    }

    public int getSistemica() {
        return sistemica;
    }

    public void setSistemica(int sistemica) {
        this.sistemica = sistemica;
    }

    public int getArterial() {
        return arterial;
    }

    public void setArterial(int arterial) {
        this.arterial = arterial;
    }

    public int getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(int diabetes) {
        this.diabetes = diabetes;
    }

    public int getGlome() {
        return glome;
    }

    public void setGlome(int glome) {
        this.glome = glome;
    }

    public int getTracto() {
        return tracto;
    }

    public void setTracto(int tracto) {
        this.tracto = tracto;
    }

    public int getLupus() {
        return lupus;
    }

    public void setLupus(int lupus) {
        this.lupus = lupus;
    }

    public int getLitiasis() {
        return litiasis;
    }

    public void setLitiasis(int litiasis) {
        this.litiasis = litiasis;
    }

    public int getNooplasia() {
        return nooplasia;
    }

    public void setNooplasia(int nooplasia) {
        this.nooplasia = nooplasia;
    }

    public int getNefro() {
        return nefro;
    }

    public void setNefro(int nefro) {
        this.nefro = nefro;
    }

    public int getDisli() {
        return disli;
    }

    public void setDisli(int disli) {
        this.disli = disli;
    }

    public int getFrenal() {
        return frenal;
    }

    public void setFrenal(int frenal) {
        this.frenal = frenal;
    }

    public int getBajopeso() {
        return bajopeso;
    }

    public void setBajopeso(int bajopeso) {
        this.bajopeso = bajopeso;
    }

    public int getPrematuro() {
        return prematuro;
    }

    public void setPrematuro(int prematuro) {
        this.prematuro = prematuro;
    }

    public int getEritro() {
        return eritro;
    }

    public void setEritro(int eritro) {
        this.eritro = eritro;
    }

    public int getRenal() {
        return renal;
    }

    public void setRenal(int renal) {
        this.renal = renal;
    }

    public int getTuberculo() {
        return tuberculo;
    }

    public void setTuberculo(int tuberculo) {
        this.tuberculo = tuberculo;
    }

    public int getOtro() {
        return otro;
    }

    public void setOtro(int otro) {
        this.otro = otro;
    }

    public int getCardio() {
        return cardio;
    }

    public void setCardio(int cardio) {
        this.cardio = cardio;
    }

    public int getReuma() {
        return reuma;
    }

    public void setReuma(int reuma) {
        this.reuma = reuma;
    }

    public int getCancer() {
        return cancer;
    }

    public void setCancer(int cancer) {
        this.cancer = cancer;
    }

    public int getCancero() {
        return cancero;
    }

    public void setCancero(int cancero) {
        this.cancero = cancero;
    }

    public int getDepre() {
        return depre;
    }

    public void setDepre(int depre) {
        this.depre = depre;
    }

    public int getEpilepsia() {
        return epilepsia;
    }

    public void setEpilepsia(int epilepsia) {
        this.epilepsia = epilepsia;
    }

    public int getPsico() {
        return psico;
    }

    public void setPsico(int psico) {
        this.psico = psico;
    }

    public int getDiscapa() {
        return discapa;
    }

    public void setDiscapa(int discapa) {
        this.discapa = discapa;
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

    public int getSuma7() {
        return suma7;
    }

    public void setSuma7(int suma7) {
        this.suma7 = suma7;
    }

    public int getSuma8() {
        return suma8;
    }

    public void setSuma8(int suma8) {
        this.suma8 = suma8;
    }

    public int getSuma9() {
        return suma9;
    }

    public void setSuma9(int suma9) {
        this.suma9 = suma9;
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

    public int getSuma13() {
        return suma13;
    }

    public void setSuma13(int suma13) {
        this.suma13 = suma13;
    }

    public int getSuma14() {
        return suma14;
    }

    public void setSuma14(int suma14) {
        this.suma14 = suma14;
    }

    public int getSuma15() {
        return suma15;
    }

    public void setSuma15(int suma15) {
        this.suma15 = suma15;
    }

    public int getSuma16() {
        return suma16;
    }

    public void setSuma16(int suma16) {
        this.suma16 = suma16;
    }

    public int getSuma17() {
        return suma17;
    }

    public void setSuma17(int suma17) {
        this.suma17 = suma17;
    }

    public int getSuma18() {
        return suma18;
    }

    public void setSuma18(int suma18) {
        this.suma18 = suma18;
    }

    public int getSuma19() {
        return suma19;
    }

    public void setSuma19(int suma19) {
        this.suma19 = suma19;
    }

    public int getSuma20() {
        return suma20;
    }

    public void setSuma20(int suma20) {
        this.suma20 = suma20;
    }

    public int getSuma21() {
        return suma21;
    }

    public void setSuma21(int suma21) {
        this.suma21 = suma21;
    }

    public int getSuma22() {
        return suma22;
    }

    public void setSuma22(int suma22) {
        this.suma22 = suma22;
    }

    public int getSuma23() {
        return suma23;
    }

    public void setSuma23(int suma23) {
        this.suma23 = suma23;
    }

    public int getSuma24() {
        return suma24;
    }

    public void setSuma24(int suma24) {
        this.suma24 = suma24;
    }

    public int getSuma25() {
        return suma25;
    }

    public void setSuma25(int suma25) {
        this.suma25 = suma25;
    }

    public int getSuma26() {
        return suma26;
    }

    public void setSuma26(int suma26) {
        this.suma26 = suma26;
    }

    public int getSuma27() {
        return suma27;
    }

    public void setSuma27(int suma27) {
        this.suma27 = suma27;
    }

    public int getSuma28() {
        return suma28;
    }

    public void setSuma28(int suma28) {
        this.suma28 = suma28;
    }

    public int getSuma29() {
        return suma29;
    }

    public void setSuma29(int suma29) {
        this.suma29 = suma29;
    }

    public int getSuma30() {
        return suma30;
    }

    public void setSuma30(int suma30) {
        this.suma30 = suma30;
    }

    public int getSuma31() {
        return suma31;
    }

    public void setSuma31(int suma31) {
        this.suma31 = suma31;
    }

    public int getSuma32() {
        return suma32;
    }

    public void setSuma32(int suma32) {
        this.suma32 = suma32;
    }

    public int getSuma33() {
        return suma33;
    }

    public void setSuma33(int suma33) {
        this.suma33 = suma33;
    }

    public int getSuma34() {
        return suma34;
    }

    public void setSuma34(int suma34) {
        this.suma34 = suma34;
    }

    public int getSuma35() {
        return suma35;
    }

    public void setSuma35(int suma35) {
        this.suma35 = suma35;
    }

    public int getSuma36() {
        return suma36;
    }

    public void setSuma36(int suma36) {
        this.suma36 = suma36;
    }

    public int getSuma37() {
        return suma37;
    }

    public void setSuma37(int suma37) {
        this.suma37 = suma37;
    }

    public int getSuma38() {
        return suma38;
    }

    public void setSuma38(int suma38) {
        this.suma38 = suma38;
    }

    public int getSuma39() {
        return suma39;
    }

    public void setSuma39(int suma39) {
        this.suma39 = suma39;
    }

    public int getSuma40() {
        return suma40;
    }

    public void setSuma40(int suma40) {
        this.suma40 = suma40;
    }

    public int getSuma41() {
        return suma41;
    }

    public void setSuma41(int suma41) {
        this.suma41 = suma41;
    }

    public int getSuma42() {
        return suma42;
    }

    public void setSuma42(int suma42) {
        this.suma42 = suma42;
    }

    public int getSuma43() {
        return suma43;
    }

    public void setSuma43(int suma43) {
        this.suma43 = suma43;
    }

    public int getSuma44() {
        return suma44;
    }

    public void setSuma44(int suma44) {
        this.suma44 = suma44;
    }

    public int getSuma45() {
        return suma45;
    }

    public void setSuma45(int suma45) {
        this.suma45 = suma45;
    }

    public int getSuma46() {
        return suma46;
    }

    public void setSuma46(int suma46) {
        this.suma46 = suma46;
    }

    public int getSuma47() {
        return suma47;
    }

    public void setSuma47(int suma47) {
        this.suma47 = suma47;
    }

    public int getSuma48() {
        return suma48;
    }

    public void setSuma48(int suma48) {
        this.suma48 = suma48;
    }

    public int getSuma49() {
        return suma49;
    }

    public void setSuma49(int suma49) {
        this.suma49 = suma49;
    }

    public int getSuma50() {
        return suma50;
    }

    public void setSuma50(int suma50) {
        this.suma50 = suma50;
    }

    public int getSuma51() {
        return suma51;
    }

    public void setSuma51(int suma51) {
        this.suma51 = suma51;
    }

    public int getSuma52() {
        return suma52;
    }

    public void setSuma52(int suma52) {
        this.suma52 = suma52;
    }

    public int getSuma53() {
        return suma53;
    }

    public void setSuma53(int suma53) {
        this.suma53 = suma53;
    }

    public int getSuma54() {
        return suma54;
    }

    public void setSuma54(int suma54) {
        this.suma54 = suma54;
    }

    public int getSuma55() {
        return suma55;
    }

    public void setSuma55(int suma55) {
        this.suma55 = suma55;
    }

    public int getSuma56() {
        return suma56;
    }

    public void setSuma56(int suma56) {
        this.suma56 = suma56;
    }

    public int getSuma57() {
        return suma57;
    }

    public void setSuma57(int suma57) {
        this.suma57 = suma57;
    }

    public int getSuma58() {
        return suma58;
    }

    public void setSuma58(int suma58) {
        this.suma58 = suma58;
    }

    public int getSuma59() {
        return suma59;
    }

    public void setSuma59(int suma59) {
        this.suma59 = suma59;
    }

    public int getSuma60() {
        return suma60;
    }

    public void setSuma60(int suma60) {
        this.suma60 = suma60;
    }

    public int getSuma61() {
        return suma61;
    }

    public void setSuma61(int suma61) {
        this.suma61 = suma61;
    }

    public int getSuma62() {
        return suma62;
    }

    public void setSuma62(int suma62) {
        this.suma62 = suma62;
    }

    public int getSuma63() {
        return suma63;
    }

    public void setSuma63(int suma63) {
        this.suma63 = suma63;
    }

    public int getSuma64() {
        return suma64;
    }

    public void setSuma64(int suma64) {
        this.suma64 = suma64;
    }

    public int getSuma65() {
        return suma65;
    }

    public void setSuma65(int suma65) {
        this.suma65 = suma65;
    }

    public int getSuma66() {
        return suma66;
    }

    public void setSuma66(int suma66) {
        this.suma66 = suma66;
    }

    public double getM3ds() {
        return m3ds;
    }

    public void setM3ds(double m3ds) {
        this.m3ds = m3ds;
    }

    public double getM2ds() {
        return m2ds;
    }

    public void setM2ds(double m2ds) {
        this.m2ds = m2ds;
    }

    public double getM1ds() {
        return m1ds;
    }

    public void setM1ds(double m1ds) {
        this.m1ds = m1ds;
    }

    public double getCm() {
        return cm;
    }

    public void setCm(double cm) {
        this.cm = cm;
    }

    public double getDs0() {
        return ds0;
    }

    public void setDs0(double ds0) {
        this.ds0 = ds0;
    }

    public double getDs1() {
        return ds1;
    }

    public void setDs1(double ds1) {
        this.ds1 = ds1;
    }

    public double getDs2() {
        return ds2;
    }

    public void setDs2(double ds2) {
        this.ds2 = ds2;
    }

    public double getDs3() {
        return ds3;
    }

    public void setDs3(double ds3) {
        this.ds3 = ds3;
    }

    public int getPrimera() {
        return primera;
    }

    public void setPrimera(int primera) {
        this.primera = primera;
    }

    public int getNumpieza() {
        return numpieza;
    }

    public void setNumpieza(int numpieza) {
        this.numpieza = numpieza;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public String getTipodent() {
        return tipodent;
    }

    public void setTipodent(String tipodent) {
        this.tipodent = tipodent;
    }

    public String getReferido() {
        return referido;
    }

    public void setReferido(String referido) {
        this.referido = referido;
    }

    public String getContraref() {
        return contraref;
    }

    public void setContraref(String contraref) {
        this.contraref = contraref;
    }

    public String getTipoconsulta() {
        return tipoconsulta;
    }

    public void setTipoconsulta(String tipoconsulta) {
        this.tipoconsulta = tipoconsulta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getOtras() {
        return otras;
    }

    public void setOtras(int otras) {
        this.otras = otras;
    }

    public int getPreventiva() {
        return preventiva;
    }

    public void setPreventiva(int preventiva) {
        this.preventiva = preventiva;
    }

    public int getRayosx() {
        return rayosx;
    }

    public void setRayosx(int rayosx) {
        this.rayosx = rayosx;
    }

    public int getEndodoncia() {
        return endodoncia;
    }

    public void setEndodoncia(int endodoncia) {
        this.endodoncia = endodoncia;
    }

    public int getCirugia() {
        return cirugia;
    }

    public void setCirugia(int cirugia) {
        this.cirugia = cirugia;
    }

    public int getPeriodoncia() {
        return periodoncia;
    }

    public void setPeriodoncia(int periodoncia) {
        this.periodoncia = periodoncia;
    }

    public int getRestauracion() {
        return restauracion;
    }

    public void setRestauracion(int restauracion) {
        this.restauracion = restauracion;
    }

    public int getExodoncia() {
        return exodoncia;
    }

    public void setExodoncia(int exodoncia) {
        this.exodoncia = exodoncia;
    }
//////modificador  

    public int getId_cuaderno() {
        return id_cuaderno;
    }

    public void setId_cuaderno(int id_cuaderno) {
        this.id_cuaderno = id_cuaderno;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_laboratorio() {
        return id_laboratorio;
    }

    public void setId_laboratorio(int id_laboratorio) {
        this.id_laboratorio = id_laboratorio;
    }

    public Date getFechap() {
        return fechap;
    }

    public void setFechap(Date fechap) {
        this.fechap = fechap;
    }

    public Date getFum() {
        return fum;
    }

    public void setFum(Date fum) {
        this.fum = fum;
    }

    public Date getFechae() {
        return fechae;
    }

    public void setFechae(Date fechae) {
        this.fechae = fechae;
    }
    //cuaderno5

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

    public String getDiagnostico_ing() {
        return diagnostico_ing;
    }

    public void setDiagnostico_ing(String diagnostico_ing) {
        this.diagnostico_ing = diagnostico_ing;
    }

    public int getAnastecia() {
        return anastecia;
    }

    public void setAnastecia(int anastecia) {
        this.anastecia = anastecia;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFec_ingreso() {
        return fec_ingreso;
    }

    public void setFec_ingreso(Date fec_ingreso) {
        this.fec_ingreso = fec_ingreso;
    }

    public Date getFec_egreso() {
        return fec_egreso;
    }

    public void setFec_egreso(Date fec_egreso) {
        this.fec_egreso = fec_egreso;
    }

    public int getEgreso() {
        return egreso;
    }

    public void setEgreso(int egreso) {
        this.egreso = egreso;
    }

    public int getDiasi() {
        return diasi;
    }

    public void setDiasi(int diasi) {
        this.diasi = diasi;
    }

    public int getDiasc() {
        return diasc;
    }

    public void setDiasc(int diasc) {
        this.diasc = diasc;
    }

    public int getTipo_egreso() {
        return tipo_egreso;
    }

    public void setTipo_egreso(int tipo_egreso) {
        this.tipo_egreso = tipo_egreso;
    }

    public int getFallecido() {
        return fallecido;
    }

    public void setFallecido(int fallecido) {
        this.fallecido = fallecido;
    }

    public int getFallecidom5() {
        return fallecidom5;
    }

    public void setFallecidom5(int fallecidom5) {
        this.fallecidom5 = fallecidom5;
    }

    public int getFallecidoy5() {
        return fallecidoy5;
    }

    public void setFallecidoy5(int fallecidoy5) {
        this.fallecidoy5 = fallecidoy5;
    }
    //cuaderno4

    public String getDglobal() {
        return dglobal;
    }

    public void setDglobal(String dglobal) {
        this.dglobal = dglobal;
    }

    public String getDcronica() {
        return dcronica;
    }

    public void setDcronica(String dcronica) {
        this.dcronica = dcronica;
    }

    public String getDhierro() {
        return dhierro;
    }

    public void setDhierro(String dhierro) {
        this.dhierro = dhierro;
    }

    public String getDvitaa() {
        return dvitaa;
    }

    public void setDvitaa(String dvitaa) {
        this.dvitaa = dvitaa;
    }

    public String getLmexclu() {
        return lmexclu;
    }

    public void setLmexclu(String lmexclu) {
        this.lmexclu = lmexclu;
    }

    public int getDesesti() {
        return desesti;
    }

    public void setDesesti(int desesti) {
        this.desesti = desesti;
    }

    public int getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(int seleccion) {
        this.seleccion = seleccion;
    }

    public String getPolio() {
        return polio;
    }

    public void setPolio(String polio) {
        this.polio = polio;
    }

    public String getPenta() {
        return penta;
    }

    public void setPenta(String penta) {
        this.penta = penta;
    }

    public String getAnti() {
        return anti;
    }

    public void setAnti(String anti) {
        this.anti = anti;
    }

    public String getMujerdt() {
        return mujerdt;
    }

    public void setMujerdt(String mujerdt) {
        this.mujerdt = mujerdt;
    }

    public int getBcg() {
        return bcg;
    }

    public void setBcg(int bcg) {
        this.bcg = bcg;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public int getVph() {
        return vph;
    }

    public void setVph(int vph) {
        this.vph = vph;
    }

    public int getRhumana() {
        return rhumana;
    }

    public void setRhumana(int rhumana) {
        this.rhumana = rhumana;
    }

    public int getRcanina() {
        return rcanina;
    }

    public void setRcanina(int rcanina) {
        this.rcanina = rcanina;
    }

    public String getSrp() {
        return srp;
    }

    public void setSrp(String srp) {
        this.srp = srp;
    }

    public String getFama() {
        return fama;
    }

    public void setFama(String fama) {
        this.fama = fama;
    }

    //Laboratorios hemograma y examen de orina
    public String getSglorojos() {
        return sglorojos;
    }

    public void setSglorojos(String sglorojos) {
        this.sglorojos = sglorojos;
    }

    public String getSblancos() {
        return sblancos;
    }

    public void setSblancos(String sblancos) {
        this.sblancos = sblancos;
    }

    public String getSplaquetas() {
        return splaquetas;
    }

    public void setSplaquetas(String splaquetas) {
        this.splaquetas = splaquetas;
    }

    public String getShemoglo() {
        return shemoglo;
    }

    public void setShemoglo(String shemoglo) {
        this.shemoglo = shemoglo;
    }

    public String getShemato() {
        return shemato;
    }

    public void setShemato(String shemato) {
        this.shemato = shemato;
    }

    public String getSvcm() {
        return svcm;
    }

    public void setSvcm(String svcm) {
        this.svcm = svcm;
    }

    public String getShgm() {
        return shgm;
    }

    public void setShgm(String shgm) {
        this.shgm = shgm;
    }

    public String getSchcm() {
        return schcm;
    }

    public void setSchcm(String schcm) {
        this.schcm = schcm;
    }

    public String getSbas() {
        return sbas;
    }

    public void setSbas(String sbas) {
        this.sbas = sbas;
    }

    public String getSeos() {
        return seos;
    }

    public void setSeos(String seos) {
        this.seos = seos;
    }

    public String getSmielo() {
        return smielo;
    }

    public void setSmielo(String smielo) {
        this.smielo = smielo;
    }

    public String getSjuy() {
        return sjuy;
    }

    public void setSjuy(String sjuy) {
        this.sjuy = sjuy;
    }

    public String getScay() {
        return scay;
    }

    public void setScay(String scay) {
        this.scay = scay;
    }

    public String getSseg() {
        return sseg;
    }

    public void setSseg(String sseg) {
        this.sseg = sseg;
    }

    public String getSlinf() {
        return slinf;
    }

    public void setSlinf(String slinf) {
        this.slinf = slinf;
    }

    public String getSmono() {
        return smono;
    }

    public void setSmono(String smono) {
        this.smono = smono;
    }

    public String getSmroja() {
        return smroja;
    }

    public void setSmroja(String smroja) {
        this.smroja = smroja;
    }

    public String getSmblanca() {
        return smblanca;
    }

    public void setSmblanca(String smblanca) {
        this.smblanca = smblanca;
    }

    public String getSmplaquetas() {
        return smplaquetas;
    }

    public void setSmplaquetas(String smplaquetas) {
        this.smplaquetas = smplaquetas;
    }

    public String getSmves() {
        return smves;
    }

    public void setSmves(String smves) {
        this.smves = smves;
    }

    public String getSmreti() {
        return smreti;
    }

    public void setSmreti(String smreti) {
        this.smreti = smreti;
    }

    public String getSmindreti() {
        return smindreti;
    }

    public void setSmindreti(String smindreti) {
        this.smindreti = smindreti;
    }

    public String getSmotros() {
        return smotros;
    }

    public void setSmotros(String smotros) {
        this.smotros = smotros;
    }

//ORINA
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getProteinas() {
        return proteinas;
    }

    public void setProteinas(String proteinas) {
        this.proteinas = proteinas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGlucosa() {
        return glucosa;
    }

    public void setGlucosa(String glucosa) {
        this.glucosa = glucosa;
    }

    public String getOlor() {
        return olor;
    }

    public void setOlor(String olor) {
        this.olor = olor;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public String getCetonas() {
        return cetonas;
    }

    public void setCetonas(String cetonas) {
        this.cetonas = cetonas;
    }

    public String getReaccion() {
        return reaccion;
    }

    public void setReaccion(String reaccion) {
        this.reaccion = reaccion;
    }

    public String getBilirrubina() {
        return bilirrubina;
    }

    public void setBilirrubina(String bilirrubina) {
        this.bilirrubina = bilirrubina;
    }

    public String getDensidad() {
        return densidad;
    }

    public void setDensidad(String densidad) {
        this.densidad = densidad;
    }

    public String getUrabilinogeno() {
        return urabilinogeno;
    }

    public void setUrabilinogeno(String urabilinogeno) {
        this.urabilinogeno = urabilinogeno;
    }

    public String getEspuma() {
        return espuma;
    }

    public void setEspuma(String espuma) {
        this.espuma = espuma;
    }

    public String getNitritos() {
        return nitritos;
    }

    public void setNitritos(String nitritos) {
        this.nitritos = nitritos;
    }

    public String getSedimento() {
        return sedimento;
    }

    public void setSedimento(String sedimento) {
        this.sedimento = sedimento;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getEpiteliales() {
        return epiteliales;
    }

    public void setEpiteliales(String epiteliales) {
        this.epiteliales = epiteliales;
    }

    public String getLeucocitos() {
        return leucocitos;
    }

    public void setLeucocitos(String leucocitos) {
        this.leucocitos = leucocitos;
    }

    public String getEmaties() {
        return ematies;
    }

    public void setEmaties(String ematies) {
        this.ematies = ematies;
    }

    public String getPiocitos() {
        return piocitos;
    }

    public void setPiocitos(String piocitos) {
        this.piocitos = piocitos;
    }

    public String getBacterias() {
        return bacterias;
    }

    public void setBacterias(String bacterias) {
        this.bacterias = bacterias;
    }

    public String getCilindros() {
        return cilindros;
    }

    public void setCilindros(String cilindros) {
        this.cilindros = cilindros;
    }

    public String getGranulosos() {
        return granulosos;
    }

    public void setGranulosos(String granulosos) {
        this.granulosos = granulosos;
    }

    public String getHialianos() {
        return hialianos;
    }

    public void setHialianos(String hialianos) {
        this.hialianos = hialianos;
    }

    public String getLeucocitarios() {
        return leucocitarios;
    }

    public void setLeucocitarios(String leucocitarios) {
        this.leucocitarios = leucocitarios;
    }

    public String getCristales() {
        return cristales;
    }

    public void setCristales(String cristales) {
        this.cristales = cristales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getNum_auto() {
        return num_auto;
    }

    public void setNum_auto(long num_auto) {
        this.num_auto = num_auto;
    }

    public long getNum_auto2() {
        return num_auto2;
    }

    public void setNum_auto2(long num_auto2) {
        this.num_auto2 = num_auto2;
    }

    public Date getFech1() {
        return fech1;
    }

    public void setFech1(Date fech1) {
        this.fech1 = fech1;
    }

    public Date getFech2() {
        return fech2;
    }

    public void setFech2(Date fech2) {
        this.fech2 = fech2;
    }

    public Date getFech3() {
        return fech3;
    }

    public void setFech3(Date fech3) {
        this.fech3 = fech3;
    }

    public Date getFech4() {
        return fech4;
    }

    public void setFech4(Date fech4) {
        this.fech4 = fech4;
    }

    public Date getFech5() {
        return fech5;
    }

    public void setFech5(Date fech5) {
        this.fech5 = fech5;
    }

    public Date getFech6() {
        return fech6;
    }

    public void setFech6(Date fech6) {
        this.fech6 = fech6;
    }

    public Date getFech7() {
        return fech7;
    }

    public void setFech7(Date fech7) {
        this.fech7 = fech7;
    }

    public Date getFech8() {
        return fech8;
    }

    public void setFech8(Date fech8) {
        this.fech8 = fech8;
    }

    public Date getFech9() {
        return fech9;
    }

    public void setFech9(Date fech9) {
        this.fech9 = fech9;
    }

    public Date getFech10() {
        return fech10;
    }

    public void setFech10(Date fech10) {
        this.fech10 = fech10;
    }

    public Date getFech11() {
        return fech11;
    }

    public void setFech11(Date fech11) {
        this.fech11 = fech11;
    }

    public Date getFech12() {
        return fech12;
    }

    public void setFech12(Date fech12) {
        this.fech12 = fech12;
    }

    public Date getFech13() {
        return fech13;
    }

    public void setFech13(Date fech13) {
        this.fech13 = fech13;
    }

    public Date getFech14() {
        return fech14;
    }

    public void setFech14(Date fech14) {
        this.fech14 = fech14;
    }

    public Date getFech15() {
        return fech15;
    }

    public void setFech15(Date fech15) {
        this.fech15 = fech15;
    }

    public Date getFech16() {
        return fech16;
    }

    public void setFech16(Date fech16) {
        this.fech16 = fech16;
    }

    public Date getFech17() {
        return fech17;
    }

    public void setFech17(Date fech17) {
        this.fech17 = fech17;
    }

    public Date getFech18() {
        return fech18;
    }

    public void setFech18(Date fech18) {
        this.fech18 = fech18;
    }

    public Date getFech19() {
        return fech19;
    }

    public void setFech19(Date fech19) {
        this.fech19 = fech19;
    }

    public Date getFech20() {
        return fech20;
    }

    public void setFech20(Date fech20) {
        this.fech20 = fech20;
    }

    public Date getFech21() {
        return fech21;
    }

    public void setFech21(Date fech21) {
        this.fech21 = fech21;
    }

    public Date getFech22() {
        return fech22;
    }

    public void setFech22(Date fech22) {
        this.fech22 = fech22;
    }

    public Date getFech23() {
        return fech23;
    }

    public void setFech23(Date fech23) {
        this.fech23 = fech23;
    }

    public Date getFech24() {
        return fech24;
    }

    public void setFech24(Date fech24) {
        this.fech24 = fech24;
    }

    public Date getFech25() {
        return fech25;
    }

    public void setFech25(Date fech25) {
        this.fech25 = fech25;
    }

    public Date getFech26() {
        return fech26;
    }

    public void setFech26(Date fech26) {
        this.fech26 = fech26;
    }

    public Date getFech27() {
        return fech27;
    }

    public void setFech27(Date fech27) {
        this.fech27 = fech27;
    }

    public Date getFech28() {
        return fech28;
    }

    public void setFech28(Date fech28) {
        this.fech28 = fech28;
    }

    public Date getFech29() {
        return fech29;
    }

    public void setFech29(Date fech29) {
        this.fech29 = fech29;
    }

    public Date getFech30() {
        return fech30;
    }

    public void setFech30(Date fech30) {
        this.fech30 = fech30;
    }

    public Date getFech31() {
        return fech31;
    }

    public void setFech31(Date fech31) {
        this.fech31 = fech31;
    }

    public Date getFech32() {
        return fech32;
    }

    public void setFech32(Date fech32) {
        this.fech32 = fech32;
    }

    public Date getFech33() {
        return fech33;
    }

    public void setFech33(Date fech33) {
        this.fech33 = fech33;
    }

    public Date getFech34() {
        return fech34;
    }

    public void setFech34(Date fech34) {
        this.fech34 = fech34;
    }

    public Date getFech35() {
        return fech35;
    }

    public void setFech35(Date fech35) {
        this.fech35 = fech35;
    }

}
