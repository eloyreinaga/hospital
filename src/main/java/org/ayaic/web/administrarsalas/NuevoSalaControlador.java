package org.ayaic.web.administrarsalas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoSalaControlador {

    private final MiFacade mi;

    public NuevoSalaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoSala.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_sala = request.getParameter("id_sala");

        if ("Ocupadas".equals(accion)) {
            Salas dsala = new Salas();
            dsala.setId_piso(0);
            List listarSalas = this.mi.getListarSalasLibres(dsala);
            //List listarSalas = this.mi.getListarSalasLibres();
            modelo.put("listarSalas", listarSalas);
            Camas listcama = new Camas();
            List listarCamasSala = this.mi.getListarCamasSala(listcama);
            //List listarCamasSala = this.mi.getListarCamasSala();
            modelo.put("listarCamas", listarCamasSala);
            Historiales dato = new Historiales();
            dato.setCod_esta(cliente.getCod_esta());
            List listarI = this.mi.getInternados(dato);
            modelo.put("milistaI", listarI);

            int mul[][] = new int[10][10];
            int num = 0;
            int ancho = 0;
            ancho = (int) (100 / listarSalas.size());
            for (int i = 0; i < listarSalas.size(); i++) {
                Salas sal = (Salas) listarSalas.get(i);
                Camas lcama = new Camas();
                lcama.setId_sala(i);
                lcama.setTipo("T");
                List listarCamas = this.mi.getListarCamasTotal(lcama);
                for (int j = 0; j < listarCamas.size(); j++) {
                    Camas cama = (Camas) listarCamas.get(j);
                    //mul[j][i]={[cama.getId_cama()],[sal.getId_sala()]};
                }
                modelo.put("sala", sal.getSala());
            }
            modelo.put("num", Integer.toString(num));
            modelo.put("ancho", Integer.toString(ancho));
            modelo.put("accion", accion);

            return new ModelAndView("administrarsalas/ListarOcupadas", modelo);
        }

        modelo.put("id_sala", request.getParameter("id_sala"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_sala") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del sala
            Salas buscarsala = this.mi.getDatosSala(Integer.parseInt(id_sala)); // saca un registro a ser modificado
            modelo.put("buscarsala", buscarsala);
            modelo.put("id_estado", buscarsala.getId_estado());
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarsalas/NuevoSala", modelo);
    }
}
