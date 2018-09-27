package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Rubros;

@Mapper
public interface RubrosDao {

    List getListarRubros(Rubros rubro);

    List getListarRubrosT(Rubros rubro);

    Rubros getDatosRubro(int id_rubro);

    void setCrearRubro(Rubros rubro);

    void setModificarRubro(Rubros rubro);

    void setEliminarRubro(Rubros rubro);

}
