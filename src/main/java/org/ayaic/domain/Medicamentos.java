package org.ayaic.domain;

import java.util.Date;
import java.io.Serializable;

public class Medicamentos implements Serializable {

    private static final long serialVersionUID = 1764454632736651497L;

    private int id_medicamento;
    private int id_programa;
    private int id_item;
    private String medicamento;
    private String forma_far;
    private String concentra;
    private String ubicacion;
    private String codsumi;
    private double precio_venta;
    private double costo_unit;
    private double stockv;
    private double stocks;
    private double stockp;
    private double stock;
    private Date fecha_ven;
    private String nro_lote;
    private String expedido;
    private String tipo;
    private String cod_cta;
    private String cod_tipo;
    private String cod_espe;
    private String cod_material;
    private int codigo;
    private int max_emerg;
    private int max_exter;
    private int max_inter;
    private int min_emerg;
    private int min_exter;
    private int min_inter;
    private int restringido;
    private int b1;
    private int b2;
    private int b3;
    private int mes;
    private int gestion;
    private String grupo;
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
    private String cadena21;
    private String cadena22;
    private String cadena23;
    private String cadena24;
    private String cadena25;
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
    private double astockv;
    private double astocks;
    private double astockp;
    private double estockv;
    private double estocks;
    private double estockp;
    private double cstockv;
    private double cstocks;
    private double cstockp;
    private double aneg;
    private double apost;
    private double sumab1;
    private double sumab2;
    private double sumab3;
    private int id_persona;
    private int id_dispensa;
    private int id_farmacia;
    private Date fecha_ini;
    private Date fecha_fin;
    private int cod_esta;

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_dispensa() {
        return id_dispensa;
    }

    public void setId_dispensa(int id_dispensa) {
        this.id_dispensa = id_dispensa;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public double getApost() {
        return apost;
    }

    public void setApost(double apost) {
        this.apost = apost;
    }

    public double getAneg() {
        return aneg;
    }

    public void setAneg(double aneg) {
        this.aneg = aneg;
    }

    public double getCstockv() {
        return cstockv;
    }

    public void setCstockv(double cstockv) {
        this.cstockv = cstockv;
    }

    public double getCstocks() {
        return cstocks;
    }

    public void setCstocks(double cstocks) {
        this.cstocks = cstocks;
    }

    public double getCstockp() {
        return cstockp;
    }

    public void setCstockp(double cstockp) {
        this.cstockp = cstockp;
    }

    public double getEstockv() {
        return estockv;
    }

    public void setEstockv(double estockv) {
        this.estockv = estockv;
    }

    public double getEstocks() {
        return estocks;
    }

    public void setEstocks(double estocks) {
        this.estocks = estocks;
    }

    public double getEstockp() {
        return estockp;
    }

    public void setEstockp(double estockp) {
        this.estockp = estockp;
    }

    public double getAstockv() {
        return astockv;
    }

    public void setAstockv(double astockv) {
        this.astockv = astockv;
    }

    public double getAstocks() {
        return astocks;
    }

    public void setAstocks(double astocks) {
        this.astocks = astocks;
    }

    public double getAstockp() {
        return astockp;
    }

    public void setAstockp(double astockp) {
        this.astockp = astockp;
    }

    public double getSumab1() {
        return sumab1;
    }

    public void setSumab1(double sumab1) {
        this.sumab1 = sumab1;
    }

    public double getSumab2() {
        return sumab2;
    }

    public void setSumab2(double sumab2) {
        this.sumab2 = sumab2;
    }

    public double getSumab3() {
        return sumab3;
    }

    public void setSumab3(double sumab3) {
        this.sumab3 = sumab3;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public int getB3() {
        return b3;
    }

    public void setB3(int b3) {
        this.b3 = b3;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
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

    public void setMedicamento(String medicamento) {
        medicamento = medicamento.toUpperCase();
        this.medicamento = medicamento;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        ubicacion = ubicacion.toUpperCase();
        this.ubicacion = ubicacion;
    }

    public String getCodsumi() {
        return codsumi;
    }

    public void setCodsumi(String codsumi) {
        codsumi = codsumi.toUpperCase();
        this.codsumi = codsumi;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getStockv() {
        return stockv;
    }

    public void setStockv(double stockv) {
        this.stockv = stockv;
    }

    public double getStocks() {
        return stocks;
    }

    public void setStocks(double stocks) {
        this.stocks = stocks;
    }

    public double getStockp() {
        return stockp;
    }

    public void setStockp(double stockp) {
        this.stockp = stockp;
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

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getMax_exter() {
        return max_exter;
    }

    public void setMax_exter(int max_exter) {
        this.max_exter = max_exter;
    }

    public int getMax_emerg() {
        return max_emerg;
    }

    public void setMax_emerg(int max_emerg) {
        this.max_emerg = max_emerg;
    }

    public int getMax_inter() {
        return max_inter;
    }

    public void setMax_inter(int max_inter) {
        this.max_inter = max_inter;
    }

    public int getMin_exter() {
        return min_exter;
    }

    public void setMin_exter(int min_exter) {
        this.min_exter = min_exter;
    }

    public int getMin_emerg() {
        return min_emerg;
    }

    public void setMin_emerg(int min_emerg) {
        this.min_emerg = min_emerg;
    }

    public int getMin_inter() {
        return min_inter;
    }

    public void setMin_inter(int min_inter) {
        this.min_inter = min_inter;
    }

    public int getRestringido() {
        return restringido;
    }

    public void setRestringido(int restringido) {
        this.restringido = restringido;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
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

    public String getCadena21() {
        return cadena21;
    }

    public void setCadena21(String cadena21) {
        this.cadena21 = cadena21;
    }

    public String getCadena22() {
        return cadena22;
    }

    public void setCadena22(String cadena22) {
        this.cadena22 = cadena22;
    }

    public String getCadena23() {
        return cadena23;
    }

    public void setCadena23(String cadena23) {
        this.cadena23 = cadena23;
    }

    public String getCadena24() {
        return cadena24;
    }

    public void setCadena24(String cadena24) {
        this.cadena24 = cadena24;
    }

    public String getCadena25() {
        return cadena25;
    }

    public void setCadena25(String cadena25) {
        this.cadena25 = cadena25;
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

}
