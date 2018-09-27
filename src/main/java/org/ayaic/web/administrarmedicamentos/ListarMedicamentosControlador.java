package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarMedicamentosControlador {

    private final MiFacade mi;

    public ListarMedicamentosControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ListarMedicamentos.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String nombres = request.getParameter("nombres");
        String accion = request.getParameter("accion");
        String cod_esta = request.getParameter("cod_esta");
        String id_farmacia = request.getParameter("id_farmacia");
        String id_grupo = request.getParameter("id_grupo");
        String id_subgrupo = request.getParameter("id_subgrupo");
        String id_partida = request.getParameter("id_partida");

        modelo.put("nombres", nombres);
        modelo.put("rol", Integer.toString(cliente.getId_rol2()));
        modelo.put("id_farmacia", id_farmacia);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datoestab.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("seguro_estab", Integer.toString(cliente.getInst_id()));
        modelo.put("area", cliente.getArea());

        Medicamentos dato = new Medicamentos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_farmacia(cliente.getId_farmacia());
        dato.setId_persona(cliente.getId_persona());
        List listarGrupo = this.mi.getListarGrupos(dato);  ///getListarGrupos
        modelo.put("listarGrupo", listarGrupo);
        dato.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(dato);
        modelo.put("datoItem", datoItem);
        if (!"".equals(id_grupo) && id_grupo != null && ("".equals(accion) || accion == null)) {
            dato.setExpedido("2");    /////
            dato.setId_medicamento(Integer.parseInt(id_grupo));
            List listarSGrupo = this.mi.getListarSubGrupos2(dato);
            modelo.put("listarSGrupo", listarSGrupo);
            modelo.put("id_grupo", id_grupo);
            dato.setSuma1(Integer.parseInt(id_grupo));
            dato.setMedicamento("%");
            List listarMedicamentos = this.mi.getListarInventaGrupo(dato);///getListarInventaGrupo
            modelo.put("listarMedicamentos", listarMedicamentos);
            //return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo); 
        }
        dato.setExpedido("P");  ///getListarPartidas
        List listarPartida = this.mi.getListarPartidas(dato);  ///getListarPartidas
        modelo.put("listarPartida", listarPartida);
        modelo.put("id_grupo", id_grupo);
        modelo.put("id_subgrupo", id_subgrupo);
        modelo.put("id_partida", id_partida);

        if ("BuscarItem".equals(accion) || "Buscar".equals(accion) || "_".equals(accion) || "Ordenar".equals(accion)) {
            String nombremed = request.getParameter("nombremed");
            String orden = request.getParameter("orden");
            if ("".equals(nombres) && nombremed == null) {
                nombres = " ";
            }
            if ("".equals(nombremed) && nombres == null) {
                nombres = " ";
            }
            if (("".equals(nombres) || nombres == null) && !"".equals(nombremed)) {
                nombres = nombremed;
            }
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");
            modelo.put("nombremed", nombres);
            modelo.put("nombres", nombres);
            nombres = nombres.replaceAll("\\s", ":*&");
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setMedicamento(nombres);
            dato.setCadena10(orden);
            if (!"".equals(id_farmacia) && id_farmacia != null) {
                dato.setId_farmacia(Integer.parseInt(id_farmacia));
            }
            //List listarMedicamentosUsua = this.mi.getListarMedicamentos(dato);
            if (!"".equals(id_grupo) && id_grupo != null) {
                dato.setExpedido("2");    ///getListarSubGrupos2
                dato.setId_medicamento(Integer.parseInt(id_grupo));
                List listarSGrupo = this.mi.getListarSubGrupos2(dato);  ///getListarSubGrupos2
                modelo.put("listarSGrupo", listarSGrupo);
                modelo.put("id_grupo", id_grupo);
                //return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo); 
            }
            if (datoestab.getCod_esta() == 200010) {
                dato.setExpedido("C");
            }
            if (!"".equals(id_partida) && id_partida != null) {
                id_partida = "0";
            }
            if (!"".equals(id_grupo) && id_grupo != null) {
                id_grupo = "0";
            }
            if (!"".equals(id_subgrupo) && id_subgrupo != null) {
                id_subgrupo = "0";
            }

            if ("".equals(nombres)) {
                List listarMedicamentos = mi.getListarMedicamentosVacio(dato);
                modelo.put("listarMedicamentos", listarMedicamentos);
            } else {
                List listarMedicamentos = mi.getListarMedicamentos(dato);
                modelo.put("listarMedicamentos", listarMedicamentos);
                if ("_".equals(accion)) {
                    List listarMedicamentosT = mi.getListarMedicamentosGestion(dato);
                    modelo.put("listarMedicamentos", listarMedicamentosT);
                }
            }

            if (!"".equals(nombres) && "0".equals(id_grupo) && "0".equals(id_subgrupo) && "0".equals(id_partida)) {
                List listarMedicamentos = this.mi.getListarMedicamentos(dato);///getListarMedicamentos
                modelo.put("listarMedicamentos", listarMedicamentos);
            }
            if (!"0".equals(id_grupo) && !"0".equals(id_subgrupo) && "0".equals(id_partida)) {
                dato.setSuma1(Integer.parseInt(id_grupo));
                dato.setSuma2(Integer.parseInt(id_subgrupo));
                List listarMedicamentos = this.mi.getListarInventaGrupoSub(dato);///getListarInventaGrupoSub
                modelo.put("listarMedicamentos", listarMedicamentos);
                modelo.put("id_subgrupo", id_subgrupo);
            }
            if (!"0".equals(id_grupo) && "0".equals(id_subgrupo) && "0".equals(id_partida)) {
                dato.setSuma1(Integer.parseInt(id_grupo));
                List listarMedicamentos = this.mi.getListarInventaGrupo(dato);///getListarInventaGrupo
                modelo.put("listarMedicamentos", listarMedicamentos);
            }
            if ("0".equals(id_grupo) && !"0".equals(id_subgrupo) && "0".equals(id_partida)) {
                dato.setSuma2(Integer.parseInt(id_subgrupo));
                List listarMedicamentos = this.mi.getListarInventaSubGrupo(dato);///getListarInventaSubGrupo
                modelo.put("listarMedicamentos", listarMedicamentos);
            }
            if ("0".equals(id_grupo) && "0".equals(id_subgrupo) && !"0".equals(id_partida)) {
                dato.setSuma3(Integer.parseInt(id_partida));
                List listarMedicamentos = this.mi.getListarInventaPartida(dato);///getListarInventaPartida
                modelo.put("listarMedicamentos", listarMedicamentos);
            }

        }
        /*
    if("BuscarItemsss".equals(accion)) {
       nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");
            modelo.put("nombremed", nombres);
            modelo.put("nombres", nombres);
            nombres = nombres.replaceAll("\\s", ":*&");
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if(nombres.length() > 0)
                nombres = nombres+":*";
            
       dato.setCod_esta(cliente.getCod_esta()); 
       dato.setId_farmacia(cliente.getId_farmacia());
       dato.setId_persona(cliente.getId_persona());
       if(!"".equals(cod_esta) && cod_esta!=null){
           dato.setCod_esta(Integer.parseInt(cod_esta)); 
           modelo.put("cod_esta",cod_esta );
       }if(cod_esta==null){
           dato.setCod_esta(cliente.getCod_esta()); 
           modelo.put("cod_esta", cliente.getCod_esta() );    
       }   
       if(!"".equals(id_farmacia) && id_farmacia!=null){
           dato.setId_farmacia(Integer.parseInt(id_farmacia));
       }
       dato.setMedicamento(nombres);
       dato.setExpedido("Z");    ////
       //List listarMedicamentosUsua = this.mi.getListarMedicamentos(dato); //// se comenta 21/11/2017 
       if(datoestab.getCod_esta()==200010){
          dato.setExpedido("C");    
       }
       //if("I".equals(cliente.getArea())){
        //  List listarInv = this.mi.getListarInventa(dato);///getListarInventa
        //  modelo.put("listarMedicamentos", listarInv);
      // }else{
          List listarMedicamentos = this.mi.getListarMedicamentos(dato);///getListarMedicamentos
          modelo.put("listarMedicamentos", listarMedicamentos); 
      // }
    }
         */
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

        if (cliente.getInst_id() != 10 && (cliente.getId_rol2() == 31 || cliente.getId_rol2() == 30)) {
            //Localidades local = new Localidades();
            local.setArea("M");
            List Estab = mi.getEstabHabiles(local);
            modelo.put("listaEstab", Estab);

            Farmacias datofar = new Farmacias();
            datofar.setCod_esta(cliente.getCod_esta());
            if (!"".equals(cod_esta) && cod_esta != null) {
                datofar.setCod_esta(Integer.parseInt(cod_esta));
                modelo.put("cod_esta", cod_esta);
            }
            List listarFarmacia = this.mi.getListarFarmacias(datofar);
            modelo.put("listarFarmacia", listarFarmacia);

            return new ModelAndView("administrarmedicamentos/ListarMedicamentosGeneral", modelo);
        }
        return new ModelAndView("administrarmedicamentos/ListarMedicamentos", modelo);

    }
}
