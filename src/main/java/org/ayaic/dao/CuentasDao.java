package org.ayaic.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import org.ayaic.domain.Cuentas;

@Mapper
public interface CuentasDao {

    List getListarLibroMayor(Cuentas dato);

    List getListarLibroDiario(int id_librodiario);

    List getListarCuentasCot();

    List getListarCuentasNom(String cuenta);

    List getListarCuentas();

    Cuentas getDatosCuenta(int id_cuenta);

    void setCrearCuenta(Cuentas cuenta);

    void setModificarCuenta(Cuentas cuenta);

    void setEliminarCuenta(Cuentas cuenta);

    int getNumLibroDiario();

    List getListarTransacciones();

    Cuentas getDatosTransaccion(int id_transaccion);

    void setCrearTransaccion(Cuentas transaccion);

    void setModificarTransaccion(Cuentas transaccion);

    void setEliminarTransaccion(Cuentas transaccion);

    void setCrearLibroDiario(Cuentas transaccion);

    void setEliminarLibroDiario(Cuentas transaccion);
}
