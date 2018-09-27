package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarMedicamentoControlador {

    private final MiFacade mi;

    public ConfirmarMedicamentoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarMedicamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_medicamento = request.getParameter("id_medicamento");
        String medicamento = request.getParameter("medicamento");
        String codsumi = request.getParameter("codsumi");
        String forma_far = request.getParameter("forma_far");
        String concentra = request.getParameter("concentra");
        String ubicacion = request.getParameter("ubicacion");
        String precio_venta = request.getParameter("precio_venta");
        String costo_unit = request.getParameter("costo_unit");
        String stockv = request.getParameter("stockv");
        String stocks = request.getParameter("stocks");
        String stockp = request.getParameter("stockp");
        String stock = request.getParameter("stock");
        String codigo = request.getParameter("codigo");
        String tipo = request.getParameter("tipo");
        String max_emerg = request.getParameter("max_emerg");
        String max_exter = request.getParameter("max_exter");
        String min_emerg = request.getParameter("min_emerg");
        String min_exter = request.getParameter("min_exter");
        String max_inter = request.getParameter("max_inter");
        String min_inter = request.getParameter("min_inter");
        String restringido = request.getParameter("restringido");
        String sspam = request.getParameter("sspam");
        String id_partida = request.getParameter("id_partida");
        String id_grupo = request.getParameter("id_grupo");
        String id_subgrupo = request.getParameter("id_subgrupo");
        String nro_lote = request.getParameter("nro_lote");
        String dia = request.getParameter("diai");
        String mes = request.getParameter("mesi");
        String anio = request.getParameter("anioi");
        Date fecha1 = new Date();

        if (codigo == null) {
            codigo = "0";
        }
        if (sspam == null) {
            sspam = "0";
        } else {
            sspam = "1";
        }

        String fecha_ven = anio + mes + dia;
        modelo.put("anio", anio);
        modelo.put("mes", mes);
        modelo.put("dia", dia);
        if (anio == null && mes == null && dia == null) {
            int anio1 = fecha1.getYear() + 1900;
            int mes1 = fecha1.getMonth() + 1;
            int dia1 = fecha1.getDate();
            String anio11 = Integer.toString(anio1);
            String mes11 = Integer.toString(mes1);
            String dia11 = Integer.toString(dia1);;
            if (mes1 < 10) {
                mes11 = "0" + mes11;
            }
            if (dia1 < 10) {
                dia11 = "0" + dia11;
            }
            modelo.put("fecha_ven", anio11 + mes11 + dia11);
            modelo.put("anio", anio11);
            modelo.put("mes", mes11);
            modelo.put("dia", dia11);
        }

        modelo.put("id_subgrupo", id_subgrupo);
        modelo.put("id_partida", id_partida);
        modelo.put("id_grupo", id_grupo);

        Medicamentos pai = new Medicamentos();
        pai.setCod_esta(cliente.getCod_esta());
        pai.setId_farmacia(cliente.getId_farmacia());
        pai.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(pai);
        modelo.put("datoItem", datoItem);
        List listarGrupo = this.mi.getListarGrupos(pai);  ///getListarGrupos
        modelo.put("listarGrupo", listarGrupo);
        if (!"".equals(id_grupo) && !"".equals(id_grupo) && "".equals(accion1)) {
            pai.setExpedido("2");    ///getListarSubGrupos2
            pai.setId_medicamento(Integer.parseInt(id_grupo));
            List listarSGrupo = this.mi.getListarSubGrupos2(pai);  ///getListarSubGrupos2
            modelo.put("listarSGrupo", listarSGrupo);
            modelo.put("id_grupo", id_grupo);
            return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo);
        }

        if ("Adicionar".equals(accion)) {
            pai.setMedicamento(medicamento);
            if (codsumi == null) {
                codsumi = "0";
            }
            if (max_emerg == null) {
                max_emerg = "0";
            }
            if (max_exter == null) {
                max_exter = "0";
            }
            if (max_inter == null) {
                max_inter = "0";
            }
            if (min_emerg == null) {
                min_emerg = "0";
            }
            if (min_exter == null) {
                min_exter = "0";
            }
            if (min_inter == null) {
                min_inter = "0";
            }
            if (restringido == null) {
                restringido = "0";
            }
            if ("".equals(forma_far) || forma_far == null) {
                forma_far = "SF";
            }
            if ("".equals(concentra) || concentra == null) {
                concentra = "SC";
            }
            if ("".equals(ubicacion) || ubicacion == null) {
                ubicacion = "SU";
            }
            if ("".equals(nro_lote) || nro_lote == null) {
                nro_lote = "SL";
            }
            if ("".equals(precio_venta) || precio_venta == null) {
                precio_venta = "0";
            }
            if ("".equals(costo_unit) || costo_unit == null) {
                costo_unit = "0";
            }
            if ("".equals(stockv) || stockv == null) {
                stockv = "0";
            }
            if ("".equals(stocks) || stocks == null) {
                stocks = "0";
            }
            if ("".equals(stockp) || stockp == null) {
                stockp = "0";
            }
            if ("".equals(stock) || stock == null) {
                stock = "0";
            }
            if ("".equals(id_grupo) || id_grupo == null) {
                id_grupo = "0";
            }
            if ("".equals(id_subgrupo) || id_subgrupo == null) {
                id_subgrupo = "0";
            }
            if ("".equals(id_partida) || id_partida == null) {
                id_partida = "0";
            }
            pai.setCodsumi(codsumi);
            pai.setForma_far(forma_far);
            pai.setConcentra(concentra);
            pai.setUbicacion(ubicacion);
            pai.setPrecio_venta(Double.parseDouble(precio_venta));
            pai.setCosto_unit(Double.parseDouble(costo_unit));
            pai.setStockv(Double.parseDouble(stockv));
            pai.setStocks(Double.parseDouble(stocks));
            pai.setStockp(Double.parseDouble(stockp));
            pai.setStock(Double.parseDouble(stock));
            pai.setNro_lote(nro_lote);
            pai.setTipo(tipo);
            pai.setMax_emerg(Integer.parseInt(max_emerg));
            pai.setMax_exter(Integer.parseInt(max_exter));
            pai.setMax_inter(Integer.parseInt(max_inter));
            pai.setMin_emerg(Integer.parseInt(min_emerg));
            pai.setMin_exter(Integer.parseInt(min_exter));
            pai.setMin_inter(Integer.parseInt(min_inter));
            pai.setSuma1(Integer.parseInt(id_grupo));
            pai.setSuma2(Integer.parseInt(id_subgrupo));
            pai.setSuma3(Integer.parseInt(id_partida));
            pai.setRestringido(Integer.parseInt(restringido));

            modelo.put("dato", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("ModificarG".equals(accion1)) {
            String grupo = request.getParameter("grupo");

            Medicamentos datomedicamento = new Medicamentos();
            datomedicamento.setCod_esta(cliente.getCod_esta());
            datomedicamento.setExpedido("G");  ///setModificarGrupo
            //List listarGrupo = this.mi.getListarGrupos(datomedicamento);  ///getListarGrupos
            //modelo.put("listarGrupo", listarGrupo); 
            modelo.put("id_grupo", id_grupo);
            modelo.put("grupo", grupo);
            return new ModelAndView("administrarmedicamentos/NuevoGrupo", modelo);
        }

        if ("ModificarP".equals(accion1)) {
            String partida = request.getParameter("partida");

            Medicamentos datomedicamento = new Medicamentos();
            datomedicamento.setCod_esta(cliente.getCod_esta());
            List listarPartida = this.mi.getListarPartidas(datomedicamento);
            modelo.put("listarPartida", listarPartida);
            modelo.put("id_partida", id_partida);
            modelo.put("partida", partida);
            return new ModelAndView("administrarmedicamentos/NuevoPartida", modelo);
        }

        if ("ModificarSG".equals(accion1)) {
            String subgrupo = request.getParameter("subgrupo");

            Medicamentos datomedicamento = new Medicamentos();
            datomedicamento.setCod_esta(cliente.getCod_esta());
            datomedicamento.setId_medicamento(Integer.parseInt(id_subgrupo));
            datomedicamento.setExpedido("T");    /////getDatosSubItem
            Medicamentos datoSubItem = this.mi.getDatosSubItem(datomedicamento);
            datomedicamento.setId_medicamento(datoSubItem.getId_medicamento());
            List listarSGrupo = this.mi.getListarSubGrupos2(datomedicamento);
            modelo.put("listarSGrupo", listarSGrupo);
            List listarGrupo2 = this.mi.getListarGrupos(pai);  ///getListarGrupos
            modelo.put("listarGrupo", listarGrupo2);
            modelo.put("id_grupo", Integer.toString(datoSubItem.getId_medicamento()));
            modelo.put("id_subgrupo", id_subgrupo);
            modelo.put("subgrupo", subgrupo);
            return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo);
        }

        if ("Modificar".equals(accion)) {

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if (stockv == null) {
                stockv = Double.toString(buscarMedicamento.getStockv());
            }
            if (stocks == null) {
                stocks = Double.toString(buscarMedicamento.getStocks());
            }
            if (stockp == null) {
                stockp = Double.toString(buscarMedicamento.getStockp());
            }
            if (stock == null) {
                stock = Double.toString(buscarMedicamento.getStock());
            }
            if ("".equals(forma_far) || forma_far == null) {
                forma_far = "SF";
            }
            if ("".equals(concentra) || concentra == null) {
                concentra = "SF";
            }
            if ("".equals(ubicacion) || ubicacion == null) {
                ubicacion = "SF";
            }
            if ("".equals(nro_lote) || nro_lote == null) {
                nro_lote = "SF";
            }
            if ("".equals(precio_venta) || precio_venta == null) {
                precio_venta = "0";
            }
            if ("".equals(costo_unit) || costo_unit == null) {
                costo_unit = "0";
            }
            if ("".equals(stockv) || stockv == null) {
                stockv = "0";
            }
            if ("".equals(stocks) || stocks == null) {
                stocks = "0";
            }
            if ("".equals(stockp) || stockp == null) {
                stockp = "0";
            }
            if ("".equals(stock) || stock == null) {
                stock = "0";
            }
            ///anterior si se esta modificando los datos de medicamentos
            pai.setId_medicamento(Integer.parseInt(id_medicamento));
            pai.setMedicamento(medicamento);
            pai.setCodsumi(codsumi);
            pai.setCodigo(Integer.parseInt(codigo));
            pai.setForma_far(forma_far);
            pai.setConcentra(concentra);
            pai.setUbicacion(ubicacion);
            pai.setPrecio_venta(Double.parseDouble(precio_venta));
            pai.setCosto_unit(Double.parseDouble(costo_unit));
            pai.setStockv(Double.parseDouble(stockv));
            pai.setStocks(Double.parseDouble(stocks));
            pai.setStockp(Double.parseDouble(stockp));
            pai.setStock(Double.parseDouble(stock));
            pai.setTipo(tipo);
            pai.setMax_emerg(Integer.parseInt(max_emerg));
            pai.setMax_exter(Integer.parseInt(max_exter));
            pai.setMax_inter(Integer.parseInt(max_inter));
            pai.setMin_emerg(Integer.parseInt(min_emerg));
            pai.setMin_exter(Integer.parseInt(min_exter));
            pai.setMin_inter(Integer.parseInt(min_inter));
            pai.setRestringido(Integer.parseInt(restringido));
            pai.setNro_lote(nro_lote);
            pai.setB1(Integer.parseInt(sspam));

            modelo.put("dato", pai);
            modelo.put("sw", request.getParameter("sw"));
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Eliminar".equals(accion)) {
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            modelo.put("dato", buscarMedicamento);

            Date fecha_nac = buscarMedicamento.getFecha_ven();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            fecha_ven = Integer.toString(xdia) + "/" + Integer.toString(xmes) + "/" + Integer.toString(xanio);
            modelo.put("fecha_ven", fecha_ven);

            modelo.put("accion", accion);
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_medicamento", id_medicamento);
        }
        return new ModelAndView("administrarmedicamentos/ConfirmarMedicamento", modelo);

    }
}
