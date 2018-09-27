package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Actividad;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarOdonMensualControlador {

    private final MiFacade mi;

    public ListarOdonMensualControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteOdonMensual.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String sAccion = request.getParameter("boton");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};

        Consultorios datosconsultorio = this.mi.getDatosConsultorio(cliente.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("dato", cliente);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
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

        if ("Modificar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");

            Cuadernos datos = new Cuadernos();
            datos.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));
            this.mi.setRegistrarOdonMensual(datos);  ///setRegistrarOdonPersonal
            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            List listaOdon = this.mi.getOdonMensual(datos);
            modelo.put("listaOdon", listaOdon);

            return new ModelAndView(new VerReporteOdonMensualPDF(), modelo);
        }

        if ("Eliminar".equals(accion1)) {
            String id_actividad = request.getParameter("id_actividad");
            Actividad elemina = new Actividad();
            elemina.setId_actividad(Integer.parseInt(id_actividad));
            try {
                this.mi.setEliminarActividad(elemina);
                Actividad dato = new Actividad();
                //dato.setFecha(dFechaini1);
                dato.setId_persona(cliente.getId_persona());
                List listarActividad = this.mi.getListarActividad(dato);
                modelo.put("listaractividad", listarActividad);
                return new ModelAndView("administrarcuadernos/NuevaActividad", modelo);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }
        }

        if ("Guardar".equals(accion1)) {
            String actividad = request.getParameter("actividad");
            String actnumero = request.getParameter("actnumero");
            String tema = request.getParameter("tema");
            String numero = request.getParameter("numero");
            String duracion = request.getParameter("duracion");
            String lugar = request.getParameter("lugar");
            String diai = request.getParameter("diai");
            String mesi = request.getParameter("mesi");
            String anioi = request.getParameter("anioi");

            int iAnio1 = Integer.parseInt(anioi) - 1900;
            int iMes1 = Integer.parseInt(mesi) - 1;
            int iDia1 = Integer.parseInt(diai);
            Date Fecha1 = new Date(iAnio1, iMes1, iDia1);

            long fechaInicial = Fecha1.getTime();
            long fechaFinal = fecha1.getTime();
            long diferencia = fechaFinal - fechaInicial;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            if (fechaInicial > fechaFinal) {
                return new ModelAndView("Aviso", "mensaje", "La fecha que desea ingresar NO es correcta para Actividad, esta adelantada a HOY");
            }

            Actividad acti = new Actividad();

            if ("1".equals(actividad)) {
                acti.setSuma1(1);
                actividad = "ACTIVIDAD EDUCATIVA";
            }
            if ("2".equals(actividad)) {
                acti.setSuma2(1);
                actividad = "ACTIVIDAD DE LA COMUNIDAD CON PARTICIPACION DEL ESTABLECIMIENTO";
            }
            if ("3".equals(actividad)) {
                acti.setSuma3(1);
                actividad = "SUPERVISION RECIBIDA";
            }
            if ("4".equals(actividad)) {
                acti.setSuma4(1);
                actividad = "CAPACITACION RECIBIDA";
            }
            if ("5".equals(actividad)) {
                acti.setSuma5(1);
                actividad = "REUNIEN CON EL COMITE LOC. DE SALUD REALIZADA";
            }
            if ("6".equals(actividad)) {
                acti.setSuma6(1);
                actividad = "REUNIEN CON AUTORIDADES LOCALES DE SALUD REALIZADA";
            }
            if ("7".equals(actividad)) {
                acti.setSuma7(1);
                actividad = "FERIA REALIZADA";
            }
            if ("8".equals(actividad)) {
                acti.setSuma8(1);
                actividad = "REUNIEN DEL CAI REALIZADA";
            }
            if ("9".equals(actividad)) {
                actividad = "TEMA DE LA ACTIVIDAD EDUCATIVA";
            }
            if ("10".equals(actividad)) {
                actividad = "REUNIONES, FERIAS Y ACTIVIDADES EDUCATIVAS-LUGAR";
            }

            if ("1".equals(actnumero)) {
                acti.setSuma11(Integer.parseInt(numero));
            }   /////num_fam_realiza
            if ("2".equals(actnumero)) {
                acti.setSuma12(Integer.parseInt(numero));
            }  /////num_nueva_carpeta
            if ("3".equals(actnumero)) {
                acti.setSuma13(Integer.parseInt(numero));
            }  /////num_seg_carpeta   
            if ("4".equals(actnumero)) {
                acti.setSuma14(Integer.parseInt(numero));
            }  /////num_medico
            if ("5".equals(actnumero)) {
                acti.setSuma15(Integer.parseInt(numero));
            }  /////num_enfermera
            if ("6".equals(actnumero)) {
                acti.setSuma16(Integer.parseInt(numero));
            }  /////num_auxiliar
            if ("7".equals(actnumero)) {
                acti.setSuma17(Integer.parseInt(numero));
            }  /////num_odontologo
            if ("8".equals(actnumero)) {
                acti.setSuma18(Integer.parseInt(numero));
            }  /////num_otro
            if ("9".equals(actnumero)) {
                acti.setSuma19(Integer.parseInt(numero));
            }  /////num_comun_cai
            if ("10".equals(actnumero)) {
                acti.setSuma20(Integer.parseInt(numero));
            }  /////num_escolar
            if ("11".equals(actnumero)) {
                acti.setSuma21(Integer.parseInt(numero));
            }  /////num_jovenes
            if ("12".equals(actnumero)) {
                acti.setSuma22(Integer.parseInt(numero));
            }  /////num_adultos
            if ("13".equals(actnumero)) {
                acti.setSuma23(Integer.parseInt(numero));
            }  /////num_dirigentes
            if ("14".equals(actnumero)) {
                acti.setSuma24(Integer.parseInt(numero));
            }  /////num_promotor
            if ("15".equals(actnumero)) {
                acti.setSuma25(Integer.parseInt(numero));
            }  /////numero_otros
            if ("16".equals(actnumero)) {
                acti.setSuma26(Integer.parseInt(numero));
            }  /////num_visitas_fam
            if ("17".equals(actnumero)) {
                acti.setSuma27(Integer.parseInt(numero));
            }  /////num_sugerencias
            if ("18".equals(actnumero)) {
                acti.setSuma28(Integer.parseInt(numero));
            }  /////num_quejas
            if ("19".equals(actnumero)) {
                acti.setSuma29(Integer.parseInt(numero));
            }  /////num_acredita
            if ("20".equals(actnumero)) {
                acti.setSuma30(Integer.parseInt(numero));
            }  /////num_auditoria

            acti.setFecha(Fecha1);
            acti.setActividad(actividad);
            acti.setTema(tema);
            acti.setCadena(lugar);
            acti.setNumero(Integer.parseInt(numero));
            acti.setDiasi(Integer.parseInt(duracion));
            acti.setId_persona(cliente.getId_persona());
            acti.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearActividad(acti);

            Actividad dato = new Actividad();
            //dato.setFecha(dFechaini1);
            dato.setId_persona(cliente.getId_persona());
            List listarActividad = this.mi.getListarActividad(dato);
            modelo.put("listaractividad", listarActividad);
            return new ModelAndView("administrarcuadernos/NuevaActividad", modelo);
        }

        if ("Mostrar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");

            Cuadernos datos = new Cuadernos();
            datos.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));

            //recuperar informacion
            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            //datos odontologia
            List listaOdon = this.mi.getOdonMensual(datos);
            modelo.put("listaOdon", listaOdon);

            return new ModelAndView(new VerReporteOdonMensualPDF(), modelo);
        }

        if ("Resumen Dental".equals(accion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                //dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("G");

                this.mi.setRegistrarOdonMensual(dato);
                modelo.put("fecha1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                modelo.put("fecha2", aniof + "-" + mesf + "-" + diaf + " " + horaf + ":" + minutof);
                Actividad act = new Actividad();
                act.setFecha(dFechaini1);
                act.setFecha2(dFechafin1);
                act.setId_estado("T");
                List listarActividad = this.mi.getListarActividadTodos(act);
                modelo.put("listaractividad", listarActividad);

                try {
                    List listaOdon = this.mi.getOdonMensual(dato);
                    modelo.put("listaOdon", listaOdon);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "No existe datos para esta fecha");
                }

                Prestaciones datos = new Prestaciones();
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                datos.setReferido("G");
                List listarPresOdon = this.mi.getListarPresOdonGen(datos);
                modelo.put("listarPresOdon", listarPresOdon);

                return new ModelAndView(new VerReporteOdonMensualPDF(), modelo);

            }
        }

        if ("Registrar Actividades".equals(accion)) {
            Actividad dato = new Actividad();
            //dato.setFecha(dFechaini1);
            dato.setId_persona(cliente.getId_persona());
            List listarActividad = this.mi.getListarActividad(dato);
            modelo.put("listaractividad", listarActividad);
            return new ModelAndView("administrarcuadernos/NuevaActividad", modelo);
        }

        if ("Informe Mensual de Actividades".equals(accion)) {
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
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
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

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);

                this.mi.setRegistrarOdonPersonal(dato);

                try {
                    List listaOdon = this.mi.getOdonMensual(dato);
                    modelo.put("listaOdon", listaOdon);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "No existe datos para este usuario");
                }

                Prestaciones datos = new Prestaciones();
                datos.setFecha_ini(dFechaini1);
                datos.setFecha_fin(dFechafin1);
                datos.setId_persona(cliente.getId_usuario());
                List listarPresOdon = this.mi.getListarPresOdon(datos);
                modelo.put("listarPresOdon", listarPresOdon);
                modelo.put("fecha1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                modelo.put("fecha2", aniof + "-" + mesf + "-" + diaf + " " + horaf + ":" + minutof);

                Actividad act = new Actividad();
                act.setFecha(dFechaini1);
                act.setFecha2(dFechafin1);
                act.setId_persona(cliente.getId_persona());
                act.setId_estado("A");
                List listarActividad = this.mi.getListarActividadTot(act);
                modelo.put("listaractividad", listarActividad);
                return new ModelAndView(new VerReporteOdonMensualPDF(), modelo);

            }
        }

        if ("Adicionar".equals(accion)) {

            List listarImm = this.mi.getListarFaltaOdonMensual();
            modelo.put("listarImm", listarImm);
            modelo.put("accion", accion);
            return new ModelAndView("administrarcuadernos/ListarOdonMensual", modelo);
        }

        List listarImm = this.mi.getListarOdonMensual();
        modelo.put("listarImm", listarImm);
        modelo.put("accion", "Mostrar");

        return new ModelAndView("administrarcuadernos/ListarOdonMensual", modelo);

    }
}
