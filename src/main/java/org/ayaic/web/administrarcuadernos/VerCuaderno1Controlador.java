package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VerCuaderno1Controlador {

    private final MiFacade mi;

    public VerCuaderno1Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuaderno1.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("accion");
        String acc = request.getParameter("acc");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        if ("SNIS Consulta Externa".equals(sAccion)) {
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

                modelo.put("dato", cliente);

                List listarSeguros = this.mi.getListarSeguros("%");
                modelo.put("listaPacAfi", listarSeguros);
                Cuadernos dato = new Cuadernos();
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setAspecto("T");
                List listaCobros = this.mi.getReporteSNISConsulta(dato); ///getReporteSNISConsulta  consultas menores 5 años
                modelo.put("listaCobros", listaCobros);
                dato.setAspecto("D");
                List listaCobros2 = this.mi.getReporteSNISConsulta2(dato); ///getReporteSNISConsulta2 consultas mayores 5 años
                modelo.put("listaCobros2", listaCobros2);
                modelo.put("persona", persona);
                modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                return new ModelAndView(new VerConsultaSnisPDF(), modelo);

            }
        }

        if ("Informe Diario Internacion".equals(sAccion) || "Informe Diario Consulta Externa".equals(sAccion) || "Cuaderno No 1".equals(sAccion) || "Cuaderno Asegurados(C1)".equals(sAccion) || "Consulta Externa".equals(sAccion) || "C-2.Consulta Externa".equals(sAccion)) {
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

                modelo.put("dato", cliente);

                List listarSeguros = this.mi.getListarSeguros("%");
                modelo.put("listaPacAfi", listarSeguros);
                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato.setId_persona(cliente.getId_persona());
                //dato.setId_historial(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipo("U");
                List listaCobros = this.mi.getVerCuaderno1(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                    List listaCobros2 = this.mi.getVerCuaderno1Todos(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                if ("Informe Diario Internacion".equals(sAccion) || "Informe Diario Consulta Externa".equals(sAccion) || "Cuaderno Asegurados(C1)".equals(sAccion)) {
                    dato.setId_estado("P");
                    List listaCobros2 = this.mi.getVerCuaderno1CNS(dato);
                    modelo.put("listaCobros", listaCobros2);
                    if ("Informe Diario Consulta Externa".equals(sAccion)) {
                        dato.setTipo("C");   ////getVerCuaderno1CNS
                        List listaCobros21 = this.mi.getVerCuaderno1CNS(dato);
                        modelo.put("listaCobros", listaCobros21);
                    }
                    if ("Informe Diario Internacion".equals(sAccion)) {
                        dato.setTipo("I");   ////getVerCuaderno1CNSInter
                        List listaCobros21 = this.mi.getVerCuaderno1CNSInter(dato);
                        modelo.put("listaCobros", listaCobros21);
                    }
                } else {
                    dato.setId_estado("%");
                }

                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);
                if ("Consulta Externa".equals(sAccion)) {
                    dato.setEstado("T");            /////getVerCuaderno1SspamTodos
                    List listaCobros3 = this.mi.getVerCuaderno1SspamTodos(dato);
                    modelo.put("listaCobros", listaCobros3);
                    return new ModelAndView(new VerCuaderno12PDF(), modelo);
                } else {
                    if ("C-2.Consulta Externa".equals(sAccion)) {
                        dato.setTipo("T");
                        dato.setEstado("T");
                        List listaCobros4 = this.mi.getVerCuaderno1T(dato);
                        modelo.put("listaCobros", listaCobros4);
                        return new ModelAndView(new VerC2PDF(), modelo);
                    } else {
                        List listaCobros5 = this.mi.getVerCuaderno1Todos(dato);
                        modelo.put("listaCobros", listaCobros5);
                        if ("Informe Diario Internacion".equals(sAccion) || "Informe Diario Consulta Externa".equals(sAccion)) {
                            return new ModelAndView(new VerCuaderno1CNSPDF(), modelo);
                        }
                        return new ModelAndView(new VerCuaderno1PDF(), modelo);
                    }
                }
            }
        }
        //////////2014
        if ("CuadernoNo 1".equals(sAccion)) {
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

                modelo.put("dato", cliente);

                //List listarSeguros = this.mi.getListarSeguros("%");
                //modelo.put("listaPacAfi", listarSeguros);
                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipo("U");
                List listalibro6 = this.mi.getVerCuaderno6C1(dato);
                modelo.put("listalibro6", listalibro6);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                    dato.setId_estado("%");
                    List listalibro61 = this.mi.getVerCuaderno6Todos(dato);
                    modelo.put("listalibro6", listalibro61);
                }

                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);

                List listaCobros = this.mi.getVerCuaderno1(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    List listaCobros2 = this.mi.getVerCuaderno1Todos(dato);
                    modelo.put("listaCobros", listaCobros2);
                }
                dato.setTipo("1");
                List listalibro62 = this.mi.getVerCuaderno6C1(dato);
                modelo.put("listalibro6", listalibro62);
                local.setArea("R");
                local.setId_persona(1);
                List Estab12 = this.mi.getListarEstabRef(local);
                modelo.put("listaEstab", Estab12);
                return new ModelAndView(new VerCuaderno1_2014PDF(), modelo);
            }
        }

        if ("ServicioEmergancia".equals(sAccion)) {
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

                modelo.put("dato", cliente);

                List listarSeguros = this.mi.getListarSeguros("%");
                modelo.put("listaPacAfi", listarSeguros);
                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setCod_esta(cliente.getCod_esta());
                dato.setTipo("U");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);
                List listaCobros = this.mi.getVerCuaderno1(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                    dato.setId_estado("%");
                    List listaCobros2 = this.mi.getVerCuaderno1Todos(dato);
                    modelo.put("listaCobros", listaCobros2);
                }

                dato.setTipo("1");
                List listalibro6 = this.mi.getVerCuaderno6C1(dato);
                modelo.put("listalibro6", listalibro6);
                local.setArea("R");
                local.setId_persona(1);
                List Estab12 = this.mi.getListarEstabRef(local);
                modelo.put("listaEstab", Estab12);
                return new ModelAndView(new VerCuaderno1_2014PDF(), modelo);
            }
        }

        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
}
