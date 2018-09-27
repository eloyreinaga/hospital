package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoMedicamentoControlador {

    private final MiFacade mi;

    public NuevoMedicamentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoMedicamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        String[] dias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String diaq1 = request.getParameter("diaq1");
        String mesq1 = request.getParameter("mesq1");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        Date fechaact = new Date();
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq), Integer.toString(anioq + 1), Integer.toString(anioq + 2), Integer.toString(anioq + 3), Integer.toString(anioq + 4), Integer.toString(anioq + 5), Integer.toString(anioq + 6), Integer.toString(anioq + 7), Integer.toString(anioq + 8), Integer.toString(anioq + 9)};

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        if (fecha1.getMonth() + 1 < 10) {
            mesq1 = "0" + Integer.toString(fecha1.getMonth() + 1);
        } else {
            mesq1 = Integer.toString(fecha1.getMonth() + 1);
        }
        if (fecha1.getDate() < 10) {
            diaq1 = "0" + Integer.toString(fecha1.getDate());
        } else {
            diaq1 = Integer.toString(fecha1.getDate());
        }
        modelo.put("mes", mesq1);
        modelo.put("dia", diaq1);

        String id_medicamento = request.getParameter("id_medicamento");
        modelo.put("id_medicamento", request.getParameter("id_medicamento"));

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));

        Medicamentos medic = new Medicamentos();
        medic.setCod_esta(cliente.getCod_esta());
        medic.setId_farmacia(cliente.getId_farmacia());
        medic.setId_persona(cliente.getId_persona());
        medic.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(medic);
        modelo.put("datoItem", datoItem);
        List listarGrupo = this.mi.getListarGrupos(medic);  ///getListarGrupos
        modelo.put("listarGrupo", listarGrupo);
        
        List listarTipo = this.mi.getListarTipos(medic);  ///getListarTipos
        modelo.put("listarTipo", listarTipo);

        if ("Grupo".equals(accion)) {
            List listarGrupo2 = this.mi.getListarGrupos(medic);  ///getListarGrupos
            modelo.put("listarGrupo", listarGrupo2);
            return new ModelAndView("administrarmedicamentos/NuevoGrupo", modelo);
        }

        if ("Partida".equals(accion)) {
            medic.setExpedido("P");  ///getListarPartidas
            List listarPartida = this.mi.getListarPartidas(medic);  ///getListarPartidas
            modelo.put("listarPartida", listarPartida);
            return new ModelAndView("administrarmedicamentos/NuevoPartida", modelo);
        }

        if ("SubGrupo".equals(accion)) {
            List listarGrupo2 = this.mi.getListarGrupos(medic);  ///getListarGrupos
            modelo.put("listarGrupo", listarGrupo2);
            medic.setExpedido("S");
            List listarSGrupo = this.mi.getListarSubGrupos(medic);  ///getListarSubGrupos
            modelo.put("listarSGrupo", listarSGrupo);
            return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo);
        }

        //Para la primera vez que entra a la pagina
        if ((request.getParameter("id_medicamento") != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos del medicamento
            //Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            modelo.put("buscarMedicamento", buscarMedicamento);
            List listarSeguros = this.mi.getListarSeguros("A");
            modelo.put("listaPacAfi", listarSeguros);

            modelo.put("sw", request.getParameter("sw"));
            modelo.put("tipo", cliente.getTipo());
            modelo.put("id_grupo", buscarMedicamento.getSuma1());

            modelo.put("anio", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
            modelo.put("mes", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
            modelo.put("dia", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            modelo.put("accion", accion);
            return new ModelAndView("administrarmedicamentos/NuevoMedicamento", modelo);
        }

        if ("eliminar".equals(accion)) {
            String cod_esta = request.getParameter("cod_esta");
            String medicamento = request.getParameter("medicamento");
            Medicamentos elimina = new Medicamentos();
            elimina.setId_medicamento(Integer.parseInt(id_medicamento));
            elimina.setCodigo(Integer.parseInt(cod_esta));
            Recetas datoreceta = new Recetas();///Verifica si existe datos en el kardex
            Date dFecha = new Date(fecha1.getYear() - 10, fecha1.getMonth(), fecha1.getDate());
            datoreceta.setFecha_ini(dFecha);
            datoreceta.setFecha_fin(fecha1);
            datoreceta.setId_medicamento(Integer.parseInt(id_medicamento));
            datoreceta.setExpedido("%");
            datoreceta.setId_estado("T");  ////getListarKardexMedicamentoSin0
            datoreceta.setCod_esta(cliente.getCod_esta());  /////23-01-2014
            datoreceta.setId_farmacia(cliente.getId_farmacia());
            List listarKardexMedicamento = this.mi.getListarKardexMedicamentoSin0(datoreceta);
            if (listarKardexMedicamento.size() > 1) {///Busca para ver si ya fue selccionado el medicamento
                return new ModelAndView("Aviso", "mensaje", "No puede Eliminar, tiene Datos en Kardex, comuniquese con el Administrador");
            } else {
                elimina.setExpedido("L");
                try {
                    this.mi.setEliminarMedicamento(elimina);
                    elimina.setCod_esta(cliente.getCod_esta());
                    elimina.setMedicamento(medicamento);
                    elimina.setId_farmacia(cliente.getId_farmacia());
                    List listarMedicamentos = this.mi.getListarMedicamentos(elimina);
                    modelo.put("listarMedicamentos", listarMedicamentos);
                    elimina.setExpedido("T");
                    List listarMedicamentosT = this.mi.getListarMedicamentos(elimina);
                    modelo.put("listarMedicamentosT", listarMedicamentosT);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "El registro a eliminar Consulte con el administrador");
                }
            }

        }

        if ("adicion".equals(accion)) {
            String medicamento = request.getParameter("medicamento");
            String forma_far = request.getParameter("forma_far");
            String concentra = request.getParameter("concentra");
            String codsumi = request.getParameter("codsumi");
            String nombres = request.getParameter("nombre");
            Date ahora = new Date();
            Medicamentos adiciona = new Medicamentos();
            adiciona.setId_medicamento(Integer.parseInt(id_medicamento));
            adiciona.setCodigo(cliente.getCod_esta());
            adiciona.setCod_esta(cliente.getCod_esta());
            adiciona.setId_farmacia(cliente.getId_farmacia());
            adiciona.setExpedido("U");   ///getDatosMedicamentoUnico
            Medicamentos buscarMedicamentouno = this.mi.getDatosMedicamentoUnico(adiciona);
            adiciona.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(adiciona);
            if (buscarMedicamento != null) {///Busca para ver si ya fue selccionado el medicamento
                return new ModelAndView("Aviso", "mensaje", "Este Medicamento ya fue seleccionado en este establecimiento");
            } else {
                adiciona.setId_medicamento(Integer.parseInt(id_medicamento));
                adiciona.setCodigo(cliente.getCod_esta());
                adiciona.setId_farmacia(cliente.getId_farmacia());
                adiciona.setMedicamento(medicamento);
                adiciona.setCodsumi(codsumi);
                adiciona.setConcentra(concentra);
                adiciona.setForma_far(forma_far);
                adiciona.setPrecio_venta(0);
                adiciona.setCosto_unit(0);
                adiciona.setStockv(0);
                adiciona.setStocks(0);
                adiciona.setStockp(0);
                adiciona.setStock(0);
                adiciona.setFecha_ven(ahora);
                adiciona.setNro_lote("SL");
                adiciona.setCod_cta(buscarMedicamentouno.getCod_cta());
                adiciona.setCod_tipo(buscarMedicamentouno.getCod_tipo());
                adiciona.setCod_espe(buscarMedicamentouno.getCod_espe());
                adiciona.setCod_material(buscarMedicamentouno.getCod_material());
                adiciona.setExpedido("L");  ///setCrearMedicamentoLocal
                try {
                    this.mi.setCrearMedicamentoLocal(adiciona);
                    /// 
                    adiciona.setCod_esta(cliente.getCod_esta());
                    medicamento = medicamento.replaceAll("\\s", ":*&");
                    adiciona.setMedicamento(medicamento + ":*");
                    adiciona.setId_farmacia(cliente.getId_farmacia());
                    List listarMedicamentos = this.mi.getListarMedicamentos(adiciona);
                    modelo.put("listarMedicamentos", listarMedicamentos);
                    adiciona.setMedicamento(medicamento + ":*");
                    List listarMedicamentosT = this.mi.getListarMedicamentosTotal(adiciona);
                    modelo.put("listarMedicamentosT", listarMedicamentosT);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización no se cumplio, verifique los datos");
                }
            }
        }

        if ("Adicionar2".equals(accion2)) {
            String nombremed = request.getParameter("nombre");
            String nombres = request.getParameter("nombre");
            if (nombremed == null) {
                nombremed = "ASP";
            }
            nombremed = nombremed.trim();
            nombremed = nombremed.replaceAll(" +", " ");
            nombres = nombremed;
            modelo.put("nombremed", nombremed);
            modelo.put("nombres", nombres);
            nombremed = nombremed.replaceAll("\\s", ":*&");
            nombremed = nombremed.replaceAll("ñ", "n");
            nombremed = nombremed.replaceAll("Ñ", "N");
            if (nombremed.length() > 0) {
                nombremed = nombremed + ":*";
            }
            nombres = "%" + nombres + "%";
            Medicamentos dato = new Medicamentos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            dato.setMedicamento(nombremed);
            dato.setCadena10("medicamento");
            List listarMedicamentos = this.mi.getListarMedicamentos(dato);
            modelo.put("listarMedicamentos", listarMedicamentos);
            dato.setMedicamento(nombres);
            List listarMedicamentosT = this.mi.getListarMedicamentosTotal(dato);
            modelo.put("listarMedicamentosT", listarMedicamentosT);
        }

        modelo.put("accion", accion);
        return new ModelAndView("administrarmedicamentos/NuevoMedicamento2", modelo);
    }
}
