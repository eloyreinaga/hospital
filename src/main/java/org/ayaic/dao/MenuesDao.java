package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Menues;

@Mapper
public interface MenuesDao {

    List getListar_Enlaces();

    Menues getCategoria(String id_categoria);

    Menues getEnlace(int id_enlace);

    int setCrearEnlace(Menues enlace);

    int setEliminarEnlace(Menues enlace);

    int setModificarEnlace(Menues enlace);

    List getListaEnlaces(int id_categoria);

    List getListaCategorias();

    //Admnistrar menues
    void setCrearMenu(Menues menu);

    void setEliminarMenu(Menues menu);

    Menues getUsrRolEnlace(Menues menu);

    List getListaUsrRolEnlaces(Menues menu);
    //Fin Administrar menues

    //Administrar roles de usuarios
    List getListaUsrRoles(Menues usuario);

    Menues getUsrRol(Menues menu);

    void setCrearUsrRol(Menues rol);

    void setModificarUsrRol(Menues rol);

    void setEliminarUsrRol(Menues rol);
    //Fin Administrar roles de usuarios

    //Admnistrar categoria
    int setEliminarCategoria(Menues categoria);

    int setModificarCategoria(Menues categoria);

    int setCrearCategoria(Menues categoria);

    int getBuscarIdCategoriaRepetido(Menues categoria);
    //Fin Administrar categorias  

}
