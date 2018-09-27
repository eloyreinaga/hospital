package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Roles;

@Mapper
public interface RolesDao {

    Roles getBuscarRol(Roles rol);

    List getListarRoles();

    void setCrearRol(Roles rol);

    void setModificarRol(Roles rol);

    void setEliminarRol(Roles rol);

    List getListarRolesCliente(Roles rol);

    Roles getBuscarRolCliente(Roles rol);

    List getListarAlmacenesCliente(Roles rol);

    Roles getBuscarAlmacenCliente(Roles rol);

}
