package org.ayaic.web.administrarcarpetas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarCarpetaControlador {

    private final MiFacade mi;

    public GrabarCarpetaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCarpeta.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String id_carpeta = request.getParameter("id_carpeta");
        String id_carpeta2 = request.getParameter("id_carpeta2");
        String id_paciente = request.getParameter("id_paciente");
        String id_pacientej = request.getParameter("id_pacientej");

        //Guardar    
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String carpeta = request.getParameter("carpeta");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {

            Carpetas datocarpeta = new Carpetas();
            datocarpeta.setId_paciente(Integer.parseInt(id_pacientej));
            datocarpeta.setCarpeta(carpeta);
            //creamos la carpeta
            this.mi.setCrearCarpeta(datocarpeta);
            //recuperamos el id_carpeta 
            Carpetas buscarCarpeta = this.mi.getDatosCarpetaPac(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            //agregamos al paciente a la carpeta
            datocarpeta.setId_carpeta(buscarCarpeta.getId_carpeta());
            datocarpeta.setId_tipo_parentesco(1);
            datocarpeta.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPacienteD(datocarpeta);
            //vamos a la pantalla de agregar dependiente
            modelo.put("id_pacientej", id_pacientej);
            modelo.put("id_carpeta", Integer.toString(buscarCarpeta.getId_carpeta()));

            // averigua quien es el jefe
            Carpetas bCarpeta = this.mi.getDatosCarpeta(buscarCarpeta.getId_carpeta()); // saca un registro a ser modificado
            modelo.put("Carpeta", bCarpeta);

            Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);
            //datos de los dependientes
            List listarP = this.mi.getListarPacientesD(buscarCarpeta.getId_carpeta());
            modelo.put("listaPacientesD", listarP);

            return new ModelAndView("administrarcarpetas/ListarPacientesD", modelo);
        }
        if ("ModificarCarpeta".equals(accion)) {

            Carpetas buscarCarpeta = new Carpetas();
            buscarCarpeta.setId_paciente(Integer.parseInt(id_carpeta2));
            buscarCarpeta.setId_carpeta(Integer.parseInt(id_carpeta));
            buscarCarpeta.setCarpeta(carpeta);
            buscarCarpeta.setId_estado("A");
            this.mi.setModificarCarpeta(buscarCarpeta);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_carpeta"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Carpetas elimina = new Carpetas();
            elimina.setId_carpeta(Integer.parseInt(request.getParameter("id_carpeta")));

            try {
                this.mi.setEliminarCarpeta(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }
            //listar categorias
            elimina.setNombres("A%");
            List listarCarpetas = this.mi.getListarCarpetas(elimina);
            modelo.put("listarCarpetas", listarCarpetas);

            return new ModelAndView("administrarcarpetas/ListarCarpetas", modelo);
        }
        return new ModelAndView("administrarcarpetas/ListarCarpetas", modelo);
    }
}
