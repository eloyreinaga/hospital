package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.ayaic.domain.Detalle;

@Mapper
public interface DetalleDao {

    List getListarDetalle(Detalle detalle);

    List getListarDetallePago(Detalle detalle);

    List getListarCobroDetalle(Detalle detalle);

    List getListarCobroDetalleInterFact(Detalle detalle);  /////I

    List getListarCobroDetalleInt(Detalle detalle);  ////N

    List getListarDetalleSaldo(Detalle detalle);

    List getListarDetalleGen(Detalle detalle);

    List getListarDetTotal(Detalle detalle); /////T

    List getListarDetIndividual(Detalle detalle); /////I  no se utiliza

    List getListarDetHistorial(Detalle detalle); ////F

    Detalle getDatosDetalle(Detalle detalle);

    void setCrearDetallePago(Detalle detalle);

    void setCrearDetalle(Detalle detalle);

    void setEliminarDetalle(Detalle detalle);

    void setEliminarDetalleLab(Detalle detalle); /////L
	
    void setModificarDetalle (Detalle detalle) ;

}
