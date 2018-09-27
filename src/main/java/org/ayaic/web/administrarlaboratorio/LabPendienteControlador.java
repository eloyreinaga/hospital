package org.ayaic.web.administrarlaboratorio;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LabPendienteControlador {

    private final MiFacade mi;

    public LabPendienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/LabPendiente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accionlab = request.getParameter("accionlab");
        Personas persona = this.mi.getBuscarPersona(cliente.getId_persona()); // saca un registro a ser modificado
        Consultorios datosconsul = this.mi.getDatosConsultorio(persona.getId_consultorio()); // saca un registro a ser modificado
        String id_pedido = request.getParameter("id_pedido");
        String estab = request.getParameter("estab1");
        String medico = request.getParameter("medico1");
        String numero = request.getParameter("numero");
        String[] dias = {"--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {"----", (Integer.toString(anioq)), (Integer.toString(anioq - 1))};
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String tipo = "L";
        String accion = request.getParameter("accion");
        String id_costo = request.getParameter("id_costo");
        String id_historial = request.getParameter("id_historial");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_cuaderno = request.getParameter("id_cuaderno");
        String id_detalle = request.getParameter("id_detalle");
        String id_paciente = request.getParameter("id_paciente");
        String id_persona = request.getParameter("id_persona");
        String id_persona2 = request.getParameter("id_persona2");
        String expedido = request.getParameter("expedido");
        //solo hemograma
        String grojo = request.getParameter("grojo");
        String gblanco = request.getParameter("gblanco");
        String plaqueta = request.getParameter("plaqueta");
        String hemoglobina = request.getParameter("hemoglobina");
        String hematocrito = request.getParameter("hematocrito");
        String vcm = request.getParameter("vcm");
        String hgm = request.getParameter("hgm");
        String chcm = request.getParameter("chcm");
        String bas = request.getParameter("bas");
        String eos = request.getParameter("eos");
        String mielo = request.getParameter("mielo");
        String juy = request.getParameter("juy");
        String cay = request.getParameter("cay");
        String seg = request.getParameter("seg");
        String linf = request.getParameter("linf");
        String mono = request.getParameter("mono");
        String coagulacion = request.getParameter("coagulacion");
        String sangria = request.getParameter("sangria");
        String protombina = request.getParameter("protombina");
        String actividad = request.getParameter("actividad");
        String grupo = request.getParameter("grupo");
        String factor = request.getParameter("factor");

        String sroja = request.getParameter("sroja");
        String leve = request.getParameter("leve");
        String sblanca = request.getParameter("sblanca");
        String moderado = request.getParameter("moderado");
        String splaquetas = request.getParameter("splaquetas");
        String tproto = request.getParameter("tproto");
        String severo = request.getParameter("severo");
        String ves = request.getParameter("ves");
        String reti = request.getParameter("reti");
        String ireti = request.getParameter("ireti");
        String otro = request.getParameter("otro");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);

        Personas persona2 = new Personas();
        persona2.setCod_esta(cliente.getCod_esta());
        persona2.setId_persona(cliente.getId_persona());
        persona2.setDip("L");          ///////getDatosPersonaConsulLabos
        persona2.setUrgencias(cliente.getId_almacen());
        List buscarEmpleado = this.mi.getDatosPersonaConsulLabos(persona2);
        modelo.put("listaPersonas", buscarEmpleado);
        modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_historial));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = mi.getDatosHistorial(datohi);

        if (id_persona == null || "".equals(id_persona) || "0".equals(id_persona)) {
            id_persona = Integer.toString(cliente.getId_persona());
        }

        String hemograma = "<u>HEMOGRAMA:  </u>" + "G.Rojos.=" + grojo + ";G.Blancos.=" + gblanco + ";Plaquetas=" + plaqueta + ";Hemoglobina =" + hemoglobina
                + ";Hematocrito=" + hematocrito + ". ;V.C.M=" + vcm + ";H.G.M=" + hgm + ";C.H.C.M=" + chcm + "<u>RECUENTO DIFERENCIAL DE LEUCOCITOS:  </u><p>"
                + ";Bas=" + bas + ";Eos=" + eos + ";Mielo=" + mielo + ";Juy =" + juy + ";Cay=" + cay + ";Seg=" + seg + ";Linf.=" + linf + ";Mono=" + mono
                + "</p><u>MORFOLOGIA SANGUNEA:  </u><p>"
                + ";S.Roja=" + sroja + ";Leve=" + leve + ";S.blanca=" + sblanca + ";Moderado=" + moderado
                + "</p><p>;Plaqueta=" + splaquetas + ";Severo=" + severo + ";V.E.S=" + ves + "mm/h"
                + ";Reticulositos=" + reti + "%(0,5a 2%);Ind.Reticulocitario=" + ireti + ";Otro=" + otro + "</p>";
        //fin hemograma
        // comienza vaiables de Orina
        String volumen = request.getParameter("volumen");
        String color = request.getParameter("color");
        String aspecto = request.getParameter("aspecto");
        String sedimento = request.getParameter("sedimento");
        String olor = request.getParameter("olor");
        String proteina = request.getParameter("proteina");
        String sangre = request.getParameter("sangre");
        String espuma = request.getParameter("espuma");
        String densidad = request.getParameter("densidad");
        String reaccion = request.getParameter("reaccion");
        String bilirrubina = request.getParameter("bilirrubina");
        String urabili = request.getParameter("urabili");
        String nitrito = request.getParameter("nitrito");
        String glucosa = request.getParameter("glucosa");
        String cetona = request.getParameter("cetona");
        String acido = request.getParameter("acido");
        String sales = request.getParameter("sales");
        String pigmentos = request.getParameter("pigmentos");
        String uro = request.getParameter("uro");
        String hemo = request.getParameter("hemo");
        String cel = request.getParameter("cel");
        String crista = request.getParameter("crista");
        String epiteli = request.getParameter("epiteli");
        String leuco = request.getParameter("leuco");
        String bacteria = request.getParameter("bacteria");
        String piocitos = request.getParameter("piocitos");
        String otros = request.getParameter("otros");
        String hematies = request.getParameter("hematies");
        String leuco2 = request.getParameter("leuco2");
        String sangre2 = request.getParameter("sangre2");
        String motros = request.getParameter("motros");
        String cilindros = request.getParameter("cilindros");
        String granulosos = request.getParameter("granulosos");
        String hialianos = request.getParameter("hialianos");
        String leucocitarios = request.getParameter("leucocitarios");
        String cristales = request.getParameter("cristales");
        String observa = request.getParameter("observa");
        String examen = request.getParameter("examen");

        String orina = "<u>EXAMEN FISICO:  </u>" + "<p>Vol.Recibido=" + volumen + ";Color=" + color + ";Olor=" + olor + "</p><p>;Aspecto=" + aspecto + ";Reaccion=" + reaccion
                + ";Espuma=" + espuma + "</p><p> ;Densidad=" + densidad + ";Sedimento=" + sedimento + "</p><u>EXAMEN QUIMICO:  </u>"
                + ";Nitrito=" + nitrito + ";Glucosa=" + glucosa + ";Sangre/Hemoglobina=" + sangre + ";Cetona=" + cetona + ";Bilirrubina=" + bilirrubina
                + ";Urobilina=" + uro + ";Albumina/Proteina=" + proteina + ";Pig.Biliares=" + pigmentos + ";Aci.Diecetico=" + acido + ";Sal_Biliares=" + sales
                + ";Leucocitos=" + leuco2 + "<u>EXAMEN MICROSCOPICO DE SEDIMENTO:  </u>" + "<p>;Cel.Epiteliales=" + epiteli + ";Leucositos=" + leuco
                + ";Cristales=" + cristales + "</p><p>;Hematies=" + hematies + ";Piocitos=" + piocitos + ";Bacterias=" + bacteria + "</p><p>;Cilindros=" + cilindros
                + ";Granulosos=" + granulosos + ";Hialianos=" + hialianos + "</p><p>;Leucositarios=" + leucocitarios + ";Cristales=" + cristales + ";Otros=" + otros
                + ";Observaciones=" + observa + "</p>";
        //fin fr variebles de Orina
        if (persona.getUrgencias() == 1) {
            String orina2 = "<u>EXAMEN MICROSCOPICO DE SEDIMENTO:  </u>" + "<p>;Cel.Epiteliales=" + epiteli + ";Leucositos=" + leuco
                    + ";Cristales=" + cristales + "</p><p>;Hematies=" + hematies + ";Piocitos=" + piocitos + ";Bacterias=" + bacteria + "</p><p>;Cilindros=" + cilindros
                    + ";Granulosos=" + granulosos + ";Hialianos=" + hialianos + "</p><p>;Leucositarios=" + leucocitarios + ";Cristales=" + cristales + ";Otros=" + otros
                    + ";Observaciones=" + observa + "</p>";
            orina = orina2;
        }
        String realizalab = "no";

        String q109 = request.getParameter("q109");///bilirrubina totales
        String q114 = request.getParameter("q114");///Proteinuria en 24 horas
        String q117 = request.getParameter("q117");///creatinina
        String q125 = request.getParameter("q125");///Fosfatasa Acida
        String q131 = request.getParameter("q131");///Glicemia/Glucosa
        String q141 = request.getParameter("q141");///acido urico
        String q248 = request.getParameter("q248");///BUM 
        String q147 = request.getParameter("q147");///albumina
        String q148 = request.getParameter("q148");///Colesterol
        String q149 = request.getParameter("q149");///TRIGLICERIDOS
        String q150 = request.getParameter("q150");///UREA
        String q163 = request.getParameter("q163");///amilasa
        String q164 = request.getParameter("q164");///Calcio
        String q167 = request.getParameter("q167");///HDLc
        String q168 = request.getParameter("q168");///LDLc
        String q200 = request.getParameter("q200");///bilirrubina directa
        String q201 = request.getParameter("q201");///bilirrubina indirecta
        String q202 = request.getParameter("q202");///creatinina
        String q203 = request.getParameter("q203");///Fosfatasa Alcalina
        String q204 = request.getParameter("q204");///Fosfatasa fraccion Prostatica
        String q205 = request.getParameter("q205");///Fosforo
        String q206 = request.getParameter("q206");///Fosforo
        String q207 = request.getParameter("q207");///Transaminasas T.G.P.
        String q208 = request.getParameter("q208");///Proteinas Totales
        String q209 = request.getParameter("q209");///VLDLc
        String q210 = request.getParameter("q210");///Sodio
        String q211 = request.getParameter("q211");///Potasio
        String q212 = request.getParameter("q212");///Cloro
        String q213 = request.getParameter("q213");///Magnesio
        String q214 = request.getParameter("q214");///CPK-MB
        String q215 = request.getParameter("q215");///GGT
        String q216 = request.getParameter("q216");///LIPASA
        String q217 = request.getParameter("q217");///NUS
        String q218 = request.getParameter("q218");///Hb Glicosilada A 1c 
        String q219 = request.getParameter("q219");///medio mal Creatinuria 24 hr
        String q220 = request.getParameter("q220");///DCE
        String q152 = request.getParameter("q152");///LDH
        String q249 = request.getParameter("q249");///ALcoholemia
        //String q153 = request.getParameter("q153");///ASTO
        String q155 = request.getParameter("q155");///LATEX
        String q607 = request.getParameter("q607");///PCR
        //String q132 = request.getParameter("q132");///Reaccion Widal
        String q171 = request.getParameter("q171");///Troponina
        String q971 = request.getParameter("q971");///igual 218 hemoglobina glucosilada debe cambiar test tolerancia a la glucosa

        String accioncurativa[][] = {{"171", q171}, {"607", q607}, {"155", q155}, 
                                     {"109", q109}, {"114", q114}, {"117", q117}, {"125", q125}, {"131", q131}, 
                                     {"248", q248}, {"141", q141}, {"147", q147}, {"148", q148}, {"149", q149}, 
                                     {"150", q150}, {"163", q163}, {"164", q164}, {"167", q167}, {"168", q168}, 
                                     {"200", q200}, {"201", q201}, {"202", q202}, {"203", q203}, {"204", q204}, 
                                     {"205", q205}, {"206", q206}, {"207", q207}, {"208", q208}, {"209", q209}, 
                                     {"210", q210}, {"211", q211}, {"212", q212}, {"213", q213}, {"214", q214}, 
                                     {"215", q215}, {"216", q216}, {"217", q217}, {"218", q218}, {"219", q219}, 
                                     {"220", q220}, {"152", q152}, {"249", q249}, {"971", q971}};

        String q112 = request.getParameter("q112"); ///Proteina C Reactiva-PCR
        String q118 = request.getParameter("q118"); ///Prueba rapida para sifilis
        String q123 = request.getParameter("q123"); ///LATEX FR(Factor Reumatoide Cuantitivo)
        String q132 = request.getParameter("q132");///Reaccion Widal
        String q133 = request.getParameter("q133"); ///Gota Gruesa (malaria) y frotis sanguineo+tincion
        String q134 = request.getParameter("q134"); ///RPR para Sifilis-VDRL
        String q139 = request.getParameter("q139"); ///Inmonoglobulinas IgG,IgM,IgA
        String q153 = request.getParameter("q153");///ASTO
        String q154 = request.getParameter("q154"); ///Prueba Rapida para VIH/SIDA
        String q157 = request.getParameter("q157"); ///Helycobacter Pylori (suero)
        String q221 = request.getParameter("q221"); ///Hepatitis A
        String q222 = request.getParameter("q222"); ///Hepatitis B
        String q223 = request.getParameter("q223"); ///Hepatitis C
        String q224 = request.getParameter("q224"); ///PSA
        String q225 = request.getParameter("q225"); ///Dosificacion HGC
        String q227 = request.getParameter("q227"); ///ELISA Chagas
        String q228 = request.getParameter("q228"); ///ELISA Toxoplasmosis
        String q229 = request.getParameter("q229"); ///ELISA Dengue
        String q231 = request.getParameter("q231"); ///ELISA Rubeola
        String q232 = request.getParameter("q232"); ///ELISA Fiebre Amarilla
        String q233 = request.getParameter("q233"); ///ELISA Hepatitis A, B
        String q234 = request.getParameter("q234"); ///T3 
        String q235 = request.getParameter("q235"); ///T4
        String q236 = request.getParameter("q236"); ///T4 Libre
        String q237 = request.getParameter("q237"); ///TSH ULTRA
        String q602 = request.getParameter("q602"); ///Anticuerpos ANTI TGO
        String q603 = request.getParameter("q603"); ///Anticuerpos ANTI TP
        String q651 = request.getParameter("q651"); ///Anti­gen prostatico especifico total y libre
        String q738 = request.getParameter("q738"); ///Chagas HAI
        String q741 = request.getParameter("q741"); ///Anti­gen prostatico especifico total y libre
        String q812 = request.getParameter("q812"); ///Toxoplasmosis  MET HAI
        

        String accionSero[][] = {{"112", q112},{"118", q118},{"123", q123}, {"132", q132}, {"133", q133}, {"134", q134}, {"139", q139},
                                 {"153", q153},{"154", q154},{"157", q157},{"221", q221}, {"222", q222}, {"223", q223}, {"224", q224},
                                 {"225", q225},{"227", q227},{"228", q228}, {"229", q229}, {"231", q231}, {"232", q232},
                                 {"233", q233},{"234", q234},{"235", q235}, {"236", q236}, {"237", q237}, {"602", q602}, 
                                 {"603", q603},{"651", q651},{"738", q738}, {"741", q741}, {"812", q812}};

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        modelo.put("id_costo", id_costo);
        modelo.put("id_historial", id_historial);
        modelo.put("id_cuaderno", id_cuaderno);
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_pedido", id_pedido);
        //modelo.put("id_persona", id_persona);
        modelo.put("id_persona2", id_persona2);
        modelo.put("id_detalle", id_detalle);
        modelo.put("expedido", expedido);

        if (id_detalle == null || "".equals(id_detalle)) {
            id_detalle = "0";
        }

        if (id_costo != null && !"".equals(id_costo) && !"0".equals(id_costo)) {
            Costos costo = new Costos();
            costo.setId_costo(Integer.parseInt(id_costo));
            costo.setCod_esta(cliente.getCod_esta());
            Costos buscarCosto = this.mi.getDatosCosto(costo);
            id_laboratorio = Integer.toString(buscarCosto.getId_laboratorio());
            if (Integer.parseInt(id_costo) == 143) {///25-03-2017
                id_laboratorio = "19";
            }
        }
        modelo.put("id_laboratorio", id_laboratorio);

        if ("Grabar".equals(accion)) {
            Cuadernos datol = new Cuadernos();
            datol.setNombre(estab);
            datol.setNombres(medico);
            datol.setId_pedido(Integer.parseInt(id_pedido));
            datol.setNumpieza(Integer.parseInt(numero));
            datol.setId_persona(cliente.getId_persona());
            datol.setCod_esta(cliente.getCod_esta());
            datol.setFechae(fecha1);
            datol.setEstado("B");
            this.mi.setModificarPedidoLab(datol);
        }
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("datos", buscarPaciente);

        Cuadernos dato = new Cuadernos();
        dato.setTipoconsulta("I");
        dato.setId_historial(Integer.parseInt(id_historial));
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setId_pedido(Integer.parseInt(id_pedido));
        dato.setCod_esta(cliente.getCod_esta());
        if (cliente.getId_laboratorio() == 0) {
            dato.setId_cargo(0);
            dato.setId_laboratorio(999);
        } else {
            dato.setId_cargo(cliente.getId_laboratorio());
            dato.setId_laboratorio(cliente.getId_laboratorio());
        }
        List lista = this.mi.getDatoPedidoLab(dato); /// saca del pedidoslab sus datos
        Cuadernos listal = (Cuadernos) lista.get(0);
        modelo.put("estab", listal.getNombre());
        modelo.put("medico", listal.getNombres());
        modelo.put("numero", Integer.toString(listal.getNumpieza()));

        if ("Aceptar".equals(accion)) {
            Cuadernos datola = new Cuadernos();
            datola.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datola.setCod_esta(cliente.getCod_esta());
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(datola);
            Cuadernos datoLab2 = this.mi.getDatosLaboratorioC(datola);
            modelo.put("datosLab", datoLab);
            String resultado = request.getParameter("resultado");
            if (resultado != null) {
                resultado = resultado.trim();
            } else {
                resultado = " ";
            }
            if (resultado.contains("Office") || resultado.contains("Word") || resultado.contains("font-family")) {
                return new ModelAndView("Aviso", "mensaje", "No puede realizar copias de Word o Excel");
            }
            Cuadernos datoll = new Cuadernos();
            datoll.setId_cuaderno(Integer.parseInt(id_historial));
            datoll.setId_pedido(Integer.parseInt(id_pedido));
            datoll.setCod_esta(cliente.getCod_esta());

            List hemogra = this.mi.getHemograma(datoll);
            if ("S".equals(expedido)) {
                Costos costo = new Costos();
                costo.setId_costo(datoLab.getId_costo());
                costo.setCod_esta(cliente.getCod_esta());
                Costos buscarCosto = this.mi.getDatosCosto(costo);
                Prestaciones prestac = new Prestaciones();
                prestac.setId_historial(Integer.parseInt(id_historial));
                prestac.setId_prestacion(buscarCosto.getId_prestacion());
                prestac.setCod_esta(cliente.getCod_esta());
                List listaPre = this.mi.getPrestacionExisteYa(prestac);
                if (listaPre.isEmpty() && buscarCosto.getId_prestacion() > 0) {///verifica si ya esta en la lista
                    List listarPr = this.mi.getListarNivelPrestacion(buscarCosto.getId_prestacion());
                    Prestaciones Lpres = (Prestaciones) listarPr.get(0);
                    Prestaciones datoPres = new Prestaciones();
                    datoPres.setId_historial(Integer.parseInt(id_historial));
                    datoPres.setId_prestacion(buscarCosto.getId_prestacion());
                    datoPres.setId_persona(cliente.getId_persona());
                    datoPres.setPrestacion(Lpres.getPrestacion());
                    datoPres.setFecha_ini(fecha1);
                    datoPres.setCantidad(1);
                    datoPres.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearRecetaSumi(datoPres);
                }
            }
            switch (Integer.parseInt(id_costo)) {
                case 137: {
                    datoLab.setSglorojos(grojo);
                    datoLab.setSblancos(gblanco);
                    datoLab.setSplaquetas(plaqueta);
                    datoLab.setShemoglo(hemoglobina);
                    datoLab.setShemato(hematocrito);
                    datoLab.setSvcm(vcm);
                    datoLab.setShgm(hgm);
                    datoLab.setSchcm(chcm);
                    datoLab.setSbas(bas);
                    datoLab.setSeos(eos);
                    datoLab.setSmielo(mielo);
                    datoLab.setSjuy(juy);
                    datoLab.setScay(cay);
                    datoLab.setSseg(seg);
                    datoLab.setSlinf(linf);
                    datoLab.setSmono(mono);
                    datoLab.setSangre(coagulacion);
                    datoLab.setAspecto(sangria);
                    datoLab.setCetonas(protombina);
                    datoLab.setReaccion(actividad);
                    datoLab.setColor(grupo);
                    datoLab.setOlor(factor);
                    datoLab.setSmroja(sroja);
                    datoLab.setSmblanca(sblanca);
                    datoLab.setSmplaquetas(splaquetas);
                    datoLab.setSmves(ves);
                    datoLab.setSmreti(reti);
                    datoLab.setSmindreti(ireti);
                    datoLab.setSmotros(otro);
                    datoLab.setEspuma(leve);
                    datoLab.setEstado(moderado);
                    datoLab.setProteinas(severo);
                    datoLab.setResultado(hemograma);
                    datoLab.setCod_esta(cliente.getCod_esta());
                    datoLab.setId_historial(Integer.parseInt(id_historial));
                    datoLab.setId_pedido(Integer.parseInt(id_pedido));
                    datoLab.setId_cargo(Integer.parseInt(id_detalle));
                    List hemogram = this.mi.getHemograma(datoLab);
                    if (hemogram.size() > 0) {
                        this.mi.setModificarHemograma(datoLab);
                    } else {
                        this.mi.setCrearDetalleSangre(datoLab);
                    }
                    if ("AB".equals(grupo)) {
                        datoLab.setContraref(grupo + "-" + factor);
                    } else {
                        datoLab.setContraref(grupo + " -" + factor);
                    }
                    datoLab.setId_paciente(Integer.parseInt(id_paciente));
                    datoLab.setTipo("G");
                    this.mi.setModificaPacienteSangre(datoLab);//modifica datos del grupo sangre
                }
                break;
                case 121: {
                    datoLab.setCantidad(volumen);
                    datoLab.setColor(color);
                    datoLab.setOlor(olor);
                    datoLab.setAspecto(aspecto);
                    datoLab.setReaccion(reaccion);
                    datoLab.setDensidad(densidad);
                    datoLab.setEspuma(espuma);
                    datoLab.setSedimento(sedimento);
                    datoLab.setNitritos(nitrito);
                    datoLab.setGlucosa(glucosa);
                    datoLab.setSangre(sangre);
                    datoLab.setCetonas(cetona);
                    datoLab.setBilirrubina(bilirrubina);
                    datoLab.setUrabilinogeno(urabili);
                    datoLab.setProteinas(proteina);
                    datoLab.setSmroja(pigmentos);
                    datoLab.setSmblanca(acido);
                    datoLab.setSmreti(sales);
                    datoLab.setSmono(leuco2);
                    datoLab.setSmotros(otros);
                    datoLab.setEpiteliales(epiteli);
                    datoLab.setLeucocitos(leuco);
                    datoLab.setEmaties(hematies);
                    datoLab.setPiocitos(piocitos);
                    datoLab.setBacterias(bacteria);
                    datoLab.setSmotros(otros);
                    datoLab.setCilindros(cilindros);
                    datoLab.setGranulosos(granulosos);
                    datoLab.setHialianos(hialianos);
                    datoLab.setLeucocitarios(leucocitarios);
                    datoLab.setCristales(cristales);
                    datoLab.setObservaciones(observa);
                    datoLab.setResultado(orina);
                    datoLab.setCod_esta(cliente.getCod_esta());
                    datoLab.setId_historial(Integer.parseInt(id_historial));
                    datoLab.setId_pedido(Integer.parseInt(id_pedido));
                    datoLab.setId_cargo(Integer.parseInt(id_detalle));
                    List orinas = this.mi.getOrina(datoLab);
                    if (orinas.size() > 0) {
                        this.mi.setModificarOrina(datoLab);
                    } else {
                        this.mi.setCrearDetalleOrina(datoLab);
                    }
                }
                break;
                case 136: {
                    datoLab.setResultado(resultado);
                }
                break;
                case 129: {
                    if ("AB".equals(grupo)) {
                        datoLab.setResultado(grupo + "-" + factor);
                        datoLab2.setResultado(grupo + "-" + factor);
                        datoLab2.setContraref(grupo + "-" + factor);
                    } else {
                        datoLab.setResultado(grupo + " -" + factor);
                        datoLab2.setResultado(grupo + " -" + factor);
                        datoLab2.setContraref(grupo + "-" + factor);
                    }
                }
                break;
                case 128: {
                    String a = "/";
                    String fum = dia + a + mes + a + anio;
                    datoLab.setResultado(examen + "  F.U.M.:" + fum);
                }
                break;
                case 144: {
                    String a = "/";
                    String fum = dia + a + mes + a + anio;
                    datoLab.setResultado(examen + "  F.U.M.:" + fum);
                }
                break;
                default:
                    datoLab.setResultado(resultado);
            }

            datoLab.setEstado("B");
            Cuadernos datol = new Cuadernos();  //actualiza datos de la persona que realiza laboratorio
            datol.setNombre(estab);
            datol.setNombres(medico);
            datol.setCod_esta(cliente.getCod_esta());
            datol.setId_pedido(Integer.parseInt(id_pedido));
            if (numero == null || "".equals(numero)) {
                numero = "0";
            }

            datol.setNumpieza(Integer.parseInt(numero));
            datol.setId_persona(cliente.getId_persona());
            datoLab.setId_persona(cliente.getId_persona());
            if (id_persona2 != null && !"".equals(id_persona2)) {
                datoLab.setId_persona(Integer.parseInt(id_persona2));
                datol.setId_persona(Integer.parseInt(id_persona2));
            }

            datol.setFechae(fecha1);
            datol.setEstado("B");
            this.mi.setModificarPedidoLab(datol);/////

            datoLab.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datoLab.setCod_esta(cliente.getCod_esta());
            datoLab.setId_por(cliente.getId_persona());
            this.mi.setModificarLaboratorioC(datoLab);
            ///Ahora para actualizar tipo sangre en pacientes
            datoLab2.setId_paciente(Integer.parseInt(id_paciente));
            this.mi.setModificaPacienteSangre(datoLab2); //modifica datos del grupo sangre

            /////////llena datos de Quimicas Sanguinea
            datoLab2.setTipo("L");   /////L general 
            datoLab2.setCod_esta(cliente.getCod_esta());
            datoLab2.setId_historial(Integer.parseInt(id_historial));
            if (cliente.getId_laboratorio() == 0) {
                datoLab2.setId_cargo(0);
                datoLab2.setId_laboratorio(99999);
            } else {
                datoLab2.setId_cargo(cliente.getId_laboratorio());
                datoLab2.setId_laboratorio(cliente.getId_laboratorio());
            }
            
            List listaquim = this.mi.getLabPendiente(datoLab2);////funciona para serologia mas
            
            if (listaquim.size() > 0) { ////empieza llenado de los agrupaos quimicas y serologia
                Costos datoq = new Costos();
                datoq.setId_rubro(6);
                datoq.setId_prestacion(6);
                datoq.setId_estado("%");
                datoq.setMuestra("L");
                datoq.setId_historial(Integer.parseInt(id_historial));
                datoq.setCod_esta(cliente.getCod_esta());
                if (cliente.getId_almacen() == 1) {
                    datoq.setId_nivel(1);
                    datoq.setEmergencias(1);
                } else {
                    datoq.setId_nivel(0);
                    datoq.setEmergencias(1);
                }
                if (cliente.getId_laboratorio() == 0) {
                    datoq.setId_paciente(0);
                    datoq.setId_persona(5000);
                } else {
                    datoq.setId_paciente(cliente.getId_laboratorio());
                    datoq.setId_persona(cliente.getId_laboratorio());
                }
                List listarLabor = this.mi.getListarCostosLabora(datoq);
            
            if (Integer.parseInt(id_laboratorio) == 2) {///18-08-2018
                for (int i = 0; i < accioncurativa.length; i++) {
                    if (!"null".equals(accioncurativa[i][1]) && !"0".equals(accioncurativa[i][1]) && accioncurativa[i][1] != null) {
                        String rr = accioncurativa[i][1];
                        for (int j = 0; j < listaquim.size(); j++) {
                            Cuadernos datoLabw = (Cuadernos) listaquim.get(j);
                            if (datoLabw.getId_costo() == Integer.parseInt(accioncurativa[i][0])) {
                                Cuadernos datola1 = new Cuadernos();
                                datola1.setId_cuaderno(datoLabw.getId_cuaderno());
                                datola1.setCod_esta(cliente.getCod_esta());
                                Cuadernos datoLabq = this.mi.getDatosLaboratorioC(datola1);
                                datoLabq.setResultado(accioncurativa[i][1]);
                                datoLabq.setId_persona(Integer.parseInt(id_persona));
                                if (id_persona2 != null && !"".equals(id_persona2)) {  ////14-10-2015 la persona que se selecciona 
                                    datoLabq.setId_persona(Integer.parseInt(id_persona2));
                                    datola1.setId_persona(Integer.parseInt(id_persona2));
                                }
                                datoLabq.setEstado("B");
                                datoLabq.setCod_esta(cliente.getCod_esta());
                                datoLabq.setId_por(cliente.getId_persona());
                                this.mi.setModificarLaboratorioC(datoLabq);
                                if ("S".equals(expedido)) {
                                    Costos costo = new Costos();
                                    costo.setId_costo(datoLabw.getId_costo());
                                    costo.setCod_esta(cliente.getCod_esta());
                                    Costos buscarCosto = this.mi.getDatosCosto(costo);
                                    Prestaciones prestaca = new Prestaciones();
                                    prestaca.setId_historial(Integer.parseInt(id_historial));
                                    prestaca.setId_prestacion(buscarCosto.getId_prestacion());
                                    prestaca.setCod_esta(cliente.getCod_esta());
                                    List listaPre = this.mi.getPrestacionExisteYa(prestaca);
                                    if (listaPre.isEmpty() && buscarCosto.getId_prestacion() > 0) {
                                        List listarPr = this.mi.getListarNivelPrestacion(buscarCosto.getId_prestacion());
                                        Prestaciones Lpres = (Prestaciones) listarPr.get(0);
                                        Prestaciones datoPres = new Prestaciones();
                                        datoPres.setId_historial(Integer.parseInt(id_historial));
                                        datoPres.setId_prestacion(buscarCosto.getId_prestacion());
                                        datoPres.setId_persona(Integer.parseInt(id_persona));
                                        datoPres.setPrestacion(Lpres.getPrestacion());
                                        datoPres.setFecha_ini(fecha1);
                                        datoPres.setCantidad(1);
                                        datoPres.setCod_esta(cliente.getCod_esta());
                                        this.mi.setCrearRecetaSumi(datoPres);
                                    }
                                }
                                j = listaquim.size();
                            }

                        }
                    }
                }////////Termina de llenar datos SEROLOGIA
            }///fin de  id_laboratorio == 2   
                
            if (Integer.parseInt(id_laboratorio) ==3) {///18-08-2018    
                for (int i = 0; i < accionSero.length; i++) {
                    if (!"null".equals(accionSero[i][1]) && !"0".equals(accionSero[i][1]) && accionSero[i][1] != null) {
                        String rr = accionSero[i][1];
                        for (int j = 0; j < listaquim.size(); j++) {
                            Cuadernos datoLabw = (Cuadernos) listaquim.get(j);
                            if (datoLabw.getId_costo() == Integer.parseInt(accionSero[i][0])) {
                                Cuadernos datola1 = new Cuadernos();
                                datola1.setId_cuaderno(datoLabw.getId_cuaderno());
                                datola1.setCod_esta(cliente.getCod_esta());
                                Cuadernos datoLabq = this.mi.getDatosLaboratorioC(datola1);
                                datoLabq.setResultado(accionSero[i][1]);
                                datoLabq.setId_persona(Integer.parseInt(id_persona));
                                if (id_persona2 != null && !"".equals(id_persona2)) {  ////14-10-2015 la persona que se selecciona 
                                    datoLabq.setId_persona(Integer.parseInt(id_persona2));
                                    datola1.setId_persona(Integer.parseInt(id_persona2));
                                }
                                datoLabq.setEstado("B");
                                datoLabq.setCod_esta(cliente.getCod_esta());
                                datoLabq.setId_por(cliente.getId_persona());
                                this.mi.setModificarLaboratorioC(datoLabq);
                                if ("S".equals(expedido)) {
                                    Costos costo = new Costos();
                                    costo.setId_costo(datoLabw.getId_costo());
                                    costo.setCod_esta(cliente.getCod_esta());
                                    Costos buscarCosto = this.mi.getDatosCosto(costo);
                                    Prestaciones prestaca = new Prestaciones();
                                    prestaca.setId_historial(Integer.parseInt(id_historial));
                                    prestaca.setId_prestacion(buscarCosto.getId_prestacion());
                                    prestaca.setCod_esta(cliente.getCod_esta());
                                    List listaPre = this.mi.getPrestacionExisteYa(prestaca);
                                    if (listaPre.isEmpty() && buscarCosto.getId_prestacion() > 0) {
                                        List listarPre = this.mi.getListarNivelPrestacion(buscarCosto.getId_prestacion());
                                        Prestaciones Lpres = (Prestaciones) listarPre.get(0);
                                        Prestaciones datoPres = new Prestaciones();
                                        datoPres.setId_historial(Integer.parseInt(id_historial));
                                        datoPres.setId_prestacion(buscarCosto.getId_prestacion());
                                        datoPres.setId_persona(Integer.parseInt(id_persona));
                                        datoPres.setPrestacion(Lpres.getPrestacion());
                                        datoPres.setFecha_ini(fecha1);
                                        datoPres.setCantidad(1);
                                        datoPres.setCod_esta(cliente.getCod_esta());
                                        this.mi.setCrearRecetaSumi(datoPres);
                                    }
                                }
                                j = listaquim.size();
                            }

                        }
                    }
                }////////Termina de llenar datos SEROLOGIA
            }////id_laboratorio ==3                   
            }/////////Termina de llenar datos de vectores quimicas y serologia
            
            
               
                

            

        }
        if (id_cuaderno == null) {
            id_cuaderno = "0";
        }

        if ("adicion".equals(accion)) {
            String id_estado = request.getParameter("id_estado");
            Cuadernos datola = new Cuadernos();
            datola.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datola.setId_historial(Integer.parseInt(id_historial));
            datola.setCod_esta(cliente.getCod_esta());
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(datola); ////saca el laboratorio en cuestion con join de costos
            id_detalle = Integer.toString(datoLab.getId_localidad()); ////saca el id de la tabla detalleorina o sangre
            id_persona = Integer.toString(datoLab.getId_persona());
            if ("0".equals(id_persona)) {    ////muestra la persona que ha realizado el laboratorio ooo el usuario si es 0    
                modelo.put("id_persona", Integer.toString(cliente.getId_persona()));
            } else {
                modelo.put("id_persona", id_persona);
            }
            if ((datoLab.getId_costo() == 128 || datoLab.getId_costo() == 144) && datoLab.getResultado().length() > 5) {
                modelo.put("anio", datoLab.getResultado().substring(23, 27));
                modelo.put("mes", datoLab.getResultado().substring(20, 22));
                modelo.put("dia", datoLab.getResultado().substring(17, 19));
                modelo.put("embarazo", datoLab.getResultado().substring(0, 8));
            }
            if (datoLab.getId_costo() == 129 && datoLab.getResultado().length() > 5) {
                String d = datoLab.getResultado().substring(0, 2).trim();
                String w = datoLab.getResultado().substring(3, 11);
                modelo.put("sgrupo", datoLab.getResultado().substring(0, 2).trim());
                modelo.put("sfactor", datoLab.getResultado().substring(3, 11));
            }
            //////para selecionar solo por tipo laboratorio
            datoLab.setId_cargo(datoLab.getId_laboratorio());
            datoLab.setId_laboratorio(datoLab.getId_laboratorio());
            datoLab.setId_historial(Integer.parseInt(id_historial));
            datoLab.setCod_esta(cliente.getCod_esta());
            datoLab.setTipo("L");
            if (Integer.parseInt(id_costo) == 143) {///25-03-2017
                datoLab.setId_laboratorio(19);
            }
            if (datoLab.getId_laboratorio() == 2) {
                List listaqui2 = this.mi.getLabPendiente(datoLab);
                modelo.put("listaquim", listaqui2);
                modelo.put("quimicas", "quimicas");
            }
            if(datoLab.getId_laboratorio()==3){
                List listasero2 = this.mi.getLabPendiente(datoLab);
                modelo.put("listasero", listasero2);
                modelo.put("serologia", "serologia");
            }

            modelo.put("id_estado", id_estado);
            modelo.put("tipo", cliente.getTipo());
            modelo.put("datosLab", datoLab);
            realizalab = "si";
        }

        modelo.put("realizalab", realizalab);

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        Cuadernos datol = new Cuadernos();
        datol.setTipo(tipo);
        datol.setId_pedido(Integer.parseInt(id_pedido));
        datol.setId_historial(Integer.parseInt(id_historial));
        datol.setCod_esta(cliente.getCod_esta());
        if (cliente.getId_laboratorio() == 0) {
            datol.setId_cargo(0);
            datol.setId_laboratorio(999);
        } else {
            datol.setId_cargo(cliente.getId_laboratorio());
            datol.setId_laboratorio(cliente.getId_laboratorio());
        }

        if (datosconsul.getId_cargo() != 12 && datosconsul.getId_cargo() != 11) {
            List listalab = this.mi.getLabPendiente(dato);////10-05-2015
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() == 11) {
            List listalab = this.mi.getLabPendienteEco(dato);
            modelo.put("listalab", listalab);
        }
        if (datosconsul.getId_cargo() == 12) {
            List listalab = this.mi.getLabPendienteRx(dato);
            modelo.put("listalab", listalab);
        }

        datol.setId_cargo(Integer.parseInt(id_detalle));

        List orinas = this.mi.getOrina(datol);
        modelo.put("orinas", orinas);
        modelo.put("norina", Integer.toString(orinas.size()));

        List hemogram = this.mi.getHemograma(datol);
        modelo.put("hemogram", hemogram);
        modelo.put("nhemogra", Integer.toString(hemogram.size()));
        modelo.put("id_historial", id_historial);

        if (id_costo == null) {
            id_costo = "0";
        }
        if (id_laboratorio == null) {
            id_laboratorio = "0";
        }

        if (Integer.parseInt(id_laboratorio) == 12 || Integer.parseInt(id_laboratorio) == 13) {
            if (Integer.parseInt(id_laboratorio) == 12) {
                id_costo = "136"; ////para ecos
            } else {
                id_costo = "138"; ////para rayosX
            }
            dato.setId_cuaderno(Integer.parseInt(id_costo));
            List detalleecos = this.mi.getDetalleEcos(dato);
            modelo.put("detalleecos", detalleecos);
        }
        if (accion1 != null) {
            dato.setAspecto(accion1);
            dato.setTipoconsulta("L");
            List detalleEcosInd = this.mi.getDetalleEcosI(dato);

            for (int i = 0; i < detalleEcosInd.size(); i++) {
                Cuadernos detalleEcosI = (Cuadernos) detalleEcosInd.get(i);
                if (detalleEcosI.getAspecto().equals(accion1)) {
                    String defecto = "";
                    accionlab = detalleEcosI.getAspecto();
                    defecto = detalleEcosI.getDiagnostico();
                    Cuadernos datola = new Cuadernos();
                    datola.setId_cuaderno(Integer.parseInt(id_cuaderno));
                    datola.setCod_esta(cliente.getCod_esta());
                    Cuadernos datoLab = this.mi.getDatosLaboratorioC(datola);
                    modelo.put("datosLab", datoLab);
                    modelo.put("accionlab", accionlab);
                    modelo.put("defecto", defecto);
                    modelo.put("realizalab", "si");
                }
            }
        }

        if ("Otro".equals(accion1)) {
            accionlab = "Otro";
            Cuadernos datola = new Cuadernos();
            datola.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datola.setCod_esta(cliente.getCod_esta());
            Cuadernos datoLab = this.mi.getDatosLaboratorioC(datola);
            modelo.put("datosLab", datoLab);
            modelo.put("accionlab", accionlab);
            modelo.put("realizalab", "si");
        }

        if ("Configurar".equals(accion1)) {
            Cuadernos dato5 = new Cuadernos();
            dato5.setId_cuaderno(Integer.parseInt(id_costo));
            List detalleecos = this.mi.getDetalleEcos(dato5);
            modelo.put("detalleecos", detalleecos);
            modelo.put("id_costo", id_costo);
            return new ModelAndView("administrarlaboratorio/ConfigurarEcos", modelo);
        }

        List lista3 = this.mi.getPedidoLab(dato);
        Cuadernos listal3 = (Cuadernos) lista3.get(0);
        modelo.put("numero", Integer.toString(listal3.getNumpieza()));
        //modelo.put("id_persona",id_persona);
        //if(cliente.getId_persona()!=Integer.parseInt(id_persona)) 
        ///     modelo.put("id_persona",Integer.toString(cliente.getId_persona()));

        Cuadernos datoa = new Cuadernos();
        datoa.setId_persona(Integer.parseInt(id_paciente));
        datoa.setTipoconsulta("A");
        List listasLabq = this.mi.getPacienteResultadoLab(datoa);
        modelo.put("milista", listasLabq);
        List listalab11 = this.mi.getLabMedico(datoa);
        modelo.put("listalab11", listalab11);
        if (datosconsul.getId_cargo() == 11) {
            datoa.setTipoconsulta("E");
            List listasLabq2 = this.mi.getPacienteResultadoLabEco(datoa);
            modelo.put("milista", listasLabq2);
            List listalab112 = this.mi.getLabMedicoEco(datoa);
            modelo.put("listalab11", listalab112);
        }
        if (datosconsul.getId_cargo() == 12) {
            datoa.setTipoconsulta("X");
            List listasLabq2 = this.mi.getPacienteResultadoLabX(datoa);
            modelo.put("milista", listasLabq2);
            List listalab112 = this.mi.getLabMedicoX(datoa);
            modelo.put("listalab11", listalab112);
        }
        if (datosconsul.getId_cargo() == 10) {
            datoa.setTipoconsulta("L");
            List listasLabq2 = this.mi.getPacienteResultadoLabLab(datoa);
            modelo.put("milista", listasLabq2);
            List listalab112 = this.mi.getLabMedicoLab(datoa);
            modelo.put("listalab11", listalab112);
        }

        if (cliente.getId_almacen() == 1) {
            return new ModelAndView("administrarlaboratorio/LabPendienteEmerg", modelo);
        }
        if (datoestab.getCod_esta() == 200010) {
            return new ModelAndView("administrarlaboratorio/LabPendienteCaja", modelo);
        }
        return new ModelAndView("administrarlaboratorio/LabPendiente", modelo);
    }
}
