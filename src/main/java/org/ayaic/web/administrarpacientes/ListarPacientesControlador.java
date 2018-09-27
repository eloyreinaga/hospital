package org.ayaic.web.administrarpacientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListarPacientesControlador {

    private final MiFacade mi;

    public ListarPacientesControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacientes.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String boton2 = request.getParameter("boton2");
        String accion = request.getParameter("accion");
        String nombre = request.getParameter("nombre");
        String sw1 = request.getParameter("sw1");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0); ////el que esta en id_estado='A' para saber de que entidad es
        modelo.put("estab", Integer.toString(datoestab.getCod_esta()));
        modelo.put("tipoestab", cliente.getArea());
        modelo.put("idrol2", Integer.toString(cliente.getId_rol2()));
        modelo.put("dato", cliente);

        if ("ListarHistoriales".equals(accion)) {
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");
            String horai = request.getParameter("horai");
            String minutoi = request.getParameter("minutoi");
            String diaf = request.getParameter("diaf");
            String mesf = request.getParameter("mesf");
            String aniof = request.getParameter("aniof");
            String horaf = request.getParameter("horaf");
            String minutof = request.getParameter("minutof");
            if (("".equals(diai)) || ("".equals(mesi)) || ("".equals(anioi))) {
                return new ModelAndView("administrarcuadernos/ReporteCIE10", modelo);
            } else {
                int iAnio1 = Integer.parseInt(anioi) - 1900;
                int iMes1 = Integer.parseInt(mesi) - 1;
                int iDia1 = Integer.parseInt(diai);
                int iHor1 = Integer.parseInt(horai);
                int iMin1 = Integer.parseInt(minutoi);

                int iAnio2 = Integer.parseInt(aniof) - 1900;
                int iMes2 = Integer.parseInt(mesf) - 1;
                int iDia2 = Integer.parseInt(diaf);
                int iHor2 = Integer.parseInt(horaf);
                int iMin2 = Integer.parseInt(minutof);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1, iHor1, iMin1, 00);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Pacientes paciente = new Pacientes();
                paciente.setFecha_ini(dFechaini1);
                paciente.setFecha_fin(dFechafin1);
                paciente.setTipo("H");   ////getListarPacientesHisto
                List listaDePacientes = this.mi.getListarPacientesHisto(paciente);
                modelo.put("listaPacientes", listaDePacientes);

                Consultorios datosconsulto = this.mi.getDatosConsultorio(cliente.getId_consultorio());
                modelo.put("especialidad", Integer.toString(datosconsulto.getId_especialidad()));

                return new ModelAndView("administrarcuadernos/ListarPacientesHisto", modelo);
            }
        }

        if ("imprimir".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String fechaped = request.getParameter("fechaped");
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());
            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);
            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            //modelo.put("id_historial", id_historial);
            //modelo.put("id_pedido", id_pedido);
            //modelo.put("expedido", expedido);
            modelo.put("id_paciente", id_paciente);
            return new ModelAndView("administrarpacientes/AdmImpresion", modelo);
        }

        if ("Imprimir".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");

            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            Pacientes datojef = mi.getDatosPacienteJefe(datodep.getId_carpeta());
            modelo.put("datojef", datojef);
            Provincias buscarProvincia = this.mi.getDatosProvincia(datodep.getId_provincia());
            modelo.put("buscarProvincia", buscarProvincia);
            Localidades localidad = new Localidades();
            localidad.setId_localidad(datodep.getId_localidad());
            Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
            modelo.put("buscarLocalidad", buscarLocalidad);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(11);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionGralHcl(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarHclPDF(), modelo);
        }

        if ("ImprimirPeri".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(12);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionGralPerinatal(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarPerinatalPDF(), modelo);
        }

        if ("ImprimirCarnetInf".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(13);  ///para orden de compra tabla conf_impresion
            List datosgral = this.mi.getConfigurarImpresionCarnetInf(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarCarnetInfPDF(), modelo);
        }

        if ("ImprimirGine".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(14);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionGinecologica(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarGinecologicaPDF(), modelo);
        }

        if ("ImprimirForm".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(15);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionFormulario(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarFormularioPDF(), modelo);
        }

        if ("ImprimirCarnet".equals(accion) && "1".equals(sw1)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("O");
            dato.setTipo_egreso(16);  ///para orden de compra
            List datosgral = this.mi.getConfigurarImpresionCarnet(dato);
            modelo.put("datosgral", datosgral);
            return new ModelAndView(new ListarCarnetPDF(), modelo);
        }

        if ("unirHCL".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            Date fecha_nac = buscarPaciente.getFec_nacimiento();
            int anio = fecha_nac.getYear() + 1900;
            int mes = fecha_nac.getMonth() + 1;
            int dia = fecha_nac.getDate();
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("direccion", buscarPaciente.getDireccion());
            modelo.put("sexo", Integer.toString(buscarPaciente.getId_tipo_sexo()));
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            modelo.put("id_paciente", id_paciente);
            String a = "/";
            String fecha_nacimiento = Integer.toString(dia) + a + Integer.toString(mes) + a + Integer.toString(anio);
            modelo.put("fec_nacimiento", fecha_nacimiento);
            return new ModelAndView("administrarpacientes/UnirHCL", modelo);
        }

        if ("CambiarHCL".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String hcl = request.getParameter("hclb");
            Pacientes elimina = new Pacientes();
            elimina.setId_paciente(Integer.parseInt(request.getParameter("id_paciente")));
            elimina.setHcl(Integer.parseInt(hcl));
            try {
                this.mi.setUnirHCL(elimina);
                return new ModelAndView("Aviso", "mensaje", "La historia Clinica se Elimino correctamente");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }
        }

        if ("biometrico".equals(accion)) {
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            List listarPac = this.mi.getListarPacientesBio(paciente);
            modelo.put("listaPacientes", listarPac);
        }

        if ("BuscarN".equals(boton2) || "BuscarCie10".equals(boton2)) {
            String nombres = request.getParameter("nombre");
            String cie10 = request.getParameter("hcl");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("�", "Ñ");
            nombre = nombres.replaceAll("\\s", "%");
            nombre = "%" + nombre.toUpperCase() + "%";
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            cie10 = cie10.trim() + "%";

            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setNombre(nombre);
            paciente.setTipo("N");  ///getListarPacientesHistoNombre
            List listarPaises = this.mi.getListarPacientesHistoNombre(paciente);
            modelo.put("listaPacientes", listarPaises);

            paciente.setId_estado(request.getParameter("id_estado"));
            paciente.setCod_esta(cliente.getCod_esta());
            if (datoestab.getCompartehcl() == 1) {  //////1 comparte la historia clinica
                paciente.setTipo("C");  ///getListarPacientesNombreCns
                List listarPaises3 = this.mi.getListarPacientesNombreCns(paciente);
                modelo.put("listaPacientes", listarPaises3);
            }
            if ("BuscarCie10".equals(boton2)) {
                paciente.setTipo("M");  ///getListarPacientesHistoCie10
                paciente.setNombres(cie10);
                List listarPaises2 = this.mi.getListarPacientesHistoCie10(paciente);
                modelo.put("listaPacientes", listarPaises2);
            }

            return new ModelAndView("administrarcuadernos/ListarPacientesHisto", modelo);
        }

        if ("BuscarN".equals(boton)) {
            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("�", "Ñ");
            nombre = nombres.replaceAll("\\s", "%");
            nombre = "%" + nombre.toUpperCase() + "%";
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }

            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setNombre(nombre);
            paciente.setId_estado(request.getParameter("id_estado"));
            paciente.setCod_esta(cliente.getCod_esta());
            List listarPaises = this.mi.getListarPacientes(paciente);
            modelo.put("listaPacientes", listarPaises);
            if (datoestab.getCompartehcl() == 1) {  //////1 comparte la historia clinica
                paciente.setTipo("C");  ///getListarPacientesNombreCns
                List listarPaises2 = this.mi.getListarPacientesNombreCns(paciente);
                modelo.put("listaPacientes", listarPaises2);
            }
            if ("P".equals(cliente.getArea())) {
                paciente.setTipo("1");  ///getListarPacientesPrivNom
                List listarPaises2 = this.mi.getListarPacientesPrivNom(paciente);
                modelo.put("listaPacientes", listarPaises2);
            }

        }

        if ("BuscarH".equals(boton)) {
            String hcl = request.getParameter("hcl");

            if ("".equals(hcl)) {
                hcl = "0";
            }
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            if (datoestab.getCompartehcl() == 1 && datoestab.getCod_esta() == 200010) {  //////1 comparte la historia clinica
                hcl = hcl.replaceAll("\\s", "%");
                hcl = "%" + hcl + "%";
                paciente.setNombres(hcl);
                paciente.setTipo("H");  ///getListarPacientesNombreCns
                List listarPaises = this.mi.getListarPacientesNombreCns(paciente);
                modelo.put("listaPacientes", listarPaises);
            } else {
                paciente.setHcl(Integer.parseInt(hcl));
                paciente.setTipo("W");
                List listarPaises = this.mi.getListarPacientesHC(paciente);
                System.out.println(listarPaises);
                modelo.put("listaPacientes", listarPaises);
            }
        }

        if ("BuscarH".equals(boton2)) {
            String hcl = request.getParameter("hcl");
            if ("".equals(hcl)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar un numero de Historia");
            }
            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setHcl(Integer.parseInt(hcl));
            paciente.setTipo("H");
            List listarPaises = this.mi.getListarPacientesHCCns(paciente);
            modelo.put("listaPacientes", listarPaises);
            return new ModelAndView("administrarcuadernos/ListarPacientesHisto", modelo);
        }

        if ("C.I.".equals(boton)) {
            String nombres = request.getParameter("hcl");
            if ("".equals(nombres)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar un dato valido devalido");
            }
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }

            Pacientes paciente = new Pacientes();
            paciente.setCod_esta(cliente.getCod_esta());
            paciente.setNombres(nombres);
            try {
                List listarPacAfi = this.mi.getListarPacientesCI(paciente);
                modelo.put("listaPacientes", listarPacAfi);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La Tabla General no esta disponible");
            }

            modelo.put("ci", boton);
        }

        if ("BuscarF".equals(boton)) {
            String aux_dia = request.getParameter("dia");
            String aux_mes = request.getParameter("mes");
            String aux_anio = request.getParameter("anio");
            if ("".equals(aux_dia) || "".equals(aux_mes) || "".equals(aux_anio)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar alguna fecha");
            }
            if ((aux_dia == null) || (aux_mes == null) || (aux_anio == null)) {
                return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
            } else {
                int dia = Integer.parseInt(aux_dia);
                int mes = Integer.parseInt(aux_mes) - 1;
                int anio = Integer.parseInt(aux_anio) - 1900;

                Date fecha = new Date(anio, mes, dia);

                Pacientes paciente = new Pacientes();
                paciente.setCod_esta(cliente.getCod_esta());
                paciente.setFec_nacimiento(fecha);
                List listaDePacientes = this.mi.getListarPacientesFN(paciente);
                modelo.put("listaPacientes", listaDePacientes);
                if (datoestab.getCompartehcl() == 1) {  //////1 comparte la historia clinica
                    paciente.setTipo("C");  ///getListarPacientesFNCns
                    List listaDePacientes2 = this.mi.getListarPacientesFNCns(paciente);
                    modelo.put("listaPacientes", listaDePacientes2);
                }
            }
        }

        if ("BuscarF".equals(boton2)) {
            String aux_dia = request.getParameter("dia");
            String aux_mes = request.getParameter("mes");
            String aux_anio = request.getParameter("anio");
            if ("".equals(aux_dia) || "".equals(aux_mes) || "".equals(aux_anio)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar alguna fecha");
            }
            if ((aux_dia == null) || (aux_mes == null) || (aux_anio == null)) {
                return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
            } else {
                int dia = Integer.parseInt(aux_dia);
                int mes = Integer.parseInt(aux_mes) - 1;
                int anio = Integer.parseInt(aux_anio) - 1900;

                Date fecha = new Date(anio, mes, dia);

                Pacientes paciente = new Pacientes();
                paciente.setFecha_ini(fecha);
                paciente.setTipo("H");
                List listaDePacientes = this.mi.getListarPacientesFN(paciente);
                modelo.put("listaPacientes", listaDePacientes);

                return new ModelAndView("administrarcuadernos/ListarPacientesHisto", modelo);
            }
        }

        return new ModelAndView("administrarpacientes/ListarPacientes", modelo);

    }
}
