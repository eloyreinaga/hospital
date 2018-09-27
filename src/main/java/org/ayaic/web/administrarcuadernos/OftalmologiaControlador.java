package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OftalmologiaControlador {

    private final MiFacade mi;

    public OftalmologiaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Oftalmologia.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();
        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String id_persona = request.getParameter("id_persona");
        String id_paciente = request.getParameter("id_paciente");
        String id_historial = request.getParameter("id_historial");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_oftal = request.getParameter("id_oftal");
        String situa = request.getParameter("situa");
        String ap = request.getParameter("ap");
        String af = request.getParameter("af");
        String krod = request.getParameter("krod");
        String os = request.getParameter("os");
        String lensometro = request.getParameter("lensometro");
        String ncd = request.getParameter("ncd");
        String phd = request.getParameter("phd");
        String ccd = request.getParameter("ccd");
        String mcd = request.getParameter("mcd");
        String cid = request.getParameter("cid");
        String nci = request.getParameter("nci");
        String phi = request.getParameter("phi");
        String cci = request.getParameter("cci");
        String mci = request.getParameter("mci");
        String cii = request.getParameter("cii");
        String esferad = request.getParameter("esferad");
        String cilindrod = request.getParameter("cilindrod");
        String ejed = request.getParameter("ejed");
        String addd = request.getParameter("addd");
        String pdd = request.getParameter("pdd");
        String esferai = request.getParameter("esferai");
        String cilindroi = request.getParameter("cilindroi");
        String ejei = request.getParameter("ejei");
        String addi = request.getParameter("addi");
        String pdi = request.getParameter("pdi");
        String keratd = request.getParameter("keratd");
        String kerati = request.getParameter("kerati");
        String dx = request.getParameter("dx");
        String rx = request.getParameter("rx");

        //Listar Departamentos
        List listaDepartamentos = mi.getListarPaisDep(1);
        modelo.put("listaDepartamentos", listaDepartamentos);
        Localidades local = new Localidades();
        List Estab1 = mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        local.setId_departamento(datoestab.getId_departamento());
        local.setId_persona(10);
        local.setArea("C");
        List Estab = mi.getEstabTransCns(local);
        modelo.put("listaEstab", Estab);
        modelo.put("datoestab", datoestab);
        modelo.put("id_rol", Integer.toString(cliente.getId_rol2()));

        Pacientes buscarPacienteh = mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPacienteh);
        modelo.put("datosp", buscarPacienteh);
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = mi.getDatosHistorial(datohi);
        Personas buscarEmpleado = mi.getDatosPersonaInt(cliente.getId_persona());
        modelo.put("empleado", buscarEmpleado);
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setId_estado("T");
        List listarCargos = mi.getListarConsultoriosTransf(a);
        modelo.put("listarCargos", listarCargos);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("id_red", Integer.toString(datoestab.getId_red()));
        modelo.put("id_localidad", Integer.toString(datoestab.getId_localidad()));
        modelo.put("id_departamento", Integer.toString(datoestab.getId_departamento()));
        modelo.put("id_consultorio", Integer.toString(cliente.getId_consultorio()));
        modelo.put("direccion", datoestab.getDireccion());
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_persona", id_persona);
        modelo.put("id_historial", id_historial);
        modelo.put("id_reservacion", id_historial);
        modelo.put("situa", situa);
        modelo.put("ap", ap);
        modelo.put("af", af);
        modelo.put("krod", krod);
        modelo.put("os", os);
        modelo.put("lensometro", lensometro);
        modelo.put("ncd", ncd);
        modelo.put("phd", phd);
        modelo.put("ccd", ccd);
        modelo.put("mcd", mcd);
        modelo.put("cid", cid);
        modelo.put("nci", nci);
        modelo.put("phi", phi);
        modelo.put("cci", cci);
        modelo.put("mci", mci);
        modelo.put("cii", cii);
        modelo.put("esferad", esferad);
        modelo.put("cilindrod", cilindrod);
        modelo.put("ejed", ejed);
        modelo.put("addd", addd);
        modelo.put("pdd", pdd);
        modelo.put("esferai", esferai);
        modelo.put("cilindroi", cilindroi);
        modelo.put("ejei", ejei);
        modelo.put("addi", addi);
        modelo.put("pdi", pdi);
        modelo.put("keratd", keratd);
        modelo.put("kerati", kerati);
        modelo.put("dx", datosHistorial.getDiagnostico());
        modelo.put("rx", datosHistorial.getAccion());
        modelo.put("id_oftal", id_oftal);
        modelo.put("tipo_medico", Integer.toString(cliente.getId_cargo()));
        modelo.put("expedido", datosHistorial.getExpedido());

        Cuadernos datot = new Cuadernos();
        datot.setId_historial(Integer.parseInt(id_historial));
        datot.setCod_esta(cliente.getCod_esta());

        datot.setTipoconsulta("P");  ////getListarEpicrisis
        datot.setCod_esta(cliente.getCod_esta());
        datot.setId_persona(cliente.getId_persona());
        datot.setId_paciente(Integer.parseInt(id_paciente));
        datot.setId_historial(Integer.parseInt(id_historial));
        List listarEpis = mi.getListarEpicrisis(datot);
        modelo.put("listarEpis", listarEpis);
        datot.setTipoconsulta("E");   ////getVerEpicrisis
        List listaEpi = mi.getVerEpicrisis(datot);
        modelo.put("condicion", "1");
        modelo.put("causa", "1");

        if ("ModificaEpi".equals(accion)) {
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("Q");  ////getDatoEpicrisis
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_oftal));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaEpis = mi.getDatoEpicrisis(dato);
            Cuadernos listep = (Cuadernos) listaEpis.get(0);
            modelo.put("listaEpi", listaEpis);
            modelo.put("Listep", listep);
            modelo.put("anio", Integer.toString(listep.getFechap().getYear() + 1900));
            modelo.put("mes", Integer.toString(listep.getFechap().getMonth() + 1));
            modelo.put("dia", Integer.toString(listep.getFechap().getDate()));
            modelo.put("hora", Integer.toString(listep.getFechap().getHours()));
            modelo.put("minuto", Integer.toString(listep.getFechap().getMinutes()));
            modelo.put("anio2", Integer.toString(listep.getFechae().getYear() + 1900));
            modelo.put("mes2", Integer.toString(listep.getFechae().getMonth() + 1));
            modelo.put("dia2", Integer.toString(listep.getFechae().getDate()));
            modelo.put("hora2", Integer.toString(listep.getFechae().getHours()));
            modelo.put("minuto2", Integer.toString(listep.getFechae().getMinutes()));
            modelo.put("clinicos", listep.getCadena1());
            modelo.put("diagnostico", listep.getCadena2());
            modelo.put("diagnosticoegr", listep.getCadena3());
            modelo.put("condicion", Integer.toString(listep.getSuma5()));
            modelo.put("causa", Integer.toString(listep.getSuma6()));
            modelo.put("examenes", listep.getCadena6());
            modelo.put("tratquirurgico", listep.getCadena7());
            modelo.put("tratmedico", listep.getCadena8());
            modelo.put("complicaciones", listep.getCadena9());
            modelo.put("pronosticovital", listep.getCadena10());
            modelo.put("pronosticofuncional", listep.getCadena11());
            modelo.put("control", listep.getCadena12());
            modelo.put("policlinico", listep.getCadena13());
            modelo.put("hospital", listep.getCadena14());
            modelo.put("recomendaciones", listep.getCadena15());
            modelo.put("id_piso", Integer.toString(listep.getSuma1()));
            modelo.put("id_sala", Integer.toString(listep.getSuma2()));
            modelo.put("id_cama", Integer.toString(listep.getSuma3()));
            modelo.put("id_consultorio", Integer.toString(listep.getId_consultorio()));
            modelo.put("id_oftal", id_oftal);
            return new ModelAndView("administrarcuadernos/Epicrisis", modelo);
        }

        if ("ImprimirEpi".equals(accion)) {

            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setTipoconsulta("Q");
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_laboratorio(Integer.parseInt(id_oftal));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaEpis = mi.getCuadernoC1(dato);  ////getDatoEpicrisis
            Cuadernos datoep = (Cuadernos) listaEpi.get(0);
            modelo.put("listaEpi", listaEpis);
            Camas buscarCama = mi.getDatosCama(datoep.getSuma3());
            modelo.put("buscarCama", buscarCama);
            Personas buscarEmpleadoi = mi.getDatosPersonaInt(datoep.getId_persona());
            modelo.put("empleado", buscarEmpleadoi);
            Personas buscaResid = mi.getDatosPersonaInt(datoep.getId_por());
            modelo.put("emplResid", buscaResid);
            local.setArea("E");   ////getDatosEstable
            local.setCod_esta(datosHistorial.getCod_esta());
            Localidades buscarestab = this.mi.getDatosEstable(local);
            modelo.put("datoestab", buscarestab);

            return new ModelAndView(new EpicrisisPDF(), modelo);
        }

        if ("Guardar".equals(accion)) {
            String cod_esta = request.getParameter("cod_esta");

            Personas buscarEmpleado2 = mi.getBuscarPersona(Integer.parseInt(id_persona));
            Consultorios c = mi.getDatosConsultorio(cliente.getId_consultorio());
            modelo.put("dato", cliente);
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_historial));
            dato.setId_persona(cliente.getId_persona());
            dato.setId_consultorio(Integer.parseInt(id_consultorio));
            if (cliente.getId_especialidad() == 99) {
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setId_consultorio(Integer.parseInt(id_consultorio));
            }
            dato.setCadena1(situa);
            dato.setCadena2(ap);
            dato.setCadena3(af);
            dato.setCadena4(krod);
            dato.setCadena5(os);
            dato.setCadena6(lensometro);
            dato.setCadena7(ncd);
            dato.setCadena8(phd);
            dato.setCadena9(ccd);
            dato.setCadena10(mcd);
            dato.setCadena11(cid);
            dato.setCadena12(nci);
            dato.setCadena13(phi);
            dato.setCadena14(cci);
            dato.setCadena15(mci);
            dato.setCadena16(cii);
            dato.setCadena17(esferad);
            dato.setCadena18(cilindrod);
            dato.setCadena19(ejed);
            dato.setCadena20(addd);
            dato.setCadena21(pdd);
            dato.setCadena22(esferai);
            dato.setCadena23(cilindroi);
            dato.setCadena24(ejei);
            dato.setCadena25(addi);
            dato.setCadena26(pdi);
            dato.setCadena27(keratd);
            dato.setCadena28(kerati);
            dato.setCadena29(dx);
            dato.setCadena30(rx);
            dato.setId_seguro(datosHistorial.getId_seguro());
            dato.setId_por(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipoconsulta("O");
            //List listaAdmi = mi.getCuadernoC1(dato);  ////getVerEpicrisis
            //if(listaAdmi.isEmpty()){
            dato.setAccion("O");
            mi.setCrearOftalmologia(dato);   ///setCrearOftalmologia
            //}else{
            //try{
            //     dato.setAccion("E");   ////setModificarEpicrisis
            //    dato.setId_laboratorio(Integer.parseInt(id_oftal));
            //     mi.setModificarCuaderno1(dato);   ////setModificarEpicrisis
            /// }catch (Exception e){ 
            //    return new ModelAndView("Aviso","mensaje","Consulte Administrador Epicrisis");
            // }     
            //}
            dato.setTipoconsulta("E");    ////getVerEpicrisis
            List listaEpi2 = mi.getVerEpicrisis(dato);
            Cuadernos datoad = (Cuadernos) listaEpi2.get(0);
            modelo.put("listaEpis", listaEpi2);
        }
        List listarEpi3 = mi.getCuadernoC1(datot);
        modelo.put("listarEpis", listarEpi3);
        modelo.put("accion", accion);
        return new ModelAndView("administrarcuadernos/HistoriaOftalmologia", modelo);
    }

}
