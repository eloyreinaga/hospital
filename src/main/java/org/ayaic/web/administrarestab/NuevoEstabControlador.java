package org.ayaic.web.administrarestab;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoEstabControlador {

    private final MiFacade mi;

    public NuevoEstabControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/NuevoEstab.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String tipodoc = request.getParameter("tipodoc");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] anios = {"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
        Date fecha1 = new Date();

        //Listar Departamentos
        List listaDepartamentos = this.mi.getListarPaisDep(1);
        modelo.put("listaDepartamentos", listaDepartamentos);

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        local.setArea("U");  ////getListarEstaUsua
        local.setCod_esta(cliente.getCod_esta());
        List Estab2 = this.mi.getListarEstaUsua(local);
        Localidades datoestab2 = (Localidades) Estab2.get(0);

        fecha1 = datol.getFecha();
        modelo.put("nit", datol.getNit().trim());
        modelo.put("num_auto", datol.getNum_auto().trim());
        modelo.put("llave", datol.getLlave().trim());
        modelo.put("num_fact", Integer.toString(datol.getNum_fact()));
        modelo.put("cod_esta", Integer.toString(datol.getCod_esta()));
        modelo.put("id_red", Integer.toString(datol.getId_red()));
        modelo.put("id_localidad", Integer.toString(datol.getId_localidad()));
        modelo.put("id_departamento", Integer.toString(datol.getId_departamento()));
        modelo.put("id_persona", Integer.toString(datol.getId_persona()));
        modelo.put("direccion", datol.getDireccion());
        modelo.put("tele1", datol.getTele1());
        modelo.put("tele2", datol.getTele2());
        modelo.put("maximo", Double.toString(datol.getMaximo()));
        modelo.put("minimo", Double.toString(datol.getMinimo()));
        modelo.put("izquierda", Integer.toString(datol.getIzquierda()));
        modelo.put("derecha", Integer.toString(datol.getDerecha()));
        modelo.put("superior", Integer.toString(datol.getSuperior()));
        modelo.put("inferior", Integer.toString(datol.getInferior()));
        modelo.put("id_tipo", Integer.toString(datol.getId_pais()));
        if (tipodoc != null) {
            modelo.put("id_tipo", tipodoc);
        }

        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));

        Localidades localidadred0 = new Localidades();
        localidadred0.setId_departamento(datol.getId_departamento());
        List red0 = this.mi.getListarRed(localidadred0);
        modelo.put("listaRed", red0);

        localidadred0.setId_red(datol.getId_red());
        List listaMuni0 = this.mi.getListarMuniRed(localidadred0);
        modelo.put("listaMuni", listaMuni0);

        localidadred0.setId_localidad(datol.getId_localidad());
        localidadred0.setId_estado("%");
        List Estab0 = this.mi.getListarEstab(localidadred0);
        modelo.put("listaEstab", Estab0);

        Personas persona = new Personas();
        persona.setCod_esta(cliente.getCod_esta());
        List listarPersonas0 = this.mi.getListarPersonasLocal(persona);
        modelo.put("listarPersonas", listarPersonas0);
        modelo.put("id_persona", datoestab2.getId_persona());

        //Cuando se eligio n departamento
        if (request.getParameter("id_departamento") != null) {
            int id_depto = Integer.parseInt(request.getParameter("id_departamento"));
            modelo.put("id_departamento", Integer.toString(id_depto));

            Localidades localidadred = new Localidades();
            localidadred.setId_departamento(id_depto);
            List red = this.mi.getListarRed(localidadred);
            modelo.put("listaRed", red);
        }
        if (request.getParameter("id_red") != null) {
            int id_depto = Integer.parseInt(request.getParameter("id_departamento"));
            int id_red = Integer.parseInt(request.getParameter("id_red"));
            modelo.put("id_departamento", Integer.toString(id_depto));
            modelo.put("id_red", Integer.toString(id_red));

            Localidades localidadred = new Localidades();
            localidadred.setId_departamento(id_depto);
            List red = this.mi.getListarRed(localidadred);
            modelo.put("listaRed", red);

            localidadred.setId_red(id_red);
            List listaMuni = this.mi.getListarMuniRed(localidadred);
            modelo.put("listaMuni", listaMuni);
        }

        //Cuando se elige una provincia
        if (request.getParameter("id_localidad") != null) {
            int id_departamento1 = Integer.parseInt(request.getParameter("id_departamento"));
            int id_localidad = Integer.parseInt(request.getParameter("id_localidad"));
            int id_red1 = Integer.parseInt(request.getParameter("id_red"));

            modelo.put("id_localidad", Integer.toString(id_localidad));

            Localidades localidadred = new Localidades();
            localidadred.setId_departamento(id_departamento1);
            List red = this.mi.getListarRed(localidadred);
            modelo.put("listaRed", red);

            localidadred.setId_red(id_red1);
            List listaMuni = this.mi.getListarMuniRed(localidadred);
            modelo.put("listaMuni", listaMuni);

            localidadred.setId_localidad(id_localidad);
            localidadred.setId_estado("%");
            List Estab = this.mi.getListarEstab(localidadred);
            modelo.put("listaEstab", Estab);
            modelo.put("id_persona", id_persona);

            //lista de persona que pueden ser ususarios del sistema
            //Personas persona=new Personas();
            //persona.setCod_esta(cliente.getCod_esta());
            List listarPersonas = this.mi.getListarPersonasLocal(persona);
            modelo.put("listarPersonas", listarPersonas);
        }

        //Para La primera vez que entra a la pagina
        if ("Guardar".equals(accion)) {
            String id_d = request.getParameter("id_departamento");
            String id_l = request.getParameter("id_localidad");
            String id_r = request.getParameter("id_red");
            String cod_e = request.getParameter("cod_esta");
            String id_p = request.getParameter("id_persona");
            String nit = request.getParameter("nit");
            String num_auto = request.getParameter("num_auto");
            String llave = request.getParameter("llave");
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String num_fact = request.getParameter("num_fact");
            String direccion = request.getParameter("direccion");
            String tele1 = request.getParameter("tele1");
            String tele2 = request.getParameter("tele2");
            String maximo = request.getParameter("maximo");
            String minimo = request.getParameter("minimo");
            String izquierda = request.getParameter("izquierda");
            String derecha = request.getParameter("derecha");
            String superior = request.getParameter("superior");
            String inferior = request.getParameter("inferior");
            Date fecha = new Date();
            int id_departamento1 = Integer.parseInt(request.getParameter("id_departamento"));
            int id_localidad = Integer.parseInt(request.getParameter("id_localidad"));
            int id_red1 = Integer.parseInt(request.getParameter("id_red"));
            int cod_esta = Integer.parseInt(request.getParameter("cod_esta"));
            if ("2".equals(tipodoc)) {
                llave = llave.trim();
                int diax = Integer.parseInt(dia);
                int mesx = Integer.parseInt(mes) - 1;
                int aniox = Integer.parseInt(anio) - 1900;
                fecha = new Date(aniox, mesx, diax);
            } else {
                llave = datol.getLlave();
                fecha = datol.getFecha();
                num_fact = Integer.toString(datol.getNum_fact());
                num_auto = datol.getNum_auto();
            }

            Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));

            if (("0".equals(id_d)) || ("0".equals(id_l)) || ("0".equals(id_r)) || ("0".equals(cod_e)) || ("0".equals(id_p))) {
                return new ModelAndView("Aviso", "mensaje", "Ingrese los datos obligatorios");
            } else {
                Localidades dato = new Localidades();
                dato.setId_localidad(id_localidad);
                dato.setId_red(id_red1);
                dato.setCod_esta(cod_esta);
                dato.setId_departamento(id_departamento1);
                dato.setNit(nit.trim());
                dato.setNum_auto(num_auto.trim());
                dato.setLlave(llave.trim());
                dato.setFecha(fecha);
                dato.setId_provincia(Integer.parseInt(id_persona));
                dato.setNum_fact(Integer.parseInt(num_fact));
                dato.setDireccion(direccion);
                dato.setTele1(tele1);
                dato.setTele2(tele2);
                dato.setMaximo(Double.parseDouble(maximo));
                dato.setMinimo(Double.parseDouble(minimo));
                dato.setIzquierda(Integer.parseInt(izquierda));
                dato.setDerecha(Integer.parseInt(derecha));
                dato.setInferior(Integer.parseInt(inferior));
                dato.setSuperior(Integer.parseInt(superior));
                dato.setId_pais(Integer.parseInt(tipodoc));
                try {
                    this.mi.setModificarEstab(dato);
                    return new ModelAndView("Aviso", "mensaje", "Los datos del Establecimiento, se grabaron Corrrectamente");
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan datos Establecimientos");
                }
            }
        }

        modelo.put("accion", accion);
        return new ModelAndView("administrarestablecimientos/NuevoEstab", modelo);
    }

}
