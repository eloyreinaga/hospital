<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div><a class="btn btn-success" href='ListarProveedores.do'>Volver</a></div>

<form name="adicionacat" method="POST">
 <center>
  <table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
      <th colspan="3"><center>ADMINISTRACION DATOS PROVEEDOR</center></th>
    </tr>    
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Razon Social  </td>
      <td><input type="text" name="razonsocial" maxlength="20" size="20" value="<c:out value="${datoproveedor.razonsocial}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Encargado  </td>
      <td><input type="text" name="encargado" maxlength="20" size="20" value="<c:out value="${datoproveedor.encargado}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Direccion  </td>
      <td><input type="text" name="direccion" maxlength="20" size="20" value="<c:out value="${datoproveedor.direccion}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Telefonos  </td>
      <td><input type="text" name="fonos" maxlength="20" size="20" value="<c:out value="${datoproveedor.fonos}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> NIT  </td>
      <td><input type="text" name="nit" maxlength="20" size="20" value="<c:out value="${datoproveedor.nit}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Email  </td>
      <td><input type="text" name="email" maxlength="20" size="20" value="<c:out value="${datoproveedor.email}"/>"></td>
    </tr> 
    <tr>
      <td bgcolor="#F2F2F2" align="right"> Ciudad  </td>
      <td><input type="text" name="ciudad" maxlength="20" size="20" value="<c:out value="${datoproveedor.ciudad}"/>"></td>
    </tr> 
    <c:if test="${sw == 1}">
      <tr>
        <td bgcolor="#F2F2F2" align="right">Estado  </td>
        <td>
          <c:if test="${id_estado == 'A'}">
            <input type=checkbox name="id_estado" value="A" <c:if test="${id_estado == 'A'}">checked</c:if>>
            Activo
          </c:if> 
          <c:if test="${id_estado == 'B'}">
            <input type=checkbox name="id_estado" value="A" <c:if test="${id_estado == 'A'}">checked</c:if>>
            Bloqueado
          </c:if> 
        </td>     
      </tr>
    </c:if>     
  </table>
  </center>   
  <center>
    <input type="submit" class="btn btn-primary btn-lg" value='Siguiente' onclick="document.adicionacat.accion1.value='Guardar';
								      document.adicionacat.action='<c:url value="/ConfirmarProveedor.do"/>'">
  </center>
    <input type="hidden" name='id_proveedor'   value='<c:out value="${id_proveedor}"/>'>
    <input type="hidden" name='accion'         value='<c:out value="${accion}"/>'>
    <input type="hidden" name='id_proveedor'   value='<c:out value="${datoproveedor.id_proveedor}"/>'>
    <input type="hidden" name='sw'             value='<c:out value="${sw}"/>'>
    <input type="hidden" name='recargado'      value='Si'>
    <input type="hidden" name='accion1'        value=''>
</form>
