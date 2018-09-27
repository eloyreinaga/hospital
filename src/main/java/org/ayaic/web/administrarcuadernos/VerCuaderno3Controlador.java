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
public class VerCuaderno3Controlador {

    private final MiFacade mi;

    public VerCuaderno3Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuaderno3.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        modelo.put("tipo_medico", Integer.toString(datosconsul.getId_cargo()));
        //Recuperamos variables del jsp
        String sId_estado = request.getParameter("id_estado");
        String sAccion = request.getParameter("accion");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);

        if ("Cuaderno No 3".equals(sAccion) || "SNIS Consultas Anticoncepcion".equals(sAccion)) {
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

                Cuadernos dato = new Cuadernos();
                dato.setId_persona(cliente.getId_persona());
                dato.setCod_esta(cliente.getCod_esta());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("U");
                modelo.put("dFechafin1", anioi + "-" + mesi + "-" + diai + " " + horai + ":" + minutoi);
                modelo.put("persona", persona);
                List listaCobros = this.mi.getVerCuaderno3(dato);
                modelo.put("listaCobros", listaCobros);
                if (datosconsul.getId_cargo() == 7) {
                    List listaCobros2 = this.mi.getVerCuaderno3Todos(dato);
                    modelo.put("listaCobros", listaCobros2);
                }

                if ("SNIS Consultas Anticoncepcion".equals(sAccion)) {
                    dato.setTipo("P");
                    List listaProd1 = this.mi.getVerCuaderno3Prod1(dato);   ////getVerCuaderno3Prod1
                    modelo.put("listaProd1", listaProd1);
                    dato.setTipo("Q");
                    List listaProd2 = this.mi.getVerCuaderno3Prod2(dato);    /////getVerCuaderno3Prod2
                    modelo.put("listaProd2", listaProd2);
                    return new ModelAndView(new VerConsultaAnticonPDF(), modelo);
                }
                return new ModelAndView(new VerCuaderno3PDF(), modelo);
            }
        }
        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
}
