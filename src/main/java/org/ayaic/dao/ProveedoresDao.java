package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Proveedores;

@Mapper
public interface ProveedoresDao {

    List getListarProveedores(Proveedores proveedor);

    Proveedores getDatosProveedor(Proveedores proveedor);

    void setCrearProveedor(Proveedores proveedor);

    void setModificarProveedor(Proveedores proveedor);

    void setEliminarProveedor(Proveedores proveedor);

}
