package org.ayaic.domain;

import java.io.Serializable;
import java.util.Date;

public class Pacientes implements Serializable {

    private static final long serialVersionUID = 2314602386809074254L;

    private int id_carpeta;
    private int id_empresa;
    private int id_seguro;
    private int id_paciente;
    private int id_pedido;
    private int id_factura;
    private int id_farmacia;
    private int id_vigencia;
    private int id_historial;
    private int id_proveedor;
    private int id_policlinico;
    private int hcl;
    private int num_fact;
    private String nombres;
    private String nombre;
    private String paterno;
    private String materno;
    private String id_estado;
    private String codigo;
    private Date fec_nacimiento;
    private String direccion;
    private String telefono;
    private String ocupacion;
    private String carnet;
    private int id_tipo_documento;
    private int id_tipo_parentesco;
    private int id_pais;
    private int id_departamento;
    private int id_provincia;
    private int id_localidad;
    private String registro;
    private String resultado;
    private String nro_registro;
    private int embarazo;
    private String tipo;
    private int id_tipo_sexo;
    private int id_ecivil;
    private int id_detalle;
    private int id_escolaridad;
    private Date fec_registro;
    private String tipo_sanguineo;
    private String factor_riesgo;
    private Date fec_baja;
    private String nit;
    private double precio_total;
    private double total;
    private double importe;
    private double ice;
    private double ime;
    private double g1;
    private double g2;
    private double g3;
    private double g4;
    private double g5;
    private double g6;
    private double g7;
    private double g8;
    private double g9;
    private int ult_usuario;
    private int edad;
    private int mes;
    private int dia;
    private int dial;
    private String expedido;
    private String num_cladoc;
    private int id_costo;
    private int id_rubro;
    private int id_persona;
    private int id_dispensa;
    private Date fecha_ini;
    private Date fecha_fin;
    private int veces;
    private String seguro;
    private int residencia;
    private int cod_esta;
    private int codestaref;
    private long nit_fact;
    private long num_auto;
    private int id_tipo_far;
    private String latitud;
    private String longitud;
    private String zoom;
    private String nro;
    private String nombrecodestared;
    private String documento;
    private String cadena;
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
    private String cadena26;
    private String cadena27;
    private String cadena28;
    private String cadena29;
    private String cadena30;
    private String cadena31;
    private String cadena32;
    private String cadena33;
    private String cadena34;
    private String cadena35;

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

    private int id_consultorio;

    public int getId_carpeta() {
        return id_carpeta;
    }

    public void setId_carpeta(int id_carpeta) {
        this.id_carpeta = id_carpeta;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(int id_seguro) {
        this.id_seguro = id_seguro;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
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

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getNum_fact() {
        return num_fact;
    }

    public void setNum_fact(int num_fact) {
        this.num_fact = num_fact;
    }

    public int getId_vigencia() {
        return id_vigencia;
    }

    public void setId_vigencia(int id_vigencia) {
        this.id_vigencia = id_vigencia;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public int getId_policlinico() {
        return id_policlinico;
    }

    public void setId_policlinico(int id_policlinico) {
        this.id_policlinico = id_policlinico;
    }

    public int getHcl() {
        return hcl;
    }

    public void setHcl(int hcl) {
        this.hcl = hcl;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        nombres = nombres.toUpperCase();
        this.nombres = nombres;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre.toUpperCase();
        this.nombre = nombre;
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

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        id_estado = id_estado.toUpperCase();
        this.id_estado = id_estado;
    }

    public Date getFec_nacimiento() {
        return fec_nacimiento;
    }

    public void setFec_nacimiento(Date fec_nacimiento) {
        this.fec_nacimiento = fec_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        direccion = direccion.toUpperCase();
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        codigo = codigo.toUpperCase();
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        ocupacion = ocupacion.toUpperCase();
        this.ocupacion = ocupacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        carnet = carnet.toUpperCase();
        this.carnet = carnet;
    }

    public int getId_tipo_documento() {
        return id_tipo_documento;
    }

    public void setId_tipo_documento(int id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public int getId_tipo_parentesco() {
        return id_tipo_parentesco;
    }

    public void setId_tipo_parentesco(int id_tipo_parentesco) {
        this.id_tipo_parentesco = id_tipo_parentesco;
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

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        registro = registro.toUpperCase();
        this.registro = registro;
    }

    public String getNro_registro() {
        return nro_registro;
    }

    public void setNro_registro(String nro_registro) {
        nro_registro = nro_registro.toUpperCase();
        this.nro_registro = nro_registro;
    }

    public int getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(int embarazo) {
        this.embarazo = embarazo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        tipo = tipo.toUpperCase();
        this.tipo = tipo;
    }

    public int getId_tipo_sexo() {
        return id_tipo_sexo;
    }

    public void setId_tipo_sexo(int id_tipo_sexo) {
        this.id_tipo_sexo = id_tipo_sexo;
    }

    public int getId_escolaridad() {
        return id_escolaridad;
    }

    public void setId_escolaridad(int id_escolaridad) {
        this.id_escolaridad = id_escolaridad;
    }

    public int getId_ecivil() {
        return id_ecivil;
    }

    public void setId_ecivil(int id_ecivil) {
        this.id_ecivil = id_ecivil;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public String getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(String tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    public String getFactor_riesgo() {
        return factor_riesgo;
    }

    public void setFactor_riesgo(String factor_riesgo) {
        this.factor_riesgo = factor_riesgo;
    }

    public Date getFec_baja() {
        return fec_baja;
    }

    public void setFec_baja(Date fec_baja) {
        this.fec_baja = fec_baja;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        nit = nit.toUpperCase();
        this.nit = nit;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getIce() {
        return ice;
    }

    public void setIce(double ice) {
        this.ice = ice;
    }

    public double getIme() {
        return ime;
    }

    public void setIme(double ime) {
        this.ime = ime;
    }

    public double getG1() {
        return g1;
    }

    public void setG1(double g1) {
        this.g1 = g1;
    }

    public double getG2() {
        return g2;
    }

    public void setG2(double g2) {
        this.g2 = g2;
    }

    public double getG3() {
        return g3;
    }

    public void setG3(double g3) {
        this.g3 = g3;
    }

    public double getG4() {
        return g4;
    }

    public void setG4(double g4) {
        this.g4 = g4;
    }

    public double getG5() {
        return g5;
    }

    public void setG5(double g5) {
        this.g5 = g5;
    }

    public double getG6() {
        return g6;
    }

    public void setG6(double g6) {
        this.g6 = g6;
    }

    public double getG7() {
        return g7;
    }

    public void setG7(double g7) {
        this.g7 = g7;
    }

    public double getG8() {
        return g8;
    }

    public void setG8(double g8) {
        this.g8 = g8;
    }

    public double getG9() {
        return g9;
    }

    public void setG9(double g9) {
        this.g9 = g9;
    }

    public int getUlt_usuario() {
        return ult_usuario;
    }

    public void setUlt_usuario(int ult_usuario) {
        this.ult_usuario = ult_usuario;
    }

    public int getId_consultorio() {
        return id_consultorio;
    }

    public void setId_consultorio(int id_consultorio) {
        this.id_consultorio = id_consultorio;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getDial() {
        return dial;
    }

    public void setDial(int dial) {
        this.dial = dial;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getNombrecodestared() {
        return nombrecodestared;
    }

    public void setNombrecodestared(String nombrecodestared) {
        this.nombrecodestared = nombrecodestared;
    }

    public String getNum_cladoc() {
        return num_cladoc;
    }

    public void setNum_cladoc(String num_cladoc) {
        num_cladoc = num_cladoc.toUpperCase();
        this.num_cladoc = num_cladoc;
    }

    public int getId_costo() {
        return id_costo;
    }

    public void setId_costo(int id_costo) {
        this.id_costo = id_costo;
    }

    public int getId_rubro() {
        return id_rubro;
    }

    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(int id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public int getId_dispensa() {
        return id_dispensa;
    }

    public void setId_dispensa(int id_dispensa) {
        this.id_dispensa = id_dispensa;
    }

    public int getId_tipo_far() {
        return id_tipo_far;
    }

    public void setId_tipo_far(int id_tipo_far) {
        this.id_tipo_far = id_tipo_far;
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

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public int getResidencia() {
        return residencia;
    }

    public void setResidencia(int residencia) {
        this.residencia = residencia;
    }

    public long getNit_fact() {
        return nit_fact;
    }

    public void setNit_fact(long nit_fact) {
        this.nit_fact = nit_fact;
    }

    public long getNum_auto() {
        return num_auto;
    }

    public void setNum_auto(long num_auto) {
        this.num_auto = num_auto;
    }

    public int getCod_esta() {
        return cod_esta;
    }

    public void setCod_esta(int cod_esta) {
        this.cod_esta = cod_esta;
    }

    public int getCodestaref() {
        return codestaref;
    }

    public void setCodestaref(int codestaref) {
        this.codestaref = codestaref;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
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
        return cadena20;
    }

    public void setCadena25(String cadena25) {
        this.cadena25 = cadena25;
    }

    public String getCadena26() {
        return cadena26;
    }

    public void setCadena26(String cadena26) {
        this.cadena26 = cadena26;
    }

    public String getCadena27() {
        return cadena27;
    }

    public void setCadena27(String cadena27) {
        this.cadena27 = cadena27;
    }

    public String getCadena28() {
        return cadena28;
    }

    public void setCadena28(String cadena28) {
        this.cadena28 = cadena28;
    }

    public String getCadena29() {
        return cadena29;
    }

    public void setCadena29(String cadena29) {
        this.cadena29 = cadena29;
    }

    public String getCadena30() {
        return cadena30;
    }

    public void setCadena30(String cadena30) {
        this.cadena30 = cadena30;
    }

    public String getCadena31() {
        return cadena31;
    }

    public void setCadena31(String cadena31) {
        this.cadena31 = cadena31;
    }

    public String getCadena32() {
        return cadena32;
    }

    public void setCadena32(String cadena32) {
        this.cadena32 = cadena32;
    }

    public String getCadena33() {
        return cadena33;
    }

    public void setCadena33(String cadena33) {
        this.cadena33 = cadena33;
    }

    public String getCadena34() {
        return cadena34;
    }

    public void setCadena34(String cadena34) {
        this.cadena34 = cadena34;
    }

    public String getCadena35() {
        return cadena30;
    }

    public void setCadena35(String cadena35) {
        this.cadena35 = cadena35;
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

    public int getSuma3() {
        return suma3;
    }

    public void setSuma3(int suma3) {
        this.suma3 = suma3;
    }

    public int getSuma4() {
        return suma4;
    }

    public void setSuma4(int suma4) {
        this.suma4 = suma4;
    }

    public int getSuma5() {
        return suma5;
    }

    public void setSuma5(int suma5) {
        this.suma5 = suma5;
    }

    public int getSuma6() {
        return suma6;
    }

    public void setSuma6(int suma6) {
        this.suma6 = suma6;
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

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

}
