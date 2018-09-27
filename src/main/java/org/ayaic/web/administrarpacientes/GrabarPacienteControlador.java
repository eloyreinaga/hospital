package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarPacienteControlador {

    private final MiFacade mi;

    public GrabarPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String acciont = request.getParameter("acciont");
        String accionsa = request.getParameter("accionsa");
        String resvig = request.getParameter("resvig");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_reservacion = request.getParameter("id_reservacion");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String id_empresa = request.getParameter("id_empresa");
        String ocupacion = request.getParameter("ocupacion");
        String carnet = request.getParameter("carnet");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        String zoom = request.getParameter("zoom");
        String registro = request.getParameter("registro");
        String residencia = request.getParameter("residencia");
        String nomempresa = request.getParameter("nomempresa");
        String patronal = request.getParameter("patronal");
        String id_farmacia = request.getParameter("id_farmacia");
        String hcl = request.getParameter("hcl");
        String nombre = request.getParameter("nombre");
        String nombres = request.getParameter("nombres");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String nro_registro = request.getParameter("nro_registro");
        String policonsul = request.getParameter("policonsul");
        String nro = request.getParameter("nro");
        String expedido = request.getParameter("expedido");
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String dia_r = request.getParameter("dia_r");
        String mes_r = request.getParameter("mes_r");
        String anio_r = request.getParameter("anio_r");
        String id_sexo = request.getParameter("id_sexo");
        String nropac = request.getParameter("nropac");
        String id_documento = request.getParameter("id_documento");
        String id_escolaridad = request.getParameter("id_escolaridad");
        String id_estado_civil = request.getParameter("id_ecivil");
        String id_departamento = request.getParameter("id_departamento");
        String id_provincia = request.getParameter("id_provincia");
        String id_pais = request.getParameter("id_pais");
        String id_localidad = request.getParameter("id_localidad");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_triaje = request.getParameter("id_triaje");
        String id_riesgo = request.getParameter("id_riesgo");
        String tipoconsult = request.getParameter("tipoconsult");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String[] aniosq = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        Date ahora = new Date();
        Date dFechaini1 = new Date();
        Date dFechafin1 = new Date();
        Date Fecha1 = new Date();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String numregistro = "";

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("estab", Integer.toString(datoestab.getCod_esta()));

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("resvig", resvig);
        modelo.put("dato", cliente);

        if ("CambiarConsultorio".equals(acciont)) {
            String id_consultoriot = request.getParameter("id_consultoriot");
            String id_personat = request.getParameter("id_personat");
            String diaa = request.getParameter("diaa");
            String mesa = request.getParameter("mesa");
            String anioa = request.getParameter("anioa");
            String horaa = request.getParameter("horaa");
            String minutoa = request.getParameter("minutoa");
            String diab = request.getParameter("diab");
            String mesb = request.getParameter("mesb");
            String aniob = request.getParameter("aniob");
            String horab = request.getParameter("horab");
            String minutob = request.getParameter("minutob");

            if (request.getParameter("diaa") != null) {
                int iAnio1 = Integer.parseInt(anioa) - 1900;
                int iMes1 = Integer.parseInt(mesa) - 1;
                int iDia1 = Integer.parseInt(diaa);
                int iHor1 = Integer.parseInt(horaa);
                int iMin1 = Integer.parseInt(minutoa);

                int iAnio2 = Integer.parseInt(aniob) - 1900;
                int iMes2 = Integer.parseInt(mesb) - 1;
                int iDia2 = Integer.parseInt(diab);
                int iHor2 = Integer.parseInt(horab);
                int iMin2 = Integer.parseInt(minutob);

                dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);
            }
            Historiales reserva = new Historiales();
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setId_persona(Integer.parseInt(id_persona));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            reserva.setId_reservacion(Integer.parseInt(id_consultoriot));
            reserva.setId_historia(Integer.parseInt(id_personat));
            reserva.setFecha_ini(dFechaini1);
            reserva.setFecha_fin(dFechafin1);
            reserva.setCadena(ip);
            reserva.setCodigo(host);
            reserva.setExpedido("M");
            reserva.setAccion("T");////setModificarReservaTotal         
            this.mi.setModificarReservaTotal(reserva);
            return new ModelAndView("Aviso", "mensaje", "Se ha realizado el Cambio, satisfactoriamente");
        }

        // eliminar espacios
        nombre = nombre.trim();
        paterno = paterno.trim();
        materno = materno.trim();

        if ("".equals(id_consultorio) || id_consultorio == null) {
            id_consultorio = "0";
        }
        if ("".equals(id_triaje) || id_triaje == null) {
            id_triaje = "0";
        }
        if ("".equals(id_riesgo) || id_riesgo == null) {
            id_riesgo = "0";
        }
        if ("".equals(tipoconsult) || tipoconsult == null) {
            tipoconsult = "0";
        }
        if ("".equals(resvig) || resvig == null) {
            resvig = "0";
        }

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(direccion)) || ("".equals(nombre))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fec_nacimiento = new Date(aniox, mesx, diax);

            String p = paterno.substring(0, 1);
            String m = materno.substring(0, 1);
            String n = nombre.substring(0, 1);

            if (!"1".equals(id_pais)) {
                id_departamento = "0";
                id_provincia = "0";
                id_localidad = "0";
            }

            if ("O".equals(cliente.getArea())) { ///Para Hospital Obrero
                String a = anio.substring(0, 4);
                if (Integer.parseInt(dia) < 10 && Integer.parseInt(mes) < 10) {
                    numregistro = a + "0" + Integer.toString(Integer.parseInt(mes)) + "0" + Integer.toString(Integer.parseInt(dia)) + n + p + m;
                }
                if (Integer.parseInt(dia) < 10 && Integer.parseInt(mes) > 9) {
                    numregistro = a + mes + "0" + Integer.toString(Integer.parseInt(dia)) + n + p + m;
                }
                if (Integer.parseInt(dia) > 9 && Integer.parseInt(mes) < 10) {
                    numregistro = a + "0" + Integer.toString(Integer.parseInt(mes)) + dia + n + p + m;
                }
                if (Integer.parseInt(dia) > 9 && Integer.parseInt(mes) > 9) {
                    numregistro = a + mes + dia + n + p + m;
                }
            } else {
                String a = anio.substring(2, 4);
                if (Integer.parseInt(dia) < 10 && Integer.parseInt(mes) < 10) {
                    numregistro = "0" + Integer.toString(Integer.parseInt(dia)) + "0" + Integer.toString(Integer.parseInt(mes)) + a + "-" + n + p + m;
                }
                if (Integer.parseInt(dia) < 10 && Integer.parseInt(mes) > 9) {
                    numregistro = "0" + Integer.toString(Integer.parseInt(dia)) + mes + a + "-" + n + p + m;
                }
                if (Integer.parseInt(dia) > 9 && Integer.parseInt(mes) < 10) {
                    numregistro = dia + "0" + Integer.toString(Integer.parseInt(mes)) + a + "-" + n + p + m;
                }
                if (Integer.parseInt(dia) > 9 && Integer.parseInt(mes) > 9) {
                    numregistro = dia + mes + a + "-" + n + p + m;
                }
            }

            if (datoestab.getCod_esta() == 200010) {

                if (!"".equals(patronal) && patronal != null) {

                    Empresas empresa = new Empresas();
                    empresa.setCod_patronal(Long.parseLong(patronal));
                    empresa.setTipo("L");
                    List listarEmp = this.mi.getListaEmpresaCod(empresa);

                    if (listarEmp.isEmpty()) {
                        Empresas datoempresa = new Empresas();
                        datoempresa.setEmpresa(nomempresa);
                        datoempresa.setCod_patronal(Long.parseLong(patronal));
                        datoempresa.setNit("0");
                        datoempresa.setDireccion("SD");
                        datoempresa.setTelefonos("0");
                        datoempresa.setResponsable("SR");
                        datoempresa.setUlt_usuario(1);
                        this.mi.setCrearEmpresa(datoempresa);
                    }
                    empresa.setCod_patronal(Long.parseLong(patronal));
                    empresa.setTipo("L");
                    List listarEmp2 = this.mi.getListaEmpresaCod(empresa);

                    Empresas datempresa2 = (Empresas) listarEmp2.get(0);
                    id_empresa = Integer.toString(datempresa2.getId_empresa());
                    nomempresa = datempresa2.getEmpresa();
                }
            }
            if ("".equals(latitud) || latitud == null) {
                latitud = datoestab.getLatitud();
            }
            if ("".equals(longitud) || longitud == null) {
                longitud = datoestab.getLongitud();
            }
            if ("".equals(zoom) || zoom == null) {
                zoom = "15";
            }
            paterno=paterno.replaceAll("�", "Ñ");
            materno=materno.replaceAll("�", "Ñ");
            nombre = nombre.replaceAll(" +", " ");
            Pacientes datopaciente = new Pacientes();
            datopaciente.setDireccion(direccion);
            datopaciente.setTelefono(telefono);
            datopaciente.setOcupacion(ocupacion);
            datopaciente.setCarnet(carnet);
            datopaciente.setLatitud(latitud);
            datopaciente.setLongitud(longitud);
            datopaciente.setZoom(zoom);
            datopaciente.setResidencia(Integer.parseInt(residencia));
            datopaciente.setNombres(nombre + ' ' + paterno + ' ' + materno);
            datopaciente.setNombre(nombre);
            datopaciente.setPaterno(paterno);
            datopaciente.setMaterno(materno);
            datopaciente.setUlt_usuario(cliente.getId_persona());
            datopaciente.setFec_nacimiento(fec_nacimiento);
            datopaciente.setFec_registro(Fecha1);
            datopaciente.setNro_registro(numregistro);
            datopaciente.setId_policlinico(Integer.parseInt(policonsul));
            datopaciente.setId_estado("A");
            datopaciente.setId_seguro(0);
            datopaciente.setCod_esta(cliente.getCod_esta());
            if (datoestab.getCod_esta() == 200010) {////22/09/2014
                datopaciente.setNro_registro(nro_registro.toUpperCase());
                datopaciente.setNro(nro.toUpperCase());
                datopaciente.setExpedido(expedido.toUpperCase());
                datopaciente.setId_estado("P");
                datopaciente.setId_seguro(35);
                datopaciente.setCadena10(nomempresa);
            }

            datopaciente.setHcl(this.mi.getNumHcl()); ///datopaciente.setHcl(this.mi.getNumHcl());  16/06/2014 se cambio la consulta a la tabla establecimientos
            datopaciente.setId_tipo_sexo(Integer.parseInt(id_sexo));
            datopaciente.setId_tipo_documento(Integer.parseInt(id_documento));
            datopaciente.setId_escolaridad(Integer.parseInt(id_escolaridad));
            datopaciente.setId_empresa(Integer.parseInt(id_empresa));
            datopaciente.setFactor_riesgo("");
            datopaciente.setId_ecivil(Integer.parseInt(id_estado_civil));
            datopaciente.setId_pais(Integer.parseInt(request.getParameter("id_pais")));
            datopaciente.setId_departamento(Integer.parseInt(id_departamento));
            datopaciente.setId_provincia(Integer.parseInt(id_provincia));
            datopaciente.setId_localidad(Integer.parseInt(id_localidad));

            try {
                this.mi.setCrearPaciente(datopaciente);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion pacientes no se cumplio, verifique los datos");
            }
            //listar pacientees
            datopaciente.setTipo("W");
            datopaciente.setId_estado("%");
            if (datoestab.getCod_esta() == 200010) {
                datopaciente.setTipo("C");
            }
            paterno = paterno.replaceAll("ñ", "n");
            materno = materno.replaceAll("Ñ", "N");
            nombre = nombre + ' ' + paterno + ' ' + materno;
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombre.replaceAll("\\s", "%");
            nombres = "%" + nombres.toUpperCase() + "%";
            nombre = nombre.replaceAll("\\s", ":*&");///:*&
            nombre = nombre.replaceAll("ñ", "n");
            nombre = nombre.replaceAll("Ñ", "N");
            nombre = nombre + ":*";
            datopaciente.setNombres(nombre); ///para la busqueda po lexemas
            datopaciente.setNombre(nombres); ///para la busqueda po lexemas
            datopaciente.setCod_esta(cliente.getCod_esta()); ///para la busqueda por lexemas
            if (datoestab.getCompartehcl() == 1) {  //////1 comparte la historia clinica
                datopaciente.setTipo("C");  ///getListarPacientesNombreCns
            }
            if ("P".equals(datoestab.getArea())) {
                List listarPaises = this.mi.getListarPacientesPrivNom(datopaciente);
                modelo.put("listaPacientes", listarPaises);
            }else{
                List listarPaises = this.mi.getListarPacientes(datopaciente);
                modelo.put("listaPacientes", listarPaises);
            }
            
            return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            String swci = request.getParameter("swci");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            if (("".equals(id_paciente)) || ("".equals(direccion)) || ("".equals(nombre))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fec_nacimiento = new Date(aniox, mesx, diax);

            if (!"1".equals(id_pais)) {
                id_departamento = "0";
                id_provincia = "0";
                id_localidad = "0";
            }

            if (datoestab.getCod_esta() == 200010) {
                if (!"".equals(patronal) && patronal != null) {
                    Empresas empresa = new Empresas();
                    empresa.setCod_patronal(Long.parseLong(patronal));
                    empresa.setTipo("L");
                    List listarEmp = this.mi.getListaEmpresaCod(empresa);

                    if (listarEmp.isEmpty()) {
                        Empresas datoempresa = new Empresas();
                        datoempresa.setEmpresa(nomempresa);
                        datoempresa.setCod_patronal(Long.parseLong(patronal));
                        datoempresa.setNit("0");
                        datoempresa.setDireccion("SD");
                        datoempresa.setTelefonos("0");
                        datoempresa.setResponsable("SR");
                        datoempresa.setUlt_usuario(1);
                        this.mi.setCrearEmpresa(datoempresa);
                    }
                    empresa.setCod_patronal(Long.parseLong(patronal));
                    empresa.setTipo("L");
                    List listarEmp2 = this.mi.getListaEmpresaCod(empresa);

                    Empresas datempresa2 = (Empresas) listarEmp2.get(0);
                    id_empresa = Integer.toString(datempresa2.getId_empresa());
                }
            }
            paterno=paterno.replaceAll("�", "Ñ");
            materno=materno.replaceAll("�", "Ñ");
            nombre = nombre.replaceAll(" +", " ");
            Pacientes categoria_m = new Pacientes();
            categoria_m.setId_paciente(Integer.parseInt(id_paciente));
            categoria_m.setDireccion(direccion);
            categoria_m.setTelefono(telefono);
            categoria_m.setOcupacion(ocupacion);
            categoria_m.setCarnet(carnet);
            categoria_m.setNombres(nombre + ' ' + paterno + ' ' + materno);
            categoria_m.setNombre(nombre);
            categoria_m.setPaterno(paterno);
            categoria_m.setMaterno(materno);
            categoria_m.setNro_registro(nro_registro.toUpperCase());
            categoria_m.setId_policlinico(Integer.parseInt(policonsul));
            categoria_m.setNro(nro.toUpperCase());
            categoria_m.setExpedido(expedido.toUpperCase());
            categoria_m.setResidencia(Integer.parseInt(residencia));
            categoria_m.setId_empresa(Integer.parseInt(id_empresa));
            categoria_m.setId_seguro(buscarPaciente.getId_seguro());
            categoria_m.setUlt_usuario(cliente.getId_persona());
            categoria_m.setFec_nacimiento(fec_nacimiento);
            categoria_m.setFec_registro(Fecha1);
            categoria_m.setCod_esta(cliente.getCod_esta());
            ///if(Integer.parseInt(hcl)>this.mi.getNumHcl()){
            ///   return new ModelAndView("Aviso","mensaje","No puede colocar Numeros de HCL mayores, busque numero de historias vacias"); 
            ///}else{
            categoria_m.setHcl(Integer.parseInt(hcl));
            ///}
            if ("".equals(latitud) || latitud == null) {
                latitud = datoestab.getLatitud();
            }
            if ("".equals(longitud) || longitud == null) {
                longitud = datoestab.getLongitud();
            }
            if ("".equals(zoom) || zoom == null) {
                zoom = "15";
            }
            categoria_m.setId_tipo_sexo(Integer.parseInt(id_sexo));
            categoria_m.setId_tipo_documento(Integer.parseInt(id_documento));
            categoria_m.setTipo_sanguineo(buscarPaciente.getTipo_sanguineo());
            categoria_m.setFactor_riesgo(buscarPaciente.getFactor_riesgo());
            categoria_m.setId_escolaridad(Integer.parseInt(id_escolaridad));
            categoria_m.setId_ecivil(Integer.parseInt(id_estado_civil));
            categoria_m.setId_estado(request.getParameter("id_estado"));
            categoria_m.setId_pais(Integer.parseInt(request.getParameter("id_pais")));
            categoria_m.setLatitud(latitud);
            categoria_m.setLongitud(longitud);
            categoria_m.setZoom(zoom);
            try {
                categoria_m.setId_departamento(Integer.parseInt(request.getParameter("id_departamento")));
                categoria_m.setId_provincia(Integer.parseInt(id_provincia));
                categoria_m.setId_localidad(Integer.parseInt(id_localidad));
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try {
                this.mi.setModificarPaciente(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos, el munero de historia clinica puede estar ocupado");
            }
            if (datoestab.getCod_esta() == 200010) {
                categoria_m.setTipo("C");
            }
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombre.replaceAll("\\s", "%");
            nombres = "%" + nombres.toUpperCase() + "%";
            nombre = nombre.replaceAll("\\s", ":*&");///:*&
            categoria_m.setNombres(nombre + ":*&" + paterno.replaceAll("Ñ", "N") + ":*&" + materno.replaceAll("Ñ", "N") + ":*"); ///para la busqueda po lexemas
            categoria_m.setNombre(nombres);
            List listaPacientes = this.mi.getListarPacientes(categoria_m);
            modelo.put("listaPacientes", listaPacientes);
            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listaPacAfi", listarSeguros);

            return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_paciente"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Pacientes elimina = new Pacientes();
            elimina.setId_paciente(Integer.parseInt(request.getParameter("id_paciente")));

            try {
                this.mi.setEliminarPaciente(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
        }

        if ("Afiliar".equals(accion) && "Aceptar".equals(accion1)) {
            String tipo = request.getParameter("tipo");
            int diax_r = Integer.parseInt(dia_r);
            int mesx_r = Integer.parseInt(mes_r) - 1;
            int aniox_r = Integer.parseInt(anio_r) - 1900;
            Date fec_registro = new Date(aniox_r, mesx_r, diax_r);

            Pacientes creasumi = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            creasumi.setUlt_usuario(cliente.getId_persona());
            creasumi.setId_seguro(Integer.parseInt(tipo));
            creasumi.setId_estado("P");
            nombre = creasumi.getNombres();
            Seguros repet = this.mi.getDatosSeguro(Integer.parseInt(request.getParameter("tipo")));

            if ("A".equals(repet.getTipo()) || "B".equals(repet.getTipo()) || "C".equals(repet.getTipo()) || "D".equals(repet.getTipo()) || "E".equals(repet.getTipo()) || "F".equals(repet.getTipo())) {
                creasumi.setId_estado("S");
            }
            try {
                this.mi.setModificarPaciente(creasumi);

                if (creasumi.getDia() < 0) {
                    return new ModelAndView("Aviso", "mensaje", "La fecha de Nacimiento no es correcta");
                }

                if ("1".equals(tipo) || "2".equals(tipo) || "3".equals(tipo) || "4".equals(tipo) || "5".equals(tipo)) {
                    if ("1".equals(tipo) && creasumi.getEdad() < 6) {
                        creasumi.setId_estado("S");
                    } else {
                        if (("2".equals(tipo) || "3".equals(tipo)) && creasumi.getEdad() > 10 && creasumi.getEdad() < 60 && creasumi.getId_tipo_sexo() == 1) {
                            creasumi.setId_estado("S");
                        } else {
                            if (("4".equals(tipo)) && creasumi.getEdad() > 59) {
                                creasumi.setId_estado("S");
                            } else {
                                if (("5".equals(tipo))) {
                                    creasumi.setId_estado("S");
                                } else {
                                    return new ModelAndView("Aviso", "mensaje", "Este Paciente no pertenece a este SEGURO, corriga Sexo o Edad");
                                }

                            }
                        }
                    }
                }

            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            creasumi.setFec_registro(Fecha1);
            creasumi.setUlt_usuario(cliente.getId_usuario());
            creasumi.setTipo(repet.getTipo());
            if (!"O".equals(cliente.getArea())) { ///Para Hospital Obrero
                creasumi.setId_carpeta(0);
                creasumi.setId_empresa(0);
            }
            if ("".equals(registro) || registro == null) {
                registro = "0";
            }
            creasumi.setRegistro(registro);
            creasumi.setCarnet(registro);
            try {
                this.mi.setCrearPacienteSumi(creasumi);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro no se actualizo tiene dependencias");
            }
            //listar categorias
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombre.replaceAll("\\s", "%");
            nombres = "%" + nombres.toUpperCase() + "%";
            nombre = nombre.replaceAll("\\s", ":*&");///:*&
            creasumi.setNombres(nombre + ":*"); ///para la busqueda po lexemas
            creasumi.setId_estado("%");
            creasumi.setNombre(nombres);
            List listaPacientes = this.mi.getListarPacientes(creasumi);
            modelo.put("listaPacientes", listaPacientes);

            return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
        }

        if ("Desafiliar".equals(accion) && "Aceptar".equals(accion1)) {

            Pacientes creasumi = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            creasumi.setUlt_usuario(cliente.getId_persona());
            creasumi.setId_estado("A");
            try {
                this.mi.setModificarPaciente(creasumi);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            // colocar fecha de baja en paciente sumi

            this.mi.setModificarPacienteSumi(creasumi);

            //listar categorias
            nombre = nombre.trim();
            nombre = nombre.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombre.replaceAll("\\s", "%");
            nombres = "%" + nombres.toUpperCase() + "%";
            nombre = nombre.replaceAll("\\s", ":*&");///:*&
            creasumi.setNombres(nombre + ":*&" + paterno + ":*&" + materno + ":*"); ///para la busqueda po lexemas
            creasumi.setId_estado("%");
            creasumi.setNombre(nombres);
            List listaPacientes = this.mi.getListarPacientes(creasumi);
            modelo.put("listaPacientes", listaPacientes);

            return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
        }

        if ("Reservar".equals(accion) && "ZZZ".equals(accionsa) && ("".equals(accion1))) {
            Date reserv = new Date();
            int id_consultoriox = Integer.parseInt(request.getParameter("id_consultorio"));
            modelo.put("id_consultorio", Integer.toString(id_consultoriox));

            //Consultorios datosconsulta = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)) ; // saca un registro a ser modificado
            if (request.getParameter("id_consultorio") != null) {
                Personas persona = new Personas();
                persona.setId_consultorio(id_consultoriox);
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
            }

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listaPacAfi", listarSeguros);

            modelo.put("accion", accion);
            modelo.put("accionsa", accionsa);
            modelo.put("area", datoestab.getArea());
            Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);

            //modelo.put("listarCargos", listarCargos); 
            modelo.put("id_paciente", id_paciente);

            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            String a = "/";
            String fecha_nacimiento = xdia + a + xmes + a + xanio;
            modelo.put("fec_nacimiento", fecha_nacimiento);

            return new ModelAndView("administrarpacientes/ConfirmarPaciente", modelo);
        }

        if ("Riesgos Extraordinarios".equals(accion)) {

            String id_historial = request.getParameter("id_reservacion");
            String id_persona1 = request.getParameter("id_persona1");
            String id_persona2 = request.getParameter("id_persona2");
            String id_persona3 = request.getParameter("id_persona3");
            String id_persona4 = request.getParameter("id_persona4");
            String tipo_medico = request.getParameter("tipo_medico");

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_historial", id_historial);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona1", id_persona1);
            modelo.put("id_persona2", id_persona2);
            modelo.put("id_persona3", id_persona3);
            modelo.put("id_persona4", id_persona4);
            modelo.put("expedido", expedido);

            return new ModelAndView("administrarpacientes/VigenciaRiesgos", modelo);
        }

        if ("Reservar".equals(accion) && "Aceptar".equals(accion1)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String id_vigencia = request.getParameter("id_vigencia");
            String numf = "0";

            if ("".equals(anioi) || anioi == null) {////28/04/2015
                anioi = Integer.toString(Fecha1.getYear() + 1900);
                mesi = Integer.toString(Fecha1.getMonth() + 1);
                diai = Integer.toString(Fecha1.getDate());
                horai = Integer.toString(Fecha1.getHours());
                minutoi = Integer.toString(Fecha1.getMinutes());
            }
            if (("".equals(id_persona)) || id_persona == null) {
                id_persona = "0";
            }
            if (("".equals(nropac)) || nropac == null) {
                nropac = "0";
            }
            if (("".equals(id_consultorio)) || id_consultorio == null) {
                id_consultorio = "0";
            }
            if (("".equals(id_vigencia)) || id_vigencia == null) {
                id_vigencia = "0";
            }
            if ("1".equals(id_consultorio) && datoestab.getCod_esta() == 200010) {
                id_vigencia = "2";
                id_consultorio = "0";
            }
            
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado

            Date reserv = new Date();
            if ("".equals(request.getParameter("id_paciente"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            Pacientes paciente = new Pacientes();
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            if ("64".equals(id_consultorio) && datoestab.getCod_esta() == 200010) {////para asignar medico familiar para las fichas
                paciente.setId_persona(Integer.parseInt(id_persona));
                this.mi.setModificarPacienteConsul(paciente);
            }
            paciente.setCod_esta(cliente.getCod_esta());
            List listI = this.mi.getDatosPacienteInt(paciente);
            if (!listI.isEmpty()) {
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setAccion("F");   ////setModificarInterDatHisto  si esta internado en otro hospital lo da de alta automaticamente
                this.mi.setModificarInterDatHisto(reserva);
            }
            List listI2 = this.mi.getDatosPacienteInt(paciente);
            //lista de consultorios
            if ("0".equals(request.getParameter("id_consultorio")) && datoestab.getCod_esta() != 200010) {
                return new ModelAndView("Aviso", "mensaje", "Debe elegir un consultorio");
            }

            if (!listI2.isEmpty() && cliente.getId_cargo() != 10 && cliente.getId_cargo() != 1 && cliente.getId_cargo() != 12 && cliente.getId_cargo() != 24) {
                return new ModelAndView("Aviso", "mensaje", "Este Paciente esta Internado, el Medico debe buscar en Pacientes internados");
            } else {
                Pacientes bPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setId_consultorio(Integer.parseInt(id_consultorio));
                reserva.setEdad(bPaciente.getEdad());
                if ("S".equals(bPaciente.getId_estado())) {
                    reserva.setExpedido("S");
                    reserva.setId_estado("C");////cancelado
                } else if ("P".equals(bPaciente.getId_estado())) {
                    reserva.setExpedido("P");
                    reserva.setId_estado("C");////cancelado
                } else {
                    reserva.setExpedido("E");
                    reserva.setId_estado("P");////por pagar, para externos
                }

                if ("1".equals(tipoconsult) || "2".equals(tipoconsult) || "3".equals(tipoconsult)) /////las interconsultas
                {
                    reserva.setId_estado("C");////cancelado
                }
                reserva.setId_riesgo(Integer.parseInt(id_riesgo));

                if ((id_persona == null || "0".equals(id_persona) || id_persona == null) && datoestab.getCod_esta() != 200010) {
                    return new ModelAndView("Aviso", "mensaje", "Debe elegir un Medico");
                }
                //if(cliente.getId_rol()==22){    
                //     id_consultorio="0";
                //}
                reserva.setUlt_usuario(Integer.parseInt(id_persona));
                reserva.setId_persona(0);
                reserva.setId_cargo(Integer.parseInt(id_vigencia));
                if (cliente.getId_rol2() == 2) {
                    reserva.setId_cargo(2);
                }
                reserva.setCod_esta(cliente.getCod_esta());
                Personas persona = new Personas();
                persona.setId_persona(Integer.parseInt(id_persona));
                persona.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarPersonaFic(persona); ///modifica tabla personas la ficha al dia de hoy   

                Personas buscarEmp = this.mi.getDatosPersona(Integer.parseInt(id_persona));
                //Historiales datof=new Historiales(); 
                //datof.setId_consultorio(Integer.parseInt(id_consultorio));
                //datof.setId_persona(Integer.parseInt(id_persona));
                //datof.setCod_esta(cliente.getCod_esta());
                //datof.setAccion("P");
                //List listarResvCoun = this.mi.getListarReservacionesCount(datof);  ///getListarReserFichas
                local.setArea("U");  ////getListarEstaUsua
                local.setCod_esta(cliente.getCod_esta());
                List Estab2 = this.mi.getListarEstaUsua(local);
                Localidades datoestab2 = (Localidades) Estab2.get(0);

                //if((datoestab.getFecha2().getYear()+ 1900==fecha1.getYear()+ 1900) && (datoestab.getFecha2().getMonth()+ 1==fecha1.getMonth()+ 1) && datoestab.getFecha2().getDate()==fecha1.getDate() ){
                if (buscarEmp != null) {
                    if (1 + buscarEmp.getSuma2() < 10) {
                        numf = "0" + (1 + buscarEmp.getSuma2());
                    }
                    if (1 + buscarEmp.getSuma2() > 9) {
                        numf = Integer.toString(1 + buscarEmp.getSuma2());
                    }
                } else {
                    numf = "01";
                }
                //} 
                //////////EMPIEZA LA MODIFICACION PARA HG SOBRE ESTADISTICA Y MORBILIDAD 

                if (diai != null) {
                    int diax = Integer.parseInt(diai);
                    int mesx = Integer.parseInt(mesi) - 1;
                    int aniox = Integer.parseInt(anioi) - 1900;
                    int horax = Integer.parseInt(horai);
                    int minutox = Integer.parseInt(minutoi);
                    reserv = new Date(aniox, mesx, diax, horax, minutox, 00);
                    reserva.setFecha(reserv);
                    reserva.setId_historia(cliente.getId_usuario());
                    reserva.setId_seguro(buscarPaciente.getId_seguro());
                    reserva.setId_riesgo(Integer.parseInt(id_riesgo));
                    reserva.setTipoconsult(Integer.parseInt(tipoconsult));
                    reserva.setId_riesgo(Integer.parseInt(id_riesgo));
                    reserva.setCod_esta(cliente.getCod_esta());
                    reserva.setId_por(cliente.getId_persona());
                    try {////29-10-2017 error java.lang.String
                        reserva.setDial(Integer.parseInt(nropac));
                    } catch (Exception e) {
                        reserva.setDial(0);
                    }

                    reserva.setCadena2(buscarEmp.getCadena2());
                    reserva.setCadena3(numf + " - " + buscarEmp.getCadena2());
                } else {
                    reserva.setFecha(fecha1);
                }
                if ("F".equals(datoestab.getArea()) && cliente.getId_consultorio() == 14) {/////para HG ya no jala
                    String tipo = request.getParameter("tipo");
                    reserva.setUlt_usuario(cliente.getId_usuario());///crea una reserva en tabla reservaciones
                    reserva.setId_persona(0);
                    reserva.setId_por(cliente.getId_persona());
                    this.mi.setCrearReservacion(reserva);

                    //Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)) ; // saca un registro a ser modificado
                    reserva.setId_estado("%");
                    reserva.setAccion("Z");////creado 04/04/2014
                    List listarHG = this.mi.getListarCobroReservaUni(reserva);
                    Historiales listaHG = (Historiales) listarHG.get(listarHG.size() - 1);

                    Historiales dato = new Historiales();
                    dato.setId_historial(listaHG.getId_reservacion());
                    dato.setId_persona(Integer.parseInt(id_persona));
                    dato.setId_paciente(listaHG.getId_paciente());
                    dato.setEdad(listaHG.getEdad());
                    dato.setId_consultorio(listaHG.getId_consultorio());
                    dato.setTalla(0);
                    dato.setPeso(0);
                    dato.setEstimc("0");
                    dato.setTemperatura(0);
                    dato.setExpedido(listaHG.getExpedido());
                    dato.setFc("0");
                    dato.setPa("0");
                    dato.setFr("0");
                    dato.setCodigo("");
                    dato.setRepetida(tipo);
                    dato.setSubjetivo("Estadistica Morbilidad");
                    dato.setDiagnostico("Estadistica Morbilidad");
                    dato.setObjetivo("");
                    dato.setId_seguro(buscarPaciente.getId_seguro());
                    dato.setCod_esta(cliente.getCod_esta());
                    dato.setId_por(cliente.getId_persona());
                    dato.setSo2("0");
                    dato.setGlicemia("0");
                    dato.setFechalab(listaHG.getFecha());
                    if (listaHG.getFecha().getTime() > listaHG.getFechalab().getTime()) {
                        dato.setFechalab(listaHG.getFechalab());
                    }
                    int iResultado = this.mi.setRegistrarHistorial(dato);
                    //crea el historial nuevo en la tabla y elimina el de reservaciones
                    Historiales elimina = new Historiales();
                    elimina.setId_reservacion(listaHG.getId_reservacion());
                    elimina.setCod_esta(cliente.getCod_esta());
                    this.mi.setEliminarReserva(elimina);
                    modelo.put("id_persona", Integer.toString(listaHG.getId_persona()));
                    modelo.put("id_paciente", Integer.toString(listaHG.getId_paciente()));
                    modelo.put("id_reservacion", Integer.toString(listaHG.getId_reservacion()));
                    modelo.put("id_consultorio", Integer.toString(listaHG.getId_consultorio()));
                    Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
                    modelo.put("datos", buscarPacienteh);
                    Medicamentos medid = new Medicamentos();
                    medid.setCod_esta(cliente.getCod_esta());
                    medid.setId_persona(listaHG.getId_persona());
                    List listarEnfermedadesCot = this.mi.getListarEnfermedadesCot(medid);
                    modelo.put("listarEnfermedadesCot", listarEnfermedadesCot);
                    Historiales datohi = new Historiales();
                    datohi.setId_historial(listaHG.getId_reservacion());
                    datohi.setCod_esta(cliente.getCod_esta());
                    Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

                    return new ModelAndView("administrarhistoriales/AtenderPaciente3", modelo);

                } else {  //////////TERMINA MODIFICACION PARA HG SOBRE ESTADISTICA Y MORBILIDAD 

                    this.mi.setCrearReservacion(reserva);///reserva en tabla reservaciones
                    this.mi.setCrearReservacionFicha(reserva);///crea en la tabla clientes junto con reserva
                    if (cliente.getCod_esta() == 400010 || cliente.getCod_esta() == 400026 || cliente.getCod_esta() == 400029) {
                        //Historiales dato=new Historiales();
                        reserva.setAccion("F"); ////getListarVigenciaFicha
                        List listarFicha = this.mi.getListarVigenciaFicha(reserva);
                        modelo.put("listaficha", listarFicha);
                        return new ModelAndView(new AtencionFichasPDF(), modelo);
                    }
                }
                //}catch (Exception e){
                //     return new ModelAndView("Aviso","mensaje","El registro no se grabo");
                // }
                //lista de consultorios
                Consultorios aa = new Consultorios();
                aa.setCod_esta(cliente.getCod_esta());
                List listarCargos = this.mi.getListarConsultorios(aa);
                modelo.put("listarCargos", listarCargos);

                // Lista de pacientes en consultorio
                Historiales datox = new Historiales();
                datox.setId_persona(cliente.getId_usuario());
                datox.setId_consultorio(Integer.parseInt(id_consultorio));
                datox.setCod_esta(cliente.getCod_esta());
                datox.setId_estado("%");
                datox.setAccion("T");

                if (request.getParameter("id_persona") != null) {
                    datox.setAccion("P");
                    datox.setId_persona(Integer.parseInt(id_persona));
                    modelo.put("id_persona", id_persona);
                    if (id_consultorio != null) {
                        //Personas persona= new Personas();
                        persona.setId_consultorio(Integer.parseInt(id_consultorio));
                        persona.setCod_esta(cliente.getCod_esta());
                        List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                        modelo.put("listaPersonas", buscarEmpleado);
                    }
                    if (cliente.getId_rol2() == 2) {
                        datox.setAccion("F");
                        datox.setFecha_ini(reserv);
                        datox.setFecha_fin(reserv);
                        datox.setId_historia(2);  ////estad =2 habilitados estadistica
                        datox.setId_historial(2);  ////estad =2 habilitados estadistica
                    }
                    List listarPaises = this.mi.getListarReservaciones(datox);
                    modelo.put("milista", listarPaises);
                    if (datoestab.getCod_esta() == 200010 && cliente.getId_rol2() == 22) {
                        datox.setId_historia(0);  ////vigencia =0 no habilitados vigencia
                        datox.setId_historial(0);  ////vigencia =0 no habilitados vigencia
                        List listarVig = this.mi.getListarVigencia(datox);
                        modelo.put("milista", listarVig);
                    }

                } else {
                    List listarPaises = this.mi.getListarReservaciones(datox);
                    modelo.put("milista", listarPaises);
                    if (datoestab.getCod_esta() == 200010) {
                        datox.setId_historia(0);  ////vigencia =0 no habilitados vigencia
                        datox.setId_historial(2);  ////vigencia =0 no habilitados vigencia
                        datox.setAccion("H");      ///getListarVigenciaHab
                        List listarVig = this.mi.getListarVigenciaHab(datox);
                        modelo.put("milista", listarVig);
                    }
                }
                //List listarSeguros = this.mi.getListarSeguros("%");
                //modelo.put("listaPacAfi", listarSeguros);

                modelo.put("id_consultorio", id_consultorio);
                modelo.put("id_persona", id_persona);
                if (datoestab.getCod_esta() == 200010 && cliente.getId_rol2() == 22) {
                    return new ModelAndView("administrarpacientes/ListarVigencias", modelo);
                }
                return new ModelAndView("administrarpacientes/ListarReservasConsul", modelo);
            }
        }

        if ("InterConsulta".equals(accion) && "Aceptar".equals(accion1)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            Date reserv = new Date();
            if ("".equals(request.getParameter("id_paciente"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            if (id_consultorio != null) {
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
            }

            Pacientes paciente = new Pacientes();
            paciente.setId_paciente(Integer.parseInt(id_paciente));
            paciente.setCod_esta(cliente.getCod_esta());
            List listI = this.mi.getDatosPacienteInt(paciente);
            if (!listI.isEmpty()) {
                Historiales reserva = new Historiales();
                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setAccion("F");   ////setModificarInterDatHisto si esta internado en otro hospital lo da de alta automaticamente
                this.mi.setModificarInterDatHisto(reserva);
            }
            List listI2 = this.mi.getDatosPacienteInt(paciente);

            if (!listI2.isEmpty() && cliente.getId_cargo() != 10 && cliente.getId_cargo() != 1 && cliente.getId_cargo() != 12 && cliente.getId_cargo() != 24) {
                return new ModelAndView("Aviso", "mensaje", "Este Paciente esta Internado, el Medico debe buscar en Pacientes internados");
            } else {

                Pacientes bPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
                Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosHist = this.mi.getDatosHistorial(datohi);

                Historiales reserva = new Historiales();

                if (diai != null) {
                    int diax = Integer.parseInt(diai);
                    int mesx = Integer.parseInt(mesi) - 1;
                    int aniox = Integer.parseInt(anioi) - 1900;
                    int horax = Integer.parseInt(horai);
                    int minutox = Integer.parseInt(minutoi);
                    reserv = new Date(aniox, mesx, diax, horax, minutox, 00);
                    reserva.setFecha(reserv);
                    reserva.setId_historia(cliente.getId_usuario());

                    reserva.setCod_esta(cliente.getCod_esta());
                } else {
                    reserva.setFecha(fecha1);
                }

                reserva.setId_paciente(Integer.parseInt(id_paciente));
                reserva.setId_consultorio(Integer.parseInt(id_consultorio));
                reserva.setEdad(bPaciente.getEdad());
                reserva.setId_persona(cliente.getId_persona());
                if (id_persona == null || "0".equals(id_persona)) {
                    id_persona = Integer.toString(cliente.getId_persona());
                }
                reserva.setUlt_usuario(Integer.parseInt(id_persona));
                reserva.setId_estado("C");
                reserva.setCod_esta(cliente.getCod_esta());
                reserva.setId_seguro(datosHist.getId_seguro());
                reserva.setId_historial(Integer.parseInt(id_reservacion));
                reserva.setId_riesgo(Integer.parseInt(id_riesgo));
                if (cliente.getId_especialidad() > 0 && cliente.getId_cargo() > 10) {
                    reserva.setTipoconsult(6);//////////reculsulta por medico
                }
                if (cliente.getId_especialidad() == 0 && cliente.getId_cargo() < 15) {
                    reserva.setTipoconsult(7); //////////reculsulta por enfermeria
                }

                if ("A".equals(bPaciente.getId_estado())) {
                    reserva.setExpedido("E");
                } else {
                    reserva.setExpedido(bPaciente.getId_estado());
                }

                try {
                    this.mi.setCrearReservacion(reserva);

                    reserva.setId_historia(datosHist.getId_consultorio());
                    reserva.setAccion("I");///para que se ejecute setCrearInterconsulta
                    this.mi.setCrearInterconsulta(reserva);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "El registro de Interconsulta o evaluacion no se grabo");
                }
                modelo.put("id_consultorio", request.getParameter("id_consultorio"));
                return new ModelAndView("Aviso", "mensaje", "La Interconsulta se realizo Satisfactoriamente");
            }
        }

        if ("Cambiar".equals(accion) && "Aceptar".equals(accion1)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            Date reserv = new Date();

            if ("".equals(request.getParameter("id_paciente"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Pacientes bPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            Historiales reserva = new Historiales();
            reserva.setId_historial(Integer.parseInt(id_reservacion));
            reserva.setId_paciente(Integer.parseInt(id_paciente));
            reserva.setCod_esta(cliente.getCod_esta());
            reserva.setAccion("R");
            Historiales datosReserva = this.mi.getDatosReserva(reserva); //getDatosReserva 
            if (id_persona != null) {
                reserva.setId_persona(Integer.parseInt(id_persona));
            } else {
                Historiales datohi = new Historiales();
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
                reserva.setId_persona(datosHistorial.getId_persona());
            }
            if (diai != null) {
                int diax = Integer.parseInt(diai);
                int mesx = Integer.parseInt(mesi) - 1;
                int aniox = Integer.parseInt(anioi) - 1900;
                int horax = Integer.parseInt(horai);
                int minutox = Integer.parseInt(minutoi);
                reserv = new Date(aniox, mesx, diax, horax, minutox, 00);
                reserva.setFecha(reserv);
                reserva.setCod_esta(cliente.getCod_esta());
            } else {
                reserva.setFecha(fecha1);
            }
            reserva.setId_reservacion(Integer.parseInt(id_reservacion));
            reserva.setId_consultorio(Integer.parseInt(id_consultorio));
            reserva.setTipoconsult(Integer.parseInt(tipoconsult));
            reserva.setId_tipo(Integer.parseInt(id_triaje));
            reserva.setAccion(ip);
            reserva.setCodigo(host);
            reserva.setExpedido("M");
            reserva.setId_riesgo(Integer.parseInt(id_riesgo));
            try {
                if ("1".equals(resvig)) {
                    reserva.setId_historia(cliente.getId_persona());
                    reserva.setAccion("V");////setModificarReservaVigencia
                }
                if (datosReserva == null) {//////modifica Historial
                    reserva.setId_cargo(Integer.parseInt(resvig));
                    reserva.setId_reservacion(Integer.parseInt(id_reservacion));
                    this.mi.setModificaVigencia(reserva);
                } else {
                    this.mi.setModificarReservaConsultorio(reserva);
                }

            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro cambio paciente no se grabo");
            }

            //lista de consultorios
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);
            modelo.put("listarCargos", listarCargos);

            // Lista de pacientes en consultorio
            Historiales datox = new Historiales();
            datox.setId_consultorio(reserva.getId_consultorio());
            datox.setId_persona(cliente.getId_usuario());
            datox.setId_estado("%");
            List listarPaises = this.mi.getListarReservacionesConsul2(datox);
            modelo.put("milista", listarPaises);
            modelo.put("id_consultorio", id_consultorio);
            return new ModelAndView("administrarpacientes/ListarReservasConsul", modelo);
        }

        if ("EliminarReserva".equals(accion) && "Aceptar".equals(accion1)) {

            Historiales reserva = new Historiales();
            reserva.setId_reservacion(Integer.parseInt(id_reservacion));
            reserva.setAccion(ip);///20/07/2014
            reserva.setCodigo(host);///20/07/2014
            reserva.setExpedido("E");///20/07/2014
            reserva.setId_persona(cliente.getId_persona());///20/07/2014
            reserva.setCod_esta(cliente.getCod_esta());

            try {
                this.mi.setEliminarReserva(reserva);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro no se ejecuto");
            }

            //lista de consultorios
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);

            modelo.put("listarCargos", listarCargos);

            // Lista de pacientes en consultorio
            Historiales datox = new Historiales();
            datox.setId_consultorio(Integer.parseInt(id_consultorio));
            datox.setId_estado("%");
            datox.setAccion("S");
            if (request.getParameter("id_persona") != null) {
                datox.setAccion("P");
                datox.setId_persona(Integer.parseInt(id_persona));
                List listarPaises = this.mi.getListarReservaciones(datox);
                modelo.put("milista", listarPaises);
            } else {
                List listarPaises = this.mi.getListarReservaciones(datox);
                modelo.put("milista", listarPaises);
            }
            if (id_consultorio != null) {
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
            }
            modelo.put("id_consultorio", request.getParameter("id_consultorio"));
            List listarSeguros = this.mi.getListarSeguros("%");
            modelo.put("listaPacAfi", listarSeguros);
            return new ModelAndView("administrarpacientes/ListarReservasConsul", modelo);
        }
        return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
    }
}
