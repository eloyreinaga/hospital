package org.ayaic.web.administrarcobranza;

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
import org.ayaic.domain.Usuarios;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarCobroReservaControlador {

    private final MiFacade mi;

    public ListarCobroReservaControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarCobroReserva.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String boton = request.getParameter("boton");
        String _id_usuario = Integer.toString(cliente.getId_usuario());
        Usuarios usuario = this.mi.getDatosUsuario(cliente.getId_usuario()); // saca un registro a ser modificado
        Personas persona = this.mi.getBuscarPersona(usuario.getId_persona()); // saca un registro a ser modificado
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Date fecha1 = new Date();
        int anioq = fecha1.getYear() + 1900;
        String[] anios = {Integer.toString(anioq), Integer.toString(anioq - 1), Integer.toString(anioq - 2), Integer.toString(anioq - 3), Integer.toString(anioq - 4), Integer.toString(anioq - 5), Integer.toString(anioq - 6), Integer.toString(anioq - 7), Integer.toString(anioq - 8), Integer.toString(anioq - 9), Integer.toString(anioq - 10), Integer.toString(anioq - 11), Integer.toString(anioq - 12), Integer.toString(anioq - 13), Integer.toString(anioq - 14), Integer.toString(anioq - 15), Integer.toString(anioq - 16), Integer.toString(anioq - 17), Integer.toString(anioq - 18), Integer.toString(anioq - 19), Integer.toString(anioq - 20), Integer.toString(anioq - 21), Integer.toString(anioq - 22), Integer.toString(anioq - 23), Integer.toString(anioq - 24), Integer.toString(anioq - 25), Integer.toString(anioq - 26), Integer.toString(anioq - 27), Integer.toString(anioq - 28), Integer.toString(anioq - 29), Integer.toString(anioq - 30), Integer.toString(anioq - 31), Integer.toString(anioq - 32), Integer.toString(anioq - 33), Integer.toString(anioq - 34), Integer.toString(anioq - 35), Integer.toString(anioq - 36), Integer.toString(anioq - 37), Integer.toString(anioq - 38), Integer.toString(anioq - 39), Integer.toString(anioq - 40), Integer.toString(anioq - 41), Integer.toString(anioq - 42), Integer.toString(anioq - 43), Integer.toString(anioq - 44), Integer.toString(anioq - 45), Integer.toString(anioq - 46), Integer.toString(anioq - 47), Integer.toString(anioq - 48), Integer.toString(anioq - 49), Integer.toString(anioq - 50), Integer.toString(anioq - 51), Integer.toString(anioq - 52), Integer.toString(anioq - 53), Integer.toString(anioq - 54), Integer.toString(anioq - 55), Integer.toString(anioq - 56), Integer.toString(anioq - 57), Integer.toString(anioq - 58), Integer.toString(anioq - 59), Integer.toString(anioq - 60), Integer.toString(anioq - 61), Integer.toString(anioq - 62), Integer.toString(anioq - 63), Integer.toString(anioq - 64), Integer.toString(anioq - 65), Integer.toString(anioq - 66), Integer.toString(anioq - 67), Integer.toString(anioq - 68), Integer.toString(anioq - 69), Integer.toString(anioq - 70), Integer.toString(anioq - 71), Integer.toString(anioq - 72), Integer.toString(anioq - 73), Integer.toString(anioq - 74), Integer.toString(anioq - 75), Integer.toString(anioq - 76), Integer.toString(anioq - 77), Integer.toString(anioq - 78), Integer.toString(anioq - 79), Integer.toString(anioq - 80), Integer.toString(anioq - 81), Integer.toString(anioq - 82), Integer.toString(anioq - 83), Integer.toString(anioq - 84), Integer.toString(anioq - 85), Integer.toString(anioq - 86), Integer.toString(anioq - 87), Integer.toString(anioq - 88), Integer.toString(anioq - 89), Integer.toString(anioq - 90), Integer.toString(anioq - 91), Integer.toString(anioq - 92), Integer.toString(anioq - 93), Integer.toString(anioq - 94), Integer.toString(anioq - 95), Integer.toString(anioq - 96), Integer.toString(anioq - 97), Integer.toString(anioq - 98), Integer.toString(anioq - 99), Integer.toString(anioq - 100), Integer.toString(anioq - 101), Integer.toString(anioq - 102), Integer.toString(anioq - 103), Integer.toString(anioq - 104), Integer.toString(anioq - 105), Integer.toString(anioq - 106), Integer.toString(anioq - 107), Integer.toString(anioq - 108), Integer.toString(anioq - 109), Integer.toString(anioq - 110)};
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("anio", Integer.toString(fecha1.getYear() + 1900));
        modelo.put("mes", Integer.toString(fecha1.getMonth() + 1));
        modelo.put("dia", Integer.toString(fecha1.getDate()));
        modelo.put("anios", anios);
        modelo.put("meses", meses);
        modelo.put("dias", dias);

        if ("EliminarReservaEnf".equals(accion1)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_pedido = request.getParameter("id_pedido");

            Pacientes dato = new Pacientes();
            dato.setId_pedido(Integer.parseInt(id_pedido));
            dato.setId_dispensa(cliente.getId_persona());
            dato.setCod_esta(cliente.getCod_esta());
            dato.setCadena1(ip);
            dato.setCadena2(host);
            try {
                this.mi.setEliminarPedido(dato);
                return new ModelAndView("Aviso", "mensaje", "Se elimino pedido correctamente");
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "No se puede eliminar pedido Enfermeria, Consulte Administrador");
            }

        }

        // pacientes a cobrar nuevos
        if ("BuscarN".equals(boton)) {
            String nombres = request.getParameter("nombre");
            String nombre = request.getParameter("nombre");
            String hcl = request.getParameter("nombre");

            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombre = nombres.replaceAll("\\s", "%");
            nombre = "%" + nombre.toUpperCase() + "%";
            nombres = nombres.replaceAll("\\s", ":*&");
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            //nombres = "%" + nombres + "%";
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setNombre(nombre);
            paciente.setId_estado("%");
            try {
                Integer.parseInt(hcl);
                paciente.setHcl(Integer.parseInt(hcl));
                List listarPa2 = this.mi.getListarPacientesHC(paciente);
                modelo.put("listaPacientes", listarPa2);
            } catch (Exception e) {
                //return new ModelAndView("Aviso","mensaje", "La datos");   
                List listarPa = this.mi.getListarPacientes(paciente);
                modelo.put("listaPacientes", listarPa);

            }
        }

        if ("BuscarH".equals(boton)) {
            String hcl = request.getParameter("hcl");
            if ("".equals(hcl)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar un numero de Historia");
            }
            Pacientes paciente = new Pacientes();
            paciente.setHcl(Integer.parseInt(hcl));

            List listarPa = this.mi.getListarPacientesHC(paciente);
            modelo.put("listaPacientes", listarPa);
        }

        if ("BuscarF".equals(boton)) {
            String aux_dia = request.getParameter("dia");
            String aux_mes = request.getParameter("mes");
            String aux_anio = request.getParameter("anio");
            if ("".equals(aux_dia) || "".equals(aux_mes) || "".equals(aux_anio)) {
                return new ModelAndView("Aviso", "mensaje", "Debe colocar alguna fecha");
            }
            if ((aux_dia == null) || (aux_mes == null) || (aux_anio == null)) {
                return new ModelAndView("administrarpacientes/ListarPacientes", modelo);
            } else {
                int dia = Integer.parseInt(aux_dia);
                int mes = Integer.parseInt(aux_mes) - 1;
                int anio = Integer.parseInt(aux_anio) - 1900;

                Date fecha = new Date(anio, mes, dia);

                Pacientes paciente = new Pacientes();
                paciente.setFec_nacimiento(fecha);
                List listaDePacientes = this.mi.getListarPacientesFN(paciente);
                modelo.put("listaPacientes", listaDePacientes);
            }
        }

        // Lista de todos lo rubros
        List listarRubros = this.mi.getListaRubro();
        modelo.put("listarRubros", listarRubros);

        // pacientes a cobrar enviados por recepcion
        Historiales elimina = new Historiales();
        elimina.setId_estado("P");
        if ("F".equals(datoestab.getArea())) {  // para HGSJDD no lo borrara de la tabla reservaciones hasta que no pague
            //elimina.setId_estado("P");
            elimina.setCod_esta(cliente.getCod_esta());
            List listarPaises = this.mi.getListarCobroReserva(elimina);
            modelo.put("milista", listarPaises);
        } else {
            elimina.setCod_esta(cliente.getCod_esta());
            List listarPaises = this.mi.getListarCobroReserva(elimina);
            modelo.put("milista", listarPaises);
        }

        // pacientes a cobrar enviados por enfermeria
        Pacientes paciente = new Pacientes();
        paciente.setId_estado("A");
        paciente.setId_rubro(5);
        paciente.setId_localidad(cliente.getCod_esta());
        List listarenfer = this.mi.getListarCobroRubro(paciente);
        modelo.put("listaEnfer", listarenfer);

        // pacientes a cobrar enviados por odontologia
        paciente.setId_rubro(3);
        List listarO = this.mi.getListarCobroRubro(paciente);
        modelo.put("listaOdon", listarO);

        Cuadernos datol = new Cuadernos();
        datol.setTipoconsulta("A");
        datol.setCod_esta(cliente.getCod_esta());////19-12-2016
        datol.setSeleccion(cliente.getId_almacen());
        if (cliente.getId_laboratorio() > 0) {
            datol.setId_cargo(cliente.getId_laboratorio());   ////12-06-2015
            datol.setId_laboratorio(cliente.getId_laboratorio());   ////12-06-2015 
        } else {
            datol.setId_cargo(0);   ////12-06-2015
            datol.setId_laboratorio(999);   ////12-06-2015 
        }
        List listalab = this.mi.getLabPacPendientePago(datol);
        modelo.put("listalab", listalab);

        if (listarenfer.size() > 0) {
            modelo.put("listaenfermeria", "1");
        }
        if (listarO.size() > 0) {
            modelo.put("listaodonto", "1");
        }
        if (listalab.size() > 0) {
            modelo.put("listalabo", "1");
        }
        modelo.put("id_persona", Integer.toString(usuario.getId_persona()));
        modelo.put("id_consultorio", Integer.toString(persona.getId_consultorio()));

        return new ModelAndView("administrarcobranza/ListarCobroReserva", modelo);

    }
}
