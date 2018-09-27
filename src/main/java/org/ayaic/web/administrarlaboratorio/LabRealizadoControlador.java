package org.ayaic.web.administrarlaboratorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabRealizadoControlador {

    private final MiFacade mi;

    public LabRealizadoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LabRealizado.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String boton = request.getParameter("boton2");
        String id_historial = request.getParameter("id_historial");
        String id_cuaderno = request.getParameter("id_cuaderno");
        String sAccion = request.getParameter("boton");
        String dia_r = request.getParameter("dia_r");
        String mes_r = request.getParameter("mes_r");
        String anio_r = request.getParameter("anio_r");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] horas = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10)};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

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

        if ("Buscar".equals(boton)) {
            String id_estado = request.getParameter("id_estado");
            String nombre = request.getParameter("nombre");
            if ("".equals(nombre)) {
                return new ModelAndView("Aviso", "mensaje", "debe escribir algun texto en la barra de busqueda");
            }
            nombre = nombre.replaceAll("\\s", "%");
            nombre = "%" + nombre + "%";
            Cuadernos dato = new Cuadernos();
            dato.setNombres(nombre);
            dato.setTipoconsulta("N");
            dato.setSeleccion(cliente.getId_almacen());   ////10-05-2015 ///urgencias as id_almacen
            dato.setCod_esta(cliente.getCod_esta());   ////06-07-2015 //
            dato.setId_persona(cliente.getId_persona());
            List listalabos = this.mi.getLabRealizadoAmbNombre(dato);
            modelo.put("listalab", listalabos);
            return new ModelAndView("administrarlaboratorio/LabRealizado", modelo);
        }

        if ("Buscar".equals(sAccion)) {
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

                dato.setId_persona(cliente.getId_persona());
                dato.setTipoconsulta("F");
                dato.setSeleccion(cliente.getId_almacen());   ////10-05-2015
                dato.setCod_esta(cliente.getCod_esta());   ////06-07-2015 //
                dato.setId_persona(cliente.getId_persona());
                List listalab1 = this.mi.getLabRealizadoAmbFecha(dato);
                modelo.put("listalab", listalab1);
                modelo.put("id_historial", id_historial);

                return new ModelAndView("administrarlaboratorio/LabRealizado", modelo);
            }
        }
        if ("Estado".equals(accion)) {
            String expedido = request.getParameter("expedido");
            String id_estado = request.getParameter("id_estado");
            String id_pedido = request.getParameter("id_pedido");
            String fechae = request.getParameter("fechae");
            String id_paciente = request.getParameter("id_paciente");
            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistorial = this.mi.getDatosHistorial(datohi);

            int dia = Integer.parseInt(fechae.substring(0, 2));
            int mes = Integer.parseInt(fechae.substring(3, 5));
            int anio = Integer.parseInt(fechae.substring(6, 10));

            modelo.put("id_pedido", id_pedido);
            modelo.put("anio", Integer.toString(anio));
            modelo.put("mes", Integer.toString(mes));
            modelo.put("dia", Integer.toString(dia));
            modelo.put("expedido", datosHistorial.getExpedido());
            modelo.put("id_historial", id_historial);
            modelo.put("nombres", buscarPaciente.getNombres());
            modelo.put("hcl", Integer.toString(buscarPaciente.getHcl()));
            return new ModelAndView("administrarlaboratorio/CambioLab", modelo);
        }
        if ("Eliminar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            Historiales auxh = new Historiales();
            auxh.setId_persona(cliente.getId_persona());
            auxh.setId_pedido(Integer.parseInt(id_pedido));
            auxh.setCod_esta(cliente.getCod_esta());
            auxh.setCadena1(ip);
            auxh.setCadena2(host);
            this.mi.setEliminaPedidoLab(auxh);
            return new ModelAndView("Aviso", "mensaje", "El registro laboratorio se elimino correctamente");
        }
        if ("Modificar".equals(accion)) {
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String expedido = request.getParameter("expedido");
            String id_estado = request.getParameter("id_estado");

            Date ahora = new Date();

            if (Integer.parseInt(anio) > (ahora.getYear() + 1900) || Integer.parseInt(mes) > 12
                    || Integer.parseInt(dia) > 31 || Integer.parseInt(anio) < 1930) {
                return new ModelAndView("Aviso", "mensaje", "La fecha no es correcta");
            }
            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales dato = this.mi.getDatosHistorial(datohi);

            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fechaa = new Date(aniox, mesx, diax, fecha1.getHours(), fecha1.getMinutes());
            dato.setFecha(fechaa);
            dato.setId_estado(id_estado);
            dato.setExpedido(expedido);
            dato.setId_historial(Integer.parseInt(id_historial));
            try {
                this.mi.setModificaLab(dato);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La Modificacion no se cumplio, verifique los datos");
            }
            // return new ModelAndView("administrarhistoriales/PacientesAtendidos", modelo);
        }

        if ("adicion".equals(accion)) {
            Cuadernos datola = new Cuadernos();
            datola.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datola.setCod_esta(cliente.getCod_esta());
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(datola);
            modelo.put("datosLab", datoLab);
            Historiales datohi = new Historiales();
            datohi.setId_historial(Integer.parseInt(id_historial));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales dato = this.mi.getDatosHistorial(datohi);

            Pacientes buscarPaciente = this.mi.getDatosPaciente(dato.getId_paciente());
            modelo.put("datos", buscarPaciente);
            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            return new ModelAndView("administrarlaboratorio/VerLabRealizado", modelo);
        }
        Cuadernos datos = new Cuadernos();
        datos.setId_persona(cliente.getId_persona());
        int Anio = fecha1.getYear();
        int Mes = fecha1.getMonth();
        int Dia = fecha1.getDay();
        Date Fecha11 = new Date(Anio, Mes, Dia);
        datos.setFecha_ini(Fecha11);
        datos.setFecha_fin(new Date());

        ///Cuadernos datol= new Cuadernos();////10-05-2015
        datos.setSeleccion(cliente.getId_almacen());   ////10-05-2015
        datos.setCod_esta(cliente.getCod_esta());   ////06-07-2015 //
        datos.setId_persona(cliente.getId_persona());
        List listalabb = this.mi.getLabRealizadoAmb(datos);
        modelo.put("listalab", listalabb);

        modelo.put("id_historial", id_historial);

        return new ModelAndView("administrarlaboratorio/LabRealizado", modelo);
    }
}
