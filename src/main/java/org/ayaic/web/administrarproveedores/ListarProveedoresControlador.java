package org.ayaic.web.administrarproveedores;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Proveedores;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarProveedoresControlador {

    private final MiFacade mi;

    public ListarProveedoresControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarProveedores.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
		String accion  = request.getParameter("accion");

        Proveedores proveedor = new Proveedores();
        proveedor.setCod_esta(cliente.getCod_esta());
        List listarProveedores = this.mi.getListarProveedores(proveedor);
        modelo.put("listarProveedores", listarProveedores);
		modelo.put("dato", cliente);
    
		if("Kardex".equals(accion) ) {
		   String id_proveedor   = request.getParameter("id_proveedor"); 
		   
		   proveedor.setId_proveedor(Integer.parseInt(id_proveedor)) ;
		   proveedor.setCod_esta(cliente.getCod_esta()) ;
		   Proveedores buscarproveedor = this.mi.getDatosProveedor( proveedor) ; // saca un registro a ser modificado
		   modelo.put("datos", buscarproveedor);
		  
		   // recupera los medicamentos del paciente a entregar       
		   Recetas midato = new Recetas();
		   midato.setId_prestacion(Integer.parseInt(id_proveedor)) ;
		   midato.setCod_esta(cliente.getCod_esta());
		   List listarKardex = this.mi.getListarKardexProve(midato);
		   modelo.put("listarKardex", listarKardex);    
		   return new ModelAndView(new VerKardexPDF(), modelo);//// 
		}

        return new ModelAndView("administrarproveedores/ListarProveedores", modelo);

    }
}
