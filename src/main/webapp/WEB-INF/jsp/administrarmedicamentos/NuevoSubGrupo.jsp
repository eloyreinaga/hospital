<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<div><a  class="btn btn-success" href='ListarMedicamentos.do'>Volver</a></div>

<form name="adicionargrupo" method="POST" action='<c:url value="/ConfirmarMedicamento.do"/>' > 
<center>    
 <center>
  <table class="table table-striped table-bordered table-condensed table-responsive">
    <tr>
      <th colspan="3" bgcolor="#F2F2F2"><center>INTRODUZCA DATOS <c:out value="${datoItem.cadena17}"/></center></th>
    </tr>  
     <tr bgcolor="#F2F2F2">   
             <td align="right"> Nombre <c:out value="${datoItem.cadena16}"/></td>    
	     <td>
               <SELECT NAME="id_grupo" onchange="javascript:document.adicionargrupo.submit();">    
                  <c:forEach var="grupo" items="${listarGrupo}">
                       <option value="<c:out value="${grupo.id_medicamento}"/>"<c:if test="${grupo.id_medicamento == id_grupo}">selected</c:if>> 
                           <c:out value="${grupo.cadena1}"/>
                       </option>
                  </c:forEach>
              </SELECT>           
             </td>
          </tr> 
    <tr>
      <td  align="right" bgcolor="#F2F2F2"> Nombre <c:out value="${datoItem.cadena17}"/> </td>
      <td><input type="text" name="subgrupo" maxlength="50" size="50" value="<c:out value="${subgrupo}"/>"></td>
    </tr>       
  </table>
  </center>   
  <center> 
    <input type="submit"  class="btn btn-primary btn-lg" value='Grabar' onclick="document.adicionargrupo.accion1.value='GuardarS';
								      document.adicionargrupo.action='<c:url value="/GrabarMedicamento.do"/>'">
  </center>
    
    <input type="hidden" name='id_subgrupo'  value='<c:out value="${id_subgrupo}"/>'>  
    <input type="hidden" name='id_grupo'     value='<c:out value="${id_grupo}"/>'>  
    <input type="hidden" name='accion1'      value=''>
    <input type="hidden" name='recargado'    value='Si'>
</form>
   

 <table class="table table-striped table-bordered table-hover table-condensed table-responsive">
    <tr>
      <th colspan="11" style="font-size: 14pt;"><c:out value="${rubro}"/></th>
    </tr>
  <tr style="font-size:9pt">
    <th bgcolor="#F2F2F2"> NO </th>
    <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena16}"/> </th>
    <th bgcolor="#F2F2F2"> <c:out value="${datoItem.cadena17}"/> </th>
    <th bgcolor="#F2F2F2"> MODIFICAR </th>
    <th bgcolor="#F2F2F2"> ELIMINAR </th> 
    </tr>  
   <c:forEach var="lista" items="${listarSGrupo}" varStatus="contador"> 
     <tr style="font-size:9pt">
       <td><c:out value="${contador.count}"/></td>
       <td><c:out value="${lista.cadena1}"/></td>   
       <td><b><c:out value="${lista.cadena2}"/></b></td>     
       <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/ConfirmarMedicamento.do"/>'>
         <td>     
           <div><a class="btn btn-warning btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Modificar</a></div>
  	   <input type="hidden" name="id_subgrupo"     value='<c:out value="${lista.id_programa}"/>' >
           <input type="hidden" name="subgrupo"        value='<c:out value="${lista.cadena2}"/>' >
	   <input type="hidden" name="accion1"         value='ModificarSG'>
         </td>
       </form>
       <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/GrabarMedicamento.do"/>'>
         <td>     
           <div><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();"> Eliminar</a></div>
           <input type="hidden" name="id_subgrupo"   value='<c:out value="${lista.id_programa}"/>'>
           <input type="hidden" name="id_grupo"      value='<c:out value="${id_grupo}"/>'>
           <input type="hidden" name="accion1"       value='EliminarSG' >
       </td>
     </form>
    </tr>   
   </c:forEach>
 </table>