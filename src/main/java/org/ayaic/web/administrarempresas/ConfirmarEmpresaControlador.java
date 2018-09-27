package org.ayaic.web.administrarempresas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarEmpresaControlador {

    private final MiFacade mi;

    public ConfirmarEmpresaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarEmpresa.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accionq = request.getParameter("accionp");
        String accion1 = request.getParameter("accion1");

        String id_empresa = request.getParameter("id_empresa");
        String id_carpeta = request.getParameter("id_carpeta");
        String empresa = request.getParameter("empresa");
        String nit = request.getParameter("nit");
        String direccion = request.getParameter("direccion");
        String telefonos = request.getParameter("telefonos");
        String responsable = request.getParameter("responsable");
        String id_estado = request.getParameter("id_estado");
        String anio = request.getParameter("anio");
        String mes = request.getParameter("mes");
        String num_cladoc = request.getParameter("num_cladoc");
        String codpatronal = request.getParameter("codpatronal");
        Date fecha1 = new Date();
        Empresas pai = new Empresas();

        if ("Adicionar".equals(accion)) {

//     pai.setId_empresa(Integer.parseInt(id_empresa)) ;
            pai.setEmpresa(empresa);
            pai.setNit(nit);
            pai.setDireccion(direccion);
            pai.setTelefonos(telefonos);
            pai.setResponsable(responsable);

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Pagos".equals(accion)) {
            String accionp = request.getParameter("accionp");
            String accionpago[] = request.getParameterValues("pago");
            modelo.put("empresa", empresa);
            modelo.put("id_empresa", id_empresa);
            modelo.put("nit", nit);
            modelo.put("sw", request.getParameter("sw"));

            Pacientes paciente = new Pacientes();
            paciente.setId_empresa(Integer.parseInt(id_empresa));
            paciente.setTipo("F");
            List listarPaises = this.mi.getListarPacientesEmp(paciente);
            if (listarPaises.size() > 0) {
                Pacientes Pacie = (Pacientes) listarPaises.get(0);
                id_carpeta = Integer.toString(Pacie.getId_carpeta());
                modelo.put("id_carpeta", id_carpeta);
            }
            modelo.put("listaPacientes", listarPaises);

            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

            return new ModelAndView("administrarempresas/ListarPacienteEmpresa", modelo);
        }
        if ("Ver Pagos".equals(accion)) {
            String accionp = request.getParameter("accionp");
            String accionpago[] = request.getParameterValues("pago");
            modelo.put("empresa", empresa);
            modelo.put("direccion", direccion);
            modelo.put("id_empresa", id_empresa);
            modelo.put("nit", nit);
            modelo.put("sw", request.getParameter("sw"));

            Pacientes paciente = new Pacientes();
            paciente.setId_empresa(Integer.parseInt(id_empresa));
            paciente.setTipo("F");
            List listarPaises = this.mi.getListarPacientesEmp(paciente);
            if (listarPaises.size() > 0) {
                Pacientes Pacie = (Pacientes) listarPaises.get(0);
                id_carpeta = Integer.toString(Pacie.getId_carpeta());
                modelo.put("id_carpeta", id_carpeta);
            }
            modelo.put("listaPacientes", listarPaises);
            Detalle detalle = new Detalle();
            detalle.setId_pedido(1);
            detalle.setCod_esta(cliente.getCod_esta());
            List listarPagos = this.mi.getListarDetallePago(detalle);
            modelo.put("listarpagos", listarPagos);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

            return new ModelAndView("administrarempresas/ListarPacienteEmpresaP", modelo);
        }
        if ("Grabar Datos".equals(accion)) {
            String empresa2 = request.getParameter("empresa");
            String id_empresa2 = request.getParameter("id_empresa");
            String nit2 = request.getParameter("nit");
            String anio2 = request.getParameter("anio");
            String mes2 = request.getParameter("mes");
            modelo.put("empresa", empresa2);
            modelo.put("id_empresa", id_empresa2);
            modelo.put("id_carpeta", id_carpeta);
            modelo.put("nit", nit2);
            modelo.put("anio", anio2);
            modelo.put("mes", mes2);
            Pacientes paciente = new Pacientes();
            paciente.setId_empresa(Integer.parseInt(id_empresa));
            paciente.setTipo("F");
            List listarPac = this.mi.getListarPacientesEmp(paciente);
            modelo.put("numempleados", Integer.toString(listarPac.size()));
            Detalle detalle = new Detalle();
            detalle.setId_pedido(1);
            detalle.setCod_esta(cliente.getCod_esta());
            List listarPagos = this.mi.getListarDetallePago(detalle);
            modelo.put("listarpagos", listarPagos);
            return new ModelAndView("administrarempresas/ListarPagoEmpresa", modelo);
        }
        if ("Grabar".equals(accion)) {
            String empresa2 = request.getParameter("empresa");
            String id_empresa2 = request.getParameter("id_empresa");
            String nit2 = request.getParameter("nit");
            String gestion = request.getParameter("anio");
            String mes2 = request.getParameter("mes");
            String costo_unit = request.getParameter("precio");
            String id_pedido = request.getParameter("id_pedido");

            Pacientes paciente = new Pacientes();
            paciente.setId_empresa(Integer.parseInt(id_empresa));
            paciente.setTipo("F");
            List listarPac = this.mi.getListarPacientesEmp(paciente);
            modelo.put("numempleados", Integer.toString(listarPac.size()));

            paciente.setCod_esta(cliente.getCod_esta());
            num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente));
            Pacientes dato = new Pacientes();
            dato.setNombres(empresa);
            dato.setPrecio_total(Float.parseFloat(costo_unit));
            dato.setId_paciente(0);
            dato.setId_estado("C");
            dato.setNum_cladoc(num_cladoc);
            dato.setNit(nit2);
            //dato.setId_costo(9999) ;
            dato.setId_rubro(999);
            dato.setId_persona(cliente.getId_usuario());
            dato.setId_carpeta(Integer.parseInt(id_carpeta));
            dato.setId_paciente(Integer.parseInt(id_empresa));
            dato.setTipo("C");
            dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
            dato.setFec_registro(fecha1);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setCrearPeedido(dato);

            id_pedido = Integer.toString(this.mi.getNumPedido(dato));
            Detalle detalle = new Detalle();
            detalle.setEntrada(Float.parseFloat(costo_unit));
            detalle.setId_carpeta(Integer.parseInt(id_carpeta));
            detalle.setId_empresa(Integer.parseInt(id_empresa));
            detalle.setId_pedido(Integer.parseInt(id_pedido));
            detalle.setMes(Integer.parseInt(mes));
            detalle.setGestion(Integer.parseInt(gestion));
            detalle.setId_rubro(999);
            detalle.setUlt_usuario(cliente.getId_usuario());
            this.mi.setCrearDetallePago(detalle);

            return new ModelAndView("Aviso", "mensaje", "Los datos se grabaron correctamente");
        }
        if ("Modificar".equals(accion)) {

            pai.setId_empresa(Integer.parseInt(id_empresa));
            pai.setEmpresa(empresa);
            pai.setNit(nit);
            pai.setDireccion(direccion);
            pai.setTelefonos(telefonos);
            pai.setResponsable(responsable);
            pai.setCod_patronal(Long.parseLong(codpatronal));
            ///pai.setId_estado(id_estado);
            pai.setId_estado("A");

            modelo.put("dato", pai);
            modelo.put("codpatronal", codpatronal);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }
        if ("Eliminar".equals(accion)) {
            Empresas buscarempresa = this.mi.getDatosEmpresa(Integer.parseInt(id_empresa)); // saca un registro a ser modificadoEmpresas buscarempresa 
            modelo.put("dato", buscarempresa);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_parentesco", id_empresa);
        }
        return new ModelAndView("administrarempresas/ConfirmarEmpresa", modelo);
    }
}
