package org.ayaic.web.administrarmedicamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarMedicamentoControlador {

    private final MiFacade mi;

    public GrabarMedicamentoControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarMedicamento.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String medicamento = request.getParameter("medicamento");
        String codsumi = request.getParameter("codsumi");
        String ubicacion = request.getParameter("ubicacion");
        String forma_far = request.getParameter("forma_far");
        String concentra = request.getParameter("concentra");
        String id_medicamento = request.getParameter("id_medicamento");
        String precio_venta = request.getParameter("precio_venta");
        String costo_unit = request.getParameter("costo_unit");
        String stockv = request.getParameter("stockv");
        String stocks = request.getParameter("stocks");
        String stockp = request.getParameter("stockp");
        String stock = request.getParameter("stock");
        String nro_lote = request.getParameter("nro_lote");
        String codigo = request.getParameter("codigo");
        String tipo = request.getParameter("tipo");
        String max_emerg = request.getParameter("max_emerg");
        String max_exter = request.getParameter("max_exter");
        String max_inter = request.getParameter("max_inter");
        String min_emerg = request.getParameter("min_emerg");
        String min_exter = request.getParameter("min_exter");
        String min_inter = request.getParameter("min_inter");
        String restringido = request.getParameter("restringido");
        String id_partida = request.getParameter("id_partida");
        String id_grupo = request.getParameter("id_grupo");
        String id_subgrupo = request.getParameter("id_subgrupo");
        String b1 = request.getParameter("b1");
        Date fecha1 = new Date();

        Medicamentos datomedicamento = new Medicamentos();
        datomedicamento.setCod_esta(cliente.getCod_esta());
        datomedicamento.setId_farmacia(cliente.getId_farmacia());
        datomedicamento.setExpedido("I");    /////getDatosItem
        Medicamentos datoItem = this.mi.getDatosItem(datomedicamento);
        modelo.put("datoItem", datoItem);
        List listarGrupo = this.mi.getListarGrupos(datomedicamento);  ///getListarGrupos
        modelo.put("listarGrupo", listarGrupo);

        if ("Guardar".equals(accion1)) {
            String grupo = request.getParameter("grupo");
            if ("".equals(grupo.trim()) || grupo == null) {
                return new ModelAndView("Aviso", "mensaje", "No se puede crear Grupos Vacios");
            }
            datomedicamento.setCadena1(grupo);
            datomedicamento.setId_persona(cliente.getId_persona());
            datomedicamento.setExpedido("G");  ///setCrearGrupo 
            if (!"".equals(grupo.trim()) && "".equals(id_grupo)) {
                this.mi.setCrearGrupo(datomedicamento);
            } else {
                datomedicamento.setId_medicamento(Integer.parseInt(id_grupo));
                this.mi.setModificarGrupo(datomedicamento);  ///setModificarGrupo  
            }
            List listarGrupo2 = this.mi.getListarGrupos(datomedicamento);  ///getListarGrupos
            modelo.put("listarGrupo", listarGrupo2);
            return new ModelAndView("administrarmedicamentos/NuevoGrupo", modelo);
        }

        if ("GuardarP".equals(accion1)) {
            String partida = request.getParameter("partida");
            if ("".equals(partida.trim()) || partida == null) {
                return new ModelAndView("Aviso", "mensaje", "No se puede crear Grupos Vacios");
            }
            datomedicamento.setCadena1(partida);
            datomedicamento.setId_persona(cliente.getId_persona());
            datomedicamento.setExpedido("P");  ///setCrearPartida
            if (!"".equals(partida.trim()) && "".equals(id_partida)) {
                this.mi.setCrearPartida(datomedicamento);
            } else {
                datomedicamento.setId_medicamento(Integer.parseInt(id_partida));
                this.mi.setModificarPartida(datomedicamento);  ///setModificarPartida 
            }
            datomedicamento.setCod_esta(cliente.getCod_esta());
            List listarPartida = this.mi.getListarPartidas(datomedicamento);
            modelo.put("listarPartida", listarPartida);
            return new ModelAndView("administrarmedicamentos/NuevoPartida", modelo);
        }

        if ("GuardarS".equals(accion1)) {
            String subgrupo = request.getParameter("subgrupo");
            if ("".equals(subgrupo.trim()) || subgrupo == null) {
                return new ModelAndView("Aviso", "mensaje", "No se puede crear Grupos Vacios");
            }

            datomedicamento.setCadena1(subgrupo);
            datomedicamento.setId_persona(cliente.getId_persona());
            datomedicamento.setId_medicamento(Integer.parseInt(id_grupo));
            datomedicamento.setExpedido("I");  ///setCrearSubGrupo 
            if (!"".equals(subgrupo.trim()) && ("".equals(id_subgrupo) || id_subgrupo == null)) {
                this.mi.setCrearSubGrupo(datomedicamento);
            } else {
                datomedicamento.setExpedido("S");  ///setModificarSubGrupo  
                datomedicamento.setId_medicamento(Integer.parseInt(id_subgrupo));
                this.mi.setModificarSubGrupo(datomedicamento);
            }
            datomedicamento.setId_medicamento(Integer.parseInt(id_grupo));
            List listarSGrupo = this.mi.getListarSubGrupos2(datomedicamento);
            modelo.put("listarSGrupo", listarSGrupo);
            modelo.put("id_grupo", id_grupo);

            return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo);
        }

        if ("EliminarG".equals(accion1)) {
            datomedicamento.setId_medicamento(Integer.parseInt(id_grupo));
            datomedicamento.setExpedido("G");  ///setEliminarGrupo
            try {
                this.mi.setEliminarGrupo(datomedicamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro Grupo, tiene dependencias");
            }
            return new ModelAndView("administrarmedicamentos/NuevoGrupo", modelo);
        }

        if ("EliminarP".equals(accion1)) {
            datomedicamento.setId_medicamento(Integer.parseInt(id_partida));
            datomedicamento.setExpedido("P");  ///setEliminarPartida
            try {
                this.mi.setEliminarPartida(datomedicamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro Grupo, tiene dependencias");
            }
            datomedicamento.setCod_esta(cliente.getCod_esta());
            List listarPartida = this.mi.getListarPartidas(datomedicamento);
            modelo.put("listarPartida", listarPartida);
            return new ModelAndView("administrarmedicamentos/NuevoPartida", modelo);
        }

        if ("EliminarSG".equals(accion1)) {
            datomedicamento.setId_medicamento(Integer.parseInt(id_subgrupo));
            datomedicamento.setExpedido("2");  ///setEliminarSubGrupo
            try {
                this.mi.setEliminarSubGrupo(datomedicamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro SubGrupo, tiene dependencias");
            }
            datomedicamento.setExpedido("S");  ///getListarSubGrupos
            List listarSGrupo = this.mi.getListarSubGrupos(datomedicamento);
            modelo.put("listarSGrupo", listarSGrupo);
            if (!"".equals(id_grupo.trim()) && id_grupo != null) {
                datomedicamento.setId_medicamento(Integer.parseInt(id_grupo));
                datomedicamento.setExpedido("2");  ///getListarSubGrupos2
                List listarSGrupo2 = this.mi.getListarSubGrupos2(datomedicamento);  ///getListarSubGrupos2
                modelo.put("listarSGrupo", listarSGrupo2);
            }

            modelo.put("id_grupo", id_grupo);
            return new ModelAndView("administrarmedicamentos/NuevoSubGrupo", modelo);
        }

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(request.getParameter("id_medicamento")));  // SI EL OBJETO EXISTE ENTONCES NO ES NULO
            medic.setCodigo(cliente.getCod_esta());
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos repetida = this.mi.getDatosMedicamento(medic);

            if (repetida == null) {
                if (("".equals(id_medicamento)) || ("".equals(medicamento)) || ("".equals(ubicacion))) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
                }
                String dia = request.getParameter("dia");
                String mes = request.getParameter("mes");
                String anio = request.getParameter("anio");

                if ("".equals(anio) && "".equals(mes) && "".equals(dia)) {
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
                }

                Date fec_nacimiento = new Date(fecha1.getYear() + 1, fecha1.getMonth(), fecha1.getDate());

                datomedicamento.setMedicamento(medicamento);
                datomedicamento.setCod_esta(cliente.getCod_esta());
                datomedicamento.setCodsumi(codsumi);
                datomedicamento.setUbicacion(ubicacion);
                datomedicamento.setConcentra(concentra);
                datomedicamento.setForma_far(forma_far);
                datomedicamento.setId_farmacia(cliente.getId_farmacia());
                datomedicamento.setPrecio_venta(Double.parseDouble(precio_venta));
                datomedicamento.setCosto_unit(Double.parseDouble(costo_unit));
                datomedicamento.setStockv(Double.parseDouble(stockv));
                datomedicamento.setStocks(Double.parseDouble(stocks));
                datomedicamento.setStockp(Double.parseDouble(stockp));
                datomedicamento.setStock(Double.parseDouble(stock));
                datomedicamento.setFecha_ven(fec_nacimiento);
                datomedicamento.setNro_lote(nro_lote);
                datomedicamento.setTipo(tipo);
                datomedicamento.setMax_emerg(Integer.parseInt(max_emerg));
                datomedicamento.setMax_exter(Integer.parseInt(max_exter));
                datomedicamento.setMax_inter(Integer.parseInt(max_inter));
                datomedicamento.setMin_emerg(Integer.parseInt(min_emerg));
                datomedicamento.setMin_exter(Integer.parseInt(min_exter));
                datomedicamento.setMin_inter(Integer.parseInt(min_inter));
                datomedicamento.setCadena1("");
                datomedicamento.setCadena2("");
                datomedicamento.setCadena3("");
                datomedicamento.setCadena4("");
                datomedicamento.setCadena5("");
                datomedicamento.setCadena10("");
				datomedicamento.setCadena11("");
                datomedicamento.setCadena12("");
                datomedicamento.setCadena13("");
                if ("".equals(id_grupo) || id_grupo == null) {////08/03/2018 para poder aumentar grupos
                    id_grupo = "0";
                    datomedicamento.setSuma10(Integer.parseInt(id_grupo));
                    datomedicamento.setCadena10("");
                } else {
                    datomedicamento.setSuma1(Integer.parseInt(id_grupo));
                    Medicamentos grupo = this.mi.getDatoGrupo(datomedicamento);
                    if (grupo != null) {
                        datomedicamento.setCadena10(grupo.getCadena1());
                    } else {
                        datomedicamento.setCadena10("");
                    }
                }

                if ("".equals(id_subgrupo) || id_subgrupo == null) {
                    id_subgrupo = "0"; 
                    datomedicamento.setSuma2(Integer.parseInt(id_subgrupo));
                    datomedicamento.setCadena11("");
                } else {
                    datomedicamento.setSuma2(Integer.parseInt(id_subgrupo));
                    Medicamentos subgrupo = this.mi.getDatoSubGrupo(datomedicamento);
                    if (subgrupo != null) {
                        datomedicamento.setCadena11(subgrupo.getCadena1());
                    } else {
                        datomedicamento.setCadena11("");
                    }
                }

                if ("".equals(id_partida) || id_partida == null) {
                    id_partida = "0"; 
                    datomedicamento.setSuma3(Integer.parseInt(id_partida));
                    datomedicamento.setCadena12("");
                } else {
                    datomedicamento.setSuma3(Integer.parseInt(id_partida));
                    Medicamentos partida = this.mi.getDatoPartida(datomedicamento);
                    if (partida != null) {
                        datomedicamento.setCadena12(partida.getCadena1());
                    } else {
                        datomedicamento.setCadena12("");
                    }
                }

                datomedicamento.setRestringido(Integer.parseInt(restringido));
                try {
                    this.mi.setCrearMedicamento(datomedicamento);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualizacion nuevo medicamento no se cumplio, verifique los datos");
                }
                /////listar medicamentos   ///16/10/2017 se elimina al crear inventarios farmacia general salia error
                medicamento = medicamento.replaceAll("\\s", ":*&");
                datomedicamento.setMedicamento(medicamento + ":*");
                datomedicamento.setCod_esta(cliente.getCod_esta());
                datomedicamento.setId_farmacia(cliente.getId_farmacia());
                datomedicamento.setCadena10("medicamento");
                List listarMedicamentos = this.mi.getListarMedicamentos(datomedicamento);
                modelo.put("listarMedicamentos", listarMedicamentos);
                return new ModelAndView("administrarmedicamentos/ListarMedicamentos", modelo);
            } else {///70151083 norman waseres
                return new ModelAndView("Aviso", "mensaje", "Ya existe un id_medicamento igual");
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(id_medicamento)) || ("".equals(medicamento)) || ("".equals(ubicacion))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan Introducir Datos");
            }
            Medicamentos categoria_m = new Medicamentos();
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");

            int diax = Integer.parseInt(dia);
            int mesx = Integer.parseInt(mes) - 1;
            int aniox = Integer.parseInt(anio) - 1900;
            Date fec_nacimiento = new Date(aniox, mesx, diax);

            categoria_m.setId_medicamento(Integer.parseInt(id_medicamento));
            categoria_m.setMedicamento(medicamento);
            categoria_m.setCodsumi(codsumi);
            categoria_m.setUbicacion(ubicacion);
            categoria_m.setConcentra(concentra);
            categoria_m.setForma_far(forma_far);
            categoria_m.setPrecio_venta(Double.parseDouble(precio_venta));
            categoria_m.setCosto_unit(Double.parseDouble(costo_unit));
            categoria_m.setStockv(Double.parseDouble(stockv));
            categoria_m.setStocks(Double.parseDouble(stocks));
            categoria_m.setStockp(Double.parseDouble(stockp));
            categoria_m.setStock(Double.parseDouble(stock));
            categoria_m.setFecha_ven(fec_nacimiento);
            categoria_m.setNro_lote(nro_lote);
            categoria_m.setCodigo(Integer.parseInt(codigo));
            categoria_m.setB1(Integer.parseInt(b1));
            categoria_m.setTipo(tipo);
            categoria_m.setMax_emerg(Integer.parseInt(max_emerg));
            categoria_m.setMax_exter(Integer.parseInt(max_exter));
            categoria_m.setMax_inter(Integer.parseInt(max_inter));
            categoria_m.setMin_emerg(Integer.parseInt(min_emerg));
            categoria_m.setMin_exter(Integer.parseInt(min_exter));
            categoria_m.setMin_inter(Integer.parseInt(min_inter));
            categoria_m.setRestringido(Integer.parseInt(restringido));
            categoria_m.setId_farmacia(cliente.getId_farmacia());
            categoria_m.setCod_esta(cliente.getCod_esta());
            if ("".equals(id_grupo) || id_grupo == null) {////08/03/2018 para poder aumentar grupos
                id_grupo = "0";
                categoria_m.setSuma10(Integer.parseInt(id_grupo));
                categoria_m.setCadena10(" ");
            } else {
                categoria_m.setSuma1(Integer.parseInt(id_grupo));
                Medicamentos grupo = this.mi.getDatoGrupo(categoria_m);
                if (grupo != null) {
                    categoria_m.setCadena10(grupo.getCadena1());
                } else {
                    categoria_m.setCadena10(" ");
                }
            }
            try {
                this.mi.setModificarMedicamento(categoria_m);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La actualizacion no se cumplio, verifique los datos");
            }

            //listar categorias
            categoria_m.setId_farmacia(cliente.getId_farmacia());
            categoria_m.setMedicamento(medicamento);
            categoria_m.setCadena10("medicamento");
            List listarMedicamentos = this.mi.getListarMedicamentoSolo(categoria_m);
            modelo.put("listarMedicamentos", listarMedicamentos);
            return new ModelAndView("administrarmedicamentos/ListarMedicamentos", modelo);
        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if ("".equals(request.getParameter("id_medicamento"))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            Medicamentos elimina = new Medicamentos();
            elimina.setId_medicamento(Integer.parseInt(id_medicamento));

            try {
                this.mi.setEliminarMedicamento(elimina);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
            }

            //listar categorias
            List listarMedicamentos = this.mi.getListarMedicamentos(null);
            modelo.put("listarMedicamentos", listarMedicamentos);

            return new ModelAndView("administrarmedicamentos/ListarMedicamentos", modelo);
        }
        return new ModelAndView("administrarmedicamentos/ListarMedicamentos", modelo);
    }
}
