package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoPacienteControlador {

    private final MiFacade mi;

    public NuevoPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String hcl = request.getParameter("hcl");
        String id_paciente = request.getParameter("id_paciente");
        String direccion = request.getParameter("direccion");
        String residencia = request.getParameter("resid");
        String patronal = request.getParameter("patronal");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String nombre = request.getParameter("nombre");
        String carnet = request.getParameter("carnet");
        String fecnac = request.getParameter("fecnac");
        String sexo = request.getParameter("sexo");
        String nomempresa = request.getParameter("nomempresa");
        String id_ecivil = request.getParameter("id_ecivil");
        String id_escolaridad = request.getParameter("id_escolaridad");
        String lugci = request.getParameter("lugci");
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        String zoom = request.getParameter("zoom");
        String nro_registro = request.getParameter("nro_registro");
        String policonsul = request.getParameter("policonsul");
        String cod = request.getParameter("cod");
        String patronal1 = request.getParameter("patronal1");
        String patronal2 = request.getParameter("patronal2");
        String patronal3 = request.getParameter("patronal3");
        String swci = request.getParameter("swci");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;

        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("codest", Integer.toString(cliente.getCod_esta()));
        local.setArea("U");  ////getListarEstaUsua
        local.setCod_esta(cliente.getCod_esta());
        List Estab3 = this.mi.getListarEstaUsua(local);
        Localidades datoestab3 = (Localidades) Estab3.get(0);

        modelo.put("id_paciente", request.getParameter("id_paciente"));
        modelo.put("swci", swci);

        Consultorios a = new Consultorios();
        a.setId_estado("I");   ///getListarCodCNS
        List listarCod = mi.getListarCodCNS(a);
        modelo.put("listarCod", listarCod);

        if ("".equals(cod) || cod == null) {
            cod = "ID";
            modelo.put("cod", cod);
        }

        //Listar Sexos 
        List listarSexos = this.mi.getListarSexos();
        modelo.put("listaSexos", listarSexos);
        //Listar Sexos 
        List listarDocumentos = this.mi.getListarDocumentos();
        modelo.put("listaDocumentos", listarDocumentos);
        //Listar Estado Civil
        List listarEciviles = this.mi.getListarEciviles();
        modelo.put("listaCivil", listarEciviles);
        //Listar Escolaridad
        List listarEscolaridad = this.mi.getListarEscolaridades();
        modelo.put("listaEscolaridad", listarEscolaridad);
        //Listar Paises
        List listarPaises = this.mi.getListarPaises();
        modelo.put("listaPaises", listarPaises);
        //Cuando de eligio un pais
        if (request.getParameter("id_pais") != null) {
            int id_paisx = Integer.parseInt(request.getParameter("id_pais"));
            modelo.put("id_pais", Integer.toString(id_paisx));

            List listaDepartamentos = this.mi.getListarPaisDep(id_paisx);
            modelo.put("listaDepartamentos", listaDepartamentos);

            // recuperar datos y reenviarlos
            if (hcl == null) {
                hcl = "0";
            }
            if (nro_registro == null) {
                nro_registro = "0";
            }
            if ("".equals(policonsul) || policonsul == null) {
                policonsul = "0";
            }
            if ("".equals(id_ecivil) || id_ecivil == null) {
                id_ecivil = "1";
            }
            if ("".equals(id_escolaridad) || id_escolaridad == null) {
                id_escolaridad = "1";
            }
            Pacientes buscarPaciente = new Pacientes(); // saca un registro a ser modificado
            buscarPaciente.setId_estado(request.getParameter("id_estado"));
            buscarPaciente.setHcl(Integer.parseInt(hcl));
            buscarPaciente.setPaterno(paterno);
            buscarPaciente.setMaterno(materno);
            buscarPaciente.setNombre(nombre);
            buscarPaciente.setId_tipo_sexo(Integer.parseInt(request.getParameter("id_sexo")));
            buscarPaciente.setId_tipo_documento(Integer.parseInt(request.getParameter("id_documento")));
            buscarPaciente.setDireccion(request.getParameter("direccion"));
            buscarPaciente.setCadena(request.getParameter("patronal"));
            buscarPaciente.setCadena2(request.getParameter("nomempresa"));
            buscarPaciente.setNro_registro(nro_registro);
            buscarPaciente.setId_policlinico(Integer.parseInt(policonsul));
            buscarPaciente.setNro(request.getParameter("cod"));
            buscarPaciente.setExpedido(request.getParameter("lugci"));
            buscarPaciente.setResidencia(Integer.parseInt(residencia));
            buscarPaciente.setTelefono(request.getParameter("telefono"));
            buscarPaciente.setOcupacion(request.getParameter("ocupacion"));
            buscarPaciente.setCarnet(request.getParameter("carnet"));
            buscarPaciente.setLatitud(datoestab.getLatitud());
            buscarPaciente.setLongitud(datoestab.getLongitud());
            buscarPaciente.setZoom("15");
            buscarPaciente.setId_ecivil(Integer.parseInt(id_ecivil));
            buscarPaciente.setId_escolaridad(Integer.parseInt(id_escolaridad));

            modelo.put("buscarPaciente", buscarPaciente);
            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("accion", accion);
            modelo.put("patronal", patronal);
            modelo.put("nomempresa", nomempresa);
            modelo.put("nro_registro", nro_registro);
            modelo.put("policonsul", policonsul);
            modelo.put("patronal1", patronal1);
            modelo.put("patronal2", patronal2);
            modelo.put("patronal3", patronal3);
            modelo.put("latitud", latitud);
            modelo.put("longitud", longitud);
            modelo.put("zoom", zoom);
            modelo.put("cod", cod);
            modelo.put("lugci", lugci);
            modelo.put("direccion", direccion);

        }
        //Cuando se eligio n departamento
        if (request.getParameter("id_departamento") != null) {
            int id_pais1 = Integer.parseInt(request.getParameter("id_pais"));
            int id_depto = Integer.parseInt(request.getParameter("id_departamento"));
            modelo.put("id_departamento", Integer.toString(id_depto));

            Provincias provincia = new Provincias();
            provincia.setId_pais(id_pais1);
            provincia.setId_departamento(id_depto);
            List listaProvincias = this.mi.getListarPaisDepProv(provincia);
            modelo.put("listaProvincias", listaProvincias);
            modelo.put("accion", accion);
        }

        //Cuando se elige una provincia
        if (request.getParameter("id_provincia") != null) {
            int id_pais1 = Integer.parseInt(request.getParameter("id_pais"));
            int id_departamento1 = Integer.parseInt(request.getParameter("id_departamento"));
            int id_prov = Integer.parseInt(request.getParameter("id_provincia"));
            modelo.put("id_provincia", Integer.toString(id_prov));

            Localidades localidad = new Localidades();
            localidad.setId_pais(id_pais1);
            localidad.setId_departamento(id_departamento1);
            localidad.setId_provincia(id_prov);
            List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
            modelo.put("listaLocalidad", listaLocalidad);
            modelo.put("accion", accion);
        }

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_paciente") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {

            if ("1".equals(swci)) {
                Pacientes buscarPaciente2 = this.mi.getDatosPacienteCI(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
                modelo.put("buscarPaciente", buscarPaciente2);

                List listaDepartamentos = this.mi.getListarPaisDep(1);
                modelo.put("listaDepartamentos", listaDepartamentos);

                Provincias provincia = new Provincias();
                provincia.setId_pais(1);
                provincia.setId_departamento(datoestab.getId_departamento());
                List listaProvincias = this.mi.getListarPaisDepProv(provincia);
                modelo.put("listaProvincias", listaProvincias);

                Localidades localidad = new Localidades();
                localidad.setId_pais(1);
                localidad.setId_departamento(datoestab.getId_departamento());
                localidad.setId_provincia(datoestab.getId_provincia());
                List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
                modelo.put("listaLocalidad", listaLocalidad);

                modelo.put("id_pais", Integer.toString(1));
                modelo.put("id_departamento", Integer.toString(datoestab.getId_departamento()));
                modelo.put("id_provincia", Integer.toString(datoestab.getId_provincia()));
                modelo.put("id_localidad", Integer.toString(datoestab.getId_localidad()));
                modelo.put("anios", anios);
                modelo.put("meses", meses);
                modelo.put("dias", dias);
                modelo.put("swci", swci);
                modelo.put("accion", accion);
                modelo.put("id_estado", buscarPaciente2.getId_estado());
                modelo.put("sw", request.getParameter("sw"));
                modelo.put("anio", Integer.toString(buscarPaciente2.getFec_nacimiento().getYear() + 1900));
                modelo.put("mes", Integer.toString(buscarPaciente2.getFec_nacimiento().getMonth() + 1));
                modelo.put("dia", Integer.toString(buscarPaciente2.getFec_nacimiento().getDate()));
                fecha1 = new Date();

            } else {

                Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
                modelo.put("buscarPaciente", buscarPaciente);
                modelo.put("cod", buscarPaciente.getNro());
                //Usuarios usuario = this.mi.getDatosUsuario(buscarPaciente.getUlt_usuario()) ; 
                Personas buscarEmpleado = this.mi.getDatosPersona(buscarPaciente.getUlt_usuario());
                modelo.put("buscarEmpleado", buscarEmpleado);

                if (datoestab.getCod_esta() == 200010) {
                    Empresas buscarempresa = this.mi.getDatosEmpresa(buscarPaciente.getId_empresa());
                    if (buscarempresa != null) {
                        patronal = Long.toString(buscarempresa.getCod_patronal());
                        String patrona = "0000000000".substring(patronal.length()) + patronal;

                        modelo.put("patronal1", patrona.substring(0, 2));
                        modelo.put("patronal2", patrona.substring(2, 5));
                        modelo.put("patronal3", patrona.substring(5, 10));
                        modelo.put("nomempresa", buscarempresa.getEmpresa());
                    }
                }

                List listaDepartamentos = this.mi.getListarPaisDep(buscarPaciente.getId_pais());
                modelo.put("listaDepartamentos", listaDepartamentos);

                Provincias provincia = new Provincias();
                provincia.setId_pais(buscarPaciente.getId_pais());
                provincia.setId_departamento(buscarPaciente.getId_departamento());
                List listaProvincias = this.mi.getListarPaisDepProv(provincia);
                modelo.put("listaProvincias", listaProvincias);

                Localidades localidad = new Localidades();
                localidad.setId_pais(buscarPaciente.getId_pais());
                localidad.setId_departamento(buscarPaciente.getId_departamento());
                localidad.setId_provincia(buscarPaciente.getId_provincia());
                List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
                modelo.put("listaLocalidad", listaLocalidad);

                modelo.put("id_pais", Integer.toString(buscarPaciente.getId_pais()));
                modelo.put("id_departamento", Integer.toString(buscarPaciente.getId_departamento()));
                modelo.put("id_provincia", Integer.toString(buscarPaciente.getId_provincia()));
                modelo.put("id_localidad", Integer.toString(buscarPaciente.getId_localidad()));
                modelo.put("anios", anios);
                modelo.put("meses", meses);
                modelo.put("dias", dias);

                modelo.put("id_estado", buscarPaciente.getId_estado());
                modelo.put("sw", request.getParameter("sw"));
                modelo.put("accion", accion);
                modelo.put("anio", Integer.toString(buscarPaciente.getFec_nacimiento().getYear() + 1900));
                modelo.put("mes", Integer.toString(buscarPaciente.getFec_nacimiento().getMonth() + 1));
                modelo.put("dia", Integer.toString(buscarPaciente.getFec_nacimiento().getDate()));
                fecha1 = buscarPaciente.getFec_registro();
            }
        } else {

            if ((request.getParameter("recargado") == null)) {
                Pacientes buscarPaciente = new Pacientes();
                buscarPaciente.setHcl(0);

                List listaDepartamentos = this.mi.getListarPaisDep(1);
                modelo.put("listaDepartamentos", listaDepartamentos);

                for (int i = 0; i < listaDepartamentos.size(); i++) {
                    Departamentos deptos = (Departamentos) listaDepartamentos.get(i);
                    if (datoestab3.getId_departamento() == deptos.getId_departamento()) {
                        modelo.put("siglaci", deptos.getSigla());
                    }
                }

                Provincias provincia = new Provincias();
                provincia.setId_pais(1);
                provincia.setId_departamento(datoestab3.getId_departamento());
                List listaProvincias = this.mi.getListarPaisDepProv(provincia);
                modelo.put("listaProvincias", listaProvincias);

                Localidades localidad = new Localidades();
                localidad.setId_pais(1);
                localidad.setId_departamento(datoestab3.getId_departamento());
                localidad.setId_provincia(Integer.parseInt(Integer.toString(datoestab3.getId_localidad()).substring(0, 3)));
                List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
                modelo.put("listaLocalidad", listaLocalidad);

                modelo.put("id_pais", Integer.toString(1));
                modelo.put("id_departamento", Integer.toString(datoestab3.getId_departamento()));
                modelo.put("id_provincia", Integer.toString(datoestab3.getId_localidad()).substring(0, 3));
                modelo.put("id_localidad", Integer.toString(datoestab3.getId_localidad()));
                modelo.put("anios", anios);
                modelo.put("meses", meses);
                modelo.put("dias", dias);
                modelo.put("telefono", "0");
                modelo.put("dias", dias);
                modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
                modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
                modelo.put("dia", Integer.toString(fecha1.getDate()));
                buscarPaciente.setCarnet("0");
                buscarPaciente.setId_ecivil(1);
                buscarPaciente.setId_tipo_sexo(1);
                buscarPaciente.setId_escolaridad(2);
                if ("1".equals(swci)) {
                    modelo.put("dia", Integer.toString(Integer.parseInt(fecnac.substring(0, 2))));
                    modelo.put("mes", Integer.toString(Integer.parseInt(fecnac.substring(3, 5))));
                    modelo.put("anio", fecnac.substring(6, 10));
                    buscarPaciente.setId_tipo_sexo(Integer.parseInt(sexo));
                    buscarPaciente.setNombre(nombre);
                    buscarPaciente.setPaterno(paterno);
                    buscarPaciente.setMaterno(materno);
                    buscarPaciente.setDireccion(direccion);
                    buscarPaciente.setCarnet(carnet);
                    buscarPaciente.setId_tipo_documento(1);
                }

                buscarPaciente.setNro(cod);
                buscarPaciente.setLatitud(datoestab3.getLatitud());
                buscarPaciente.setLongitud(datoestab3.getLongitud());
                buscarPaciente.setZoom("15");
                buscarPaciente.setId_policlinico(0);
                modelo.put("buscarPaciente", buscarPaciente);
                modelo.put("accion", accion);
            }
        }

        modelo.put("anio_r", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes_r", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia_r", Integer.toString(fecha1.getDate()));

        return new ModelAndView("administrarpacientes/NuevoPaciente", modelo);
    }
}
