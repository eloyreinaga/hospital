package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Prestaciones;

@Mapper
public interface PrestacionesDao {

    List getListarPresOdon(Prestaciones dato);

    List getListarPresOdonGen(Prestaciones dato);///G

    List getListarNivelPrestacion(int id_prestacion);

    void setCrearNivelPaque(Prestaciones dato);

    void setEliminarNivelPaque(Prestaciones dato);

    Prestaciones getDatosPrestacion(int id_prestacion);

    List getListarPrestacionesSumi(Prestaciones dato);

    List getListarPrestacionesSumiH(Prestaciones dato);///H  

    List getListarMedicamentosSumi(Prestaciones dato);

    void setCrearPrestacion(Prestaciones dato);

    void setModificarPrestacion(Prestaciones dato);

    void setHabilitaPrestacion(Prestaciones dato);///H

    void setEliminarPrestacion(Prestaciones dato);

    List getListarPrestacionesCot(Prestaciones dato);

    List getListarPrestacionesCot22(Prestaciones dato);

    List getListarPrestaciones(Prestaciones dato);

    List getListarPrestaciones22(Prestaciones dato);

    List getListarPrestacionesDes(Prestaciones dato);

    List getListarPrestacionesDes22(Prestaciones dato);

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

    List getListarPacientesPrestacionesPedi(Prestaciones dato);///P

    List getListarPacientesPrestacionesMayor(Prestaciones dato);///A

    List getListarPacientesPrestacionesMuje(Prestaciones dato);///M
}
