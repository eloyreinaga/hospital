package org.ayaic.web.administrarenfermeria;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CobrarPacienteEControlador {

    private final MiFacade mi;

    public CobrarPacienteEControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/CobrarPacienteEnfermeria.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String sw = request.getParameter("sw");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String id_rubro = request.getParameter("id_rubro");
        String id_costo = request.getParameter("id_costo");
        String num_cladoc = request.getParameter("num_cladoc");
        String observa = request.getParameter("observa");
        String id_pedido = request.getParameter("id_pedido");
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};

        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        Costos datoq = new Costos();
        datoq.setId_rubro(Integer.parseInt(id_rubro));
        datoq.setId_prestacion(Integer.parseInt(id_rubro));
        datoq.setId_estado("%");
        datoq.setTipo(0);
        datoq.setId_persona(5000);
        datoq.setEmergencias(0);
        datoq.setCod_esta(cliente.getCod_esta());///para todos 20-02-2017    
        Pacientes paciente1 = new Pacientes();
        paciente1.setCod_esta(cliente.getCod_esta());
        if ("Nuevo".equals(accion)) {
            num_cladoc = Long.toString(this.mi.getNumClaDoc(paciente1));
        }

        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);
        if (request.getParameter("id_rubro") != null) {

            List listarCostos = this.mi.getListarCostos(datoq);
            modelo.put("listarCostos", listarCostos);
            if (request.getParameter("id_costo") != null) {
                Costos costo = new Costos();
                costo.setId_costo(Integer.parseInt(id_costo));
                costo.setCod_esta(cliente.getCod_esta());
                Costos buscarCosto = this.mi.getDatosCosto(costo);
                if (buscarCosto != null) {
                    modelo.put("precio", Double.toString(buscarCosto.getCosto_unit()));
                } else {
                    modelo.put("precio", "0");
                }
            }
        }

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        String id_sexo_e = Integer.toString(buscarPaciente.getId_tipo_sexo());          //Sacar id_sexo

        Date fecha_nac = buscarPaciente.getFec_nacimiento();
        int xanio = fecha_nac.getYear() + 1900;
        int xmes = fecha_nac.getMonth() + 1;
        int xdia = fecha_nac.getDate();

        if (xanio < 1901) {
            return new ModelAndView("Aviso", "mensaje", "El año de nacimiento no es correcto");
        }

        //Buscar Sexo
        Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
        modelo.put("buscarSexo", buscarsexo);

        String a = "/";
        String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
        modelo.put("fec_nacimiento", fecha_nacimiento);

        modelo.put("accion", accion);
        modelo.put("sw", request.getParameter("sw"));
        modelo.put("id_paciente", id_paciente);
        modelo.put("id_reservacion", id_reservacion);
        modelo.put("id_consultorio", id_consultorio);
        modelo.put("id_persona", id_persona);
        modelo.put("id_rubro", id_rubro);
        modelo.put("id_costo", id_costo);
        modelo.put("num_cladoc", num_cladoc);
        modelo.put("id_pedido", id_pedido);

        if ("Terminar".equals(accion)) {
            // almacenar el monto de cobro
            String costo_unit = request.getParameter("precio");
            String nombres = request.getParameter("nombres");
            if ("3".equals(sw)) {
                //Pacientes paciente1= new Pacientes() ;
                paciente1.setId_pedido(Integer.parseInt(id_pedido));
                paciente1.setCod_esta(cliente.getCod_esta());
                Pacientes paciente = this.mi.getDatosPedido(paciente1); //////////////   
                paciente.setId_estado("C");
                paciente.setId_persona(Integer.parseInt(id_persona));
                this.mi.setModificarPedido(paciente);
            } else {
                Pacientes dato = new Pacientes();
                dato.setNombres(nombres);
                dato.setPrecio_total(Float.parseFloat(costo_unit));
                dato.setId_paciente(0);
                dato.setId_estado("A");
                dato.setNum_cladoc(num_cladoc);
                dato.setNit("0");
                //dato.setId_costo(Integer.parseInt(id_costo)) ;
                dato.setId_rubro(Integer.parseInt(id_rubro));
                dato.setId_persona(cliente.getId_persona());
                dato.setId_dispensa(cliente.getId_persona());
                dato.setId_paciente(Integer.parseInt(id_paciente));
                //dato.setId_carpeta(Integer.parseInt(id_reservacion)) ;
                dato.setId_factura(0);/////0 sin factura, 1 con factura, 2, 3, 4, para num correlativo, entradas, ajuste+ y ajuste-
                dato.setTipo("C");
                dato.setFec_registro(fecha1);
                dato.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearPeedido(dato);

                id_pedido = Integer.toString(this.mi.getNumPedido(dato));
                Detalle detalle = new Detalle();
                detalle.setId_costo(Integer.parseInt(id_costo));
                detalle.setId_pedido(Integer.parseInt(id_pedido));
                detalle.setEntrada(Float.parseFloat(costo_unit));
                detalle.setSalida(Float.parseFloat(costo_unit));
                detalle.setId_rubro(Integer.parseInt(id_rubro));
                detalle.setIndicacion(observa.trim());
                detalle.setUlt_usuario(Integer.parseInt(id_persona));
                detalle.setCod_esta(cliente.getCod_esta());
                this.mi.setCrearDetalle(detalle);

                if ("2".equals(sw)) {
                    Historiales datohi = new Historiales();
                    datohi.setId_reservacion(Integer.parseInt(id_reservacion));
                    datohi.setCod_esta(cliente.getCod_esta());
                    datohi.setId_estado("C");
                    this.mi.setModificarPagoReserva(datohi);
                }

            }
            // pacientes a cobrar enviados por odontologia
            Pacientes paciente = new Pacientes();
            paciente.setId_estado("A");
            paciente.setId_rubro(5);
            paciente.setId_localidad(cliente.getCod_esta());
            paciente.setCod_esta(cliente.getCod_esta());
            List listarCo = this.mi.getListarCobroRubroEnfer(paciente);
            modelo.put("listaEnfer", listarCo);

            paciente.setId_persona(cliente.getId_persona());
            List listarpedenf = this.mi.getListarCobroEnfer(paciente);
            modelo.put("listacobenfer", listarpedenf);

            Cuadernos cua6 = new Cuadernos();
            cua6.setId_persona(cliente.getId_persona());
            cua6.setCod_esta(cliente.getCod_esta());
            List listarAtendidos = this.mi.getListaPacientesCuaderno6(cua6);
            modelo.put("listarAtendidos", listarAtendidos);

            return new ModelAndView("administrarenfermeria/ListarCobroReserva", modelo);
        }
        return new ModelAndView("administrarenfermeria/CobrarPaciente", modelo);

    }
}
