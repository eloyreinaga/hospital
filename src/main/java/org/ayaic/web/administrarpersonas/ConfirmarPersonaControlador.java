package org.ayaic.web.administrarpersonas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmarPersonaControlador {

    private final MiFacade mi;

    public ConfirmarPersonaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/ConfirmarPersona.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion = request.getParameter("accion");
        String accion1 = request.getParameter("accion1");
        String swclav = request.getParameter("swclav");
        String swclave = request.getParameter("swclav");
        String id_persona = request.getParameter("id_persona");
        String id_farmacia = request.getParameter("id_farmacia");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_medico = request.getParameter("id_medico");
        String nropac = request.getParameter("nropac");
        String id_piso = request.getParameter("id_piso");
        String dip = request.getParameter("dip");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String id_sexo = request.getParameter("id_sexo");
        String correo = request.getParameter("correo");
        String firma = request.getParameter("firma");
        String diax = request.getParameter("dia");
        String mesx = request.getParameter("mes");
        String aniox = request.getParameter("anio");
        String id_departamentox = request.getParameter("id_departamento");
        String id_estado_civilx = request.getParameter("id_ecivil");
        String id_pais = request.getParameter("id_pais");
        String id_provincia = request.getParameter("id_provincia");
        String id_localidad = request.getParameter("id_localidad");
        String telefono = request.getParameter("telefono");
        String id_estado = request.getParameter("id_estado");
        String id_consultorio = request.getParameter("id_consultorio");
        String celular = request.getParameter("celular");
        String inicial = request.getParameter("inicial");
        String cod_esta1 = request.getParameter("cod_esta");
        String urgencias = request.getParameter("urgencias");
        String matricula = request.getParameter("matricula");
        String codigoprof = request.getParameter("codigoprof");

        Localidades local = new Localidades();
        List Estab1 = this.mi.getListarEsta(local);
        Localidades datoestab = (Localidades) Estab1.get(0);
        modelo.put("codesta", Integer.toString(cliente.getCod_esta()));

        if ("".equals(urgencias) || urgencias == null) {
            urgencias = "0";
        }
        if ("".equals(nropac) || nropac == null) {
            nropac = "0";
        }

        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");

        Personas pai = new Personas();
        if ("Adicionar".equals(accion)) {
            if ("".equals(id_farmacia) || id_farmacia == null) {
                id_farmacia = "0";
            }
            if ("".equals(id_laboratorio) || id_laboratorio == null) {
                id_laboratorio = "0";
            }
            if ("".equals(id_piso) || id_piso == null) {
                id_piso = "0";
            }
            pai.setDip(dip);
            pai.setPaterno(paterno);
            pai.setMaterno(materno);
            pai.setNombres(nombres);
            pai.setDireccion(direccion);
            pai.setCorreo(correo);
            pai.setFirma(firma);
            pai.setTelefono(telefono);
            pai.setId_consultorio(Integer.parseInt(id_consultorio));
            pai.setCelular(celular);
            pai.setCod_esta(Integer.parseInt(cod_esta1));
            pai.setId_farmacia(Integer.parseInt(id_farmacia));
            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            pai.setId_piso(Integer.parseInt(id_piso));
            pai.setId_medico(Integer.parseInt(id_medico));
            pai.setNropac(Integer.parseInt(nropac));
            pai.setUrgencias(Integer.parseInt(urgencias));
            pai.setMatricula(matricula);
            pai.setCadena1(inicial);
            pai.setCodigoprof(codigoprof);

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_estado_civilx)); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);

            //Buscar Cargo
            try {
                Consultorios buscarEtel = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado
                modelo.put("datosCargo", buscarEtel);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamentox)); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            try {
                Provincias buscarProvincia = this.mi.getDatosProvincia(Integer.parseInt(id_provincia));
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            //Buscar Localidad
            try {
                Localidades localidad = new Localidades();
                localidad.setId_localidad(Integer.parseInt(id_localidad));
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            String a = "/";
            String fecha_nacimiento = dia + a + mes + a + anio;
            modelo.put("fec_nacimiento", fecha_nacimiento);

            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));

            modelo.put("datos", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);
        }

        if ("Modificar".equals(accion)) {
            Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));

            if ("".equals(id_farmacia) || id_farmacia == null) {
                id_farmacia = "0";
            }
            if ("".equals(id_medico) || id_medico == null) {
                id_medico = "0";
            }
            if ("".equals(nropac) || nropac == null) {
                nropac = "0";
            }
            if ("".equals(id_laboratorio) || id_laboratorio == null) {
                id_laboratorio = "0";
            }
            if ("".equals(id_piso) || id_piso == null) {
                id_piso = "0";
            }

            pai.setDip(dip);
            pai.setPaterno(paterno);
            pai.setMaterno(materno);
            pai.setNombres(nombres);
            pai.setDireccion(direccion);
            pai.setCorreo(correo);
            pai.setFirma(firma);
            pai.setTelefono(telefono);
            pai.setId_consultorio(Integer.parseInt(id_consultorio));
            pai.setCelular(celular);
            pai.setId_estado(id_estado);
            pai.setId_farmacia(Integer.parseInt(id_farmacia));
            pai.setId_laboratorio(Integer.parseInt(id_laboratorio));
            pai.setId_medico(Integer.parseInt(id_medico));
            pai.setNropac(Integer.parseInt(nropac));
            pai.setId_piso(Integer.parseInt(id_piso));
            pai.setCod_esta(Integer.parseInt(cod_esta1));
            pai.setUrgencias(Integer.parseInt(urgencias));
            pai.setCadena1(inicial);
            pai.setMatricula(matricula);
            pai.setCodigoprof(codigoprof);

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(Integer.parseInt(id_estado_civilx)); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);
            //Buscar Cargo
            try {
                Consultorios buscarEtel = this.mi.getDatosConsultorio(Integer.parseInt(id_consultorio)); // saca un registro a ser modificado
                modelo.put("datosCargo", buscarEtel);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }
            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(Integer.parseInt(id_departamentox)); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            try {
                Provincias buscarProvincia = this.mi.getDatosProvincia(Integer.parseInt(id_provincia));
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            try {
                Localidades localidad = new Localidades();
                localidad.setId_localidad(Integer.parseInt(id_localidad));
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos");
            }

            if ("1".equals(swclave)) {
                modelo.put("cod_esta", Integer.toHexString(buscarEmpleado.getCod_esta()));
                pai.setCod_esta(cliente.getCod_esta());
            }

            String a = "/";
            String fecha_nacimiento = dia + a + mes + a + anio;
            modelo.put("fec_nacimiento", fecha_nacimiento);

            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));

            modelo.put("datos", pai);
            modelo.put("accion", accion);
            modelo.put("accion1", accion1);

        }

        if ("Eliminar".equals(accion)) {

            Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));
            String id_sexo_e = Integer.toString(buscarEmpleado.getId_sexo());          //Sacar id_sexo
            int id_estado_civil_e = buscarEmpleado.getId_ecivil();
            int id_pais_e = buscarEmpleado.getId_pais();          //Sacar id_pais 
            int id_departamento_e = buscarEmpleado.getId_departamento();  //Sacar id_departamento 
            int id_provincia_e = buscarEmpleado.getId_provincia();     //Sacar id_provincia       
            int id_localidad_e = buscarEmpleado.getId_localidad();     //Sacar id_localidad       
            int id_consultorio_e = buscarEmpleado.getId_consultorio();     //Sacar id_consultorio
            Date fecha_nac = buscarEmpleado.getFec_nacimiento();
            int xanio = fecha_nac.getYear() + 1900;
            int xmes = fecha_nac.getMonth() + 1;
            int xdia = fecha_nac.getDate();

            //Buscar Sexo
            Sexos buscarsexo = this.mi.getDatosSexo(Integer.parseInt(id_sexo_e)); // saca un registro a ser modificado
            modelo.put("buscarSexo", buscarsexo);

            //Buscar Estado Civil
            Eciviles buscarecivil = this.mi.getDatosEcivil(id_estado_civil_e); // saca un registro a ser modificado
            modelo.put("buscarEstadoCivil", buscarecivil);

            //Buscar Cargo
            try {
                Consultorios buscarEtel = this.mi.getDatosConsultorio(id_consultorio_e); // saca un registro a ser modificado
                modelo.put("datosCargo", buscarEtel);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "La persona tiene dependencias, consulte con Administrador");
            }

            //Buscar Departamento      
            try {
                Departamentos buscardepartamento = this.mi.getDatosDepartamento(id_departamento_e); // saca un registro a ser modificado
                modelo.put("buscarDepartamento", buscardepartamento);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Departamento");
            }
            try {
                Provincias buscarProvincia = this.mi.getDatosProvincia(id_provincia_e);
                modelo.put("buscarProvincia", buscarProvincia);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Provincia");
            }
            //Buscar Localidad
            try {
                Localidades localidad = new Localidades();
                localidad.setId_localidad(id_localidad_e);
                Localidades buscarLocalidad = this.mi.getDatosLocalidad(localidad);
                modelo.put("buscarLocalidad", buscarLocalidad);
            } catch (Exception e) {
                return new ModelAndView("Aviso", "mensaje", "Faltan datos Localidad");
            }

            String a = "/";

            String fecha_nacimiento = Integer.toString(xdia) + a + Integer.toString(xmes) + a + Integer.toString(xanio);
            modelo.put("fec_nacimiento", fecha_nacimiento);

            modelo.put("anio", request.getParameter("anio"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("dia", request.getParameter("dia"));

            modelo.put("datos", buscarEmpleado);
            modelo.put("id_persona", id_persona);
            modelo.put("accion", accion);
        }
        modelo.put("id_persona", id_persona);
        modelo.put("swclav", swclav);
        modelo.put("swclave", swclave);

        return new ModelAndView("administrarpersonas/ConfirmarPersona", modelo);

    }
}
