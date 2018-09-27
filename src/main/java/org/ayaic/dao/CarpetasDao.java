package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Carpetas;

@Mapper
public interface CarpetasDao {

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
}
