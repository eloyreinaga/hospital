package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Sexos;

@Mapper
public interface SexosDao {

    List getListarSexos();

    Sexos getDatosSexo(int id_sexo);

    void setCrearSexo(Sexos sexo);

    void setModificarSexo(Sexos sexo);

    void setEliminarSexo(Sexos sexo);

}
