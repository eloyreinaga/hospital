package org.ayaic.web.administrarhistoriales.ambulatorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PacientesAtendidosControlador {

    private final MiFacade mi;

    public PacientesAtendidosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacientesAtendidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Consultorios datosconsul = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("horas", horas);
        modelo.put("minutos", minutos);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }

        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");
        modelo.put("dato", cliente);

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        persona.setDip("S");
        Menues usuario_rol = new Menues();
        usuario_rol.setId_usuario(cliente.getId_usuario());
        List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
        List listarPaises = this.mi.getListarPersonasSoloAten(persona);
        modelo.put("listaPersonas", listarPaises);
        for (int i = 0; i < listaUsrRoles.size(); i++) {
            Menues datorol = (Menues) listaUsrRoles.get(i);
            if (datorol.getId_rol() == 27) {
                persona.setDip("L"); /////getListarPersonasLocal
                List listarPaises2 = this.mi.getListarPersonasLocal(persona);
                modelo.put("listaPersonas", listarPaises2);
            }
        }

        modelo.put("estab", cliente.getArea());
        modelo.put("rol", Integer.toString(cliente.getId_rol()));

        String sId_estado = request.getParameter("id_estado");
        String id_persona = request.getParameter("id_persona");
        String sAccion = request.getParameter("boton");
        String accion = request.getParameter("boton");

        if ("Adolecentes".equals(accion)) {
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
            Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

            modelo.put("mes", Integer.toString(iMes2 + 1));
            modelo.put("annio", Integer.toString(iAnio2 + 1900));
            modelo.put("id_persona", id_persona);

            Historiales datohis19 = new Historiales();
            datohis19.setFecha_ini(dFechaini1);
            datohis19.setFecha_fin(dFechafin2);

            datohis19.setId_persona(cliente.getId_persona());
            List listarPacientes = this.mi.getListarPacientes19T(datohis19);
            modelo.put("milista19", listarPacientes);
            return new ModelAndView(new ListarPacientes19PDF(), modelo);
        }

        if ("ResumenSUMI".equals(accion)) {
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
            Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

            Historiales datoSumi = new Historiales();
            datoSumi.setFecha_ini(dFechaini1);
            datoSumi.setFecha_fin(dFechafin2);

            List listarPacientes = this.mi.getSumi(datoSumi);
            modelo.put("milistaSumi", listarPacientes);
            modelo.put("anio2", aniof);
            modelo.put("mes2", mesf);
            return new ModelAndView(new ListarPacientesSumiPDF(), modelo);
        }

        if ("Resumen Laboratorios".equals(accion)) {
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
                return new ModelAndView("administrarhistoriales/PacientesAtendidos", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);

                dato.setId_persona(cliente.getId_persona());
                dato.setId_seguro(0);
                dato.setId_cuaderno(1000);
                List listalabT = this.mi.getLabRealizadoSumi(dato);
                modelo.put("listalabT", listalabT);
                dato.setTipoconsulta("I");
                List listalabS = this.mi.getLabRealizadoTotal(dato);
                modelo.put("listalabS", listalabS);
                Laboratorios pai = new Laboratorios();
                pai.setId_estado("A");
                List listalabos = this.mi.getListarLaboratorios(pai);
                modelo.put("labos", listalabos);

                return new ModelAndView("administrarlaboratorio/LabResumen", modelo);
            }
        }

        if ("ResumenPrestacion".equals(accion) || "ResumenPrestacionPartos-Cesareas".equals(accion)) {
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
            Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

            Historiales datoSumi = new Historiales();
            datoSumi.setFecha_ini(dFechaini1);
            datoSumi.setFecha_fin(dFechafin2);

            if ("ResumenPrestacion".equals(accion)) {
                List listarPacientes = this.mi.getListarResumenPrestacion(datoSumi);
                modelo.put("milista", listarPacientes);
            }
            if ("ResumenPrestacionPartos-Cesareas".equals(accion)) {
                List listarPacientes2 = this.mi.getListarResumenPrestacion2(datoSumi);
                modelo.put("milista", listarPacientes2);
            }

            return new ModelAndView("administrarhistoriales/ResumenPrestaciones", modelo);
        }

        if ("PresDuplicadas".equals(accion) || "ResumenMedPrestacion".equals(accion) || "ResumenMedicaPartos-Cesareas".equals(accion) || "PrestacionesSinMedicamentos".equals(accion) || "PrestacionesDuplicadas".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_prestacion = request.getParameter("id_prestacion");
            String prestacion = request.getParameter("prestacion");
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
            Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

            modelo.put("diai", diai);
            modelo.put("mesi", mesi);
            modelo.put("anioi", anioi);
            modelo.put("horai", horai);
            modelo.put("minutoi", minutoi);
            modelo.put("diaf", diaf);
            modelo.put("mesf", mesf);
            modelo.put("aniof", aniof);
            modelo.put("horaf", horaf);
            modelo.put("minutof", minutof);

            Prestaciones datoPres = new Prestaciones();
            Recetas datoRes = new Recetas();
            datoPres.setFecha_ini(dFechaini1);
            datoPres.setFecha_fin(dFechafin2);
            datoRes.setFecha_ini(dFechaini1);
            datoRes.setFecha_fin(dFechafin2);
            datoRes.setCod_esta(cliente.getCod_esta());
            datoPres.setCod_esta(cliente.getCod_esta());

            if ("PresDuplicadas".equals(accion)) {
                //Prestaciones dato = new Prestaciones(); 
                String sFecha_ini = request.getParameter("valor_1");
                String sFecha_fin = request.getParameter("valor_2");
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio11 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes11 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia11 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio12 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes12 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia12 = Integer.parseInt(sFechafin[2]);

                Date dFechaini11 = new Date(iAnio11, iMes11, iDia11);
                Date dFechafin12 = new Date(iAnio12, iMes12, iDia12);
                datoPres.setFecha_ini(dFechaini11);
                datoPres.setFecha_fin(dFechafin12);
                datoRes.setFecha_ini(dFechaini11);
                datoRes.setFecha_fin(dFechafin12);
                datoPres.setId_paciente(Integer.parseInt(id_paciente));
                datoPres.setId_prestacion(Integer.parseInt(id_prestacion));
                datoPres.setDescripcion("P");///11/06/2016    setEliminarPresDupli
                this.mi.setEliminarPresDupli(datoPres);
                accion = "PrestacionesDuplicadas";
            }

            List listarRecetasPres = this.mi.getListarSumiRecetasPres(datoPres);
            modelo.put("listarRecetasPres", listarRecetasPres);

            if ("ResumenMedicaPartos-Cesareas".equals(accion)) {
                datoPres.setReferido("2");   ////getListarSumiRecetasPres2
                List listarRecetasPres2 = this.mi.getListarSumiRecetasPres2(datoPres);
                modelo.put("listarRecetasPres", listarRecetasPres2);
            }
            if ("PrestacionesDuplicadas".equals(accion)) {
                datoPres.setReferido("3");  ////getListarPrestacionesDuplis
                List listarRecetasPres2 = this.mi.getListarPrestacionesDuplis(datoPres);
                modelo.put("listarRecetasPres", listarRecetasPres2);
            }
            if ("PrestacionesSinMedicamentos".equals(accion)) {
                datoPres.setReferido("4");  ////getListarPrestacionSinMed
                List listarRecetasPres2 = this.mi.getListarPrestacionSinMed(datoPres);
                modelo.put("listarRecetasPres", listarRecetasPres2);
            }

            List listarRecetasP = this.mi.getListarRecetasPres(datoRes);
            modelo.put("listarRecetasP", listarRecetasP);

            if ("ResumenMedicaPartos-Cesareas".equals(accion)) {
                return new ModelAndView("administrarhistoriales/ResumenPrestacionMed2", modelo);
            }
            if ("PrestacionesDuplicadas".equals(accion)) {
                return new ModelAndView("administrarhistoriales/ResumenPrestacionesDup", modelo);
            }
            if ("PrestacionesSinMedicamentos".equals(accion)) {
                return new ModelAndView("administrarhistoriales/ResumenPrestacionesSinMed", modelo);
            }
            return new ModelAndView("administrarhistoriales/ResumenPrestacionMed", modelo);
        }

        if ("ResumenEnfer".equals(accion)) {
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
            Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

            Cuadernos cua6 = new Cuadernos();
            cua6.setId_persona(Integer.parseInt(id_persona));
            cua6.setCod_esta(cliente.getCod_esta());
            cua6.setFecha_ini(dFechaini1);
            cua6.setFecha_fin(dFechafin2);
            cua6.setAspecto("T");
            List listarAtendidos = this.mi.getListaPacientesCuaderno6Total(cua6);
            modelo.put("listarAtendidos", listarAtendidos);

            modelo.put("id_persona", id_persona);
            return new ModelAndView("administrarhistoriales/ListarAtendidosEnfer", modelo);
        }

        if ("Resumen".equals(sAccion) || "AdultoMayor".equals(sAccion) || "ResumenPrestaciones".equals(sAccion) || "Neonatos".equals(sAccion) || "Pediatricos".equals(sAccion) || "Mujer".equals(sAccion)) {
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
                return new ModelAndView("administrarhistoriales/PacientesAtendidos", modelo);
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
                Date dFechafin2 = new Date(iAnio2, iMes2, iDia2, iHor2, iMin2, 59);

                modelo.put("mes", Integer.toString(iMes2 + 1));
                modelo.put("annio", Integer.toString(iAnio2 + 1900));
                modelo.put("id_persona", id_persona);

                Prestaciones datopre = new Prestaciones();
                datopre.setCod_esta(cliente.getCod_esta());
                if (id_persona == null) {
                    datopre.setId_persona(cliente.getId_persona());
                } else {
                    datopre.setId_persona(Integer.parseInt(id_persona));
                }

                datopre.setFecha_ini(dFechaini1);
                datopre.setFecha_fin(dFechafin2);

                Historiales datohis = new Historiales();
                if (id_persona == null) {
                    datohis.setId_persona(cliente.getId_persona());
                    Personas buscarEmpleado = this.mi.getBuscarPersona(cliente.getId_persona());
                    modelo.put("empleado", buscarEmpleado);
                } else {
                    datohis.setId_persona(Integer.parseInt(id_persona));
                    Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));
                    modelo.put("empleado", buscarEmpleado);
                }
                datohis.setFecha_ini(dFechaini1);
                datohis.setFecha_fin(dFechafin2);
                List prestacionesDadas = this.mi.getListarPrestacionesDadasNeo(datopre);
                modelo.put("prestacionesDadas", prestacionesDadas);
                if ("ResumenPrestaciones".equals(sAccion)) {
                    datopre.setPrestacion("R");
                    prestacionesDadas = this.mi.getListarResumenPrest(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas);
                    modelo.put("persona", persona);
                    modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                    return new ModelAndView(new VerResumenPresPDF(), modelo);
                }

                if ("Neonatos".equals(sAccion)) {
                    datohis.setAccion("N");
                    datopre.setPrestacion("N");
                    modelo.put("soloedad", "si");
                    prestacionesDadas = this.mi.getListarPrestacionesDadasNeo(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas);
                }
                if ("Pediatricos".equals(sAccion)) {
                    datohis.setAccion("P");
                    datopre.setPrestacion("P");
                    modelo.put("soloedad", "no");
                    prestacionesDadas = this.mi.getListarPrestacionesDadasPedi(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas);
                }
                if ("Mujer".equals(sAccion)) {
                    datohis.setAccion("M");
                    datopre.setPrestacion("M");
                    modelo.put("soloedad", "no");
                    prestacionesDadas = this.mi.getListarPrestacionesDadasMuje(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas);
                }
                if ("AdultoMayor".equals(sAccion)) {
                    datohis.setAccion("A");
                    datopre.setPrestacion("A");
                    modelo.put("soloedad", "no");
                    prestacionesDadas = this.mi.getListarPrestacionesDadasMayor(datopre);
                    modelo.put("prestacionesDadas", prestacionesDadas);
                }
                if ("Resumen".equals(sAccion)) {
                    datohis.setAccion("Z");////getHistorialAtendidosFecha
                    List listarAtendidos = this.mi.getHistorialAtendidosFecha(datohis);
                    modelo.put("milistaAten", listarAtendidos);
                    List listarSeguros = this.mi.getListarSeguros("A");
                    modelo.put("listaPacAfi", listarSeguros);
                    return new ModelAndView("administrarhistoriales/ListarAtendidos", modelo);
                }

                if ("Neonatos".equals(sAccion)) {
                    List listaConsultorios = this.mi.getListarPacientesHistoNeo(datohis);
                    modelo.put("listaPacientes", listaConsultorios);
                    List pacientePrestacion2 = this.mi.getListarPacientesPrestacionesNeo(datopre);
                    modelo.put("pacientePrestacion", pacientePrestacion2);
                }
                if ("Pediatricos".equals(sAccion)) {
                    List listaConsultorios = this.mi.getListarPacientesHistoPedi(datohis);
                    modelo.put("listaPacientes", listaConsultorios);
                    List pacientePrestacion2 = this.mi.getListarPacientesPrestacionesPedi(datopre);
                    modelo.put("pacientePrestacion", pacientePrestacion2);
                }
                if ("Mujer".equals(sAccion)) {
                    List listaConsultorios = this.mi.getListarPacientesHistoMuje(datohis);
                    modelo.put("listaPacientes", listaConsultorios);
                    List pacientePrestacion2 = this.mi.getListarPacientesPrestacionesMuje(datopre);
                    modelo.put("pacientePrestacion", pacientePrestacion2);
                }
                if ("AdultoMayor".equals(sAccion)) {
                    List listaConsultorios = this.mi.getListarPacientesHistoMayor(datohis);
                    modelo.put("listaPacientes", listaConsultorios);
                    List pacientePrestacion2 = this.mi.getListarPacientesPrestacionesMayor(datopre);
                    modelo.put("pacientePrestacion", pacientePrestacion2);
                }

                if (prestacionesDadas.size() > 30 && datosconsul.getId_cargo() == 10) {///solo para laboratorio y las prestaciones son muchas
                    return new ModelAndView(new ListarConsultaLabPDF(), modelo);
                } else {
                    return new ModelAndView(new ListarConsultaPDF(), modelo);
                }
            }
        }
        return new ModelAndView("administrarhistoriales/PacientesAtendidos", modelo);
    }
}
