package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Laboratorios;

@Mapper
public interface LaboratoriosDao {

    List getListarLaboratorios(Laboratorios laboratorio);

    List getDatosLaboratorioRayos(Laboratorios laboratorio);///X

    List getListarLabGrupo(Laboratorios laboratorio);////G

    List getListarLabGrupoDet(Laboratorios laboratorio);////D

    Laboratorios getDatosLaboratorio(Laboratorios laboratorio);

    Laboratorios getDatosLaboratorioNombre(Laboratorios laboratorio);

    void setCrearLaboratorio(Laboratorios laboratorio);

    void setCrearLaboratorioGrupo(Laboratorios laboratorio);

    void setCrearLaboratorioGrupoDet(Laboratorios laboratorio);

    void setModificarLaboratorio(Laboratorios laboratorio);

    void setEliminarLaboratorio(Laboratorios laboratorio);

    void setEliminarLaboratorioGrupo(Laboratorios laboratorio);

    void setEliminarLaboratorioGrupoDet(Laboratorios laboratorio);
}
