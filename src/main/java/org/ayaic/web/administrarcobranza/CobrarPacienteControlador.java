package org.ayaic.web.administrarcobranza;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarPacienteControlador {

    private final MiFacade mi;

    public CobrarPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String accion = request.getParameter("accion");
        String sw = request.getParameter("sw");

        String id_paciente = request.getParameter("id_paciente");
        String nombres = request.getParameter("nombres");
        String id_persona = request.getParameter("id_persona");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");;
        String num_cladoc = request.getParameter("num_cladoc");
        String id_pedido = request.getParameter("id_pedido");
        String nit = request.getParameter("nit");

        Localidades local = new Localidades();
        local.setCod_esta(cliente.getCod_esta());
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("Factura", Integer.toString(datoestab.getId_pais()));
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoest = (Localidades) Estab2.get(0);
        modelo.put("Factura", Integer.toString(datoest.getId_pais()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Pacientes paciente = new Pacientes();
        paciente.setCod_esta(cliente.getCod_esta());
        if ("Nuevo".equals(accion)) {
            num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente));
        }
        if ("Kardex".equals(accion)) {
            Detalle dato = new Detalle();
            dato.setId_pedido(Integer.parseInt(id_paciente));
            dato.setId_carpeta(cliente.getCod_esta());
            dato.setCod_esta(cliente.getCod_esta());
            List listarcobrosKardex = this.mi.getListarDetalle(dato);
            modelo.put("listarcobroskardex", listarcobrosKardex);

            if (listarcobrosKardex.size() == 0) {
                return new ModelAndView("Aviso", "mensaje", "NO TIENE KARDEX");
            }

            List listarcobroSaldo = this.mi.getListarDetalleSaldo(dato);
            modelo.put("listarcobrosaldo", listarcobroSaldo);

            modelo.put("nombres", nombres);
            return new ModelAndView("administrarcobranza/CobrarPacienteKardex", modelo);
        }

        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);

        modelo.put("accion", accion);
        modelo.put("sw", request.getParameter("sw"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("nombres", nombres);
        modelo.put("id_persona", id_persona);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);
        modelo.put("num_cladoc", num_cladoc);
        modelo.put("id_pedido", id_pedido);
        modelo.put("nit", "0");

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        if (!"".equals(buscarPaciente.getCadena3()) && buscarPaciente.getCadena3() != null) {
            modelo.put("nit", buscarPaciente.getCadena3());
            modelo.put("nombres", buscarPaciente.getCadena2());
        } else {
            if (!"".equals(buscarPaciente.getCarnet()) || buscarPaciente.getCarnet() != null) {
                modelo.put("nit", buscarPaciente.getCarnet());
            }
        }

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        if (xanio < 1901) {
            return new ModelAndView("Aviso", "mensaje", "El año de nacimiento no es correcto");
        }

        // pacientes a cobrar enviados por recepcion
        Historiales datoh = new Historiales();
        datoh.setId_estado("P");
        List listarPaises = this.mi.getListarCobroReserva(datoh);
        modelo.put("milista", listarPaises);

        // pacientes a cobrar enviados por odontologia
        //Pacientes paciente= new Pacientes() ;
        paciente.setId_estado("A");
        paciente.setId_rubro(3);
        paciente.setId_localidad(cliente.getCod_esta());
        List listarOdon = this.mi.getListarCobroRubroOdon(paciente);
        modelo.put("listaOdon", listarOdon);

        // pacientes a cobrar enviados por odontologia
        paciente.setId_rubro(5);
        List listarenfer = this.mi.getListarCobroRubro(paciente);
        modelo.put("listaEnfer", listarenfer);

        return new ModelAndView("administrarcobranza/CobrarPaciente", modelo);
    }
}
