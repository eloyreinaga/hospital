package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Historiales;

@Mapper
public interface HistorialesDao {

    void setCrearReservacion(Historiales dato);
    
    void setCrearReservacionFicha(Historiales dato);

    void setCrearInterconsulta(Historiales dato);///I

    void setCrearInternado(Historiales dato);

    void setCrearInternado2(Historiales dato);///A

    void setCrearInternadoEmerg(Historiales dato);///E

    void setCrearMorbilidad(Historiales dato);

    void setEliminarReserva(Historiales dato);

    void setEliminarHistoria(Historiales dato);

    void setEliminarMorbilidad(Historiales dato);

    void setEliminarMorbilidadFarma(Historiales dato);///K

    void setEliminaLab(Historiales dato);

    void setEliminaPedidoLab(Historiales dato);

    void setModificaLab(Historiales dato);

    void setModificaVigencia(Historiales dato);

    void setModificarPagoReserva(Historiales dato);

    void setModificarMorbilidad(Historiales dato);

    void setModificarInter(Historiales dato);

    void setModificarInterFecha(Historiales dato);///F

    void setModificarInterReceta(Historiales dato);///R

    void setModificarInterDat(Historiales dato);

    void setModificarInterDatHisto(Historiales dato);///F

    void setModificarInterAltaHisto(Historiales dato);///R

    void setModificarSignosReserva(Historiales dato);

    void setModificarReservaConsultorio(Historiales dato);

    void setModificarReservaVigencia(Historiales dato);///V

    void setModificarReservaTotal(Historiales dato);///T

    void setModificarEstadoHistorial(Historiales dato);

    void setModificarInternado(Historiales dato);

    void setModificarInternadoFecha(Historiales dato);///A

    void setModificarEmergencias(Historiales dato);///E

    List getListarReservaciones(Historiales dato);

    List getListarReservaciones1(Historiales dato);///A

    List getListarReservacionesPersona(Historiales dato);///P

    List getListarReservacionesCIE10(Historiales dato);///C

    List getListarReservacionesCIE10Consul(Historiales dato);///L

    List getListarReservacionesCIE10ConsulE(Historiales dato);///E

    List getListarReservacionesMedico(Historiales dato);///M

    List getListarReservacionesConsul(Historiales dato);///S

    List getListarReservacionesEstadFecha(Historiales dato);///R

    List getListarReservacionesHemo(Historiales dato);///1

    List getListarReservacionesUcaLuo(Historiales dato);///2

    List getListarReservacionesUcaLuoF(Historiales dato);///3

    List getListarReservacionesConsul2(Historiales dato);///4

    List getListarReservacionesResid(Historiales dato);///R

    List getListarReservacionesResidConsul(Historiales dato);///O

    List getListarReservacionesResidConsulPerso(Historiales dato);///Q

    List getListarReservacionesConsulPublico(Historiales dato);///Y

    List getListarReservacionesCaja(Historiales dato);///V

    List getListarReservacionesNombreCaja(Historiales dato);///B

    List getListarReservacionesConsulMedicoPublico(Historiales dato);///D

    List getListarReservacionesCount(Historiales dato);

    List getListarReservacionesCountMed(Historiales dato);

    List getListarReservacionesAsig(Historiales dato);
    
    List getListarReservacionesInternet(Historiales dato);

    List getListarReserFichas(Historiales dato);

    List getListarVigencia(Historiales dato);

    List getListarVigenciaMedico(Historiales dato);///M

    List getListarVigenciaConsul(Historiales dato);///S

    List getListarVigenciaFicha(Historiales dato);///F

    List getListarVigenciaHab(Historiales dato);///H

    List getListarVigenciaHabCambioConsul(Historiales dato);///C

    List getListarCobroReserva(Historiales dato);

    List getListarCobroReservaSignos(Historiales dato);

    List getListarCobroReservaUni(Historiales dato);

    List getListarReservaSignosCNS(Historiales dato);

    List getListarReservaSignosCNS_SC(Historiales dato);

    List getListarReservaSignosCNS_SCUrg(Historiales dato);

    List getListarAtendidos(Historiales dato);

    List getListarAtendidosCNS(Historiales dato);///C

    List getListarAtendidosTot(Historiales dato);///T

    List getListarAtendidosFarNom(Historiales dato);///N

    List getListarAtendidosPend(Historiales dato);///P

    List getListarAtendidosEnf(Historiales dato);

    List getListarAtendidosI(Historiales dato);

    List getListarAtendidosITot(Historiales dato);///T

    List getListarAtendidosIFarNom(Historiales dato);///N

    List getListarAtendidosICNS(Historiales dato);///C

    List getListarHistoria(Historiales dato);

    List getListarHistoriaHoy(Historiales dato);///H

    List getListarHistoriaInterHoy(Historiales dato);///I

    List getListarHistoriaMed(Historiales dato);///M

    List getListarHistoriaEmergenGen(Historiales dato);///G

    List getListarHistoriaEmergen(Historiales dato);///E

    List getListarAtendidoHoy(Historiales dato);///A

    List getListarHistoriaTodo(Historiales dato);///T

    List getListarHistoriaI(Historiales dato);

    List getUltHistoriaInter(Historiales dato);///B

    List getHistoriaInterIndi(Historiales dato);///I

    List getHistoriaInter(Historiales dato);

    List getHistoriaInterEnfer(Historiales dato);///E

    List getHistoriaInterMedico(Historiales dato);///M

    List getHistoriaInterMedicoReceta(Historiales dato);///R

    List getListaMorbi(Historiales dato);

    int setRegistrarHistorial(Historiales dato);

    Historiales getDatosHistorial(Historiales dato);

    Historiales getDatosHistorialUlt(Historiales dato);///U

    Historiales getDatosHistorialUltHisto(Historiales dato);///G

    Historiales getDatosReserva(Historiales dato);///R

    Historiales getDatosEmergencias(Historiales dato);///E

    Historiales getDatosEmergenciaUltimo(Historiales dato);///F

    Historiales getDatosEmergencia(Historiales dato);///I

    Historiales getDatosEmergenciaSolo(Historiales dato);///O

    void setModificarEstadoHistoria(Historiales dato);

    void setModificarRangoHistoria(Historiales dato);

    List getListarKardexPaciente(Historiales dato);

    List getListarPacientesHistoNeo(Historiales dato);///N

    List getListarPacientesHistoMuje(Historiales dato);///N

    List getListarPacientesHistoPedi(Historiales dato);///P

    List getListarPacientesHistoMayor(Historiales dato);///A

    List getListarHistoriaGen(Historiales dato);

    List getListarHistoriaMicro(Historiales dato);

    List getListarResumenPrestacion(Historiales dato);

    List getListarResumenPrestacion2(Historiales dato);

    List getListarResumenPrestacionYa(Historiales dato);

    List getListarPacientesHistoGeneral(Historiales dato);

    List getListarPacientes19(Historiales dato);

    List getListarPacientes19T(Historiales dato);

    List getListarPacientesSpam(Historiales dato);
//  int getNum(int id_paciente) ;

    List getResumenEconomico(Historiales dato);

    List getResumenEconomicoLab(Historiales dato);///L

    List getResumenEconomicoEco(Historiales dato);///E

    List getResumenEconomicoTot(Historiales dato);///T

    List getHistorialAtendidos(Historiales dato);

    List getHistorialAtendidosEco(Historiales dato);///E

    List getHistorialAtendidosH(Historiales dato);///H

    List getHistorialAtendidosHemo(Historiales dato);///1

    List getHistorialAtendidosResid(Historiales dato);///T

    List getHistorialAtendidosResidConsul(Historiales dato);///O

    List getHistorialAtendidosResidNombre(Historiales dato);///B

    List getHistorialAtendidosResidConsulPer(Historiales dato);///Q

    List getInternadosCajaObservacionHemo(Historiales dato);///2

    List getInternadosCajaObservacion(Historiales dato);///P

    List getInternadosCajaObservacionBuscar(Historiales dato);///U

    List getInternadosCajaObservacionPiso(Historiales dato);///V

    List getInternadosCajaObservacionPisoPer(Historiales dato);///W

    List getHistorialAtendidosFecha(Historiales dato);///F

    List getAtendidosInter(Historiales dato);

    List getAtendidosInterSala(Historiales dato);

    List getHistorialLibros(Historiales dato);

    List getHistorialAtendidosP(Historiales dato);

    List getAtendidosGeneral(Historiales dato);

    List getAtendidosMicronutriente(Historiales dato);

    List getSumi(Historiales dato);

    List getInternados(Historiales dato);

    List getInternadosSala(Historiales dato);///S

    List getVigenciaFecha2(Historiales dato);///2

    List getVigenciaFecha(Historiales dato);///F

    List getRecetasInternados(Historiales dato);///I

    List getRecetasInternadosNombre(Historiales dato);///N

    List getInternadosSalaCajaNombre(Historiales dato);///M

    List getInternadosSalaCajaAltaNombre(Historiales dato);///B

    List getRecetasInternadosUnico(Historiales dato);///U

    List getVigencia_now(Historiales dato);///V

    List getVigencia_now_triaje(Historiales dato);///T

    List getInternadosPisoCaja(Historiales dato);///P

    List getInternadosCaja(Historiales dato);///Q

    List getInternadosPisoCajaVacio(Historiales dato);///W

    List getListaHistoriaImp(int rango);
}
