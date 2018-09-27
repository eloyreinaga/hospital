package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistorialAtendidosControlador {

    private final MiFacade mi;

    public HistorialAtendidosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/HistorialAtendidos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionc = request.getParameter("accionc");
        String id_paciente = request.getParameter("id_paciente");
        String id_pedido = request.getParameter("id_pedido");
        String tipo = "L";

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("datoestab", datoestab);
        modelo.put("dato", cliente);

        Personas personas = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        modelo.put("personas", personas);
        Consultorios datosconsul = this.mi.getDatosConsultorio(personas.getId_consultorio()); // saca un registro a ser modificado

        Historiales datos = new Historiales();
        datos.setId_persona(cliente.getId_persona());
        datos.setAccion("H");
        List listarPaises = this.mi.getHistorialAtendidosH(datos);
        modelo.put("milista", listarPaises);

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(personas.getId_consultorio()));
        modelo.put("id_paciente", id_paciente);

        if ("imprimeAnverso".equals(accionc) || "imprimeReverso".equals(accionc) || "imprimeHCLcaja".equals(accionc) || "imprimeNotaIntCaja".equals(accionc) || "imprimeHCLCompcaja".equals(accionc) || "imprimeHCLBasica1".equals(accionc) || "imprimeHCLBasica2".equals(accionc) || "imprimeHCLBasica3".equals(accionc)) {
            String id_historial = request.getParameter("id_historial");
            String id_historia = request.getParameter("id_historia");

            if (id_historia == null || "".equals(id_historia)) {
                id_historia = "0";
            }
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setCod_esta(cliente.getCod_esta());////// obrero 11/06/2015
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_historia(Integer.parseInt(id_historia));
            dato.setAccion("E");    ////getDatosEmergencias
            if ("imprimeAnverso".equals(accionc) || "imprimeReverso".equals(accionc) || "imprimeHCLcaja".equals(accionc)) {
                dato.setId_tipo(1);
            }
            if ("imprimeHCLCompcaja".equals(accionc)) {
                dato.setId_tipo(2);
            }
            if ("imprimeNotaIntCaja".equals(accionc)) {
                dato.setId_tipo(3);
            }
            Historiales datosEmerg = this.mi.getDatosEmergencias(dato);    ////getDatosEmergencias
            local.setCod_esta(cliente.getCod_esta());  /////////////falla
            if (datosEmerg != null) {
                local.setCod_esta(datosEmerg.getCod_esta());  /////////////falla            
            }//else{
            // return new ModelAndView("Aviso","mensaje","No hay Dato para Imprimir, genere la historia y guardela primeramente");
            //}
            modelo.put("milistas", datosEmerg);
            local.setArea("E");   ////getDatosEstable        
            Localidades buscarestab = this.mi.getDatosEstable(local);
            modelo.put("datoestab", buscarestab);

            dato.setAccion("H");
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listasBas = this.mi.getListarHistoriaHoy(dato);////// obrero 8/05/2015  getListarHistoriaHoy
            modelo.put("listaBas", listasBas);

            dato.setCod_esta(cliente.getCod_esta());
            Historiales datoh = this.mi.getDatosHistorial(dato);
            Personas persona = this.mi.getBuscarPersona(datoh.getId_persona()); // saca un registro a ser modificado
            modelo.put("persona", persona);

            Recetas datort = new Recetas();
            datort.setId_paciente(Integer.parseInt(id_paciente));
            List listarRecetaTotal = this.mi.getListarRecetasTotal(datort);
            modelo.put("listarRecetaTotal", listarRecetaTotal);

            if (id_pedido == null) {
                id_pedido = "0";
            }
            Cuadernos datol = new Cuadernos();
            datol.setId_cuaderno(Integer.parseInt(id_historial));
            datol.setId_pedido(Integer.parseInt(id_pedido));
            datol.setCod_esta(cliente.getCod_esta());

            datol.setAspecto("M");
            datol.setId_historial(Integer.parseInt(id_historial));
            List listaCie = this.mi.getVerCuaderno1CieMorbi(datol);  ///getVerCuaderno1CieMorbi
            modelo.put("listaCie", listaCie);

            List hemo = this.mi.getHemograma(datol);
            modelo.put("hemo", hemo);

            List orina = this.mi.getOrina(datol);
            modelo.put("orina", orina);
            modelo.put("dato", cliente);

            Cuadernos datoll = new Cuadernos();
            datoll.setId_historial(Integer.parseInt(id_historial));
            datoll.setCod_esta(cliente.getCod_esta());
            List listalab = this.mi.getPacienteLaboratorio(datoll);
            modelo.put("listalab", listalab);

            if ("imprimeNotaIntCaja".equals(accionc)) {
                return new ModelAndView(new HCLNotaInterPDF(), modelo);
            }
            if ("imprimeHCLCompcaja".equals(accionc)) {
                return new ModelAndView(new HCLCompletaPDF(), modelo);
            }
            if ("imprimeHCLBasica1".equals(accionc)) {
                if (cliente.getCod_esta() == 700241) {
                    if (cliente.getId_almacen() == 0) {
                        return new ModelAndView(new HCLBasicaSC2PDF(), modelo);
                    }
                    return new ModelAndView(new HCLBasicaSCPDF(), modelo);
                }
                return new ModelAndView(new HCLBasicaPDF(), modelo);
            }
            if ("imprimeHCLBasica2".equals(accionc)) {
                return new ModelAndView(new HCLBasica2PDF(), modelo);
            }
            if ("imprimeHCLBasica3".equals(accionc)) {
                return new ModelAndView(new HCLBasica3PDF(), modelo);
            }
            if ("imprimeAnverso".equals(accionc)) {
                return new ModelAndView(new HCLHojaAtencionAnversoPDF(), modelo);
            }
            if ("imprimeReverso".equals(accionc)) {
                return new ModelAndView(new HCLHojaAtencionReversoPDF(), modelo);
            }
            return new ModelAndView(new HCLHojaAtencionTPDF(), modelo);
        }

        if ("imprimeHCL".equals(accion)) {
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milistas", listas);
            Recetas datort = new Recetas();
            datort.setId_paciente(Integer.parseInt(id_paciente));
            List listarRecetaTotal = this.mi.getListarRecetasTotal(datort);
            modelo.put("listarRecetaTotal", listarRecetaTotal);

            if ("C".equals(datoestab.getArea())) {
                return new ModelAndView(new ListarHCLCajaPDF(), modelo);
            } else {
                return new ModelAndView(new ListarHCLPDF(), modelo);
            }
        }

        if ("imprimeHCLCara".equals(accion)) {
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milistas", listas);
            Recetas datort = new Recetas();
            datort.setId_paciente(Integer.parseInt(id_paciente));
            List listarRecetaTotal = this.mi.getListarRecetasTotal(datort);
            modelo.put("listarRecetaTotal", listarRecetaTotal);

            return new ModelAndView(new ListarHCLCaraPDF(), modelo);
        }
        return new ModelAndView("administrarhistoriales/HistorialAtendidos", modelo);

    }
}
