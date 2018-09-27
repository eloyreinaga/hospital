package org.ayaic.web.administrarecografia;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AtenderPacienteEcoControlador {

    private final MiFacade mi;

    public AtenderPacienteEcoControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/AtenderPacienteEco.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String nombres = request.getParameter("nombres");
        String id_paciente = request.getParameter("id_paciente");
        String id_reservacion = request.getParameter("id_reservacion");
        String id_consultorio = request.getParameter("id_consultorio");
        String id_persona = request.getParameter("id_persona");
        String expedido = request.getParameter("expedido");
        String subjetivo = request.getParameter("subjetivo");
        String objetivo = request.getParameter("objetivo");
        String diagnostico = request.getParameter("diagnostico");
        String miaccion = request.getParameter("miaccion");
        String codigo = request.getParameter("codigo");
        String literal = request.getParameter("literal");
        String boton = request.getParameter("boton");
        String sw = request.getParameter("sw");
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();

        modelo.put("nombres", nombres);

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);

        if ("ecografia".equals(sw)) {
            Historiales auxm = new Historiales();
            auxm.setId_reservacion(Integer.parseInt(id_reservacion));
            auxm.setAccion(ip);
            auxm.setCodigo(host);
            auxm.setExpedido("A");
            auxm.setId_persona(cliente.getId_persona());
            auxm.setCod_esta(cliente.getCod_esta());
            this.mi.setEliminarReserva(auxm);

            Cuadernos datosHistorial = this.mi.getHistorialPacientesEco(Integer.parseInt(id_paciente)); // saca un registro a ser modificado
            if (datosHistorial == null) {
                Historiales aux = new Historiales();
                aux.setId_historial(Integer.parseInt(id_reservacion));
                aux.setEdad(buscarPaciente.getEdad());
                aux.setTalla(0);
                aux.setPeso(0);
                aux.setTemperatura(0);
                aux.setFc("x");
                aux.setPa("x");
                aux.setFr("x");
                aux.setExpedido(expedido);
                aux.setRepetida("N");
                aux.setDiagnostico("Ecografia Externo");
                aux.setId_consultorio(Integer.parseInt(id_consultorio));
                aux.setId_paciente(Integer.parseInt(id_paciente));
                aux.setId_persona(Integer.parseInt(id_persona));
                aux.setObjetivo("Ecografia Externo");
                aux.setId_seguro(buscarPaciente.getId_seguro());
                aux.setCod_esta(cliente.getCod_esta());
                aux.setId_por(cliente.getId_persona());
                ///int iResultado = this.mi.setRegistrarHistorial(aux);  
                // grabar los datos introducidos en la ventana  
                Cuadernos dato = new Cuadernos();
                dato.setId_historial(Integer.parseInt(id_reservacion));
                dato.setId_costo(16);
                dato.setTipoconsulta("Externo");
                dato.setEstado("A");
                dato.setLaboratorio("Ecografia Obstetrica");
                dato.setCod_esta(cliente.getCod_esta());
                dato.setId_laboratorio(16);
                dato.setId_por(cliente.getId_persona());
                this.mi.setCrearLaboratorioC(dato);
            } else {
                id_reservacion = Integer.toString(datosHistorial.getId_historial());
            }
        }

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

        modelo.put("subjetivo", subjetivo);
        modelo.put("objetivo", objetivo);
        modelo.put("diagnostico", diagnostico);
        modelo.put("miaccion", miaccion);
        modelo.put("codigo", codigo);
        modelo.put("literal", literal);

        Cuadernos dato = new Cuadernos();
        dato.setTipo("E");
        //dato.setId_pedido(Integer.parseInt(id_pedido));
        dato.setId_persona(Integer.parseInt(id_persona));
        dato.setCod_esta(cliente.getCod_esta());  ////habia id_reservacion  20/04/2015
        dato.setId_historial(Integer.parseInt(id_reservacion));
        if (cliente.getId_laboratorio() == 0) {
            dato.setId_cargo(0);
            dato.setId_laboratorio(999);
        } else {
            dato.setId_cargo(cliente.getId_laboratorio());
            dato.setId_laboratorio(cliente.getId_laboratorio());
        }
        List listalab = this.mi.getPedidoLab(dato);
        modelo.put("listalab", listalab);

        return new ModelAndView("administrarecografia/LabPendiente", modelo);

    }
}
