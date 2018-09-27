package org.ayaic.web.administrarsumi;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModificarPaqueteControlador {

    private final MiFacade mi;

    public ModificarPaqueteControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ModificarPaquete.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String nombres = request.getParameter("nombres");
        String nombremed = request.getParameter("nombremed");
        String id_medicamento = request.getParameter("id_medicamento");
        String cantidad = request.getParameter("cantidad");
        String utiliza = request.getParameter("utiliza");
        String id_prestacion = request.getParameter("id_prestacion");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");

        Prestaciones buscarPres = this.mi.getDatosPrestacion(Integer.parseInt(id_prestacion));
        modelo.put("datos", buscarPres);
        modelo.put("aaa", "bbb");

        if ("adicion".equals(accion)) {
            Recetas dato = new Recetas();
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setEntrada(Double.parseDouble(cantidad));
            dato.setSalida(Double.parseDouble(utiliza));
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearRecetaPaque(dato);
        }

        if ("eliminar".equals(accion)) {
            Recetas dato = new Recetas();
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            this.mi.setEliminarRecetaPaque(dato);
        }

        if ("adicionPres".equals(accion)) {
            String prestacion = request.getParameter("prestacion");

            Prestaciones dato = new Prestaciones();
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setPrestacion(prestacion);
            dato.setId_grupo(buscarPres.getId_grupo());

            this.mi.setCrearNivelPaque(dato);
        }

        if ("eliminarPres".equals(accion)) {
            String prestacion = request.getParameter("prestacion");

            Prestaciones dato = new Prestaciones();
            dato.setId_prestacion(Integer.parseInt(id_prestacion));
            dato.setPrestacion(prestacion);

            this.mi.setEliminarNivelPaque(dato);
        }

        if ("modificar".equals(accion)) {
            String id_paquete = request.getParameter("id_paquete");
            String entrada = request.getParameter("entrada");
            Recetas dato = new Recetas();
            dato.setId_receta(Integer.parseInt(id_paquete));
            dato.setSalida(Integer.parseInt(entrada));
            modelo.put("aaa", "aaa");
            modelo.put("id_paquete", id_paquete);
            this.mi.setModificarRecetaPaquete(dato);
        }

        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        List listarFarmacia = this.mi.getListarFarmacias(datofar);
        //modelo.put("listarFarmacia", listarFarmacia);

        Recetas datomed = new Recetas();
        datomed.setCod_esta(cliente.getCod_esta());
        datomed.setId_prestacion(Integer.parseInt(id_prestacion));
        datomed.setId_farmacia(cliente.getId_farmacia());
        if (cliente.getId_farmacia() == 0 && listarFarmacia.size() > 0) {///10-12-2015 no funciona id_farmacia=0 SI para varias farmacias y sistema esta 
            Farmacias datofarm = (Farmacias) listarFarmacia.get(0);
            datomed.setId_farmacia(datofarm.getId_farmacia());
        }
        datomed.setCod_esta(cliente.getCod_esta());

        List listarRecetas = this.mi.getListarMedPaquete(datomed);
        modelo.put("listarRecetas", listarRecetas);
        double sum = 0;
        for (int j = 0; j < listarRecetas.size(); j++) {
            Recetas datoc = (Recetas) listarRecetas.get(j);
            sum = sum + datoc.getSalida() * datoc.getEntrada();

        }
        modelo.put("suma", Double.toString(sum));

        List listarNivel = this.mi.getListarNivelPrestacion(Integer.parseInt(id_prestacion));
        modelo.put("listarNivel", listarNivel);
        if ("".equals(nombres) && nombremed == null) {
            nombres = " ";
        }
        if ("".equals(nombremed) && nombres == null) {
            nombres = " ";
        }
        if (("".equals(nombres) || nombres == null) && !"".equals(nombremed)) {
            nombres = nombremed;
        }
        if (nombres == null) {
            nombres = " ";
        }
        nombres = nombres.trim();
        nombres = nombres.replaceAll(" +", " ");
        modelo.put("nombremed", nombres);
        modelo.put("nombres", nombres);
        nombres = nombres.replaceAll("\\s", ":*&");
        nombres = nombres.replaceAll("ñ", "n");
        nombres = nombres.replaceAll("Ñ", "N");
        if (nombres.length() > 0) {
            nombres = nombres + ":*";
        }

        //nombres = nombres.replaceAll("\\s", "%");
        //nombremed = nombres; 
        //nombres = "%" + nombres + "%"; 
        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setCadena10("medicamento");
        dato.setId_farmacia(cliente.getId_farmacia());
        //if(cliente.getId_farmacia()==0 && listarFarmacia.size()>0){///10-12-2015 no funciona id_farmacia=0 SI para varias farmacias 
        //   Farmacias datofarm = (Farmacias) listarFarmacia.get(0); 
        //   dato.setId_farmacia(datofarm.getId_farmacia()); 
        //}   
        dato.setMedicamento(nombres);
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);

        modelo.put("id_prestacion", id_prestacion);
        //modelo.put("nombres",nombremed);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);

        return new ModelAndView("administrarsumi/ModificarPaquete", modelo);
    }
}
