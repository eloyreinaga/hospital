package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;
import javax.swing.table.DefaultTableModel;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Documentos;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Empresasws;
import org.ayaic.domain.Escolaridades;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Paises;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConfirmarPacienteControlador {

    private final MiFacade mi;

    public ConfirmarPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception, MalformedURLException, IOException, ParserConfigurationException, SAXException {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String acciona = request.getParameter("acciona");
        String accion1 = request.getParameter("accion1");
        String accion2 = request.getParameter("accion2");
        String accionsa = request.getParameter("accionsa");
        String resvig = request.getParameter("resvig");

        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_carpeta = request.getParameter("id_carpeta");
        String id_empresa = request.getParameter("id_empresa");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        String zoom = request.getParameter("zoom");
        String hcl = request.getParameter("hcl");
        String nombre = request.getParameter("nombre");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String direccion = request.getParameter("direccion");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String tipoconsult = request.getParameter("tipoconsult");
        String id_riesgo = request.getParameter("id_riesgo");
        String telefono = request.getParameter("telefono");
        String ocupacion = request.getParameter("ocupacion");
        String residencia = request.getParameter("resid");
        String carnet = request.getParameter("carnet");
        String patronal = request.getParameter("patronal");
        String patronal1 = request.getParameter("patronal1");
        String patronal2 = request.getParameter("patronal2");
        String patronal3 = request.getParameter("patronal3");
        String nomempresa = request.getParameter("nomempresa");
        String nro_registro = request.getParameter("nro_registro");
        String policonsul = request.getParameter("policonsul");
        String cod = request.getParameter("cod");
        String lugci = request.getParameter("lugci");
        String id_estado_civilx = request.getParameter("id_ecivil");
        String id_sexo = request.getParameter("id_sexo");
        String id_documento = request.getParameter("id_documento");
        String id_escolaridad = request.getParameter("id_escolaridad");
        String id_pais = request.getParameter("id_pais");
        String id_departamentox = request.getParameter("id_departamento");
        String id_provincia = request.getParameter("id_provincia");
        String id_localidad = request.getParameter("id_localidad");
        String id_consultoriot = request.getParameter("id_consultoriot");
        String id_personat = request.getParameter("id_personat");
        String swinter = request.getParameter("swinter");
        String fechainit = request.getParameter("fechainit");
        String fechafint = request.getParameter("fechafint");
        String acciont = request.getParameter("acciont");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int nemp = 0;
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq + 1), Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        DefaultTableModel modelo1;
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] nropa = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String[] aniosqq = {(Integer.toString(anioq)), (Integer.toString(anioq + 1))};
        Date ahora = new Date();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String dia_r = request.getParameter("dia_r");
        String mes_r = request.getParameter("mes_r");
        String anio_r = request.getParameter("anio_r");
 
        Pacientes pai = new Pacientes();

        Localidades local = new Localidades();
        List Estab2 = this.mi.getListarEsta(local);
        local.setCod_esta(cliente.getCod_esta());
        local.setArea("U");  ///getListarEstaUsua el estab del usuario
        List Estab1 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab2.get(0);
        modelo.put("tipoestab", cliente.getArea());

        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoest.getCod_esta()));
        modelo.put("codest", Integer.toString(cliente.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(datoest.getCod_esta()));
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_paciente", id_paciente);
        modelo.put("nropa", nropa);
        modelo.put("resvig", resvig);
        modelo.put("id_rol", Integer.toString(cliente.getId_rol()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("swinter", swinter);
        modelo.put("tipoconsult", tipoconsult);
        modelo.put("id_riesgo", id_riesgo);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("hora", Integer.toString(fecha1.getHours()));
        modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
        modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("diae", Integer.toString(fecha1.getDate()));
        modelo.put("horae", Integer.toString(fecha1.getHours()));
        modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));
                
        if ("".equals(id_estado_civilx) || id_estado_civilx == null) {
            id_estado_civilx = "1";
        }
        if ("".equals(id_escolaridad) || id_escolaridad == null) {
            id_escolaridad = "1";
        }

        if ("AltaPac".equals(acciona)) {
            String id_historial = request.getParameter("id_historial");

            Historiales reserva = new Historiales();
            reserva.setId_paciente(Integer.parseInt(id_paciente));
            reserva.setId_historial(Integer.parseInt(id_historial));
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setInternado(10);  ///10 alta por vigencia 
            reserva.setAccion("R");   ////setModificarInterAltaHisto si esta internado lo da de alta automaticamente
            this.mi.setModificarInterAltaHisto(reserva);
        }

        if ("EliminaSeguro".equals(accion1)) {
            Pacientes elimina = new Pacientes();
            elimina.setId_carpeta(Integer.parseInt(request.getParameter("id_carpeta")));
            elimina.setTipo("E");

            this.mi.setEliminarPacienteSeguro(elimina);
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            Pacientes paciente = new Pacientes();
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            List buscapacientes = this.mi.getDatosAfiliados(paciente); // saca un registro a ser modificado
            modelo.put("buscapaciente", buscapacientes);
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            modelo.put("id_paciente", id_paciente);
            modelo.put("accion", accion);
            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);
            return new ModelAndView("administrarpacientes/ConfirmarPaciente", modelo);
        }

        if ("Adicionar".equals(accion)){
            if ("".equals(paterno) || paterno == null) {
                paterno = ".";
            }if ("".equals(materno) || materno == null) {
                materno = ".";
            }if ("".equals(nombre) || nombre == null) {
                return new ModelAndView("Aviso", "mensaje", "nombre no puede ser vacio o nulo");
            }

            if (Integer.parseInt(anio) > (ahora.getYear() + 1900) || Integer.parseInt(mes) > 12 || Integer.parseInt(dia) > 31
                    || Integer.parseInt(anio) < 1901) {
                return new ModelAndView("Aviso", "mensaje", "La fecha de nacimiento no es correcta");
            }
            if (datoest.getCod_esta() == 200010) {
                String patrona1 = "00".substring(patronal1.length()) + patronal1;
                String patrona2 = "000".substring(patronal2.length()) + patronal2;
                String patrona3 = "00000".substring(patronal3.length()) + patronal3;
                patronal = patrona1 + patrona2 + patrona3;

                if (nro_registro.length() > 10 || nro_registro.length() < 9) {
                    return new ModelAndView("Aviso", "mensaje", "La matricula no es correcta 999999XXX");
                }
                if ("".equals(policonsul) || policonsul == null) {
                    policonsul = "0";
                }
                if (!"".equals(patronal) || patronal != null) {
                    Empresas empresa = new Empresas();
                    empresa.setCod_patronal(Long.parseLong(patronal));
                    empresa.setTipo("L");
                    List listarEmp = this.mi.getListaEmpresaCod(empresa);
                    if (listarEmp.isEmpty() && "".equals(nomempresa)) {
                        return new ModelAndView("Aviso", "mensaje", "La empresa NO existe, debe colocar Nombre Empresa para poder crear");
                    }
                    if (!listarEmp.isEmpty()) {
                        Empresas datempresa = (Empresas) listarEmp.get(0);
                        id_empresa = Integer.toString(datempresa.getId_empresa());
                    } else {
                        id_empresa = "0";
                    }
                }
            } else {
                patronal = "0";
                nomempresa = "0";
                id_empresa = "0";
                nro_registro = "0";
                policonsul = "0";
            }
            paterno=paterno.replaceAll("�", "Ñ");
            materno=materno.replaceAll("�", "Ñ");
            pai.setHcl(0);
            pai.setNombre(nombre);
            pai.setPaterno(paterno);
            pai.setMaterno(materno);
            pai.setDireccion(direccion);
            pai.setTelefono(telefono);
            pai.setId_empresa(Integer.parseInt(id_empresa));
            pai.setNro_registro(nro_registro);
            pai.setId_policlinico(Integer.parseInt(policonsul));
            pai.setNro(cod);
            pai.setExpedido(lugci);
            pai.setCadena(patronal);
            pai.setCadena2(nomempresa);
            pai.setResidencia(Integer.parseInt(residencia));
            pai.setOcupacion(ocupacion);
            pai.setCarnet(carnet);
            pai.setLatitud(latitud);
            pai.setLongitud(longitud);
            pai.setZoom(zoom);
            modelo.put("datos", pai);

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            //Buscar documento
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento)); // saca un registro a ser modificado
            modelo.put("buscarDocumento", buscardocumento);
            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_estado_civilx)); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);
            //Buscar Escolaridad
            Escolaridades buscarescolaridad = this.mi.getDatosEscolaridad(Integer.parseInt(id_escolaridad)); // saca un registro a ser modificado
            modelo.put("buscarEscolaridad", buscarescolaridad);

            //Buscar Pais
            try {
                Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
                modelo.put("id_pais", id_pais);

                if (!"1".equals(id_pais)) {
                    id_departamentox = "0";
                    id_provincia = "0";
                    id_localidad = "0";
                    modelo.put("id_pais", id_pais);
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamentox)); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try { //Buscar Provincia    
                Provincias buscarProvincia = this.mi.getDatosProvincia(Integer.parseInt(id_provincia));
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try {//Buscar Localidad
                Localidades localidad = new Localidades();
                localidad.setId_localidad(Integer.parseInt(id_localidad));
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            String a = "/";
            String fecha_nacimiento = dia + a + mes + a + anio;
            modelo.put("fec_nacimiento", fecha_nacimiento);
            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));

            String fecha_registro = dia_r + a + mes_r + a + anio_r;
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", request.getParameter("anio_r"));
            modelo.put("mes_r", request.getParameter("mes_r"));
            modelo.put("dia_r", request.getParameter("dia_r"));

            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fechanac = new Date(aniox, mesx, diax);
            long fechaInicialMs = fechanac.getTime();
            long fechaFinalMs = ahora.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double diasx = Math.floor(diferencia / (1000 * 60 * 60 * 24));

            if (diasx < 0) {
                return new ModelAndView("Aviso", "mensaje", "El fecha de Naciemiento no es correcto");
            }

            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Reservar".equals(accion) && "ZZZ".equals(accionsa)) {
            String id_persona = request.getParameter("id_persona");
            int id_consultoriox = Integer.parseInt(request.getParameter("id_consultorio"));
            modelo.put("id_consultorio", Integer.toString(id_consultoriox));
  
            Personas persona = new Personas();
            persona.setId_consultorio(id_consultoriox);
            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            //}

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            //if(datoest.getCod_esta()==200010){
            //  List listarPagos = this.mi.getListarDetallePago(buscarPaciente.getId_empresa());
            //   modelo.put("listarpagos", listarPagos);
            //  modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
            // }
            modelo.put("accion", accion);
            modelo.put("accionsa", accionsa);
            modelo.put("id_persona", id_persona);

            modelo.put("area", datoestab.getArea());
            Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);

            modelo.put("listarCargos", listarCargos);
            modelo.put("tipo_medico", tipo_medico);
            modelo.put("id_paciente", id_paciente);

            modelo.put("direccion", direccion);

            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            String a = "/";
            String fecha_nacimiento = xdia + a + xmes + a + xanio;
            modelo.put("fec_nacimiento", fecha_nacimiento);

            /////////////
            Historiales dato = new Historiales();

            dato.setId_consultorio(id_consultoriox);
            if (id_consultoriox == 0) {
                dato.setAccion("A");
            } else {
                dato.setAccion("M");
                if ("0".equals(id_persona)) {
                    dato.setAccion("S");
                } else {
                    dato.setId_persona(Integer.parseInt(id_persona));
                }
            }
            dato.setId_estado("%");
            List listarPaises = this.mi.getListarReservaciones(dato);
            modelo.put("milista", listarPaises);

            persona.setId_consultorio(id_consultoriox);
            persona.setCod_esta(cliente.getCod_esta());
            modelo.put("estab", datoestab.getArea());
            /////////////
            return new ModelAndView("administrarpacientes/ConfirmarPaciente", modelo);
        }

        if ("Modificar".equals(accion)) {
            String swci = request.getParameter("swci");
            String id_estado = request.getParameter("id_estado");

            if (datoest.getCod_esta() == 200010) {
                String patrona1 = "00".substring(patronal1.length()) + patronal1;
                String patrona2 = "000".substring(patronal2.length()) + patronal2;
                String patrona3 = "00000".substring(patronal3.length()) + patronal3;
                patronal = patrona1 + patrona2 + patrona3;
            } else {
                if (id_paciente != null) {
                    Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
                    if (buscarPaciente == null) {
                        Pacientes buscarPacienteCi = this.mi.getDatosPacienteCI(Integer.parseInt(id_paciente));
                        if (buscarPacienteCi != null) {
                            nro_registro = buscarPacienteCi.getNro_registro();
                        } else {
                            nro_registro = "0";
                        }
                    } else {
                        id_empresa = Integer.toString(buscarPaciente.getId_empresa());
                        nro_registro = buscarPaciente.getNro_registro();
                    }
                    patronal = "0";
                    nomempresa = "0";
                    policonsul = "0";
                } else {
                    patronal = "0";
                    nomempresa = "0";
                    id_empresa = "0";
                    policonsul = "0";
                }
            }

            modelo.put("id_pais", id_pais);
            if (!"1".equals(id_pais)) {
                id_departamentox = "0";
                id_provincia = "0";
                id_localidad = "0";
                modelo.put("id_pais", id_pais);
            }
            if (residencia == null || "".equals(residencia)) {
                residencia = Integer.toHexString(pai.getResidencia());
            }
            pai.setId_paciente(Integer.parseInt(id_paciente));

            pai.setHcl(Integer.parseInt(hcl));
            pai.setNombre(nombre);
            paterno=paterno.replaceAll("�", "Ñ");
            materno=materno.replaceAll("�", "Ñ");
            pai.setPaterno(paterno);
            pai.setMaterno(materno);
            pai.setDireccion(direccion);
            pai.setTelefono(telefono);
            pai.setOcupacion(ocupacion);
            pai.setNro_registro(nro_registro);
            pai.setId_policlinico(Integer.parseInt(policonsul));
            pai.setNro(cod);
            pai.setExpedido(lugci);
            pai.setResidencia(Integer.parseInt(residencia));
            pai.setCarnet(carnet);
            pai.setId_estado(id_estado);
            pai.setCadena(patronal);
            pai.setLatitud(latitud);
            pai.setLongitud(longitud);
            pai.setZoom(zoom);
            pai.setCadena2(nomempresa);
            modelo.put("datos", pai);
            modelo.put("swci", swci);
            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            //Buscar documento
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento)); // saca un registro a ser modificado
            modelo.put("buscarDocumento", buscardocumento);
            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_estado_civilx)); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);

            //Buscar Escolaridad
            Escolaridades buscarescolaridad = this.mi.getDatosEscolaridad(Integer.parseInt(id_escolaridad)); // saca un registro a ser modificado
            modelo.put("buscarEscolaridad", buscarescolaridad);

            //Buscar Pais
            try {
                Paises buscarPais = this.mi.getDatosPais(Integer.parseInt(id_pais)); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamentox)); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try {

                Provincias buscarProvincia = this.mi.getDatosProvincia(Integer.parseInt(id_provincia));
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try {
                Localidades localidad = new Localidades();
                localidad.setId_localidad(Integer.parseInt(id_localidad));
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            String a = "/";
            String fecha_nacimiento = dia + a + mes + a + anio;
            modelo.put("fec_nacimiento", fecha_nacimiento);
            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));

            String fecha_registro = dia_r + a + mes_r + a + anio_r;
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", request.getParameter("anio_r"));
            modelo.put("mes_r", request.getParameter("mes_r"));
            modelo.put("dia_r", request.getParameter("dia_r"));

            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fechanac = new Date(aniox, mesx, diax);
            long fechaInicialMs = fechanac.getTime();
            long fechaFinalMs = ahora.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double diasx = Math.floor(diferencia / (1000 * 60 * 60 * 24));

            if (diasx < 0) {
                return new ModelAndView("Aviso", "mensaje", "El fecha de Naciemiento no es correcto");
            }

            modelo.put("sw", request.getParameter("sw"));
            if ("1".equals(swci))//si el paciente es con CI se va a grabar
            {
                modelo.put("accion", "Adicionar");
            } else {
                modelo.put("accion", accion);
            }
            modelo.put("accion1", accion1);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);

        }

        if ("Eliminar".equals(accion) || "Afiliar".equals(accion) || "Desafiliar".equals(accion)) {
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            List listarSeguros2 = this.mi.getListarSeguros("A");
            modelo.put("listarSeguros", listarSeguros2);

            if (datoest.getCod_esta() == 200010) {
                modelo.put("id_seguro", "35");
            }

            int id_estado_civil_e = buscarPaciente.getId_ecivil();
            String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo
            String id_documento_e = Integer.toString(buscarPaciente.getId_tipo_documento());
            String id_escolaridad_e = Integer.toString(buscarPaciente.getId_escolaridad());          //Sacar id_sexo
            int id_pais_e = buscarPaciente.getId_pais();          //Sacar id_pais 
            int id_departamento_e = buscarPaciente.getId_departamento();  //Sacar id_departamento 
            int id_provincia_e = buscarPaciente.getId_provincia();     //Sacar id_provincia       
            int id_localidad_e = buscarPaciente.getId_localidad();     //Sacar id_localidad       

            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            if (xanio < 1901) {
                return new ModelAndView("Aviso", "mensaje", "El año de nacimiento no es correcto");
            }

            Date fecha_reg = buscarPaciente.getFec_registro();
            int xanio_r = fecha_reg.getYear() + 1900;
            int xmes_r = fecha_reg.getMonth() + 1;
            int xdia_r = fecha_reg.getDate();

            if ("O".equals(cliente.getArea())) {
                modelo.put("registro", this.mi.getNumAsegurado(Integer.parseInt(id_paciente)));
            } else {
                modelo.put("registro", this.mi.getNumRegistro(Integer.parseInt(id_paciente)));
            }
            //Buscar Sexo
            if (datoest.getCod_esta() == 200010) {
                modelo.put("registro", buscarPaciente.getNro_registro());
            }

            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            //Buscar documento
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento_e)); // saca un registro a ser modificado
            modelo.put("buscarDocumento", buscardocumento);
            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(id_estado_civil_e); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);
            //Busca lsta pacientes afiliados y desafiliados
            if ("Afiliar".equals(accion) || "Desafiliar".equals(accion)) {
                Pacientes paciente = new Pacientes();
                paciente.setId_paciente(Integer.parseInt(id_paciente));
                List buscapacientes = this.mi.getDatosAfiliadosNada(paciente); // saca un registro a ser modificado
                modelo.put("buscapaciente", buscapacientes);
                if (buscapacientes.size() > 0) {
                    paciente.setId_estado("T");
                    List buscapacientes2 = this.mi.getDatosAfiliados(paciente); // saca un registro a ser modificado
                    modelo.put("buscapaciente", buscapacientes2);
                }

            }
            //Buscar Escolaridad
            Escolaridades buscarescolaridad = this.mi.getDatosEscolaridad(Integer.parseInt(id_escolaridad_e)); // saca un registro a ser modificado
            modelo.put("buscarEscolaridad", buscarescolaridad);
            //Buscar Pais
            try {
                Paises buscarPais = this.mi.getDatosPais(id_pais_e); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Pais");
            }
            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(id_departamento_e); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Departamento");
            }
            //Buscar Provincia 
            try {
                Provincias buscarProvincia = this.mi.getDatosProvincia(id_provincia_e);
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Provincia");
            }
            //Buscar Localidad
            try {
                Localidades localidad = new Localidades();
                localidad.setId_localidad(id_localidad_e);
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Localidad");
            }
            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            String fecha_registro = dia_r + a + mes_r + a + anio_r;
            modelo.put("fec_registro", fecha_registro);

            modelo.put("accion", accion);
            modelo.put("anio_r", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes_r", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("dia_r", Integer.toString(fecha1.getDate()));
            modelo.put("sw1", request.getParameter("sw1"));
        }

        if ("InterConsulta".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultori");
            String id_persona = request.getParameter("id_persona");
            String sw = request.getParameter("sw");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            if (buscarPaciente.getDia() < 0) {
                return new ModelAndView("Aviso", "mensaje", "La fecha de Nacimiento no es correcta");
            }

            Date fecha_reg = buscarPaciente.getFec_registro();
            int xanio_r = fecha_reg.getYear() + 1900;
            int xmes_r = fecha_reg.getMonth() + 1;
            int xdia_r = fecha_reg.getDate();

            String a = "/";

            String fecha_registro = Integer.toString(xdia_r) + a + Integer.toString(xmes_r) + a + Integer.toString(xanio_r);
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", Integer.toString(xanio_r));
            modelo.put("mes_r", Integer.toString(xmes_r));
            modelo.put("dia_r", Integer.toString(xdia_r));
            modelo.put("estab", datoestab.getArea());

            //lista de consultorios
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);

            if (id_consultorio != null) {
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
            } else {
                if (cliente.getId_especialidad() > 3) {
                    id_consultorio = Integer.toString(cliente.getId_consultorio());
                    id_persona = Integer.toString(cliente.getId_persona());
                    Personas persona = new Personas();
                    persona.setId_consultorio(Integer.parseInt(id_consultorio));
                    persona.setCod_esta(cliente.getCod_esta());
                    List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                    modelo.put("listaPersonas", buscarEmpleado);
                    modelo.put("id_persona", id_persona);
                }
            }

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("tipo_medico", tipo_medico);
            modelo.put("expedido", expedido);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_paciente", id_paciente);

            return new ModelAndView("administrarpacientes/InterConsulta", modelo);
        }

        if ("InternarPaciente".equals(accion) || "Admision".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String edad = request.getParameter("edad");

            if ("0".equals(id_consultorio) || "0".equals(id_persona)) {
                return new ModelAndView("Aviso", "mensaje", "Debe tener medico y consultorio o servicio  ");
            }

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

            Historiales dator = new Historiales();
            dator.setAccion("F"); ////getListarVigenciaFicha
            dator.setCod_esta(cliente.getCod_esta());
            dator.setId_reservacion(Integer.parseInt(id_reservacion));
            List listarFicha = this.mi.getListarVigenciaFicha(dator);
            Historiales listaHG = (Historiales) listarFicha.get(0);

            Historiales dato = new Historiales();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setEdad(listaHG.getEdad());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            dato.setTalla(0);
            dato.setPeso(0);
            dato.setEstimc("0");
            dato.setTemperatura(0);
            dato.setId_estado("B");
            dato.setExpedido("P");
            if (!"P".equals(listaHG.getExpedido())) {
                dato.setExpedido(listaHG.getExpedido());
            }
            dato.setFc("0");
            dato.setPa("0");
            dato.setFr("0");
            dato.setCodigo("");
            dato.setRepetida("N");
            dato.setSubjetivo("Internado Vigencia");
            dato.setDiagnostico("Internado Vigencia");
            dato.setObjetivo("Internado Vigencia");
            dato.setAccion("Internar");
            dato.setCodigo("Z000");
            dato.setId_seguro(buscarPaciente.getId_seguro());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_por(cliente.getId_persona());
            dato.setSo2("0");
            dato.setGlicemia("0");
            dato.setFechalab(listaHG.getFecha());

            int iResultado = this.mi.setRegistrarHistorial(dato);
            //crea el historial nuevo en la tabla y elimina el de reservaciones
            Historiales elimina = new Historiales();
            elimina.setId_reservacion(listaHG.getId_reservacion());
            elimina.setAccion(ip);
            elimina.setCodigo(host);
            elimina.setExpedido("A");
            elimina.setId_persona(cliente.getId_persona());
            id_reservacion = Integer.toString(listaHG.getId_reservacion());
            elimina.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarReserva(elimina);

            Cuadernos dato5 = new Cuadernos();
            dato5.setAspecto("M");
            dato5.setId_historial(Integer.parseInt(id_reservacion));
            List listaCie = this.mi.getVerCuaderno1CieMorbi(dato5);
            String CIE10i = "";
            String CIE10e = "";

            dato5.setId_historial(Integer.parseInt(id_reservacion));
            dato5.setServicio(Integer.parseInt(id_consultorio));
            dato5.setId_persona(Integer.parseInt(id_persona));
            dato5.setId_consultorio(Integer.parseInt(id_consultorio));
            dato5.setReferido("");
            dato5.setContraref("");
            dato5.setDiagnostico_ing(CIE10i);
            dato5.setDiagnostico("Internado Vigencia");
            dato5.setDiasi(0);
            dato5.setDiasc(0);
            dato5.setId_estado("A");
            dato5.setIngreso(0);
            dato5.setCirugia(0);
            dato5.setAnastecia(0);
            dato5.setEgreso(0);
            dato5.setTipo_egreso(0);
            dato5.setFallecido(0);
            dato5.setFallecidom5(0);
            dato5.setFallecidoy5(9);
            dato5.setCod_esta(cliente.getCod_esta());

            if (buscarPaciente.getEdad() < 5) {
                dato5.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 5 && buscarPaciente.getEdad() <= 9) {
                dato5.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 10 && buscarPaciente.getEdad() <= 20) {
                dato5.setEdad(1);
            }
            if (buscarPaciente.getEdad() >= 21 && buscarPaciente.getEdad() <= 59) {
                dato5.setEdad(1);
            }
            if (buscarPaciente.getEdad() > 59) {
                dato5.setEdad(1);
            }
            this.mi.setCrearCuaderno5(dato5);

            Historiales datoi = new Historiales();
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setAccion("R");
            datoi.setId_historial(Integer.parseInt(id_reservacion));
            datoi.setId_cargo(2);
            datoi.setExpedido("B");
            datoi.setId_ecivil(13);////internado por vigencia admisiones
            datoi.setId_por(cliente.getId_persona());
            datoi.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarMorbilidad(datoi);
            this.mi.setModificarInternado(datoi);

            datoi.setId_consultorio(Integer.parseInt(id_consultorio));
            datoi.setId_persona(Integer.parseInt(id_persona));
            datoi.setDiagnostico("Internado por Vigencia");
            datoi.setId_estado("A");
            datoi.setId_cargo(0);
            datoi.setId_sexo(0);
            datoi.setAccion("A");
            datoi.setId_por(cliente.getId_persona());
            datoi.setId_persona(Integer.parseInt(id_persona));
            this.mi.setCrearInternado(datoi);    /////setCrearInternado2

            if ("Admision".equals(accion)) {
                //Cuadernos dato = new Cuadernos();
                dato5.setFecha(ahora);
                dato5.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato5.setId_paciente(Integer.parseInt(id_paciente));
                dato5.setId_historial(Integer.parseInt(id_reservacion));
                dato5.setId_persona(Integer.parseInt(id_persona));
                dato5.setId_consultorio(Integer.parseInt(id_consultorio));
                dato5.setId_piso(0);
                dato5.setId_sala(0);
                dato5.setId_cama(0);
                dato5.setPa("0");
                dato5.setFc("0");
                dato5.setFr("0");
                dato5.setTalla(0);
                dato5.setPeso(0);
                dato5.setTemperatura(0);
                dato5.setBacterias(cliente.getConsultorio());
                dato5.setBilirrubina(cliente.getEstablecimiento());
                dato5.setDiagnostico("Por Vigencia");
                dato5.setAspecto("");
                dato5.setCetonas("");
                dato5.setCilindros("");
                dato5.setCadena1("");
                dato5.setCadena2("");
                dato5.setCadena3("");
                dato5.setCadena4("");
                dato5.setCadena5("");
                dato5.setSuma20(Integer.parseInt(id_consultorio));
                dato5.setSuma21(cliente.getCod_esta());
                dato5.setSuma22(cliente.getId_persona());
                dato5.setId_seguro(buscarPaciente.getId_seguro());
                dato5.setCod_esta(cliente.getCod_esta());
                dato5.setAccion("A");
                mi.setCrearCuaderno1(dato5);  ///setCrearAdmision 
            }

            return new ModelAndView("Aviso", "mensaje", "El Pacientese Interno Correctamente");
        }

        if ("Internar".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String cod_esta = request.getParameter("cod_esta");
            String nombres = request.getParameter("nombres");

            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            if (cod_esta != null) {
                aa.setCod_esta(Integer.parseInt(cod_esta));
            }
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);

            Personas persona = new Personas();
            if (!"0".equals(id_consultorio) && id_consultorio != null) {
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            persona.setCod_esta(cliente.getCod_esta());
            List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
            modelo.put("listaPersonas", buscarEmpleado);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("nombres", nombres);
            return new ModelAndView("administrarpacientes/AsignarCama", modelo);
        }

        if ("eliminasscp".equals(accion) || "Reservar".equals(accion) || "Cambiar".equals(accion) || "EliminarReserva".equals(accion) || "ReservarVig".equals(accion)) {

            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String accions = request.getParameter("accions");
            String swv = request.getParameter("swv");
            
            if ("eliminasscp".equals(accion) ) {
                String id_elimina = request.getParameter("id_elimina");
                
                Pacientes elimina = new Pacientes();
                elimina.setId_vigencia(Integer.parseInt(id_elimina));
                elimina.setId_persona(cliente.getId_persona());
                this.mi.setEliminarPacienteSscp(elimina);
            }
            if("CSBP".equals(accions) || "CBES".equals(accions) || "CORDES".equals(accions) || "COSMIL".equals(accions) || "CNC".equals(accions) || "SSU".equals(accions) || "CNS".equals(accions) || "CPS".equals(accions)) {
                Pacientes paccaja = new Pacientes();
                paccaja.setId_paciente(Integer.parseInt(id_paciente));
                paccaja.setId_persona(cliente.getId_persona());
                paccaja.setCadena("CAJA NACIONAL DE SALUD");
                if("CSBP".equals(accions)) paccaja.setCadena("CAJA DE SALUD BANCA PRIVADA");
                if("CBES".equals(accions)) paccaja.setCadena("CAJA BANCARIA ESTATAL");
                if("CORDES".equals(accions)) paccaja.setCadena("CAJA CORDES");
                if("COSMIL".equals(accions)) paccaja.setCadena("CAJA COSMIL");
                if("CNC".equals(accions)) paccaja.setCadena("CAJA NACIONAL DE CAMINOS");
                if("SSU".equals(accions)) paccaja.setCadena("SEGURO SOCIAL UNIVERSITARIO");
                if("CPS".equals(accions)) paccaja.setCadena("CAJA PETROLERA DE SALUD");
                this.mi.setCrearPacienteCaja(paccaja);
            }
            
            String sw = request.getParameter("sw");
            int gsw = 1;
            if ("".equals(id_persona) || id_persona == null) {
                id_persona = "1";
            }
            Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
            Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            modelo.put("id_carpeta", Integer.toString(buscarPaciente.getId_carpeta()));

            if (cliente.getInst_id() != 10) {   /////25/07/2017 para pacientes asegurados de la lista de minsalud
                String aa = Integer.toString(buscarPaciente.getFec_nacimiento().getYear() + 1900);
                String mm = Integer.toString(buscarPaciente.getFec_nacimiento().getMonth() + 1);
                String dd = Integer.toString(buscarPaciente.getFec_nacimiento().getDate());
                String mat = "";
                String pat = "";
                String nom = "";
                if (Integer.parseInt(mm) < 10) {
                    mm = "0" + mm;
                }
                if (Integer.parseInt(dd) < 10) {
                    dd = "0" + dd;
                }
                int cpat=buscarPaciente.getPaterno().length();
                int cmat=buscarPaciente.getMaterno().length();
                int cnom=buscarPaciente.getNombre().length();
                String matric = dd + mm + aa.substring(2, 4) + buscarPaciente.getPaterno().substring(0, 1) + buscarPaciente.getMaterno().substring(0, 1) + buscarPaciente.getNombre().substring(0, 1)+ " " + buscarPaciente.getPaterno() + " " + buscarPaciente.getMaterno();
                matric = matric.replaceAll(" +", " ");
                matric = matric.replaceAll("\\s", ":*&");
                matric = matric.replaceAll("Ñ", "N");
                matric = matric.toLowerCase() + ":*";
                Pacientes paciente = new Pacientes();
                paciente.setNombres(matric);
                List listarPacientesSeg = this.mi.getListarPacientesSeguro(paciente);
                modelo.put("listaPacientesSeg", listarPacientesSeg);
                if (!listarPacientesSeg.isEmpty()) {
                    modelo.put("datosseguro", "SI");
                }

            }

            if (cliente.getId_cargo() == 11 || cliente.getId_cargo() == 12 || cliente.getId_cargo() == 10) {   /////para ecografia y rayosX
                String id_pedido = request.getParameter("id_pedido");

                Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setId_consultorio(buscarEmpleado.getId_consultorio());
                reserva.setEdad(buscarPaciente.getEdad());
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setFecha(fecha1);
                if ("S".equals(buscarPaciente.getId_estado())) {
                    reserva.setExpedido("S");
                    reserva.setId_estado("C");
                    expedido = "S";
                } else if ("P".equals(buscarPaciente.getId_estado())) {
                    reserva.setExpedido("P");
                    reserva.setId_estado("C");
                    expedido = "P";
                } else {
                    reserva.setExpedido("E");
                    reserva.setId_estado("C");
                    expedido = "E";
                }
                reserva.setId_persona(0);
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setUlt_usuario(cliente.getId_persona());///crea una reserva en tabla reservaciones
                this.mi.setCrearReservacion(reserva);

                reserva.setId_estado("%");
                List listarHG = this.mi.getListarCobroReservaUni(reserva);
                Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);
                id_reservacion = Integer.toString(listaHG.getId_reservacion());
                modelo.put("id_reservacion", id_reservacion);
                modelo.put("id_historial", id_reservacion);

                Historiales datoa = new Historiales();
                datoa.setId_historial(Integer.parseInt(id_reservacion));
                datoa.setId_persona(cliente.getId_persona());////03/05/2014 id_persona
                datoa.setId_paciente(Integer.parseInt(id_paciente));
                datoa.setEdad(buscarPaciente.getEdad());
                datoa.setId_consultorio(cliente.getId_consultorio());
                datoa.setTalla(0);
                datoa.setPeso(0);
                datoa.setEstimc("0");
                datoa.setTemperatura(0);
                datoa.setExpedido(expedido);
                datoa.setFc("0");
                datoa.setPa("0");
                datoa.setFr("0");
                datoa.setCodigo("");
                datoa.setRepetida("N");
                datoa.setSubjetivo("Imagenologia");
                datoa.setDiagnostico("Imagenologia");
                datoa.setObjetivo("Ecografia");
                if (cliente.getId_cargo() == 12) {
                    datoa.setObjetivo("RayosX");
                }
                datoa.setAccion("Pedido");
                datoa.setCodigo("Z718");
                datoa.setSo2("");
                datoa.setGlicemia("");
                datoa.setId_seguro(buscarPaciente.getId_seguro());
                datoa.setCod_esta(cliente.getCod_esta());
                datoa.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(datoa);

                //crea el historial nuevo en la tabla y elimina el de reservaciones
                Historiales elimina = new Historiales();
                elimina.setId_reservacion(Integer.parseInt(id_reservacion));
                elimina.setAccion(ip);
                elimina.setCodigo(host);
                elimina.setExpedido("A");
                elimina.setCod_esta(cliente.getCod_esta());
                elimina.setId_persona(cliente.getId_persona());
                this.mi.setEliminarReserva(elimina);

                Cuadernos datol = new Cuadernos();
                datol.setTipoconsulta("P");  ////getLabPacPasados
                datol.setId_historial(Integer.parseInt(id_reservacion));
                datol.setId_paciente(Integer.parseInt(id_paciente));
                datol.setCod_esta(cliente.getCod_esta());
                //List listalab = this.mi.getLabPacPasados(datol);
                //modelo.put("listalab", listalab);
                //Cuadernos dato = new Cuadernos();

                datol.setResultado("");
                datol.setId_persona(cliente.getId_persona());
                datol.setId_dispensa(cliente.getId_persona());
                datol.setTipo(expedido);
                datol.setCod_esta(cliente.getCod_esta());
                datol.setTipoconsulta("");
                datol.setNombres(cliente.getNombres());
                datol.setNombre(cliente.getEstablecimiento());
                datol.setSeleccion(0);
                datol.setId_historia(0);
                datol.setFechap(fecha1);
                datol.setFechae(fecha1);
                datol.setTipo(expedido);
                if ("E".equals(expedido)) {
                    datol.setId_estado("G");
                } else {
                    datol.setId_estado("A");
                }

                List listaP = this.mi.getPedidoLab(datol);/// saca datos de pedidoslab

                if (listaP.isEmpty() == true) {
                    this.mi.setCrearPedidoLab(datol);
                    List listaPs = this.mi.getPedidoLab(datol);
                    Cuadernos datoped = (Cuadernos) listaPs.get(0);
                    id_pedido = Integer.toString(datoped.getId_pedido());
                } else {
                    Cuadernos datoped = (Cuadernos) listaP.get(0);
                    id_pedido = Integer.toString(datoped.getId_pedido());
                }
                modelo.put("id_pedido", id_pedido);
                Cuadernos dato = new Cuadernos();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_historia(dato.getId_historia());
                dato.setId_paciente(Integer.parseInt(id_persona));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_pedido(Integer.parseInt(id_pedido));
                dato.setLaboratorio("ECOGRAFIA");
                dato.setId_costo(136);
                dato.setId_laboratorio(12);
                if (cliente.getId_cargo() == 12) {
                    dato.setLaboratorio("RAYOS X");
                    dato.setId_costo(138);
                    dato.setId_laboratorio(13);
                }
                dato.setResultado("");
                dato.setTipoconsulta(".");
                dato.setId_estado("A");
                dato.setId_por(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                if (cliente.getId_cargo() == 11 || cliente.getId_cargo() == 12) {
                    this.mi.setCrearLaboratorioC(dato);
                }

                dato.setTipoconsulta("I");
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_pedido(Integer.parseInt(id_pedido));
                if (cliente.getId_laboratorio() == 0) {
                    dato.setId_cargo(0);
                    dato.setId_laboratorio(999);
                } else {
                    dato.setId_cargo(cliente.getId_laboratorio());
                    dato.setId_laboratorio(cliente.getId_laboratorio());
                }
                dato.setCod_esta(cliente.getCod_esta());
                List lista = this.mi.getDatoPedidoLab(dato);   ////getDatoPedidoLab
                Cuadernos listal = (Cuadernos) lista.get(0);
                modelo.put("estab", listal.getNombre());
                modelo.put("medico", listal.getNombres());
                modelo.put("numero", Integer.toString(listal.getNumpieza()));
                //Buscar Sexo
                Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
                modelo.put("buscarSexo", buscarsexo);

                //dato.setTipo(tipo); 
                if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
                    List listalab = this.mi.getLabPendiente(dato);////10-05-2015
                    modelo.put("listalab", listalab);
                }
                if (datosconsul.getId_cargo() == 11) {
                    List listalab = this.mi.getLabPendienteEco(dato);
                    modelo.put("listalab", listalab);
                }
                if (datosconsul.getId_cargo() == 12) {
                    List listalab = this.mi.getLabPendienteRx(dato);
                    modelo.put("listalab", listalab);
                }
                //List listalab = this.mi.getLabPendiente(dato);
                //modelo.put("listalab", listalab);
                modelo.put("expedido", expedido);
                return new ModelAndView("administrarlaboratorio/LabPendiente", modelo);

            }

            if (cliente.getCod_esta() == 2000010) {   /////datoest.getCod_esta() 
                String matric = buscarPaciente.getRegistro();

                if (matric.length() == 9) {
                    matric = "0" + matric.substring(0, 1) + "-" + matric.substring(1, 4) + "-" + matric.substring(4, 9);
                    gsw = 2;
                }
                if (matric.length() == 10) {
                    matric = matric.substring(0, 2) + "-" + matric.substring(2, 5) + "-" + matric.substring(5, 10);
                }
                String url = "http://vlags.cns.gob.bo/erpWS/swSegurityServices/wsSecureVigencia.asmx/GetInformacionEmpleador?userId=1&user=cns.vigencia&pass=cns.vigencia.2017&userFinal=C006R00A2R&NroPatronal=" + matric + "&ubicacionId=2";
                URL obj = new URL(url);
                HttpURLConnection con = null;
                BufferedReader in = null;
                String inputLine;
                StringBuffer response1 = new StringBuffer();
                try {
                    con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } catch (Exception e) {
                    con = null;
                }
                if (con != null) {
                    while ((inputLine = in.readLine()) != null) {///////////////////
                        response1.append(inputLine);
                    }
                    in.close();
                    byte[] bytes = response1.toString().getBytes();
                    InputStream inputStream = new ByteArrayInputStream(bytes);
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(inputStream);
                    doc.getDocumentElement().normalize();
                    List empresadatos = new ArrayList();
                    NodeList nList = doc.getElementsByTagName("Table1");
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            Empresasws datoemp = new Empresasws();
                            try {
                                int id = Integer.parseInt(eElement.getElementsByTagName("Orden").item(0).getTextContent());
                                datoemp.setId(id);
                            } catch (Exception e) {
                                datoemp.setId(0);
                            }
                            try {
                                datoemp.setNropatronal(eElement.getElementsByTagName("NroPatronal").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setNropatronal("SinPatronal");
                            }
                            try {
                                datoemp.setRazonsocial(eElement.getElementsByTagName("RazonSocial").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setRazonsocial("SinRazonSocial");
                            }
                            try {
                                datoemp.setNit(eElement.getElementsByTagName("NIT").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setNit("0");
                            }
                            try {
                                datoemp.setEstadomora(eElement.getElementsByTagName("EstadoMora").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setEstadomora("SD");
                            }
                            try {
                                datoemp.setEstadoafiliacion(eElement.getElementsByTagName("EstadoAfiliacion").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setEstadoafiliacion("SE");
                            }
                            try {
                                datoemp.setDetalle(eElement.getElementsByTagName("PeridoPago").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setDetalle("Sin Dato");
                            }
                            try {
                                datoemp.setFecha(eElement.getElementsByTagName("Fecha").item(0).getTextContent());
                            } catch (Exception e) {
                                datoemp.setFecha("Sin Fecha");
                            }
                            empresadatos.add(datoemp);
                        }
                    }
                    modelo.put("empresadatos", empresadatos);
                }

                Pacientes paciente = new Pacientes();
                paciente.setTipo("C");    ////getListarPacientesCaja 30/06/2016 se elimina por ora base de datos para afiliacion
                paciente.setId_carpeta(buscarPaciente.getId_carpeta());
                paciente.setId_paciente(buscarPaciente.getId_paciente());
                //List listarPac = this.mi.getListarPacientesCaja(paciente);   /////getListarPacientesCaja
                //if(listarPac.size()!=0){
                //    Pacientes datopac = (Pacientes) listarPac.get(0);
                //   modelo.put("datopacv", datopac);
                // }   
                modelo.put("area", datoestab.getArea());
                modelo.put("id_reservacion", id_reservacion);
                modelo.put("id_consultorio", id_consultorio);
                Date fecha_nac = buscarPaciente.getFec_nacimiento();
                int xanio = fecha_nac.getYear() + 1900;
                int xmes = fecha_nac.getMonth() + 1;
                int xdia = fecha_nac.getDate();
                String a = "/";
                String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
                modelo.put("fec_nacimiento", fecha_nacimiento);

            }

            if (cliente.getCod_esta() == 200010) {   /////datoest.getCod_esta() 
                if ("ReservarVig".equals(accion)) {
                    return new ModelAndView("administrarpacientes/DocumentosVigencia", modelo);
                }
            }

            modelo.put("area", datoestab.getArea());
            modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
            if ("T".equals(datoestab.getArea()) || "P".equals(datoestab.getArea()) || "S".equals(datoestab.getArea())) {
                gsw = 0; // saca tipo de estab Privado es "P" y no obliga a <5 deben ser SUMI 
            }
            if (buscarPaciente.getDia() < 0) {
                return new ModelAndView("Aviso", "mensaje", "La fecha de Nacimiento no es correcta");
            }

            ///    if (buscarPaciente.getEdad()>59 && "A".equals(buscarPaciente.getId_estado()))  return new ModelAndView("Aviso","mensaje","Este Paciente es mayor de 60 años , debe estar afiliado al SPS (SUMI)");    
            //if (buscarPaciente.getEdad()<5 && "A".equals(buscarPaciente.getId_estado()) && gsw==1)  return new ModelAndView("Aviso","mensaje","El paciente es menor 5 años, debe estar afiliado al SPS (SUMI)");
            String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo
            String id_documento_e = Integer.toString(buscarPaciente.getId_tipo_documento());
            String id_escolaridad_e = Integer.toString(buscarPaciente.getId_escolaridad());          //Sacar id_sexo
            int id_pais_e = buscarPaciente.getId_pais();          //Sacar id_pais 
            int id_departamento_e = buscarPaciente.getId_departamento();  //Sacar id_departamento 
            int id_provincia_e = buscarPaciente.getId_provincia();     //Sacar id_provincia       
            int id_localidad_e = buscarPaciente.getId_localidad();     //Sacar id_localidad       

            int id_estado_civil_e = buscarPaciente.getId_ecivil();

            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            Date fecha_reg = buscarPaciente.getFec_registro();
            int xanio_r = fecha_reg.getYear() + 1900;
            int xmes_r = fecha_reg.getMonth() + 1;
            int xdia_r = fecha_reg.getDate();

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            //Buscar documento
            Documentos buscardocumento = this.mi.getDatosDocumento(Integer.parseInt(id_documento_e)); // saca un registro a ser modificado
            modelo.put("buscarDocumento", buscardocumento);

            //Buscar Escolaridad
            Escolaridades buscarescolaridad = this.mi.getDatosEscolaridad(Integer.parseInt(id_escolaridad_e)); // saca un registro a ser modificado
            modelo.put("buscarEscolaridad", buscarescolaridad);

            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(id_estado_civil_e); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);
            try {//Buscar Pais
                Paises buscarPais = this.mi.getDatosPais(id_pais_e); // saca un registro a ser modificado
                modelo.put("buscarPais", buscarPais);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Pais");
            }
            try {//Buscar Departamento    
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(id_departamento_e); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Departamento");
            }
            try {
                Provincias buscarProvincia = this.mi.getDatosProvincia(id_provincia_e);
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Provincia");
            }
            try { //Buscar Localidad
                Localidades localidad = new Localidades();
                localidad.setId_localidad(id_localidad_e);
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Localidad");
            }
            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            String fecha_registro = Integer.toString(xdia_r) + a + Integer.toString(xmes_r) + a + Integer.toString(xanio_r);
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", Integer.toString(xanio_r));
            modelo.put("mes_r", Integer.toString(xmes_r));
            modelo.put("dia_r", Integer.toString(xdia_r));

            if (xanio < 1901) {
                return new ModelAndView("Aviso", "mensaje", "El año de nacimiento no es correcto");
            }
            modelo.put("anios", aniosqq);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("horas", horas);
            modelo.put("minutos", minutos);
            modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("dia", Integer.toString(fecha1.getDate()));
            modelo.put("hora", Integer.toString(fecha1.getHours()));
            modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
            modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("diae", Integer.toString(fecha1.getDate()));
            modelo.put("horae", Integer.toString(fecha1.getHours()));
            modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));
            //lista de consultorios
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);
            if ("1".equals(resvig) || cliente.getId_rol() == 23) {////getListarConsultoriosEmerg para Obrero solo personal de emergencias
                aa.setId_estado("U");
                aa.setId_especialidad(1);
                persona.setDip("U");
                List listarCargos2 = this.mi.getListarConsultoriosUrgen(aa);
                modelo.put("listarCargos", listarCargos2);
                if (cliente.getId_rol() == 4 || cliente.getId_rol() == 12) { //enfermera cons ext. enfermeria CIMFA
                    persona.setDip("A");////paar que aparesca en enfermeria los medicos asignados
                    List listarCargos3 = this.mi.getListarConsultorios(aa);
                    modelo.put("listarCargos", listarCargos3);
                }
                if (cliente.getId_rol() == 2 || cliente.getId_rol() == 26) {
                    aa.setId_estado("X");   ///23-07-2015 estaba C pero eso era para Centros que se creo nuevo
                    persona.setDip("X");////persona.setDip("A"); 29/04/2015 para estadistica de Obrero aparece todos los consultoriosï¿½
                    List listarCargos4 = this.mi.getListarConsultorios(aa);
                    modelo.put("listarCargos", listarCargos4);
                }

            }

            if (id_consultorio != null && !"".equals(id_consultorio)) {
                //Personas persona= new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
                modelo.put("tipoconsult", tipoconsult);
                modelo.put("id_persona", id_persona);
                modelo.put("id_riesgo", id_riesgo);
                if (id_persona != null && !"0".equals(id_persona)) {
                    Personas persona3 = this.mi.getBuscarPersona(Integer.parseInt(id_persona)); // saca un registro a ser modificado 
                    modelo.put("nropac", persona3.getNropac());
                    Historiales dato = new Historiales();
                    dato.setId_consultorio(Integer.parseInt(id_consultorio));
                    dato.setId_persona(Integer.parseInt(id_persona));
                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setAccion("P");
                    dato.setId_estado("%");
                    List listarResv2 = this.mi.getListarReserFichas(dato);  ///getListarReserFichas
                    modelo.put("milista2", listarResv2);
                }

            }

            if ("eco".equals(swv)) {
                Cuadernos datoLab = new Cuadernos();
                String id_cuaderno = request.getParameter("id_cuaderno");
                id_consultorio = "19";
                datoLab.setId_cuaderno(Integer.parseInt(id_cuaderno));
                this.mi.setReservaLabEcografia(datoLab);
            }
            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("tipoconsult", tipoconsult);

            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milista", listas);

        }

        modelo.put("id_paciente", id_paciente);
        return new ModelAndView("administrarpacientes/ConfirmarPaciente", modelo);
    }

}
