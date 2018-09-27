package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Usuarios;

@Mapper
public interface UsuariosDao {

    Usuarios getDatosUsuario(int id_usuario);

    List getListarUsuariosUbicacionOrganica(Usuarios usuario);

    int getVerificarUsuario(Usuarios usuario);

    int setRegistrarNuevaClave(Usuarios usuario);

    List getListaUsuarios(Usuarios usuario);

    List getListaUsuariosNombre(Usuarios usuario);

    List getListaUsuariosLocal(Usuarios usuario);

    List getListaUsuariosLocal2(Usuarios usuario);

    List getListaUsuariosNombreLocal(Usuarios usuario);

    void setCrearUsuario(Usuarios usuario);
    
    void setCrearRegUsuario(Usuarios usuario);

    void setModificarUsuario(Usuarios usuario);

    void setEliminarUsuario(Usuarios usuario);

}
