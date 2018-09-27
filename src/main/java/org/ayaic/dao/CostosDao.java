package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Costos;

@Mapper
public interface CostosDao {

    List getReporteCobro(Costos costo);

    List getListarCostos(Costos costo);

    List getListarCostosLabSumi(Costos costo);

    List getListarCostosLabora(Costos costo);

    List getListarLabosMedicoConf(Costos costo);

    List getListarLabosMedico(Costos costo);

    List getListarTodos(Costos costo);

    List getListarNombreCosto(Costos costo);

    List getListarCostosRx(Costos costo);

    List getListarCostosEco(Costos costo);

    List getListarNombreCosto22(Costos costo);

    Costos getDatosCosto(Costos costo);

    void setCrearCosto(Costos costo);

    void setModificarCosto(Costos costo);

    void setEliminarCosto(Costos costo);

    List getListarCostosRubro(Costos costo);

    List getListarCostosRubroFact(Costos costo);

    List getListaRubro();
}
