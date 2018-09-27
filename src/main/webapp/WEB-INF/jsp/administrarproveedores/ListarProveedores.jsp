<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div style="font-size:15pt"> Administraci&oacute;n Proveedores</div>
<br>
<table>
  <tr>
  <form name=forma method=post action='<c:url value="/NuevoProveedor.do"/>'>
    <td colspan="2">
      <div>
       <a class="btn btn-primary" href="javascript:document.forma.submit();" >Nuevo</a>
       <input type=hidden name=accion value='Adicionar'>
    </div></td>
    </form>
  <tr>
</table>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NRO </th>
    <th bgcolor="#F2F2F2"> RAZON SOCIAL </th>
    <th bgcolor="#F2F2F2"> ENCARGADO </th>
    <th bgcolor="#F2F2F2"> DIRECCION </th>
    <th bgcolor="#F2F2F2"> NIT </th>
    <th bgcolor="#F2F2F2"> TELEFONOS </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    <th bgcolor="#F2F2F2"> KARDEX </th> 
    </tr>  
   <c:forEach var="lista" items="${listarProveedores}" varStatus="contador">
     <tr style="font-size:9pt">
       <td align="center"><c:out value="${contador.count}"/></td>
       <td><c:out value="${lista.razonsocial}"/></td>      
       <td><c:out value="${lista.encargado}"/></td>   
       <td><c:out value="${lista.direccion}"/></td>   
       <td><c:out value="${lista.nit}"/></td>   
       <td><c:out value="${lista.fonos}"/></td>   
     <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/NuevoProveedor.do"/>'>
       <td>     
         <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
  	 <input type="hidden" name="id_proveedor"      value=<c:out value="${lista.id_proveedor}"/> >
	 <input type="hidden" name="accion"            value='Modificar' >
	 <input type="hidden" name="sw"                value='1' >
       </td>
     </form>
     <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarProveedor.do"/>'>
       <td>     
         <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
         <input type="hidden" name="id_proveedor"      value=<c:out value="${lista.id_proveedor}"/> >
         <input type="hidden" name="accion"            value='Eliminar' >
         <input type="hidden" name="sw1"               value='1' >
       </td>
     </form>
     <form name=formaKa<c:out value="${contador.count}"/> method=post action='<c:url value="/ListarProveedores.do"/>'>
       <td>     
         <div><a class="btn btn-info btn-xs" href="javascript:document.formaKa<c:out value="${contador.count}"/>.submit();"> Kardex</a></div>
         <input type="hidden" name="id_proveedor"      value=<c:out value="${lista.id_proveedor}"/> >
         <input type="hidden" name="accion"            value='Kardex' >
         <input type="hidden" name="sw1"               value='1' >
       </td>
     </form>
    </tr>  
   </c:forEach>
</table>