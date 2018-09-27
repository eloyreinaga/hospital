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
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Cuaderno4Controlador {

    private final MiFacade mi;

    public Cuaderno4Controlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/Cuaderno4.do")
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
        String PCronica = "A";
        Date fecha_nac = new Date();
        Date fecha_act = new Date();
        int semanas = 0;

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datol = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(datol.getCod_esta()));
        modelo.put("localidades", datol);
        local.setArea("R");
        local.setId_persona(1);
        List EstabRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestab", EstabRef);
        local.setId_persona(2);
        List EstabCRef = this.mi.getListarEstabRef(local);
        modelo.put("listarestabC", EstabCRef);
        modelo.put("tipo", datol.getTipo());

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente));
        modelo.put("urgencias", Integer.toString(cliente.getId_almacen()));
        modelo.put("datos", buscarPaciente);
        fecha_nac = buscarPaciente.getFec_nacimiento();

        long fechaInicialMs = fecha_nac.getTime();
        long fechaFinalMs = fecha1.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        semanas = (int) (Math.round(((float) dias) / 7));

        Cuadernos dato2 = new Cuadernos();
        dato2.setCod_esta(cliente.getCod_esta());
        dato2.setId_paciente(Integer.parseInt(id_paciente));
        dato2.setTipo("4");
        Cuadernos datosC = this.mi.getVerCuaderno4PaciUlt(dato2);   /////getVerCuaderno4PaciUlt
        modelo.put("datosC", datosC);
        modelo.put("swinter", swinter);
        if ("Cuaderno4".equals(accion)) {// llena solo el cuaderno 4 unico del pciente
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));
            List listaAten4Paci = this.mi.getVerCuaderno4Uni(dato);
            modelo.put("listaCuaderno4", listaAten4Paci);
            List listaCie = this.mi.getVerCuaderno1Cie(dato);
            modelo.put("listaCie", listaCie);
            return new ModelAndView(new VerCuaderno4PDF(), modelo);
        }

        Cuadernos datoc4 = new Cuadernos();
        datoc4.setCod_esta(cliente.getCod_esta());
        datoc4.setId_historial(Integer.parseInt(id_reservacion));
        List C4 = this.mi.getPacienteCuaderno4(datoc4);

        if (datosC != null) {
            Pultimo = datosC.getDglobal();
            PCronica = datosC.getDcronica();
        }

        //calculamos la nutricion
        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHisto = this.mi.getDatosHistorial(datohi);
        modelo.put("datoHisto", datosHisto);

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

        if ("C.Atencion Integ.< 5 anios".equals(accion) || ".Terminar.".equals(accion) || "C13.Pediatria".equals(accion)) {
            if ("3".equals(datol.getTipo())) {
                accion4 = "Ninio Sano";
                modelo.put("accion4", accion4);
            } else {
                accion4 = "Ninio Enfermo";
                modelo.put("accion4", accion4);
            }
        }

//Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(buscarPaciente.getId_tipo_sexo());
        modelo.put("buscarSexo", buscarsexo);

        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);
        modelo.put("tipo_medico", tipo_medico);

        if ("Agregar".equals(accion)) {
            String id_hospital = request.getParameter("id_hospital");
            String id_hospital2 = request.getParameter("id_hospital2");
            String tipo = request.getParameter("tipo");
            String dgloba = request.getParameter("dgloba");
            String dglobal = request.getParameter("dglobal");
            String dcronica = request.getParameter("dcronica");
            String dhierro = request.getParameter("dhierro");
            String dvitaa = request.getParameter("dvitaa");
            String lmexclu = request.getParameter("lmexclu");
            String suma1 = request.getParameter("suma1");
            String suma2 = request.getParameter("suma2");
            String suma3 = request.getParameter("suma3");
            String suma4 = request.getParameter("suma4");
            String suma5 = request.getParameter("suma5");
            //llena datos para desarrollo del niño
            String observa = request.getParameter("observa");
            String auto = request.getParameter("anemia");
            String urinaria = request.getParameter("edema");
            String sistemica = request.getParameter("emacia");
            String arterial = request.getParameter("lista1");
            String diabetes = request.getParameter("lista2");
            String glome = request.getParameter("lista3");
            String tracto = request.getParameter("lista4");
            String lupus = request.getParameter("lista5");
            String litiasis = request.getParameter("v1");
            String nooplasia = request.getParameter("v2");
            String nefro = request.getParameter("v3");
            String disli = request.getParameter("v4");
            String frenal = request.getParameter("v5");
            String resultado = request.getParameter("resultado");
            String fuera = request.getParameter("fuera");
            String mortalidad = request.getParameter("mortalidad");
            String id_refer = request.getParameter("id_refer");
            String bja = request.getParameter("bja");

            if ("1".equals(suma5) && (buscarPaciente.getMes() != 6 || buscarPaciente.getMes() != 7) && buscarPaciente.getEdad() > 0) {
                suma5 = "0";
            }
            if (id_hospital == null || "".equals(id_hospital)) {
                id_hospital = "0";
            }
            if (id_hospital2 == null || "".equals(id_hospital2)) {
                id_hospital2 = "0";
            }
            if (bja == null || "".equals(bja)) {
                bja = "0";
            }
            if (id_refer == null || "".equals(id_refer)) {
                id_refer = "0";
            }
            if (mortalidad == null || "".equals(mortalidad)) {
                mortalidad = "0";
            }

            int salud = 0;
            int desa = 0;

            if (C4.isEmpty() != true) {
                if ("Ninio Sano".equals(accion4) || "Ninio Enfermo".equals(accion4)) {
                    return new ModelAndView("Aviso", "mensaje", "YA SE HA REGISTRADO UNA VEZ");
                } else {
                    if (lupus == null) {
                        lupus = "2";
                        frenal = "0";
                    }
                    if (tracto == null) {
                        tracto = "2";
                        disli = "0";
                    }
                    if (glome == null) {
                        glome = "2";
                        nefro = "0";
                    }
                    if (diabetes == null) {
                        diabetes = "2";
                        nooplasia = "0";
                    }
                    if (arterial == null) {
                        arterial = "2";
                        litiasis = "0";
                    }

                    Cuadernos dato42 = new Cuadernos();
                    dato42.setCod_esta(cliente.getCod_esta());
                    dato42.setId_paciente(Integer.parseInt(id_paciente));
                    dato42.setTipo("4");
                    Cuadernos dato = this.mi.getVerCuaderno4PaciUlt(dato42);   /////getVerCuaderno4PaciUlt
                    ///para la parde de desarrollo 2 puntos por item
                    if ("L".equals(dato.getDglobal()) || "N".equals(dato.getDglobal()) || "S".equals(dato.getDglobal()) || "O".equals(dato.getDglobal())) {
                        salud = salud + 2;
                    } else {
                        salud = salud + 0;
                    }
                    ///23/07/2013 aumento Leve para estado bueno 
                    if ("N".equals(dato.getDcronica())) {
                        salud = salud + 2;
                    } else {
                        salud = salud + 0;
                    }

                    dato.setId_historial(Integer.parseInt(id_reservacion));
                    dato.setTipoconsulta(dato.getTipoconsulta());
                    //dato.setTipo_egreso(Integer.parseInt(fuera));
                    dato.setAqv(dato.getId_cuaderno());
                    if ("Desarrollo".equals(accion4)) {
                        //cuaderno de seguimiento
                        dato.setAuto(Integer.parseInt(auto));
                        dato.setUrinaria(Integer.parseInt(urinaria));
                        dato.setSistemica(Integer.parseInt(sistemica));
                        dato.setArterial(Integer.parseInt(arterial));
                        dato.setDiabetes(Integer.parseInt(diabetes));
                        dato.setGlome(Integer.parseInt(glome));
                        dato.setTracto(Integer.parseInt(tracto));
                        dato.setLupus(Integer.parseInt(lupus));
                        dato.setLitiasis(Integer.parseInt(litiasis));
                        dato.setNooplasia(Integer.parseInt(nooplasia));
                        dato.setNefro(Integer.parseInt(nefro));
                        dato.setDisli(Integer.parseInt(disli));
                        dato.setFrenal(Integer.parseInt(frenal));

                        salud = salud + Integer.parseInt(auto) + Integer.parseInt(urinaria) + Integer.parseInt(sistemica);
                        desa = desa + Integer.parseInt(arterial) + Integer.parseInt(diabetes) + Integer.parseInt(glome) + Integer.parseInt(tracto) + Integer.parseInt(lupus);
                        if (salud == 10 && desa == 10) {
                            resultado = "SANO";
                        } else {
                            if (salud < 10 && desa == 10) {
                                resultado = "OS";
                            } else {
                                if (salud == 10 && desa < 10) {
                                    resultado = "OD";
                                } else {
                                    resultado = "NR"; ////////////////rpabon
                                }
                            }
                        }
                        dato.setResultado(resultado);
                        dato.setNumpieza(salud + desa);
                        modelo.put("resultado", resultado);
                        this.mi.setModificarCuaderno4(dato);
                    }/////7fin desarrollo

                    if ("Desarrollo Simple".equals(accion4)) {
                        String lista0 = request.getParameter("lista0");
                        String lista1 = request.getParameter("lista1");
                        String lista2 = request.getParameter("lista2");
                        String lista3 = request.getParameter("lista3");
                        String lista4 = request.getParameter("lista4");
                        String lista5 = request.getParameter("lista5");
                        String lista6 = request.getParameter("lista6");
                        String lista7 = request.getParameter("lista7");
                        String lista8 = request.getParameter("lista8");
                        String lista9 = request.getParameter("lista9");
                        String lista10 = request.getParameter("lista10");
                        String lista11 = request.getParameter("lista11");
                        String lista12 = request.getParameter("lista12");
                        String lista13 = request.getParameter("lista13");
                        String lista14 = request.getParameter("lista14");
                        String v0 = request.getParameter("v0");
                        String v1 = request.getParameter("v1");
                        String v2 = request.getParameter("v2");
                        String v3 = request.getParameter("v3");
                        String v4 = request.getParameter("v4");
                        String v5 = request.getParameter("v5");
                        String v6 = request.getParameter("v6");
                        String v7 = request.getParameter("v7");
                        String v8 = request.getParameter("v8");
                        String v9 = request.getParameter("v9");
                        String v10 = request.getParameter("v10");
                        String v11 = request.getParameter("v11");
                        String v12 = request.getParameter("v12");
                        String v13 = request.getParameter("v13");
                        String v14 = request.getParameter("v14");

                        //cuaderno de seguimiento simple
                        if (v0 != null && lista0 != null) {
                            dato.setSuma1(Integer.parseInt(v0) * Integer.parseInt(lista0));
                        } else {
                            dato.setSuma1(0);
                        }
                        if (v1 != null && lista1 != null) {
                            dato.setSuma2(Integer.parseInt(v1) * Integer.parseInt(lista1));
                        } else {
                            dato.setSuma2(0);
                        }
                        if (v2 != null && lista2 != null) {
                            dato.setSuma3(Integer.parseInt(v2) * Integer.parseInt(lista2));
                        } else {
                            dato.setSuma3(0);
                        }
                        if (v3 != null && lista3 != null) {
                            dato.setSuma4(Integer.parseInt(v3) * Integer.parseInt(lista3));
                        } else {
                            dato.setSuma4(0);
                        }
                        if (v4 != null && lista4 != null) {
                            dato.setSuma5(Integer.parseInt(v4) * Integer.parseInt(lista4));
                        } else {
                            dato.setSuma5(0);
                        }
                        if (v5 != null && lista5 != null) {
                            dato.setSuma6(Integer.parseInt(v5) * Integer.parseInt(lista5));
                        } else {
                            dato.setSuma6(0);
                        }
                        if (v6 != null && lista6 != null) {
                            dato.setSuma7(Integer.parseInt(v6) * Integer.parseInt(lista6));
                        } else {
                            dato.setSuma7(0);
                        }
                        if (v7 != null && lista7 != null) {
                            dato.setSuma8(Integer.parseInt(v7) * Integer.parseInt(lista7));
                        } else {
                            dato.setSuma8(0);
                        }
                        if (v8 != null && lista8 != null) {
                            dato.setSuma9(Integer.parseInt(v8) * Integer.parseInt(lista8));
                        } else {
                            dato.setSuma9(0);
                        }
                        if (v9 != null && lista9 != null) {
                            dato.setSuma10(Integer.parseInt(v9) * Integer.parseInt(lista9));
                        } else {
                            dato.setSuma10(0);
                        }
                        if (v10 != null && lista10 != null) {
                            dato.setSuma11(Integer.parseInt(v10) * Integer.parseInt(lista10));
                        } else {
                            dato.setSuma11(0);
                        }
                        if (v11 != null && lista11 != null) {
                            dato.setSuma12(Integer.parseInt(v11) * Integer.parseInt(lista11));
                        } else {
                            dato.setSuma12(0);
                        }
                        if (v12 != null && lista12 != null) {
                            dato.setSuma13(Integer.parseInt(v12) * Integer.parseInt(lista12));
                        } else {
                            dato.setSuma13(0);
                        }
                        if (v13 != null && lista13 != null) {
                            dato.setSuma14(Integer.parseInt(v13) * Integer.parseInt(lista13));
                        } else {
                            dato.setSuma14(0);
                        }

                        dato.setSuma15(1);
                        dato.setAspecto("2");
                        modelo.put("resultado", resultado);
                        this.mi.setModificarCuaderno24(dato);
                    }/////fin desarrollo simple

                }

                accion4 = "Ninio Enfermo";
                modelo.put("accion4", accion4);
            } else {
                if ("Desarrollo".equals(accion4) || "Desarrollo Simple".equals(accion4)) {
                    return new ModelAndView("Aviso", "mensaje", "No puede elegir esta OPCION, Elija niño Sano o Enfermo");
                }
                Historiales datohis = new Historiales();
                datohis.setId_historial(Integer.parseInt(id_reservacion));
                datohis.setCod_esta(cliente.getCod_esta());
                Historiales datosHistorial = this.mi.getDatosHistorial(datohis);

                datosHistorial.setRepetida(tipo);

                datosHistorial.setId_historial(Integer.parseInt(id_reservacion));
                datosHistorial.setCod_esta(cliente.getCod_esta());
                datosHistorial.setId_por(cliente.getId_persona());
                int iResultado = this.mi.setRegistrarHistorial(datosHistorial);

                Cuadernos dato = new Cuadernos();
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setTipo_egreso(Integer.parseInt(fuera));
                dato.setSuma10(Integer.parseInt(bja));
                dato.setSuma11(Integer.parseInt(id_refer));
                dato.setSuma12(Integer.parseInt(mortalidad));
                dato.setTipoconsulta(tipo);

                if ("Ninio Sano".equals(accion4)) {

                    dato.setObserva(dgloba);
                    dato.setDglobal(dglobal);
                    dato.setDcronica(dcronica);
                    dato.setDhierro(dhierro);
                    dato.setDvitaa(dvitaa);
                    dato.setLmexclu(lmexclu);

                    int valor = 0;
                    if ("1".equals(request.getParameter("desesti1"))) {
                        valor += 1 << 0;
                    }
                    if ("2".equals(request.getParameter("desesti2"))) {
                        valor += 1 << 1;
                    }
                    if ("3".equals(request.getParameter("desesti3"))) {
                        valor += 1 << 2;
                    }
                    if ("4".equals(request.getParameter("desesti4"))) {
                        valor += 1 << 3;
                    }
                    if ("5".equals(request.getParameter("desesti5"))) {
                        valor += 1 << 4;
                    }
                    if ("6".equals(request.getParameter("desesti6"))) {
                        valor += 1 << 5;
                    }
                    if ("7".equals(request.getParameter("desesti7"))) {
                        valor += 1 << 6;
                    }
                    if ("8".equals(request.getParameter("desesti8"))) {
                        valor += 1 << 7;
                    }

                    dato.setDesesti(valor);

                    if ("1".equals(suma1)) {
                        dato.setSuma1(Integer.parseInt(suma1));
                    }
                    if ("1".equals(suma2)) {
                        dato.setSuma2(Integer.parseInt(suma2));
                    }
                    if ("1".equals(suma3)) {
                        dato.setSuma3(Integer.parseInt(suma3));
                    }
                    if ("1".equals(suma4)) {
                        dato.setSuma4(Integer.parseInt(suma4));
                    }
                    if ("1".equals(suma5)) {
                        dato.setSuma5(Integer.parseInt(suma5));
                    }

                    if (Pultimo.equals(dglobal) && PCronica.equals(dcronica)) {
                        dato.setTabletas(0);
                    } ////guarda en tipo para cuando cambia de estado nutricional
                    else {
                        if (!Pultimo.equals(dglobal) && PCronica.equals(dcronica)) {
                            dato.setTabletas(1);          ///cambia solo cuando peso cambia de estado nutricional
                            if ("R".equals(tipo)) {
                                dato.setTabletas(0); ///11/03/2016 cuando esta vacio los datos pero el carnet del niño ya tiene datos
                            }
                        } else {
                            if (Pultimo.equals(dglobal) && !PCronica.equals(dcronica)) {
                                dato.setTabletas(2); ////cambia solo cuando talla cambia estado nutricional
                                if ("R".equals(tipo)) {
                                    dato.setTabletas(0); ///11/03/2016 cuando esta vacio los datos pero el carnet del niño ya tiene datos
                                }
                            } else {
                                dato.setTabletas(3);   /////para los cambio de estado nutricional en campo "tipo" cambia en las 2
                                if ("R".equals(tipo)) {
                                    dato.setTabletas(0); ///11/03/2016 cuando esta vacio los datos pero el carnet del niño ya tiene datos
                                }
                            }
                        }
                    }   ////////cuando cambia esto nutricianal se pone en uno

                } else {
                    dato.setTipoconsulta(tipo);
                    dato.setTipo_egreso(Integer.parseInt(fuera));
                    dato.setObserva("0");
                    dato.setDglobal("0");
                    dato.setDcronica("0");
                    dato.setDhierro(dhierro);
                    dato.setDvitaa(dvitaa);
                    dato.setLmexclu(lmexclu);
                    dato.setSeleccion(1);
                    if (cliente.getId_cargo() == 13) {////para meter desde vacunas las campanas
                        dato.setSeleccion(0);
                        dato.setTipoconsulta("0");
                    }

                    int valor = 0;
                    if ("1".equals(request.getParameter("desesti1"))) {
                        valor += 1 << 0;
                    }
                    if ("2".equals(request.getParameter("desesti2"))) {
                        valor += 1 << 1;
                    }
                    if ("3".equals(request.getParameter("desesti3"))) {
                        valor += 1 << 2;
                    }
                    if ("4".equals(request.getParameter("desesti4"))) {
                        valor += 1 << 3;
                    }
                    if ("5".equals(request.getParameter("desesti5"))) {
                        valor += 1 << 4;
                    }
                    if ("6".equals(request.getParameter("desesti6"))) {
                        valor += 1 << 5;
                    }
                    if ("7".equals(request.getParameter("desesti7"))) {
                        valor += 1 << 6;
                    }
                    if ("8".equals(request.getParameter("desesti8"))) {
                        valor += 1 << 7;
                    }

                    dato.setDesesti(valor);

                    if ("1".equals(suma1)) {
                        dato.setSuma1(Integer.parseInt(suma1));
                    }
                    if ("1".equals(suma2)) {
                        dato.setSuma2(Integer.parseInt(suma2));
                    }
                    if ("1".equals(suma3)) {
                        dato.setSuma3(Integer.parseInt(suma3));
                    }
                    if ("1".equals(suma4)) {
                        dato.setSuma4(Integer.parseInt(suma4));
                    }
                    if ("1".equals(suma5)) {
                        dato.setSuma5(Integer.parseInt(suma5));
                    }

                }
                dato.setReferido(request.getParameter("ref"));
                dato.setContraref(request.getParameter("cref"));
                dato.setSuma30(Integer.parseInt(id_hospital));
                dato.setSuma31(Integer.parseInt(id_hospital2));
                //Se aumenta datos de seguimiento
                dato.setAuto(0);
                dato.setUrinaria(0);
                dato.setSistemica(0);
                dato.setArterial(0);
                dato.setDiabetes(0);
                dato.setGlome(0);
                dato.setTracto(0);
                dato.setLupus(0);
                dato.setLitiasis(0);
                dato.setNooplasia(0);
                dato.setNefro(0);
                dato.setDisli(0);
                dato.setFrenal(0);
                dato.setResultado("");
                dato.setTipo_egreso(Integer.parseInt(fuera));
                dato.setId_persona(Integer.parseInt(id_persona));
                dato.setCod_esta(cliente.getCod_esta());
                // grabar los datos introducidos en la ventana  
                if(dias<60 ){///30/07/2018 solo se ebe evaluar a mayores de 2 meses
                    dato.setTabletas(0); ////no cambia como nuevo
                }
                this.mi.setCrearCuaderno4(dato);
                accion4 = "Ninio Enfermo";
                modelo.put("accion4", accion4);
            }
        }
        if ("Ninio Sano".equals(accion) && cliente.getId_cargo() != 13) {
            if (p == 0 || t == 0) {
                return new ModelAndView("Aviso", "mensaje", "Para niño SANO,Falta datos de Peso o Talla");
            }
            try {
                if (datosHisto.getPeso() <= datoNu.getM3ds()) {
                    nutricion = "G";
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Verifique la talla y peso, son incorrectos para la edad");
            }

            if (datosHisto.getPeso() < datoNu.getM3ds()) {
                nutricion = "G";
            }
            if (datosHisto.getPeso() > datoNu.getM3ds() && datosHisto.getPeso() <= datoNu.getM2ds()) {
                nutricion = "M";
            }
            if (datosHisto.getPeso() > datoNu.getM2ds() && datosHisto.getPeso() <= datoNu.getM1ds()) {
                nutricion = "N";////23/07/2013 se aumenta desde -2 hasta +2 como normal
            }
            if (datosHisto.getPeso() > datoNu.getM1ds() && datosHisto.getPeso() <= datoNu.getDs2()) {
                nutricion = "N";
            }
            if (datosHisto.getPeso() > datoNu.getDs2() && datosHisto.getPeso() <= datoNu.getDs3()) {
                nutricion = "S";
            }
            if (datosHisto.getPeso() > datoNu.getDs3()) {
                nutricion = "O";
            }
            modelo.put("nutricion", nutricion);
            //Calculamos la nutricion talla edad
            String tallaedad = "";
            ///Cuadernos datoTEdad = this.mi.getDatosTallaEdad(datosHisto.getEdad(),datosHisto.getMes(),buscarPaciente.getId_tipo_sexo()) ;

            Cuadernos datote = new Cuadernos();
            datote.setTalla(t);
            datote.setMes(datosHisto.getEdad() * 12 + datosHisto.getMes());
            Cuadernos datoTEdad = new Cuadernos();

            if (buscarPaciente.getId_tipo_sexo() == 1) {
                if (datosHisto.getEdad() < 2) {
                    datoTEdad = this.mi.getDatosTallaEdadF02(datote);
                } else {
                    datoTEdad = this.mi.getDatosTallaEdadF25(datote);
                }
            } else {
                if (datosHisto.getEdad() < 2) {
                    datoTEdad = this.mi.getDatosTallaEdadM02(datote);
                } else {
                    datoTEdad = this.mi.getDatosTallaEdadM25(datote);
                }
            }

            //Cuadernos datoTEdad = this.mi.getDatosTallaEdad(datosHisto.getEdad(),datosHisto.getMes(),buscarPaciente.getId_tipo_sexo()) ;
            if (datosHisto.getTalla() <= datoTEdad.getM3ds()) {
                tallaedad = "L";
            }
            if (datosHisto.getTalla() > datoTEdad.getM3ds() && datosHisto.getTalla() <= datoTEdad.getM2ds()) {
                tallaedad = "L";
            }
            if (datosHisto.getTalla() > datoTEdad.getM2ds()) {
                tallaedad = "N";
            }
            modelo.put("tallaedad", tallaedad);

            if (t <= datoTEdad.getM3ds()) {
                tallaedad = "L";
            }
            if (t > datoTEdad.getM3ds() && t <= datoTEdad.getM2ds()) {
                tallaedad = "L";
            }
            if (t > datoTEdad.getM2ds()) {
                tallaedad = "N";
            }

            //desnutricion global
            String pesoedad = "";
            Cuadernos datoc = new Cuadernos();
            datoc.setId_tipo_sexo(buscarPaciente.getId_tipo_sexo());
            datoc.setMes(datosHisto.getEdad());
            if (semanas < 24) {
                datoc.setEdad(semanas);
                if (buscarPaciente.getId_tipo_sexo() == 1) {
                    Cuadernos datoPEdad = this.mi.getDatosPesoEdadF02(datoc);
                    if (datosHisto.getPeso() <= datoPEdad.getM3ds()) {
                        pesoedad = "G";
                    }
                    if (datosHisto.getPeso() > datoPEdad.getM3ds() && datosHisto.getTalla() <= datoPEdad.getM2ds()) {
                        pesoedad = "L";
                    }
                    if (datosHisto.getPeso() > datoPEdad.getM2ds()) {
                        pesoedad = "N";
                    }
                    modelo.put("pesoedad", pesoedad);
                } else {
                    Cuadernos datoPEdad = this.mi.getDatosPesoEdadM02(datoc);
                    if (datosHisto.getPeso() <= datoPEdad.getM3ds()) {
                        pesoedad = "G";
                    }
                    if (datosHisto.getPeso() > datoPEdad.getM3ds() && datosHisto.getTalla() <= datoPEdad.getM2ds()) {
                        pesoedad = "L";
                    }
                    if (datosHisto.getPeso() > datoPEdad.getM2ds()) {
                        pesoedad = "N";
                    }
                    modelo.put("pesoedad", pesoedad);
                }

            } else {
                pesoedad = "N";
                modelo.put("pesoedad", pesoedad);
            }

            accion4 = "Ninio Sano";
            modelo.put("accion4", accion4);
            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4r);
            modelo.put("listaOdon", listaOdon);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipo("P");
            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);
            //modelo.put("tipo", "1");
            return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
        }
        if ("Ninio Enfermo".equals(accion)) {
            accion4 = "Ninio Enfermo";
            modelo.put("accion4", accion4);
            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4r);
            modelo.put("listaOdon", listaOdon);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipo("P");
            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);

            return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
        }

        if ("Desarrollo SSS".equals(accion)) {
            accion4 = "Desarrollo";
            modelo.put("accion4", accion4);
            modelo.put("lista1", Integer.toString(0));
            modelo.put("lista2", Integer.toString(0));
            modelo.put("lista3", Integer.toString(0));
            modelo.put("lista4", Integer.toString(0));
            modelo.put("lista5", Integer.toString(0));
            modelo.put("v1", Integer.toString(0));
            modelo.put("v2", Integer.toString(0));
            modelo.put("v3", Integer.toString(0));
            modelo.put("v4", Integer.toString(0));
            modelo.put("v5", Integer.toString(0));
            modelo.put("v6", Integer.toString(0));
            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4r);
            modelo.put("listaOdon", listaOdon);

            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setTipo("2");
            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);
            dato.setId_paciente((buscarPaciente.getMes() + buscarPaciente.getEdad() * 12));
            dato.setId_laboratorio(0);
            List listam1 = this.mi.getVerDesarrollo2(dato);
            modelo.put("listam1", listam1);
            if (listam1.isEmpty() == true) {
                dato.setId_paciente((buscarPaciente.getEdad() * 12) + (buscarPaciente.getMes() - 1));
                List listam2 = this.mi.getVerDesarrollo2(dato);
                modelo.put("listam1", listam2);
                if (listam2.isEmpty() == true) {
                    dato.setId_paciente((buscarPaciente.getEdad() * 12 + buscarPaciente.getMes() - 2));
                    List listam3 = this.mi.getVerDesarrollo2(dato);
                    modelo.put("listam1", listam3);
                    if (listam3.isEmpty() == true) {
                        dato.setId_paciente((buscarPaciente.getEdad() * 12 + buscarPaciente.getMes() - 3));
                        List listam4 = this.mi.getVerDesarrollo2(dato);
                        modelo.put("listam1", listam4);
                    }
                }
            }

            return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
        }

        if ("Desarrollo".equals(accion)) {
            accion4 = "Desarrollo"; ////cambiado de dasarrollo simple
            modelo.put("accion4", accion4);
            modelo.put("lista0", Integer.toString(0));
            modelo.put("lista1", Integer.toString(0));
            modelo.put("lista2", Integer.toString(0));
            modelo.put("lista3", Integer.toString(0));
            modelo.put("lista4", Integer.toString(0));
            modelo.put("lista5", Integer.toString(0));
            modelo.put("lista6", Integer.toString(0));
            modelo.put("lista7", Integer.toString(0));
            modelo.put("lista8", Integer.toString(0));
            modelo.put("lista9", Integer.toString(0));
            modelo.put("lista10", Integer.toString(0));
            modelo.put("lista11", Integer.toString(0));
            modelo.put("lista12", Integer.toString(0));
            modelo.put("lista13", Integer.toString(0));
            modelo.put("lista14", Integer.toString(0));
            modelo.put("v0", Integer.toString(0));
            modelo.put("v1", Integer.toString(0));
            modelo.put("v2", Integer.toString(0));
            modelo.put("v3", Integer.toString(0));
            modelo.put("v4", Integer.toString(0));
            modelo.put("v5", Integer.toString(0));
            modelo.put("v6", Integer.toString(0));
            modelo.put("v7", Integer.toString(0));
            modelo.put("v8", Integer.toString(0));
            modelo.put("v9", Integer.toString(0));
            modelo.put("v10", Integer.toString(0));
            modelo.put("v11", Integer.toString(0));
            modelo.put("v12", Integer.toString(0));
            modelo.put("v13", Integer.toString(0));
            modelo.put("v14", Integer.toString(0));

            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4r);
            modelo.put("listaOdon", listaOdon);
            int edad = Math.round(buscarPaciente.getMes() + buscarPaciente.getEdad() * 12);
            switch (edad) {
                case 0:
                    edad = 0;
                    break;
                case 1:
                    edad = 1;
                    break;
                case 2:
                case 3:
                    edad = 2;
                    break;
                case 4:
                case 5:
                    edad = 4;
                    break;
                case 6:
                case 7:
                case 8:
                    edad = 6;
                    break;
                case 9:
                case 10:
                case 11:
                    edad = 9;
                    break;
                case 12:
                case 13:
                case 14:
                    edad = 12;
                    break;
                case 15:
                case 16:
                case 17:
                    edad = 15;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    edad = 18;
                    break;
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                    edad = 24;
                    break;
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                    edad = 30;
                    break;
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                    edad = 36;
                    break;
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                    edad = 42;
                    break;
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                    edad = 48;
                    break;
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    edad = 54;
                    break;
            }
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));

            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);

            dato.setId_paciente(edad);
            dato.setId_laboratorio(1);
            dato.setTipo("2");   /////getVerDesarrollo2
            List listam1 = this.mi.getVerDesarrollo2(dato);
            modelo.put("listam1", listam1);

            return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
        }

        if ("AIEPII".equals(accion)) {
            accion4 = "AIEPII";
            modelo.put("accion4", accion4);
            modelo.put("id_paciente", id_paciente);
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_persona", id_persona);
            modelo.put("expedido", expedido);
            modelo.put("tipo_medico", tipo_medico);

            Cuadernos dato = new Cuadernos();
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setCod_esta(cliente.getCod_esta());
            dato.setTipo("P");
            List listaAten = this.mi.getVerCuaderno4Paci(dato);
            modelo.put("listaAten", listaAten);

            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            datoc4r.setTipoconsulta("A");
            datoc4r.setId_paciente(Integer.parseInt(id_paciente));
            List listaV = this.mi.getVerVacunasUni(datoc4r);
            if (listaAten.size() == 0) {
                return new ModelAndView("Aviso", "mensaje", "Primeramente debe llenar el Ninio Sano o Enfermo");
            }
            Cuadernos listaaiepi = this.mi.getPacienteCuaderno4A(datoc4r);
            modelo.put("aiepi", listaaiepi);
            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", diass);
            int jj = listaaiepi.getFec_egreso().getDay();
            int jk = listaaiepi.getFec_egreso().getMonth() + 1;
            int jy = listaaiepi.getFec_egreso().getYear() + 1900;
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
        if ("Eliminar".equals(accion)) {
            accion4 = "Ninio Enfermo";
            modelo.put("accion4", accion4);
            String id_cuaderno = request.getParameter("id_cuaderno");
            Cuadernos datox = new Cuadernos();
            datox.setCod_esta(cliente.getCod_esta());
            datox.setId_cuaderno(Integer.parseInt(id_cuaderno));
            datox.setId_historial(Integer.parseInt(id_reservacion));
            this.mi.setEliminarCuaderno4(datox);
        }

        if ("imprimirSimple".equals(accion)) {
            accion4 = "Ninio Enfermo";
            modelo.put("accion4", accion4);
            String id_cuaderno = request.getParameter("id_cuaderno");

            Cuadernos datoc4r = new Cuadernos();
            datoc4r.setCod_esta(cliente.getCod_esta());
            datoc4r.setId_historial(Integer.parseInt(id_reservacion));
            List listaOdon = this.mi.getPacienteCuaderno4(datoc4r);
            modelo.put("listaOdon", listaOdon);
            int edad = Math.round(buscarPaciente.getMes() + buscarPaciente.getEdad() * 12);
            switch (edad) {
                case 0:
                case 1:
                case 2:
                case 3:
                    edad = 3;
                    break;
                case 4:
                case 5:
                case 6:
                    edad = 6;
                    break;
                case 7:
                case 8:
                case 9:
                    edad = 9;
                    break;
                case 10:
                case 11:
                case 12:
                    edad = 12;
                    break;
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    edad = 18;
                    break;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    edad = 24;
                    break;
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                    edad = 36;
                    break;
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                    edad = 36;
                    break;
            }
            Cuadernos dato = new Cuadernos();
            dato.setCod_esta(cliente.getCod_esta());
            dato.setId_paciente(Integer.parseInt(id_paciente));

            List listaAten = this.mi.getVerCuaderno4(dato);
            modelo.put("listaAten", listaAten);

            dato.setId_paciente(edad);
            dato.setId_laboratorio(1);
            List listam1 = this.mi.getVerDesarrollo(dato);
            modelo.put("listam1", listam1);

            dato.setId_historial(Integer.parseInt(id_reservacion));
            dato.setTipo("D");
            List listaAtenDes = this.mi.getDesarrolloSimple(dato);
            modelo.put("listadesarollo", listaAtenDes);
            modelo.put("dato", cliente);

            return new ModelAndView(new VerDesarrolloSimplePDF(), modelo);
        }

        if ("Terminar".equals(accion)) {
            if ("inter".equals(swinter)) {
                return new ModelAndView("administrarhistoriales/PlanAccionPacienteI", modelo);
            } else {
                return new ModelAndView("administrarhistoriales/PlanAccionPaciente", modelo);
            }
        }
        //Sacar los datos de cuaderno 4
        Cuadernos datoc4n = new Cuadernos();
        datoc4n.setCod_esta(cliente.getCod_esta());
        datoc4n.setId_historial(Integer.parseInt(id_reservacion));
        List listaOdon = this.mi.getPacienteCuaderno4(datoc4n);
        modelo.put("listaOdon", listaOdon);

        Cuadernos dato = new Cuadernos();
        dato.setCod_esta(cliente.getCod_esta());
        dato.setId_paciente(Integer.parseInt(id_paciente));
        dato.setTipo("P");
        List listaAten = this.mi.getVerCuaderno4(dato);
        modelo.put("listaAten", listaAten);

        return new ModelAndView("administrarcuadernos/Cuaderno4", modelo);
    }
}
