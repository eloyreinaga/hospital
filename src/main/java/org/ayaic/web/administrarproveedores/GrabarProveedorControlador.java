package org.ayaic.web.administrarproveedores;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Proveedores;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarProveedorControlador {

    private final MiFacade mi;

    public GrabarProveedorControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarProveedor.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");

        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");

        String id_proveedor = request.getParameter("id_proveedor");
        String razonsocial = request.getParameter("razonsocial");
        String encargado = request.getParameter("encargado");
        String direccion = request.getParameter("direccion");
        String fonos = request.getParameter("fonos");
        String nit = request.getParameter("nit");
        String email = request.getParameter("email");
        String ciudad = request.getParameter("ciudad");

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Proveedores proveedor = new Proveedores();
            proveedor.setId_proveedor(Integer.parseInt(id_proveedor));
            proveedor.setCod_esta(cliente.getCod_esta());
            Proveedores repetida = this.mi.getDatosProveedor(proveedor);  // SI EL OBJETO EXISTE ENTONCES NO ES NULO

            Proveedores datoproveedor = new Proveedores();
            datoproveedor.setId_proveedor(Integer.parseInt(id_proveedor));
            datoproveedor.setRazonsocial(razonsocial);
            datoproveedor.setEncargado(encargado);
            datoproveedor.setDireccion(direccion);
            datoproveedor.setFonos(fonos);
            datoproveedor.setNit(nit);
            datoproveedor.setEmail(email);
            datoproveedor.setCiudad(ciudad);
            datoproveedor.setCod_esta(cliente.getCod_esta());
            try {
                this.mi.setCrearProveedor(datoproveedor);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
            }
            //listar Proveedors
            //Proveedores proveedor= new Proveedores() ;
            proveedor.setCod_esta(cliente.getCod_esta());
            List buscarproveedor = this.mi.getListarProveedores(proveedor);
            modelo.put("listarProveedores", buscarproveedor);
            return new ModelAndView("administrarproveedores/ListarProveedores", modelo);

        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {

            Proveedores categoria_m = new Proveedores();
            categoria_m.setId_proveedor(Integer.parseInt(request.getParameter("id_proveedor")));
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
                this.mi.setModificarProveedor(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualización proveedores no se cumplio, verifique los datos");
            }
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_proveedor"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Proveedores elimina = new Proveedores();
            elimina.setId_proveedor(Integer.parseInt(id_proveedor));
            elimina.setCod_esta(cliente.getCod_esta());

            try {
                this.mi.setEliminarProveedor(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro de proveedor a eliminar tiene dependencias");
            }
        }

        //listar proveedores
        Proveedores proveedor = new Proveedores();
        proveedor.setCod_esta(cliente.getCod_esta());
        List buscarproveedor = this.mi.getListarProveedores(proveedor);
        modelo.put("listarProveedores", buscarproveedor);

        return new ModelAndView("administrarproveedores/ListarProveedores", modelo);

    }
}
