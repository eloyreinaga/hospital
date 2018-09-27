package org.ayaic.web.administrarproveedores;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Proveedores;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarProveedorControlador {

    private final MiFacade mi;

    public ConfirmarProveedorControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarProveedor.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_proveedor = request.getParameter("id_proveedor");
        String razonsocial = request.getParameter("razonsocial");
        String encargado = request.getParameter("encargado");
        String direccion = request.getParameter("direccion");
        String fonos = request.getParameter("fonos");
        String nit = request.getParameter("nit");
        String ciudad = request.getParameter("ciudad");
        String email = request.getParameter("email");

        String id_estado = request.getParameter("id_estado");
        Proveedores pai = new Proveedores();

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

            pai.setId_proveedor(Integer.parseInt(id_proveedor));
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
            pai.setId_proveedor(Integer.parseInt(id_proveedor));
            pai.setCod_esta(cliente.getCod_esta());
            Proveedores buscarproveedor = this.mi.getDatosProveedor(pai); // saca un registro a ser modificado
            modelo.put("dato", buscarproveedor);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_proveedor", id_proveedor);
        }

        return new ModelAndView("administrarproveedores/ConfirmarProveedor", modelo);

    }
}
