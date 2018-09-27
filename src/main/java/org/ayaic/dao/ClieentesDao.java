package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Clieentes;

@Mapper
public interface ClieentesDao {

    List getListarClientes(Clieentes cliente);

    Clieentes getDatosCliente(Clieentes cliente);

    void setCrearCliente(Clieentes cliente);

    void setModificarCliente(Clieentes cliente);

    void setEliminarCliente(Clieentes cliente);

}
