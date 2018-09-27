package org.ayaic.web.administrarclientes;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clieentes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarClienteControlador {

    private final MiFacade mi;

    public ConfirmarClienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ConfirmarCliente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes clientes = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_cliente = request.getParameter("id_cliente");
        String razonsocial = request.getParameter("razonsocial");
        String encargado = request.getParameter("encargado");
        String direccion = request.getParameter("direccion");
        String fonos = request.getParameter("fonos");
        String nit = request.getParameter("nit");
        String ciudad = request.getParameter("ciudad");
        String email = request.getParameter("email");
        String id_estado = request.getParameter("id_estado");

        Clieentes pai = new Clieentes();

        if ("Adicionar".equals(accion)) {
            pai.setRazonsocial(razonsocial);
            pai.setEncargado(encargado);
            pai.setDireccion(direccion);
            pai.setFonos(fonos);
            pai.setNit(nit);
            pai.setEmail(email);
            pai.setCiudad(ciudad);
            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {

            pai.setId_cliente(Integer.parseInt(id_cliente));
            pai.setRazonsocial(razonsocial);
            pai.setEncargado(encargado);
            pai.setDireccion(direccion);
            pai.setFonos(fonos);
            pai.setNit(nit);
            pai.setEmail(email);
            pai.setCiudad(ciudad);
            pai.setId_estado(id_estado);

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            pai.setId_cliente(Integer.parseInt(id_cliente));
            pai.setCod_esta(clientes.getCod_esta());
            Clieentes buscarcliente = this.mi.getDatosCliente(pai); // saca un registro a ser modificado
            modelo.put("dato", buscarcliente);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_cliente", id_cliente);
        }

        return new ModelAndView("administrarclientes/ConfirmarCliente", modelo);

    }
}
