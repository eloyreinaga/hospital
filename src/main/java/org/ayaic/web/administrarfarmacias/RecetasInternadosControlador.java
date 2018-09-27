package org.ayaic.web.administrarfarmacias;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecetasInternadosControlador {

    private final MiFacade mi;

    public RecetasInternadosControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/RecetasInternados.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String _nombres = cliente.getNombres();
        String accion = request.getParameter("accion");
        String id_pedido = request.getParameter("id_pedido");
        String id_paciente = request.getParameter("id_paciente");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String num_cladoc = request.getParameter("num_cladoc");
        String id_historial = request.getParameter("id_historial");
        String id_medicamento = request.getParameter("id_medicamento");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        String sw = request.getParameter("sw");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        modelo.put("id_medicamento", id_medicamento);
        modelo.put("id_historial", id_historial);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_pedido", id_pedido);

        Historiales dato = new Historiales();/////lista las recetas del perfil de todos los pacientes del hopistal
        dato.setCod_esta(cliente.getCod_esta());
        List listarAtendidosI2 = this.mi.getRecetasInternados(dato);
        modelo.put("listarAtendidosI", listarAtendidosI2);

        if ("Buscar".equals(accion)) {
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            //Historiales dato= new Historiales(); 
            dato.setNombres(nombres);
            dato.setAccion("N");
            dato.setCod_esta(cliente.getCod_esta());
            List listarAtendidosI3 = this.mi.getRecetasInternadosNombre(dato);  ///getRecetasInternadosNombre
            modelo.put("listarAtendidosI", listarAtendidosI3);
        }

        if ("ListaReversiones".equals(accion)) {
            Recetas datore = new Recetas();
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB1(2);
            datore.setB2(2);
            datore.setExpedido("L");
            List listarKardexRev = this.mi.getListarKardexPerfilFar(datore);   ////getListarKardexPerfilFar
            modelo.put("listarPerfilRev", listarKardexRev);

            return new ModelAndView("administrarfarmacias/PerfilReversion", modelo);
        }

        if ("Revertir".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            String id_receta = request.getParameter("id_receta");
            String id_kardex = request.getParameter("id_kardex");

            Pacientes buscarPaciente = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);
            modelo.put("datomedica", buscarMedicamento);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB1(0);
            datore.setB2(0);
            datore.setExpedido("P");
            datore.setId_kardex(Integer.parseInt(id_kardex));
            datore.setCod_esta(cliente.getCod_esta());///05/06/2016
            datore.setId_estado("K");  ////setModificarKardexPaciente
            this.mi.setModificarKardexPaciente(datore);

            List listarKardex = this.mi.getListarKardex(datore);  ////getListarKardexPerfilFar carga nuevamente la lista de medicamentos del paciente
            modelo.put("listarKardexPerfil", listarKardex);
            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_detalle", id_detalle);
            modelo.put("id_kardex", id_kardex);
            modelo.put("id_receta", id_receta);

            return new ModelAndView("administrarfarmacias/VerPerfil", modelo);
        }

        if ("Perfil".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            String id_receta = request.getParameter("id_receta");
            String id_kardex = request.getParameter("id_kardex");

            Pacientes buscarPaciente = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);
            modelo.put("datomedica", buscarMedicamento);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB1(0);
            datore.setB2(0);
            List listarKardex = this.mi.getListarKardexPerfilFar(datore);  ////getListarKardexPerfilFar carga nuevamente la lista de medicamentos del paciente
            modelo.put("listarKardexPerfil", listarKardex);
            modelo.put("VerP", "Ver");

            datore.setB1(0); ////id_estado=0 kardexperfil, id_estado=1 inhabilitado paara verlo, id_estado=2 en lista reversion, id_estado=3 revertido  
            datore.setB2(0);
            datore.setId_medicamento(Integer.parseInt(id_medicamento));
            List listarKardexp = this.mi.getListaKardexPaciente(datore);
            modelo.put("listarKardex", listarKardexp);
            modelo.put("id_medicamento", id_medicamento);
            modelo.put("id_detalle", id_detalle);
            modelo.put("id_kardex", id_kardex);
            modelo.put("id_receta", id_receta);

            return new ModelAndView("administrarfarmacias/VerPerfil", modelo);
        }

        if ("ImprimirPerfil".equals(accion) || "ImprimirPerfil2".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            String id_receta = request.getParameter("id_receta");
            String id_kardex = request.getParameter("id_kardex");

            Pacientes buscarPaciente = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);

            dato.setAccion("U");
            dato.setId_historial(Integer.parseInt(id_historial));
            List listarAtendidosI3 = this.mi.getRecetasInternadosUnico(dato);  ///getRecetasInternadosUnico
            modelo.put("listarAtendidosI", listarAtendidosI3);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB1(0);
            datore.setB2(0);
            datore.setExpedido("G");
            List listarKardex = this.mi.getListarKardexPerfilFarGen(datore);   ////getListarKardexPerfilFarGen lista inicial del perfil
            modelo.put("listarKardexPerfil", listarKardex);
            datore.setExpedido("R");
            List listarKardexDet = this.mi.getListarKardexPerfilFarDet(datore);   ////getListarKardexPerfilFarDet para la impresion
            modelo.put("listarKardexPerfilDet", listarKardexDet);
            datore.setExpedido("3");
            List listarKardexDes = this.mi.getListaKardexPacienteDesg(datore);  ////getListaKardexPacienteDesg
            modelo.put("listarKardexDes", listarKardexDes);
            //datore.setExpedido("D");
            //List listarKardexFec = this.mi.getListarFechaPerfil(datore);   ////getListarFechaPerfil
            //datore.setExpedido("2");
            //List listarKardexFec2 = this.mi.getListarFechaPerfil2(datore);   ////getListarFechaPerfil2
            //modelo.put("listarFechaPerfil", listarKardexFec2);
            //datore.setExpedido("E");
            List listarKardexDat = this.mi.getListarFechaPerfildat(datore);   ////getListarFechaPerfildat
            modelo.put("listarPerfilDat", listarKardexDat);
            modelo.put("VerP", "Ver");
            modelo.put("dato", cliente);

            Recetas datorec = (Recetas) listarKardex.get(0);
            modelo.put("dia", Integer.toString(datorec.getFecha().getDate()));
            modelo.put("mes", Integer.toString(datorec.getFecha().getMonth() + 1));
            modelo.put("anio", Integer.toString(datorec.getFecha().getYear() + 1900));
            Recetas datorecdet = (Recetas) listarKardexDet.get(0);

            long fechaInicialMs = datorec.getFecha().getTime();
            long fechaFinalMs = datorecdet.getFecha().getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            //Date fechas[] ;
            //for (int i = 0; i < diass; i++){
            //    fechas[i]=datorec.getFecha();
            //}

            /*
        datore.setId_estado("I");
        datore.setB1(0);   ////id_estado=0 kardexperfil, id_estado=1 inhabilitado paara verlo, id_estado=2 en lista reversion, id_estado=3 revertido  
        datore.setId_medicamento(Integer.parseInt(id_medicamento)) ; 
        List listarKardexp = this.mi.getListaMedKardexEntregados(datore);////getListaKardexPaciente
        modelo.put("listarKardex", listarKardexp);
        modelo.put("id_medicamento", id_medicamento);
             */
            if ("ImprimirPerfil2".equals(accion)) {
                return new ModelAndView(new PerfilFarmacovigilancia2PDF(), modelo);
            }
            return new ModelAndView(new PerfilFarmacovigilanciaPDF(), modelo);
        }

        if ("EntregarM".equals(accion)) {
            String id_detalle = request.getParameter("id_detalle");
            String id_receta = request.getParameter("id_receta");
            String id_kardex = request.getParameter("id_kardex");
            String cantidad = request.getParameter("cantidad");
            String observa = request.getParameter("observa");
            String indica = request.getParameter("indicacion");
            int ent = 0, sal = 0, sald = 0, id_hl = 0, id_m = 0, id_h = 0, id_d = 0, id_r = 0, id_p = 0, id_i = 0, dosif = 0;

            Pacientes buscarPaciente = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);
            modelo.put("id_kardex", id_kardex);

            if ("".equals(id_medicamento) || id_medicamento == null) {
                return new ModelAndView("Aviso", "mensaje", "Primero debe elegir el Medicamento");
            }

            Recetas datore = new Recetas();
            datore.setB1(0);   ////id_estado=0 kardexperfil, id_estado=1 inhabilitado paara verlo, id_estado=2 en lista reversion, id_estado=3 revertido  
            datore.setB2(0);
            datore.setId_pedido(Integer.parseInt(id_pedido));
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setId_medicamento(Integer.parseInt(id_medicamento));
            datore.setEntradas(0);
            datore.setCadena1(observa);
            datore.setId_farmacia(cliente.getId_farmacia());
            List listaKardexp = this.mi.getListaKardexPacienteIndi(datore);

            for (int j = 0; j < listaKardexp.size(); j++) {
                Recetas dator = (Recetas) listaKardexp.get(j);
                ent += dator.getEntradas();
                sal += dator.getSalidas();
                sald += dator.getSaldos();
                id_i = dator.getSuma1();
                id_hl = dator.getId_historial();
                id_h = dator.getId_historia();
                id_m = dator.getId_medico();
                id_r = dator.getId_receta();
                id_p = dator.getId_pedido();
                id_d = dator.getId_detalle();
                dosif = dator.getDosifica();
                indica = dator.getIndicacion();
            }
            if (sald < Integer.parseInt(cantidad)) {
                return new ModelAndView("Aviso", "mensaje", "La cantidad NO ES CORRECTO , no existe Saldo Suficiente");
            }
            if (sald == Integer.parseInt(cantidad)) {   ///para cerrarlo el medicamento y finalizar
                datore.setSaldos(0);
                datore.setId_prestacion(1);
            } else {
                datore.setSaldos(sald - Integer.parseInt(cantidad));
            }

            datore.setSalidas(Integer.parseInt(cantidad));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_kardex(Integer.parseInt(id_kardex));
            datore.setId_medico(id_m);
            datore.setId_detalle(id_d);
            datore.setId_receta(id_r);
            datore.setId_pedido(id_p);
            datore.setSuma1(id_i);
            datore.setDosifica(dosif);
            datore.setId_persona(cliente.getId_persona());
            datore.setId_factura(0);
            datore.setId_historial(id_hl);
            datore.setId_historia(id_h);
            datore.setNro_lote("SL");
            datore.setExpedido("I");  //////setCrearKardexPaciente
            datore.setIndicacion(indica);
            datore.setCadena1(observa);
            this.mi.setCrearKardexPaciente(datore);
            //int iResultado = this.mi.setRegistrarKardex(datore);

            Medicamentos medic = new Medicamentos();
            medic.setId_medicamento(Integer.parseInt(id_medicamento));
            medic.setCod_esta(cliente.getCod_esta());
            medic.setId_farmacia(cliente.getId_farmacia());
            Medicamentos buscarMedicamento = this.mi.getDatosMedicamento(medic);
            modelo.put("datomedica", buscarMedicamento);

            //Recetas datore=new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB2(0);
            datore.setB1(0);   ////id_estado=0 kardexperfil, id_estado=1 inhabilitado paara verlo, id_estado=2 en lista reversion, id_estado=3 revertido  
            List listarKardex = this.mi.getListarKardexPerfilFar(datore);////getListarKardexPerfilFar
            modelo.put("listarKardexPerfil", listarKardex);
            modelo.put("VerP", "Ver");

            datore.setId_estado("K");   /////getListaKardexPaciente
            datore.setB1(0);   ////id_estado=0 kardexperfil, id_estado=1 inhabilitado paara verlo, id_estado=2 en lista reversion, id_estado=3 revertido  
            datore.setB2(0);
            datore.setId_medicamento(Integer.parseInt(id_medicamento));
            List listarKardexp = this.mi.getListaKardexPaciente(datore);
            modelo.put("listarKardex", listarKardexp);
            List listaKardexp2 = this.mi.getListaKardexPacienteIndi(datore);
            Recetas dator = (Recetas) listaKardexp2.get(0);  ///saca nuevamente id id_kardex del ultimo insertado por si quiere volver a entregar
            modelo.put("id_kardex", Integer.toString(dator.getId_kardex()));

            return new ModelAndView("administrarfarmacias/VerPerfil", modelo);
        }

        if ("verreceta".equals(accion)) {

            String id_historia = request.getParameter("id_historia");
            String id_persona = request.getParameter("id_persona");

            Pacientes buscarPaciente = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datos", buscarPaciente);

            Recetas datore = new Recetas();
            datore.setId_historial(Integer.parseInt(id_historial));
            datore.setCod_esta(cliente.getCod_esta());
            datore.setId_farmacia(cliente.getId_farmacia());
            datore.setB1(0);
            datore.setB2(0);
            List listarKardex = this.mi.getListarKardexPerfilFar(datore);   ////getListarKardexPerfilFar
            modelo.put("listarKardexPerfil", listarKardex);

            return new ModelAndView("administrarfarmacias/VerPerfil", modelo);
        }

        //List listarAtendidosI = this.mi.getInternados(dato);
        //modelo.put("listarAtendidosI", listarAtendidosI);
        return new ModelAndView("administrarfarmacias/RecetasInternados", modelo);
    }
}
