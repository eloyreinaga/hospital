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
import org.ayaic.domain.Sexos;
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Cuaderno2Controlador {

    private final MiFacade mi;

    public Cuaderno2Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno2.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        Usuarios usuario = this.mi.getDatosUsuario(cliente.getId_usuario());
        String accion = request.getParameter("accion");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String tipo_medico = request.getParameter("tipo_medico");
        String nemba = request.getParameter("nemba");
        String accioncua = request.getParameter("accioncua");
        String semanas = request.getParameter("semanas");
        String fuera = request.getParameter("fuera");
        String nembarazo = request.getParameter("nembarazo");
        String estimc = request.getParameter("estimc");
        String swinter = request.getParameter("swinter");
        String[] numemba = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        String[] ncontrol = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        String[] nsemanas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49"};
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {(Integer.toString(anioq)), (Integer.toString(anioq - 1))};

        int semges = 0, numcontrol = 0, semges1, cal;
        Date fumu;

        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");

        Localidades locall = new Localidades();
        List Estab1 = this.mi.getListarEsta(locall);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("localidades", datol);
        locall.setArea("R");
        locall.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(locall);
        modelo.put("listarestab", EstabRef);
        locall.setId_persona(2);
        List EstabCRef = this.mi.getListarEstabRef(locall);
        modelo.put("listarestabC", EstabCRef);
        modelo.put("tipo", datol.getTipo());
        modelo.put("numpap", Integer.toString(0));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPaciente);
        //Datos de la ultima vez que vino
        Cuadernos dato2 = new Cuadernos();
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        dato2.setCod_esta(cliente.getCod_esta());
        dato2.setTipo("0");   ////getVerCuaderno2Ult
        Cuadernos datosC2 = this.mi.getVerCuaderno2Ult(dato2);
        if (datosC2 != null) {
            numcontrol = datosC2.getNumpieza();
        }
        modelo.put("datosC2", datosC2);
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);
        modelo.put("ncontrol", ncontrol);
        modelo.put("nsemanas", nsemanas);
        modelo.put("numemba", numemba);
        modelo.put("swinter", swinter);
        // llena solo el cuaderno 2 unico del paciente  
        if ("Cuaderno2".equals(accion)) {
            //Cuadernos dato2= new Cuadernos();
            dato2.setId_paciente(Integer.parseInt(id_paciente));
            dato2.setCod_esta(cliente.getCod_esta());
            List listaUni = this.mi.getVerCuaderno2Uni(dato2);
            modelo.put("listaCobros", listaUni);
            return new ModelAndView(new VerCuaderno2PDF(), modelo);
        }
        //Cuadernos dato2=new Cuadernos();
        dato2.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
        dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
        List C2 = this.mi.getPacienteCuaderno2(dato2);
        if (datosC2 != null) {
            fumu = datosC2.getFum();
            nemba = Integer.toString(datosC2.getNembarazo());

            if (fumu != null) {
                long fechaInicialMs = fumu.getTime();
                long fechaFinalMs = fecha1.getTime();
                long diferencia = fechaFinalMs - fechaInicialMs;
                double diass = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                semges = (int) (Math.round(((float) diass) / 7));

                modelo.put("nemba", Integer.toString(datosC2.getNembarazo()));
                modelo.put("anio_fum", Integer.toString(fumu.getYear() + 1900));
                modelo.put("mes_fum", Integer.toString(fumu.getMonth() + 1));
                modelo.put("dia_fum", Integer.toString(fumu.getDate()));
                modelo.put("semges", Integer.toString(semges));
                modelo.put("numpieza", Integer.toString(1 + datosC2.getNumpieza()));
//            accioncua = "Prenatal"; 
                modelo.put("accioncua", accioncua);
            } else {
                //           accioncua = "Prenatal"; 
                modelo.put("accioncua", accioncua);
            }
        }
        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo()); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);

        //cuando se apreta el boton del cuaderno 2 (parto y puerperio)
        if ("C.Prenatal-Parto-Puerperio".equals(accion)) {

            modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("dia", Integer.toString(fecha1.getDate()));
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            accioncua = "Prenatal";
            modelo.put("accioncua", accioncua);
        }
        
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHistorial);

        if ("Agregar".equals(accion)) {
            if (C2.isEmpty() != true && ("Prenatal".equals(accioncua) || "Parto".equals(accioncua) || "Parto2".equals(accioncua) || "Puerperio".equals(accioncua))) {
                return new ModelAndView("Aviso", "mensaje", "YA HA REGISTRADO UNA VEZ");
            } else {
                String accioncurativa = request.getParameter("accioncurativa");
                String id_hospital = request.getParameter("id_hospital");
                String id_hospital2 = request.getParameter("id_hospital2");
                String pap = request.getParameter("pap");
                String numepap = request.getParameter("numepap");
                String respap = request.getParameter("respap");
                String tparto = request.getParameter("tparto");
                String ivaa = request.getParameter("ivaa");
                String numeivaa = request.getParameter("numeivaa");
                String resivaa = request.getParameter("resivaa");
                String mama = request.getParameter("mama");
                String resmama = request.getParameter("resmama");
                String eclam = request.getParameter("eclam");
                String aborto = request.getParameter("aborto");
                String controlpos = request.getParameter("controlpos");
                String hemo = request.getParameter("hemo");
                String tabletas = request.getParameter("tabletas");
                String sfembarazada = request.getParameter("sfembarazada");
                String sfpuerpera = request.getParameter("sfpuerpera");
                String vitamina = request.getParameter("vitamina");
                String numero = request.getParameter("numero");
                String peso = request.getParameter("peso");
                String sexo = request.getParameter("sexo");
                String talla = request.getParameter("talla");
                String estado = request.getParameter("estado");
                //String estadod      = request.getParameter("estadod");
                String general = request.getParameter("general");
                String local = request.getParameter("local");
                String id_refer = request.getParameter("id_refer");
                String malforma = request.getParameter("malforma");
                String alimcomp = request.getParameter("alimcomp");///////////////////
                String lactmaterna = request.getParameter("lmi");///////////////////
                String apeprecoz = request.getParameter("raa");///////////////////
                String bonojap = request.getParameter("bono");///////////////////
                String rn48h = request.getParameter("rn48");///////////////////
                String mujer48h = request.getParameter("mujer48");///////////////////
                String muertemat = request.getParameter("muertemat");///////////////////
                String muertern = request.getParameter("muertern");///////////////////

                if (malforma == null || "".equals(malforma)) {
                    malforma = "0";
                }
                if (id_hospital == null || "".equals(id_hospital)) {
                    id_hospital = "0";
                }
                if (id_hospital2 == null || "".equals(id_hospital2)) {
                    id_hospital2 = "0";
                }
                if (fuera == null || "".equals(fuera)) {
                    fuera = "0";
                }
                if (tparto == null || "".equals(tparto)) {
                    tparto = "0";
                }
                if (estado == null || "".equals(estado)) {
                    estado = "V";
                }

                if (accioncurativa == null) {
                    accioncurativa = "0";
                }

                if (numepap == null) {
                    numepap = "0";
                }
                if (respap == null) {
                    respap = "0";
                }
                if (ivaa == null) {
                    ivaa = "0";
                }
                if (numeivaa == null) {
                    numeivaa = "0";
                }
                if (resivaa == null) {
                    resivaa = "0";
                }
                if (mama == null) {
                    mama = "0";
                }
                if (resmama == null) {
                    resmama = "0";
                }
                if ("1".equals(pap) && "0".equals(numepap)) {
                    numepap = "1";
                }
                if ("1".equals(ivaa) && "0".equals(numeivaa)) {
                    numeivaa = "1";
                }
                
                datosHistorial.setRepetida("");
                if (Integer.parseInt(accioncurativa) == 1 || Integer.parseInt(accioncurativa) == 2) {
                    datosHistorial.setRepetida("N");
                }
                if (Integer.parseInt(accioncurativa) == 3) {
                    datosHistorial.setRepetida("R");
                }
                if (Integer.parseInt(accioncurativa) == 4) {
                    datosHistorial.setRepetida("R");
                    datosHistorial.setRango(1);
                } else {
                    datosHistorial.setRepetida("R");
                    datosHistorial.setRango(0);
                }

                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());

                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

                Pacientes datoemb = new Pacientes();
                datoemb.setId_paciente(Integer.parseInt(id_paciente));
                datoemb.setCod_esta(cliente.getCod_esta());
                Cuadernos dato = new Cuadernos();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setPeso(0);
                dato.setTipo_egreso(Integer.parseInt(fuera));
                dato.setEstado(estado);

                if ("1".equals(pap)) {
                    dato.setPap(Integer.parseInt(pap));
                }
                if ("1".equals(eclam) || "2".equals(eclam)) {
                    dato.setEclam(Integer.parseInt(eclam));
                }
                if ("1".equals(aborto) || "2".equals(aborto) || "3".equals(aborto)) {
                    dato.setAborto(Integer.parseInt(aborto));
                }

                if ("1".equals(alimcomp)) {
                    dato.setSuma32(Integer.parseInt(alimcomp));
                }
                if ("1".equals(lactmaterna)) {
                    dato.setSuma33(Integer.parseInt(lactmaterna));
                }
                if ("1".equals(apeprecoz)) {
                    dato.setSuma34(Integer.parseInt(apeprecoz));
                }
                if ("1".equals(bonojap)) {
                    dato.setSuma35(Integer.parseInt(bonojap));
                }
                if ("1".equals(rn48h)) {
                    dato.setSuma36(Integer.parseInt(rn48h));
                }
                if ("1".equals(mujer48h)) {
                    dato.setSuma37(Integer.parseInt(mujer48h));
                }
                if ("1".equals(muertemat)) {
                    dato.setSuma38(Integer.parseInt(muertemat));
                }
                if ("1".equals(muertemat)) {
                    dato.setSuma39(Integer.parseInt(muertemat));
                }
                if ("1".equals(muertern)) {
                    dato.setSuma39(Integer.parseInt(muertern));
                }

                if ("1".equals(sfembarazada)) {
                    dato.setSfembarazada(Integer.parseInt(sfembarazada));
                    dato.setTabletas(Integer.parseInt(tabletas));
                }
                if ("2".equals(sfembarazada)) {
                    dato.setSfpuerpera(1);
                    dato.setTabletas(Integer.parseInt(tabletas));
                }
                if ("1".equals(vitamina)) {
                    dato.setVitamina(Integer.parseInt(vitamina));
                }
                if ("2".equals(vitamina)) {
                    dato.setVitamina(Integer.parseInt(vitamina));
                }
                if ("1".equals(controlpos)) {
                    dato.setControlpos(1);
                    datoemb.setEmbarazo(2);
                    datoemb.setId_factura(3);
                } else if ("2".equals(controlpos)) {
                    dato.setControlpos(2);
                    datoemb.setEmbarazo(2);
                    datoemb.setId_factura(3);
                } else {
                    dato.setControlpos(0);
                }

                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));
                dato.setSuma10(Integer.parseInt(numepap));
                dato.setSuma11(Integer.parseInt(respap));
                dato.setSuma12(Integer.parseInt(ivaa));
                dato.setSuma13(Integer.parseInt(numeivaa));
                dato.setSuma14(Integer.parseInt(resivaa));
                dato.setSuma15(Integer.parseInt(mama));
                dato.setSuma16(Integer.parseInt(resmama));
                dato.setSuma30(Integer.parseInt(id_hospital));
                dato.setSuma31(Integer.parseInt(id_hospital2));
                dato.setNumpieza(1 + numcontrol);
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setCod_esta(cliente.getCod_esta());

                switch (Integer.parseInt(accioncurativa)) {
                    case 1:
                        dato.setSuma1(1);
                        datoemb.setEmbarazo(1);
                        if (Integer.parseInt(numero) > 1) {
                            return new ModelAndView("Aviso", "mensaje", "Este no es el Primer control, Verifique numero de controles");
                        }
                        datoemb.setId_factura(1);
                        dato.setNumpieza(1);
                        break;
                    case 2:
                        dato.setSuma2(1);
                        datoemb.setEmbarazo(1);
                        if (Integer.parseInt(numero) > 1) {
                            return new ModelAndView("Aviso", "mensaje", "Este no es el Primer control, Verifique numero de controles");
                        }
                        datoemb.setId_factura(1);
                        dato.setNumpieza(1);
                        break;
                    case 3:
                        dato.setSuma3(1);
                        datoemb.setEmbarazo(1);
                        datoemb.setId_factura(1);
                        switch (Integer.parseInt(numero)) {
                            case 1:
                                dato.setNumpieza(1);
                                break;
                            case 2:
                                dato.setNumpieza(2);
                                break;
                            case 3:
                                dato.setNumpieza(3);
                                break;
                            case 4:
                                dato.setNumpieza(4);
                                dato.setSuma4(1);
                                break;
                            case 5:
                                dato.setNumpieza(5);
                                break;
                            case 6:
                                dato.setNumpieza(6);
                                break;
                            case 7:
                                dato.setNumpieza(7);
                                break;
                            case 8:
                                dato.setNumpieza(8);
                                break;
                            case 9:
                                dato.setNumpieza(9);
                                break;
                            case 10:
                                dato.setNumpieza(10);
                                break;
                            case 11:
                                dato.setNumpieza(11);
                                break;
                            case 12:
                                dato.setNumpieza(12);
                                break;
                            case 13:
                                dato.setNumpieza(13);
                                break;
                            case 14:
                                dato.setNumpieza(14);
                                break;
                            case 15:
                                dato.setNumpieza(15);
                                break;
                            case 16:
                                dato.setNumpieza(16);
                                break;
                            case 17:
                                dato.setNumpieza(17);
                                break;
                            case 18:
                                dato.setNumpieza(18);
                                break;
                            case 19:
                                dato.setNumpieza(19);
                                break;
                            default:
                                dato.setNumpieza(20);
                        }
                        break;
                    case 4:
                        dato.setSuma4(1);
                        dato.setSuma3(1);
                        datoemb.setEmbarazo(1);
                        datoemb.setId_factura(1);
                        break;
                    case 5:
                        tparto = "1";
                    case 6:
                        switch (Integer.parseInt(accioncurativa)) {
                            case 5:
                                dato.setParto(1);
                                dato.setSexo(Integer.parseInt(sexo));
                                dato.setSfpuerpera(1);
                                //dato.setSuma3(1); ////21/02/2016 no daba en los partos el numero de control
                                dato.setNumpieza(1 + numcontrol); ////21/02/2016 no daba en los partos el numero de control
                                dato.setTabletas(90);
                                dato.setVitamina(1);
                                dato.setControlpos(1);
                                datoemb.setEmbarazo(2);
                                datoemb.setId_factura(2);
                                tparto = "1";
                                break;
                            case 6:
                                dato.setParto(2);
                                dato.setSexo(Integer.parseInt(sexo));
                                dato.setSfpuerpera(1);
                                //dato.setSuma3(1); ////21/02/2016 no daba en los partos el numero de control
                                dato.setNumpieza(1 + numcontrol); ////21/02/2016 no daba en los partos el numero de control
                                dato.setTabletas(90);
                                dato.setVitamina(1);
                                dato.setControlpos(1);
                                datoemb.setEmbarazo(2);
                                datoemb.setId_factura(2);
                                tparto = "2";
                                if ("1".equals(general)) {
                                    dato.setAnestesia(1);
                                }
                                if ("2".equals(general)) {
                                    dato.setAnestesia(2);
                                }
                                break;
                        }
                        if (Integer.parseInt(peso) >= 2500) {
                            dato.setPeso(2);
                            dato.setAuto(Integer.parseInt(peso));
                        } else {
                            dato.setPeso(1);
                            dato.setAuto(Integer.parseInt(peso));
                        }
                        dato.setEstado(estado);
                        break;
                    case 7:
                    case 8:
                        switch (Integer.parseInt(accioncurativa)) {
                            case 7:
                                dato.setParto(3);
                                dato.setTabletas(90);
                                dato.setVitamina(1);
                                if (Integer.parseInt(peso) >= 2500) {
                                    dato.setPeso(2);
                                    dato.setAuto(Integer.parseInt(peso));
                                } else {
                                    dato.setPeso(1);
                                    dato.setAuto(Integer.parseInt(peso));
                                }
                                dato.setSexo(Integer.parseInt(sexo));
                                dato.setSfpuerpera(1);
                                //dato.setSuma3(1); ////21/02/2016 no daba en los partos el numero de control
                                dato.setNumpieza(1 + numcontrol); ////21/02/2016 no daba en los partos el numero de control
                                dato.setTabletas(90);
                                dato.setVitamina(1);
                                dato.setControlpos(1);
                                datoemb.setEmbarazo(2);
                                datoemb.setId_factura(2);
                                break;
                            case 8:
                                dato.setParto(4);
                                if (Integer.parseInt(peso) >= 2500) {
                                    dato.setPeso(2);
                                    dato.setAuto(Integer.parseInt(peso));
                                } else {
                                    dato.setPeso(1);
                                    dato.setAuto(Integer.parseInt(peso));
                                }
                                dato.setControlpos(1);
                                //dato.setSuma3(1); ////21/02/2016 no daba en los partos el numero de control
                                dato.setNumpieza(1 + numcontrol); ////21/02/2016 no daba en los partos el numero de control
                                dato.setSexo(Integer.parseInt(sexo));
                                datoemb.setEmbarazo(2);
                                datoemb.setId_factura(2);
                                break;
                        }
                        dato.setEstado(estado);
                        break;
                    case 9:
                        dato.setParto(5);
                        break;
                    case 10:
                        dato.setParto(6);
                        break;
                    case 11:
                        dato.setParto(7);
                        break;
                    case 12:
                        dato.setParto(8);
                        break;
                }
                //calcula el IMC
                double peso1 = 0.0001;

                dato.setParto(Integer.parseInt(tparto));  /////tipo de parto
                if (Integer.parseInt(tparto) > 2) {
                    dato.setPeso(2);
                    dato.setEstado(estado);
                    dato.setTabletas(90);
                    dato.setVitamina(1);
                    dato.setSfpuerpera(1);
                    dato.setSexo(Integer.parseInt(sexo));
                    dato.setAuto(Integer.parseInt(peso));
                }

                if (semges <= 16) {
                    peso1 = datosHistorial.getPeso() - 1;
                }
                if (semges >= 17 && semges <= 20) {
                    peso1 = datosHistorial.getPeso() - 1;
                }
                if (semges >= 21 && semges <= 24) {
                    peso1 = datosHistorial.getPeso() - 5.5;
                }
                if (semges >= 25 && semges <= 28) {
                    peso1 = datosHistorial.getPeso() - 7;
                }
                if (semges >= 29 && semges <= 32) {
                    peso1 = datosHistorial.getPeso() - 8.5;
                }
                if (semges >= 33 && semges <= 36) {
                    peso1 = datosHistorial.getPeso() - 10;
                }
                if (semges >= 37) {
                    peso1 = datosHistorial.getPeso() - 11;
                }
                double valor = peso1 / ((datosHistorial.getTalla()) * (datosHistorial.getTalla()) / (100 * 100));
                if (valor <= 20) {
                    dato.setImc(1);
                } else if (valor > 20 && valor <= 25) {
                    dato.setImc(2);
                } else if (valor > 25 && valor <= 30) {
                    dato.setImc(3);
                } else {
                    dato.setImc(4);
                }
                // fin de calcula 
                if ("Puerperio".equals(accioncua)) {
                    dato.setSemanas(0);
                    dato.setNembarazo(2);
                    dato.setFum(fecha1);
                } else {
                    if ("".equals(semanas) || "".equals(nembarazo)) {
                        return new ModelAndView("Aviso", "mensaje", "Faltan datos semanas gestacion y No embarazo");
                    } else {
                        if (Integer.parseInt(semanas) > 52) {
                            datoemb.setId_factura(0);
                            //semanas="0";
                        }
                    }
                    dato.setSemanas(Integer.parseInt(semanas));
                    dato.setNembarazo(Integer.parseInt(nembarazo));
                    int diax = Integer.parseInt(dia);
                    int mesx = Integer.parseInt(mes) - 1;
                    int aniox = Integer.parseInt(anio) - 1900;
                    Date fum = new Date(aniox, mesx, diax);
                    dato.setFum(fum);
                }
                dato.setSuma50(Integer.parseInt(malforma));
                dato.setSuma51(Integer.parseInt(id_refer));
                try {
                    this.mi.setCrearCuaderno2(dato);
                    datoemb.setId_carpeta(Integer.parseInt(id_reservacion));
                    this.mi.setModificaPacienteEmb(datoemb);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "La actualización C2 no se cumplio, verifique los datos");
                }
            }
        }

        if ("Perinatal".equals(accion)) {
            try {
                modelo.put("anio_r", Integer.toString(datosC2.getFum().getYear() + 1900));
                modelo.put("mes_r", Integer.toString(datosC2.getFum().getMonth() + 1));
                modelo.put("dia_r", Integer.toString(datosC2.getFum().getDate()));
                modelo.put("peso", Double.toString(datosC2.getPeso()));
                modelo.put("talla", Double.toString(datosC2.getTalla()));
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Verifique los datos");
            }

            return new ModelAndView("administrarhistoriales/Perinatal", modelo);
        }

        if ("Prenatal".equals(accion)) {
            accioncua = "Prenatal";
            modelo.put("accioncua", accioncua);
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listaOdon = this.mi.getPacienteCuaderno2(dato);
            modelo.put("listaControlPre", listaOdon);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);

            Cuadernos dato22 = new Cuadernos();
            dato22.setCod_esta(cliente.getCod_esta());
            dato2.setId_paciente(Integer.parseInt(id_paciente));
            dato2.setTipo("0");   ////getVerCuaderno2Ult
            Cuadernos datosC22 = this.mi.getVerCuaderno2Ult(dato2);
            if (datosC22 != null) {
                modelo.put("numpieza", Integer.toString(1 + datosC22.getNumpieza()));
            } else {
                modelo.put("numpieza", Integer.toString(0));
            }

            return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);
        }

        if ("Parto Dentro".equals(accion)) {
            accioncua = "Parto";
            modelo.put("accioncua", accioncua);
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listaOdon = this.mi.getPacienteCuaderno2(dato);
            modelo.put("listaControlPre", listaOdon);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);
        }

        if ("Parto Multiple".equals(accion)) {
            accioncua = "Multiple";
            modelo.put("accioncua", accioncua);
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listaOdon = this.mi.getPacienteCuaderno2(dato);
            modelo.put("listaControlPre", listaOdon);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);
        }

        if ("Parto Fuera".equals(accion)) {
            accioncua = "Parto2";
            modelo.put("accioncua", accioncua);
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listaOdon = this.mi.getPacienteCuaderno2(dato);
            modelo.put("listaControlPre", listaOdon);
            return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);
        }

        if ("Puerperio".equals(accion)) {
            accioncua = "Puerperio";
            semanas = "0";
            nembarazo = "1";
            modelo.put("semanas", semanas);
            modelo.put("nembarazo", nembarazo);
            modelo.put("accioncua", accioncua);
            Cuadernos dato = new Cuadernos();
            dato.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
            dato.setCod_esta(cliente.getCod_esta());  ///19-05-2015
            List listaOdon = this.mi.getPacienteCuaderno2(dato);
            modelo.put("listaControlPre", listaOdon);
            return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);
        }

        if ("Eliminar".equals(accion)) {
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            accioncua = "Prenatal";
            modelo.put("accioncua", accioncua);
            this.mi.setEliminarCuaderno2(datox);
        }

        if ("Terminar".equals(accion)) {

            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de cuaderno2
        //Cuadernos dato=new Cuadernos();
        dato2.setId_historial(Integer.parseInt(id_reservacion));   ///19-05-2015
        dato2.setCod_esta(cliente.getCod_esta());  ///19-05-2015
        List listaOdon = this.mi.getPacienteCuaderno2(dato2);
        modelo.put("listaControlPre", listaOdon);

        //Cuadernos dato2= new Cuadernos();
        dato2.setCod_esta(cliente.getCod_esta());
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        List listac2 = this.mi.getVerCuaderno2Uni(dato2);
        modelo.put("listac2", listac2);

        Cuadernos dato22 = new Cuadernos();
        dato2.setCod_esta(cliente.getCod_esta());
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        dato2.setTipo("0");   ////getVerCuaderno2Ult
        Cuadernos datosC22 = this.mi.getVerCuaderno2Ult(dato2);
        if (datosC22 != null) {
            modelo.put("numpieza", Integer.toString(1 + datosC22.getNumpieza()));
        } else {
            modelo.put("numpieza", Integer.toString(0));
        }

        Cuadernos dato3 = new Cuadernos();
        dato3.setCod_esta(cliente.getCod_esta());
        dato3.setId_paciente(Integer.parseInt(id_paciente));
        dato3.setTipo("3");   ////getVerCuaderno2Ult
        Cuadernos datosC32 = this.mi.getVerCuaderno3Count(dato3);
        if (datosC32 != null) {
            modelo.put("numpap", Integer.toString(1 + datosC32.getSuma1()));
        } else {
            modelo.put("numpap", Integer.toString(0));
        }

        return new ModelAndView("administrarcuadernos/Cuaderno2", modelo);

    }
}
