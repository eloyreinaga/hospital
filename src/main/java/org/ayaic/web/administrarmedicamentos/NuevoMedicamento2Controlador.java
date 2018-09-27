package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoMedicamento2Controlador {

    private final MiFacade mi;

    public NuevoMedicamento2Controlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoMedicamento2.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_medicamento = request.getParameter("id_medicamento");
        String recargado = request.getParameter("recargado");
        String id_grupo = request.getParameter("id_grupo");
        modelo.put("id_medicamento", request.getParameter("id_medicamento"));
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq), Integer.toString(anioq + 1), Integer.toString(anioq + 2), Integer.toString(anioq + 3), Integer.toString(anioq + 4), Integer.toString(anioq + 5), Integer.toString(anioq + 6), Integer.toString(anioq + 7), Integer.toString(anioq + 8), Integer.toString(anioq + 9)};

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
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
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);

        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());
        dato.setId_persona(cliente.getId_persona());
        List listarGrupo = this.mi.getListarGrupos(dato);  ///getListarGrupos
        modelo.put("listarGrupo", listarGrupo);
        dato.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(dato);
        modelo.put("datoItem", datoItem);
        modelo.put("id_grupo", id_grupo);
        if (!"".equals(id_grupo) && id_grupo != null && "Adicionar".equals(accion)) {
            dato.setExpedido("2");    ///getListarSubGrupos2
            dato.setId_medicamento(Integer.parseInt(id_grupo));
            List listarSGrupo = this.mi.getListarSubGrupos2(dato);  ///getListarSubGrupos2
            modelo.put("listarSGrupo", listarSGrupo);
            modelo.put("id_grupo", id_grupo);
            //return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo); 
        }

        //dato.setExpedido("S");
        //List listarSGrupo = this.mi.getListarGrupos(dato);  ///getListarGrupos
        //modelo.put("listarSGrupo", listarSGrupo); 
        dato.setExpedido("P");  ///getListarPartidas
        List listarPartida = this.mi.getListarPartidas(dato);  ///getListarPartidas
        modelo.put("listarPartida", listarPartida);

        //Para la primera vez que entra a la pagina
        if ((id_medicamento != null) && ("Modificar".equals(accion)) && (recargado != null)) {
            //Sacamos los datos del medicamento
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            modelo.put("buscarMedicamento", buscarMedicamento);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);
            modelo.put("sw", request.getParameter("sw"));

            modelo.put("anio", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
            modelo.put("mes", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
            modelo.put("dia", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));

        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarmedicamentos/NuevoMedicamento", modelo);
    }
}
