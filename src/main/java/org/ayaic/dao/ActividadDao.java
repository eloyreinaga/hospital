package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Actividad;

@Mapper
public interface ActividadDao {

    List getListarActividad(Actividad actividad);

    List getListarActividadTot(Actividad actividad);

    List getListarActividadTodos(Actividad actividad);

    Actividad getDatosActividad(int id_actividad);

    void setCrearActividad(Actividad actividad);

    void setModificarActividad(Actividad actividad);

    void setEliminarActividad(Actividad actividad);

}
