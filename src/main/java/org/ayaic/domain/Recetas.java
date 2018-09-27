package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Recetas extends Medicamentos implements Serializable {

    private static final long serialVersionUID = -3352266447944883568L;

    private int id_medicamento;
    private int id_detalle;
    private int id_prestacion;
    private int id_receta;
    private int id_kardex;
    private int id_paciente;
    private int id_historial;
    private int id_historia;
    private int id_pedido;
    private int id_factura;
    private int id_medico;
    private int id_farmacia;
    private int dosifica;
    private int numeracion;
    private int id_programa;
    private String medico;
    private String medicamento;
    private String expedido;
    private String prestacion;
    private String indicacion;
    private int entradas;
    private int salidas;
    private int saldos;
    private double salida;
    private double entrada;
    private double ajuste;
    private double precio_venta;
    private double costo_unit;
    private double precio_total;
    private double costo_total;
    private double precio_totalc;
    private double precio_totala;
    private String id_estado;
    private String forma_far;
    private String concentra;
    private String cod_cta;
    private String cod_tipo;
    private String cod_espe;
    private String cod_material;
    private Date fecha;
    private Date fecha_ven;
    private String nro_lote;
    private double stock;
    private double stock_i;
    private double stock_f;
    private String num_cladoc;
    private String nit;
    private int cod_esta;
    private int num_recetak;
    private Date fecha_ini;
    private Date fecha_fin;
    private String cadena1;
    private String cadena2;
    private String cadena3;
    private String cadena4;
    private String cadena5;
    private String cadena6;
    private String cadena7;
    private String cadena8;
    private String cadena9;
    private String cadena10;
    private String cadena11;
    private String cadena12;
    private String cadena13;
    private String cadena14;
    private String cadena15;
    private String cadena16;
    private String cadena17;
    private String cadena18;
    private String cadena19;
    private String cadena20;
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

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public int getId_kardex() {
        return id_kardex;
    }

    public void setId_kardex(int id_kardex) {
        this.id_kardex = id_kardex;
    }

    public int getId_prestacion() {
        return id_prestacion;
    }

    public void setId_prestacion(int id_prestacion) {
        this.id_prestacion = id_prestacion;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
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

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
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

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getSaldos() {
        return saldos;
    }

    public void setSaldos(int saldos) {
        this.saldos = saldos;
    }

    public int getDosifica() {
        return dosifica;
    }

    public void setDosifica(int dosifica) {
        this.dosifica = dosifica;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String nombre) {
        nombre = nombre.toUpperCase();
        this.medicamento = nombre;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        prestacion = prestacion.toUpperCase();
        this.prestacion = prestacion;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        indicacion = indicacion.toUpperCase();
        this.indicacion = indicacion;
    }

    public double getSalida() {
        return salida;
    }

    public void setSalida(double salida) {
        this.salida = salida;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public double getAjuste() {
        return ajuste;
    }

    public void setAjuste(double ajuste) {
        this.ajuste = ajuste;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getCosto_unit() {
        return costo_unit;
    }

    public void setCosto_unit(double costo_unit) {
        this.costo_unit = costo_unit;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public double getPrecio_totalc() {
        return precio_totalc;
    }

    public void setPrecio_totalc(double precio_totalc) {
        this.precio_totalc = precio_totalc;
    }

    public double getPrecio_totala() {
        return precio_totala;
    }

    public void setPrecio_totala(double precio_totala) {
        this.precio_totala = precio_totala;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getStock_i() {
        return stock_i;
    }

    public void setStock_i(double stock_i) {
        this.stock_i = stock_i;
    }

    public double getStock_f() {
        return stock_f;
    }

    public void setStock_f(double stock_f) {
        this.stock_f = stock_f;
    }

    public String getForma_far() {
        return forma_far;
    }

    public void setForma_far(String forma_far) {
        forma_far = forma_far.toUpperCase();
        this.forma_far = forma_far;
    }

    public String getConcentra() {
        return concentra;
    }

    public void setConcentra(String concentra) {
        concentra = concentra.toUpperCase();
        this.concentra = concentra;
    }

    public String getNum_cladoc() {
        return num_cladoc;
    }

    public void setNum_cladoc(String num_cladoc) {
        num_cladoc = num_cladoc.toUpperCase();
        this.num_cladoc = num_cladoc;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        nit = nit.toUpperCase();
        this.nit = nit;
    }

    public Date getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(Date fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getNro_lote() {
        return nro_lote;
    }

    public void setNro_lote(String nro_lote) {
        nro_lote = nro_lote.toUpperCase();
        this.nro_lote = nro_lote;
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

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
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

    public String getCadena5() {
        return cadena5;
    }

    public void setCadena5(String cadena5) {
        this.cadena5 = cadena5;
    }

    public String getCadena6() {
        return cadena6;
    }

    public void setCadena6(String cadena6) {
        this.cadena6 = cadena6;
    }

    public String getCadena7() {
        return cadena7;
    }

    public void setCadena7(String cadena7) {
        this.cadena7 = cadena7;
    }

    public String getCadena8() {
        return cadena8;
    }

    public void setCadena8(String cadena8) {
        this.cadena8 = cadena8;
    }

    public String getCadena9() {
        return cadena9;
    }

    public void setCadena9(String cadena9) {
        this.cadena9 = cadena9;
    }

    public String getCadena10() {
        return cadena10;
    }

    public void setCadena10(String cadena10) {
        this.cadena10 = cadena10;
    }

    public String getCadena11() {
        return cadena11;
    }

    public void setCadena11(String cadena11) {
        this.cadena11 = cadena11;
    }

    public String getCadena12() {
        return cadena12;
    }

    public void setCadena12(String cadena12) {
        this.cadena12 = cadena12;
    }

    public String getCadena13() {
        return cadena13;
    }

    public void setCadena13(String cadena13) {
        this.cadena13 = cadena13;
    }

    public String getCadena14() {
        return cadena14;
    }

    public void setCadena14(String cadena14) {
        this.cadena14 = cadena14;
    }

    public String getCadena15() {
        return cadena15;
    }

    public void setCadena15(String cadena15) {
        this.cadena15 = cadena15;
    }

    public String getCadena16() {
        return cadena16;
    }

    public void setCadena16(String cadena16) {
        this.cadena16 = cadena16;
    }

    public String getCadena17() {
        return cadena17;
    }

    public void setCadena17(String cadena17) {
        this.cadena17 = cadena17;
    }

    public String getCadena18() {
        return cadena18;
    }

    public void setCadena18(String cadena18) {
        this.cadena18 = cadena18;
    }

    public String getCadena19() {
        return cadena19;
    }

    public void setCadena19(String cadena19) {
        this.cadena19 = cadena19;
    }

    public String getCadena20() {
        return cadena20;
    }

    public void setCadena20(String cadena20) {
        this.cadena20 = cadena20;
    }

    public String getCod_cta() {
        return cod_cta;
    }

    public void setCod_cta(String cod_cta) {
        this.cod_cta = cod_cta;
    }

    public String getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(String cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getCod_espe() {
        return cod_espe;
    }

    public void setCod_espe(String cod_espe) {
        this.cod_espe = cod_espe;
    }

    public String getCod_material() {
        return cod_material;
    }

    public void setCod_material(String cod_material) {
        this.cod_material = cod_material;
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

    public int getNum_recetak() {
        return num_recetak;
    }

    public void setNum_recetak(int num_recetak) {
        this.num_recetak = num_recetak;
    }

}
