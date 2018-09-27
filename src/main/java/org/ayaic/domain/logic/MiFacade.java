package org.ayaic.domain.logic;

import java.util.List;
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

public interface MiFacade {

    Enlaces getEnlBuscarEnlace(Enlaces enlace);
    //Clientes

    Clientes getBuscarConexion(Usuarios usuario);
    //fin Clientes

    //Roles
    Roles getBuscarRol(Roles rol);

    List getListarRoles();

    void setCrearRol(Roles rol);

    void setModificarRol(Roles rol);

    void setEliminarRol(Roles rol);

    List getListarRolesCliente(Roles rol);

    Roles getBuscarRolCliente(Roles rol);

    List getListarAlmacenesCliente(Roles rol);

    Roles getBuscarAlmacenCliente(Roles rol);
    //fin Roles

    //Menues
    List getListarCategorias(Categorias categoria);

    List getListarEnlaces(Enlaces enlace);

    Enlaces getBuscarEnlace(Enlaces enlace);
    //fin Menues

    //Personas
    List getListarPersonas(Personas persona);
    
    List getListarPersonasInter(Personas persona);

    List getListarPersonasFacturas(Personas persona);

    List getListarPersonasSoloAten(Personas persona);

    List getListarPersonasNombre(Personas persona);

    List getListarPersonasNombreMat(Personas persona);

    List getListarPersonasUsua(Personas persona);

    List getListarPersonasUsuaLocal(Personas persona);

    List getListarPersonaUnico(Personas persona);

    List getListarPersonasNombreLocal(Personas persona);

    List getListarPersonasLocal(Personas persona);

    List getListarPersonasEstab(Personas persona);

    List getListarPersonasFarma(Personas persona);
    
    List getListarPersonasFichas(Personas persona);

    Personas getDatosPersona(int id_persona);

    Personas getDatosPersonaInt(int id_persona);

    List getDatosPersonaInter(Personas persona);
    
    List getDatosPersonaConsul(Personas persona);

    List getDatosPersonaConsulUrgen(Personas persona);

    List getDatosPersonaConsulEmerg(Personas persona);

    List getDatosPersonaConsulLabos(Personas persona);

    List getDatosPersonaConsulCIE(Personas persona);

    List getListaMedicoAsig(Personas persona);

    Personas getBuscarPersona(int id_persona);

    void setCrearPersona(Personas persona);

    void setCrearPersonaEnfer(Personas persona);
    
    void setCrearPersonaInter(Personas persona);

    void setModificarPersona(Personas persona);

    void setModificarPersonaFic(Personas persona);

    void setEliminarPersona(Personas persona);
    
    void setEliminarPersonaInter(Personas persona);

    void setEliminarPersonaAsig(Personas persona);

    List getListarFichadas(Personas persona);
    //Fin Personas

    //Administrar noticias
    List getListarNoticias();

    List getListarTiposTableros();

    List getListarTiposAvisos();

    int setRegistrarTablero(Tableros tablero);

    Tableros getBuscarTablero(Tableros tablero);

    int setEliminarTablero(Tableros tablero);
    //Fin Administrar noticias

    //Usuarios
    int getVerificarUsuario(Usuarios usuario);

    int setRegistrarNuevaClave(Usuarios usuario);

    List getListaUsuarios(Usuarios usuario);

    List getListaUsuariosNombre(Usuarios usuario);

    List getListaUsuariosLocal(Usuarios usuario);

    List getListaUsuariosLocal2(Usuarios usuario);

    List getListaUsuariosNombreLocal(Usuarios usuario);

    Usuarios getDatosUsuario(int id_usuario);

    void setCrearUsuario(Usuarios usuario);
    
    void setCrearRegUsuario(Usuarios usuario);

    void setModificarUsuario(Usuarios usuario);

    void setEliminarUsuario(Usuarios usuario);
    //Fin Usuarios

    //Administrar enlaces
    List getListar_Enlaces();

    Menues getCategoria(String id_categoria);

    Menues getEnlace(int id_enlace);

    int setCrearEnlace(Menues enlace);

    int setEliminarEnlace(Menues enlace);

    int setModificarEnlace(Menues enlace);

    List getListaEnlaces(int id_categoria);

    List getListaCategorias();

    //menues
    Menues getUsrRolEnlace(Menues menu);

    List getListaUsrRolEnlaces(Menues menu);

    void setCrearMenu(Menues menu);

    void setEliminarMenu(Menues menu);

    int setEliminarCategoria(Menues categoria);

    int setModificarCategoria(Menues categoria);

    int setCrearCategoria(Menues categoria);

    int getBuscarIdCategoriaRepetido(Menues categoria);
    //usuarios rol

    List getListaUsrRoles(Menues usuario);

    Menues getUsrRol(Menues menu);

    void setCrearUsrRol(Menues rol);

    void setModificarUsrRol(Menues rol);

    void setEliminarUsrRol(Menues rol);

    // paises
    List getListarPaises();

    Paises getDatosPais(int id_pais);

    void setCrearPais(Paises pais);

    void setModificarPais(Paises pais);

    void setEliminarPais(Paises pais);

    // laboratorios
    List getListarLaboratorios(Laboratorios laboratorio);

    List getDatosLaboratorioRayos(Laboratorios laboratorio);

    List getListarLabGrupo(Laboratorios laboratorio);

    List getListarLabGrupoDet(Laboratorios laboratorio);

    Laboratorios getDatosLaboratorio(Laboratorios laboratorio);

    Laboratorios getDatosLaboratorioNombre(Laboratorios laboratorio);

    void setCrearLaboratorio(Laboratorios laboratorio);

    void setCrearLaboratorioGrupo(Laboratorios laboratorio);

    void setCrearLaboratorioGrupoDet(Laboratorios laboratorio);

    void setModificarLaboratorio(Laboratorios laboratorio);

    void setEliminarLaboratorio(Laboratorios laboratorio);

    void setEliminarLaboratorioGrupo(Laboratorios laboratorio);

    void setEliminarLaboratorioGrupoDet(Laboratorios laboratorio);

    // costos
    List getReporteCobro(Costos costo);

    List getListarCostos(Costos costo);

    List getListarCostosLabSumi(Costos costo);

    List getListarCostosLabora(Costos costo);

    List getListarLabosMedicoConf(Costos costo);

    List getListarLabosMedico(Costos costo);

    List getListarTodos(Costos costo);

    List getListarNombreCosto(Costos costo);

    List getListarCostosRx(Costos costo);

    List getListarCostosEco(Costos costo);

    List getListarNombreCosto22(Costos costo);

    Costos getDatosCosto(Costos costo);

    void setCrearCosto(Costos costo);

    void setModificarCosto(Costos costo);

    void setEliminarCosto(Costos costo);

    List getListarCostosRubro(Costos costo);

    List getListarCostosRubroFact(Costos costo);

    List getListaRubro();

    // camas
    List getListarCamas(Camas cama);

    List getListarCamasTotal(Camas cama);

    List getListarCamaUnit(Camas cama);

    List getListarCamaPiso(Camas cama);

    Camas getDatosCama(int id_cama);

    void setCrearCama(Camas cama);

    void setModificarCama(Camas cama);

    void setModificarCamaVacia(Camas cama);

    void setEliminarCama(Camas cama);

    List getListarCamasSala(Camas cama);

    List getListarCamasEstado(Camas cama);

    // sexos
    List getListarSexos();

    Sexos getDatosSexo(int id_sexo);

    void setCrearSexo(Sexos sexo);

    void setModificarSexo(Sexos sexo);

    void setEliminarSexo(Sexos sexo);

    ///proveedores
    List getListarProveedores(Proveedores proveedor);

    Proveedores getDatosProveedor(Proveedores proveedor);

    void setCrearProveedor(Proveedores proveedor);

    void setModificarProveedor(Proveedores proveedor);

    void setEliminarProveedor(Proveedores proveedor);

    // clientes
    List getListarClientes(Clieentes cliente);

    Clieentes getDatosCliente(Clieentes cliente);

    void setCrearCliente(Clieentes cliente);

    void setModificarCliente(Clieentes cliente);

    void setEliminarCliente(Clieentes cliente);

    // parentescos
    List getListarParentescos();

    Parentescos getDatosParentesco(int id_parentesco);

    void setCrearParentesco(Parentescos parentesco);

    void setModificarParentesco(Parentescos parentesco);

    void setEliminarParentesco(Parentescos parentesco);

    // rubros
    List getListarRubros(Rubros rubro);

    List getListarRubrosT(Rubros rubro);

    Rubros getDatosRubro(int id_rubro);

    void setCrearRubro(Rubros rubro);

    void setModificarRubro(Rubros rubro);

    void setEliminarRubro(Rubros rubro);

    // salas
    List getListarSalas();

    List getListarPisos(Salas sala);

    List getListarPisosTotal(Salas sala);

    List getListarSalasLibres(Salas sala);

    List getListarSalaPiso(Salas sala);

    List getListarSalaPisoTotal(Salas sala);

    Salas getDatosSala(int id_sala);

    void setCrearSala(Salas sala);

    void setModificarSala(Salas sala);

    void setEliminarSala(Salas sala);

    // seguros
    List getListarSeguros(String id_estado);

    Seguros getDatosSeguro(int id_seguro);

    void setCrearSeguro(Seguros seguro);

    void setModificarSeguro(Seguros seguro);

    void setEliminarSeguro(Seguros seguro);

    // empresas
    List getListarEmpresas(String id_estado);

    List getListarEmpresa2(Empresas empresa);

    List getListarEmpresa3(Empresas empresa);

    List getListarEmpresaCaja(Empresas empresa);

    List getListaEmpresa(Empresas empresa);

    List getListaEmpresaCod(Empresas empresa);

    List getListaEmpEmpresa(Empresas empresa);

    List getListaEmpEmpresaCod(Empresas empresa);

    Empresas getDatosEmpresa(int id_empresa);

    void setCrearEmpresa(Empresas empresa);

    void setModificarEmpresa(Empresas empresa);

    void setEliminarEmpresa(Empresas empresa);

    // documentos
    List getListarDocumentos();

    Documentos getDatosDocumento(int id_documento);

    void setCrearDocumento(Documentos documento);

    void setModificarDocumento(Documentos documento);

    void setEliminarDocumento(Documentos documento);

    // escolaridades
    List getListarEscolaridades();

    Escolaridades getDatosEscolaridad(int id_escolaridad);

    void setCrearEscolaridad(Escolaridades escolaridad);

    void setModificarEscolaridad(Escolaridades escolaridad);

    void setEliminarEscolaridad(Escolaridades escolaridad);

    // consultorios
    List getListarConsultorios(Consultorios a);
    
    List getListarConsultoriosInter(Consultorios a);

    List getListarConsultoriosEmerg(Consultorios a);

    List getListarConsultoriosTransf(Consultorios a);

    List getListarConsultoriosUrgen(Consultorios a);

    List getListarTipoRecetaCNS(Consultorios a);

    List getListarCentroCNS(Consultorios a);

    List getListarCentroCNSFar(Consultorios a);

    List getListarServicioCNS(Consultorios a);

    List getListarServicioCNSFar(Consultorios a);

    List getListarServicioCNS1(Consultorios a);

    List getListarServicioCNS2(Consultorios a);

    List getListarCodCNS(Consultorios a);

    List getListarConsultoriosGen(Consultorios a);

    Consultorios getDatosConsultorio(int id_cargo);

    void setCrearConsultorio(Consultorios a);

    void setModificarConsultorio(Consultorios a);

    void setEliminarConsultorio(Consultorios a);
    //cargos

    List getListarCargos();

    Consultorios getDatosCargo(int id_cargo);

    void setCrearCargo(Consultorios a);

    void setModificarCargo(Consultorios a);

    void setEliminarCargo(Consultorios a);

    // departamentos
    List getListarDepartamentos();

    Departamentos getDatosDepartamento(int id_departamento);

    void setCrearDepartamento(Departamentos departamento);

    void setModificarDepartamento(Departamentos departamento);

    void setEliminarDepartamento(Departamentos departamento);

    List getListarPaisDep(int id_pais);
    // Eciviles

    List getListarEciviles();

    Eciviles getDatosEcivil(int id_ecivil);

    void setCrearEcivil(Eciviles ecivil);

    void setModificarEcivil(Eciviles ecivil);

    void setEliminarEcivil(Eciviles ecivil);

    // Provincias
    List getListarProvincias();

    Provincias getDatosProvincia(int id_provincia);

    void setCrearProvincia(Provincias provincia);

    void setModificarProvincia(Provincias provincia);

    void setEliminarProvincia(Provincias provincia);

    List getListarPaisDepProv(Provincias provincia);

    //localidades
    List getListarPaisDepProvLoc(Localidades dato);

    List getListarMuniRed(Localidades dato);

    Localidades getDatosLocalidad(Localidades dato);

    Localidades getDatosEstable(Localidades dato);

    List getListarRed(Localidades dato);

    List getListarEstab(Localidades dato);

    List getListarEstabRef(Localidades dato);

    List getEstabTransCns(Localidades dato);

    List getEstabHabiles(Localidades dato);

    List getListarEstabGen(Localidades dato);

    List getListarEsta(Localidades dato);

    List getListarEstaUsua(Localidades dato);

    void setModificarEstab(Localidades dato);

    // pacientes
    List getDatosFaltaHcl(Pacientes dato);

    List getDatosFaltaHcl2(Pacientes dato);

    List getReporteAfiliadosSumi(Pacientes dato);

    List getReporteAfiliados(Pacientes dato);

    List getOtrosAfiliados(Pacientes dato);

    List getDatosAfiliados(Pacientes dato);

    List getDatosAfiliadosAct(Pacientes dato);

    List getDatosAfiliadosNada(Pacientes dato);

    void setModificarPacienteConsul(Pacientes paciente);
    
    void setModificarPacienteSumi(Pacientes paciente);

    void setModificarPacienteSumiEmpresa(Pacientes paciente);

    void setModificarPedidos(Pacientes paciente);

    void setModificarPedidoFactura(Pacientes paciente);

    void setUnirHCL(Pacientes paciente);

    void setCrearPacienteSumi(Pacientes paciente);
    
    void setCrearPacienteCaja(Pacientes paciente);

    void setCrearPacienteTicket(Pacientes paciente);

    String getNumRegistro(int id_paciente);
    
    String getNumProforma(Pacientes paciente) ; 

    String getNumAsegurado(int id_paciente);

    String getNumSumi(String id_estado, int cod_esta);

    List getListarPacientes(Pacientes paciente);
    
    List getListarPacientesFichas(Pacientes paciente);

    List getListarPacientesPPagos(Pacientes paciente);

    List getListarPacientesHisto(Pacientes paciente);

    List getListarPacientesHistoNombre(Pacientes paciente);

    List getListarPacientesHistoCie10(Pacientes paciente);

    List getListarPacientesNombreCns(Pacientes paciente);

    List getListarPacientesCnsUnico(Pacientes paciente);

    List getListarPacientesSeguro(Pacientes paciente);

    List getListarPacientesPrivNom(Pacientes paciente);

    List getListarPacientesBio(Pacientes paciente);

    List getListarPacientesAfi(Pacientes paciente);

    List getListarPacientesCI(Pacientes paciente);

    List getListarPacientesGrafica(Pacientes paciente);

    List getListarPacientesAfiR(Pacientes paciente);

    List getListarPacientesFN(Pacientes paciente);

    List getListarPacientesFNCns(Pacientes paciente);
    
    List getListarPacAfiliados(Pacientes paciente);

    List getListarPacientesHC(Pacientes paciente);

    List getListarPacientesEmp(Pacientes paciente);

    List getListarPacientesFarma(Pacientes paciente);

    List getListarPacienteFar(Pacientes paciente);

    List getListarPacientesCaja(Pacientes paciente);

    List getListarPacientesVigencia(Pacientes paciente);

    List getListarPacientesVigenciaBuscar(Pacientes paciente);

    List getListarPacientesVigenciaSolo(Pacientes paciente);

    List getListarPacientesHCCns(Pacientes paciente);

    Pacientes getDatosPaciente(int id_paciente);

    Pacientes getDatosPacienteJefe(int id_paciente);

    Pacientes getDatosPacienteCI(int id_paciente);

    List getDatosPacienteInt(Pacientes paciente);

    void setCrearPaciente(Pacientes paciente);

    void setCrearVigencia(Pacientes paciente);

    void setModificarPaciente(Pacientes paciente);

    void setModificarVigencia(Pacientes paciente);

    void setModificaPacienteEmb(Pacientes paciente);

    void setModificaPacienteSangre(Pacientes paciente);

    void setModificaPacienteFact(Pacientes paciente);

    void setModificaPacienteNit(Pacientes paciente);

    void setEliminarPaciente(Pacientes paciente);

    void setEliminarPacienteSeguro(Pacientes paciente);
    
    void setEliminarPacienteSscp(Pacientes paciente);

    int getNumHcl();
//  int getNum(int id_paciente);

    void setCrearPeedido(Pacientes paciente);
    
    void setCrearProforma(Pacientes paciente);
    
    void setCrearFactura(Pacientes paciente);

    void setCrearPedidoNutri(Pacientes paciente);

    void setCrearFacturaInter(Pacientes paciente);

    void setCrearFacturaInter2(Pacientes paciente);

    void setEliminarPedido(Pacientes paciente);
    
    void setEliminarPedidoProf(Pacientes paciente);

    int getNumReceta(Pacientes paciente);

    int getNumPedido(Pacientes paciente);

    int getNumPedidoProf(Pacientes paciente);
    
    Pacientes getDatosPedido(Pacientes paciente) ;
  
    Pacientes getDatosPedidoProf(Pacientes paciente) ;

    Pacientes getDatosPedidoHisto(Pacientes paciente);

    Pacientes getDatosPedidoHis(Pacientes paciente);

    Pacientes getDatosFactura(Pacientes paciente);

    Pacientes getDatosPedidoKardex(Pacientes paciente);

    Pacientes getDatosPedidoI(Pacientes paciente);

    void setModificarPedido(Pacientes paciente);
    
    void setModificarPedidoProf(Pacientes paciente);

    void setModificarPedidoPPago(Pacientes paciente);

    void setModificarPedidoElimPP(Pacientes paciente);

    void setModificarPedidoSinFecha(Pacientes paciente);

    void setModificarPedidoAnt(Pacientes paciente);

    List getListarCobroRubro(Pacientes paciente);

    List getListarCobroRubroEnfer(Pacientes paciente);

    List getListarCobroRubroOdon(Pacientes paciente);

    List getListarCobroEnfer(Pacientes paciente);

    List getListarCobroRubroFar(Pacientes paciente);
    
    List getListarProformas(Pacientes paciente) ;
    
    List getListarProformasNom(Pacientes paciente) ;

    List getReporteCobroFarm(Pacientes dato);

    List getReporteCobroFarmGen(Pacientes dato);

    List getListaMedEntregados(Pacientes dato);

    List getListaMedEntregadosI(Pacientes dato);

    List getListaMedKEntregados(Pacientes dato);

    List getListaMedKEntregadosEnfer(Pacientes dato);

    List getListaMedKEntregadosSPAM(Pacientes dato);

    List getListaMedKEntregadosFecha(Pacientes dato);

    List getListaMedKEntregadosUni(Pacientes dato);

    List getListaCobroTotal(Pacientes dato);

    List getListaMedRecibidos(Pacientes dato);

    List getListaMedRecibidosHcl(Pacientes dato);

    List getListaMedRecibidosTipo(Pacientes dato);

    List getListaMedRecibidos2(Pacientes dato);

    List getListaMedRecibidosAlmaGen(Pacientes dato);

    List getReporteEconomico(Pacientes dato);

    List getReporteEconomicoGenFac(Pacientes dato);

    List getReporteEconomicoFac(Pacientes dato);

    List getReporteEconomicoPPago(Pacientes dato);

    List getReporteEconomicoGenFacRub(Pacientes dato);

    List getReporteFacturasEmitidasHcl(Pacientes dato);

    List getDatosPedidoRubro(Pacientes dato);

    List getDatosPedidoRubrosDet(Pacientes dato);

    List getDatosPedidoRubrosDetDeu(Pacientes dato);

    List getDatosPedidoDetRubro(Pacientes dato);

    List getDatosPedidoRubroDental(Pacientes dato);

    List getDatosPedidoRubroGeneral(Pacientes dato);

    List getDatosPedidoRubroGeneralSf(Pacientes dato);

    List getDatosPedidoRubrosDetSf(Pacientes dato);

    List getDatosPedidoRubrosDetSfPag(Pacientes dato);

    List getReporteEconomicoRubro(Pacientes dato);

    List getReporteEconomicoGenRubro(Pacientes dato);

    List getReporteEconomicoGenRubroDental(Pacientes dato);

    List getListaMedKardexEntregados(Recetas dato);

    List getKardexRestringido(Recetas dato);

    List getKardexFarmaciaCNS(Recetas dato);

    List getKardexUnitario(Recetas dato);

    List getListaMedKardexXPedido(Recetas dato);

    List getListaKardexPaciente(Recetas dato);

    List getKardexUsuario(Recetas dato);

    List getKardexRestringido3(Recetas dato);

    List getKardexRestringido4(Recetas dato);

    List getKardexRestringido5(Recetas dato);

    List getKardexRestringido6(Recetas dato);

    List getKardexRestringido7(Recetas dato);

    List getKardexFarmaciaCNS_SC(Recetas dato);

    List getKardexFarmaciaCNSDet_SC(Recetas dato);

    List getKardexFarmaciaCNSDet(Recetas dato);

    List getKardexFarmaciaCNSDetFar(Recetas dato);

    List getListaKardexPacienteIndi(Recetas dato);

    List getKardexRemision(Recetas dato);

    List getKardexResumenDispensa(Recetas dato);

    List getKardexResumenDispensaDia(Recetas dato);

    List getKardexExport(Recetas dato);

    List getRevercionExcel(Recetas dato);

    List getListaRecetaGen(Recetas dato);

    List getResumenXmedico(Recetas dato);

    List getResumenXespecialidad(Recetas dato);

    List getVencimientos(Recetas dato);

    List getResumenXservicio(Recetas dato);

    List getKardexResumenXMedica(Recetas dato);

    List getKardexResumenXMedicaSaldo(Recetas dato);

    long getNumClaDoc(Pacientes dato);
    
    long getNumClaDocProf(Pacientes dato);
    //historiales

    void setCrearReservacion(Historiales dato);
    
    void setCrearReservacionFicha(Historiales dato);

    void setCrearInterconsulta(Historiales dato);

    void setCrearInternado(Historiales dato);

    void setCrearInternado2(Historiales dato);

    void setCrearInternadoEmerg(Historiales dato);

    void setCrearMorbilidad(Historiales dato);

    void setEliminarReserva(Historiales dato);

    void setEliminarHistoria(Historiales dato);

    void setEliminarMorbilidad(Historiales dato);

    void setEliminarMorbilidadFarma(Historiales dato);

    void setEliminaLab(Historiales dato);

    void setEliminaPedidoLab(Historiales dato);

    void setModificaLab(Historiales dato);

    void setModificaVigencia(Historiales dato);

    void setModificarPagoReserva(Historiales dato);

    void setModificarMorbilidad(Historiales dato);

    void setModificarInter(Historiales dato);

    void setModificarInterFecha(Historiales dato);

    void setModificarInterReceta(Historiales dato);

    void setModificarInterDat(Historiales dato);

    void setModificarInterDatHisto(Historiales dato);

    void setModificarInterAltaHisto(Historiales dato);

    void setModificarSignosReserva(Historiales dato);

    void setModificarReservaConsultorio(Historiales dato);

    void setModificarReservaVigencia(Historiales dato);

    void setModificarReservaTotal(Historiales dato);

    void setModificarEstadoHistorial(Historiales dato);

    void setModificarInternado(Historiales dato);

    void setModificarInternadoFecha(Historiales dato);

    void setModificarEmergencias(Historiales dato);

    List getListarReservacionesCount(Historiales dato);

    List getListarReservacionesCountMed(Historiales dato);

    List getListarReservacionesAsig(Historiales dato);
    
    List getListarReservacionesInternet(Historiales dato);

    List getListarReserFichas(Historiales dato);

    List getListarReservaciones(Historiales dato);

    List getListarReservaciones1(Historiales dato);

    List getListarReservacionesPersona(Historiales dato);

    List getListarReservacionesCIE10(Historiales dato);

    List getListarReservacionesCIE10Consul(Historiales dato);

    List getListarReservacionesCIE10ConsulE(Historiales dato);

    List getListarReservacionesMedico(Historiales dato);

    List getListarReservacionesConsul(Historiales dato);

    List getListarReservacionesEstadFecha(Historiales dato);

    List getListarReservacionesHemo(Historiales dato);

    List getListarReservacionesUcaLuo(Historiales dato);

    List getListarReservacionesUcaLuoF(Historiales dato);

    List getListarReservacionesConsul2(Historiales dato);

    List getListarReservacionesResid(Historiales dato);

    List getListarReservacionesResidConsul(Historiales dato);

    List getListarReservacionesResidConsulPerso(Historiales dato);

    List getListarReservacionesConsulPublico(Historiales dato);

    List getListarReservacionesCaja(Historiales dato);

    List getListarReservacionesNombreCaja(Historiales dato);

    List getListarReservacionesConsulMedicoPublico(Historiales dato);

    List getListarVigencia(Historiales dato);

    List getListarVigenciaMedico(Historiales dato);

    List getListarVigenciaConsul(Historiales dato);

    List getListarVigenciaFicha(Historiales dato);

    List getListarVigenciaHab(Historiales dato);

    List getListarVigenciaHabCambioConsul(Historiales dato);

    List getListarCobroReserva(Historiales dato);

    List getListarCobroReservaSignos(Historiales dato);

    List getListarCobroReservaUni(Historiales dato);

    List getListarReservaSignosCNS(Historiales dato);

    List getListarReservaSignosCNS_SC(Historiales dato);

    List getListarReservaSignosCNS_SCUrg(Historiales dato);

    List getListarAtendidos(Historiales dato);

    List getListarAtendidosCNS(Historiales dato);

    List getListarAtendidosTot(Historiales dato);

    List getListarAtendidosFarNom(Historiales dato);

    List getListarAtendidosPend(Historiales dato);

    List getListarAtendidosEnf(Historiales dato);

    List getListarAtendidosI(Historiales dato);

    List getListarAtendidosITot(Historiales dato);

    List getListarAtendidosIFarNom(Historiales dato);

    List getListarAtendidosICNS(Historiales dato);

    List getListarHistoria(Historiales dato);

    List getListarHistoriaHoy(Historiales dato);

    List getListarHistoriaInterHoy(Historiales dato);

    List getListarHistoriaMed(Historiales dato);

    List getListarHistoriaEmergenGen(Historiales dato);

    List getListarHistoriaEmergen(Historiales dato);

    List getListarAtendidoHoy(Historiales dato);

    List getListarHistoriaTodo(Historiales dato);

    List getListarHistoriaI(Historiales dato);

    List getUltHistoriaInter(Historiales dato);

    List getHistoriaInterIndi(Historiales dato);

    List getHistoriaInter(Historiales dato);

    List getHistoriaInterEnfer(Historiales dato);

    List getHistoriaInterMedico(Historiales dato);

    List getHistoriaInterMedicoReceta(Historiales dato);

    List getListaMorbi(Historiales dato);

    int setRegistrarHistorial(Historiales dato);

    Historiales getDatosHistorial(Historiales dato);

    Historiales getDatosHistorialUlt(Historiales dato);

    Historiales getDatosHistorialUltHisto(Historiales dato);

    Historiales getDatosReserva(Historiales dato);

    Historiales getDatosEmergencias(Historiales dato);

    Historiales getDatosEmergenciaUltimo(Historiales dato);

    Historiales getDatosEmergencia(Historiales dato);

    Historiales getDatosEmergenciaSolo(Historiales dato);

    void setModificarEstadoHistoria(Historiales dato);

    void setModificarRangoHistoria(Historiales dato);

    List getListarKardexPaciente(Historiales dato);

    List getListarPacientesHistoGeneral(Historiales dato);

    List getListarHistoriaGen(Historiales dato);

    List getListarHistoriaMicro(Historiales dato);

    List getListarResumenPrestacion(Historiales dato);

    List getListarResumenPrestacion2(Historiales dato);

    List getListarResumenPrestacionYa(Historiales dato);

    List getListarPacientesHistoMuje(Historiales dato);

    List getListarPacientesHistoNeo(Historiales dato);

    List getListarPacientesHistoPedi(Historiales dato);

    List getListarPacientesHistoMayor(Historiales dato);

    List getListarPacientes19(Historiales dato);

    List getListarPacientes19T(Historiales dato);

    List getListarPacientesSpam(Historiales dato);

    List getResumenEconomico(Historiales dato);

    List getResumenEconomicoLab(Historiales dato);

    List getResumenEconomicoEco(Historiales dato);

    List getResumenEconomicoTot(Historiales dato);

    List getHistorialAtendidos(Historiales dato);

    List getHistorialAtendidosEco(Historiales dato);

    List getHistorialAtendidosH(Historiales dato);

    List getHistorialAtendidosHemo(Historiales dato);

    List getHistorialAtendidosResid(Historiales dato);

    List getHistorialAtendidosResidConsul(Historiales dato);

    List getHistorialAtendidosResidNombre(Historiales dato);

    List getHistorialAtendidosResidConsulPer(Historiales dato);

    List getInternadosCajaObservacionHemo(Historiales dato);

    List getInternadosCajaObservacion(Historiales dato);

    List getInternadosCajaObservacionBuscar(Historiales dato);

    List getInternadosCajaObservacionPiso(Historiales dato);

    List getInternadosCajaObservacionPisoPer(Historiales dato);

    List getHistorialAtendidosFecha(Historiales dato);

    List getAtendidosInter(Historiales dato);

    List getAtendidosInterSala(Historiales dato);

    List getHistorialLibros(Historiales dato);

    List getHistorialAtendidosP(Historiales dato);

    List getAtendidosGeneral(Historiales dato);

    List getAtendidosMicronutriente(Historiales dato);

    List getSumi(Historiales dato);

    List getInternados(Historiales dato);

    List getVigenciaFecha2(Historiales dato);

    List getInternadosSala(Historiales dato);

    List getVigenciaFecha(Historiales dato);

    List getRecetasInternados(Historiales dato);

    List getRecetasInternadosNombre(Historiales dato);

    List getInternadosSalaCajaNombre(Historiales dato);

    List getInternadosSalaCajaAltaNombre(Historiales dato);

    List getRecetasInternadosUnico(Historiales dato);

    List getVigencia_now(Historiales dato);

    List getVigencia_now_triaje(Historiales dato);

    List getInternadosPisoCaja(Historiales dato);

    List getInternadosCaja(Historiales dato);

    List getInternadosPisoCajaVacio(Historiales dato);

    List getIndicadores();

    List getListaHistoriaImp(int rango);
    //Medicamentos

    List getListarMedicamentosGestion(Medicamentos dato);

    List getListarMedicamentosCot(Medicamentos dato);

    List getListarMedicamentosCotFar(Medicamentos dato);

    List getListarMedicamentosCotb1(Medicamentos dato);

    List getListarMedicamentos(Medicamentos dato);

    List getListarMedicamentosTotal(Medicamentos dato);

    List getListarMedicamentosAsignados(Medicamentos dato);

    List getListarMedicamentosPorAsig(Medicamentos dato);

    List getListarMedicamentosAlma(Medicamentos dato);

    List getListarInvAlma(Medicamentos dato);

    List getListarMedicamentosAlmaGral(Medicamentos dato);

    List getListarMedicamentosVacio(Medicamentos dato);

    List getListarMedicamentoSolo(Medicamentos dato);

    List getListarInventa(Medicamentos dato);

    List getListarInventaGrupo(Medicamentos dato);

    List getListarInventaGrupoSub(Medicamentos dato);

    List getListarInventaSubGrupo(Medicamentos dato);

    List getListarInventaPartida(Medicamentos dato);

    List getListarMedicamentosb1(Medicamentos dato);
    
    List getListarTipos(Medicamentos dato);

    List getListarGrupos(Medicamentos dato);

    List getListarSubGrupos(Medicamentos dato);

    List getListarSubGrupos2(Medicamentos dato);

    List getListarPartidas(Medicamentos dato);

    List getListarMedicamentosRe(Medicamentos dato);

    List getActualizarMedicamentos(Medicamentos dato);

    List getActualizarMedicamentos_med(Medicamentos dato);

    List getListarMedicamentosMicro(Medicamentos dato);

    List getListarCarmelosExel(Medicamentos dato);

    Medicamentos getDatosMedicamento(Medicamentos dato);

    Medicamentos getDatosMedicamentoB(Medicamentos dato);

    Medicamentos getDatoGrupo(Medicamentos dato);

    Medicamentos getDatoSubGrupo(Medicamentos dato);

    Medicamentos getDatoPartida(Medicamentos dato);

    Medicamentos getDatoSubPartida(Medicamentos dato);

    Medicamentos getDatosItem(Medicamentos dato);

    Medicamentos getDatosItemAlmacen(Medicamentos dato);

    Medicamentos getDatosSubItem(Medicamentos dato);

    Medicamentos getDatosMedicamentoAsig(Medicamentos dato);

    Medicamentos getDatosMedicamentoPasado(Medicamentos dato);

    Medicamentos getDatosMedicamentoUnico(Medicamentos dato);

    Medicamentos getDatosMedicamentoExiste(Medicamentos dato);

    Medicamentos getDatoPrograma(Medicamentos dato);

    void setEliminarMedicamento(Medicamentos medicamento);

    void setEliminarMedicamentoLocal(Medicamentos medicamento);

    void setEliminarMedicamentoAsignado(Medicamentos medicamento);

    void setEliminarGrupo(Medicamentos medicamento);

    void setEliminarPartida(Medicamentos medicamento);

    void setEliminarSubGrupo(Medicamentos medicamento);

    void setCrearMedicamento(Medicamentos medicamento);

    void setCrearMedicamentoLocal(Medicamentos medicamento);

    void setCrearMedicamentoLocal2(Medicamentos medicamento);

    void setCrearMedicamentoAsignacion(Medicamentos medicamento);

    void setCrearGrupo(Medicamentos medicamento);

    void setCrearPartida(Medicamentos medicamento);

    void setCrearSubGrupo(Medicamentos medicamento);

    void setModificarMedicamento(Medicamentos medicamento);

    void setModificarMedicamentoStock(Medicamentos medicamento);

    void setModificarMedicamentoTotal(Medicamentos medicamento);

    void setModificarGrupo(Medicamentos medicamento);

    void setModificarPartida(Medicamentos medicamento);

    void setModificarSubGrupo(Medicamentos medicamento);

    void setModificarCie10(Medicamentos medicamento);

    List getListarEnfermedades(String nombres);

    List getListarEnfermedadesOtra(String nombres);

    List getListarEnfermedadesCod(String nombres);

    List getListarEnfermedadesCot(Medicamentos medicamento);

    List getListarProgramas(Medicamentos medicamento);

    List getListarImm(Medicamentos medicamento);

    List getListarImmFarma(Medicamentos medicamento);

    List getListarImmCNS(Medicamentos medicamento);

    List getListarImmCNS2(Medicamentos medicamento);

    List getListarImmCNSsaldo(Medicamentos medicamento);

    List getListarImmCNSsaldo2(Medicamentos medicamento);

    List getListarFaltanImm(Medicamentos medicamento);

    List getListarDatosImm(Medicamentos medicamento);

    List getListarDatosImmCNS2(Medicamentos medicamento);

    List getListarActImm(Medicamentos medicamento);

    List getListarImmFechaDada(Medicamentos medicamento);

    List getListarImmFechaDadaProg(Medicamentos medicamento);

    List getListarImmFechaDadaUsua(Medicamentos medicamento);

    List getListarImmFechaDadaCNS(Medicamentos medicamento);

    List getListarImmFechaDet(Medicamentos medicamento);

    List getListarActCpt(Medicamentos medicamento);

    List getListarActCptII(Medicamentos medicamento);

    List getListarCptPsico(Medicamentos medicamento);
    //Recetas

    List getListarMedPaquete(Recetas dato);

    void setCrearRecetaPaque(Recetas dato);

    void setEliminarRecetaPaque(Recetas dato);

    void setModificarRecetaPaquete(Recetas dato);

    int setRegistrarKardex(Recetas dato);

    List getListarRecetasPres(Recetas dato);

    List getListarRecetas(Recetas dato);

    List getListarRecetasPrivado(Recetas dato);

    List getListarRecetasHistorial(Recetas dato);

    List getListarRecetasCNS(Recetas dato);

    List getListarRecetasUlt(Recetas dato);

    List getListarUltRecetaI(Recetas dato);

    List getListarUltReceta(Recetas dato);

    List getListarRecetaAnterior(Recetas dato);

    List getListarRecetaAnteriorCarmelo(Recetas dato);

    List getListarRecetasYa(Recetas dato);

    List getListarRecetasInt(Recetas dato);

    List getListarRecetasTotal(Recetas dato);

    List getListarRecetasTotalMed(Recetas dato);

    List getListarRecetasCaros(Recetas dato);

    List getListarRecetasMedico(Recetas dato);

    List getListarRecetasMedicoCNS(Recetas dato);

    List getListarRepRecetaCNS(Recetas dato);

    List getListarKardexPac(Recetas dato);

    List getListarRecetasInter(Recetas dato);

    List getListarRecetasIndi(Recetas dato);

    List getListarRecetasMedicoI(Recetas dato);

    List getListarRecetasMedicoImp(Recetas dato);

    void setCrearReceta(Recetas dato);

    void setEliminarReceta(Recetas dato);

    void setModificarEstadoReceta(Recetas dato);

    void setModificarRecetaNumera(Recetas dato);

    void setModificarRecetaDosifi(Recetas dato);

    void setModificarEstadoInter(Recetas dato);

    void setCrearRecetaMedSumi(Recetas dato);

    List getListarRecetasS(Recetas dato);

    List getListarKardex(Recetas dato);
    
    List getListarKardexProf(Recetas dato) ;

    List getListarKardexInterFact(Recetas dato);

    List getListarKardexFactura(Recetas dato);

    List getListarKardexPerfilFar(Recetas dato);

    List getListarKardexPerfilFarGen(Recetas dato);

    List getListarFechaPerfil(Recetas dato);

    List getListarFechaPerfil2(Recetas dato);

    List getListaKardexPacienteDesg(Recetas dato);

    List getListarFechaPerfildat(Recetas dato);

    List getListarPerfilReversion(Recetas dato);

    List getListarKardexPerfilFarDet(Recetas dato);

    List getListarKardexComprueba(Recetas dato);

    List getListarKardexCodsumi(Recetas dato);

    List getListarKardexAjus(Recetas dato);

    List getListarKardexPago(int id_historial);

    void setModificarKardex(Recetas dato);

    void setModificarKardexV(Recetas dato);

    void setModificarKardexR(Recetas dato);

    void setModificarKardexVenta(Recetas dato);

    void setModificarKardexPaciente(Recetas dato);

    List getListarKardexI(Recetas dato);

    List getListarKardexIImpRec(Recetas dato);

    List getListarKardexInter(Recetas dato);

    List getListarKardexTotal(Recetas dato);
	
    List getListarKardexProve(Recetas dato);

    void setCrearKardex(Recetas dato);
    
    void setCrearKardexProf(Recetas dato) ;
    
    void setCrearKardexProf2(Recetas dato) ;

    void setCrearKardexPaciente(Recetas dato);

    void setCrearKardexPacInsert(Recetas dato);

    void setEliminarKardex(Recetas dato);
    
    void setEliminarKardexProf(Recetas dato);

    void setEliminarKardexReceta(Recetas dato);

    List getListarKardexMedicamento(Recetas dato);

    List getListarKardexMedicamentoSin0(Recetas dato);

    List getListarKardexMedicamentoSolo0(Recetas dato);

    List getListarKardexMedicamentoSoloE(Recetas dato);

    List getListarKardexMedicamentoSoloA(Recetas dato);

    List getListarKardexMedicamentoSoloS(Recetas dato);

    List getListarKardexControl(Recetas dato);
    
    List getListarKardexProg(Recetas dato);

    List getListarKardexIndi(Recetas dato);

    List getListarKardexMicro(Recetas dato);

    //Detalle Cobros
    List getListarDetalleGen(Detalle detalle);

    List getListarDetTotal(Detalle detalle);

    List getListarDetIndividual(Detalle detalle);

    List getListarDetHistorial(Detalle detalle);

    List getListarDetalle(Detalle detalle);

    List getListarDetallePago(Detalle detalle);

    List getListarCobroDetalle(Detalle detalle);

    List getListarCobroDetalleInterFact(Detalle detalle);

    List getListarCobroDetalleInt(Detalle detalle);

    List getListarDetalleSaldo(Detalle detalle);

    void setCrearDetallePago(Detalle detalle);

    void setCrearDetalle(Detalle detalle);

    void setEliminarDetalle(Detalle detalle);

    void setEliminarDetalleLab(Detalle detalle);
	
	void setModificarDetalle (Detalle detalle);

    //Prestaciones
    List getListarPresOdon(Prestaciones dato);

    List getListarPresOdonGen(Prestaciones dato);

    List getListarNivelPrestacion(int id_prestacion);

    void setCrearNivelPaque(Prestaciones dato);

    void setEliminarNivelPaque(Prestaciones dato);

    Prestaciones getDatosPrestacion(int id_prestacion);

    void setCrearPrestacion(Prestaciones dato);

    void setModificarPrestacion(Prestaciones dato);

    void setHabilitaPrestacion(Prestaciones dato);

    void setEliminarPrestacion(Prestaciones dato);

    List getListarPrestacionesSumi(Prestaciones dato);

    List getListarPrestacionesSumiH(Prestaciones dato);

    List getListarMedicamentosSumi(Prestaciones dato);

    List getListarPrestaciones(Prestaciones dato);

    List getListarPrestaciones22(Prestaciones dato);

    List getListarPrestacionesDes(Prestaciones dato);

    List getListarPrestacionesDes22(Prestaciones dato);

    List getListarPrestacionesCot(Prestaciones dato);

    List getListarPrestacionesCot22(Prestaciones dato);

    List getListarSumiRecetasPres(Prestaciones dato);

    List getListarPrestacionesDuplis(Prestaciones dato);

    List getListarPrestacionSinMed(Prestaciones dato);

    List getListarSumiRecetasPres2(Prestaciones dato);

    List getListarSumiRecetas(Prestaciones dato);

    List getPrestacionExisteYa(Prestaciones dato);

    List getPrestacionExisteYaDia(Prestaciones dato);

    List getListarSumiRecetasI(Prestaciones dato);

    List getListarSumiPresta(Prestaciones dato);

    List getListarSumiRecetasIntImp(Prestaciones dato);
    
    List getPrestacionGen(Prestaciones dato);

    void setCrearRecetaSumi(Prestaciones dato);

    void setEliminarRecetaSumi(Prestaciones dato);

    void setEliminarPresDupli(Prestaciones dato);

    void setEliminarRecetaSumiI(Prestaciones dato);

    void setEliminarRecetaMedSumi(Prestaciones dato);

    void setEliminarRecetaMedSumiKardex(Prestaciones dato);

    List getListarRepes();

    List getListarFaltanRepes();

    List getListarFopos(int mes, int anio);

    List getListarActRepes(Prestaciones dato);

    List getListarMorbilidad(Prestaciones dato);

    List getListarPrestacionesDadasMuje(Prestaciones dato);

    List getListarPrestacionesDadasNeo(Prestaciones dato);

    List getListarPrestacionesDadasPedi(Prestaciones dato);

    List getListarResumenPrest(Prestaciones dato);

    List getListarPrestacionesDadasMayor(Prestaciones dato);

    List getListarPrestacionesGen(Prestaciones dato);

    List getListarPacientesPrestacionesGen(Prestaciones dato);

    List getListarPacientesPrestacionesNeo(Prestaciones dato);

    List getListarPacientesPrestacionesPedi(Prestaciones dato);

    List getListarPacientesPrestacionesMayor(Prestaciones dato);

    List getListarPacientesPrestacionesMuje(Prestaciones dato);

    // Cuadernos  .
    void setRegistrarOdonMensual(Cuadernos dato);

    void setRegistrarOdonPersonal(Cuadernos dato);

    void setRegistrarSnis301(Cuadernos dato);

    List getListarSnis301();

    List getListarFaltaSnis301();

    List getConfigurarImpresion(Cuadernos dato);

    List getImpresionSerologia(Cuadernos dato);

    List getImpresionHemograma(Cuadernos dato);

    List getImpresionOrina(Cuadernos dato);

    List getImpresionOtros(Cuadernos dato);

    List getImpresionEmbarazo(Cuadernos dato);

    List getImpresionQuimicas(Cuadernos dato);

    List getImpresionEcografia(Cuadernos dato);

    List getConfigurarImpresionGral(Cuadernos dato);

    List getConfigurarImpresionGralHcl(Cuadernos dato);

    List getConfigurarImpresionGralPerinatal(Cuadernos dato);

    List getConfigurarImpresionCarnetInf(Cuadernos dato);

    List getConfigurarImpresionGinecologica(Cuadernos dato);

    List getConfigurarImpresionFormulario(Cuadernos dato);

    List getConfigurarImpresionCarnet(Cuadernos dato);

    List getConfigurarImpresionRiesgo(Cuadernos dato);

    List getCuadernosImpresion(Cuadernos dato);

    List getCodigoControl(Cuadernos dato);

    List getListarOdonMensual();

    List getListarFaltaOdonMensual();

    Cuadernos getDatosTallaPesoF02(Cuadernos dato);

    Cuadernos getDatosTallaPesoF25(Cuadernos dato);

    Cuadernos getDatosTallaPesoM02(Cuadernos dato);

    Cuadernos getDatosTallaPesoM25(Cuadernos dato);

    Cuadernos getDatosTallaEdadF02(Cuadernos dato);

    Cuadernos getDatosTallaEdadF25(Cuadernos dato);

    Cuadernos getDatosTallaEdadM02(Cuadernos dato);

    Cuadernos getDatosTallaEdadM25(Cuadernos dato);

    Cuadernos getDatosPesoEdadF02(Cuadernos dato);

    Cuadernos getDatosPesoEdadM02(Cuadernos dato);

    Cuadernos getHistorialPacientesEco(int id_paciente);

    List getListarPacientesEco();

    List getPacienteResultadoLab(Cuadernos dato);

    List getPacienteResultadoLabEco(Cuadernos dato);

    List getPacienteResultadoLabX(Cuadernos dato);

    List getPacienteResultadoLabLab(Cuadernos dato);

    List getPacienteResultadoLabEndo(Cuadernos dato);

    List getDetalleFotosEndo(Cuadernos dato);

    List getPacienteLaboratorio(Cuadernos dato);

    List getDatosLaboratorios(Cuadernos dato);

    Cuadernos getDatosLaboratorioC(Cuadernos dato);

    List getLabPacPendiente(Cuadernos dato);

    List getLabPacPendientePago(Cuadernos dato);

    List getLabPacPasados(Cuadernos dato);

    List getLabPacPendienteRx(Cuadernos dato);

    List getLabPacPendienteEco(Cuadernos dato);

    List getLabSSPAM(Cuadernos dato);

    List getLabPendienteAmb(Cuadernos dato);

    List getLabPendienteAmbFecha(Cuadernos dato);

    List getLabPendienteInter(Cuadernos dato);

    List getLabPendienteInterFecha(Cuadernos dato);

    List getLabPendienteInterNum(Cuadernos dato);

    List getLabPendienteInterHcl(Cuadernos dato);

    List getLabPacPendienteInter(Cuadernos dato);

    List getLabPacPendienteRxInter(Cuadernos dato);

    List getLabPacPendienteEcoInter(Cuadernos dato);

    List getLabPendiente(Cuadernos dato);

    List getLabPendienteMed(Cuadernos dato);

    List getLabPendienteRx(Cuadernos dato);

    List getLabPendienteImp(Cuadernos dato);

    List getLabPendienteEco(Cuadernos dato);

    List getLabPendienteEpi(Cuadernos dato);

    List getLabPendienteCNS(Cuadernos dato);

    List getLabPendienteCNSUlt(Cuadernos dato);

    List getHemograma(Cuadernos dato);

    List getOrina(Cuadernos dato);

    List getDetalleEcos(Cuadernos dato);

    List getDetalleEcosI(Cuadernos dato);

    List getLabRealizadoAmb(Cuadernos dato);

    List getLabRealizadoAmbFecha(Cuadernos dato);

    List getLabRealizadoAmbNombre(Cuadernos dato);

    List getLabRealizadoInter(Cuadernos dato);

    List getLabRealizadoInterFecha(Cuadernos dato);

    List getLabRealizadoInterNombre(Cuadernos dato);

    List getLabRealizadoInterHcl(Cuadernos dato);

    List getLabRealizadoInterNum(Cuadernos dato);

    List getLabRealizadoTotal(Cuadernos dato);

    List getLabRealizadoSumi(Cuadernos dato);

    List getPedidoLab(Cuadernos dato);

    List getDatoPedidoLab(Cuadernos dato);

    List getLabMedico(Cuadernos dato);

    List getLabMedicoEco(Cuadernos dato);

    List getLabMedicoX(Cuadernos dato);

    List getLabMedicoLab(Cuadernos dato);

    Cuadernos getDatos302_5(Cuadernos dato);

    Cuadernos getDatos302imc(Cuadernos dato);

    void setEliminarLaboratorioC(Cuadernos dato);

    void setCrearLaboratorioC(Cuadernos dato);

    void setCrearProduccion(Cuadernos dato);

    void setCrearProduccionEmerg(Cuadernos dato);

    void setCrearPedidoLab(Cuadernos dato);

    void setModificarPedidoLab(Cuadernos dato);

    void setModificarEcoDetalle(Cuadernos dato);

    void setModificarPedidoLabo(Cuadernos dato);

    void setCrearDetalleSangre(Cuadernos dato);

    void setCrearDetalleOrina(Cuadernos dato);

    void setModificarLaboratorioC(Cuadernos dato);

    void setModificarLaboratorioCobrar(Cuadernos dato);

    void setModificarHemograma(Cuadernos dato);

    void setModificarOrina(Cuadernos dato);

    void setReservaLabEcografia(Cuadernos dato);

    void setModificarC2(Cuadernos dato);

    List getListarEstadisticas(Cuadernos dato);

    List getListarEstadisticasHospi(Cuadernos dato);

    void setModificarImpOrina(Cuadernos dato);

    void setModificarImpRiesgo(Cuadernos dato);

    List getCuadernoC1(Cuadernos dato);

    List getVerTransferencia(Cuadernos dato);

    List getListarTransferencia(Cuadernos dato);

    List getDatoTransferencia(Cuadernos dato);

    List getVerAdmisiones(Cuadernos dato);

    List getVerEpicrisis(Cuadernos dato);

    List getVerProtocolos(Cuadernos dato);

    List getListarAdmision(Cuadernos dato);

    List getListarEpicrisis(Cuadernos dato);

    List getListarProtocolos(Cuadernos dato);

    List getDatoAdmision(Cuadernos dato);

    List getDatoEpicrisis(Cuadernos dato);

    List getDatoProtocolo(Cuadernos dato);

    List getListarAdmisionTot(Cuadernos dato);

    List getListarFechas(Cuadernos dato);

    List getListarFechasCount(Cuadernos dato);

    List getVerSolSangre(Cuadernos dato);

    List getListarSolSangres(Cuadernos dato);

    List getDatoSolSangre(Cuadernos dato);

    List getVerOftalmologia(Cuadernos dato);

    List getCuadernoC2(Cuadernos dato);

    List getCuadernoC3(Cuadernos dato);

    List getCuadernoC4(Cuadernos dato);

    List getCuadernoC5(Cuadernos dato);

    List getCuadernoC6(Cuadernos dato);

    List getCuadernoC7(Cuadernos dato);

    List getCuadernoV(Cuadernos dato);

    List getLabGen(Cuadernos dato);

    List getReporteCIE10(Cuadernos dato);

    List getReporteCIE10edadPersonal(Cuadernos dato);

    List getReporteCIE10xMedico(Cuadernos dato);

    List getReporteCIE10Urgencia(Cuadernos dato);

    List getReporteCIE10edad(Cuadernos dato);

    List getReporteCIE10IngInter(Cuadernos dato);

    List getReporteCIE10EgrInter(Cuadernos dato);

    List getReporteProduccionG1(Cuadernos dato);

    List getReporteProduccion(Cuadernos dato);

    List getReporteProduccion2(Cuadernos dato);

    List getReporteCIE10_C1(Cuadernos dato);

    List getReporteCIE10_C2(Cuadernos dato);

    List getReporteCIE10_C3(Cuadernos dato);

    List getReporteCIE10_C4(Cuadernos dato);

    List getReporteCIE10_C7(Cuadernos dato);

    List getReporteCIE10_CEmerg(Cuadernos dato);

    List getPacienteCuaderno7(int id_historial);

    void setEliminarCuaderno7(Cuadernos dato);

    void setCrearCuaderno7(Cuadernos dato);

    Cuadernos getSnis301Odontologia(Cuadernos dato);

    Cuadernos getSnis301Nutricion(Cuadernos dato);

    List getPacienteCemergencia(int id_historial);

    List getPacienteCuaderno1(int id_historial);

    void setCrearCuaderno1(Cuadernos dato);

    void setCrearTransferencia(Cuadernos dato);

    void setCrearAdmision(Cuadernos dato);

    void setCrearEpicrisis(Cuadernos dato);

    void setCrearProtocolo(Cuadernos dato);

    void setCrearSolSangre(Cuadernos dato);

    void setCrearOftalmologia(Cuadernos dato);

    void setModificarCuaderno1(Cuadernos dato);

    void setModificarCodigo(Cuadernos dato);

    void setModificarTranferencia(Cuadernos dato);

    void setModificarAdmision(Cuadernos dato);

    void setModificarEpicrisis(Cuadernos dato);

    void setModificarPrococolos(Cuadernos dato);

    void setModificarSangre(Cuadernos dato);

    void setEliminarCuaderno1(Cuadernos dato);

    Cuadernos getSnis301Externa(Cuadernos dato);

    List getPacienteCuadernof(Cuadernos dato);

    void setCrearCuadernof(Cuadernos dato);

    void setEliminarCuadernof(Cuadernos dato);

    List getPacienteCuaderno3(int id_historial);

    void setCrearCuaderno3(Cuadernos dato);

    void setEliminarCuaderno3(Cuadernos dato);

    Cuadernos getSnis301Prevencion(Cuadernos dato);

    void setCrearCuaderno2(Cuadernos dato);

    void setEliminarCuaderno2(Cuadernos dato);

    Cuadernos getSnis301ControlPre(Cuadernos dato);

    List getPacienteCuaderno2(Cuadernos dato);

    Cuadernos getVerCuaderno2Ult(Cuadernos dato);

    Cuadernos getVerCuaderno3Count(Cuadernos dato);

    Cuadernos getVerCuaderno4PaciUlt(Cuadernos dato);

    Cuadernos getVerCuaderno5Ult(Cuadernos dato);

    void setEliminarCuaderno4(Cuadernos dato);

    void setCrearCuaderno4(Cuadernos dato);

    void setModificarCuaderno4(Cuadernos dato);

    void setModificarCuaderno4A(Cuadernos dato);

    void setModificarCuaderno4B(Cuadernos dato);

    void setModificarCuaderno4C(Cuadernos dato);

    void setModificarCuaderno4D(Cuadernos dato);

    void setModificarCuaderno4E(Cuadernos dato);

    void setModificarCuaderno4F(Cuadernos dato);

    void setModificarCuaderno4G(Cuadernos dato);

    void setModificarCuaderno24(Cuadernos dato);

    List getPacienteCuaderno4(Cuadernos dato);

    Cuadernos getPacienteCuaderno4A(Cuadernos dato);

    List getVerDesarrollo(Cuadernos dato);

    List getVerDesarrolloSimple(Cuadernos dato);

    List getVerDesarrollo2(Cuadernos dato);

    void setEliminarVacunas(Cuadernos dato);

    void setCrearVacunas(Cuadernos dato);

    List getPacienteVacunas(Cuadernos dato);

    Cuadernos getSnis301Vacunas(Cuadernos dato);

    List getOdonMensual(Cuadernos dato);

    void setEliminarCuaderno5(Cuadernos dato);

    void setCrearCuaderno5(Cuadernos dato);

    List getPacienteCuaderno5(Cuadernos dato);

    void setModificarCuaderno5(Cuadernos dato);

    void setModificarAdmision2(Cuadernos dato);

    void setEliminarCuaderno6(Cuadernos dato);

    void setCrearCuaderno6(Cuadernos dato);

    List getPacienteCuaderno6(Cuadernos dato);

    List getPacienteCuaderno6H(Cuadernos dato);

    List getListaPacientesCuaderno6(Cuadernos dato);

    List getListaPacientesCuaderno6Total(Cuadernos dato);

    Cuadernos getSnis301Enfermeria(Cuadernos dato);

    List getVerCuaderno7(Cuadernos dato);

    List getVerCuaderno7Paci(Cuadernos dato);

    List getVerCuaderno7Todos(Cuadernos dato);

    List getReporteDental(Cuadernos dato);

    List getReporteDentalT(Cuadernos dato);

    List getVerCuaderno7Uni(Cuadernos dato);

    List getVerVacunas(Cuadernos dato);

    List getVerVacunasTodos(Cuadernos dato);

    List getVerVacunasUni(Cuadernos dato);

    List getVerCemergencias(Cuadernos dato);

    List getVerCemergenciasTodos(Cuadernos dato);

    List getVerCuaderno1(Cuadernos dato);

    List getVerCuaderno1Sspam(Cuadernos dato);

    List getVerCuaderno1CNS(Cuadernos dato);

    List getVerCuaderno1CNSInter(Cuadernos dato);

    List getVerCuaderno1SspamTodos(Cuadernos dato);

    List getVerCuaderno1Uni(Cuadernos dato);

    List getVerCuaderno1T(Cuadernos dato);

    List getVerCuaderno1Todos(Cuadernos dato);

    List getVerCuaderno1Cie(Cuadernos dato);

    List getVerCuaderno1CieUni(Cuadernos dato);

    List getVerCuaderno1CieMorbi(Cuadernos dato);

    List getReporteSNISConsulta(Cuadernos dato);

    List getReporteSNISConsulta2(Cuadernos dato);

    List getVerCuadernoFisio(Cuadernos dato);

    List getVerCuadernofTodos(Cuadernos dato);

    List getVerCuadernoFisioTodos(Cuadernos dato);

    List getVerCuadernof(Cuadernos dato);

    List getVerCuaderno6(Cuadernos dato);

    List getVerCuaderno6Todos(Cuadernos dato);

    List getVerCuaderno6C1(Cuadernos dato);

    List getVerCuaderno6C4(Cuadernos dato);

    List getVerCuaderno6Uni(Cuadernos dato);

    List getVerEcografias(Cuadernos dato);

    List getVerEcografiasAdmi(Cuadernos dato);

    List getVerCuaderno2(Cuadernos dato);

    List getVerCuaderno2Todos(Cuadernos dato);

    List getVerCuaderno2C3(Cuadernos dato);

    List getVerCuaderno2C3Uni(Cuadernos dato);

    List getReporteProduccionPrenatal(Cuadernos dato);

    List getVerCuaderno2Uni(Cuadernos dato);

    List getVerCuaderno2Parto(Cuadernos dato);

    List getVerCuaderno3(Cuadernos dato);

    List getVerCuaderno3Todos(Cuadernos dato);

    List getVerCuaderno3Prod1(Cuadernos dato);

    List getVerCuaderno3Prod2(Cuadernos dato);

    List getVerCuaderno3Uni(Cuadernos dato);

    List getDatosUni(Cuadernos dato);

    List getVerCuaderno4_C2(Cuadernos dato);

    List getVerCuaderno4_C2Consul(Cuadernos dato);

    List getVerCuaderno4_C2Creci(Cuadernos dato);

    List getVerCuaderno4_C22014D(Cuadernos dato);

    List getVerCuaderno4_C22014F(Cuadernos dato);

    List getVerCuaderno4_C22014DIndi(Cuadernos dato);

    List getVerCuaderno4_C22014FIndi(Cuadernos dato);

    List getVerCuaderno4(Cuadernos dato);

    List getVerCuaderno4Paci(Cuadernos dato);

    List getVerCuaderno4Todos(Cuadernos dato);

    List getVerCuaderno4Uni(Cuadernos dato);

    List getDesarrolloSimple(Cuadernos dato);

    List getVerCuaderno4Creci(Cuadernos dato);

    List getVerCuaderno4CreciDA(Cuadernos dato);

    List getVerCuaderno4CreciNuevoDA(Cuadernos dato);

    List getVerCuaderno4Enfer(Cuadernos dato);

    List getVerCuaderno5(Cuadernos dato);

    List getVerCuaderno5Todos(Cuadernos dato);

    List getVerCuadernoInterServicio(Cuadernos dato);

    List getVerCuaderno5Piso(Cuadernos dato);

    List getVerCuaderno5Uni(Cuadernos dato);

    List getVerSnis302(Cuadernos dato);
    //control de calidad

    List getO80_C2(Cuadernos dato);

    List getPC42_C2(Cuadernos dato);

    List getPC64_C2(Cuadernos dato);

    List getZ34_C2(Cuadernos dato);

    List getZ39_C2(Cuadernos dato);

    List getZ301_C3(Cuadernos dato);

    List getZ305_C3(Cuadernos dato);

    List getZ124_C3(Cuadernos dato);

    List getC2_O80(Cuadernos dato);

    List getC2_PC42(Cuadernos dato);

    List getC2_PC64(Cuadernos dato);

    List getC2_Z34(Cuadernos dato);

    List getC2_Z39(Cuadernos dato);

    List getC3_Z301(Cuadernos dato);

    List getC3_Z305(Cuadernos dato);

    List getC3_Z124(Cuadernos dato);

    List getZ301_DIU(Cuadernos dato);

    List getPC23_C3(Cuadernos dato);

    List getC3_PC23(Cuadernos dato);

    List getPC23_DEPO(Cuadernos dato);

    List getCensoDiario(Cuadernos dato);

    List getGrafica1(Cuadernos dato);

    List getGrafica2(Cuadernos dato);

    List getGrafica3(Cuadernos dato);

    List getGrafica4(Cuadernos dato);

    // cuentas
    List getListarLibroMayor(Cuentas dato);

    List getListarLibroDiario(int id_librodiario);

    List getListarCuentasCot();

    List getListarCuentasNom(String cuenta);

    List getListarCuentas();

    Cuentas getDatosCuenta(int id_cuenta);

    void setCrearCuenta(Cuentas cuenta);

    void setModificarCuenta(Cuentas cuenta);

    void setEliminarCuenta(Cuentas cuenta);
    // transacciones

    int getNumLibroDiario();

    List getListarTransacciones();

    Cuentas getDatosTransaccion(int id_transaccion);

    void setCrearTransaccion(Cuentas transaccion);

    void setModificarTransaccion(Cuentas transaccion);

    void setEliminarTransaccion(Cuentas transaccion);

    void setCrearLibroDiario(Cuentas transaccion);

    void setEliminarLibroDiario(Cuentas transaccion);

    // Carpetas
    List getListarCarpetas(Carpetas carpeta);

    List getListarDependientes(Carpetas carpeta);

    List getListarCarpetasCaja(Carpetas carpeta);

    List getListarCarpetasFam(Carpetas carpeta);

    List getListarCarpetasAse(Carpetas carpeta);

    List getListarCarpetasId(Carpetas carpeta);

    Carpetas getDatosCarpeta(int id_carpeta);

    Carpetas getDatosCarpetaPac(int id_paciente);

    void setCrearCarpeta(Carpetas carpeta);

    void setModificarCarpeta(Carpetas carpeta);

    void setEliminarCarpeta(Carpetas carpeta);

    List getListarPacientesD(int id_carpeta);

    List getListarPacientesDJefe(int id_carpeta);

    void setCrearPacienteD(Carpetas paciente);

    void setEliminarPacienteD(Carpetas paciente);

    List getSalmi(Cuadernos dato);

    List getPersonasSalmi(Cuadernos dato);

    List getPacientesSalmi(Cuadernos dato);

    List getSalidasSalmiSumi(Cuadernos dato);

    List getLotesSalmi(Cuadernos dato);

    List getRelSalPreSalmi(Cuadernos dato);

    List getMovimientoSalmi(Cuadernos dato);

    List getDiagnosticoSalmi(Cuadernos dato);

    List getCie10Salmi(Cuadernos dato);

    List getMovSalmi(Cuadernos dato);

    List getLotesSalmiUni(Cuadernos dato);

    List getFopo(Cuadernos dato);

    List getIMMSalmi(Cuadernos dato);

    List getPrestacion(Cuadernos dato);

    List getMedNoe(Cuadernos dato);

    List getSalidasSalmiNutri(Cuadernos dato);

    List getSalidasSalmiOtro(Cuadernos dato);

    List getSalidasSalmiExt(Cuadernos dato);

    List getSelecionMed(Cuadernos dato);

    List getInvMed(Cuadernos dato);

    // actividad
    List getListarActividad(Actividad actividad);

    List getListarActividadTot(Actividad actividad);

    List getListarActividadTodos(Actividad actividad);

    Actividad getDatosActividad(int id_actividad);

    void setCrearActividad(Actividad actividad);

    void setModificarActividad(Actividad actividad);

    void setEliminarActividad(Actividad actividad);

    // quirofanos
    List getListarQuirofanos(Quirofanos quirofano);

    List getListarQuirofanosLibres(Quirofanos quirofano);

    List getListarQuirofanosLibres1(Quirofanos quirofano);

    List getListarQuirofanosLibres2(Quirofanos quirofano);

    Quirofanos getDatosQuirofano(Quirofanos quirofano);

    void setCrearQuirofano(Quirofanos quirofano);

    void setCrearReservaQuirofano(Quirofanos quirofano);

    void setModificarQuirofano(Quirofanos quirofano);

    void setEliminarQuirofano(Quirofanos quirofano);

    List getAtencionFicha(Pacientes dato);

    void setCrearFicha(Pacientes paciente);

    // farmacias
    List getListarFarmacias(Farmacias farmacia);

    List getListarFarmaciasAsig(Farmacias farmacia);

    List getListarFarmaciasHosp(Farmacias farmacia);

    Farmacias getDatosFarmacia(Farmacias farmacia);

    void setCrearFarmacia(Farmacias farmacia);

    void setModificarFarmacia(Farmacias farmacia);

    void setEliminarFarmacia(Farmacias farmacia);

}
