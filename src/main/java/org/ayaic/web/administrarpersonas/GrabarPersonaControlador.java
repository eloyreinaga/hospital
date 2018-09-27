package org.ayaic.web.administrarpersonas;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Personas;
import org.ayaic.domain.logic.MiFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrabarPersonaControlador {

    private final MiFacade mi;

    public GrabarPersonaControlador(MiFacade mi) {
        this.mi = mi;
    }
    
    @RequestMapping("/GrabarPersona.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Map modelo = new HashMap();

        Clientes cliente = (Clientes) request.getSession().getAttribute("__sess_cliente");
        String accion1 = request.getParameter("accion1");
        String accion = request.getParameter("accion");
        String swclav = request.getParameter("swclav");
        String swclave = request.getParameter("swclave");
        String id_persona = request.getParameter("id_persona");
        String dip = request.getParameter("dip");
        String paterno = request.getParameter("paterno");
        String materno = request.getParameter("materno");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String id_sexo = request.getParameter("id_sexo");
        String id_estado_civil = request.getParameter("id_ecivil");
        String id_pais = request.getParameter("id_pais");
        String id_medico = request.getParameter("id_medico");
        String nropac = request.getParameter("nropac");
        String id_farmacia = request.getParameter("id_farmacia");
        String id_departamento = request.getParameter("id_departamento");
        String id_provincia = request.getParameter("id_provincia");
        String id_localidad = request.getParameter("id_localidad");
        String telefono = request.getParameter("telefono");
        String dia = request.getParameter("dia");
        String correo = request.getParameter("correo");
        String firma = request.getParameter("firma");
        String mes = request.getParameter("mes");
        String anio = request.getParameter("anio");
        String id_consultorio = request.getParameter("id_consultorio");
        String celular = request.getParameter("celular");
        String cod_esta1 = request.getParameter("cod_esta");
        String urgencias = request.getParameter("urgencias");
        String inicial = request.getParameter("inicial");
        String id_laboratorio = request.getParameter("id_laboratorio");
        String id_piso = request.getParameter("id_piso");
        String matricula = request.getParameter("matricula");
        String codigoprof = request.getParameter("codigoprof");
        //InetAddress ip;
        //ip = InetAddress.getLocalHost();
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        modelo.put("swclav", swclav);

        if ("".equals(cod_esta1)) {
            cod_esta1 = Integer.toString(cliente.getCod_esta());/////////a revisar por afiliacion
        }

        if ("Adicionar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(dip)) || ("".equals(nombres)) || ("".equals(direccion)) || ("".equals(id_sexo))
                    || ("".equals(id_departamento)) || ("".equals(id_provincia))
                    || ("".equals(id_localidad)) || ("".equals(dia)) || ("".equals(mes)) || ("".equals(anio))
                    || ("".equals(id_estado_civil)) || ("".equals(id_consultorio))) {
                return new ModelAndView("Aviso", "mensaje", "Ingrese los datos obligatorios");
            } else {
                int diax = Integer.parseInt(dia);
                int mesx = Integer.parseInt(mes) - 1;
                int aniox = Integer.parseInt(anio) - 1900;
                Date fec_nacimiento = new Date(aniox, mesx, diax);

                Personas persona = new Personas();
                persona.setDip(dip);
                persona.setPaterno(paterno);
                persona.setMaterno(materno);
                persona.setNombres(nombres);
                persona.setId_sexo(Integer.parseInt(request.getParameter("id_sexo")));
                persona.setId_departamento(Integer.parseInt(request.getParameter("id_departamento")));
                persona.setId_provincia(Integer.parseInt(request.getParameter("id_provincia")));
                persona.setId_localidad(Integer.parseInt(request.getParameter("id_localidad")));
                persona.setId_ecivil(Integer.parseInt(request.getParameter("id_ecivil")));
                persona.setTelefono(telefono);
                persona.setCorreo(correo);
                persona.setFirma(firma);
                persona.setDireccion(direccion);
                persona.setFec_nacimiento(fec_nacimiento);
                persona.setUlt_usuario(cliente.getId_usuario());
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setId_farmacia(Integer.parseInt(id_farmacia));
                persona.setId_laboratorio(Integer.parseInt(id_laboratorio));
                persona.setId_medico(Integer.parseInt(id_medico));
                persona.setNropac(Integer.parseInt(nropac));
                persona.setId_piso(Integer.parseInt(id_piso));
                persona.setCelular(celular);
                persona.setCelular(celular);
                persona.setCod_esta(Integer.parseInt(cod_esta1));
                persona.setUrgencias(Integer.parseInt(urgencias));
                persona.setMatricula(matricula.toUpperCase());
                persona.setCodigoprof(codigoprof.toUpperCase());
                persona.setCadena1(inicial);

                this.mi.setCrearPersona(persona);

                Menues usuario_rol = new Menues();
                usuario_rol.setId_usuario(cliente.getId_usuario());
                List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
                List listarPersonas = this.mi.getListarPersonas(persona);
                modelo.put("listarPersonas", listarPersonas);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        persona.setDip("L");
                        List listarPersonas2 = this.mi.getListarPersonasLocal(persona);
                        modelo.put("listarPersonas", listarPersonas2);
                    }
                }

                return new ModelAndView("administrarpersonas/ListarPersonas", modelo);
            }
        }

        if ("Modificar".equals(accion) && "Aceptar".equals(accion1)) {
            if (("".equals(dip)) || ("".equals(nombres)) || ("".equals(direccion)) || ("".equals(id_sexo))
                    || ("".equals(id_departamento)) || ("".equals(id_provincia))
                    || ("".equals(id_localidad)) || ("".equals(dia)) || ("".equals(mes)) || ("".equals(anio))
                    || ("".equals(id_estado_civil)) || ("".equals(id_consultorio))) {
                return new ModelAndView("Aviso", "mensaje", "Faltan introducir datos para modificar");
            } else {
                int diad = Integer.parseInt(request.getParameter("dia"));
                int mesd = Integer.parseInt(request.getParameter("mes")) - 1;
                int aniod = Integer.parseInt(request.getParameter("anio")) - 1900;
                Date fec_nacimiento = new Date(aniod, mesd, diad);

                if ("1".equals(swclave)) {
                    cod_esta1 = Integer.toString(cliente.getCod_esta());
                }

                Personas persona = new Personas();
                persona.setId_persona(Integer.parseInt(request.getParameter("id_persona")));
                persona.setDip(request.getParameter("dip"));
                persona.setPaterno(request.getParameter("paterno"));
                persona.setMaterno(request.getParameter("materno"));
                persona.setNombres(request.getParameter("nombres"));
                persona.setId_sexo(Integer.parseInt(request.getParameter("id_sexo")));
                //persona.setId_pais(Integer.parseInt(request.getParameter("id_pais")));
                persona.setConsultorio(request.getParameter("cargo"));
                try {
                    persona.setId_departamento(Integer.parseInt(request.getParameter("id_departamento")));
                    persona.setId_provincia(Integer.parseInt(request.getParameter("id_provincia")));
                    persona.setId_localidad(Integer.parseInt(request.getParameter("id_localidad")));
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "Faltan datos");
                }
                persona.setId_ecivil(Integer.parseInt(request.getParameter("id_ecivil")));
                persona.setTelefono(request.getParameter("telefono"));
                persona.setCorreo(request.getParameter("correo"));
                persona.setFirma(request.getParameter("firma"));
                persona.setDireccion(request.getParameter("direccion"));
                persona.setFec_nacimiento(fec_nacimiento);
                persona.setUlt_usuario(cliente.getId_usuario());
                persona.setId_consultorio(Integer.parseInt(id_consultorio));
                persona.setCelular(celular);
                persona.setCod_esta(Integer.parseInt(cod_esta1));
                persona.setUrgencias(Integer.parseInt(urgencias));
                persona.setId_farmacia(Integer.parseInt(id_farmacia));
                persona.setId_laboratorio(Integer.parseInt(id_laboratorio));
                persona.setId_medico(Integer.parseInt(id_medico));
                persona.setNropac(Integer.parseInt(nropac));
                persona.setId_piso(Integer.parseInt(id_piso));
                persona.setMatricula(matricula.toUpperCase());
                persona.setCodigoprof(codigoprof.toUpperCase());

                if ("A".equals(request.getParameter("id_estado"))) {
                    persona.setId_estado("A");
                } else {
                    persona.setId_estado("B");
                }
                persona.setUlt_usuario(cliente.getId_persona());
                persona.setCadena1("M");
                persona.setCadena2(ip);
                persona.setCadena3(host);
                persona.setCadena4(inicial);
                this.mi.setModificarPersona(persona);

                if ("1".equals(swclave)) {
                    request.getSession().removeAttribute("__sess_cliente");
                    request.getSession().invalidate();
                    return new ModelAndView("Distro", "url", ".");
                }

                //Listado de Personas
                persona.setCod_esta(cliente.getCod_esta());
                Menues usuario_rol = new Menues();
                usuario_rol.setId_usuario(cliente.getId_usuario());
                List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
                List listarPersonas = this.mi.getListarPersonas(persona);
                modelo.put("listarPersonas", listarPersonas);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        persona.setDip("L");
                        List listarPersonas2 = this.mi.getListarPersonasLocal(persona);
                        modelo.put("listarPersonas", listarPersonas2);
                    }
                }

                modelo.put("nombres", cliente.getNombres());
                if ("1".equals(swclav)) {
                    return new ModelAndView("administrarClaves/RegistrarClave", modelo);
                }
                return new ModelAndView("administrarpersonas/ListarPersonas", modelo);
            }

        }

        if ("Eliminar".equals(accion) && "Aceptar".equals(accion1)) {
            if (request.getParameter("id_persona") != null) {
                Personas eliminar = new Personas();
                eliminar.setId_persona(Integer.parseInt(request.getParameter("id_persona")));

                try {
                    this.mi.setEliminarPersona(eliminar);
                } catch (Exception e) {
                    return new ModelAndView("Aviso", "mensaje", "El registro a eliminar tiene dependencias");
                }
                //Listado de Personas
                Personas persona = new Personas();
                persona.setCod_esta(cliente.getCod_esta());
                List listarPersonas = this.mi.getListarPersonas(persona);
                modelo.put("listarPersonas", listarPersonas);
                Menues usuario_rol = new Menues();
                usuario_rol.setId_usuario(cliente.getId_usuario());
                List listaUsrRoles = this.mi.getListaUsrRoles(usuario_rol);
                for (int i = 0; i < listaUsrRoles.size(); i++) {
                    Menues datorol = (Menues) listaUsrRoles.get(i);
                    if (datorol.getId_rol() == 27) {
                        persona.setDip("L");
                        List listarPersonas2 = this.mi.getListarPersonasLocal(persona);
                        modelo.put("listarPersonas", listarPersonas2);
                    }
                }

                return new ModelAndView("administrarpersonas/ListarPersonas", modelo);
            }
        }

        return new ModelAndView("administrarpersonas/ListarPersonas", modelo);
    }
}
