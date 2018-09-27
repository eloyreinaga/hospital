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
public class VerCuaderno4Controlador {

    private final MiFacade mi;

    public VerCuaderno4Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuaderno4.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        modelo.put("dato", cliente);

        if ("Cuaderno No 4".equals(sAccion) || "Atencion<5anios".equals(sAccion) || "Pediatria".equals(sAccion) || "C13.Pediatria".equals(sAccion) || "C-13.Consulta Ext.Pediatria".equals(sAccion)) {
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
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("U");

                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                }
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                if ("Atencion<5anios".equals(sAccion)) {
                    dato.setTipo("X"); ////getVerCuaderno4Todos
                    List listaCuaderno4 = this.mi.getVerCuaderno4Todos(dato);
                    modelo.put("listaCuaderno4", listaCuaderno4);
                    return new ModelAndView(new VerSNISninos4PDF(), modelo);
                }
                if ("Pediatria".equals(sAccion)) {
                    dato.setEstado("T");
                    List listaCuaderno4 = this.mi.getVerCuaderno4(dato);
                    modelo.put("listaCuaderno4", listaCuaderno4);
                    return new ModelAndView(new VerCuaderno42PDF(), modelo);
                } else {
                    if ("C-13.Consulta Ext.Pediatria".equals(sAccion)) {
                        List listaCuaderno4 = this.mi.getVerCuaderno4(dato);
                        modelo.put("listaCuaderno4", listaCuaderno4);
                        return new ModelAndView(new VerC13PDF(), modelo);
                    } else {
                        if ("C13.Pediatria".equals(sAccion)) {
                            List listaCuaderno4 = this.mi.getVerCuaderno4(dato);
                            modelo.put("listaCuaderno4", listaCuaderno4);
                            return new ModelAndView(new VerC13PDF(), modelo);
                        } else {
                            List listaCuaderno4 = this.mi.getVerCuaderno4(dato);
                            modelo.put("listaCuaderno4", listaCuaderno4);
                            return new ModelAndView(new VerCuaderno4PDF(), modelo);
                        }
                    }
                }
            }
        }

        if ("CuadernoNo2Fuera".equals(sAccion) || "CuadernoNo2Dentro".equals(sAccion) || "CuadernoNo2Dentro".equals(sAccion) || "CuadernoNo2Fuera".equals(sAccion) || "CuadernoNo 2Crecimiento".equals(sAccion) || "CuadernoNo 2Consultas".equals(sAccion)) {
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
                dato.setTipo("U");
                List listaCuaderno4 = this.mi.getVerCuaderno4_C2(dato);
                modelo.put("listaCuaderno4", listaCuaderno4);
                if (datosconsul.getId_cargo() == 7) {
                    dato.setTipo("T");
                }
                List listaCuaderno41 = this.mi.getVerCuaderno4_C2(dato);
                modelo.put("listaCuaderno4", listaCuaderno41);
                if ("CuadernoNo2Dentro".equals(sAccion)) {
                    dato.setBcg(0); ////para formatos 2014
                } else {
                    dato.setBcg(1);
                }
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                List listaCuaderno42 = this.mi.getVerCuaderno4_C2(dato);
                modelo.put("listaCuaderno4", listaCuaderno42);
                if ("Pediatria".equals(sAccion)) {
                    dato.setEstado("T");
                    List listaCuaderno43 = this.mi.getVerCuaderno4(dato);
                    modelo.put("listaCuaderno4", listaCuaderno43);
                    return new ModelAndView(new VerCuaderno42PDF(), modelo);
                } else {//////formatos 2014

                    if ("CuadernoNo 2Consultas".equals(sAccion)) {
                        dato.setTipo("C");
                        List listaCuaderno44 = this.mi.getVerCuaderno4_C2Consul(dato);
                        modelo.put("listaCuaderno4", listaCuaderno44);
                    } else if ("CuadernoNo 2Crecimiento".equals(sAccion)) {
                        dato.setTipo("D");
                        List listaCuaderno44 = this.mi.getVerCuaderno4_C2Creci(dato);
                        modelo.put("listaCuaderno4", listaCuaderno44);
                    } else if ("CuadernoNo2Dentro".equals(sAccion)) {
                        List listaCuaderno44 = this.mi.getVerCuaderno4_C22014DIndi(dato);
                        modelo.put("listaCuaderno4", listaCuaderno44);
                        if (datosconsul.getId_cargo() == 7) {
                            List listaCuaderno45 = this.mi.getVerCuaderno4_C22014D(dato);
                            modelo.put("listaCuaderno4", listaCuaderno45);
                        }
                    }
                    if ("CuadernoNo2Fuera".equals(sAccion)) {
                        List listaCuaderno45 = this.mi.getVerCuaderno4_C22014FIndi(dato);
                        modelo.put("listaCuaderno4", listaCuaderno45);
                        if (datosconsul.getId_cargo() == 7) {
                            List listaCuaderno46 = this.mi.getVerCuaderno4_C22014F(dato);
                            modelo.put("listaCuaderno4", listaCuaderno46);
                        }
                    }

                    dato.setTipo("2");
                    List listalibro6 = this.mi.getVerCuaderno6(dato);
                    modelo.put("listalibro6", listalibro6);
                    return new ModelAndView(new VerCuaderno4_2014PDF(), modelo);
                }
            }
        }

        if ("UNI Consolidado".equals(sAccion)) {
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
                dato.setTipo("U");

                List listaCuadernoUni = this.mi.getDatosUni(dato);
                modelo.put("listaCuadernoUni", listaCuadernoUni);
                return new ModelAndView(new VerUNI_PDF(), modelo);
            }
        }

        if ("Solo Crecimiento".equals(sAccion) || "P/T Desnutricion Aguda".equals(sAccion) || "Nuevos DA P/T".equals(sAccion)) {
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
                dato.setTipo("U");

                if ("Solo Crecimiento".equals(sAccion)) {
                    List listaCuaderno4 = this.mi.getVerCuaderno4Creci(dato);
                    modelo.put("listaCuaderno4", listaCuaderno4);
                } else {
                    if ("P/T Desnutricion Aguda".equals(sAccion)) {
                        dato.setTipo("A");
                        List listaCuaderno4 = this.mi.getVerCuaderno4CreciDA(dato);
                        modelo.put("listaCuaderno4", listaCuaderno4);
                    } else {
                        dato.setTipo("N");
                        List listaCuaderno4 = this.mi.getVerCuaderno4CreciNuevoDA(dato);
                        modelo.put("listaCuaderno4", listaCuaderno4);
                    }
                }

                dato.setTipo("T");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);

                return new ModelAndView(new VerCuaderno4PDF(), modelo);
            }
        }

        if ("Solo Consulta<5".equals(sAccion)) {
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
                dato.setTipo("U");

                List listaCuaderno4 = this.mi.getVerCuaderno4Enfer(dato);
                modelo.put("listaCuaderno4", listaCuaderno4);
                dato.setTipo("T");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                return new ModelAndView(new VerCuaderno4PDF(), modelo);
            }
        }

        if ("UNI Consolidado".equals(sAccion)) {
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
                dato.setTipo("U");

                List listaCuaderno4 = this.mi.getVerCuaderno4Enfer(dato);
                modelo.put("listaCuaderno4", listaCuaderno4);
                dato.setTipo("T");
                List listaCie = this.mi.getVerCuaderno1Cie(dato);
                modelo.put("listaCie", listaCie);
                return new ModelAndView(new VerUNI_PDF(), modelo);
            }
        }

        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
}
