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
public class AsignarMedicamentosControlador {

    private final MiFacade mi;

    public AsignarMedicamentosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/AsignarMedicamentos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String nombremed = request.getParameter("nombre");
        String accion = request.getParameter("accion");
        String id_medicamento = request.getParameter("id_medicamento");
        modelo.put("id_medicamento", request.getParameter("id_medicamento"));
        Date fecha1 = new Date();

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));

        if (nombremed == null) {
            nombremed = "%";
        }
        nombremed = "%" + nombremed + "%";
        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_persona(cliente.getId_persona());
        dato.setId_farmacia(cliente.getId_farmacia());
        dato.setMedicamento(nombremed);
        dato.setExpedido("S");
        List listarMedicamentos = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentos", listarMedicamentos);
        dato.setExpedido("W");
        List listarMedicamentosT = this.mi.getListarMedicamentos(dato);
        modelo.put("listarMedicamentosT", listarMedicamentosT);

        if ("adicion".equals(accion)) {
            String medicamento = request.getParameter("medicamento");
            Medicamentos adiciona = new Medicamentos();
            adiciona.setCod_esta(cliente.getCod_esta());
            adiciona.setId_persona(cliente.getId_persona());
            adiciona.setId_farmacia(cliente.getId_farmacia());
            adiciona.setId_medicamento(Integer.parseInt(id_medicamento));
            adiciona.setExpedido("S");
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(adiciona);
            if (buscarMedicamento != null) {///Busca para ver si ya fue selccionado el medicamento
                return new ModelAndView("Aviso", "mensaje", "Este Medicamento ya fue seleccionado en este usuario");
            } else {
                try {
                    this.mi.setCrearMedicamento(adiciona);
                    ///
                    List listarMedicamentos2 = this.mi.getListarMedicamentos(adiciona);
                    modelo.put("listarMedicamentos", listarMedicamentos2);
                    adiciona.setExpedido("W");
                    List listarMedicamentosT2 = this.mi.getListarMedicamentos(adiciona);
                    modelo.put("listarMedicamentosT", listarMedicamentosT2);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
            }
        }

        if ("eliminar".equals(accion)) {
            String medicamento = request.getParameter("medicamento");
            Medicamentos elimina = new Medicamentos();
            elimina.setId_medicamento(Integer.parseInt(id_medicamento));
            elimina.setCod_esta(cliente.getCod_esta());
            elimina.setId_persona(cliente.getId_persona());
            elimina.setExpedido("S");
            try {
                this.mi.setEliminarMedicamento(elimina);
                elimina.setMedicamento(medicamento);
                List listarMedicamentos2 = this.mi.getListarMedicamentos(elimina);
                modelo.put("listarMedicamentos", listarMedicamentos2);
                elimina.setExpedido("W");
                List listarMedicamentosT2 = this.mi.getListarMedicamentos(elimina);
                modelo.put("listarMedicamentosT", listarMedicamentosT2);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar Consulte con el administrador");
            }
        }

        modelo.put("accion", accion);
        return new ModelAndView("administrarmedicamentos/AsignarMedicamentos", modelo);
    }
}
