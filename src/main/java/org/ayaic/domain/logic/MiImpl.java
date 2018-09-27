package org.ayaic.domain.logic;

import java.util.List;

import org.ayaic.dao.ActividadDao;
import org.ayaic.dao.CamasDao;
import org.ayaic.dao.CarpetasDao;
import org.ayaic.dao.CategoriasDao;
import org.ayaic.dao.ClieentesDao;
import org.ayaic.dao.ClientesDao;
import org.ayaic.dao.ConsultoriosDao;
import org.ayaic.dao.CostosDao;
import org.ayaic.dao.CuadernosDao;
import org.ayaic.dao.CuentasDao;
import org.ayaic.dao.DepartamentosDao;
import org.ayaic.dao.DetalleDao;
import org.ayaic.dao.DocumentosDao;
import org.ayaic.dao.EcivilesDao;
import org.ayaic.dao.EmpresasDao;
import org.ayaic.dao.EnlacesDao;
import org.ayaic.dao.EscolaridadesDao;
import org.ayaic.dao.FarmaciasDao;
import org.ayaic.dao.HistorialesDao;
import org.ayaic.dao.LaboratoriosDao;
import org.ayaic.dao.LocalidadesDao;
import org.ayaic.dao.MedicamentosDao;
import org.ayaic.dao.MenuesDao;
import org.ayaic.dao.PacientesDao;
import org.ayaic.dao.PaisesDao;
import org.ayaic.dao.ParentescosDao;
import org.ayaic.dao.PersonasDao;
import org.ayaic.dao.PrestacionesDao;
import org.ayaic.dao.ProveedoresDao;
import org.ayaic.dao.ProvinciasDao;
import org.ayaic.dao.QuirofanosDao;
import org.ayaic.dao.RecetasDao;
import org.ayaic.dao.RolesDao;
import org.ayaic.dao.RubrosDao;
import org.ayaic.dao.SalasDao;
import org.ayaic.dao.SegurosDao;
import org.ayaic.dao.SexosDao;
import org.ayaic.dao.TablerosDao;
import org.ayaic.dao.UsuariosDao;
import org.ayaic.domain.Actividad;
import org.ayaic.domain.Camas;
import org.ayaic.domain.Carpetas;
import org.ayaic.domain.Categorias;
import org.ayaic.domain.Clieentes;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Consultorios;
import org.ayaic.domain.Costos;
import org.ayaic.domain.Cuadernos;
import org.ayaic.domain.Cuentas;
import org.ayaic.domain.Departamentos;
import org.ayaic.domain.Detalle;
import org.ayaic.domain.Documentos;
import org.ayaic.domain.Eciviles;
import org.ayaic.domain.Empresas;
import org.ayaic.domain.Enlaces;
import org.ayaic.domain.Escolaridades;
import org.ayaic.domain.Farmacias;
import org.ayaic.domain.Historiales;
import org.ayaic.domain.Laboratorios;
import org.ayaic.domain.Localidades;
import org.ayaic.domain.Medicamentos;
import org.ayaic.domain.Menues;
import org.ayaic.domain.Pacientes;
import org.ayaic.domain.Paises;
import org.ayaic.domain.Parentescos;
import org.ayaic.domain.Personas;
import org.ayaic.domain.Prestaciones;
import org.ayaic.domain.Proveedores;
import org.ayaic.domain.Provincias;
import org.ayaic.domain.Quirofanos;
import org.ayaic.domain.Recetas;
import org.ayaic.domain.Roles;
import org.ayaic.domain.Rubros;
import org.ayaic.domain.Salas;
import org.ayaic.domain.Seguros;
import org.ayaic.domain.Sexos;
import org.ayaic.domain.Tableros;
import org.ayaic.domain.Usuarios;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MiImpl implements MiFacade {

    private final ClientesDao clientesDao;
    private final UsuariosDao usuariosDao;
    private final RolesDao rolesDao;
    private final CategoriasDao categoriasDao;
    private final EnlacesDao enlacesDao;
    private final PersonasDao personasDao;
    private final TablerosDao tablerosDao;
    private final MenuesDao menuesDao;
    private final PaisesDao paisesDao;
    private final LaboratoriosDao laboratoriosDao;
    private final CarpetasDao carpetasDao;
    private final CostosDao costosDao;
    private final CamasDao camasDao;
    private final DetalleDao detalleDao;
    private final SexosDao sexosDao;
    private final ClieentesDao clieentesDao;
    private final ProveedoresDao proveedoresDao;
    private final ParentescosDao parentescosDao;
    private final RubrosDao rubrosDao;
    private final SalasDao salasDao;
    private final SegurosDao segurosDao;
    private final EmpresasDao empresasDao;
    private final DocumentosDao documentosDao;
    private final EscolaridadesDao escolaridadesDao;
    private final ConsultoriosDao consultoriosDao;
    private final DepartamentosDao departamentosDao;
    private final EcivilesDao ecivilesDao;
    private final ProvinciasDao provinciasDao;
    private final LocalidadesDao localidadesDao;
    private final PacientesDao pacientesDao;
    private final HistorialesDao historialesDao;
    private final MedicamentosDao medicamentosDao;
    private final RecetasDao recetasDao;
    private final PrestacionesDao prestacionesDao;
    private final CuadernosDao cuadernosDao;
    private final CuentasDao cuentasDao;
    private final ActividadDao actividadDao;
    private final QuirofanosDao quirofanosDao;
    private final FarmaciasDao farmaciasDao;

    public MiImpl(ClientesDao clientesDao, UsuariosDao usuariosDao, RolesDao rolesDao, CategoriasDao categoriasDao, EnlacesDao enlacesDao, PersonasDao personasDao, TablerosDao tablerosDao, MenuesDao menuesDao, PaisesDao paisesDao, LaboratoriosDao laboratoriosDao, CarpetasDao carpetasDao, CostosDao costosDao, CamasDao camasDao, DetalleDao detalleDao, SexosDao sexosDao, ClieentesDao clieentesDao, ProveedoresDao proveedoresDao, ParentescosDao parentescosDao, RubrosDao rubrosDao, SalasDao salasDao, SegurosDao segurosDao, EmpresasDao empresasDao, DocumentosDao documentosDao, EscolaridadesDao escolaridadesDao, ConsultoriosDao consultoriosDao, DepartamentosDao departamentosDao, EcivilesDao ecivilesDao, ProvinciasDao provinciasDao, LocalidadesDao localidadesDao, PacientesDao pacientesDao, HistorialesDao historialesDao, MedicamentosDao medicamentosDao, RecetasDao recetasDao, PrestacionesDao prestacionesDao, CuadernosDao cuadernosDao, CuentasDao cuentasDao, ActividadDao actividadDao, QuirofanosDao quirofanosDao, FarmaciasDao farmaciasDao) {
        this.clientesDao = clientesDao;
        this.usuariosDao = usuariosDao;
        this.rolesDao = rolesDao;
        this.categoriasDao = categoriasDao;
        this.enlacesDao = enlacesDao;
        this.personasDao = personasDao;
        this.tablerosDao = tablerosDao;
        this.menuesDao = menuesDao;
        this.paisesDao = paisesDao;
        this.laboratoriosDao = laboratoriosDao;
        this.carpetasDao = carpetasDao;
        this.costosDao = costosDao;
        this.camasDao = camasDao;
        this.detalleDao = detalleDao;
        this.sexosDao = sexosDao;
        this.clieentesDao = clieentesDao;
        this.proveedoresDao = proveedoresDao;
        this.parentescosDao = parentescosDao;
        this.rubrosDao = rubrosDao;
        this.salasDao = salasDao;
        this.segurosDao = segurosDao;
        this.empresasDao = empresasDao;
        this.documentosDao = documentosDao;
        this.escolaridadesDao = escolaridadesDao;
        this.consultoriosDao = consultoriosDao;
        this.departamentosDao = departamentosDao;
        this.ecivilesDao = ecivilesDao;
        this.provinciasDao = provinciasDao;
        this.localidadesDao = localidadesDao;
        this.pacientesDao = pacientesDao;
        this.historialesDao = historialesDao;
        this.medicamentosDao = medicamentosDao;
        this.recetasDao = recetasDao;
        this.prestacionesDao = prestacionesDao;
        this.cuadernosDao = cuadernosDao;
        this.cuentasDao = cuentasDao;
        this.actividadDao = actividadDao;
        this.quirofanosDao = quirofanosDao;
        this.farmaciasDao = farmaciasDao;
    }

//-------------------------------------------------------------------------
// Operation methods, implementing the MiFacade interface
//-------------------------------------------------------------------------
    @Override
    public Enlaces getEnlBuscarEnlace(Enlaces enlace) {
        return this.enlacesDao.getEnlBuscarEnlace(enlace);
    }
    //Clientes

    @Override
    public Clientes getBuscarConexion(Usuarios usuario) {
        return this.clientesDao.getBuscarConexion(usuario);
    }
    //fin Clientes

    //Roles
    @Override
    public Roles getBuscarRol(Roles rol) {
        return this.rolesDao.getBuscarRol(rol);
    }

    @Override
    public List getListarRoles() {
        return this.rolesDao.getListarRoles();
    }

    @Override
    public void setCrearRol(Roles rol) {
        this.rolesDao.setCrearRol(rol);
    }

    @Override
    public void setModificarRol(Roles rol) {
        this.rolesDao.setModificarRol(rol);
    }

    @Override
    public void setEliminarRol(Roles rol) {
        this.rolesDao.setEliminarRol(rol);
    }

    @Override
    public List getListarRolesCliente(Roles rol) {
        return this.rolesDao.getListarRolesCliente(rol);
    }

    @Override
    public Roles getBuscarRolCliente(Roles rol) {
        return this.rolesDao.getBuscarRolCliente(rol);
    }

    @Override
    public List getListarAlmacenesCliente(Roles rol) {
        return this.rolesDao.getListarAlmacenesCliente(rol);
    }

    @Override
    public Roles getBuscarAlmacenCliente(Roles rol) {
        return this.rolesDao.getBuscarAlmacenCliente(rol);
    }
    //fin Roles

    //Menues
    @Override
    public List getListarCategorias(Categorias categoria) {
        return this.categoriasDao.getListarCategorias(categoria);
    }

    @Override
    public List getListarEnlaces(Enlaces enlace) {
        return this.enlacesDao.getListarEnlaces(enlace);
    }

    @Override
    public Enlaces getBuscarEnlace(Enlaces enlace) {
        return this.enlacesDao.getBuscarEnlace(enlace);
    }
    //fin Menues

    public Clientes getComprobarUsuario(Usuarios usuario) {
        return this.clientesDao.getComprobarUsuario(usuario);
    }

    //Personas
    @Override
    public List getListarPersonas(Personas persona) {
        return this.personasDao.getListarPersonas(persona);
    }
    
    @Override
    public List getListarPersonasInter(Personas persona) {
        return this.personasDao.getListarPersonasInter(persona);
    }

    @Override
    public List getListarPersonasFacturas(Personas persona) {
        return this.personasDao.getListarPersonasFacturas(persona);
    }

    @Override
    public List getListarPersonasSoloAten(Personas persona) {
        return this.personasDao.getListarPersonasSoloAten(persona);
    }

    @Override
    public List getListarPersonasNombre(Personas persona) {
        return this.personasDao.getListarPersonasNombre(persona);
    }

    @Override
    public List getListarPersonasNombreMat(Personas persona) {
        return this.personasDao.getListarPersonasNombreMat(persona);
    }

    @Override
    public List getListarPersonasUsua(Personas persona) {
        return this.personasDao.getListarPersonasUsua(persona);
    }

    @Override
    public List getListarPersonasUsuaLocal(Personas persona) {
        return this.personasDao.getListarPersonasUsuaLocal(persona);
    }

    @Override
    public List getListarPersonaUnico(Personas persona) {
        return this.personasDao.getListarPersonaUnico(persona);
    }

    @Override
    public List getListarPersonasNombreLocal(Personas persona) {
        return this.personasDao.getListarPersonasNombreLocal(persona);
    }

    @Override
    public List getListarPersonasLocal(Personas persona) {
        return this.personasDao.getListarPersonasLocal(persona);
    }

    @Override
    public List getListarPersonasEstab(Personas persona) {
        return this.personasDao.getListarPersonasEstab(persona);
    }

    @Override
    public List getListarPersonasFarma(Personas persona) {
        return this.personasDao.getListarPersonasFarma(persona);
    }
    
    @Override
    public List getListarPersonasFichas(Personas persona) {
        return this.personasDao.getListarPersonasFichas(persona);
    }

    @Override
    public Personas getDatosPersona(int id_persona) {
        return this.personasDao.getDatosPersona(id_persona);
    }

    @Override
    public Personas getDatosPersonaInt(int id_persona) {
        return this.personasDao.getDatosPersonaInt(id_persona);
    }

    public List getDatosPersonaInter(Personas persona) {
        return this.personasDao.getDatosPersonaInter(persona);
    }
    
    public List getDatosPersonaConsul(Personas persona) {
        return this.personasDao.getDatosPersonaConsul(persona);
    }

    public List getDatosPersonaConsulUrgen(Personas persona) {
        return this.personasDao.getDatosPersonaConsulUrgen(persona);
    }

    public List getDatosPersonaConsulEmerg(Personas persona) {
        return this.personasDao.getDatosPersonaConsulEmerg(persona);
    }

    @Override
    public List getDatosPersonaConsulLabos(Personas persona) {
        return this.personasDao.getDatosPersonaConsulLabos(persona);
    }

    public List getDatosPersonaConsulCIE(Personas persona) {
        return this.personasDao.getDatosPersonaConsulCIE(persona);
    }

    public List getListaMedicoAsig(Personas persona) {
        return this.personasDao.getListaMedicoAsig(persona);
    }

    public Personas getBuscarPersona(int id_persona) {
        return this.personasDao.getBuscarPersona(id_persona);
    }

    public void setCrearPersona(Personas persona) {
        this.personasDao.setCrearPersona(persona);
    }

    public void setCrearPersonaInter(Personas persona) {
        this.personasDao.setCrearPersonaInter(persona);
    }
    
    public void setCrearPersonaEnfer(Personas persona) {
        this.personasDao.setCrearPersonaEnfer(persona);
    }

    public void setModificarPersona(Personas persona) {
        this.personasDao.setModificarPersona(persona);
    }

    public void setModificarPersonaFic(Personas persona) {
        this.personasDao.setModificarPersonaFic(persona);
    }

    public void setEliminarPersona(Personas persona) {
        this.personasDao.setEliminarPersona(persona);
    }
    
    public void setEliminarPersonaInter(Personas persona) {
        this.personasDao.setEliminarPersonaInter(persona);
    }

    public void setEliminarPersonaAsig(Personas persona) {
        this.personasDao.setEliminarPersonaAsig(persona);
    }

    public List getListarFichadas(Personas persona) {
        return this.personasDao.getListarFichadas(persona);
    }
    //Fin Personas

    //Administrar noticias    
    public List getListarNoticias() {
        return this.tablerosDao.getListarNoticias();
    }

    public List getListarTiposTableros() {
        return this.tablerosDao.getListarTiposTableros();
    }

    public List getListarTiposAvisos() {
        return this.tablerosDao.getListarTiposAvisos();
    }

    public int setRegistrarTablero(Tableros tablero) {
        return this.tablerosDao.setRegistrarTablero(tablero);
    }

    public Tableros getBuscarTablero(Tableros tablero) {
        return this.tablerosDao.getBuscarTablero(tablero);
    }

    public int setEliminarTablero(Tableros tablero) {
        return this.tablerosDao.setEliminarTablero(tablero);
    }
    //Fin Administrar noticias

    public List getListaUsuarios(Usuarios usuario) {
        return this.usuariosDao.getListaUsuarios(usuario);
    }

    public List getListaUsuariosLocal(Usuarios usuario) {
        return this.usuariosDao.getListaUsuariosLocal(usuario);
    }

    public List getListaUsuariosLocal2(Usuarios usuario) {
        return this.usuariosDao.getListaUsuariosLocal2(usuario);
    }

    public List getListaUsuariosNombre(Usuarios usuario) {
        return this.usuariosDao.getListaUsuariosNombre(usuario);
    }

    public List getListaUsuariosNombreLocal(Usuarios usuario) {
        return this.usuariosDao.getListaUsuariosNombreLocal(usuario);
    }

    public Usuarios getDatosUsuario(int id_usuario) {
        return this.usuariosDao.getDatosUsuario(id_usuario);
    }

    public List getListarUsuariosUbicacionOrganica(Usuarios usuario) {
        return this.usuariosDao.getListarUsuariosUbicacionOrganica(usuario);
    }

    public int getVerificarUsuario(Usuarios usuario) {
        return this.usuariosDao.getVerificarUsuario(usuario);
    }

    public int setRegistrarNuevaClave(Usuarios usuario) {
        return this.usuariosDao.setRegistrarNuevaClave(usuario);
    }

    @Override
    public void setCrearUsuario(Usuarios usuario) {
        this.usuariosDao.setCrearUsuario(usuario);
    }
    
    @Override
    public void setCrearRegUsuario(Usuarios usuario) {
        this.usuariosDao.setCrearRegUsuario(usuario);
    }

    public void setModificarUsuario(Usuarios usuario) {
        this.usuariosDao.setModificarUsuario(usuario);
    }

    public void setEliminarUsuario(Usuarios usuario) {
        this.usuariosDao.setEliminarUsuario(usuario);
    }

    //Administrar noticias
    public List getListar_Enlaces() {
        return this.menuesDao.getListar_Enlaces();
    }

    public Menues getCategoria(String id_categoria) {
        return this.menuesDao.getCategoria(id_categoria);
    }

    public Menues getEnlace(int id_enlace) {
        return this.menuesDao.getEnlace(id_enlace);
    }

    public int setCrearEnlace(Menues enlace) {
        return this.menuesDao.setCrearEnlace(enlace);
    }

    public int setEliminarEnlace(Menues enlace) {
        return this.menuesDao.setEliminarEnlace(enlace);
    }

    public int setModificarEnlace(Menues enlace) {
        return this.menuesDao.setModificarEnlace(enlace);
    }

    public List getListaEnlaces(int id_categoria) {
        return this.menuesDao.getListaEnlaces(id_categoria);
    }

    public List getListaCategorias() {
        return this.menuesDao.getListaCategorias();
    }
    //menues

    public void setCrearMenu(Menues menu) {
        this.menuesDao.setCrearMenu(menu);
    }

    public void setEliminarMenu(Menues menu) {
        this.menuesDao.setEliminarMenu(menu);
    }

    public Menues getUsrRolEnlace(Menues menu) {
        return this.menuesDao.getUsrRolEnlace(menu);
    }

    public List getListaUsrRolEnlaces(Menues menu) {
        return this.menuesDao.getListaUsrRolEnlaces(menu);
    }

    public int setEliminarCategoria(Menues categoria) {
        return this.menuesDao.setEliminarCategoria(categoria);
    }

    public int setModificarCategoria(Menues categoria) {
        return this.menuesDao.setModificarCategoria(categoria);
    }

    public int setCrearCategoria(Menues categoria) {
        return this.menuesDao.setCrearCategoria(categoria);
    }

    public int getBuscarIdCategoriaRepetido(Menues categoria) {
        return this.menuesDao.getBuscarIdCategoriaRepetido(categoria);
    }

    //usuarios rol
    public List getListaUsrRoles(Menues usuario) {
        return this.menuesDao.getListaUsrRoles(usuario);
    }

    public Menues getUsrRol(Menues menu) {
        return this.menuesDao.getUsrRol(menu);
    }

    public void setCrearUsrRol(Menues rol) {
        this.menuesDao.setCrearUsrRol(rol);
    }

    public void setModificarUsrRol(Menues rol) {
        this.menuesDao.setModificarUsrRol(rol);
    }

    public void setEliminarUsrRol(Menues rol) {
        this.menuesDao.setEliminarUsrRol(rol);
    }

    //Paises
    public List getListarPaises() {
        return this.paisesDao.getListarPaises();
    }

    public Paises getDatosPais(int id_pais) {
        return this.paisesDao.getDatosPais(id_pais);
    }

    public void setCrearPais(Paises pais) {
        this.paisesDao.setCrearPais(pais);
    }

    public void setModificarPais(Paises pais) {
        this.paisesDao.setModificarPais(pais);
    }

    public void setEliminarPais(Paises pais) {
        this.paisesDao.setEliminarPais(pais);
    }

    //Laboratorios
    public List getListarLaboratorios(Laboratorios laboratorio) {
        return this.laboratoriosDao.getListarLaboratorios(laboratorio);
    }

    public List getDatosLaboratorioRayos(Laboratorios laboratorio) {
        return this.laboratoriosDao.getDatosLaboratorioRayos(laboratorio);
    }

    public List getListarLabGrupo(Laboratorios laboratorio) {
        return this.laboratoriosDao.getListarLabGrupo(laboratorio);
    }

    public List getListarLabGrupoDet(Laboratorios laboratorio) {
        return this.laboratoriosDao.getListarLabGrupoDet(laboratorio);
    }

    public Laboratorios getDatosLaboratorio(Laboratorios laboratorio) {
        return this.laboratoriosDao.getDatosLaboratorio(laboratorio);
    }

    public Laboratorios getDatosLaboratorioNombre(Laboratorios laboratorio) {
        return this.laboratoriosDao.getDatosLaboratorioNombre(laboratorio);
    }

    public void setCrearLaboratorio(Laboratorios laboratorio) {
        this.laboratoriosDao.setCrearLaboratorio(laboratorio);
    }

    public void setCrearLaboratorioGrupo(Laboratorios laboratorio) {
        this.laboratoriosDao.setCrearLaboratorioGrupo(laboratorio);
    }

    public void setCrearLaboratorioGrupoDet(Laboratorios laboratorio) {
        this.laboratoriosDao.setCrearLaboratorioGrupoDet(laboratorio);
    }

    public void setModificarLaboratorio(Laboratorios laboratorio) {
        this.laboratoriosDao.setModificarLaboratorio(laboratorio);
    }

    public void setEliminarLaboratorio(Laboratorios laboratorio) {
        this.laboratoriosDao.setEliminarLaboratorio(laboratorio);
    }

    public void setEliminarLaboratorioGrupo(Laboratorios laboratorio) {
        this.laboratoriosDao.setEliminarLaboratorioGrupo(laboratorio);
    }

    public void setEliminarLaboratorioGrupoDet(Laboratorios laboratorio) {
        this.laboratoriosDao.setEliminarLaboratorioGrupoDet(laboratorio);
    }
    //Costos

    public List getReporteCobro(Costos costo) {
        return this.costosDao.getReporteCobro(costo);
    }

    public List getListarCostos(Costos costo) {
        return this.costosDao.getListarCostos(costo);
    }

    public List getListarCostosLabSumi(Costos costo) {
        return this.costosDao.getListarCostosLabSumi(costo);
    }

    public List getListarCostosLabora(Costos costo) {
        return this.costosDao.getListarCostosLabora(costo);
    }

    public List getListarLabosMedicoConf(Costos costo) {
        return this.costosDao.getListarLabosMedicoConf(costo);
    }

    public List getListarLabosMedico(Costos costo) {
        return this.costosDao.getListarLabosMedico(costo);
    }

    public List getListarTodos(Costos costo) {
        return this.costosDao.getListarTodos(costo);
    }

    public List getListarNombreCosto(Costos costo) {
        return this.costosDao.getListarNombreCosto(costo);
    }

    public List getListarCostosRx(Costos costo) {
        return this.costosDao.getListarCostosRx(costo);
    }

    public List getListarCostosEco(Costos costo) {
        return this.costosDao.getListarCostosEco(costo);
    }

    public List getListarNombreCosto22(Costos costo) {
        return this.costosDao.getListarNombreCosto22(costo);
    }

    public Costos getDatosCosto(Costos costo) {
        return this.costosDao.getDatosCosto(costo);
    }

    public void setCrearCosto(Costos costo) {
        this.costosDao.setCrearCosto(costo);
    }

    public void setModificarCosto(Costos costo) {
        this.costosDao.setModificarCosto(costo);
    }

    public void setEliminarCosto(Costos costo) {
        this.costosDao.setEliminarCosto(costo);
    }

    public List getListarCostosRubro(Costos costo) {
        return this.costosDao.getListarCostosRubro(costo);
    }

    public List getListarCostosRubroFact(Costos costo) {
        return this.costosDao.getListarCostosRubroFact(costo);
    }

    public List getListaRubro() {
        return this.costosDao.getListaRubro();
    }

    //Camas
    public List getListarCamas(Camas cama) {
        return this.camasDao.getListarCamas(cama);
    }

    public List getListarCamasTotal(Camas cama) {
        return this.camasDao.getListarCamasTotal(cama);
    }

    public List getListarCamaUnit(Camas cama) {
        return this.camasDao.getListarCamaUnit(cama);
    }

    public List getListarCamaPiso(Camas cama) {
        return this.camasDao.getListarCamaPiso(cama);
    }

    public Camas getDatosCama(int id_cama) {
        return this.camasDao.getDatosCama(id_cama);
    }

    public void setCrearCama(Camas cama) {
        this.camasDao.setCrearCama(cama);
    }

    public void setModificarCama(Camas cama) {
        this.camasDao.setModificarCama(cama);
    }

    public void setModificarCamaVacia(Camas cama) {
        this.camasDao.setModificarCamaVacia(cama);
    }

    public void setEliminarCama(Camas cama) {
        this.camasDao.setEliminarCama(cama);
    }

    public List getListarCamasSala(Camas cama) {
        return this.camasDao.getListarCamasSala(cama);
    }

    public List getListarCamasEstado(Camas cama) {
        return this.camasDao.getListarCamasEstado(cama);
    }

    //Sexos
    public List getListarSexos() {
        return this.sexosDao.getListarSexos();
    }

    public Sexos getDatosSexo(int id_sexo) {
        return this.sexosDao.getDatosSexo(id_sexo);
    }

    public void setCrearSexo(Sexos sexo) {
        this.sexosDao.setCrearSexo(sexo);
    }

    public void setModificarSexo(Sexos sexo) {
        this.sexosDao.setModificarSexo(sexo);
    }

    public void setEliminarSexo(Sexos sexo) {
        this.sexosDao.setEliminarSexo(sexo);
    }

    ///Clientes
    public List getListarClientes(Clieentes cliente) {
        return this.clieentesDao.getListarClientes(cliente);
    }

    public Clieentes getDatosCliente(Clieentes cliente) {
        return this.clieentesDao.getDatosCliente(cliente);
    }

    public void setCrearCliente(Clieentes cliente) {
        this.clieentesDao.setCrearCliente(cliente);
    }

    public void setModificarCliente(Clieentes cliente) {
        this.clieentesDao.setModificarCliente(cliente);
    }

    public void setEliminarCliente(Clieentes cliente) {
        this.clieentesDao.setEliminarCliente(cliente);
    }

    //Proveedores
    public List getListarProveedores(Proveedores proveedor) {
        return this.proveedoresDao.getListarProveedores(proveedor);
    }

    public Proveedores getDatosProveedor(Proveedores proveedor) {
        return this.proveedoresDao.getDatosProveedor(proveedor);
    }

    public void setCrearProveedor(Proveedores proveedor) {
        this.proveedoresDao.setCrearProveedor(proveedor);
    }

    public void setModificarProveedor(Proveedores proveedor) {
        this.proveedoresDao.setModificarProveedor(proveedor);
    }

    public void setEliminarProveedor(Proveedores proveedor) {
        this.proveedoresDao.setEliminarProveedor(proveedor);
    }

    //Parentescos
    public List getListarParentescos() {
        return this.parentescosDao.getListarParentescos();
    }

    public Parentescos getDatosParentesco(int id_parentesco) {
        return this.parentescosDao.getDatosParentesco(id_parentesco);
    }

    public void setCrearParentesco(Parentescos parentesco) {
        this.parentescosDao.setCrearParentesco(parentesco);
    }

    public void setModificarParentesco(Parentescos parentesco) {
        this.parentescosDao.setModificarParentesco(parentesco);
    }

    public void setEliminarParentesco(Parentescos parentesco) {
        this.parentescosDao.setEliminarParentesco(parentesco);
    }

    //Rubros
    public List getListarRubros(Rubros rubro) {
        return this.rubrosDao.getListarRubros(rubro);
    }

    public List getListarRubrosT(Rubros rubro) {
        return this.rubrosDao.getListarRubrosT(rubro);
    }

    public Rubros getDatosRubro(int id_rubro) {
        return this.rubrosDao.getDatosRubro(id_rubro);
    }

    public void setCrearRubro(Rubros rubro) {
        this.rubrosDao.setCrearRubro(rubro);
    }

    public void setModificarRubro(Rubros rubro) {
        this.rubrosDao.setModificarRubro(rubro);
    }

    public void setEliminarRubro(Rubros rubro) {
        this.rubrosDao.setEliminarRubro(rubro);
    }

    //Salas
    public List getListarPisosTotal(Salas sala) {
        return this.salasDao.getListarPisosTotal(sala);
    }

    public List getListarPisos(Salas sala) {
        return this.salasDao.getListarPisos(sala);
    }

    public List getListarSalas() {
        return this.salasDao.getListarSalas();
    }

    public List getListarSalasLibres(Salas sala) {
        return this.salasDao.getListarSalasLibres(sala);
    }

    public List getListarSalaPiso(Salas sala) {
        return this.salasDao.getListarSalaPiso(sala);
    }

    public List getListarSalaPisoTotal(Salas sala) {
        return this.salasDao.getListarSalaPisoTotal(sala);
    }

    public Salas getDatosSala(int id_sala) {
        return this.salasDao.getDatosSala(id_sala);
    }

    public void setCrearSala(Salas sala) {
        this.salasDao.setCrearSala(sala);
    }

    public void setModificarSala(Salas sala) {
        this.salasDao.setModificarSala(sala);
    }

    public void setEliminarSala(Salas sala) {
        this.salasDao.setEliminarSala(sala);
    }

    //Seguros
    public List getListarSeguros(String id_estado) {
        return this.segurosDao.getListarSeguros(id_estado);
    }

    public Seguros getDatosSeguro(int id_seguro) {
        return this.segurosDao.getDatosSeguro(id_seguro);
    }

    public void setCrearSeguro(Seguros seguro) {
        this.segurosDao.setCrearSeguro(seguro);
    }

    public void setModificarSeguro(Seguros seguro) {
        this.segurosDao.setModificarSeguro(seguro);
    }

    public void setEliminarSeguro(Seguros seguro) {
        this.segurosDao.setEliminarSeguro(seguro);
    }

    //Empresas
    public List getListarEmpresas(String id_estado) {
        return this.empresasDao.getListarEmpresas(id_estado);
    }

    public List getListarEmpresa2(Empresas empresa) {
        return this.empresasDao.getListarEmpresa2(empresa);
    }

    public List getListarEmpresa3(Empresas empresa) {
        return this.empresasDao.getListarEmpresa3(empresa);
    }

    public List getListarEmpresaCaja(Empresas empresa) {
        return this.empresasDao.getListarEmpresaCaja(empresa);
    }

    public List getListaEmpresa(Empresas empresa) {
        return this.empresasDao.getListaEmpresa(empresa);
    }

    public List getListaEmpresaCod(Empresas empresa) {
        return this.empresasDao.getListaEmpresaCod(empresa);
    }

    public List getListaEmpEmpresa(Empresas empresa) {
        return this.empresasDao.getListaEmpEmpresa(empresa);
    }

    public List getListaEmpEmpresaCod(Empresas empresa) {
        return this.empresasDao.getListaEmpEmpresaCod(empresa);
    }

    public Empresas getDatosEmpresa(int id_empresa) {
        return this.empresasDao.getDatosEmpresa(id_empresa);
    }

    public void setCrearEmpresa(Empresas empresa) {
        this.empresasDao.setCrearEmpresa(empresa);
    }

    public void setModificarEmpresa(Empresas empresa) {
        this.empresasDao.setModificarEmpresa(empresa);
    }

    public void setEliminarEmpresa(Empresas empresa) {
        this.empresasDao.setEliminarEmpresa(empresa);
    }

    //Documentos
    public List getListarDocumentos() {
        return this.documentosDao.getListarDocumentos();
    }

    public Documentos getDatosDocumento(int id_documento) {
        return this.documentosDao.getDatosDocumento(id_documento);
    }

    public void setCrearDocumento(Documentos documento) {
        this.documentosDao.setCrearDocumento(documento);
    }

    public void setModificarDocumento(Documentos documento) {
        this.documentosDao.setModificarDocumento(documento);
    }

    public void setEliminarDocumento(Documentos documento) {
        this.documentosDao.setEliminarDocumento(documento);
    }
    //Escolaridades

    public List getListarEscolaridades() {
        return this.escolaridadesDao.getListarEscolaridades();
    }

    public Escolaridades getDatosEscolaridad(int id_escolaridad) {
        return this.escolaridadesDao.getDatosEscolaridad(id_escolaridad);
    }

    public void setCrearEscolaridad(Escolaridades escolaridad) {
        this.escolaridadesDao.setCrearEscolaridad(escolaridad);
    }

    public void setModificarEscolaridad(Escolaridades escolaridad) {
        this.escolaridadesDao.setModificarEscolaridad(escolaridad);
    }

    public void setEliminarEscolaridad(Escolaridades escolaridad) {
        this.escolaridadesDao.setEliminarEscolaridad(escolaridad);
    }

    // consultorios
    public List getListarConsultorios(Consultorios a) {
        return this.consultoriosDao.getListarConsultorios(a);
    }
    
    public List getListarConsultoriosInter(Consultorios a) {
        return this.consultoriosDao.getListarConsultoriosInter(a);
    }
    
    public List getListarConsultoriosEmerg(Consultorios a) {
        return this.consultoriosDao.getListarConsultoriosEmerg(a);
    }

    public List getListarConsultoriosTransf(Consultorios a) {
        return this.consultoriosDao.getListarConsultoriosTransf(a);
    }

    public List getListarConsultoriosUrgen(Consultorios a) {
        return this.consultoriosDao.getListarConsultoriosUrgen(a);
    }

    public List getListarCentroCNS(Consultorios a) {
        return this.consultoriosDao.getListarCentroCNS(a);
    }

    public List getListarTipoRecetaCNS(Consultorios a) {
        return this.consultoriosDao.getListarTipoRecetaCNS(a);
    }

    public List getListarCentroCNSFar(Consultorios a) {
        return this.consultoriosDao.getListarCentroCNSFar(a);
    }

    public List getListarServicioCNS(Consultorios a) {
        return this.consultoriosDao.getListarServicioCNS(a);
    }

    public List getListarServicioCNSFar(Consultorios a) {
        return this.consultoriosDao.getListarServicioCNSFar(a);
    }

    public List getListarServicioCNS1(Consultorios a) {
        return this.consultoriosDao.getListarServicioCNS1(a);
    }

    public List getListarServicioCNS2(Consultorios a) {
        return this.consultoriosDao.getListarServicioCNS2(a);
    }

    public List getListarCodCNS(Consultorios a) {
        return this.consultoriosDao.getListarCodCNS(a);
    }

    public List getListarConsultoriosGen(Consultorios a) {
        return this.consultoriosDao.getListarConsultoriosGen(a);
    }

    public Consultorios getDatosConsultorio(int id_cargo) {
        return this.consultoriosDao.getDatosConsultorio(id_cargo);
    }

    public void setCrearConsultorio(Consultorios a) {
        this.consultoriosDao.setCrearConsultorio(a);
    }

    public void setModificarConsultorio(Consultorios a) {
        this.consultoriosDao.setModificarConsultorio(a);
    }

    public void setEliminarConsultorio(Consultorios a) {
        this.consultoriosDao.setEliminarConsultorio(a);
    }

    // cargos
    public List getListarCargos() {
        return this.consultoriosDao.getListarCargos();
    }

    public Consultorios getDatosCargo(int id_cargo) {
        return this.consultoriosDao.getDatosCargo(id_cargo);
    }

    public void setCrearCargo(Consultorios a) {
        this.consultoriosDao.setCrearCargo(a);
    }

    public void setModificarCargo(Consultorios a) {
        this.consultoriosDao.setModificarCargo(a);
    }

    public void setEliminarCargo(Consultorios a) {
        this.consultoriosDao.setEliminarCargo(a);
    }

    //Departamentos
    public List getListarDepartamentos() {
        return this.departamentosDao.getListarDepartamentos();
    }

    public Departamentos getDatosDepartamento(int id_departamento) {
        return this.departamentosDao.getDatosDepartamento(id_departamento);
    }

    public void setCrearDepartamento(Departamentos departamento) {
        this.departamentosDao.setCrearDepartamento(departamento);
    }

    public void setModificarDepartamento(Departamentos departamento) {
        this.departamentosDao.setModificarDepartamento(departamento);
    }

    public void setEliminarDepartamento(Departamentos departamento) {
        this.departamentosDao.setEliminarDepartamento(departamento);
    }

    public List getListarPaisDep(int id_pais) {
        return this.departamentosDao.getListarPaisDep(id_pais);
    }
    //Eciviles

    public List getListarEciviles() {
        return this.ecivilesDao.getListarEciviles();
    }

    public Eciviles getDatosEcivil(int id_ecivil) {
        return this.ecivilesDao.getDatosEcivil(id_ecivil);
    }

    public void setCrearEcivil(Eciviles ecivil) {
        this.ecivilesDao.setCrearEcivil(ecivil);
    }

    public void setModificarEcivil(Eciviles ecivil) {
        this.ecivilesDao.setModificarEcivil(ecivil);
    }

    public void setEliminarEcivil(Eciviles ecivil) {
        this.ecivilesDao.setEliminarEcivil(ecivil);
    }

    //Provincias
    public List getListarProvincias() {
        return this.provinciasDao.getListarProvincias();
    }

    public Provincias getDatosProvincia(int id_provincia) {
        return this.provinciasDao.getDatosProvincia(id_provincia);
    }

    public void setCrearProvincia(Provincias provincia) {
        this.provinciasDao.setCrearProvincia(provincia);
    }

    public void setModificarProvincia(Provincias provincia) {
        this.provinciasDao.setModificarProvincia(provincia);
    }

    public void setEliminarProvincia(Provincias provincia) {
        this.provinciasDao.setEliminarProvincia(provincia);
    }

    public List getListarPaisDepProv(Provincias provincia) {
        return this.provinciasDao.getListarPaisDepProv(provincia);
    }

    //Localidades  
    public List getListarPaisDepProvLoc(Localidades dato) {
        return this.localidadesDao.getListarPaisDepProvLoc(dato);
    }

    public List getListarMuniRed(Localidades dato) {
        return this.localidadesDao.getListarMuniRed(dato);
    }

    public List getListarRed(Localidades dato) {
        return this.localidadesDao.getListarRed(dato);
    }

    public List getListarEstab(Localidades dato) {
        return this.localidadesDao.getListarEstab(dato);
    }

    public List getListarEstabRef(Localidades dato) {
        return this.localidadesDao.getListarEstabRef(dato);
    }

    public List getEstabTransCns(Localidades dato) {
        return this.localidadesDao.getEstabTransCns(dato);
    }

    public List getEstabHabiles(Localidades dato) {
        return this.localidadesDao.getEstabHabiles(dato);
    }

    public List getListarEstabGen(Localidades dato) {
        return this.localidadesDao.getListarEstabGen(dato);
    }

    public List getListarEsta(Localidades dato) {
        return this.localidadesDao.getListarEsta(dato);
    }

    public List getListarEstaUsua(Localidades dato) {
        return this.localidadesDao.getListarEstaUsua(dato);
    }

    public Localidades getDatosLocalidad(Localidades dato) {
        return this.localidadesDao.getDatosLocalidad(dato);
    }

    public Localidades getDatosEstable(Localidades dato) {
        return this.localidadesDao.getDatosEstable(dato);
    }

    public void setModificarEstab(Localidades dato) {
        this.localidadesDao.setModificarEstab(dato);
    }
    //Pacientes

    public List getDatosFaltaHcl(Pacientes dato) {
        return this.pacientesDao.getDatosFaltaHcl(dato);
    }

    public List getDatosFaltaHcl2(Pacientes dato) {
        return this.pacientesDao.getDatosFaltaHcl2(dato);
    }

    public List getReporteAfiliadosSumi(Pacientes dato) {
        return this.pacientesDao.getReporteAfiliadosSumi(dato);
    }

    public List getReporteAfiliados(Pacientes dato) {
        return this.pacientesDao.getReporteAfiliados(dato);
    }

    public List getOtrosAfiliados(Pacientes dato) {
        return this.pacientesDao.getOtrosAfiliados(dato);
    }

    public List getDatosAfiliados(Pacientes dato) {
        return this.pacientesDao.getDatosAfiliados(dato);
    }

    public List getDatosAfiliadosAct(Pacientes dato) {
        return this.pacientesDao.getDatosAfiliadosAct(dato);
    }

    public List getDatosAfiliadosNada(Pacientes dato) {
        return this.pacientesDao.getDatosAfiliadosNada(dato);
    }

    public void setModificarPacienteSumi(Pacientes paciente) {
        this.pacientesDao.setModificarPacienteSumi(paciente);
    }
    
    public void setModificarPacienteConsul(Pacientes paciente) {
        this.pacientesDao.setModificarPacienteConsul(paciente);
    }

    public void setModificarPacienteSumiEmpresa(Pacientes paciente) {
        this.pacientesDao.setModificarPacienteSumiEmpresa(paciente);
    }

    public void setModificarPedidos(Pacientes paciente) {
        this.pacientesDao.setModificarPedidos(paciente);
    }

    public void setModificarPedidoFactura(Pacientes paciente) {
        this.pacientesDao.setModificarPedidoFactura(paciente);
    }

    public void setUnirHCL(Pacientes paciente) {
        this.pacientesDao.setUnirHCL(paciente);
    }

    public void setCrearPacienteSumi(Pacientes paciente) {
        this.pacientesDao.setCrearPacienteSumi(paciente);
    }
    
    public void setCrearPacienteCaja(Pacientes paciente) {
        this.pacientesDao.setCrearPacienteCaja(paciente);
    }

    public void setCrearPacienteTicket(Pacientes paciente) {
        this.pacientesDao.setCrearPacienteTicket(paciente);
    }

    public String getNumRegistro(int id_paciente) {
        return this.pacientesDao.getNumRegistro(id_paciente);
    }
    
    public String getNumProforma(Pacientes paciente){
       return this.pacientesDao.getNumProforma(paciente);
    }

    public String getNumAsegurado(int id_paciente) {
        return this.pacientesDao.getNumAsegurado(id_paciente);
    }

    public String getNumSumi(String id_estado, int cod_esta) {
        return this.pacientesDao.getNumSumi(id_estado, cod_esta);
    }

    public List getListarPacientes(Pacientes paciente) {
        return this.pacientesDao.getListarPacientes(paciente);
    }
    
    public List getListarPacientesFichas(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesFichas(paciente);
    }

    public List getListarPacientesPPagos(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesPPagos(paciente);
    }

    public List getListarPacientesHisto(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesHisto(paciente);
    }

    public List getListarPacientesHistoNombre(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesHistoNombre(paciente);
    }

    public List getListarPacientesHistoCie10(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesHistoCie10(paciente);
    }

    public List getListarPacientesNombreCns(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesNombreCns(paciente);
    }

    public List getListarPacientesCnsUnico(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesCnsUnico(paciente);
    }

    public List getListarPacientesSeguro(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesSeguro(paciente);
    }

    public List getListarPacientesPrivNom(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesPrivNom(paciente);
    }

    public List getListarPacientesBio(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesBio(paciente);
    }

    public List getListarPacientesAfi(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesAfi(paciente);
    }

    public List getListarPacientesCI(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesCI(paciente);
    }

    public List getListarPacientesGrafica(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesGrafica(paciente);
    }

    public List getListarPacientesAfiR(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesAfiR(paciente);
    }

    public List getListarPacientesFN(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesFN(paciente);
    }

    public List getListarPacientesFNCns(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesFNCns(paciente);
    }
    
    public List getListarPacAfiliados(Pacientes paciente) {
        return this.pacientesDao.getListarPacAfiliados(paciente);
    }

    public List getListarPacientesHC(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesHC(paciente);
    }

    public List getListarPacientesEmp(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesEmp(paciente);
    }

    public List getListarPacientesFarma(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesFarma(paciente);
    }

    public List getListarPacienteFar(Pacientes paciente) {
        return this.pacientesDao.getListarPacienteFar(paciente);
    }

    public List getListarPacientesCaja(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesCaja(paciente);
    }

    public List getListarPacientesVigencia(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesVigencia(paciente);
    }

    public List getListarPacientesVigenciaBuscar(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesVigenciaBuscar(paciente);
    }

    public List getListarPacientesVigenciaSolo(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesVigenciaSolo(paciente);
    }

    public List getListarPacientesHCCns(Pacientes paciente) {
        return this.pacientesDao.getListarPacientesHCCns(paciente);
    }

    public Pacientes getDatosPaciente(int id_paciente) {
        return this.pacientesDao.getDatosPaciente(id_paciente);
    }

    public Pacientes getDatosPacienteJefe(int id_paciente) {
        return this.pacientesDao.getDatosPacienteJefe(id_paciente);
    }

    public Pacientes getDatosPacienteCI(int id_paciente) {
        return this.pacientesDao.getDatosPacienteCI(id_paciente);
    }

    public List getDatosPacienteInt(Pacientes paciente) {
        return this.pacientesDao.getDatosPacienteInt(paciente);
    }

    public void setCrearPaciente(Pacientes paciente) {
        this.pacientesDao.setCrearPaciente(paciente);
    }

    public void setCrearVigencia(Pacientes paciente) {
        this.pacientesDao.setCrearVigencia(paciente);
    }

    public void setModificarPaciente(Pacientes paciente) {
        this.pacientesDao.setModificarPaciente(paciente);
    }

    public void setModificarVigencia(Pacientes paciente) {
        this.pacientesDao.setModificarVigencia(paciente);
    }

    public void setModificaPacienteEmb(Pacientes paciente) {
        this.pacientesDao.setModificaPacienteEmb(paciente);
    }

    public void setModificaPacienteSangre(Pacientes paciente) {
        this.pacientesDao.setModificaPacienteSangre(paciente);
    }

    public void setModificaPacienteFact(Pacientes paciente) {
        this.pacientesDao.setModificaPacienteFact(paciente);
    }

    public void setModificaPacienteNit(Pacientes paciente) {
        this.pacientesDao.setModificaPacienteNit(paciente);
    }

    public void setEliminarPaciente(Pacientes paciente) {
        this.pacientesDao.setEliminarPaciente(paciente);
    }

    public void setEliminarPacienteSeguro(Pacientes paciente) {
        this.pacientesDao.setEliminarPacienteSeguro(paciente);
    }
    
    public void setEliminarPacienteSscp(Pacientes paciente) {
        this.pacientesDao.setEliminarPacienteSscp(paciente);
    }

    public int getNumHcl() {
        return this.pacientesDao.getNumHcl();
    }
//  public int getNum(int id_paciente) {
    //   return this.historialesDao.getNum();
    // }   

    public void setCrearPeedido(Pacientes paciente) {
        this.pacientesDao.setCrearPeedido(paciente);
    }
    
    public void setCrearProforma (Pacientes paciente) {
       this.pacientesDao.setCrearProforma(paciente);
    } 
    public void setCrearFactura(Pacientes paciente) {
        this.pacientesDao.setCrearFactura(paciente);
    }

    public void setCrearPedidoNutri(Pacientes paciente) {
        this.pacientesDao.setCrearPedidoNutri(paciente);
    }

    public void setCrearFacturaInter(Pacientes paciente) {
        this.pacientesDao.setCrearFacturaInter(paciente);
    }

    public void setCrearFacturaInter2(Pacientes paciente) {
        this.pacientesDao.setCrearFacturaInter2(paciente);
    }

    public void setEliminarPedido(Pacientes paciente) {
        this.pacientesDao.setEliminarPedido(paciente);
    }
    
    public void setEliminarPedidoProf(Pacientes paciente) {
        this.pacientesDao.setEliminarPedidoProf(paciente);
    }

    public int getNumReceta(Pacientes paciente) {
        return this.pacientesDao.getNumReceta(paciente);
    }

    public int getNumPedido(Pacientes paciente) {
        return this.pacientesDao.getNumPedido(paciente);
    }

    public int getNumPedidoProf(Pacientes paciente) {
        return this.pacientesDao.getNumPedidoProf(paciente);
    }

    public Pacientes getDatosPedido(Pacientes paciente){
        return this.pacientesDao.getDatosPedido(paciente);
    }  
    
    public Pacientes getDatosPedidoProf(Pacientes paciente){
        return this.pacientesDao.getDatosPedidoProf(paciente);
    }  

    public Pacientes getDatosPedidoHisto(Pacientes paciente) {
        return this.pacientesDao.getDatosPedidoHisto(paciente);
    }

    public Pacientes getDatosPedidoHis(Pacientes paciente) {
        return this.pacientesDao.getDatosPedidoHis(paciente);
    }

    public Pacientes getDatosFactura(Pacientes paciente) {
        return this.pacientesDao.getDatosFactura(paciente);
    }

    public Pacientes getDatosPedidoKardex(Pacientes paciente) {
        return this.pacientesDao.getDatosPedidoKardex(paciente);
    }

    public Pacientes getDatosPedidoI(Pacientes paciente) {
        return this.pacientesDao.getDatosPedidoI(paciente);
    }

    public void setModificarPedido(Pacientes paciente) {
        this.pacientesDao.setModificarPedido(paciente);
    }

    public void setModificarPedidoPPago(Pacientes paciente) {
        this.pacientesDao.setModificarPedidoPPago(paciente);
    }

    public void setModificarPedidoElimPP(Pacientes paciente) {
        this.pacientesDao.setModificarPedidoElimPP(paciente);
    }

    public void setModificarPedidoSinFecha(Pacientes paciente) {
        this.pacientesDao.setModificarPedidoSinFecha(paciente);
    }

    public void setModificarPedidoAnt(Pacientes paciente) {
        this.pacientesDao.setModificarPedidoAnt(paciente);
    }
    
    public void setModificarPedidoProf(Pacientes paciente) {
      this.pacientesDao.setModificarPedidoProf(paciente);
    }  
    
    public List getListarCobroRubro(Pacientes paciente) {
        return this.pacientesDao.getListarCobroRubro(paciente);
    }

    public List getListarCobroRubroEnfer(Pacientes paciente) {
        return this.pacientesDao.getListarCobroRubroEnfer(paciente);
    }

    public List getListarCobroRubroOdon(Pacientes paciente) {
        return this.pacientesDao.getListarCobroRubroOdon(paciente);
    }

    public List getListarCobroEnfer(Pacientes paciente) {
        return this.pacientesDao.getListarCobroEnfer(paciente);
    }

    public List getListarCobroRubroFar(Pacientes paciente) {
        return this.pacientesDao.getListarCobroRubroFar(paciente);
    }
    
    public List getListarProformas(Pacientes paciente) {
      return this.pacientesDao.getListarProformas(paciente);
    }
    
    public List getListarProformasNom(Pacientes paciente) {
      return this.pacientesDao.getListarProformasNom(paciente);
    }
    
    public List getReporteCobroFarm(Pacientes dato) {
        return this.pacientesDao.getReporteCobroFarm(dato);
    }

    public List getReporteCobroFarmGen(Pacientes dato) {
        return this.pacientesDao.getReporteCobroFarmGen(dato);
    }

    public List getListaMedEntregados(Pacientes dato) {
        return this.pacientesDao.getListaMedEntregados(dato);
    }

    public List getListaMedEntregadosI(Pacientes dato) {
        return this.pacientesDao.getListaMedEntregadosI(dato);
    }

    public List getListaMedKEntregados(Pacientes dato) {
        return this.pacientesDao.getListaMedKEntregados(dato);
    }

    public List getListaMedKEntregadosEnfer(Pacientes dato) {
        return this.pacientesDao.getListaMedKEntregadosEnfer(dato);
    }

    public List getListaMedKEntregadosSPAM(Pacientes dato) {
        return this.pacientesDao.getListaMedKEntregadosSPAM(dato);
    }

    public List getListaMedKEntregadosFecha(Pacientes dato) {
        return this.pacientesDao.getListaMedKEntregadosFecha(dato);
    }

    public List getListaMedKEntregadosUni(Pacientes dato) {
        return this.pacientesDao.getListaMedKEntregadosUni(dato);
    }

    public List getListaCobroTotal(Pacientes dato) {
        return this.pacientesDao.getListaCobroTotal(dato);
    }

    public List getListaMedRecibidos(Pacientes dato) {
        return this.pacientesDao.getListaMedRecibidos(dato);
    }

    public List getListaMedRecibidosHcl(Pacientes dato) {
        return this.pacientesDao.getListaMedRecibidosHcl(dato);
    }

    public List getListaMedRecibidosTipo(Pacientes dato) {
        return this.pacientesDao.getListaMedRecibidosTipo(dato);
    }

    public List getListaMedRecibidos2(Pacientes dato) {
        return this.pacientesDao.getListaMedRecibidos2(dato);
    }

    public List getListaMedRecibidosAlmaGen(Pacientes dato) {
        return this.pacientesDao.getListaMedRecibidosAlmaGen(dato);
    }

    public List getReporteEconomico(Pacientes dato) {
        return this.pacientesDao.getReporteEconomico(dato);
    }

    public List getReporteEconomicoGenFac(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoGenFac(dato);
    }

    public List getReporteEconomicoFac(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoFac(dato);
    }

    public List getReporteEconomicoPPago(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoPPago(dato);
    }

    public List getReporteEconomicoGenFacRub(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoGenFacRub(dato);
    }

    public List getReporteFacturasEmitidasHcl(Pacientes dato) {
        return this.pacientesDao.getReporteFacturasEmitidasHcl(dato);
    }

    public List getDatosPedidoRubro(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubro(dato);
    }

    public List getDatosPedidoRubrosDet(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubrosDet(dato);
    }

    public List getDatosPedidoRubrosDetDeu(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubrosDetDeu(dato);
    }

    public List getDatosPedidoDetRubro(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoDetRubro(dato);
    }

    public List getDatosPedidoRubroDental(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubroDental(dato);
    }

    public List getDatosPedidoRubroGeneral(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubroGeneral(dato);
    }

    public List getDatosPedidoRubroGeneralSf(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubroGeneralSf(dato);
    }

    public List getDatosPedidoRubrosDetSf(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubrosDetSf(dato);
    }

    public List getDatosPedidoRubrosDetSfPag(Pacientes dato) {
        return this.pacientesDao.getDatosPedidoRubrosDetSfPag(dato);
    }

    public List getReporteEconomicoRubro(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoRubro(dato);
    }

    public List getReporteEconomicoGenRubro(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoGenRubro(dato);
    }

    public List getReporteEconomicoGenRubroDental(Pacientes dato) {
        return this.pacientesDao.getReporteEconomicoGenRubroDental(dato);
    }

    public List getListaMedKardexEntregados(Recetas dato) {
        return this.recetasDao.getListaMedKardexEntregados(dato);
    }

    public List getKardexRestringido(Recetas dato) {
        return this.recetasDao.getKardexRestringido(dato);
    }

    public List getKardexFarmaciaCNS(Recetas dato) {
        return this.recetasDao.getKardexFarmaciaCNS(dato);
    }

    public List getKardexUnitario(Recetas dato) {
        return this.recetasDao.getKardexUnitario(dato);
    }

    public List getListaMedKardexXPedido(Recetas dato) {
        return this.recetasDao.getListaMedKardexXPedido(dato);
    }

    public List getListaKardexPaciente(Recetas dato) {
        return this.recetasDao.getListaKardexPaciente(dato);
    }

    public List getKardexUsuario(Recetas dato) {
        return this.recetasDao.getKardexUsuario(dato);
    }

    public List getKardexRestringido3(Recetas dato) {
        return this.recetasDao.getKardexRestringido3(dato);
    }

    public List getKardexRestringido4(Recetas dato) {
        return this.recetasDao.getKardexRestringido4(dato);
    }

    public List getKardexRestringido5(Recetas dato) {
        return this.recetasDao.getKardexRestringido5(dato);
    }

    public List getKardexRestringido6(Recetas dato) {
        return this.recetasDao.getKardexRestringido6(dato);
    }

    public List getKardexRestringido7(Recetas dato) {
        return this.recetasDao.getKardexRestringido7(dato);
    }

    public List getKardexFarmaciaCNS_SC(Recetas dato) {
        return this.recetasDao.getKardexFarmaciaCNS_SC(dato);
    }

    public List getKardexFarmaciaCNSDet_SC(Recetas dato) {
        return this.recetasDao.getKardexFarmaciaCNSDet_SC(dato);
    }

    public List getKardexFarmaciaCNSDet(Recetas dato) {
        return this.recetasDao.getKardexFarmaciaCNSDet(dato);
    }

    public List getKardexFarmaciaCNSDetFar(Recetas dato) {
        return this.recetasDao.getKardexFarmaciaCNSDetFar(dato);
    }

    public List getListaKardexPacienteIndi(Recetas dato) {
        return this.recetasDao.getListaKardexPacienteIndi(dato);
    }

    public List getKardexRemision(Recetas dato) {
        return this.recetasDao.getKardexRemision(dato);
    }

    public List getKardexResumenDispensa(Recetas dato) {
        return this.recetasDao.getKardexResumenDispensa(dato);
    }

    public List getKardexResumenDispensaDia(Recetas dato) {
        return this.recetasDao.getKardexResumenDispensaDia(dato);
    }

    public List getKardexExport(Recetas dato) {
        return this.recetasDao.getKardexExport(dato);
    }

    public List getRevercionExcel(Recetas dato) {
        return this.recetasDao.getRevercionExcel(dato);
    }

    public List getListaRecetaGen(Recetas dato) {
        return this.recetasDao.getListaRecetaGen(dato);
    }

    public List getResumenXmedico(Recetas dato) {
        return this.recetasDao.getResumenXmedico(dato);
    }

    public List getResumenXespecialidad(Recetas dato) {
        return this.recetasDao.getResumenXespecialidad(dato);
    }

    public List getVencimientos(Recetas dato) {
        return this.recetasDao.getVencimientos(dato);
    }

    public List getResumenXservicio(Recetas dato) {
        return this.recetasDao.getResumenXservicio(dato);
    }

    public List getKardexResumenXMedica(Recetas dato) {
        return this.recetasDao.getKardexResumenXMedica(dato);
    }

    public List getKardexResumenXMedicaSaldo(Recetas dato) {
        return this.recetasDao.getKardexResumenXMedicaSaldo(dato);
    }

    public long getNumClaDoc(Pacientes dato) {
        return this.pacientesDao.getNumClaDoc(dato);
    }
    
    public long getNumClaDocProf(Pacientes dato) {
        return this.pacientesDao.getNumClaDocProf(dato);
    }

    // historiales
    public void setCrearReservacion(Historiales dato) {
        this.historialesDao.setCrearReservacion(dato);
    }
    
    public void setCrearReservacionFicha(Historiales dato) {
        this.historialesDao.setCrearReservacionFicha(dato);
    }

    public void setCrearInterconsulta(Historiales dato) {
        this.historialesDao.setCrearInterconsulta(dato);
    }

    public void setCrearInternado(Historiales dato) {
        this.historialesDao.setCrearInternado(dato);
    }

    public void setCrearInternado2(Historiales dato) {
        this.historialesDao.setCrearInternado2(dato);
    }

    public void setCrearInternadoEmerg(Historiales dato) {
        this.historialesDao.setCrearInternadoEmerg(dato);
    }

    public void setCrearMorbilidad(Historiales dato) {
        this.historialesDao.setCrearMorbilidad(dato);
    }

    public void setEliminarReserva(Historiales dato) {
        this.historialesDao.setEliminarReserva(dato);
    }

    public void setEliminarHistoria(Historiales dato) {
        this.historialesDao.setEliminarHistoria(dato);
    }

    public void setEliminarMorbilidad(Historiales dato) {
        this.historialesDao.setEliminarMorbilidad(dato);
    }

    public void setEliminarMorbilidadFarma(Historiales dato) {
        this.historialesDao.setEliminarMorbilidadFarma(dato);
    }

    public void setEliminaLab(Historiales dato) {
        this.historialesDao.setEliminaLab(dato);
    }

    public void setEliminaPedidoLab(Historiales dato) {
        this.historialesDao.setEliminaPedidoLab(dato);
    }

    public void setModificaLab(Historiales dato) {
        this.historialesDao.setModificaLab(dato);
    }

    public void setModificaVigencia(Historiales dato) {
        this.historialesDao.setModificaVigencia(dato);
    }

    public void setModificarPagoReserva(Historiales dato) {
        this.historialesDao.setModificarPagoReserva(dato);
    }

    public void setModificarMorbilidad(Historiales dato) {
        this.historialesDao.setModificarMorbilidad(dato);
    }

    public void setModificarInter(Historiales dato) {
        this.historialesDao.setModificarInter(dato);
    }

    public void setModificarInterFecha(Historiales dato) {
        this.historialesDao.setModificarInterFecha(dato);
    }

    public void setModificarInterReceta(Historiales dato) {
        this.historialesDao.setModificarInterReceta(dato);
    }

    public void setModificarInterDat(Historiales dato) {
        this.historialesDao.setModificarInterDat(dato);
    }

    public void setModificarInterDatHisto(Historiales dato) {
        this.historialesDao.setModificarInterDatHisto(dato);
    }

    public void setModificarInterAltaHisto(Historiales dato) {
        this.historialesDao.setModificarInterAltaHisto(dato);
    }

    public void setModificarSignosReserva(Historiales dato) {
        this.historialesDao.setModificarSignosReserva(dato);
    }

    public void setModificarReservaConsultorio(Historiales dato) {
        this.historialesDao.setModificarReservaConsultorio(dato);
    }

    public void setModificarReservaVigencia(Historiales dato) {
        this.historialesDao.setModificarReservaVigencia(dato);
    }

    public void setModificarReservaTotal(Historiales dato) {
        this.historialesDao.setModificarReservaTotal(dato);
    }

    public void setModificarEstadoHistorial(Historiales dato) {
        this.historialesDao.setModificarEstadoHistorial(dato);
    }

    public void setModificarInternado(Historiales dato) {
        this.historialesDao.setModificarInternado(dato);
    }

    public void setModificarInternadoFecha(Historiales dato) {
        this.historialesDao.setModificarInternadoFecha(dato);
    }

    public void setModificarEmergencias(Historiales dato) {
        this.historialesDao.setModificarEmergencias(dato);
    }

    public List getListarReservacionesCount(Historiales dato) {
        return this.historialesDao.getListarReservacionesCount(dato);
    }

    public List getListarReservacionesCountMed(Historiales dato) {
        return this.historialesDao.getListarReservacionesCountMed(dato);
    }

    public List getListarReservacionesAsig(Historiales dato) {
        return this.historialesDao.getListarReservacionesAsig(dato);
    }
    
    public List getListarReservacionesInternet(Historiales dato) {
        return this.historialesDao.getListarReservacionesInternet(dato);
    }

    public List getListarReserFichas(Historiales dato) {
        return this.historialesDao.getListarReserFichas(dato);
    }

    public List getListarReservaciones(Historiales dato) {
        return this.historialesDao.getListarReservaciones(dato);
    }

    public List getListarReservaciones1(Historiales dato) {
        return this.historialesDao.getListarReservaciones1(dato);
    }

    public List getListarReservacionesPersona(Historiales dato) {
        return this.historialesDao.getListarReservacionesPersona(dato);
    }

    public List getListarReservacionesCIE10(Historiales dato) {
        return this.historialesDao.getListarReservacionesCIE10(dato);
    }

    public List getListarReservacionesCIE10Consul(Historiales dato) {
        return this.historialesDao.getListarReservacionesCIE10Consul(dato);
    }

    public List getListarReservacionesCIE10ConsulE(Historiales dato) {
        return this.historialesDao.getListarReservacionesCIE10ConsulE(dato);
    }

    public List getListarReservacionesMedico(Historiales dato) {
        return this.historialesDao.getListarReservacionesMedico(dato);
    }

    public List getListarReservacionesConsul(Historiales dato) {
        return this.historialesDao.getListarReservacionesConsul(dato);
    }

    public List getListarReservacionesEstadFecha(Historiales dato) {
        return this.historialesDao.getListarReservacionesEstadFecha(dato);
    }

    public List getListarReservacionesHemo(Historiales dato) {
        return this.historialesDao.getListarReservacionesHemo(dato);
    }

    public List getListarReservacionesUcaLuo(Historiales dato) {
        return this.historialesDao.getListarReservacionesUcaLuo(dato);
    }

    public List getListarReservacionesUcaLuoF(Historiales dato) {
        return this.historialesDao.getListarReservacionesUcaLuoF(dato);
    }

    public List getListarReservacionesConsul2(Historiales dato) {
        return this.historialesDao.getListarReservacionesConsul2(dato);
    }

    public List getListarReservacionesResid(Historiales dato) {
        return this.historialesDao.getListarReservacionesResid(dato);
    }

    public List getListarReservacionesResidConsul(Historiales dato) {
        return this.historialesDao.getListarReservacionesResidConsul(dato);
    }

    public List getListarReservacionesResidConsulPerso(Historiales dato) {
        return this.historialesDao.getListarReservacionesResidConsulPerso(dato);
    }

    public List getListarReservacionesConsulPublico(Historiales dato) {
        return this.historialesDao.getListarReservacionesConsulPublico(dato);
    }

    public List getListarReservacionesCaja(Historiales dato) {
        return this.historialesDao.getListarReservacionesCaja(dato);
    }

    public List getListarReservacionesNombreCaja(Historiales dato) {
        return this.historialesDao.getListarReservacionesNombreCaja(dato);
    }

    public List getListarReservacionesConsulMedicoPublico(Historiales dato) {
        return this.historialesDao.getListarReservacionesConsulMedicoPublico(dato);
    }

    public List getListarVigencia(Historiales dato) {
        return this.historialesDao.getListarVigencia(dato);
    }

    public List getListarVigenciaMedico(Historiales dato) {
        return this.historialesDao.getListarVigenciaMedico(dato);
    }

    public List getListarVigenciaConsul(Historiales dato) {
        return this.historialesDao.getListarVigenciaConsul(dato);
    }

    public List getListarVigenciaFicha(Historiales dato) {
        return this.historialesDao.getListarVigenciaFicha(dato);
    }

    public List getListarVigenciaHab(Historiales dato) {
        return this.historialesDao.getListarVigenciaHab(dato);
    }

    public List getListarVigenciaHabCambioConsul(Historiales dato) {
        return this.historialesDao.getListarVigenciaHabCambioConsul(dato);
    }

    public List getListarCobroReserva(Historiales dato) {
        return this.historialesDao.getListarCobroReserva(dato);
    }

    public List getListarCobroReservaSignos(Historiales dato) {
        return this.historialesDao.getListarCobroReservaSignos(dato);
    }

    public List getListarCobroReservaUni(Historiales dato) {
        return this.historialesDao.getListarCobroReservaUni(dato);
    }

    public List getListarReservaSignosCNS(Historiales dato) {
        return this.historialesDao.getListarReservaSignosCNS(dato);
    }

    public List getListarReservaSignosCNS_SC(Historiales dato) {
        return this.historialesDao.getListarReservaSignosCNS_SC(dato);
    }

    public List getListarReservaSignosCNS_SCUrg(Historiales dato) {
        return this.historialesDao.getListarReservaSignosCNS_SCUrg(dato);
    }

    public List getListarAtendidos(Historiales dato) {
        return this.historialesDao.getListarAtendidos(dato);
    }

    public List getListarAtendidosCNS(Historiales dato) {
        return this.historialesDao.getListarAtendidosCNS(dato);
    }

    public List getListarAtendidosTot(Historiales dato) {
        return this.historialesDao.getListarAtendidosTot(dato);
    }

    public List getListarAtendidosFarNom(Historiales dato) {
        return this.historialesDao.getListarAtendidosFarNom(dato);
    }

    public List getListarAtendidosPend(Historiales dato) {
        return this.historialesDao.getListarAtendidosPend(dato);
    }

    public List getListarAtendidosEnf(Historiales dato) {
        return this.historialesDao.getListarAtendidosEnf(dato);
    }

    public List getListarAtendidosI(Historiales dato) {
        return this.historialesDao.getListarAtendidosI(dato);
    }

    public List getListarAtendidosITot(Historiales dato) {
        return this.historialesDao.getListarAtendidosITot(dato);
    }

    public List getListarAtendidosIFarNom(Historiales dato) {
        return this.historialesDao.getListarAtendidosIFarNom(dato);
    }

    public List getListarAtendidosICNS(Historiales dato) {
        return this.historialesDao.getListarAtendidosICNS(dato);
    }

    public int setRegistrarHistorial(Historiales dato) {
        return this.historialesDao.setRegistrarHistorial(dato);
    }

    public Historiales getDatosHistorial(Historiales dato) {
        return this.historialesDao.getDatosHistorial(dato);
    }

    public Historiales getDatosHistorialUlt(Historiales dato) {
        return this.historialesDao.getDatosHistorialUlt(dato);
    }

    public Historiales getDatosHistorialUltHisto(Historiales dato) {
        return this.historialesDao.getDatosHistorialUltHisto(dato);
    }

    public Historiales getDatosReserva(Historiales dato) {
        return this.historialesDao.getDatosReserva(dato);
    }

    public Historiales getDatosEmergencias(Historiales dato) {
        return this.historialesDao.getDatosEmergencias(dato);
    }

    public Historiales getDatosEmergenciaUltimo(Historiales dato) {
        return this.historialesDao.getDatosEmergenciaUltimo(dato);
    }

    public Historiales getDatosEmergencia(Historiales dato) {
        return this.historialesDao.getDatosEmergencia(dato);
    }

    public Historiales getDatosEmergenciaSolo(Historiales dato) {
        return this.historialesDao.getDatosEmergenciaSolo(dato);
    }

    public List getListarHistoria(Historiales dato) {
        return this.historialesDao.getListarHistoria(dato);
    }

    public List getListarHistoriaHoy(Historiales dato) {
        return this.historialesDao.getListarHistoriaHoy(dato);
    }

    public List getListarHistoriaInterHoy(Historiales dato) {
        return this.historialesDao.getListarHistoriaInterHoy(dato);
    }

    public List getListarHistoriaMed(Historiales dato) {
        return this.historialesDao.getListarHistoriaMed(dato);
    }

    public List getListarHistoriaEmergenGen(Historiales dato) {
        return this.historialesDao.getListarHistoriaEmergenGen(dato);
    }

    public List getListarHistoriaEmergen(Historiales dato) {
        return this.historialesDao.getListarHistoriaEmergen(dato);
    }

    public List getListarAtendidoHoy(Historiales dato) {
        return this.historialesDao.getListarAtendidoHoy(dato);
    }

    public List getListarHistoriaTodo(Historiales dato) {
        return this.historialesDao.getListarHistoriaTodo(dato);
    }

    public List getListarHistoriaI(Historiales dato) {
        return this.historialesDao.getListarHistoriaI(dato);
    }

    public List getUltHistoriaInter(Historiales dato) {
        return this.historialesDao.getUltHistoriaInter(dato);
    }

    public List getHistoriaInterIndi(Historiales dato) {
        return this.historialesDao.getHistoriaInterIndi(dato);
    }

    public List getHistoriaInter(Historiales dato) {
        return this.historialesDao.getHistoriaInter(dato);
    }

    public List getHistoriaInterEnfer(Historiales dato) {
        return this.historialesDao.getHistoriaInterEnfer(dato);
    }

    public List getHistoriaInterMedico(Historiales dato) {
        return this.historialesDao.getHistoriaInterMedico(dato);
    }

    public List getHistoriaInterMedicoReceta(Historiales dato) {
        return this.historialesDao.getHistoriaInterMedicoReceta(dato);
    }

    public List getListaMorbi(Historiales dato) {
        return this.historialesDao.getListaMorbi(dato);
    }

    public void setModificarEstadoHistoria(Historiales dato) {
        this.historialesDao.setModificarEstadoHistoria(dato);
    }

    public void setModificarRangoHistoria(Historiales dato) {
        this.historialesDao.setModificarRangoHistoria(dato);
    }

    public List getListarHistoriaGen(Historiales dato) {
        return this.historialesDao.getListarHistoriaGen(dato);
    }

    public List getListarHistoriaMicro(Historiales dato) {
        return this.historialesDao.getListarHistoriaMicro(dato);
    }

    public List getListarResumenPrestacion(Historiales dato) {
        return this.historialesDao.getListarResumenPrestacion(dato);
    }

    public List getListarResumenPrestacion2(Historiales dato) {
        return this.historialesDao.getListarResumenPrestacion2(dato);
    }

    public List getListarResumenPrestacionYa(Historiales dato) {
        return this.historialesDao.getListarResumenPrestacionYa(dato);
    }

    public List getListarKardexPaciente(Historiales dato) {
        return this.historialesDao.getListarKardexPaciente(dato);
    }

    public List getListarPacientesHistoGeneral(Historiales dato) {
        return this.historialesDao.getListarPacientesHistoGeneral(dato);
    }

    public List getListarPacientesHistoMuje(Historiales dato) {
        return this.historialesDao.getListarPacientesHistoMuje(dato);
    }

    public List getListarPacientesHistoNeo(Historiales dato) {
        return this.historialesDao.getListarPacientesHistoNeo(dato);
    }

    public List getListarPacientesHistoPedi(Historiales dato) {
        return this.historialesDao.getListarPacientesHistoPedi(dato);
    }

    public List getListarPacientesHistoMayor(Historiales dato) {
        return this.historialesDao.getListarPacientesHistoMayor(dato);
    }

    public List getListarPacientes19(Historiales dato) {
        return this.historialesDao.getListarPacientes19(dato);
    }

    public List getListarPacientes19T(Historiales dato) {
        return this.historialesDao.getListarPacientes19T(dato);
    }

    public List getListarPacientesSpam(Historiales dato) {
        return this.historialesDao.getListarPacientesSpam(dato);
    }

    public List getResumenEconomico(Historiales dato) {
        return this.historialesDao.getResumenEconomico(dato);
    }

    public List getResumenEconomicoLab(Historiales dato) {
        return this.historialesDao.getResumenEconomicoLab(dato);
    }

    public List getResumenEconomicoEco(Historiales dato) {
        return this.historialesDao.getResumenEconomicoEco(dato);
    }

    public List getResumenEconomicoTot(Historiales dato) {
        return this.historialesDao.getResumenEconomicoTot(dato);
    }

    public List getHistorialAtendidos(Historiales dato) {
        return this.historialesDao.getHistorialAtendidos(dato);
    }

    public List getHistorialAtendidosEco(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosEco(dato);
    }

    public List getHistorialAtendidosH(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosH(dato);
    }

    public List getHistorialAtendidosHemo(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosHemo(dato);
    }

    public List getHistorialAtendidosResid(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosResid(dato);
    }

    public List getHistorialAtendidosResidConsul(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosResidConsul(dato);
    }

    public List getHistorialAtendidosResidNombre(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosResidNombre(dato);
    }

    public List getHistorialAtendidosResidConsulPer(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosResidConsulPer(dato);
    }

    public List getInternadosCajaObservacionHemo(Historiales dato) {
        return this.historialesDao.getInternadosCajaObservacionHemo(dato);
    }

    public List getInternadosCajaObservacion(Historiales dato) {
        return this.historialesDao.getInternadosCajaObservacion(dato);
    }

    public List getInternadosCajaObservacionBuscar(Historiales dato) {
        return this.historialesDao.getInternadosCajaObservacionBuscar(dato);
    }

    public List getInternadosCajaObservacionPiso(Historiales dato) {
        return this.historialesDao.getInternadosCajaObservacionPiso(dato);
    }

    public List getInternadosCajaObservacionPisoPer(Historiales dato) {
        return this.historialesDao.getInternadosCajaObservacionPisoPer(dato);
    }

    public List getHistorialAtendidosFecha(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosFecha(dato);
    }

    public List getAtendidosInter(Historiales dato) {
        return this.historialesDao.getAtendidosInter(dato);
    }

    public List getAtendidosInterSala(Historiales dato) {
        return this.historialesDao.getAtendidosInterSala(dato);
    }

    public List getHistorialLibros(Historiales dato) {
        return this.historialesDao.getHistorialLibros(dato);
    }

    public List getHistorialAtendidosP(Historiales dato) {
        return this.historialesDao.getHistorialAtendidosP(dato);
    }

    public List getAtendidosGeneral(Historiales dato) {
        return this.historialesDao.getAtendidosGeneral(dato);
    }

    public List getAtendidosMicronutriente(Historiales dato) {
        return this.historialesDao.getAtendidosMicronutriente(dato);
    }

    public List getSumi(Historiales dato) {
        return this.historialesDao.getSumi(dato);
    }

    public List getInternados(Historiales dato) {
        return this.historialesDao.getInternados(dato);
    }

    public List getInternadosSala(Historiales dato) {
        return this.historialesDao.getInternadosSala(dato);
    }

    public List getVigenciaFecha2(Historiales dato) {
        return this.historialesDao.getVigenciaFecha2(dato);
    }

    public List getVigenciaFecha(Historiales dato) {
        return this.historialesDao.getVigenciaFecha(dato);
    }

    public List getRecetasInternados(Historiales dato) {
        return this.historialesDao.getRecetasInternados(dato);
    }

    public List getRecetasInternadosNombre(Historiales dato) {
        return this.historialesDao.getRecetasInternadosNombre(dato);
    }

    public List getInternadosSalaCajaNombre(Historiales dato) {
        return this.historialesDao.getInternadosSalaCajaNombre(dato);
    }

    public List getInternadosSalaCajaAltaNombre(Historiales dato) {
        return this.historialesDao.getInternadosSalaCajaAltaNombre(dato);
    }

    public List getRecetasInternadosUnico(Historiales dato) {
        return this.historialesDao.getRecetasInternadosUnico(dato);
    }

    public List getVigencia_now(Historiales dato) {
        return this.historialesDao.getVigencia_now(dato);
    }

    public List getVigencia_now_triaje(Historiales dato) {
        return this.historialesDao.getVigencia_now_triaje(dato);
    }

    public List getInternadosPisoCaja(Historiales dato) {
        return this.historialesDao.getInternadosPisoCaja(dato);
    }

    public List getInternadosCaja(Historiales dato) {
        return this.historialesDao.getInternadosCaja(dato);
    }

    public List getInternadosPisoCajaVacio(Historiales dato) {
        return this.historialesDao.getInternadosPisoCajaVacio(dato);
    }

    public List getIndicadores() {
        return this.cuadernosDao.getIndicadores();
    }

    public List getListaHistoriaImp(int rango) {
        return this.historialesDao.getListaHistoriaImp(rango);
    }
    // medicamentos

    public List getListarMedicamentosGestion(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosGestion(dato);
    }

    public List getListarMedicamentosCot(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosCot(dato);
    }

    public List getListarMedicamentosCotFar(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosCotFar(dato);
    }

    public List getListarMedicamentosCotb1(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosCotb1(dato);
    }

    public List getListarMedicamentos(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentos(dato);
    }

    public List getListarMedicamentosTotal(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosTotal(dato);
    }

    public List getListarMedicamentosAsignados(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosAsignados(dato);
    }

    public List getListarMedicamentosPorAsig(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosPorAsig(dato);
    }

    public List getListarMedicamentosAlma(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosAlma(dato);
    }

    public List getListarInvAlma(Medicamentos dato) {
        return this.medicamentosDao.getListarInvAlma(dato);
    }

    public List getListarMedicamentosAlmaGral(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosAlmaGral(dato);
    }

    public List getListarMedicamentosVacio(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosVacio(dato);
    }

    public List getListarMedicamentoSolo(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentoSolo(dato);
    }

    public List getListarInventaGrupo(Medicamentos dato) {
        return this.medicamentosDao.getListarInventaGrupo(dato);
    }

    public List getListarInventaGrupoSub(Medicamentos dato) {
        return this.medicamentosDao.getListarInventaGrupoSub(dato);
    }

    public List getListarInventa(Medicamentos dato) {
        return this.medicamentosDao.getListarInventa(dato);
    }

    public List getListarInventaSubGrupo(Medicamentos dato) {
        return this.medicamentosDao.getListarInventaSubGrupo(dato);
    }

    public List getListarInventaPartida(Medicamentos dato) {
        return this.medicamentosDao.getListarInventaPartida(dato);
    }

    public List getListarMedicamentosb1(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosb1(dato);
    }
    
    public List getListarTipos(Medicamentos dato) {
        return this.medicamentosDao.getListarTipos(dato);
    }
    
    public List getListarGrupos(Medicamentos dato) {
        return this.medicamentosDao.getListarGrupos(dato);
    }

    public List getListarSubGrupos(Medicamentos dato) {
        return this.medicamentosDao.getListarSubGrupos(dato);
    }

    public List getListarSubGrupos2(Medicamentos dato) {
        return this.medicamentosDao.getListarSubGrupos2(dato);
    }

    public List getListarPartidas(Medicamentos dato) {
        return this.medicamentosDao.getListarPartidas(dato);
    }

    public List getListarMedicamentosRe(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosRe(dato);
    }

    public List getActualizarMedicamentos(Medicamentos dato) {
        return this.medicamentosDao.getActualizarMedicamentos(dato);
    }

    public List getActualizarMedicamentos_med(Medicamentos dato) {
        return this.medicamentosDao.getActualizarMedicamentos_med(dato);
    }

    public List getListarMedicamentosMicro(Medicamentos dato) {
        return this.medicamentosDao.getListarMedicamentosMicro(dato);
    }

    public List getListarCarmelosExel(Medicamentos dato) {
        return this.medicamentosDao.getListarCarmelosExel(dato);
    }

    public Medicamentos getDatosMedicamento(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamento(dato);
    }

    public Medicamentos getDatosMedicamentoB(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamentoB(dato);
    }

    public Medicamentos getDatoGrupo(Medicamentos dato) {
        return this.medicamentosDao.getDatoGrupo(dato);
    }

    public Medicamentos getDatoSubGrupo(Medicamentos dato) {
        return this.medicamentosDao.getDatoSubGrupo(dato);
    }

    public Medicamentos getDatoPartida(Medicamentos dato) {
        return this.medicamentosDao.getDatoPartida(dato);
    }

    public Medicamentos getDatoSubPartida(Medicamentos dato) {
        return this.medicamentosDao.getDatoSubPartida(dato);
    }

    public Medicamentos getDatosItem(Medicamentos dato) {
        return this.medicamentosDao.getDatosItem(dato);
    }

    public Medicamentos getDatosItemAlmacen(Medicamentos dato) {
        return this.medicamentosDao.getDatosItemAlmacen(dato);
    }

    public Medicamentos getDatosSubItem(Medicamentos dato) {
        return this.medicamentosDao.getDatosSubItem(dato);
    }

    public Medicamentos getDatosMedicamentoAsig(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamentoAsig(dato);
    }

    public Medicamentos getDatosMedicamentoPasado(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamentoPasado(dato);
    }

    public Medicamentos getDatosMedicamentoUnico(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamentoUnico(dato);
    }

    public Medicamentos getDatosMedicamentoExiste(Medicamentos dato) {
        return this.medicamentosDao.getDatosMedicamentoExiste(dato);
    }

    public Medicamentos getDatoPrograma(Medicamentos dato) {
        return this.medicamentosDao.getDatoPrograma(dato);
    }

    public void setEliminarMedicamento(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarMedicamento(medicamento);
    }

    public void setEliminarMedicamentoLocal(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarMedicamentoLocal(medicamento);
    }

    public void setEliminarMedicamentoAsignado(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarMedicamentoAsignado(medicamento);
    }

    public void setEliminarGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarGrupo(medicamento);
    }

    public void setEliminarPartida(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarPartida(medicamento);
    }

    public void setEliminarSubGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setEliminarSubGrupo(medicamento);
    }

    public void setCrearMedicamento(Medicamentos medicamento) {
        this.medicamentosDao.setCrearMedicamento(medicamento);
    }

    public void setCrearMedicamentoLocal(Medicamentos medicamento) {
        this.medicamentosDao.setCrearMedicamentoLocal(medicamento);
    }

    public void setCrearMedicamentoLocal2(Medicamentos medicamento) {
        this.medicamentosDao.setCrearMedicamentoLocal2(medicamento);
    }

    public void setCrearMedicamentoAsignacion(Medicamentos medicamento) {
        this.medicamentosDao.setCrearMedicamentoAsignacion(medicamento);
    }

    public void setCrearGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setCrearGrupo(medicamento);
    }

    public void setCrearPartida(Medicamentos medicamento) {
        this.medicamentosDao.setCrearPartida(medicamento);
    }

    public void setCrearSubGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setCrearSubGrupo(medicamento);
    }

    public void setModificarMedicamento(Medicamentos medicamento) {
        this.medicamentosDao.setModificarMedicamento(medicamento);
    }

    public void setModificarMedicamentoStock(Medicamentos medicamento) {
        this.medicamentosDao.setModificarMedicamentoStock(medicamento);
    }

    public void setModificarMedicamentoTotal(Medicamentos medicamento) {
        this.medicamentosDao.setModificarMedicamentoTotal(medicamento);
    }

    public void setModificarGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setModificarGrupo(medicamento);
    }

    public void setModificarPartida(Medicamentos medicamento) {
        this.medicamentosDao.setModificarPartida(medicamento);
    }

    public void setModificarSubGrupo(Medicamentos medicamento) {
        this.medicamentosDao.setModificarSubGrupo(medicamento);
    }

    public void setModificarCie10(Medicamentos medicamento) {
        this.medicamentosDao.setModificarCie10(medicamento);
    }

    public List getListarEnfermedades(String nombres) {
        return this.medicamentosDao.getListarEnfermedades(nombres);
    }

    public List getListarEnfermedadesOtra(String nombres) {
        return this.medicamentosDao.getListarEnfermedadesOtra(nombres);
    }

    public List getListarEnfermedadesCod(String nombres) {
        return this.medicamentosDao.getListarEnfermedadesCod(nombres);
    }

    public List getListarEnfermedadesCot(Medicamentos medicamento) {
        return this.medicamentosDao.getListarEnfermedadesCot(medicamento);
    }

    public List getListarProgramas(Medicamentos medicamento) {
        return this.medicamentosDao.getListarProgramas(medicamento);
    }

    public List getListarImm(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImm(medicamento);
    }

    public List getListarImmFarma(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFarma(medicamento);
    }

    public List getListarImmCNS(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmCNS(medicamento);
    }

    public List getListarImmCNS2(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmCNS2(medicamento);
    }

    public List getListarImmCNSsaldo(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmCNSsaldo(medicamento);
    }

    public List getListarImmCNSsaldo2(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmCNSsaldo2(medicamento);
    }

    public List getListarFaltanImm(Medicamentos medicamento) {
        return this.medicamentosDao.getListarFaltanImm(medicamento);
    }

    public List getListarDatosImm(Medicamentos medicamento) {
        return this.medicamentosDao.getListarDatosImm(medicamento);
    }

    public List getListarDatosImmCNS2(Medicamentos medicamento) {
        return this.medicamentosDao.getListarDatosImmCNS2(medicamento);
    }

    public List getListarActImm(Medicamentos medicamento) {
        return this.medicamentosDao.getListarActImm(medicamento);
    }

    public List getListarImmFechaDada(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFechaDada(medicamento);
    }

    public List getListarImmFechaDadaProg(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFechaDadaProg(medicamento);
    }

    public List getListarImmFechaDadaUsua(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFechaDadaUsua(medicamento);
    }

    public List getListarImmFechaDadaCNS(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFechaDadaCNS(medicamento);
    }

    public List getListarImmFechaDet(Medicamentos medicamento) {
        return this.medicamentosDao.getListarImmFechaDet(medicamento);
    }

    public List getListarActCpt(Medicamentos medicamento) {
        return this.medicamentosDao.getListarActCpt(medicamento);
    }

    public List getListarActCptII(Medicamentos medicamento) {
        return this.medicamentosDao.getListarActCptII(medicamento);
    }

    public List getListarCptPsico(Medicamentos medicamento) {
        return this.medicamentosDao.getListarCptPsico(medicamento);
    }

    // Recetas
    public List getListarMedPaquete(Recetas dato) {
        return this.recetasDao.getListarMedPaquete(dato);
    }

    public void setEliminarRecetaPaque(Recetas dato) {
        this.recetasDao.setEliminarRecetaPaque(dato);
    }

    public void setCrearRecetaPaque(Recetas dato) {
        this.recetasDao.setCrearRecetaPaque(dato);
    }

    public int setRegistrarKardex(Recetas dato) {
        return this.recetasDao.setRegistrarKardex(dato);
    }

    public void setModificarRecetaPaquete(Recetas dato) {
        this.recetasDao.setModificarRecetaPaquete(dato);
    }

    public List getListarRecetas(Recetas dato) {
        return this.recetasDao.getListarRecetas(dato);
    }

    public List getListarRecetasPrivado(Recetas dato) {
        return this.recetasDao.getListarRecetasPrivado(dato);
    }

    public List getListarRecetasHistorial(Recetas dato) {
        return this.recetasDao.getListarRecetasHistorial(dato);
    }

    public List getListarRecetasCNS(Recetas dato) {
        return this.recetasDao.getListarRecetasCNS(dato);
    }

    public List getListarRecetasPres(Recetas dato) {
        return this.recetasDao.getListarRecetasPres(dato);
    }

    public List getListarRecetasUlt(Recetas dato) {
        return this.recetasDao.getListarRecetasUlt(dato);
    }

    public List getListarUltRecetaI(Recetas dato) {
        return this.recetasDao.getListarUltRecetaI(dato);
    }

    public List getListarUltReceta(Recetas dato) {
        return this.recetasDao.getListarUltReceta(dato);
    }

    public List getListarRecetaAnterior(Recetas dato) {
        return this.recetasDao.getListarRecetaAnterior(dato);
    }

    public List getListarRecetaAnteriorCarmelo(Recetas dato) {
        return this.recetasDao.getListarRecetaAnteriorCarmelo(dato);
    }

    public List getListarRecetasYa(Recetas dato) {
        return this.recetasDao.getListarRecetasYa(dato);
    }

    public List getListarRecetasInt(Recetas dato) {
        return this.recetasDao.getListarRecetasInt(dato);
    }

    public List getListarRecetasTotal(Recetas dato) {
        return this.recetasDao.getListarRecetasTotal(dato);
    }

    public List getListarRecetasTotalMed(Recetas dato) {
        return this.recetasDao.getListarRecetasTotalMed(dato);
    }

    public List getListarRecetasCaros(Recetas dato) {
        return this.recetasDao.getListarRecetasCaros(dato);
    }

    public List getListarRecetasMedico(Recetas dato) {
        return this.recetasDao.getListarRecetasMedico(dato);
    }

    public List getListarRecetasMedicoCNS(Recetas dato) {
        return this.recetasDao.getListarRecetasMedicoCNS(dato);
    }

    public List getListarRepRecetaCNS(Recetas dato) {
        return this.recetasDao.getListarRepRecetaCNS(dato);
    }

    public List getListarKardexPac(Recetas dato) {
        return this.recetasDao.getListarKardexPac(dato);
    }

    public List getListarRecetasInter(Recetas dato) {
        return this.recetasDao.getListarRecetasInter(dato);
    }

    public List getListarRecetasIndi(Recetas dato) {
        return this.recetasDao.getListarRecetasIndi(dato);
    }

    public List getListarRecetasMedicoI(Recetas dato) {
        return this.recetasDao.getListarRecetasMedicoI(dato);
    }

    public List getListarRecetasMedicoImp(Recetas dato) {
        return this.recetasDao.getListarRecetasMedicoImp(dato);
    }

    public void setCrearReceta(Recetas dato) {
        this.recetasDao.setCrearReceta(dato);
    }

    public void setEliminarReceta(Recetas dato) {
        this.recetasDao.setEliminarReceta(dato);
    }

    public void setModificarEstadoReceta(Recetas dato) {
        this.recetasDao.setModificarEstadoReceta(dato);
    }

    public void setModificarRecetaNumera(Recetas dato) {
        this.recetasDao.setModificarRecetaNumera(dato);
    }

    public void setModificarRecetaDosifi(Recetas dato) {
        this.recetasDao.setModificarRecetaDosifi(dato);
    }

    public void setModificarEstadoInter(Recetas dato) {
        this.recetasDao.setModificarEstadoInter(dato);
    }

    public void setCrearRecetaMedSumi(Recetas dato) {
        this.recetasDao.setCrearRecetaMedSumi(dato);
    }

    public List getListarRecetasS(Recetas dato) {
        return this.recetasDao.getListarRecetasS(dato);
    }

    public List getListarKardex(Recetas dato) {
        return this.recetasDao.getListarKardex(dato);
    }
    
    public List getListarKardexProf(Recetas dato){
      return this.recetasDao.getListarKardexProf(dato);
    }
    
    public List getListarKardexInterFact(Recetas dato) {
        return this.recetasDao.getListarKardexInterFact(dato);
    }

    public List getListarKardexFactura(Recetas dato) {
        return this.recetasDao.getListarKardexFactura(dato);
    }

    public List getListarKardexPerfilFar(Recetas dato) {
        return this.recetasDao.getListarKardexPerfilFar(dato);
    }

    public List getListarKardexPerfilFarGen(Recetas dato) {
        return this.recetasDao.getListarKardexPerfilFarGen(dato);
    }

    public List getListarFechaPerfil(Recetas dato) {
        return this.recetasDao.getListarFechaPerfil(dato);
    }

    public List getListarFechaPerfil2(Recetas dato) {
        return this.recetasDao.getListarFechaPerfil2(dato);
    }

    public List getListaKardexPacienteDesg(Recetas dato) {
        return this.recetasDao.getListaKardexPacienteDesg(dato);
    }

    public List getListarFechaPerfildat(Recetas dato) {
        return this.recetasDao.getListarFechaPerfildat(dato);
    }

    public List getListarPerfilReversion(Recetas dato) {
        return this.recetasDao.getListarPerfilReversion(dato);
    }

    public List getListarKardexPerfilFarDet(Recetas dato) {
        return this.recetasDao.getListarKardexPerfilFarDet(dato);
    }

    public List getListarKardexComprueba(Recetas dato) {
        return this.recetasDao.getListarKardexComprueba(dato);
    }

    public List getListarKardexCodsumi(Recetas dato) {
        return this.recetasDao.getListarKardexCodsumi(dato);
    }

    public List getListarKardexAjus(Recetas dato) {
        return this.recetasDao.getListarKardexAjus(dato);
    }

    public List getListarKardexPago(int id_historial) {
        return this.recetasDao.getListarKardexPago(id_historial);
    }

    public void setModificarKardex(Recetas dato) {
        this.recetasDao.setModificarKardex(dato);
    }

    public void setModificarKardexV(Recetas dato) {
        this.recetasDao.setModificarKardexV(dato);
    }

    public void setModificarKardexR(Recetas dato) {
        this.recetasDao.setModificarKardexR(dato);
    }

    public void setModificarKardexVenta(Recetas dato) {
        this.recetasDao.setModificarKardexVenta(dato);
    }

    public void setModificarKardexPaciente(Recetas dato) {
        this.recetasDao.setModificarKardexPaciente(dato);
    }

    public List getListarKardexI(Recetas dato) {
        return this.recetasDao.getListarKardexI(dato);
    }

    public List getListarKardexIImpRec(Recetas dato) {
        return this.recetasDao.getListarKardexIImpRec(dato);
    }

    public List getListarKardexInter(Recetas dato) {
        return this.recetasDao.getListarKardexInter(dato);
    }

    public List getListarKardexTotal(Recetas dato) {
        return this.recetasDao.getListarKardexTotal(dato);
    }
    
    public List getListarKardexProve(Recetas dato) {
        return this.recetasDao.getListarKardexProve(dato);
    }
	
    public void setCrearKardex(Recetas dato) {
        this.recetasDao.setCrearKardex(dato);
    }
    
    public void setCrearKardexProf(Recetas dato) {
      this.recetasDao.setCrearKardexProf(dato);
    }
    
    public void setCrearKardexProf2(Recetas dato) {
      this.recetasDao.setCrearKardexProf2(dato);
    }
    
    public void setCrearKardexPaciente(Recetas dato) {
        this.recetasDao.setCrearKardexPaciente(dato);
    }

    @Override
    public void setCrearKardexPacInsert(Recetas dato) {
        this.recetasDao.setCrearKardexPacInsert(dato);
    }

    @Override
    public void setEliminarKardex(Recetas dato) {
        this.recetasDao.setEliminarKardex(dato);
    }
    
    public void setEliminarKardexProf(Recetas dato){
      this.recetasDao.setEliminarKardexProf(dato);
    }
    
    public void setEliminarKardexReceta(Recetas dato) {
        this.recetasDao.setEliminarKardexReceta(dato);
    }

    public List getListarKardexMedicamento(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamento(dato);
    }

    public List getListarKardexMedicamentoSin0(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamentoSin0(dato);
    }

    public List getListarKardexMedicamentoSolo0(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamentoSolo0(dato);
    }

    public List getListarKardexMedicamentoSoloE(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamentoSoloE(dato);
    }

    public List getListarKardexMedicamentoSoloA(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamentoSoloA(dato);
    }

    public List getListarKardexMedicamentoSoloS(Recetas dato) {
        return this.recetasDao.getListarKardexMedicamentoSoloS(dato);
    }

    public List getListarKardexProg(Recetas dato) {
        return this.recetasDao.getListarKardexProg(dato);
    }
    
    public List getListarKardexControl(Recetas dato) {
        return this.recetasDao.getListarKardexControl(dato);
    }

    public List getListarKardexIndi(Recetas dato) {
        return this.recetasDao.getListarKardexIndi(dato);
    }

    public List getListarKardexMicro(Recetas dato) {
        return this.recetasDao.getListarKardexMicro(dato);
    }
    // Detalle Cobros

    public List getListarDetalleGen(Detalle detalle) {
        return this.detalleDao.getListarDetalleGen(detalle);
    }

    public List getListarDetTotal(Detalle detalle) {
        return this.detalleDao.getListarDetTotal(detalle);
    }

    public List getListarDetIndividual(Detalle detalle) {
        return this.detalleDao.getListarDetIndividual(detalle);
    }

    public List getListarDetHistorial(Detalle detalle) {
        return this.detalleDao.getListarDetHistorial(detalle);
    }

    public List getListarDetalle(Detalle detalle) {
        return this.detalleDao.getListarDetalle(detalle);
    }

    public List getListarDetallePago(Detalle detalle) {
        return this.detalleDao.getListarDetallePago(detalle);
    }

    public List getListarCobroDetalle(Detalle detalle) {
        return this.detalleDao.getListarCobroDetalle(detalle);
    }

    public List getListarCobroDetalleInterFact(Detalle detalle) {
        return this.detalleDao.getListarCobroDetalleInterFact(detalle);
    }

    public List getListarCobroDetalleInt(Detalle detalle) {
        return this.detalleDao.getListarCobroDetalleInt(detalle);
    }

    public List getListarDetalleSaldo(Detalle detalle) {
        return this.detalleDao.getListarDetalleSaldo(detalle);
    }

    public void setCrearDetalle(Detalle detalle) {
        this.detalleDao.setCrearDetalle(detalle);
    }

    public void setCrearDetallePago(Detalle detalle) {
        this.detalleDao.setCrearDetallePago(detalle);
    }

    public void setEliminarDetalle(Detalle detalle) {
        this.detalleDao.setEliminarDetalle(detalle);
    }

    public void setEliminarDetalleLab(Detalle detalle) {
        this.detalleDao.setEliminarDetalleLab(detalle);
    }
    
	public void setModificarDetalle (Detalle detalle) {
      this.detalleDao.setModificarDetalle(detalle);
    }
    // Prestaciones
    public List getListarPresOdon(Prestaciones dato) {
        return this.prestacionesDao.getListarPresOdon(dato);
    }

    public List getListarPresOdonGen(Prestaciones dato) {
        return this.prestacionesDao.getListarPresOdonGen(dato);
    }

    public List getListarNivelPrestacion(int id_prestacion) {
        return this.prestacionesDao.getListarNivelPrestacion(id_prestacion);
    }

    public void setCrearNivelPaque(Prestaciones dato) {
        this.prestacionesDao.setCrearNivelPaque(dato);
    }

    public void setEliminarNivelPaque(Prestaciones dato) {
        this.prestacionesDao.setEliminarNivelPaque(dato);
    }

    public Prestaciones getDatosPrestacion(int id_prestacion) {
        return this.prestacionesDao.getDatosPrestacion(id_prestacion);
    }

    public List getListarPrestacionesSumi(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesSumi(dato);
    }

    public List getListarPrestacionesSumiH(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesSumiH(dato);
    }

    public List getListarMedicamentosSumi(Prestaciones dato) {
        return this.prestacionesDao.getListarMedicamentosSumi(dato);
    }

    public void setCrearPrestacion(Prestaciones dato) {
        this.prestacionesDao.setCrearPrestacion(dato);
    }

    public void setModificarPrestacion(Prestaciones dato) {
        this.prestacionesDao.setModificarPrestacion(dato);
    }

    public void setHabilitaPrestacion(Prestaciones dato) {
        this.prestacionesDao.setHabilitaPrestacion(dato);
    }

    public void setEliminarPrestacion(Prestaciones dato) {
        this.prestacionesDao.setEliminarPrestacion(dato);
    }

    public List getListarPrestaciones(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestaciones(dato);
    }

    public List getListarPrestaciones22(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestaciones22(dato);
    }

    public List getListarPrestacionesDes(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDes(dato);
    }

    public List getListarPrestacionesDes22(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDes22(dato);
    }

    public List getListarPrestacionesCot(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesCot(dato);
    }

    public List getListarPrestacionesCot22(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesCot22(dato);
    }

    public void setCrearRecetaSumi(Prestaciones dato) {
        this.prestacionesDao.setCrearRecetaSumi(dato);
    }

    public List getListarSumiRecetasPres(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiRecetasPres(dato);
    }

    public List getListarPrestacionesDuplis(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDuplis(dato);
    }

    public List getListarPrestacionSinMed(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionSinMed(dato);
    }

    public List getListarSumiRecetasPres2(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiRecetasPres2(dato);
    }

    public List getListarSumiRecetas(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiRecetas(dato);
    }

    public List getListarSumiRecetasI(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiRecetasI(dato);
    }

    public List getListarSumiPresta(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiPresta(dato);
    }

    public List getPrestacionExisteYa(Prestaciones dato) {
        return this.prestacionesDao.getPrestacionExisteYa(dato);
    }

    public List getPrestacionExisteYaDia(Prestaciones dato) {
        return this.prestacionesDao.getPrestacionExisteYaDia(dato);
    }

    public List getListarSumiRecetasIntImp(Prestaciones dato) {
        return this.prestacionesDao.getListarSumiRecetasIntImp(dato);
    }
    
    public List getPrestacionGen(Prestaciones dato) {
        return this.prestacionesDao.getPrestacionGen(dato);
    }

    public void setEliminarRecetaSumi(Prestaciones dato) {
        this.prestacionesDao.setEliminarRecetaSumi(dato);
    }

    public void setEliminarPresDupli(Prestaciones dato) {
        this.prestacionesDao.setEliminarPresDupli(dato);
    }

    public void setEliminarRecetaSumiI(Prestaciones dato) {
        this.prestacionesDao.setEliminarRecetaSumiI(dato);
    }

    public void setEliminarRecetaMedSumi(Prestaciones dato) {
        this.prestacionesDao.setEliminarRecetaMedSumi(dato);
    }

    public void setEliminarRecetaMedSumiKardex(Prestaciones dato) {
        this.prestacionesDao.setEliminarRecetaMedSumiKardex(dato);
    }

    public List getListarRepes() {
        return this.prestacionesDao.getListarRepes();
    }

    public List getListarFaltanRepes() {
        return this.prestacionesDao.getListarFaltanRepes();
    }

    public List getListarFopos(int mes, int anio) {
        return this.prestacionesDao.getListarFopos(mes, anio);
    }

    public List getListarActRepes(Prestaciones dato) {
        return this.prestacionesDao.getListarActRepes(dato);
    }

    public List getListarMorbilidad(Prestaciones dato) {
        return this.prestacionesDao.getListarMorbilidad(dato);
    }

    public List getListarPrestacionesDadasMuje(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDadasMuje(dato);
    }

    public List getListarPrestacionesDadasNeo(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDadasNeo(dato);
    }

    public List getListarPrestacionesDadasPedi(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDadasPedi(dato);
    }

    public List getListarResumenPrest(Prestaciones dato) {
        return this.prestacionesDao.getListarResumenPrest(dato);
    }

    public List getListarPrestacionesDadasMayor(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesDadasMayor(dato);
    }

    public List getListarPrestacionesGen(Prestaciones dato) {
        return this.prestacionesDao.getListarPrestacionesGen(dato);
    }

    public List getListarPacientesPrestacionesGen(Prestaciones dato) {
        return this.prestacionesDao.getListarPacientesPrestacionesGen(dato);
    }

    public List getListarPacientesPrestacionesNeo(Prestaciones dato) {
        return this.prestacionesDao.getListarPacientesPrestacionesNeo(dato);
    }

    public List getListarPacientesPrestacionesPedi(Prestaciones dato) {
        return this.prestacionesDao.getListarPacientesPrestacionesPedi(dato);
    }

    public List getListarPacientesPrestacionesMayor(Prestaciones dato) {
        return this.prestacionesDao.getListarPacientesPrestacionesMayor(dato);
    }

    public List getListarPacientesPrestacionesMuje(Prestaciones dato) {
        return this.prestacionesDao.getListarPacientesPrestacionesMuje(dato);
    }
    // Cuadernos

    public List getCuadernoC1(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC1(dato);
    }

    public List getVerTransferencia(Cuadernos dato) {
        return this.cuadernosDao.getVerTransferencia(dato);
    }

    public List getListarTransferencia(Cuadernos dato) {
        return this.cuadernosDao.getListarTransferencia(dato);
    }

    public List getDatoTransferencia(Cuadernos dato) {
        return this.cuadernosDao.getDatoTransferencia(dato);
    }

    public List getVerAdmisiones(Cuadernos dato) {
        return this.cuadernosDao.getVerAdmisiones(dato);
    }

    public List getVerEpicrisis(Cuadernos dato) {
        return this.cuadernosDao.getVerEpicrisis(dato);
    }

    public List getVerProtocolos(Cuadernos dato) {
        return this.cuadernosDao.getVerProtocolos(dato);
    }

    public List getListarAdmision(Cuadernos dato) {
        return this.cuadernosDao.getListarAdmision(dato);
    }

    public List getListarEpicrisis(Cuadernos dato) {
        return this.cuadernosDao.getListarEpicrisis(dato);
    }

    public List getListarProtocolos(Cuadernos dato) {
        return this.cuadernosDao.getListarProtocolos(dato);
    }

    public List getDatoAdmision(Cuadernos dato) {
        return this.cuadernosDao.getDatoAdmision(dato);
    }

    public List getDatoEpicrisis(Cuadernos dato) {
        return this.cuadernosDao.getDatoEpicrisis(dato);
    }

    public List getDatoProtocolo(Cuadernos dato) {
        return this.cuadernosDao.getDatoProtocolo(dato);
    }

    public List getListarAdmisionTot(Cuadernos dato) {
        return this.cuadernosDao.getListarAdmisionTot(dato);
    }

    public List getListarFechas(Cuadernos dato) {
        return this.cuadernosDao.getListarFechas(dato);
    }

    public List getListarFechasCount(Cuadernos dato) {
        return this.cuadernosDao.getListarFechasCount(dato);
    }

    public List getVerSolSangre(Cuadernos dato) {
        return this.cuadernosDao.getVerSolSangre(dato);
    }

    public List getListarSolSangres(Cuadernos dato) {
        return this.cuadernosDao.getListarSolSangres(dato);
    }

    public List getDatoSolSangre(Cuadernos dato) {
        return this.cuadernosDao.getDatoSolSangre(dato);
    }

    public List getVerOftalmologia(Cuadernos dato) {
        return this.cuadernosDao.getVerOftalmologia(dato);
    }

    public List getCuadernoC2(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC2(dato);
    }

    public List getCuadernoC3(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC3(dato);
    }

    public List getCuadernoC4(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC4(dato);
    }

    public List getCuadernoC5(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC5(dato);
    }

    public List getCuadernoC6(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC6(dato);
    }

    public List getCuadernoC7(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoC7(dato);
    }

    public List getCuadernoV(Cuadernos dato) {
        return this.cuadernosDao.getCuadernoV(dato);
    }

    public List getLabGen(Cuadernos dato) {
        return this.cuadernosDao.getLabGen(dato);
    }

    public void setRegistrarOdonMensual(Cuadernos dato) {
        this.cuadernosDao.setRegistrarOdonMensual(dato);
    }

    public void setRegistrarOdonPersonal(Cuadernos dato) {
        this.cuadernosDao.setRegistrarOdonPersonal(dato);
    }

    public void setModificarC2(Cuadernos dato) {
        this.cuadernosDao.setModificarC2(dato);
    }

    public void setRegistrarSnis301(Cuadernos dato) {
        this.cuadernosDao.setRegistrarSnis301(dato);
    }

    public List getListarSnis301() {
        return this.cuadernosDao.getListarSnis301();
    }

    public List getListarFaltaSnis301() {
        return this.cuadernosDao.getListarFaltaSnis301();
    }

    public List getConfigurarImpresion(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresion(dato);
    }

    public List getImpresionSerologia(Cuadernos dato) {
        return this.cuadernosDao.getImpresionSerologia(dato);
    }

    public List getImpresionHemograma(Cuadernos dato) {
        return this.cuadernosDao.getImpresionHemograma(dato);
    }

    public List getImpresionOrina(Cuadernos dato) {
        return this.cuadernosDao.getImpresionOrina(dato);
    }

    public List getImpresionOtros(Cuadernos dato) {
        return this.cuadernosDao.getImpresionOtros(dato);
    }

    public List getImpresionEmbarazo(Cuadernos dato) {
        return this.cuadernosDao.getImpresionEmbarazo(dato);
    }

    public List getImpresionQuimicas(Cuadernos dato) {
        return this.cuadernosDao.getImpresionQuimicas(dato);
    }

    public List getImpresionEcografia(Cuadernos dato) {
        return this.cuadernosDao.getImpresionEcografia(dato);
    }

    public List getConfigurarImpresionGral(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionGral(dato);
    }

    public List getConfigurarImpresionGralHcl(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionGralHcl(dato);
    }

    public List getConfigurarImpresionGralPerinatal(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionGralPerinatal(dato);
    }

    public List getConfigurarImpresionCarnetInf(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionCarnetInf(dato);
    }

    public List getConfigurarImpresionGinecologica(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionGinecologica(dato);
    }

    public List getConfigurarImpresionFormulario(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionFormulario(dato);
    }

    public List getConfigurarImpresionCarnet(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionCarnet(dato);
    }

    public List getConfigurarImpresionRiesgo(Cuadernos dato) {
        return this.cuadernosDao.getConfigurarImpresionRiesgo(dato);
    }

    public List getCuadernosImpresion(Cuadernos dato) {
        return this.cuadernosDao.getCuadernosImpresion(dato);
    }

    public List getCodigoControl(Cuadernos dato) {
        return this.cuadernosDao.getCodigoControl(dato);
    }

    public List getListarOdonMensual() {
        return this.cuadernosDao.getListarOdonMensual();
    }

    public List getListarFaltaOdonMensual() {
        return this.cuadernosDao.getListarFaltaOdonMensual();
    }

    public Cuadernos getDatosTallaPesoF02(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaPesoF02(dato);
    }

    public Cuadernos getDatosTallaPesoF25(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaPesoF25(dato);
    }

    public Cuadernos getDatosTallaPesoM02(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaPesoM02(dato);
    }

    public Cuadernos getDatosTallaPesoM25(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaPesoM25(dato);
    }

    public Cuadernos getDatosTallaEdadF02(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaEdadF02(dato);
    }

    public Cuadernos getDatosTallaEdadF25(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaEdadF25(dato);
    }

    public Cuadernos getDatosTallaEdadM02(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaEdadM02(dato);
    }

    public Cuadernos getDatosTallaEdadM25(Cuadernos dato) {
        return this.cuadernosDao.getDatosTallaEdadM25(dato);
    }

    public Cuadernos getDatosPesoEdadF02(Cuadernos dato) {
        return this.cuadernosDao.getDatosPesoEdadF02(dato);
    }

    public Cuadernos getDatosPesoEdadM02(Cuadernos dato) {
        return this.cuadernosDao.getDatosPesoEdadM02(dato);
    }

    public Cuadernos getHistorialPacientesEco(int id_paciente) {
        return this.cuadernosDao.getHistorialPacientesEco(id_paciente);
    }

    public List getListarPacientesEco() {
        return this.cuadernosDao.getListarPacientesEco();
    }

    public List getPacienteResultadoLab(Cuadernos dato) {
        return this.cuadernosDao.getPacienteResultadoLab(dato);
    }

    public List getPacienteResultadoLabEco(Cuadernos dato) {
        return this.cuadernosDao.getPacienteResultadoLabEco(dato);
    }

    public List getPacienteResultadoLabX(Cuadernos dato) {
        return this.cuadernosDao.getPacienteResultadoLabX(dato);
    }

    public List getPacienteResultadoLabLab(Cuadernos dato) {
        return this.cuadernosDao.getPacienteResultadoLabLab(dato);
    }

    public List getPacienteResultadoLabEndo(Cuadernos dato) {
        return this.cuadernosDao.getPacienteResultadoLabEndo(dato);
    }

    public List getDetalleFotosEndo(Cuadernos dato) {
        return this.cuadernosDao.getDetalleFotosEndo(dato);
    }

    public List getPacienteLaboratorio(Cuadernos dato) {
        return this.cuadernosDao.getPacienteLaboratorio(dato);
    }

    public List getDatosLaboratorios(Cuadernos dato) {
        return this.cuadernosDao.getDatosLaboratorios(dato);
    }

    public Cuadernos getDatosLaboratorioC(Cuadernos dato) {
        return this.cuadernosDao.getDatosLaboratorioC(dato);
    }

    public List getLabPacPendiente(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendiente(dato);
    }

    public List getLabPacPendientePago(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendientePago(dato);
    }

    public List getLabPacPasados(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPasados(dato);
    }

    public List getLabPacPendienteRx(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendienteRx(dato);
    }

    public List getLabPacPendienteEco(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendienteEco(dato);
    }

    public List getLabSSPAM(Cuadernos dato) {
        return this.cuadernosDao.getLabSSPAM(dato);
    }

    public List getLabPendienteAmb(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteAmb(dato);
    }

    public List getLabPendienteAmbFecha(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteAmbFecha(dato);
    }

    public List getLabPendienteInter(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteInter(dato);
    }

    public List getLabPendienteInterFecha(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteInterFecha(dato);
    }

    public List getLabPendienteInterNum(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteInterNum(dato);
    }

    public List getLabPendienteInterHcl(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteInterHcl(dato);
    }

    public List getLabPacPendienteInter(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendienteInter(dato);
    }

    public List getLabPacPendienteRxInter(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendienteRxInter(dato);
    }

    public List getLabPacPendienteEcoInter(Cuadernos dato) {
        return this.cuadernosDao.getLabPacPendienteEcoInter(dato);
    }

    public List getLabPendiente(Cuadernos dato) {
        return this.cuadernosDao.getLabPendiente(dato);
    }

    public List getLabPendienteMed(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteMed(dato);
    }

    public List getLabPendienteRx(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteRx(dato);
    }

    public List getLabPendienteImp(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteImp(dato);
    }

    public List getLabPendienteEco(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteEco(dato);
    }

    public List getLabPendienteEpi(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteEpi(dato);
    }

    public List getLabPendienteCNS(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteCNS(dato);
    }

    public List getLabPendienteCNSUlt(Cuadernos dato) {
        return this.cuadernosDao.getLabPendienteCNSUlt(dato);
    }

    public List getHemograma(Cuadernos dato) {
        return this.cuadernosDao.getHemograma(dato);
    }

    public List getOrina(Cuadernos dato) {
        return this.cuadernosDao.getOrina(dato);
    }

    public List getDetalleEcos(Cuadernos dato) {
        return this.cuadernosDao.getDetalleEcos(dato);
    }

    public List getDetalleEcosI(Cuadernos dato) {
        return this.cuadernosDao.getDetalleEcosI(dato);
    }

    public List getLabRealizadoAmb(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoAmb(dato);
    }

    public List getLabRealizadoAmbFecha(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoAmbFecha(dato);
    }

    public List getLabRealizadoAmbNombre(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoAmbNombre(dato);
    }

    public List getLabRealizadoInter(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoInter(dato);
    }

    public List getLabRealizadoInterFecha(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoInterFecha(dato);
    }

    public List getLabRealizadoInterNombre(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoInterNombre(dato);
    }

    public List getLabRealizadoInterHcl(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoInterHcl(dato);
    }

    public List getLabRealizadoInterNum(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoInterNum(dato);
    }

    public List getLabRealizadoTotal(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoTotal(dato);
    }

    public List getLabRealizadoSumi(Cuadernos dato) {
        return this.cuadernosDao.getLabRealizadoSumi(dato);
    }

    public List getPedidoLab(Cuadernos dato) {
        return this.cuadernosDao.getPedidoLab(dato);
    }

    public List getDatoPedidoLab(Cuadernos dato) {
        return this.cuadernosDao.getDatoPedidoLab(dato);
    }

    public List getLabMedico(Cuadernos dato) {
        return this.cuadernosDao.getLabMedico(dato);
    }

    public List getLabMedicoEco(Cuadernos dato) {
        return this.cuadernosDao.getLabMedicoEco(dato);
    }

    public List getLabMedicoX(Cuadernos dato) {
        return this.cuadernosDao.getLabMedicoX(dato);
    }

    public List getLabMedicoLab(Cuadernos dato) {
        return this.cuadernosDao.getLabMedicoLab(dato);
    }

    public Cuadernos getDatos302_5(Cuadernos dato) {
        return this.cuadernosDao.getDatos302_5(dato);
    }

    public Cuadernos getDatos302imc(Cuadernos dato) {
        return this.cuadernosDao.getDatos302imc(dato);
    }

    public void setEliminarLaboratorioC(Cuadernos dato) {
        this.cuadernosDao.setEliminarLaboratorioC(dato);
    }

    public void setCrearLaboratorioC(Cuadernos dato) {
        this.cuadernosDao.setCrearLaboratorioC(dato);
    }

    public void setCrearProduccion(Cuadernos dato) {
        this.cuadernosDao.setCrearProduccion(dato);
    }

    public void setCrearProduccionEmerg(Cuadernos dato) {
        this.cuadernosDao.setCrearProduccionEmerg(dato);
    }

    public void setCrearPedidoLab(Cuadernos dato) {
        this.cuadernosDao.setCrearPedidoLab(dato);
    }

    public void setModificarPedidoLab(Cuadernos dato) {
        this.cuadernosDao.setModificarPedidoLab(dato);
    }

    public void setModificarEcoDetalle(Cuadernos dato) {
        this.cuadernosDao.setModificarEcoDetalle(dato);
    }

    public void setModificarPedidoLabo(Cuadernos dato) {
        this.cuadernosDao.setModificarPedidoLabo(dato);
    }

    public void setModificarImpOrina(Cuadernos dato) {
        this.cuadernosDao.setModificarImpOrina(dato);
    }

    public void setModificarImpRiesgo(Cuadernos dato) {
        this.cuadernosDao.setModificarImpRiesgo(dato);
    }

    public void setModificarLaboratorioC(Cuadernos dato) {
        this.cuadernosDao.setModificarLaboratorioC(dato);
    }

    public void setModificarLaboratorioCobrar(Cuadernos dato) {
        this.cuadernosDao.setModificarLaboratorioCobrar(dato);
    }

    public void setModificarHemograma(Cuadernos dato) {
        this.cuadernosDao.setModificarHemograma(dato);
    }

    public void setModificarOrina(Cuadernos dato) {
        this.cuadernosDao.setModificarOrina(dato);
    }

    public void setCrearDetalleSangre(Cuadernos dato) {
        this.cuadernosDao.setCrearDetalleSangre(dato);
    }

    public void setCrearDetalleOrina(Cuadernos dato) {
        this.cuadernosDao.setCrearDetalleOrina(dato);
    }

    public void setReservaLabEcografia(Cuadernos dato) {
        this.cuadernosDao.setReservaLabEcografia(dato);
    }

    public List getReporteCIE10(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10(dato);
    }

    public List getReporteCIE10edadPersonal(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10edadPersonal(dato);
    }

    public List getReporteCIE10xMedico(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10xMedico(dato);
    }

    public List getReporteCIE10Urgencia(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10Urgencia(dato);
    }

    public List getReporteCIE10edad(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10edad(dato);
    }

    public List getReporteCIE10IngInter(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10IngInter(dato);
    }

    public List getReporteCIE10EgrInter(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10EgrInter(dato);
    }

    public List getReporteProduccionG1(Cuadernos dato) {
        return this.cuadernosDao.getReporteProduccionG1(dato);
    }

    public List getReporteProduccion(Cuadernos dato) {
        return this.cuadernosDao.getReporteProduccion(dato);
    }

    public List getReporteProduccion2(Cuadernos dato) {
        return this.cuadernosDao.getReporteProduccion2(dato);
    }

    public List getReporteCIE10_C1(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_C1(dato);
    }

    public List getReporteCIE10_C2(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_C2(dato);
    }

    public List getReporteCIE10_C3(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_C3(dato);
    }

    public List getReporteCIE10_C4(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_C4(dato);
    }

    public List getReporteCIE10_C7(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_C7(dato);
    }

    public List getReporteCIE10_CEmerg(Cuadernos dato) {
        return this.cuadernosDao.getReporteCIE10_CEmerg(dato);
    }

    public List getListarEstadisticas(Cuadernos dato) {
        return this.cuadernosDao.getListarEstadisticas(dato);
    }

    public List getListarEstadisticasHospi(Cuadernos dato) {
        return this.cuadernosDao.getListarEstadisticasHospi(dato);
    }

    public List getPacienteCuaderno7(int id_historial) {
        return this.cuadernosDao.getPacienteCuaderno7(id_historial);
    }

    public void setEliminarCuaderno7(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno7(dato);
    }

    public void setCrearCuaderno7(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno7(dato);
    }

    public Cuadernos getSnis301Odontologia(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Odontologia(dato);
    }

    public Cuadernos getSnis301Nutricion(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Nutricion(dato);
    }

    public List getPacienteCemergencia(int id_historial) {
        return this.cuadernosDao.getPacienteCemergencia(id_historial);
    }

    public List getPacienteCuaderno1(int id_historial) {
        return this.cuadernosDao.getPacienteCuaderno1(id_historial);
    }

    public void setCrearCuaderno1(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno1(dato);
    }

    public void setCrearTransferencia(Cuadernos dato) {
        this.cuadernosDao.setCrearTransferencia(dato);
    }

    public void setCrearAdmision(Cuadernos dato) {
        this.cuadernosDao.setCrearAdmision(dato);
    }

    public void setCrearEpicrisis(Cuadernos dato) {
        this.cuadernosDao.setCrearEpicrisis(dato);
    }

    public void setCrearProtocolo(Cuadernos dato) {
        this.cuadernosDao.setCrearProtocolo(dato);
    }

    public void setCrearSolSangre(Cuadernos dato) {
        this.cuadernosDao.setCrearSolSangre(dato);
    }

    public void setCrearOftalmologia(Cuadernos dato) {
        this.cuadernosDao.setCrearOftalmologia(dato);
    }

    public void setModificarCuaderno1(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno1(dato);
    }

    public void setModificarCodigo(Cuadernos dato) {
        this.cuadernosDao.setModificarCodigo(dato);
    }

    public void setModificarTranferencia(Cuadernos dato) {
        this.cuadernosDao.setModificarTranferencia(dato);
    }

    public void setModificarAdmision(Cuadernos dato) {
        this.cuadernosDao.setModificarAdmision(dato);
    }

    public void setModificarEpicrisis(Cuadernos dato) {
        this.cuadernosDao.setModificarEpicrisis(dato);
    }

    public void setModificarPrococolos(Cuadernos dato) {
        this.cuadernosDao.setModificarPrococolos(dato);
    }

    public void setModificarSangre(Cuadernos dato) {
        this.cuadernosDao.setModificarSangre(dato);
    }

    public void setEliminarCuaderno1(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno1(dato);
    }

    public List getPacienteCuadernof(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuadernof(dato);
    }

    public void setCrearCuadernof(Cuadernos dato) {
        this.cuadernosDao.setCrearCuadernof(dato);
    }

    public void setEliminarCuadernof(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuadernof(dato);
    }

    public Cuadernos getSnis301Externa(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Externa(dato);
    }

    public List getPacienteCuaderno3(int id_historial) {
        return this.cuadernosDao.getPacienteCuaderno3(id_historial);
    }

    public void setEliminarCuaderno3(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno3(dato);
    }

    public void setCrearCuaderno3(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno3(dato);
    }

    public Cuadernos getSnis301Prevencion(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Prevencion(dato);
    }

    public void setEliminarCuaderno2(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno2(dato);
    }

    public void setCrearCuaderno2(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno2(dato);
    }

    public Cuadernos getSnis301ControlPre(Cuadernos dato) {
        return this.cuadernosDao.getSnis301ControlPre(dato);
    }

    public List getPacienteCuaderno2(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno2(dato);
    }

    public Cuadernos getVerCuaderno2Ult(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2Ult(dato);
    }

    public Cuadernos getVerCuaderno3Count(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3Count(dato);
    }

    public Cuadernos getVerCuaderno4PaciUlt(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4PaciUlt(dato);
    }

    public Cuadernos getVerCuaderno5Ult(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno5Ult(dato);
    }

    public void setEliminarCuaderno4(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno4(dato);
    }

    public void setCrearCuaderno4(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno4(dato);
    }

    public void setModificarCuaderno4(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4(dato);
    }

    public void setModificarCuaderno4A(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4A(dato);
    }

    public void setModificarCuaderno4B(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4B(dato);
    }

    public void setModificarCuaderno4C(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4C(dato);
    }

    public void setModificarCuaderno4D(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4D(dato);
    }

    public void setModificarCuaderno4E(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4E(dato);
    }

    public void setModificarCuaderno4F(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4F(dato);
    }

    public void setModificarCuaderno4G(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno4G(dato);
    }

    public void setModificarCuaderno24(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno24(dato);
    }

    public List getPacienteCuaderno4(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno4(dato);
    }

    public Cuadernos getPacienteCuaderno4A(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno4A(dato);
    }

    public List getVerCuaderno4Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4Uni(dato);
    }

    public List getDesarrolloSimple(Cuadernos dato) {
        return this.cuadernosDao.getDesarrolloSimple(dato);
    }

    public List getVerDesarrollo(Cuadernos dato) {
        return this.cuadernosDao.getVerDesarrollo(dato);
    }

    public List getVerDesarrolloSimple(Cuadernos dato) {
        return this.cuadernosDao.getVerDesarrolloSimple(dato);
    }

    public List getVerDesarrollo2(Cuadernos dato) {
        return this.cuadernosDao.getVerDesarrollo2(dato);
    }

    public void setEliminarCuaderno5(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno5(dato);
    }

    public void setCrearCuaderno5(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno5(dato);
    }

    public List getPacienteCuaderno5(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno5(dato);
    }

    public List getVerCuaderno5Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno5Uni(dato);
    }

    public List getVerCuaderno5(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno5(dato);
    }

    public List getVerCuaderno5Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno5Todos(dato);
    }

    public List getVerCuadernoInterServicio(Cuadernos dato) {
        return this.cuadernosDao.getVerCuadernoInterServicio(dato);
    }

    public List getVerCuaderno5Piso(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno5Piso(dato);
    }

    public List getVerSnis302(Cuadernos dato) {
        return this.cuadernosDao.getVerSnis302(dato);
    }

    public void setModificarCuaderno5(Cuadernos dato) {
        this.cuadernosDao.setModificarCuaderno5(dato);
    }

    public void setModificarAdmision2(Cuadernos dato) {
        this.cuadernosDao.setModificarAdmision2(dato);
    }

    public void setEliminarVacunas(Cuadernos dato) {
        this.cuadernosDao.setEliminarVacunas(dato);
    }

    public void setCrearVacunas(Cuadernos dato) {
        this.cuadernosDao.setCrearVacunas(dato);
    }

    public List getPacienteVacunas(Cuadernos dato) {
        return this.cuadernosDao.getPacienteVacunas(dato);
    }
    //control de calidad

    public List getO80_C2(Cuadernos dato) {
        return this.cuadernosDao.getO80_C2(dato);
    }

    public List getPC42_C2(Cuadernos dato) {
        return this.cuadernosDao.getPC42_C2(dato);
    }

    public List getPC64_C2(Cuadernos dato) {
        return this.cuadernosDao.getPC64_C2(dato);
    }

    public List getZ34_C2(Cuadernos dato) {
        return this.cuadernosDao.getZ34_C2(dato);
    }

    public List getZ39_C2(Cuadernos dato) {
        return this.cuadernosDao.getZ39_C2(dato);
    }

    public List getZ301_C3(Cuadernos dato) {
        return this.cuadernosDao.getZ301_C3(dato);
    }

    public List getZ305_C3(Cuadernos dato) {
        return this.cuadernosDao.getZ305_C3(dato);
    }

    public List getZ124_C3(Cuadernos dato) {
        return this.cuadernosDao.getZ124_C3(dato);
    }

    public List getC2_O80(Cuadernos dato) {
        return this.cuadernosDao.getC2_O80(dato);
    }

    public List getC2_PC42(Cuadernos dato) {
        return this.cuadernosDao.getC2_PC42(dato);
    }

    public List getC2_PC64(Cuadernos dato) {
        return this.cuadernosDao.getC2_PC64(dato);
    }

    public List getC2_Z34(Cuadernos dato) {
        return this.cuadernosDao.getC2_Z34(dato);
    }

    public List getC2_Z39(Cuadernos dato) {
        return this.cuadernosDao.getC2_Z39(dato);
    }

    public List getC3_Z301(Cuadernos dato) {
        return this.cuadernosDao.getC3_Z301(dato);
    }

    public List getC3_Z305(Cuadernos dato) {
        return this.cuadernosDao.getC3_Z305(dato);
    }

    public List getC3_Z124(Cuadernos dato) {
        return this.cuadernosDao.getC3_Z124(dato);
    }

    public List getZ301_DIU(Cuadernos dato) {
        return this.cuadernosDao.getZ301_DIU(dato);
    }

    public List getPC23_C3(Cuadernos dato) {
        return this.cuadernosDao.getPC23_C3(dato);
    }

    public List getC3_PC23(Cuadernos dato) {
        return this.cuadernosDao.getC3_PC23(dato);
    }

    public List getGrafica1(Cuadernos dato) {
        return this.cuadernosDao.getGrafica1(dato);
    }

    public List getGrafica2(Cuadernos dato) {
        return this.cuadernosDao.getGrafica2(dato);
    }

    public List getGrafica3(Cuadernos dato) {
        return this.cuadernosDao.getGrafica3(dato);
    }

    public List getGrafica4(Cuadernos dato) {
        return this.cuadernosDao.getGrafica4(dato);
    }

    public List getPC23_DEPO(Cuadernos dato) {
        return this.cuadernosDao.getPC23_DEPO(dato);
    }

    public List getCensoDiario(Cuadernos dato) {
        return this.cuadernosDao.getCensoDiario(dato);
    }

    public Cuadernos getSnis301Vacunas(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Vacunas(dato);
    }

    public List getOdonMensual(Cuadernos dato) {
        return this.cuadernosDao.getOdonMensual(dato);
    }

    public void setEliminarCuaderno6(Cuadernos dato) {
        this.cuadernosDao.setEliminarCuaderno6(dato);
    }

    public void setCrearCuaderno6(Cuadernos dato) {
        this.cuadernosDao.setCrearCuaderno6(dato);
    }

    public List getPacienteCuaderno6(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno6(dato);
    }

    public List getPacienteCuaderno6H(Cuadernos dato) {
        return this.cuadernosDao.getPacienteCuaderno6H(dato);
    }

    public List getListaPacientesCuaderno6(Cuadernos dato) {
        return this.cuadernosDao.getListaPacientesCuaderno6(dato);
    }

    public List getListaPacientesCuaderno6Total(Cuadernos dato) {
        return this.cuadernosDao.getListaPacientesCuaderno6Total(dato);
    }

    public List getVerCuaderno6Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno6Uni(dato);
    }

    public List getVerEcografias(Cuadernos dato) {
        return this.cuadernosDao.getVerEcografias(dato);
    }

    public List getVerEcografiasAdmi(Cuadernos dato) {
        return this.cuadernosDao.getVerEcografiasAdmi(dato);
    }

    public Cuadernos getSnis301Enfermeria(Cuadernos dato) {
        return this.cuadernosDao.getSnis301Enfermeria(dato);
    }

    public List getVerCuaderno7(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno7(dato);
    }

    public List getVerCuaderno7Paci(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno7Paci(dato);
    }

    public List getVerCuaderno7Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno7Todos(dato);
    }

    public List getReporteDental(Cuadernos dato) {
        return this.cuadernosDao.getReporteDental(dato);
    }

    public List getReporteDentalT(Cuadernos dato) {
        return this.cuadernosDao.getReporteDentalT(dato);
    }

    public List getVerCuaderno7Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno7Uni(dato);
    }

    public List getVerVacunas(Cuadernos dato) {
        return this.cuadernosDao.getVerVacunas(dato);
    }

    public List getVerVacunasTodos(Cuadernos dato) {
        return this.cuadernosDao.getVerVacunasTodos(dato);
    }

    public List getVerVacunasUni(Cuadernos dato) {
        return this.cuadernosDao.getVerVacunasUni(dato);
    }

    public List getVerCemergencias(Cuadernos dato) {
        return this.cuadernosDao.getVerCemergencias(dato);
    }

    public List getVerCemergenciasTodos(Cuadernos dato) {
        return this.cuadernosDao.getVerCemergenciasTodos(dato);
    }

    public List getVerCuaderno1(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1(dato);
    }

    public List getVerCuaderno1Sspam(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1Sspam(dato);
    }

    public List getVerCuaderno1CNS(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1CNS(dato);
    }

    public List getVerCuaderno1CNSInter(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1CNSInter(dato);
    }

    public List getVerCuaderno1SspamTodos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1SspamTodos(dato);
    }

    public List getVerCuaderno1Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1Uni(dato);
    }

    public List getVerCuaderno1T(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1T(dato);
    }

    public List getVerCuaderno1Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1Todos(dato);
    }

    public List getVerCuaderno1Cie(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1Cie(dato);
    }

    public List getVerCuaderno1CieUni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1CieUni(dato);
    }

    public List getVerCuaderno1CieMorbi(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno1CieMorbi(dato);
    }

    public List getReporteSNISConsulta(Cuadernos dato) {
        return this.cuadernosDao.getReporteSNISConsulta(dato);
    }

    public List getReporteSNISConsulta2(Cuadernos dato) {
        return this.cuadernosDao.getReporteSNISConsulta2(dato);
    }

    public List getVerCuadernoFisio(Cuadernos dato) {
        return this.cuadernosDao.getVerCuadernoFisio(dato);
    }

    public List getVerCuadernofTodos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuadernofTodos(dato);
    }

    public List getVerCuadernoFisioTodos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuadernoFisioTodos(dato);
    }

    public List getVerCuadernof(Cuadernos dato) {
        return this.cuadernosDao.getVerCuadernof(dato);
    }

    public List getVerCuaderno6(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno6(dato);
    }

    public List getVerCuaderno6Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno6Todos(dato);
    }

    public List getVerCuaderno6C1(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno6C1(dato);
    }

    public List getVerCuaderno6C4(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno6C4(dato);
    }

    public List getVerCuaderno2(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2(dato);
    }

    public List getVerCuaderno2Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2Todos(dato);
    }

    public List getVerCuaderno2C3(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2C3(dato);
    }

    public List getVerCuaderno2C3Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2C3Uni(dato);
    }

    public List getReporteProduccionPrenatal(Cuadernos dato) {
        return this.cuadernosDao.getReporteProduccionPrenatal(dato);
    }

    public List getVerCuaderno2Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2Uni(dato);
    }

    public List getVerCuaderno2Parto(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno2Parto(dato);
    }

    public List getVerCuaderno3(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3(dato);
    }

    public List getVerCuaderno3Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3Todos(dato);
    }

    public List getVerCuaderno3Prod1(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3Prod1(dato);
    }

    public List getVerCuaderno3Prod2(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3Prod2(dato);
    }

    public List getVerCuaderno3Uni(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno3Uni(dato);
    }

    public List getDatosUni(Cuadernos dato) {
        return this.cuadernosDao.getDatosUni(dato);
    }

    public List getVerCuaderno4_C2(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C2(dato);
    }

    public List getVerCuaderno4_C22014FIndi(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C22014FIndi(dato);
    }

    public List getVerCuaderno4_C2Consul(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C2Consul(dato);
    }

    public List getVerCuaderno4(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4(dato);
    }

    public List getVerCuaderno4_C2Creci(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C2Creci(dato);
    }

    public List getVerCuaderno4_C22014D(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C22014D(dato);
    }

    public List getVerCuaderno4_C22014F(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C22014F(dato);
    }

    public List getVerCuaderno4_C22014DIndi(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4_C22014DIndi(dato);
    }

    public List getVerCuaderno4Paci(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4Paci(dato);
    }

    public List getVerCuaderno4Todos(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4Todos(dato);
    }

    public List getVerCuaderno4Creci(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4Creci(dato);
    }

    public List getVerCuaderno4CreciDA(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4CreciDA(dato);
    }

    public List getVerCuaderno4CreciNuevoDA(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4CreciNuevoDA(dato);
    }

    public List getVerCuaderno4Enfer(Cuadernos dato) {
        return this.cuadernosDao.getVerCuaderno4Enfer(dato);
    }
    //Cuentas

    public List getListarLibroMayor(Cuentas dato) {
        return this.cuentasDao.getListarLibroMayor(dato);
    }

    public List getListarLibroDiario(int id_librodiario) {
        return this.cuentasDao.getListarLibroDiario(id_librodiario);
    }

    public List getListarCuentasNom(String cuenta) {
        return this.cuentasDao.getListarCuentasNom(cuenta);
    }

    public List getListarCuentasCot() {
        return this.cuentasDao.getListarCuentasCot();
    }

    public List getListarCuentas() {
        return this.cuentasDao.getListarCuentas();
    }

    public Cuentas getDatosCuenta(int id_cuenta) {
        return this.cuentasDao.getDatosCuenta(id_cuenta);
    }

    public void setCrearCuenta(Cuentas cuenta) {
        this.cuentasDao.setCrearCuenta(cuenta);
    }

    public void setModificarCuenta(Cuentas cuenta) {
        this.cuentasDao.setModificarCuenta(cuenta);
    }

    public void setEliminarCuenta(Cuentas cuenta) {
        this.cuentasDao.setEliminarCuenta(cuenta);
    }

    //Transacciones
    public int getNumLibroDiario() {
        return this.cuentasDao.getNumLibroDiario();
    }

    public List getListarTransacciones() {
        return this.cuentasDao.getListarTransacciones();
    }

    public Cuentas getDatosTransaccion(int id_transaccion) {
        return this.cuentasDao.getDatosTransaccion(id_transaccion);
    }

    public void setCrearTransaccion(Cuentas transaccion) {
        this.cuentasDao.setCrearTransaccion(transaccion);
    }

    public void setModificarTransaccion(Cuentas transaccion) {
        this.cuentasDao.setModificarTransaccion(transaccion);
    }

    public void setEliminarTransaccion(Cuentas transaccion) {
        this.cuentasDao.setEliminarTransaccion(transaccion);
    }

    public void setCrearLibroDiario(Cuentas transaccion) {
        this.cuentasDao.setCrearLibroDiario(transaccion);
    }

    public void setEliminarLibroDiario(Cuentas transaccion) {
        this.cuentasDao.setEliminarLibroDiario(transaccion);
    }
    //Carpetas

    public List getListarCarpetas(Carpetas carpeta) {
        return this.carpetasDao.getListarCarpetas(carpeta);
    }

    public List getListarDependientes(Carpetas carpeta) {
        return this.carpetasDao.getListarDependientes(carpeta);
    }

    public List getListarCarpetasCaja(Carpetas carpeta) {
        return this.carpetasDao.getListarCarpetasCaja(carpeta);
    }

    public List getListarCarpetasFam(Carpetas carpeta) {
        return this.carpetasDao.getListarCarpetasFam(carpeta);
    }

    public List getListarCarpetasAse(Carpetas carpeta) {
        return this.carpetasDao.getListarCarpetasAse(carpeta);
    }

    public List getListarCarpetasId(Carpetas carpeta) {
        return this.carpetasDao.getListarCarpetasId(carpeta);
    }

    public Carpetas getDatosCarpeta(int id_carpeta) {
        return this.carpetasDao.getDatosCarpeta(id_carpeta);
    }

    public Carpetas getDatosCarpetaPac(int id_paciente) {
        return this.carpetasDao.getDatosCarpetaPac(id_paciente);
    }

    public void setCrearCarpeta(Carpetas carpeta) {
        this.carpetasDao.setCrearCarpeta(carpeta);
    }

    public void setModificarCarpeta(Carpetas carpeta) {
        this.carpetasDao.setModificarCarpeta(carpeta);
    }

    public void setEliminarCarpeta(Carpetas carpeta) {
        this.carpetasDao.setEliminarCarpeta(carpeta);
    }

    public List getListarPacientesD(int id_carpeta) {
        return this.carpetasDao.getListarPacientesD(id_carpeta);
    }

    public List getListarPacientesDJefe(int id_carpeta) {
        return this.carpetasDao.getListarPacientesDJefe(id_carpeta);
    }

    public void setCrearPacienteD(Carpetas paciente) {
        this.carpetasDao.setCrearPacienteD(paciente);
    }

    public void setEliminarPacienteD(Carpetas paciente) {
        this.carpetasDao.setEliminarPacienteD(paciente);
    }

    public List getSalmi(Cuadernos dato) {
        return this.cuadernosDao.getSalmi(dato);
    }

    public List getPersonasSalmi(Cuadernos dato) {
        return this.cuadernosDao.getPersonasSalmi(dato);
    }

    public List getPacientesSalmi(Cuadernos dato) {
        return this.cuadernosDao.getPacientesSalmi(dato);
    }

    public List getSalidasSalmiSumi(Cuadernos dato) {
        return this.cuadernosDao.getSalidasSalmiSumi(dato);
    }

    public List getLotesSalmi(Cuadernos dato) {
        return this.cuadernosDao.getLotesSalmi(dato);
    }

    public List getRelSalPreSalmi(Cuadernos dato) {
        return this.cuadernosDao.getRelSalPreSalmi(dato);
    }

    public List getMovimientoSalmi(Cuadernos dato) {
        return this.cuadernosDao.getMovimientoSalmi(dato);
    }

    public List getDiagnosticoSalmi(Cuadernos dato) {
        return this.cuadernosDao.getDiagnosticoSalmi(dato);
    }

    public List getCie10Salmi(Cuadernos dato) {
        return this.cuadernosDao.getCie10Salmi(dato);
    }

    public List getMovSalmi(Cuadernos dato) {
        return this.cuadernosDao.getMovSalmi(dato);
    }

    public List getLotesSalmiUni(Cuadernos dato) {
        return this.cuadernosDao.getLotesSalmiUni(dato);
    }

    public List getFopo(Cuadernos dato) {
        return this.cuadernosDao.getFopo(dato);
    }

    public List getIMMSalmi(Cuadernos dato) {
        return this.cuadernosDao.getIMMSalmi(dato);
    }

    public List getPrestacion(Cuadernos dato) {
        return this.cuadernosDao.getPrestacion(dato);
    }

    public List getSalidasSalmiNutri(Cuadernos dato) {
        return this.cuadernosDao.getSalidasSalmiNutri(dato);
    }

    public List getMedNoe(Cuadernos dato) {
        return this.cuadernosDao.getMedNoe(dato);
    }

    public List getSalidasSalmiOtro(Cuadernos dato) {
        return this.cuadernosDao.getSalidasSalmiOtro(dato);
    }

    public List getSalidasSalmiExt(Cuadernos dato) {
        return this.cuadernosDao.getSalidasSalmiExt(dato);
    }

    public List getSelecionMed(Cuadernos dato) {
        return this.cuadernosDao.getSelecionMed(dato);
    }

    public List getInvMed(Cuadernos dato) {
        return this.cuadernosDao.getSalmi(dato);
    }

    //Actividad
    public List getListarActividad(Actividad actividad) {
        return this.actividadDao.getListarActividad(actividad);
    }

    public List getListarActividadTot(Actividad actividad) {
        return this.actividadDao.getListarActividadTot(actividad);
    }

    public List getListarActividadTodos(Actividad actividad) {
        return this.actividadDao.getListarActividadTodos(actividad);
    }

    public Actividad getDatosActividad(int id_actividad) {
        return this.actividadDao.getDatosActividad(id_actividad);
    }

    public void setCrearActividad(Actividad actividad) {
        this.actividadDao.setCrearActividad(actividad);
    }

    public void setModificarActividad(Actividad actividad) {
        this.actividadDao.setModificarActividad(actividad);
    }

    public void setEliminarActividad(Actividad actividad) {
        this.actividadDao.setEliminarActividad(actividad);
    }

//quirofanos
    public List getListarQuirofanos(Quirofanos quirofano) {
        return this.quirofanosDao.getListarQuirofanos(quirofano);
    }

    public List getListarQuirofanosLibres(Quirofanos quirofano) {
        return this.quirofanosDao.getListarQuirofanosLibres(quirofano);
    }

    public List getListarQuirofanosLibres1(Quirofanos quirofano) {
        return this.quirofanosDao.getListarQuirofanosLibres1(quirofano);
    }

    public List getListarQuirofanosLibres2(Quirofanos quirofano) {
        return this.quirofanosDao.getListarQuirofanosLibres2(quirofano);
    }

    public Quirofanos getDatosQuirofano(Quirofanos quirofano) {
        return this.quirofanosDao.getDatosQuirofano(quirofano);
    }

    public void setCrearQuirofano(Quirofanos quirofano) {
        this.quirofanosDao.setCrearQuirofano(quirofano);
    }

    public void setCrearReservaQuirofano(Quirofanos quirofano) {
        this.quirofanosDao.setCrearReservaQuirofano(quirofano);
    }

    public void setModificarQuirofano(Quirofanos quirofano) {
        this.quirofanosDao.setModificarQuirofano(quirofano);
    }

    public void setEliminarQuirofano(Quirofanos quirofano) {
        this.quirofanosDao.setEliminarQuirofano(quirofano);
    }

    public List getAtencionFicha(Pacientes dato) {
        return this.pacientesDao.getAtencionFicha(dato);
    }

    public void setCrearFicha(Pacientes paciente) {
        this.pacientesDao.setCrearFicha(paciente);
    }

    //Farmacias
    public List getListarFarmacias(Farmacias farmacia) {
        return this.farmaciasDao.getListarFarmacias(farmacia);
    }

    public List getListarFarmaciasAsig(Farmacias farmacia) {
        return this.farmaciasDao.getListarFarmaciasAsig(farmacia);
    }

    public List getListarFarmaciasHosp(Farmacias farmacia) {
        return this.farmaciasDao.getListarFarmaciasHosp(farmacia);
    }

    public Farmacias getDatosFarmacia(Farmacias farmacia) {
        return this.farmaciasDao.getDatosFarmacia(farmacia);
    }

    public void setCrearFarmacia(Farmacias farmacia) {
        this.farmaciasDao.setCrearFarmacia(farmacia);
    }

    public void setModificarFarmacia(Farmacias farmacia) {
        this.farmaciasDao.setModificarFarmacia(farmacia);
    }

    public void setEliminarFarmacia(Farmacias farmacia) {
        this.farmaciasDao.setEliminarFarmacia(farmacia);
    }

}
