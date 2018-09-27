package org.ayaic.web.administrarpersonas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Salas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NuevoPersonaControlador {

    private final MiFacade mi;

    public NuevoPersonaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/NuevoPersona.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String swclav = request.getParameter("swclav");
        String swclave = request.getParameter("swclav");
        String accion = request.getParameter("accion");
        String id_estado = request.getParameter("id_estado");
        String id_persona = request.getParameter("id_persona");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_piso = request.getParameter("id_piso");
        String id_farmacia = request.getParameter("id_famacia");
        String cod_esta = request.getParameter("cod_esta");

        //Listar Sexos 
        List listarSexos = this.mi.getListarSexos();
        modelo.put("listaSexos", listarSexos);
        //Listar Estado Civil
        List listarEciviles = this.mi.getListarEciviles();
        modelo.put("listaCivil", listarEciviles);
        //Listar Paises
        List listarPaises = this.mi.getListarPaises();
        modelo.put("listaPaises", listarPaises);
        //Listar Empresas Telefonicas
        Consultorios a = new Consultorios();
        a.setCod_esta(cliente.getCod_esta());
        a.setId_estado("G");
        List listarCargos = this.mi.getListarConsultoriosGen(a);
        modelo.put("listaCargos", listarCargos);

        modelo.put("id_pais", "1");
        List listaDepartamentos1 = this.mi.getListarPaisDep(1);
        modelo.put("listaDepartamentos", listaDepartamentos1);

        Farmacias datofar = new Farmacias();
        datofar.setCod_esta(cliente.getCod_esta());
        List listarFarmacia = this.mi.getListarFarmacias(datofar);
        modelo.put("listarFarmacia", listarFarmacia);

        Laboratorios pai = new Laboratorios();
        pai.setId_estado("A");
        List listarLaboratorios = this.mi.getListarLaboratorios(pai);
        modelo.put("listarLaboratorios", listarLaboratorios);

        Salas dsala = new Salas();
        dsala.setCod_esta(cliente.getCod_esta());
        dsala.setTipo("T");   /////getListarPisosTotal
        List listarPisos = this.mi.getListarPisosTotal(dsala);
        modelo.put("listarPisos", listarPisos);

        Localidades localidad = new Localidades();
        List Estab1 = this.mi.getListarEsta(localidad);
        Localidades datol = (Localidades) Estab1.get(0);
        localidad.setId_localidad(datol.getId_localidad());
        localidad.setId_estado("X");
        List Estab0 = this.mi.getListarEstab(localidad);//getListarEstabGen
        modelo.put("listaEstab", Estab0);
        if (datol.getCod_esta() == 100000) {
            localidad.setArea("G");
            List Estab00 = this.mi.getListarEstabGen(localidad);//getListarEstabGen
            modelo.put("listaEstab", Estab00);
        }
        modelo.put("codesta", Integer.toString(datol.getCod_esta()));
        modelo.put("cod_esta", Integer.toString(cliente.getCod_esta()));
        modelo.put("idcargo", Integer.toString(cliente.getId_cargo()));

        if (datol.getCod_esta() == 200010) {
            Localidades datoestab = (Localidades) Estab1.get(0);
            localidad.setId_departamento(Integer.parseInt(Integer.toString(cliente.getCod_esta()).substring(0, 1)));
            localidad.setId_persona(10);
            localidad.setArea("C");
            List Estab = this.mi.getEstabTransCns(localidad);  /////getEstabTransCns
            modelo.put("listaEstab", Estab);
        }

        modelo.put("swclav", swclav);
        modelo.put("id_estado", id_estado);

        //Cuando de eligio un pais
        if (request.getParameter("id_pais") != null) {
            int id_paisx = Integer.parseInt(request.getParameter("id_pais"));
            modelo.put("id_pais", Integer.toString(id_paisx));

            List listaDepartamentos = this.mi.getListarPaisDep(1);
            modelo.put("listaDepartamentos", listaDepartamentos);

            modelo.put("dip", request.getParameter("dip"));
            modelo.put("paterno", request.getParameter("paterno"));
            modelo.put("materno", request.getParameter("materno"));
            modelo.put("nombres", request.getParameter("nombres"));
            modelo.put("id_sexo", request.getParameter("id_sexo"));
            modelo.put("id_estado", request.getParameter("id_estado"));
            modelo.put("id_ecivil", request.getParameter("id_ecivil"));
            modelo.put("id_consultorio", request.getParameter("id_consultorio"));
            modelo.put("dia", request.getParameter("dia"));
            modelo.put("mes", request.getParameter("mes"));
            modelo.put("anio", request.getParameter("anio"));
            modelo.put("direccion", request.getParameter("direccion"));
            modelo.put("telefono", request.getParameter("telefono"));
            modelo.put("celular", request.getParameter("celular"));
            modelo.put("correo", request.getParameter("correo"));
            modelo.put("firma", request.getParameter("firma"));
            modelo.put("matricula", request.getParameter("matricula"));
            modelo.put("codigoprof", request.getParameter("codigoprof"));
        }

        //Cuando se eligio n departamento
        if (request.getParameter("id_departamento") != null) {
            int id_pais1 = Integer.parseInt(request.getParameter("id_pais"));
            int id_depto = Integer.parseInt(request.getParameter("id_departamento"));
            modelo.put("id_departamento", Integer.toString(id_depto));

            Provincias provincia = new Provincias();
            provincia.setId_pais(1);
            provincia.setId_departamento(id_depto);
            List listaProvincias = this.mi.getListarPaisDepProv(provincia);
            modelo.put("listaProvincias", listaProvincias);
        }
        //Cuando se elige una provincia
        if (request.getParameter("id_provincia") != null) {
            int id_pais1 = Integer.parseInt(request.getParameter("id_pais"));
            int id_departamento1 = Integer.parseInt(request.getParameter("id_departamento"));
            int id_prov = Integer.parseInt(request.getParameter("id_provincia"));
            modelo.put("id_provincia", Integer.toString(id_prov));

            localidad.setId_pais(1);
            localidad.setId_departamento(id_departamento1);
            localidad.setId_provincia(id_prov);
            List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
            modelo.put("listaLocalidad", listaLocalidad);
        }

        //Para La primera vez que entra a la pagina
        if ((id_persona != null) && ("Modificar".equals(accion)) && (request.getParameter("recargado") == null)) {
            //Sacamos los datos 
            if ("1".equals(swclave)) { ////cuando entra usuario y el quiere modificar sus datos
                id_estado = "1";
            }
            Personas buscarEmpleado = this.mi.getBuscarPersona(Integer.parseInt(id_persona));
            modelo.put("buscarEmpleado", buscarEmpleado);

            List listaDepartamentos = this.mi.getListarPaisDep(buscarEmpleado.getId_pais());
            modelo.put("listaDepartamentos", listaDepartamentos);

            Provincias provincia = new Provincias();
            provincia.setId_pais(buscarEmpleado.getId_pais());
            provincia.setId_departamento(buscarEmpleado.getId_departamento());
            List listaProvincias = this.mi.getListarPaisDepProv(provincia);
            modelo.put("listaProvincias", listaProvincias);

            localidad.setId_pais(buscarEmpleado.getId_pais());
            localidad.setId_departamento(buscarEmpleado.getId_departamento());
            localidad.setId_provincia(buscarEmpleado.getId_provincia());
            List listaLocalidad = this.mi.getListarPaisDepProvLoc(localidad);
            modelo.put("listaLocalidad", listaLocalidad);

            modelo.put("id_pais", Integer.toString(buscarEmpleado.getId_pais()));
            modelo.put("id_departamento", Integer.toString(buscarEmpleado.getId_departamento()));
            modelo.put("id_provincia", Integer.toString(buscarEmpleado.getId_provincia()));
            modelo.put("id_localidad", Integer.toString(buscarEmpleado.getId_localidad()));
            modelo.put("dip", buscarEmpleado.getDip());
            modelo.put("nombres", buscarEmpleado.getNombres());
            modelo.put("paterno", buscarEmpleado.getPaterno());
            modelo.put("materno", buscarEmpleado.getMaterno());
            modelo.put("telefono", buscarEmpleado.getTelefono());
            modelo.put("celular", buscarEmpleado.getCelular());
            modelo.put("direccion", buscarEmpleado.getDireccion());
            modelo.put("correo", buscarEmpleado.getCorreo());
            modelo.put("firma", buscarEmpleado.getFirma());
            modelo.put("matricula", buscarEmpleado.getMatricula());
            modelo.put("codigoprof", buscarEmpleado.getCodigoprof());
            modelo.put("inicial", buscarEmpleado.getCadena1());
            modelo.put("id_estado", buscarEmpleado.getId_estado());
            modelo.put("id_persona", buscarEmpleado.getCorreo());
            modelo.put("id_medico", Integer.toString(buscarEmpleado.getId_medico()));
            modelo.put("nropac", Integer.toString(buscarEmpleado.getNropac()));
            modelo.put("id_urgencias", Integer.toString(buscarEmpleado.getUrgencias()));
            modelo.put("id_laboratorio", Integer.toString(buscarEmpleado.getId_laboratorio()));
            modelo.put("id_piso", Integer.toString(buscarEmpleado.getId_piso()));
            modelo.put("id_farmacia", Integer.toString(buscarEmpleado.getId_farmacia()));
            modelo.put("id_consultorio", Integer.toString(buscarEmpleado.getId_consultorio()));
            modelo.put("cod_esta", Integer.toString(buscarEmpleado.getCod_esta()));
            modelo.put("celular", buscarEmpleado.getCelular());
            modelo.put("id_sexo", Integer.toString(buscarEmpleado.getId_sexo()));
            modelo.put("id_ecivil", Integer.toString(buscarEmpleado.getId_ecivil()));
            modelo.put("anio", Integer.toString(buscarEmpleado.getFec_nacimiento().getYear() + 1900));
            modelo.put("mes", Integer.toString(buscarEmpleado.getFec_nacimiento().getMonth() + 1));
            modelo.put("dia", Integer.toString(buscarEmpleado.getFec_nacimiento().getDate()));

            buscarEmpleado.setDip("L");
            List listarPersonas = this.mi.getListarPersonasLocal(buscarEmpleado);
            modelo.put("listarPersonas", listarPersonas);
        }

        modelo.put("id_persona", id_persona);
        modelo.put("accion", accion);
        return new ModelAndView("administrarpersonas/NuevoPersona", modelo);
    }
}
