package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarMedicamentosAlmaControlador {

    private final MiFacade mi;

    public ListarMedicamentosAlmaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarMedicamentosAlma.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String nombres = request.getParameter("nombres");
        String accion = request.getParameter("accion");
        modelo.put("nombres", nombres);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        datofar.setId_persona(cliente.getId_persona());
        datofar.setTipo("A");
        datofar.setId_estado("%");
        List listarFarmaciaAsig = this.mi.getListarFarmaciasAsig(datofar); // getListarFarmaciasAsig
        modelo.put("listafarAsig", listarFarmaciaAsig);
        modelo.put("dato", cliente);

        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_persona(cliente.getId_persona());
        dato.setId_farmacia(cliente.getId_farmacia());

        if ("Buscar".equals(accion) || "Imprimir".equals(accion)) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            dato.setMedicamento(nombres);
            dato.setExpedido("A");    ////
            dato.setId_farmacia(cliente.getId_farmacia());
            List listarMedicamentos = this.mi.getListarMedicamentosAlma(dato);///getListarMedicamentosAlma todos los medicamentos de cada farmacia
            modelo.put("listarMedicamentos", listarMedicamentos);
            dato.setExpedido("G");    ////
            List listarMedicamentosGral = this.mi.getListarMedicamentosAlmaGral(dato);///getListarMedicamentosAlmaGral
            modelo.put("listarMedicamentosGral", listarMedicamentosGral);
            if ("Imprimir".equals(accion)) {
                Personas buscarEmpleado = this.mi.getDatosPersona(cliente.getId_persona());
                modelo.put("persona", buscarEmpleado);
                return new ModelAndView(new VerInventarioAlmaGralPDF(), modelo);
            }
        }

        if ("Actualiza".equals(accion)) {
            List Med = this.mi.getActualizarMedicamentos(dato); ///getActualizarMedicamentos
            return new ModelAndView("Aviso", "mensaje", "Los datos se actualizaron correctamente");
        }

        if ("ActualizarMed".equals(accion)) {
            String id_medicamento = request.getParameter("id_medicamento");

            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setExpedido("M");    /////getActualizarMedicamentos_med
            List Med = this.mi.getActualizarMedicamentos_med(dato);
            return new ModelAndView("Aviso", "mensaje", "El medicamento : " + id_medicamento + ", se actualizo correctamente");
        }
        return new ModelAndView("administrarmedicamentos/ListarMedicamentosAlma", modelo);

    }
}
