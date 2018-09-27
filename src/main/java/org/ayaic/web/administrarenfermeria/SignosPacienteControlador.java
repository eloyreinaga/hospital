package org.ayaic.web.administrarenfermeria;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
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
public class SignosPacienteControlador {

    private final MiFacade mi;

    public SignosPacienteControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/SignosPaciente.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");

        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] horas = {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String[] minutos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        Historiales datohi = new Historiales();
        datohi.setId_historial(Integer.parseInt(id_reservacion));
        datohi.setAccion("R");
        datohi.setCod_esta(cliente.getCod_esta());
        Historiales datosHistorial = this.mi.getDatosReserva(datohi);   //getDatosReserva 

        if (datosHistorial == null) {
            datosHistorial = new Historiales();
            datosHistorial.setEdad(buscarPaciente.getEdad());
            datosHistorial.setTalla(0);
            datosHistorial.setPeso(0);
            datosHistorial.setTemperatura(0);
            datosHistorial.setFc("0");
            datosHistorial.setPa("0");
            datosHistorial.setFr("0");
            datosHistorial.setSo2("0");
            datosHistorial.setGlicemia("0");
        }

        modelo.put("datoshisto", datosHistorial);
        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        modelo.put("fec_nacimiento", fecha_nacimiento);

        modelo.put("accion", accion);
        modelo.put("sw1", request.getParameter("sw1"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("expedido", expedido);

        if ("Repetir Ultima Talla".equals(accion)) {
            //Historiales datohi= new Historiales() ;
            datohi.setId_historial(Integer.parseInt(id_paciente));
            datohi.setAccion("U"); ///
            datohi.setCod_esta(cliente.getCod_esta());
            Historiales datosHistoUlt = this.mi.getDatosHistorialUlt(datohi);
            if (datosHistoUlt != null) {

                //Historiales datohi= new Historiales() ;
                datohi.setId_historial(datosHistoUlt.getId_historial());
                datohi.setAccion("H"); ///
                datohi.setCod_esta(cliente.getCod_esta());
                Historiales datosH = this.mi.getDatosHistorial(datohi);

                Historiales daton = new Historiales();
                daton.setId_historial(Integer.parseInt(id_reservacion));
                //daton.setId_persona(Integer.parseInt(id_persona));
                daton.setId_paciente(Integer.parseInt(id_paciente));
                daton.setEdad(buscarPaciente.getEdad());
                //daton.setId_consultorio(Integer.parseInt(id_consultorio));   
                daton.setTalla(datosH.getTalla());
                daton.setPeso(datosH.getPeso());
                daton.setTemperatura(datosH.getTemperatura());
                daton.setExpedido(expedido);
                daton.setFc(datosH.getFc());
                daton.setPa(datosH.getPa());
                daton.setFr(datosH.getFr());
                daton.setSo2(datosH.getSo2());
                daton.setGlicemia(datosH.getGlicemia());
                daton.setCod_esta(cliente.getCod_esta());
                this.mi.setModificarSignosReserva(daton);
                datohi.setAccion("R");   //getDatosReserva 
                datohi.setCod_esta(cliente.getCod_esta());
                datohi.setId_historial(Integer.parseInt(id_reservacion));
                Historiales datosReser = this.mi.getDatosHistorial(datohi);  //getDatosReserva 
                if (datosReser == null) {
                    Historiales datohis = new Historiales();
                    datohis.setId_historial(Integer.parseInt(id_reservacion));
                    datohis.setCod_esta(cliente.getCod_esta());
                    Historiales datosHisto1 = this.mi.getDatosReserva(datohis);

                    modelo.put("datoshisto", datosHisto1);
                } else {
                    Historiales datosReserva2 = this.mi.getDatosHistorial(datohi);
                    modelo.put("datoshisto", datosReserva2);
                }
            } else {
                Historiales datosReserva2 = this.mi.getDatosHistorial(datohi);
                modelo.put("datoshisto", datosReserva2);
            }
        }

        if ("Cambiar Consultorio".equals(accion)) {
            String sw = request.getParameter("sw");
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");

            Historiales dato = new Historiales();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            //dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setEdad(buscarPaciente.getEdad());
            //dato.setId_consultorio(Integer.parseInt(id_consultorio));   
            dato.setTalla(Double.parseDouble(talla));
            dato.setPeso(Double.parseDouble(peso));
            dato.setTemperatura(Double.parseDouble(temperatura));
            dato.setExpedido(expedido);
            dato.setFc(fc);
            dato.setPa(pa);
            dato.setFr(fr);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarSignosReserva(dato);
            modelo.put("datos", buscarPaciente);

            if (buscarPaciente.getDia() < 0) {
                return new ModelAndView("Aviso", "mensaje", "La fecha de Nacimiento no es correcta");
            }

            Date fecha_reg = buscarPaciente.getFec_registro();
            int xanio_r = fecha_reg.getYear() + 1900;
            int xmes_r = fecha_reg.getMonth() + 1;
            int xdia_r = fecha_reg.getDate();

            String fecha_registro = Integer.toString(xdia_r) + a + Integer.toString(xmes_r) + a + Integer.toString(xanio_r);
            modelo.put("fec_registro", fecha_registro);
            modelo.put("anio_r", Integer.toString(xanio_r));
            modelo.put("mes_r", Integer.toString(xmes_r));
            modelo.put("dia_r", Integer.toString(xdia_r));

            //lista de consultorios
            Consultorios aa = new Consultorios();
            aa.setCod_esta(cliente.getCod_esta());
            List listarCargos = this.mi.getListarConsultorios(aa);

            modelo.put("listarCargos", listarCargos);

            if (id_consultorio != null) {
                Personas persona = new Personas();
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCod_esta(cliente.getCod_esta());
                List buscarEmpleado = this.mi.getDatosPersonaConsul(persona);
                modelo.put("listaPersonas", buscarEmpleado);
            }

            modelo.put("anios", anios);
            modelo.put("meses", meses);
            modelo.put("dias", dias);
            modelo.put("horas", horas);
            modelo.put("minutos", minutos);
            modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("dia", Integer.toString(fecha1.getDate()));
            modelo.put("hora", Integer.toString(fecha1.getHours()));
            modelo.put("minuto", Integer.toString(fecha1.getMinutes()));
            modelo.put("anioe", Integer.toString(fecha1.getYear() + 1900));
            modelo.put("mese", Integer.toString(fecha1.getMonth() + 1));
            modelo.put("diae", Integer.toString(fecha1.getDate()));
            modelo.put("horae", Integer.toString(fecha1.getHours()));
            modelo.put("minutoe", Integer.toString(fecha1.getMinutes()));

            modelo.put("accion", "Cambiar");
            modelo.put("sw1", request.getParameter("sw1"));
            modelo.put("id_reservacion", id_reservacion);
            modelo.put("id_consultorio", id_consultorio);
            modelo.put("id_paciente", id_paciente);

            return new ModelAndView("administrarpacientes/ConfirmarPaciente", modelo);
        }

        if ("Terminar".equals(accion)) {
            String talla = request.getParameter("talla");
            String peso = request.getParameter("peso");
            String temperatura = request.getParameter("temperatura");
            String fc = request.getParameter("fc");
            String pa = request.getParameter("pa");
            String fr = request.getParameter("fr");
            String so2 = request.getParameter("so2");
            String glicemia = request.getParameter("glicemia");

            try {
                if (Float.parseFloat(talla) != 0 || Float.parseFloat(peso) != 0) {
                    if (Float.parseFloat(peso) > 200 && Float.parseFloat(peso) != 0) {
                        return new ModelAndView("Aviso", "mensaje", "El Peso debe estar en Kilogramos");
                    }
                    if (Float.parseFloat(talla) > 300) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                    }
                    if (Float.parseFloat(peso) * 1 != Float.parseFloat(peso)) {
                        return new ModelAndView("Aviso", "mensaje", "El Peso no es un numero valido");
                    }
                    if (Float.parseFloat(talla) * 1 != Float.parseFloat(talla)) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla no es un numero valido");
                    }
                    if (Float.parseFloat(talla) * 1 < 4) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla debe estar en centimetros");
                    }
                    if (Float.parseFloat(talla) < Float.parseFloat(peso)) {
                        return new ModelAndView("Aviso", "mensaje", "La Talla y Peso estan invertidos");
                    }
                }
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Los datos de peso y talla no son correctos");
            }

            Historiales dato = new Historiales();
            dato.setId_historial(Integer.parseInt(id_reservacion));
            //dato.setId_persona(Integer.parseInt(id_persona));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            dato.setEdad(buscarPaciente.getEdad());
            //dato.setId_consultorio(Integer.parseInt(id_consultorio));   
            dato.setTalla(Double.parseDouble(talla));
            dato.setPeso(Double.parseDouble(peso));
            dato.setTemperatura(Double.parseDouble(temperatura));
            dato.setExpedido(expedido);
            dato.setFc(fc);
            dato.setPa(pa);
            dato.setFr(fr);
            dato.setSo2(so2);
            dato.setGlicemia(glicemia);
            dato.setCod_esta(cliente.getCod_esta());
            this.mi.setModificarSignosReserva(dato);
            dato.setId_reservacion(Integer.parseInt(id_reservacion));
            datohi.setCod_esta(cliente.getCod_esta());
            dato.setId_estado("C");
            this.mi.setModificarPagoReserva(dato);

            List listarres = this.mi.getListarCobroReservaSignos(dato);
            modelo.put("milistasig", listarres);

            return new ModelAndView("administrarenfermeria/ListarSignosReserva", modelo);
        }
        return new ModelAndView("administrarenfermeria/SignosPaciente", modelo);

    }
}
