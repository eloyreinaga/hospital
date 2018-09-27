<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div><a  class="btn btn-success" href='ListarMedicamentos.do'>Volver</a></div>

<form name="adicionacat" method="POST">
 <center>
  <table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
      <th colspan="3" bgcolor="#F2F2F2"><center>INTRODUZCA DATOS <c:out value="${datoItem.cadena16}"/></center></th>
    </tr>  
    <tr>
      <td  align="right" bgcolor="#F2F2F2"> Nombre <c:out value="${datoItem.cadena16}"/> </td>
      <td><input type="text" name="grupo" maxlength="50" size="50" value="<c:out value="${grupo}"/>"></td>
    </tr>       
  </table>
  </center>   
  <center>
    <input type="submit"  class="btn btn-primary btn-lg" value='Grabar' onclick="document.adicionacat.accion1.value='Guardar';
								      document.adicionacat.action='<c:url value="/GrabarMedicamento.do"/>'">
  </center>
    <input type="hidden" name='accion1'   value=''>
    <input type="hidden" name='id_grupo'  value='<c:out value="${id_grupo}"/>'>  
    <input type="hidden" name='recargado' value='Si'>
</form>
   

 <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr>
      <th colspan="11" style="font-size: 14pt;"><c:out value="${rubro}"/></th>
    </tr>
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NO </th>
    <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena16}"/> </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    </tr>  
   <c:forEach var="lista" items="${listarGrupo}" varStatus="contador"> 
     <tr style="font-size:9pt">
       <td><c:out value="${contador.count}"/></td>
       <td><c:out value="${lista.cadena1}"/></td>     
       <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarMedicamento.do"/>'>
         <td>     
           <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
  	   <input type="hidden" name="id_grupo"   value='<c:out value="${lista.id_medicamento}"/>' >
           <input type="hidden" name="grupo"      value='<c:out value="${lista.cadena1}"/>' >
	   <input type="hidden" name="accion1"         value='ModificarG'>
         </td>
       </form>
       <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/GrabarMedicamento.do"/>'>
         <td>     
           <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
           <input type="hidden" name="id_grupo"   value='<c:out value="${lista.id_medicamento}"/>' >
           <input type="hidden" name="accion1"     value='EliminarG' >
       </td>
     </form>
    </tr>   
   </c:forEach>
 </table>