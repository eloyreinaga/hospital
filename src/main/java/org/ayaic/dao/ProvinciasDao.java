package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Provincias;

@Mapper
public interface ProvinciasDao {

    List getListarProvincias();

    Provincias getDatosProvincia(int id_provincia);

    void setCrearProvincia(Provincias provincia);

    void setModificarProvincia(Provincias provincia);

    void setEliminarProvincia(Provincias provincia);

    List getListarPaisDepProv(Provincias provincia);

}
