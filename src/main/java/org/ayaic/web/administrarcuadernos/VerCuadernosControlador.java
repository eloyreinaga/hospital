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
public class VerCuadernosControlador {

    private final MiFacade mi;

    public VerCuadernosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/VerCuadernos.do")
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

        if ("Cuadernos".equals(sAccion) || "C-2.Consulta Externa".equals(sAccion)) {
            String sFecha_ini = request.getParameter("valor_1");
            String sFecha_fin = request.getParameter("valor_2");
            if (("".equals(sFecha_ini)) || ("".equals(sFecha_fin))) {
                return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
            } else {
                String[] sFechaini = sFecha_ini.split("-");
                int iAnio1 = Integer.parseInt(sFechaini[0]) - 1900;
                int iMes1 = Integer.parseInt(sFechaini[1]) - 1;
                int iDia1 = Integer.parseInt(sFechaini[2]);

                String[] sFechafin = sFecha_fin.split("-");
                int iAnio2 = Integer.parseInt(sFechafin[0]) - 1900;
                int iMes2 = Integer.parseInt(sFechafin[1]) - 1;
                int iDia2 = Integer.parseInt(sFechafin[2]);

                Date dFechaini1 = new Date(iAnio1, iMes1, iDia1);
                Date dFechafin1 = new Date(iAnio2, iMes2, iDia2);

                modelo.put("dato", cliente);

                List listarSeguros = this.mi.getListarSeguros("%");
                modelo.put("listaPacAfi", listarSeguros);
                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                List confvariables = this.mi.getCuadernosImpresion(dato);
                modelo.put("confvariables", confvariables);
                dato.setId_persona(cliente.getId_persona());
                dato.setFecha_ini(dFechaini1);
                dato.setFecha_fin(dFechafin1);
                dato.setTipo("T");

                List listaCie = this.mi.getVerCuaderno1Cie(dato);  ///datos del CIE10 
                modelo.put("listaCie", listaCie);
                List listaCobrosq = this.mi.getVerCuaderno1(dato);
                modelo.put("listaCuadernos", listaCobrosq);
                if ("Cuadernos".equals(sAccion)) {
                    List listaCobros = this.mi.getVerCuaderno1(dato);
                    modelo.put("listaCuadernos", listaCobros);
                    return new ModelAndView(new VerCuadernosPDF(), modelo);
                }
            }
        }
        return new ModelAndView("administrarcuadernos/ListarLibros", modelo);
    }
}
