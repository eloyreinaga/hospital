package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Farmacias;

@Mapper
public interface FarmaciasDao {

    List getListarFarmacias(Farmacias farmacia);

    List getListarFarmaciasAsig(Farmacias farmacia); ////A

    List getListarFarmaciasHosp(Farmacias farmacia); ////H

    Farmacias getDatosFarmacia(Farmacias farmacia);

    void setCrearFarmacia(Farmacias farmacia);

    void setModificarFarmacia(Farmacias farmacia);

    void setEliminarFarmacia(Farmacias farmacia);

}
