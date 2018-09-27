package org.ayaic.web.administrarhistoriales;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCobroRubroControlador {

    private final MiFacade mi;

    public ListarCobroRubroControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCobroRubro.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String sw = request.getParameter("sw");

        String id_persona = request.getParameter("id_persona");
        String id_rubro = request.getParameter("id_rubro");
        String nombres = request.getParameter("nombres");
        String id_costo = request.getParameter("id_costo");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String tipo_medico = request.getParameter("tipo_medico");
        String id_consultorio = request.getParameter("id_consultorio");
        String expedido = request.getParameter("expedido");
        Date fecha1 = new Date();
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado

        Detalle datod = new Detalle();
        datod.setId_pedido(Integer.parseInt(id_paciente));
        datod.setId_carpeta(cliente.getCod_esta());
        datod.setCod_esta(cliente.getCod_esta());
        List listarcobrosKardex1 = this.mi.getListarDetalle(datod);
        modelo.put("listarcobroskardex1", listarcobrosKardex1);

        if (listarcobrosKardex1.size() == 0) {
            return new ModelAndView("Aviso", "mensaje", "NO TIENE KARDEX DE PAGOS");
        }

        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);
        if (request.getParameter("id_rubro") != null) {
            Costos datoq = new Costos();
            datoq.setId_rubro(Integer.parseInt(id_rubro));
            datoq.setId_prestacion(Integer.parseInt(id_rubro));
            datoq.setId_estado("%");
            datoq.setTipo(0);
            datoq.setId_persona(5000);
            datoq.setEmergencias(0);
            datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017
            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            if (request.getParameter("id_costo") != null) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos buscarCosto = this.mi.getDatosCosto(costo);
                if (buscarCosto != null) {
                    modelo.put("precio", Double.toString(buscarCosto.getCosto_unit()));
                } else {
                    modelo.put("precio", "0");
                }
            }
        }

        modelo.put("accion", accion);
        modelo.put("sw", request.getParameter("sw"));
        modelo.put("id_persona", id_persona);
        modelo.put("id_paciente", id_paciente);
        modelo.put("nombres", nombres);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);

        if ("Eliminar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");

            Pacientes datoe = new Pacientes();
            datoe.setId_pedido(Integer.parseInt(id_pedido));
            datoe.setId_persona(cliente.getId_persona());
            datoe.setCod_esta(cliente.getCod_esta());
            datoe.setCadena1(ip);
            datoe.setCadena2(host);
            this.mi.setEliminarPedido(datoe);
        }
        if ("Agregar".equals(accion)) {
            // almacenar el monto de cobro
            String costo_unit = request.getParameter("precio");

            Pacientes dato = new Pacientes();
            dato.setNombres(nombres);
            dato.setPrecio_total(Float.parseFloat(costo_unit));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_reservacion));
            dato.setId_estado("A");
            dato.setNum_cladoc(Long.toString(this.mi.getNumClaDoc(dato)));
            dato.setNit("");
            //dato.setId_costo(Integer.parseInt(id_costo)) ;
            dato.setId_rubro(Integer.parseInt(id_rubro));
            dato.setId_dispensa(Integer.parseInt(id_persona));
            dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            dato.setTipo("C");
            dato.setFec_registro(fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(dato);
        }
        if ("Terminar".equals(accion)) {
            Pacientes buscarPac = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            String id_sexo_e = Integer.toString(buscarPac.getId_tipo_sexo());          //Sacar id_sexo

            Date fecha_nac = buscarPac.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }

        if ("Consultar".equals(accion)) {
            String id_pedido = request.getParameter("id_pedido");
            Pacientes paciente1 = new Pacientes();
            paciente1.setId_pedido(Integer.parseInt(id_pedido));
            paciente1.setCod_esta(cliente.getCod_esta());
            Pacientes paciente = this.mi.getDatosPedido(paciente1);
            paciente.setId_estado("E");
            this.mi.setModificarPedido(paciente);////setModificarPedidoFactura

            Pacientes buscarPac = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datos", buscarPaciente);

            String id_sexo_e = Integer.toString(buscarPac.getId_tipo_sexo());          //Sacar id_sexo

            Date fecha_nac = buscarPac.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            modelo.put("nombres", nombres);
            Detalle datod2 = new Detalle();
            datod2.setId_pedido(Integer.parseInt(id_paciente));
            datod2.setId_carpeta(cliente.getCod_esta());
            datod2.setCod_esta(cliente.getCod_esta());
            List listarcobrosKardex = this.mi.getListarDetalle(datod2);
            modelo.put("listarcobroskardex", listarcobrosKardex);

            List listarcobroSaldo = this.mi.getListarDetalleSaldo(datod2);
            modelo.put("listarcobrosaldo", listarcobroSaldo);
            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            String a = "/";
            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
        }

        modelo.put("nombres", nombres);
        Detalle datod2 = new Detalle();
        datod2.setId_pedido(Integer.parseInt(id_paciente));
        datod2.setId_carpeta(cliente.getCod_esta());
        datod2.setCod_esta(cliente.getCod_esta());
        List listarcobrosKardex = this.mi.getListarDetalle(datod2);
        modelo.put("listarcobroskardex", listarcobrosKardex);

        List listarcobroSaldo = this.mi.getListarDetalleSaldo(datod2);
        modelo.put("listarcobrosaldo", listarcobroSaldo);
        // pacientes a cobrar enviados por recepcion
        Pacientes paciente = new Pacientes();
        paciente.setId_estado("A");
        paciente.setId_rubro(3);
        paciente.setId_localidad(cliente.getCod_esta());
        List listarPaises = this.mi.getListarCobroRubroOdon(paciente);
        modelo.put("milista", listarPaises);

        // pacientes a cobrar enviados por recepcion
        paciente.setId_estado("C");
        List listarPagados = this.mi.getListarCobroRubro(paciente);
        modelo.put("listaP", listarPagados);

        return new ModelAndView("administrarhistoriales/ListarCobroRubroKardex", modelo);
    }
}
