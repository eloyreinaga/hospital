package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCptControlador {

    private final MiFacade mi;

    public ListarCptControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCpt.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq - 1)), (Integer.toString(anioq - 2)), (Integer.toString(anioq - 3)), (Integer.toString(anioq - 4)), (Integer.toString(anioq - 5))};

        String _id_usuario = Integer.toString(cliente.getId_usuario());
        String accion = request.getParameter("accion");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoper = (Localidades) Estab1.get(0);
        Personas buscarEmpleado = this.mi.getDatosPersona(datoper.getId_persona());
        modelo.put("datosJMed", buscarEmpleado);
        modelo.put("localidades", datoper);
        modelo.put("anios", anios);

        if ("Mostrar Todo".equals(accion) || "SoloPositivos".equals(accion) || "Psicotropicos".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");
            modelo.put("dato", cliente);

            Medicamentos datos = new Medicamentos();
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));
            datos.setCod_esta(cliente.getCod_esta());
            datos.setId_farmacia(cliente.getId_farmacia());
            List listarDatosImm = this.mi.getListarActCpt(datos);
            modelo.put("listarDatosImm", listarDatosImm);
            if ("SoloPositivos".equals(accion)) {
                datos.setExpedido("P");
                List listarDatosImm2 = this.mi.getListarActCptII(datos);
                modelo.put("listarDatosImm", listarDatosImm2);
            }
            if ("Psicotropicos".equals(accion)) {
                datos.setExpedido("S");  ///getListarCptPsico
                List listarDatosImm2 = this.mi.getListarCptPsico(datos);
                modelo.put("listarDatosImm", listarDatosImm2);
            }

            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            if ("SoloPositivos".equals(accion)) {
                return new ModelAndView(new ListarConsultaCptIIPDF(), modelo);
            }
            if ("Psicotropicos".equals(accion)) {
                return new ModelAndView(new ListarConsultaPsicoPDF(), modelo);
            }

            return new ModelAndView(new ListarConsultaCptPDF(), modelo);
        }

        modelo.put("gestion", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", "1");

        return new ModelAndView("administrarfarmacias/ListarCpt", modelo);

    }
}
