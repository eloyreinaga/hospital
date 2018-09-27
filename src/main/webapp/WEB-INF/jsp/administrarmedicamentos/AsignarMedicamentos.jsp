<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../Superior.jsp" %>
<script language='JavaScript' SRC="./validar.js"></script>

<table class="table table-striped table-bordered table-hover table-condensed table-responsive">  
  <tr>
    <td width="50%" valign="top">
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive">  
  <tr>
      <th colspan="5" style="font-size:12pt">Lista de Medicamentos asignados al Usuario</th>
  </tr>   
  <tr style="font-size:8pt">
    <th> Codigos </th>
    <th> Medicamento </th>
    <th> Forma </th>                
    <th> Concentra </th>        
    <th> Eliminar </th>
    </tr>  
   <c:forEach var="listado" items="${listarMedicamentos}" varStatus="contador">
     <tr style="font-size:8pt">
       <td align="center"><font color="red"><c:out value="${contador.count}"/></font>-<c:out value="${listado.id_medicamento}"/><br><font color="blue"><c:out value="${listado.codsumi}"/></font></td>
       <td><c:out value="${listado.medicamento}"/></td>      
       <td><c:out value="${listado.forma_far}"/></td>          
       <td><c:out value="${listado.concentra}"/></td>  
     <form name=formaE<c:out value="${contador.count}"/> method=post action='<c:url value="/AsignarMedicamentos.do"/>'>
       <td>     
         <div class="modificar"><a class="btn btn-danger btn-xs" href="javascript:document.formaE<c:out value="${contador.count}"/>.submit();">Eliminar</a></div>
         <input type="hidden" name='cod_esta'       value='<c:out value="${listado.b1}"/>'>           
  	 <input type="hidden" name="id_medicamento" value=<c:out value="${listado.id_medicamento}"/> >
         <input type="hidden" name="medicamento"    value=<c:out value="${listado.medicamento}"/> >
         <input type="hidden" name="accion"         value='eliminar' >
	 <input type="hidden" name="sw"             value='1'>
       </td>
     </form>
    </tr> 
   </c:forEach>
    
</table>
    </td>

    <td width="50%" valign="top">
         <form name=forma action="<c:url value="/AsignarMedicamentos.do"/>" method="POST">        
         <table class="table table-striped table-bordered table-hover table-condensed table-responsive"> 
          <tr>    
            <td align=right>Nombre Medicamento</td>    	
            <td ><input class="form-control" type="text" name="nombre"  value="<c:out value="${nombres}"/>"  maxlength=20 onblur='validar=(nombres,"A ")'/></td>            
            <td ><input style="font-size:11pt" class="btn btn-success" type="submit" value="Buscar"></td>
          </tr>  
         </table>
  <input type="hidden" name="accion2" value='Adicionar2' >
</form>      
   
  <table class="table table-striped table-bordered table-hover table-condensed table-responsive">  
  <tr style="font-size:8pt">
    <th> Codigos </th>
    <th> Medicamento </th>
    <th> Forma </th>                
    <th> Concentra </th>            
    <th> Agregar </th>
    </tr>  
   <c:forEach var="lista" items="${listarMedicamentosT}" varStatus="contador">
     <tr style="font-size:8pt">
       <td align="center"><c:out value="${lista.id_medicamento}"/><br><font color="blue"><c:out value="${lista.codsumi}"/></font></td>
       <td><c:out value="${lista.medicamento}"/></td>      
       <td><c:out value="${lista.forma_far}"/></td>          
       <td><c:out value="${lista.concentra}"/></td>   
     <form name=formaM<c:out value="${contador.count}"/> method=post action='<c:url value="/AsignarMedicamentos.do"/>'>
       <td>     
         <div class="modificar"><a class="btn btn-primary btn-xs" href="javascript:document.formaM<c:out value="${contador.count}"/>.submit();">Añadir</a></div>
         <input type="hidden" name="id_medicamento"   value=<c:out value="${lista.id_medicamento}"/> >
         <input type="hidden" name='medicamento'      value='<c:out value="${lista.medicamento}"/>'>
         <input type="hidden" name='forma_far'        value='<c:out value="${lista.forma_far}"/>'>                   
         <input type="hidden" name='concentra'        value='<c:out value="${lista.concentra}"/>'>                   
         <input type="hidden" name='codsumi'          value='<c:out value="${lista.codsumi}"/>'>                   
	 <input type="hidden" name="accion"           value='adicion' >
	 <input type="hidden" name="sw"               value='1' >
       </td>
     </form>
    </tr> 
   </c:forEach>

</table>
 
    </td>
  </tr>

</table>
