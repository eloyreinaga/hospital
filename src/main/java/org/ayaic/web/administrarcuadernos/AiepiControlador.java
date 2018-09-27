package org.ayaic.web.administrarcuadernos;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AiepiControlador {

    private final MiFacade mi;

    public AiepiControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Aiepi.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion4 = request.getParameter("accion4");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String swinter = request.getParameter("swinter");

        String[] diass = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq + 1))};

        String Pultimo = "A";
        Date fecha_nac = new Date();
        Date fecha_act = new Date();
        int semanas = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        modelo.put("datocli", cliente);
        modelo.put("tipo", datol.getTipo());

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("datos", buscarPaciente);
        fecha_nac = buscarPaciente.getFec_nacimiento();

        long fechaInicialMs = fecha_nac.getTime();
        long fechaFinalMs = fecha1.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        semanas = (int) (Math.round(((float) dias) / 7));

        Cuadernos dato2 = new Cuadernos();
        dato2.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        dato2.setTipo("4");
        Cuadernos datosC = this.mi.getVerCuaderno4PaciUlt(dato2);   /////getVerCuaderno4PaciUlt
        modelo.put("datosC", datosC);
        modelo.put("swinter", swinter);
        if ("Cuaderno4".equals(accion)) {// llena solo el cuaderno 4 unico del pciente
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaAten4Paci = this.mi.getVerCuaderno4Uni(dato);
            modelo.put("listaCuaderno4", listaAten4Paci);
            return new ModelAndView(new VerCuaderno4PDF(), modelo);
        }
        Cuadernos datoc4 = new Cuadernos();
        datoc4.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        datoc4.setId_historial(Integer.parseInt(id_reservacion));
        List C4 = this.mi.getPacienteCuaderno4(datoc4);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHisto = this.mi.getDatosHistorial(datohi);

        double p = datosHisto.getPeso();
        double t = datosHisto.getTalla();
        modelo.put("peso", Double.toString(datosHisto.getPeso()));
        modelo.put("talla", Double.toString(datosHisto.getTalla()));
        modelo.put("semanas", Integer.toString(semanas));

        ///Cuadernos datoNu = this.mi.getDatosTallaPeso(datosHisto.getTalla(),datosHisto.getEdad(),buscarPaciente.getId_tipo_sexo()) ;
        Cuadernos datotp = new Cuadernos();
        datotp.setTalla(datosHisto.getTalla());
        datotp.setEdad(datosHisto.getEdad());
        Cuadernos datoNu = new Cuadernos();
        if (buscarPaciente.getId_tipo_sexo() == 1) {
            if (datosHisto.getEdad() < 2) {
                datoNu = this.mi.getDatosTallaPesoF02(datotp);
            } else {
                datoNu = this.mi.getDatosTallaPesoF25(datotp);
            }
        } else {
            if (datosHisto.getEdad() < 2) {
                datoNu = this.mi.getDatosTallaPesoM02(datotp);
            } else {
                datoNu = this.mi.getDatosTallaPesoM25(datotp);
            }
        }
        String nutricion = "";

//Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo());
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);

        if (".Terminar.".equals(accion)) {
            //Cuadernos dato2=new Cuadernos();
            dato2.setId_paciente(Integer.parseInt(id_paciente));
            dato2.setTipo("4");
            dato2.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            Cuadernos datosC2 = this.mi.getVerCuaderno4PaciUlt(dato2);   /////getVerCuaderno4PaciUlt
            //Cuadernos datosC2 = this.mi.getVerCuaderno4PaciUlt(Integer.parseInt(id_paciente));
            modelo.put("datosC", datosC2);
            modelo.put("swinter", swinter);
            if ("Cuaderno4".equals(accion)) {// llena solo el cuaderno 4 unico del pciente
                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
                dato.setId_paciente(Integer.parseInt(id_paciente));
                List listaAten4Paci = this.mi.getVerCuaderno4Uni(dato);
                modelo.put("listaCuaderno4", listaAten4Paci);
                return new ModelAndView(new VerCuaderno4PDF(), modelo);
            }
            Cuadernos datoc42 = new Cuadernos();
            datoc42.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datoc42.setId_historial(Integer.parseInt(id_reservacion));
            List C42 = this.mi.getPacienteCuaderno4(datoc42);

            if (datosC2 != null) {
                Pultimo = datosC.getDglobal();
            }

            Cuadernos datoc4n = new Cuadernos();
            datoc4n.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datoc4n.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4n);
            modelo.put("listaOdon", listaOdon);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipo("P");
            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);

            accion4 = "Ninio Enfermo";
            modelo.put("accion4", accion4);
            modelo.put("accion", accion);
            return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
        }
        if ("imprimir".equals(accion)) {
            Pacientes buscarPacienteh = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            modelo.put("datosp", buscarPacienteh);
            Historiales dato = new Historiales();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setCod_esta(cliente.getCod_esta());
            List listas = this.mi.getListarHistoria(dato);
            modelo.put("milistas", listas);
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHis = this.mi.getDatosHistorial(datohi);
            modelo.put("datosHis", datosHis);
            Personas persona = this.mi.getBuscarPersona(datosHis.getId_persona());
            modelo.put("datosPer", persona);

            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            datoc4r.setTipoconsulta("A");
            datoc4r.setId_paciente(Integer.parseInt(id_paciente));
            List listaV = this.mi.getVerVacunasUni(datoc4r);
            modelo.put("vacunas", listaV);
            Cuadernos listaaiepi = this.mi.getPacienteCuaderno4A(datoc4r);
            modelo.put("aiepi", listaaiepi);

            return new ModelAndView(new VerAiepiPDF(), modelo);
        }
        if ("...Grabar1...".equals(accion)) {
            String c1 = request.getParameter("c1");
            String r1 = request.getParameter("r1");
            String u1 = request.getParameter("u1");
            String u2 = request.getParameter("u2");
            String u3 = request.getParameter("u3");
            String u4 = request.getParameter("u4");
            String m1 = request.getParameter("m1");

            if (c1 == null) {
                c1 = "0";
            }

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setBilirrubina(u1);
            dato.setCantidad(u2);
            dato.setCetonas(u3);
            dato.setCilindros(u4);
            dato.setBajopeso(Integer.parseInt(c1));
            dato.setBcg(Integer.parseInt(r1));
            dato.setTipodent(m1);

            dato.setAspecto("C");
            this.mi.setModificarCuaderno4C(dato);

        }
        if ("...Grabar2...".equals(accion)) {
            String radio1 = request.getParameter("radio1");
            String radio2 = request.getParameter("radio2");
            String radio3 = request.getParameter("radio3");
            String radio4 = request.getParameter("radio4");
            String radio5 = request.getParameter("radio5");
            String u5 = request.getParameter("u5");
            String u6 = request.getParameter("u6");
            String u7 = request.getParameter("u7");
            String u8 = request.getParameter("u8");
            String u9 = request.getParameter("u9");
            String u10 = request.getParameter("u10");
            String u11 = request.getParameter("u11");
            String u12 = request.getParameter("u12");
            String u13 = request.getParameter("u13");
            String u14 = request.getParameter("u14");
            String r1 = request.getParameter("r1");
            String r2 = request.getParameter("r2");
            String r3 = request.getParameter("r3");
            String r4 = request.getParameter("r4");
            String r5 = request.getParameter("r5");

            if (radio1 == null) {
                radio1 = "0";
            }
            if (radio2 == null) {
                radio2 = "0";
            }
            if (radio3 == null) {
                radio3 = "0";
            }
            if (radio4 == null) {
                radio4 = "0";
            }
            if (radio5 == null) {
                radio5 = "0";
            }

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));

            int valor = 0;
            if ("1".equals(request.getParameter("q1"))) {
                valor += 1 << 0;
            }
            if ("2".equals(request.getParameter("q2"))) {
                valor += 1 << 1;
            }
            if ("3".equals(request.getParameter("q3"))) {
                valor += 1 << 2;
            }
            if ("4".equals(request.getParameter("q4"))) {
                valor += 1 << 3;
            }
            if ("5".equals(request.getParameter("q5"))) {
                valor += 1 << 4;
            }
            if ("6".equals(request.getParameter("q6"))) {
                valor += 1 << 5;
            }
            if ("7".equals(request.getParameter("q7"))) {
                valor += 1 << 6;
            }
            if ("8".equals(request.getParameter("q8"))) {
                valor += 1 << 7;
            }
            if ("9".equals(request.getParameter("q9"))) {
                valor += 1 << 8;
            }
            if ("10".equals(request.getParameter("q10"))) {
                valor += 1 << 9;
            }

            dato.setCondon(valor);

            dato.setEspuma(u5);
            dato.setContraref(u6);
            dato.setCristales(u7);
            dato.setFama(u8);
            dato.setDensidad(u9);
            dato.setEstado(u10);
            dato.setDhierro(u11);
            dato.setDvitaa(u12);
            dato.setEmaties(u13);
            dato.setEpiteliales(u14);
            dato.setControlpos(Integer.parseInt(r1));
            dato.setCuraciones(Integer.parseInt(r2));
            dato.setDepre(Integer.parseInt(r3));
            dato.setDesesti(Integer.parseInt(r4));
            dato.setDiabetes(Integer.parseInt(r5));
            dato.setDiasc(Integer.parseInt(radio1));
            dato.setDiasi(Integer.parseInt(radio2));
            dato.setDiscapa(Integer.parseInt(radio3));
            dato.setDisli(Integer.parseInt(radio4));
            dato.setDiu(Integer.parseInt(radio5));

            dato.setAspecto("D");
            this.mi.setModificarCuaderno4D(dato);

        }
        if ("...Grabar3...".equals(accion)) {
            String radio1 = request.getParameter("radio1");
            String radio2 = request.getParameter("radio2");
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");
            String v1 = request.getParameter("v1");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setEclam(Integer.parseInt(radio1));
            dato.setEndodoncia(Integer.parseInt(radio2));
            Date fech = new Date(Integer.parseInt(anio) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
            dato.setFec_egreso(fech);
            dato.setGlucosa(v1);
            dato.setAspecto("E");
            this.mi.setModificarCuaderno4E(dato);

        }
        if ("...Grabar4...".equals(accion)) {
            String u16 = request.getParameter("u16");
            String u17 = request.getParameter("u17");
            String u18 = request.getParameter("u18");
            String u19 = request.getParameter("u19");
            String u20 = request.getParameter("u20");
            String u21 = request.getParameter("u21");
            String u22 = request.getParameter("u22");
            String u23 = request.getParameter("u23");
            String u24 = request.getParameter("u24");
            String u25 = request.getParameter("u25");
            String u26 = request.getParameter("u26");
            String u27 = request.getParameter("u27");
            String u28 = request.getParameter("u28");
            String u29 = request.getParameter("u29");
            String u30 = request.getParameter("u30");
            String u31 = request.getParameter("u31");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setGranulosos(u16);
            dato.setHialianos(u17);
            dato.setLaboratorio(u18);
            dato.setLeucocitos(u19);
            dato.setLeucocitarios(u20);
            dato.setLmexclu(u21);
            dato.setMujerdt(u22);
            dato.setNitritos(u23);
            dato.setObserva(u24);
            dato.setObservaciones(u25);
            dato.setOlor(u26);
            dato.setOtros(u27);
            dato.setPenta(u28);
            dato.setPesonac(u29);
            dato.setPiocitos(u30);
            dato.setProteinas(u31);

            int valor = 0;
            if ("1".equals(request.getParameter("c1"))) {
                valor += 1 << 0;
            }
            if ("2".equals(request.getParameter("c2"))) {
                valor += 1 << 1;
            }
            if ("3".equals(request.getParameter("c3"))) {
                valor += 1 << 2;
            }
            if ("4".equals(request.getParameter("c4"))) {
                valor += 1 << 3;
            }
            if ("5".equals(request.getParameter("c5"))) {
                valor += 1 << 4;
            }
            if ("6".equals(request.getParameter("c6"))) {
                valor += 1 << 5;
            }
            if ("7".equals(request.getParameter("c7"))) {
                valor += 1 << 6;
            }
            if ("8".equals(request.getParameter("c8"))) {
                valor += 1 << 7;
            }
            if ("9".equals(request.getParameter("c9"))) {
                valor += 1 << 8;
            }
            if ("10".equals(request.getParameter("c10"))) {
                valor += 1 << 9;
            }

            dato.setSuma5(valor);
            dato.setAspecto("F");
            this.mi.setModificarCuaderno4F(dato);
        }

        if ("...Grabar5...".equals(accion)) {
            String u16 = request.getParameter("u16");
            String u17 = request.getParameter("u17");
            String u18 = request.getParameter("u18");
            String u19 = request.getParameter("u19");
            String u20 = request.getParameter("u20");
            String u21 = request.getParameter("u21");
            String u22 = request.getParameter("u22");
            String u23 = request.getParameter("u23");
            String u24 = request.getParameter("u24");
            String u25 = request.getParameter("u25");
            String u26 = request.getParameter("u26");
            String u27 = request.getParameter("u27");
            String u28 = request.getParameter("u28");
            String u29 = request.getParameter("u29");
            String u30 = request.getParameter("u30");
            String u31 = request.getParameter("u31");
            String u32 = request.getParameter("u32");
            String u33 = request.getParameter("u33");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setGranulosos(u16);
            dato.setHialianos(u17);
            dato.setLaboratorio(u18);
            dato.setLeucocitos(u19);
            dato.setLeucocitarios(u20);
            dato.setLmexclu(u21);
            dato.setMujerdt(u22);
            dato.setNitritos(u23);
            dato.setObserva(u24);
            dato.setObservaciones(u25);
            dato.setOlor(u26);
            dato.setOtros(u27);
            dato.setPenta(u28);
            dato.setPesonac(u29);
            dato.setPiocitos(u30);
            dato.setProteinas(u31);
            dato.setReaccion(u32);
            dato.setReferido(u33);

            int valor = 0;
            if ("1".equals(request.getParameter("c1"))) {
                valor += 1 << 0;
            }
            if ("2".equals(request.getParameter("c2"))) {
                valor += 1 << 1;
            }
            if ("3".equals(request.getParameter("c3"))) {
                valor += 1 << 2;
            }
            if ("4".equals(request.getParameter("c4"))) {
                valor += 1 << 3;
            }
            if ("5".equals(request.getParameter("c5"))) {
                valor += 1 << 4;
            }
            if ("6".equals(request.getParameter("c6"))) {
                valor += 1 << 5;
            }
            if ("7".equals(request.getParameter("c7"))) {
                valor += 1 << 6;
            }
            if ("8".equals(request.getParameter("c8"))) {
                valor += 1 << 7;
            }
            if ("9".equals(request.getParameter("c9"))) {
                valor += 1 << 8;
            }
            if ("10".equals(request.getParameter("c10"))) {
                valor += 1 << 9;
            }

            dato.setSuma5(valor);
            dato.setAspecto("F");
            this.mi.setModificarCuaderno4F(dato);
        }
        if ("...Grabar6...".equals(accion)) {
            String u34 = request.getParameter("u34");
            String u35 = request.getParameter("u35");
            String u36 = request.getParameter("u36");
            String u37 = request.getParameter("u37");
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String anio = request.getParameter("anio");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setSangre(u34);
            dato.setSbas(u35);
            dato.setSblancos(u36);
            dato.setScay(u37);
            Date fech = new Date(Integer.parseInt(anio) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
            dato.setFec_ingreso(fech);

            dato.setAspecto("G");
            this.mi.setModificarCuaderno4(dato);
        }

        if (".....Grabar1.....".equals(accion)) {
            String t1 = request.getParameter("t1");
            String t2 = request.getParameter("t2");
            String m1 = request.getParameter("m1");

            String resultado = request.getParameter("resultado");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setTipoconsulta(t1);
            dato.setResultado(t2);
            dato.setTipodent(m1);

            int valor = 0;
            if ("1".equals(request.getParameter("n1"))) {
                valor += 1 << 0;
            }
            if ("2".equals(request.getParameter("n2"))) {
                valor += 1 << 1;
            }
            if ("3".equals(request.getParameter("n3"))) {
                valor += 1 << 2;
            }
            if ("4".equals(request.getParameter("n4"))) {
                valor += 1 << 3;
            }
            if ("5".equals(request.getParameter("n5"))) {
                valor += 1 << 4;
            }
            if ("6".equals(request.getParameter("n6"))) {
                valor += 1 << 5;
            }
            if ("7".equals(request.getParameter("n7"))) {
                valor += 1 << 6;
            }
            if ("8".equals(request.getParameter("n8"))) {
                valor += 1 << 7;
            }

            dato.setDesesti(valor);
            dato.setAspecto("A");
            this.mi.setModificarCuaderno4A(dato);
        }
        if (".....Grabar2.....".equals(accion)) {
            String t3 = request.getParameter("t3");
            String t4 = request.getParameter("t4");
            String t5 = request.getParameter("t5");
            String t6 = request.getParameter("t6");
            String radio = request.getParameter("radio");
            String dias1 = request.getParameter("dias");

            String resultado = request.getParameter("resultado");

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());  ///16-01-2016
            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setTipoconsulta(t3);
            dato.setResultado(t4);
            dato.setAccion(t5);
            dato.setBacterias(t6);

            int valor = 0;
            if ("1".equals(request.getParameter("n1"))) {
                valor += 1 << 0;
            }
            if ("2".equals(request.getParameter("n2"))) {
                valor += 1 << 1;
            }
            if ("3".equals(request.getParameter("n3"))) {
                valor += 1 << 2;
            }
            if ("4".equals(request.getParameter("n4"))) {
                valor += 1 << 3;
            }
            if ("5".equals(request.getParameter("n5"))) {
                valor += 1 << 4;
            }
            if ("6".equals(request.getParameter("n6"))) {
                valor += 1 << 5;
            }
            if ("7".equals(request.getParameter("n7"))) {
                valor += 1 << 6;
            }

            dato.setAuto(Integer.parseInt(radio));
            dato.setAqv(Integer.parseInt(dias1));
            dato.setDesesti(valor);
            dato.setAspecto("B");
            this.mi.setModificarCuaderno4B(dato);
        }

        Cuadernos datoc4r = new Cuadernos();
        datoc4r.setCod_esta(cliente.getCod_esta());  ///16-01-2016
        datoc4r.setId_historial(Integer.parseInt(id_reservacion));
        datoc4r.setTipoconsulta("A");
        datoc4r.setId_paciente(Integer.parseInt(id_paciente));
        List listaV = this.mi.getVerVacunasUni(datoc4r);
        Cuadernos listaaiepi = this.mi.getPacienteCuaderno4A(datoc4r);
        modelo.put("aiepi", listaaiepi);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", diass);
        modelo.put("diaz", Integer.toString(listaaiepi.getFec_egreso().getDate()));
        modelo.put("mesz", Integer.toString(listaaiepi.getFec_egreso().getMonth() + 1));
        modelo.put("anioz", Integer.toString(listaaiepi.getFec_egreso().getYear() + 1900));

        modelo.put("diay", Integer.toString(listaaiepi.getFec_ingreso().getDate()));
        modelo.put("mesy", Integer.toString(listaaiepi.getFec_ingreso().getMonth() + 1));
        modelo.put("anioy", Integer.toString(listaaiepi.getFec_ingreso().getYear() + 1900));

        if ((listaaiepi.getSuma1() & 1) == 1) {
            modelo.put("r11", "S");
        }
        if ((listaaiepi.getSuma1() & 2) == 2) {
            modelo.put("r12", "S");
        }
        if ((listaaiepi.getSuma1() & 4) == 4) {
            modelo.put("r13", "S");
        }
        if ((listaaiepi.getSuma1() & 8) == 8) {
            modelo.put("r14", "S");
        }
        if ((listaaiepi.getSuma1() & 16) == 16) {
            modelo.put("r15", "S");
        }
        if ((listaaiepi.getSuma1() & 32) == 32) {
            modelo.put("r16", "S");
        }
        if ((listaaiepi.getSuma1() & 64) == 64) {
            modelo.put("r17", "S");
        }
        if ((listaaiepi.getSuma1() & 128) == 128) {
            modelo.put("r18", "S");
        }

        if ((listaaiepi.getSuma2() & 1) == 1) {
            modelo.put("r21", "S");
        }
        if ((listaaiepi.getSuma2() & 2) == 2) {
            modelo.put("r22", "S");
        }
        if ((listaaiepi.getSuma2() & 4) == 4) {
            modelo.put("r23", "S");
        }
        if ((listaaiepi.getSuma2() & 8) == 8) {
            modelo.put("r24", "S");
        }
        if ((listaaiepi.getSuma2() & 16) == 16) {
            modelo.put("r25", "S");
        }
        if ((listaaiepi.getSuma2() & 32) == 32) {
            modelo.put("r26", "S");
        }
        if ((listaaiepi.getSuma2() & 64) == 64) {
            modelo.put("r27", "S");
        }

        if ((listaaiepi.getSuma4() & 1) == 1) {
            modelo.put("r30", "S");
        }
        if ((listaaiepi.getSuma4() & 2) == 2) {
            modelo.put("r31", "S");
        }
        if ((listaaiepi.getSuma4() & 4) == 4) {
            modelo.put("r32", "S");
        }
        if ((listaaiepi.getSuma4() & 8) == 8) {
            modelo.put("r33", "S");
        }
        if ((listaaiepi.getSuma4() & 16) == 16) {
            modelo.put("r34", "S");
        }
        if ((listaaiepi.getSuma4() & 32) == 32) {
            modelo.put("r35", "S");
        }
        if ((listaaiepi.getSuma4() & 64) == 64) {
            modelo.put("r36", "S");
        }
        if ((listaaiepi.getSuma4() & 128) == 128) {
            modelo.put("r37", "S");
        }
        if ((listaaiepi.getSuma4() & 256) == 256) {
            modelo.put("r38", "S");
        }
        if ((listaaiepi.getSuma4() & 512) == 512) {
            modelo.put("r39", "S");
        }

        if ((listaaiepi.getSuma5() & 1) == 1) {
            modelo.put("z1", "S");
        }
        if ((listaaiepi.getSuma5() & 2) == 2) {
            modelo.put("z2", "S");
        }
        if ((listaaiepi.getSuma5() & 4) == 4) {
            modelo.put("z3", "S");
        }
        if ((listaaiepi.getSuma5() & 8) == 8) {
            modelo.put("z4", "S");
        }
        if ((listaaiepi.getSuma5() & 16) == 16) {
            modelo.put("z5", "S");
        }
        if ((listaaiepi.getSuma5() & 32) == 32) {
            modelo.put("z6", "S");
        }
        if ((listaaiepi.getSuma5() & 64) == 64) {
            modelo.put("z7", "S");
        }
        if ((listaaiepi.getSuma5() & 128) == 128) {
            modelo.put("z8", "S");
        }
        if ((listaaiepi.getSuma5() & 256) == 256) {
            modelo.put("z9", "S");
        }
        if ((listaaiepi.getSuma5() & 512) == 512) {
            modelo.put("z10", "S");
        }

        String a = "/";
        for (int i = 0; i < listaV.size(); i++) {
            Cuadernos datov = (Cuadernos) listaV.get(i);
            if (datov.getBcg() == 1) {
                modelo.put("bcg", Integer.toString(i));
                modelo.put("fbcg", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
            if ("P".equals(datov.getPenta())) {
                modelo.put("penta1", Integer.toString(i));
                modelo.put("fpenta1", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
            if ("S".equals(datov.getPenta())) {
                modelo.put("penta2", Integer.toString(i));
                modelo.put("fpenta2", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
            if ("T".equals(datov.getPenta())) {
                modelo.put("penta3", Integer.toString(i));
                modelo.put("fpenta3", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
            if ("1".equals(datov.getSrp())) {
                modelo.put("srp", Integer.toString(i));
                modelo.put("fsrp", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
            if ("1".equals(datov.getFama()) || "2".equals(datov.getFama())) {
                modelo.put("fama", Integer.toString(i));
                modelo.put("ffama", Integer.toString(datov.getFechap().getDate()) + a + Integer.toString(datov.getFechap().getMonth() + 1) + a + Integer.toString(datov.getFechap().getYear() + 1900));
            }
        }

        return new ModelAndView("administrarcuadernos/AIEPII", modelo);
    }
}
