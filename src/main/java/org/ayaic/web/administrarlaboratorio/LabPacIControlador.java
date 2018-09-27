package org.ayaic.web.administrarlaboratorio;

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
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabPacIControlador {

    private final MiFacade mi;

    public LabPacIControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LabPacI.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        String id_pedido = request.getParameter("id_pedido");
        String estab = request.getParameter("estab1");
        String medico = request.getParameter("medico1");
        String accion = request.getParameter("accion");
        String boton = request.getParameter("boton");
        String boton2 = request.getParameter("boton2");
        String id_historial = request.getParameter("id_historial");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
        String tipo = "L";

        if (datosconsul.getId_cargo() == 11) {
            tipo = "E";
        }
        if (datosconsul.getId_cargo() == 12) {
            tipo = "R";
        }
        modelo.put("id_historial", id_historial);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_pedido", id_pedido);

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
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);
        modelo.put("anio2", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes2", mesq1);
        modelo.put("dia2", diaq1);
        modelo.put("hora", "00");
        modelo.put("minuto", "00");
        modelo.put("hora2", "23");
        modelo.put("minuto2", "59");
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        if (id_pedido == null) {
            id_pedido = "0";
        }
        if (id_historial == null) {
            id_historial = "0";
        }
        if (id_persona == null) {
            id_persona = Integer.toString(cliente.getId_persona());
        }

        Cuadernos datop = new Cuadernos();
        datop.setTipoconsulta("I");
        datop.setId_pedido(Integer.parseInt(id_pedido));
        datop.setId_persona(Integer.parseInt(id_persona));
        datop.setCod_esta(cliente.getCod_esta());
        datop.setId_historial(Integer.parseInt(id_historial));
        if (cliente.getId_laboratorio() == 0) {
            datop.setId_cargo(0);
            datop.setId_laboratorio(999);
        } else {
            datop.setId_cargo(cliente.getId_laboratorio());
            datop.setId_laboratorio(cliente.getId_laboratorio());
        }
        List lista = this.mi.getDatoPedidoLab(datop);  /////datos tabla pedidolab

        if (lista.size() > 0) {
            Cuadernos listal = (Cuadernos) lista.get(0);
            modelo.put("estab", listal.getNombre());
            modelo.put("medico", listal.getNombres());
            modelo.put("numero", Integer.toString(listal.getNumpieza()));
        }
        if ("GrabarNum".equals(accion)) {
            String numero = request.getParameter("numero");

            Cuadernos dato = new Cuadernos();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            List lista3 = this.mi.getPedidoLab(dato);
            Cuadernos listal3 = (Cuadernos) lista3.get(0);

            dato.setNombre(listal3.getNombre());
            dato.setNombres(listal3.getNombres());
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setNumpieza(Integer.parseInt(numero));
            dato.setId_persona(cliente.getId_persona());
            dato.setFechae(fecha1);
            dato.setEstado("A");
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarPedidoLab(dato);
        }

        if ("Buscar".equals(boton2)) {
            String id_estado = request.getParameter("id_estado");
            String nombre = request.getParameter("nombre");
            if ("".equals(nombre)) {
                return new ModelAndView("Aviso", "mensaje", "debe escribir algun texto en la barra de busqueda");
            }
            if ("N".equals(id_estado)) {
                nombre = nombre.trim();
                nombre = nombre.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
                nombre = nombre.replaceAll("\\s", ":*&");///:*&
                nombre = nombre.replaceAll("ñ", "n");
                nombre = nombre.replaceAll("Ñ", "N");
                ///nombre = "%" + nombre + "%";
                Cuadernos dato = new Cuadernos();
                dato.setNombres(nombre);
                dato.setTipoconsulta("N");
                dato.setCod_esta(cliente.getCod_esta());
                dato.setSeleccion(cliente.getId_almacen());  //////urgencias as id_almacen
                List listalabos = this.mi.getLabPendienteInter(dato);
                modelo.put("listalab", listalabos);
                return new ModelAndView("administrarlaboratorio/LabPacI", modelo);
            }

            if ("P".equals(id_estado)) {
                Cuadernos dato = new Cuadernos();
                dato.setTipoconsulta("P");
                dato.setSeleccion(cliente.getId_almacen());  //////urgencias as id_almacen
                dato.setNumpieza(Integer.parseInt(nombre));
                List listalabos = this.mi.getLabPendienteInterNum(dato);
                modelo.put("listalab", listalabos);
                return new ModelAndView("administrarlaboratorio/LabPacI", modelo);
            }
            if ("H".equals(id_estado)) {
                Cuadernos dato = new Cuadernos();
                dato.setTipoconsulta("H");
                dato.setSeleccion(cliente.getId_almacen());
                dato.setNumpieza(Integer.parseInt(nombre));
                List listalabos = this.mi.getLabPendienteInterHcl(dato);
                modelo.put("listalab", listalabos);
                return new ModelAndView("administrarlaboratorio/LabPacI", modelo);
            }
        }

        if ("Buscar".equals(boton)) {
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
                return new ModelAndView("administrarmedicamentos/BuscarPedidos", modelo);
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
                // fechas de cobro
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin2);
                dato.setTipoconsulta("F");
                dato.setCod_esta(cliente.getCod_esta());
                dato.setSeleccion(cliente.getId_almacen());
                List listalabos = this.mi.getLabPendienteInterFecha(dato);
                modelo.put("listalab", listalabos);
                return new ModelAndView("administrarlaboratorio/LabPacI", modelo);
            }
        }

        if ("adicion".equals(accion)) {
            String expedido = request.getParameter("expedido");
            //Buscar Sexo
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);
            Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            Cuadernos dato = new Cuadernos();
            dato.setTipo(tipo);
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setSeleccion(cliente.getId_almacen());
            if (cliente.getId_laboratorio() == 0) {
                dato.setId_cargo(0);
                dato.setId_laboratorio(999);
            } else {
                dato.setId_cargo(cliente.getId_laboratorio());
                dato.setId_laboratorio(cliente.getId_laboratorio());
            }
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
            modelo.put("expedido", expedido);
            return new ModelAndView("administrarlaboratorio/LabPendiente", modelo);
        }

        if ("EliminaLab".equals(accion)) {
            Historiales auxh = new Historiales();
            auxh.setId_historial(Integer.parseInt(id_historial));
            this.mi.setEliminaLab(auxh);
            return new ModelAndView("administrarlaboratorio/LabPacPendiente", modelo);
        }

        if ("ModificaLab".equals(accion)) {
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String fechae = request.getParameter("fechae");
            String expedido = request.getParameter("expedido");
            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales dato = this.mi.getDatosHistorial(datohi);

            int diax = Integer.parseInt(fechae.substring(0, 2));
            int mesx = Integer.parseInt(fechae.substring(3, 5));
            int aniox = Integer.parseInt(fechae.substring(6, 10));
            Date fechaa = new Date(aniox - 1900, mesx - 1, diax);
            dato.setFecha(fechaa);
            dato.setId_estado("B");
            dato.setExpedido(expedido);
            dato.setId_historial(Integer.parseInt(id_historial));
            try {
                this.mi.setModificaLab(dato);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La Modificacion no se cumplio, verifique los datos");
            }
        }

        Cuadernos datol = new Cuadernos();////10-05-2015
        datol.setTipoconsulta(tipo);////10-05-2015
        datol.setCod_esta(cliente.getCod_esta());////19-12-2016
        datol.setSeleccion(cliente.getId_almacen());   ////10-05-2015
        if (cliente.getId_laboratorio() > 0) {
            datol.setId_cargo(cliente.getId_laboratorio());   ////12-06-2015
            datol.setId_laboratorio(cliente.getId_laboratorio());   ////12-06-2015 
        } else {
            datol.setId_cargo(0);   ////12-06-2015
            datol.setId_laboratorio(999);   ////12-06-2015 
        }
        if (datosconsul.getId_cargo() == 11) {
            List listalab = this.mi.getLabPacPendienteEcoInter(datol);
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() == 12) {
            List listalab = this.mi.getLabPacPendienteRxInter(datol);
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
            List listalab = this.mi.getLabPacPendienteInter(datol);
            modelo.put("listalab", listalab);
        }

        return new ModelAndView("administrarlaboratorio/LabPacI", modelo);
    }
}
