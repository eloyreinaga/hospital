package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Eciviles;

@Mapper
public interface EcivilesDao {

    List getListarEciviles();

    Eciviles getDatosEcivil(int id_ecivil);

    void setCrearEcivil(Eciviles ecivil);

    void setModificarEcivil(Eciviles ecivil);

    void setEliminarEcivil(Eciviles ecivil);

}
