package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarSnis301Controlador {

    private final MiFacade mi;

    public ListarSnis301Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ReporteSnis301.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sAccion = request.getParameter("boton");

        if ("Modificar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");
            modelo.put("dato", cliente);

            Cuadernos datos = new Cuadernos();
            datos.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));

            this.mi.setRegistrarSnis301(datos);
            //recuperar informacion

            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            // recupera datos de consulta externa
            Cuadernos datoexterna;
            datos.setId_cuaderno(1);
            datos.setTipoconsulta("N");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt3", datoexterna);
            datos.setId_cuaderno(1);
            datos.setTipoconsulta("R");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt4", datoexterna);
            datos.setId_cuaderno(2);
            datos.setTipoconsulta("N");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt1", datoexterna);
            datos.setId_cuaderno(2);
            datos.setTipoconsulta("R");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt2", datoexterna);

            // datos de odontologia    
            Cuadernos datoOdon = this.mi.getSnis301Odontologia(datos);
            modelo.put("datoOdon", datoOdon);

            // datos enfermeria
            Cuadernos datovacu = this.mi.getSnis301Enfermeria(datos);
            modelo.put("datoEnfer", datovacu);

            // datos de nutricion infantil    
            Cuadernos datoNutri = this.mi.getSnis301Nutricion(datos);
            modelo.put("datoNutri", datoNutri);

            //datos de control prenatales
            datos.setTipoconsulta("A");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoControlPre", datoexterna);
            //datos  partos en servicio
            datos.setTipoconsulta("B");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoPartoServ", datoexterna);
            //datos  partos en dimicilio
            datos.setTipoconsulta("C");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoPartoDomi", datoexterna);

            //datos de prevencion
            Cuadernos datoprevencion;
            datos.setTipoconsulta("N");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoNPrev", datoprevencion);

            datos.setTipoconsulta("C");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoCPrev", datoprevencion);

            datos.setTipoconsulta("U");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoUPrev", datoprevencion);

            //datos vacunas menor 1 año
            datos.setTipoconsulta("P");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac1", datovacu);
            datos.setTipoconsulta("S");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac2", datovacu);
            datos.setTipoconsulta("T");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac3", datovacu);
            datos.setTipoconsulta("C");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac4", datovacu);

            return new ModelAndView(new VerReporteSnis301PDF(), modelo);
        }

        if ("Mostrar".equals(accion)) {
            String mes = request.getParameter("mes");
            String gestion = request.getParameter("gestion");
            modelo.put("dato", cliente);

            Cuadernos datos = new Cuadernos();
            datos.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datos.setMes(Integer.parseInt(mes));
            datos.setGestion(Integer.parseInt(gestion));

            //recuperar informacion
            modelo.put("gestion", gestion);
            modelo.put("mes", mes);

            // recupera datos de consulta externa
            Cuadernos datoexterna;
            datos.setId_cuaderno(1);
            datos.setTipoconsulta("N");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt3", datoexterna);
            datos.setId_cuaderno(1);
            datos.setTipoconsulta("R");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt4", datoexterna);
            datos.setId_cuaderno(2);
            datos.setTipoconsulta("N");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt1", datoexterna);
            datos.setId_cuaderno(2);
            datos.setTipoconsulta("R");
            datoexterna = this.mi.getSnis301Externa(datos);
            modelo.put("datoExt2", datoexterna);

            // datos de odontologia    
            Cuadernos datoOdon = this.mi.getSnis301Odontologia(datos);
            modelo.put("datoOdon", datoOdon);

            // datos enfermeria
            Cuadernos datovacu = this.mi.getSnis301Enfermeria(datos);
            modelo.put("datoEnfer", datovacu);

            // datos de nutricion infantil    
            Cuadernos datoNutri = this.mi.getSnis301Nutricion(datos);
            modelo.put("datoNutri", datoNutri);

            //datos de control prenatales
            datos.setTipoconsulta("A");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoControlPre", datoexterna);
            //datos  partos en servicio
            datos.setTipoconsulta("B");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoPartoServ", datoexterna);
            //datos  partos en dimicilio
            datos.setTipoconsulta("C");
            datoexterna = this.mi.getSnis301ControlPre(datos);
            modelo.put("datoPartoDomi", datoexterna);

            //datos de prevencion
            Cuadernos datoprevencion;
            datos.setTipoconsulta("N");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoNPrev", datoprevencion);

            datos.setTipoconsulta("C");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoCPrev", datoprevencion);

            datos.setTipoconsulta("U");
            datoprevencion = this.mi.getSnis301Prevencion(datos);
            modelo.put("datoUPrev", datoprevencion);

            //datos vacunas menor 1 año
            datos.setTipoconsulta("P");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac1", datovacu);
            datos.setTipoconsulta("S");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac2", datovacu);
            datos.setTipoconsulta("T");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac3", datovacu);
            datos.setTipoconsulta("C");
            datovacu = this.mi.getSnis301Vacunas(datos);
            modelo.put("datoVac4", datovacu);

            return new ModelAndView(new VerReporteSnis301PDF(), modelo);
        }

        if ("Adicionar".equals(accion)) {

            List listarImm = this.mi.getListarFaltaSnis301();
            modelo.put("listarImm", listarImm);
            modelo.put("accion", accion);
            return new ModelAndView("administrarcuadernos/ListarSnis301", modelo);
        }

        List listarImm = this.mi.getListarSnis301();
        modelo.put("listarImm", listarImm);
        modelo.put("accion", "Mostrar");

        return new ModelAndView("administrarcuadernos/ListarSnis301", modelo);

    }
}
