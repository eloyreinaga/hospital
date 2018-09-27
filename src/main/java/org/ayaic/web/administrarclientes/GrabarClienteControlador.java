package org.ayaic.web.administrarclientes;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clieentes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarClienteControlador {

    private final MiFacade mi;

    public GrabarClienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/GrabarCliente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String id_cliente = request.getParameter("id_cliente");
        String razonsocial = request.getParameter("razonsocial");
        String encargado = request.getParameter("encargado");
        String direccion = request.getParameter("direccion");
        String fonos = request.getParameter("fonos");
        String nit = request.getParameter("nit");
        String email = request.getParameter("email");
        String ciudad = request.getParameter("ciudad");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Clieentes clientes = new Clieentes();
            clientes.setId_cliente(Integer.parseInt(id_cliente));
            clientes.setCod_esta(cliente.getCod_esta());
            Clieentes repetida = this.mi.getDatosCliente(clientes);  // SI EL OBJETO EXISTE ENTONCES NO ES NULO

            Clieentes datocliente = new Clieentes();
            datocliente.setId_cliente(Integer.parseInt(id_cliente));
            datocliente.setRazonsocial(razonsocial);
            datocliente.setEncargado(encargado);
            datocliente.setDireccion(direccion);
            datocliente.setFonos(fonos);
            datocliente.setNit(nit);
            datocliente.setEmail(email);
            datocliente.setCiudad(ciudad);
            datocliente.setCod_esta(cliente.getCod_esta());
            //try{
            this.mi.setCrearCliente(datocliente);
            //}catch (Exception e){ 
            // return new ModelAndView("Aviso","mensaje", "La actualización no se cumplio, verifique los datos");
            //}   
            //listar Clientes
            //Clieentes cliente= new Clieentes() ;
            clientes.setCod_esta(cliente.getCod_esta());
            List buscarcliente = this.mi.getListarClientes(clientes);
            modelo.put("listarClientes", buscarcliente);
            return new ModelAndView("administrarclientes/ListarClientes", modelo);

        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {

            Clieentes categoria_m = new Clieentes();
            categoria_m.setId_cliente(Integer.parseInt(request.getParameter("id_cliente")));
            categoria_m.setRazonsocial(razonsocial);
            categoria_m.setEncargado(encargado);
            categoria_m.setDireccion(direccion);
            categoria_m.setFonos(fonos);
            categoria_m.setNit(nit);
            categoria_m.setEmail(email);
            categoria_m.setCiudad(ciudad);
            categoria_m.setCod_esta(cliente.getCod_esta());

            if ("A".equals(request.getParameter("id_estado"))) {
                categoria_m.setId_estado("A");
            } else {
                categoria_m.setId_estado("B");
            }
            try {
                this.mi.setModificarCliente(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización clientes no se cumplio, verifique los datos");
            }
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_cliente"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Clieentes elimina = new Clieentes();
            elimina.setId_cliente(Integer.parseInt(id_cliente));
            elimina.setCod_esta(cliente.getCod_esta());

            try {
                this.mi.setEliminarCliente(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro de cliente a eliminar tiene dependencias");
            }
        }

        //listar clientees
        Clieentes clientes = new Clieentes();
        clientes.setCod_esta(cliente.getCod_esta());
        List buscarcliente = this.mi.getListarClientes(clientes);
        modelo.put("listarClientes", buscarcliente);

        return new ModelAndView("administrarclientes/ListarClientes", modelo);

    }
}
