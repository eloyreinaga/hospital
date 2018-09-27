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
public class NuevoProveedorControlador {

    private final MiFacade mi;

    public NuevoProveedorControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoProveedor.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_proveedor = request.getParameter("id_proveedor");

        modelo.put("id_proveedor", request.getParameter("id_proveedor"));

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_proveedor") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del proveedor
            Proveedores proveedor = new Proveedores();
            proveedor.setCod_esta(cliente.getCod_esta());
            proveedor.setId_proveedor(Integer.parseInt(id_proveedor));
            Proveedores datoproveedor = this.mi.getDatosProveedor(proveedor);
            modelo.put("datoproveedor", datoproveedor);
            modelo.put("sw", request.getParameter("sw"));
        }
        modelo.put("accion", accion);
        return new ModelAndView("administrarproveedores/NuevoProveedor", modelo);
    }
}
