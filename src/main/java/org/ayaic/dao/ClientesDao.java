package org.ayaic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ayaic.domain.Clientes;
import org.ayaic.domain.Usuarios;

@Mapper
public interface ClientesDao {

    Clientes getBuscarConexion(Usuarios usuario);

    Clientes getComprobarUsuario(Usuarios usuario);

}
