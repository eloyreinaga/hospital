package org.ayaic.web.administrarcarpetas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Parentescos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListarPacientesDControlador {

    private final MiFacade mi;

    public ListarPacientesDControlador(MiFacade mi) {
        this.mi = mi;
    }

    @RequestMapping("/ListarPacientesD.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String boton = request.getParameter("boton");
        String accion = request.getParameter("accion");
        String id_pacientej = request.getParameter("id_pacientej");
        String id_carpeta = request.getParameter("id_carpeta");
        modelo.put("dato", cliente);
        // averigua quien es el jefe
        Carpetas bCarpeta = this.mi.getDatosCarpeta(Integer.parseInt(id_carpeta)); // saca un registro a ser modificado
        modelo.put("Carpeta", bCarpeta);
        id_pacientej = Integer.toString(bCarpeta.getId_paciente());

        Pacientes buscarPaciente = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
        modelo.put("datos", buscarPaciente);
        //datos de los dependientes
        List listarP = this.mi.getListarPacientesD(Integer.parseInt(id_carpeta));
        modelo.put("listaPacientesD", listarP);
        //datos del jefe
        modelo.put("id_pacientej", id_pacientej);
        modelo.put("id_carpeta", id_carpeta);

        if ("Buscar".equals(boton)) {
            String nombres = request.getParameter("empresa");
            String id_paciente = request.getParameter("id_paciente");
            String parentesco = request.getParameter("parentesco");
            nombres = nombres.replaceAll("\\s", "%");
            nombres = "%" + nombres + "%";
            Empresas empresa = new Empresas();
            empresa.setNombres(nombres);
            ///empresa.setId_estado("%");
            empresa.setId_estado("A");
            List listarEmp = this.mi.getListarEmpresa2(empresa);
            modelo.put("listaEmp", listarEmp);
            modelo.put("id_carpeta", id_carpeta);
            Empresas empresa1 = new Empresas();
            ///empresa.setId_estado("%");
            empresa.setId_estado("A");
            empresa1.setTipo("L");
            empresa1.setId_carpeta(Integer.parseInt(id_carpeta));
            List listarEmp1 = this.mi.getListarEmpresa3(empresa1);
            modelo.put("listaEmpresa", listarEmp1);

            return new ModelAndView("administrarcarpetas/ListarPacientesEmpresa", modelo);
        }

        if ("EliminarEmpresa".equals(accion)) {
            String id_empresa = request.getParameter("id_empresa");
            String parentesco = request.getParameter("parentesco");
            modelo.put("id_carpeta", id_carpeta);
            Empresas empresa1 = new Empresas();
            ///empresa.setId_estado("%");
            empresa1.setId_estado("A");
            empresa1.setTipo("L");
            empresa1.setId_carpeta(Integer.parseInt(id_carpeta));
            Pacientes creasumi = this.mi.getDatosPaciente(buscarPaciente.getId_paciente()); // saca un registro a ser modificado
            creasumi.setUlt_usuario(cliente.getId_usuario());
            creasumi.setId_carpeta(Integer.parseInt(id_carpeta));
            creasumi.setId_estado("E");
            this.mi.setModificarPacienteSumiEmpresa(creasumi);
            List listarEmp1 = this.mi.getListarEmpresa3(empresa1);
            modelo.put("listaEmpresa", listarEmp1);
            return new ModelAndView("administrarcarpetas/ListarPacientesEmpresa", modelo);
        }

        if ("AgregarEmpresa".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String id_empresa = request.getParameter("id_empresa");

            Empresas empresa = new Empresas();
            ///empresa.setId_estado("%");
            empresa.setId_estado("A");
            empresa.setTipo("L");
            empresa.setId_carpeta(Integer.parseInt(id_carpeta));
            Carpetas paciente = new Carpetas();
            paciente.setId_estado("F");
            paciente.setId_carpeta(Integer.parseInt(id_carpeta));
            List listarEmp2 = this.mi.getListarEmpresa3(empresa);
            if (listarEmp2.size() == 0) {
                Pacientes creasumi = this.mi.getDatosPaciente(buscarPaciente.getId_paciente()); // saca un registro a ser modificado
                creasumi.setUlt_usuario(cliente.getId_usuario());
                creasumi.setId_carpeta(Integer.parseInt(id_carpeta));
                creasumi.setId_estado("E");
                creasumi.setId_empresa(Integer.parseInt(id_empresa));
                this.mi.setModificarPacienteSumiEmpresa(creasumi);
                List listarCarpetas = this.mi.getListarCarpetas(paciente);
                for (int i = 0; i < listarCarpetas.size(); i++) {
                    Carpetas carpe = (Carpetas) listarCarpetas.get(i);
                    Pacientes carpeta = this.mi.getDatosPaciente(carpe.getId_paciente());
                    carpeta.setUlt_usuario(cliente.getId_usuario());
                    carpeta.setRegistro(carpe.getId_estado());
                    carpeta.setTipo("1");
                    carpeta.setId_estado("P");
                    this.mi.setModificarPaciente(carpeta);
                    carpeta.setId_carpeta(Integer.parseInt(id_carpeta));
                    carpeta.setId_empresa(Integer.parseInt(id_empresa));
                    carpeta.setCod_esta(cliente.getCod_esta());
                    this.mi.setCrearPacienteSumi(carpeta);
                }
                List listarEmp = this.mi.getListarEmpresa2(empresa);
                modelo.put("listaEmpresa", listarEmp);
                modelo.put("id_carpeta", id_carpeta);
            } else {
                return new ModelAndView("Aviso", "mensaje", "Solo puede estar afiliado a una empresa");
            }
            return new ModelAndView("administrarcarpetas/ListarPacientesEmpresa", modelo);
        }

        if ("ImprimirCarpeta".equals(accion)) {
            Pacientes buscarPaciente2 = this.mi.getDatosPaciente(Integer.parseInt(id_pacientej)); // saca un registro a ser modificado
            modelo.put("responsable", buscarPaciente2);
            Localidades localidad = new Localidades();
            localidad.setId_localidad(buscarPaciente.getId_localidad());
            Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
            modelo.put("localidad", buscarLocalidad);
            Parentescos buscarParentesco = this.mi.getDatosParentesco(buscarPaciente.getId_tipo_parentesco());
            modelo.put("parentesco", buscarParentesco);

            return new ModelAndView(new ListaCarpetaPDF(), modelo);
        }

        if ("Imprimir".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            Pacientes datojef = mi.getDatosPaciente(Integer.parseInt(id_pacientej));
            modelo.put("datojef", datojef);
            Pacientes datodep = mi.getDatosPaciente(Integer.parseInt(id_paciente));
            modelo.put("datodep", datodep);
            modelo.put("dato", cliente);
            return new ModelAndView(new ListarHclPDF(), modelo);
        }

        if ("Afiliar".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String parentesco = request.getParameter("parentesco");
            String hcl = request.getParameter("hcl");

            Empresas empresa = new Empresas();
            ///empresa.setId_estado("%");
            empresa.setId_estado("A");
            empresa.setTipo("L");
            empresa.setId_carpeta(Integer.parseInt(id_carpeta));
            List listarEmp = this.mi.getListarEmpresa3(empresa);
            modelo.put("listaEmpresa", listarEmp);
            return new ModelAndView("administrarcarpetas/ListarPacientesEmpresa", modelo);
        }

        if ("Eliminar".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            Carpetas dato = new Carpetas();

            dato.setId_carpeta(Integer.parseInt(id_carpeta));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            // quitamos al dependiente
            if (dato.getId_paciente() != bCarpeta.getId_paciente()) {
                this.mi.setEliminarPacienteD(dato);
            }
        }

        if ("Agregar".equals(accion)) {
            String id_paciente = request.getParameter("id_paciente");
            String parentesco = request.getParameter("parentesco");
            Carpetas dato = new Carpetas();
            dato.setId_tipo_parentesco(Integer.parseInt(parentesco));
            dato.setId_carpeta(Integer.parseInt(id_carpeta));
            dato.setId_paciente(Integer.parseInt(id_paciente));
            this.mi.setCrearPacienteD(dato);
        }

        if ("BuscarN".equals(boton)) {

            String nombres = request.getParameter("nombre");
            nombres = nombres.trim();
            nombres = nombres.replaceAll(" +", " ");///quita espacios en blanco entre cadenas
            nombres = nombres.replaceAll("\\s", ":*&");///:*&
            nombres = nombres.replaceAll("ñ", "n");
            nombres = nombres.replaceAll("Ñ", "N");
            if (nombres.length() > 0) {
                nombres = nombres + ":*";
            }
            Pacientes paciente = new Pacientes();
            paciente.setNombres(nombres);
            paciente.setId_estado("%");
            List listarPaises = this.mi.getListarPacientes(paciente);
            modelo.put("listaPacientes", listarPaises);
            List listarParentescos = this.mi.getListarParentescos();
            modelo.put("listarParentescos", listarParentescos);
        }
        if ("BuscarH".equals(boton)) {

            String hcl = request.getParameter("hcl");
            Pacientes paciente = new Pacientes();
            paciente.setHcl(Integer.parseInt(hcl));

            List listarPaises = this.mi.getListarPacientesHC(paciente);
            modelo.put("listaPacientes", listarPaises);
            List listarParentescos = this.mi.getListarParentescos();
            modelo.put("listarParentescos", listarParentescos);
        }

        return new ModelAndView("administrarcarpetas/ListarPacientesD", modelo);

    }
}
