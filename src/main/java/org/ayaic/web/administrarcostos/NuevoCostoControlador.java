package org.ayaic.web.administrarcostos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoCostoControlador {

    private final MiFacade mi;

    public NuevoCostoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoCosto.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_costo = request.getParameter("id_costo");
        String id_rubro = request.getParameter("id_rubro");
        String id_lab = request.getParameter("id_laboratorio");
        String rubro = request.getParameter("rubro");
        String normales = request.getParameter("normales");
        String[] muestra = {"No definida", "Sangre", "Orina", "Heces", "Secresion Bucal", "Secresion Vaginal"};
        String[] seguro = {"A", "S"};

        modelo.put("id_costo", request.getParameter("id_costo"));
        modelo.put("id_lab", request.getParameter("id_lab"));
        modelo.put("rubro", rubro);
        modelo.put("normales", normales);
        modelo.put("id_rubro", request.getParameter("id_rubro"));
        modelo.put("listarmuestra", muestra);
        modelo.put("lseguro", seguro);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_urgencias", "0");
        modelo.put("id_color", "0");
        modelo.put("id_prestacion", "0");
        Costos costo = new Costos();
        costo.setCosto_unit(0);
        modelo.put("buscarCosto", costo);

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_costo") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del costo
            ///Costos costo= new Costos() ;
            costo.setId_costo(Integer.parseInt(id_costo));
            costo.setCod_esta(cliente.getCod_esta());
            Costos buscarCosto = this.mi.getDatosCosto(costo);
            modelo.put("buscarCosto", buscarCosto);
            modelo.put("id_urgencias", Integer.toString(buscarCosto.getEmergencias()));
            modelo.put("id_prestacion", Integer.toString(buscarCosto.getId_prestacion()));
            modelo.put("id_color", Integer.toString(buscarCosto.getId_nivel()));
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        modelo.put("id_lab", id_rubro);
        Laboratorios pai = new Laboratorios();
        pai.setId_estado("A");
        List listarLaboratorios = this.mi.getListarLaboratorios(pai);
        modelo.put("listarLaboratorios", listarLaboratorios);

        Costos datocosto = new Costos();
        datocosto.setCod_esta(cliente.getCod_esta());
        List listarCostosRubro = this.mi.getListarCostosRubro(datocosto);
        modelo.put("listarCostosRubro", listarCostosRubro);
        
        Prestaciones datopres = new Prestaciones();
        datopres.setCod_esta(cliente.getCod_esta());
        datopres.setDescripcion("PL%");
        List listPrestacion = this.mi.getListarPrestaciones(datopres);
        modelo.put("listarPrestaciones", listPrestacion);

        return new ModelAndView("administrarcostos/NuevoCosto", modelo);
    }
}
