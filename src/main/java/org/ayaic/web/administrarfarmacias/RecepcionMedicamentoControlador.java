package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecepcionMedicamentoControlador {

    private final MiFacade mi;

    public RecepcionMedicamentoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecepcionMedicamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String expedido = request.getParameter("expedido");
        String id_pedido = request.getParameter("id_pedido");
        String id_programa = request.getParameter("id_programa");
        String id_farmacia = request.getParameter("id_farmacia");
        String id_medicamento = request.getParameter("id_medicamento");
        String nombremed = request.getParameter("nombremed");
        String valor_1 = request.getParameter("valor_1");
        String tipo = request.getParameter("tipo");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String recepcion = "no";
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] anios = {"2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
        Date fecha1 = new Date();
        Date Fecha1 = new Date();
        double total;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("estab", Integer.toString(datoestab.getCod_esta()));

        Medicamentos medid = new Medicamentos();
        medid.setCod_esta(cliente.getCod_esta());
        medid.setId_farmacia(cliente.getId_farmacia());
        List listarprog = this.mi.getListarProgramas(medid);
        modelo.put("listarProg", listarprog);
        medid.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(medid);
        modelo.put("datoItem", datoItem);
        modelo.put("expedido", expedido);
        modelo.put("id_farmacia", id_farmacia);
        Pacientes paciente1 = new Pacientes();
        paciente1.setId_pedido(Integer.parseInt(id_pedido));
        paciente1.setCod_esta(cliente.getCod_esta());
        Pacientes paciente = this.mi.getDatosPedido(paciente1);
        Fecha1 = paciente.getFec_registro();
        modelo.put("recepcion", recepcion);

        if ("Aceptar".equals(accion)) {
            String cantidad = request.getParameter("cantidad");
            String costo_unit = request.getParameter("costo_unit");
            String costo_venta = request.getParameter("precio_venta");
            String nro_lote = request.getParameter("nro_lote");
            String dia_r = request.getParameter("dia_r");
            String mes_r = request.getParameter("mes_r");
            String anio_r = request.getParameter("anio_r");

            //expedido=paciente.getTipo(); ///// coloca en 'C' en kardex expedido 27/12/2016
            tipo = "0";
            if ("".equals(id_programa) || id_programa == null) {
                id_programa = "0";
            }
            if ("".equals(id_farmacia) || id_farmacia == null) {
                id_farmacia = "0";
            }
            if (paciente.getId_tipo_far() == 2) {  ///|| paciente.getId_tipo_far()==4
                tipo = "1";
            }
            if (paciente.getId_tipo_far() == 3) {
                tipo = "-1";        //// para llenar ajustes si es 0 ajuste 0
            }

            if ("".equals(dia_r) || dia_r == null) {
                dia_r = "01";
            }
            if ("".equals(mes_r) || mes_r == null) {
                mes_r = "01";
            }
            if ("".equals(anio_r) || anio_r == null) {
                anio_r = "2018";
            }
            int diax_r = Integer.parseInt(dia_r);
            int mesx_r = Integer.parseInt(mes_r) - 1;
            int aniox_r = Integer.parseInt(anio_r) - 1900;
            Date fec_reg = new Date(aniox_r, mesx_r, diax_r);

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());

            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if (paciente.getId_tipo_far() == 4) {////traspasos y si no hay el medica en el otro almacen lo crea nuevo
                medic.setId_farmacia(Integer.parseInt(id_farmacia));
                medic.setExpedido("B");    /////getDatosMedicamentoB
                Medicamentos buscarMedicamento2 = this.mi.getDatosMedicamentoB(medic);
                if (buscarMedicamento2 == null) {
                    medic.setId_medicamento(Integer.parseInt(id_medicamento));
                    medic.setCodigo(cliente.getCod_esta());
                    medic.setCod_esta(cliente.getCod_esta());
                    medic.setId_farmacia(Integer.parseInt(id_farmacia));
                    medic.setId_medicamento(Integer.parseInt(id_medicamento));
                    medic.setCodigo(cliente.getCod_esta());
                    medic.setMedicamento(buscarMedicamento.getMedicamento());
                    medic.setCodsumi(buscarMedicamento.getCodsumi());
                    medic.setConcentra(buscarMedicamento.getConcentra());
                    medic.setForma_far(buscarMedicamento.getForma_far());
                    medic.setPrecio_venta(0);
                    medic.setCosto_unit(0);
                    medic.setStockv(0);
                    medic.setStocks(0);
                    medic.setStockp(0);
                    medic.setStock(0);
                    medic.setFecha_ven(buscarMedicamento.getFecha_ven());
                    medic.setNro_lote("SL");
                    medic.setCod_cta(buscarMedicamento.getCod_cta());
                    medic.setCod_tipo(buscarMedicamento.getCod_tipo());
                    medic.setCod_espe(buscarMedicamento.getCod_espe());
                    medic.setCod_material(buscarMedicamento.getCod_material());
                    medic.setId_programa(cliente.getId_farmacia());
                    medic.setExpedido("L");  ///setCrearMedicamentoLocal
                    if ("I".equals(cliente.getArea())) {
                        medic.setExpedido("2");  ///setCrearMedicamentoLocal2 para almacenes crea todo el item
                        this.mi.setCrearMedicamentoLocal2(medic);
                    }
                    if (!"I".equals(cliente.getArea())) {
                        medic.setExpedido("2");  ///setCrearMedicamentoLocal2 para almacenes crea todo el item 
                        this.mi.setCrearMedicamentoLocal(medic);
                    }

                }
            }

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setEntrada(0);
            dato.setSalida(0);

            dato.setAjuste(0);
            dato.setNro_lote(nro_lote);
            dato.setFecha_ven(fec_reg);
            if ("".equals(cantidad) || cantidad == null) {
                return new ModelAndView("Aviso", "mensaje", "Coloque un numero valido");
            }
            if ("0".equals(tipo)) {
                dato.setEntrada(Double.parseDouble(cantidad));
            } else {
                dato.setAjuste(Double.parseDouble(tipo) * Double.parseDouble(cantidad));
            }

            if (costo_venta == null || costo_venta == "") {
                costo_venta = Double.toString(buscarMedicamento.getPrecio_venta());
            }
            dato.setPrecio_venta(Double.parseDouble(costo_venta));
            dato.setCosto_unit(Double.parseDouble(costo_unit));
            dato.setFecha(fecha1);
            dato.setExpedido(expedido);

            if ("S".equals(expedido)) {
                id_programa = "1";
            }
            if ("V".equals(expedido)) {
                id_programa = "0";
            }
            if ("R".equals(expedido)) {/////algunos en cns aparecen R y no se porque
                dato.setExpedido("P");
                id_programa = "13";
            }
            if ("V".equals(paciente.getTipo()) && cliente.getInst_id() == 1) {///para privados
                dato.setExpedido("V");
                id_programa = "0";
            }
            dato.setId_programa(Integer.parseInt(id_programa));
            // entregamos el medicamento
            dato.setFecha(Fecha1);//////////////////////////////
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_farmacia(cliente.getId_farmacia());
            if (paciente.getId_tipo_far() == 4) { ////borrado 12/12/2017 cliente.getId_rol2()==30 || cliente.getId_rol2()==32 ) &&
                dato.setId_farmacia(Integer.parseInt(id_farmacia));
                dato.setId_receta(-1);
            }
            if (buscarMedicamento == null) {
                if ("S".equals(expedido) || "P".equals(expedido)) {
                    dato.setPrecio_venta(buscarMedicamento.getPrecio_venta());
                }
            }
            dato.setId_detalle(Integer.parseInt(id_farmacia));///////10/11/2015
            dato.setId_persona(cliente.getId_persona());
            dato.setId_factura(0);
            dato.setId_historial(0);
            dato.setId_historia(0);
            dato.setCadena1(ip);///01/03/2016
            dato.setCadena2(host);///01/03/2016
            //this.mi.setCrearKardex(dato); 
            int iResultado = this.mi.setRegistrarKardex(dato);
            if (paciente.getId_tipo_far() == 4) { ////borrado 12/12/2017 (cliente.getId_rol2()==30 || cliente.getId_rol2()==32 ) &&
                dato.setEntrada(0);
                dato.setSalida(Double.parseDouble(cantidad));
                dato.setId_farmacia(cliente.getId_farmacia());
                int iResultado2 = this.mi.setRegistrarKardex(dato);
                //this.mi.setCrearKardex(dato);
            }
            // acutalizamos el stock del medicamento
            modelo.put("id_programa", id_programa);
            modelo.put("recepcion", "no");

        }

        if ("adicion".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            Date ahora = new Date();

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            if (datoestab.getCod_esta() == 200010 && paciente.getId_tipo_far() == 4) {
                medic.setId_farmacia(Integer.parseInt(id_farmacia));
                Medicamentos buscarMedicamentoff = this.mi.getDatosMedicamento(medic);
                if (buscarMedicamentoff == null) {//// cre nuevo medicamento a la farmacia que estamos haciendo traspaso
                    Medicamentos adiciona = new Medicamentos();
                    adiciona.setId_medicamento(Integer.parseInt(id_medicamento));
                    adiciona.setCodigo(cliente.getCod_esta());
                    adiciona.setCod_esta(cliente.getCod_esta());
                    adiciona.setId_farmacia(Integer.parseInt(id_farmacia));
                    adiciona.setId_medicamento(Integer.parseInt(id_medicamento));
                    adiciona.setCodigo(cliente.getCod_esta());
                    adiciona.setMedicamento(buscarMedicamento.getMedicamento());
                    adiciona.setCodsumi(buscarMedicamento.getCodsumi());
                    adiciona.setConcentra(buscarMedicamento.getConcentra());
                    adiciona.setForma_far(buscarMedicamento.getForma_far());
                    adiciona.setPrecio_venta(0);
                    adiciona.setCosto_unit(0);
                    adiciona.setStockv(0);
                    adiciona.setStocks(0);
                    adiciona.setStockp(0);
                    adiciona.setStock(0);
                    adiciona.setFecha_ven(ahora);
                    adiciona.setNro_lote("SL");
                    adiciona.setCod_cta(buscarMedicamento.getCod_cta());
                    adiciona.setCod_tipo(buscarMedicamento.getCod_tipo());
                    adiciona.setCod_espe(buscarMedicamento.getCod_espe());
                    adiciona.setCod_material(buscarMedicamento.getCod_material());
                    adiciona.setExpedido("L");  ///setCrearMedicamentoLocal
                    this.mi.setCrearMedicamentoLocal(adiciona);
                    //return new ModelAndView("Aviso","mensaje","NO existe este medicamento en la farmacia seleccionada, primeramente crearlo");
                }
            }

            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                modelo.put("dato", buscarMedicamentox);
                modelo.put("anio_r", Integer.toString(buscarMedicamentox.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamentox.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamentox.getFecha_ven().getDate()));
            } else {
                modelo.put("dato", buscarMedicamento);
                modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            }

            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("cantidad", "0");
            recepcion = "si";
            modelo.put("recepcion", recepcion);
        }

        if ("P".equals(expedido) && !"Regresar".equals(accion) && !"Buscar".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String cantidad = request.getParameter("cantidad");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            modelo.put("dato", buscarMedicamento);
            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);

            modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
            modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
            modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            recepcion = "si";
            modelo.put("programa", "progra");
            modelo.put("expedido", expedido);
            modelo.put("cantidad", cantidad);
        }

        if (("V".equals(expedido) || "S".equals(expedido)) && !"Regresar".equals(accion) && !"Buscar".equals(accion)) {
            String id_consultorio = request.getParameter("id_consultorio");
            String id_persona = request.getParameter("id_persona");
            String cantidad = request.getParameter("cantidad");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);
            if (buscarMedicamento == null) {
                medic.setExpedido("A");
                Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic);
                modelo.put("dato", buscarMedicamentox);
                modelo.put("anio_r", Integer.toString(buscarMedicamentox.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamentox.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamentox.getFecha_ven().getDate()));
            } else {
                modelo.put("dato", buscarMedicamento);
                modelo.put("anio_r", Integer.toString(buscarMedicamento.getFecha_ven().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(buscarMedicamento.getFecha_ven().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(buscarMedicamento.getFecha_ven().getDate()));
            }

            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_pedido", id_pedido);
            modelo.put("id_persona", id_persona);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            recepcion = "si";
            modelo.put("expedido", expedido);
            modelo.put("cantidad", cantidad);
        }

        if ("eliminar".equals(accion)) {
            String ajuste = request.getParameter("ajuste");
            String cantidad = request.getParameter("entrada");
            String id_kardex = request.getParameter("id_kardex");
            String id_farmac = request.getParameter("id_farmac");

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            medic.setExpedido("B");    /////getDatosMedicamentoB
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamentoB(medic);

            Recetas dato = new Recetas();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_medicamento(Integer.parseInt(id_medicamento));
            dato.setId_factura(Integer.parseInt(id_kardex));
            dato.setId_farmacia(Integer.parseInt(id_farmac));
            dato.setSalida(0);
            dato.setEntrada(Double.parseDouble(cantidad));
            dato.setAjuste(Double.parseDouble(ajuste));
            // quitamos el medicamento
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarKardex(dato);
            // acutalizamos el stock del medicamento
            /*
           if (buscarMedicamento==null) {
              medic.setExpedido("A") ;
              Medicamentos buscarMedicamentox = this.mi.getDatosMedicamento(medic) ; 
              buscarMedicamentox.setStock(buscarMedicamentox.getStock()-dato.getEntrada()-dato.getAjuste());   
               if(dato.getAjuste()!=0)
                   dato.setEntrada(dato.getAjuste());                      

               if("V".equals(expedido)) 
                   buscarMedicamentox.setStockv(buscarMedicamentox.getStockv()-dato.getEntrada());   
               else 
                 if("S".equals(expedido))                              
                   buscarMedicamentox.setStocks(buscarMedicamentox.getStocks()-dato.getEntrada());   
                 else 
                     buscarMedicamentox.setStockp(buscarMedicamentox.getStockp()-dato.getEntrada());   

               buscarMedicamentox.setCod_esta(cliente.getCod_esta());
               this.mi.setModificarMedicamento(buscarMedicamentox);
           }else{
               buscarMedicamento.setStock(buscarMedicamento.getStock()-dato.getEntrada()-dato.getAjuste());   
               if(dato.getAjuste()!=0)
                   dato.setEntrada(dato.getAjuste());                      

               if("V".equals(expedido)) 
                   buscarMedicamento.setStockv(buscarMedicamento.getStockv()-dato.getEntrada());   
               else 
                 if("S".equals(expedido))                              
                   buscarMedicamento.setStocks(buscarMedicamento.getStocks()-dato.getEntrada());   
                 else 
                     buscarMedicamento.setStockp(buscarMedicamento.getStockp()-dato.getEntrada());   

               buscarMedicamento.setCod_esta(cliente.getCod_esta());
               buscarMedicamento.setId_farmacia(cliente.getId_farmacia());
               this.mi.setModificarMedicamento(buscarMedicamento);
            }
             */
        }

        if (nombremed != null) {
            nombremed = nombremed.trim();
            nombremed = nombremed.replaceAll(" +", " ");
            modelo.put("nombremed", nombremed);
            nombremed = nombremed.replaceAll("\\s", ":*&");
            nombremed = nombremed.replaceAll("ñ", "n");
            nombremed = nombremed.replaceAll("Ñ", "N");

            Medicamentos nommed = new Medicamentos();
            nommed.setCod_esta(cliente.getCod_esta());
            nommed.setId_farmacia(cliente.getId_farmacia());
            if (nombremed.length() > 0) {
                nombremed = nombremed.toLowerCase() + ":*";
                nommed.setMedicamento(nombremed);
                List listarMedicamentos = this.mi.getListarMedicamentosRe(nommed);
                modelo.put("listarMedicamentos", listarMedicamentos);
            } else {
                List listarMedicamentos = mi.getListarMedicamentosVacio(nommed);
                modelo.put("listarMedicamentos", listarMedicamentos);
            }

        }

        Recetas midato = new Recetas();
        midato.setId_pedido(Integer.parseInt(id_pedido));
        midato.setId_prestacion(cliente.getCod_esta());
        midato.setCod_esta(cliente.getCod_esta());
        midato.setId_farmacia(cliente.getId_farmacia());
        if (paciente.getId_tipo_far() == 4 || paciente.getId_tipo_far() == 2) {
            midato.setExpedido("A");
        }
        List listarKardex = this.mi.getListarKardex(midato);
        modelo.put("listarKardex", listarKardex);
        // Calculamos el total a pagar
        total = 0;
        for (int i = 0; i < listarKardex.size(); i++) {
            midato = (Recetas) listarKardex.get(i);
            total = total + midato.getPrecio_totalc();
        }
        paciente.setPrecio_total(total);
        paciente.setFecha_ini(Fecha1);
        this.mi.setModificarPedidoAnt(paciente);
        modelo.put("datos", paciente);
        modelo.put("valor_1", valor_1);
        modelo.put("id_pedido", id_pedido);
        modelo.put("id_programa", Integer.toString(paciente.getId_persona()));

        return new ModelAndView("administrarfarmacias/RecepcionMedicamento", modelo);
    }
}
